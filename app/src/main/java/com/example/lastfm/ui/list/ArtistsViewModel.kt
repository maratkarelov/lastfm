package com.example.lastfm.ui.list

import androidx.lifecycle.MutableLiveData
import com.example.lastfm.core.ui.BaseViewModel
import com.example.lastfm.data.dao.ArtistsDao
import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.ArtistEntity
import com.example.lastfm.network.usecases.ArtistsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistsViewModel(
    private val artistsDao: ArtistsDao,
    private val artistsUseCase: ArtistsUseCase
) : BaseViewModel() {
    val liveArtists: MutableLiveData<List<ArtistEntity>> = MutableLiveData()


    fun readArtists(online: Boolean, country: String) {
        if (online) {
            artistsUseCase(country) {
                it.either({
                    ::handleFailure
                    CoroutineScope(Dispatchers.Default).launch {
                        handleArtists(artistsDao.getLocalArtists(country))
                    }
                },
                    {
                        handleArtists(
                            Convertable.convert(it.topartists.artist)
                                .sortedBy { artist -> artist.name })
                    }
                )
            }
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                handleArtists(artistsDao.getLocalArtists(country))
            }
        }
    }

    private fun handleArtists(list: List<ArtistEntity>) {
        liveArtists.postValue(list)
    }
}