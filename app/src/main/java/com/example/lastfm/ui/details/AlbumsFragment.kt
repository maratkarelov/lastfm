package com.example.lastfm.ui.details

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.lastfm.R
import com.example.lastfm.core.Const
import com.example.lastfm.core.ui.BaseFragment
import com.example.lastfm.core.ui.setOnSingleClickListener
import com.example.lastfm.data.models.entity.ArtistEntity
import com.example.lastfm.ui.adapters.AlbumsAdapter
import kotlinx.android.synthetic.main.albums.*
import org.koin.android.ext.android.inject

class AlbumsFragment : BaseFragment() {
    private val viewModel: AlbumsViewModel by inject()
    private lateinit var albumsAdapter: AlbumsAdapter
    override fun layoutId(): Int = R.layout.albums

    override fun initViews() {
        iv_back.setOnSingleClickListener {
            requireActivity().onBackPressed()
        }
        albumsAdapter =
            AlbumsAdapter(mutableListOf(), getString(R.string.play_counter))
        rv.adapter = albumsAdapter
        rv.isNestedScrollingEnabled = false
    }

    override fun observeViewModel() {
        progressBar.isVisible = true
        arguments?.let {
            val artistEntity = it[Const.ARTIST] as ArtistEntity
            Glide.with(this)
                .load(artistEntity.imagePath)
                .into(iv_artist)
            tv_title.text = artistEntity.name
            viewModel.readAlbums(artistEntity.name)
            viewModel.liveAlbums.observe(viewLifecycleOwner) { albums ->
                albumsAdapter.addItems(albums)
                progressBar.isVisible = false
            }
        }
    }

}