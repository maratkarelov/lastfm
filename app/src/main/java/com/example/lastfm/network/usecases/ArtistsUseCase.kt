package com.example.lastfm.network.usecases

import com.example.lastfm.core.Const
import com.example.lastfm.core.data.Either
import com.example.lastfm.core.data.Failure
import com.example.lastfm.core.data.UseCase
import com.example.lastfm.data.dao.ArtistsDao
import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.ArtistEntity
import com.example.lastfm.data.models.response.ArtistTopWrapper
import com.example.lastfm.network.ServerAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ArtistsUseCase @Inject constructor(
    private val api: ServerAPI,
    private val artistsDao: ArtistsDao
) : UseCase<ArtistTopWrapper, String>() {
    override suspend fun run(params: String): Either<Failure, ArtistTopWrapper> {
        lateinit var response: Response<ArtistTopWrapper>
        lateinit var entities:List<ArtistEntity>
        try {
            runBlocking(Dispatchers.Default) {
                response = api.readArtists("geo.gettopartists", params, Const.API_KEY, Const.JSON)
                    .execute()
                if (response.isSuccessful) {
                    response.body()?.topartists.let {wrapper->
                        artistsDao.clearTable(params)
                        if (wrapper != null) {
                            entities = Convertable.convert(wrapper.artist)
                            entities.forEach {
                                it.country = params
                            }
                            artistsDao.storeList(entities)
                        }
                    }
                }
            }
            when (response.code()) {
                200 -> {
                    return Either.Right(response.body() as ArtistTopWrapper)
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