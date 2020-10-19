package com.example.lastfm.ui.list

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.lastfm.BuildConfig
import com.example.lastfm.R
import com.example.lastfm.core.Const
import com.example.lastfm.core.ui.BaseFragment
import com.example.lastfm.core.ui.ItemClickCallback
import com.example.lastfm.data.models.entity.ArtistEntity
import com.example.lastfm.ui.adapters.ArtistsAdapter
import com.example.lastfm.ui.details.AlbumsActivity
import kotlinx.android.synthetic.main.artists.*
import org.koin.android.ext.android.inject

class ArtistsFragment : BaseFragment(), ItemClickCallback<ArtistEntity> {
    private val viewModel: ArtistsViewModel by inject()
    private lateinit var artistsAdapter: ArtistsAdapter
    override fun layoutId(): Int = R.layout.artists

    override fun initViews() {
        switch_online.isVisible = BuildConfig.FLAVOR == "dev"
        val countries: Array<String> = arrayOf("Japan", "Italy", "Iceland")
        spinner_country.adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, countries)
        spinner_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.readArtists(switch_online.isChecked, countries[position])
                progressBar.isVisible = true
            }
        }
        artistsAdapter =
            ArtistsAdapter(this, mutableListOf(), getString(R.string.listeners_counter))
        rv.adapter = artistsAdapter
    }

    override fun observeViewModel() {
        viewModel.liveArtists.observe(viewLifecycleOwner) {
            artistsAdapter.addItems(it)
            progressBar.isVisible = false
        }
    }

    override fun OnItemClick(item: ArtistEntity, position: Int) {
        startActivity(
            Intent(
                requireActivity(),
                AlbumsActivity::class.java
            ).putExtras(bundleOf(Const.ARTIST to item))
        )
    }
}