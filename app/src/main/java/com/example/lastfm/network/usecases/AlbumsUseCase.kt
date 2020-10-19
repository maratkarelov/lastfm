package com.example.lastfm.network.usecases

import com.example.lastfm.core.Const
import com.example.lastfm.core.data.Either
import com.example.lastfm.core.data.Failure
import com.example.lastfm.core.data.UseCase
import com.example.lastfm.data.dao.AlbumsDao
import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.AlbumEntity
import com.example.lastfm.data.models.response.AlbumsTopWrapper
import com.example.lastfm.network.ServerAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class AlbumsUseCase @Inject constructor(
    private val api: ServerAPI,
    private val albumsDao: AlbumsDao
) : UseCase<AlbumsTopWrapper, String>() {
    override suspend fun run(params: String): Either<Failure, AlbumsTopWrapper> {
        lateinit var response: Response<AlbumsTopWrapper>
        lateinit var entities:List<AlbumEntity>
        try {
            runBlocking(Dispatchers.Default) {
                response = api.readAlbums("artist.gettopalbums", params, Const.API_KEY, Const.JSON)
                    .execute()
                if (response.isSuccessful) {
                    response.body()?.topalbums.let {wrapper->
                        albumsDao.clearTable(params)
                        if (wrapper != null) {
                            entities = Convertable.convert(wrapper.album)
                            entities.forEach {
                                it.artist = params
                            }
                            albumsDao.storeList(entities)
                        }
                    }
                }
            }
            when (response.code()) {
                200 -> {
                    return Either.Right(response.body() as AlbumsTopWrapper)
                }
                else -> {
                    return Either.Left(
                        Failure.UnknownError(response.message())
                    )
                }
            }
        } catch (e: Exception) {
            if (e is UnknownHostException || e is ConnectException) {
                return Either.Left(Failure.NetworkConnection())
            } else {
                return Either.Left(Failure.UnknownError(e.message))
            }
        }

    }


}