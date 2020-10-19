package com.example.lastfm.ui.details

import androidx.lifecycle.MutableLiveData
import com.example.lastfm.core.ui.BaseViewModel
import com.example.lastfm.data.dao.AlbumsDao
import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.AlbumEntity
import com.example.lastfm.network.usecases.AlbumsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val albumsDao: AlbumsDao,
    private val albumsUseCase: AlbumsUseCase
) : BaseViewModel() {
    val liveAlbums: MutableLiveData<List<AlbumEntity>> = MutableLiveData()


    fun readAlbums(artist:String) {
        albumsUseCase(artist) {
            it.either({
                ::handleFailure
                CoroutineScope(Dispatchers.Default).launch {
                    handleAlbums(albumsDao.getLocalAlbums(artist))
                }
            },
                { handleAlbums(Convertable.convert(it.topalbums.album)) }
            )
        }
    }

    private fun handleAlbums(list: List<AlbumEntity>) {
        liveAlbums.postValue(list)
    }
}