package com.example.lastfm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastfm.R
import com.example.lastfm.core.ui.ItemClickCallback
import com.example.lastfm.core.ui.loadCircularImage
import com.example.lastfm.data.models.entity.ArtistEntity
import kotlinx.android.synthetic.main.item_artist.view.*

class ArtistsAdapter(
    private val itemClickCallback: ItemClickCallback<ArtistEntity>,
    private val items: MutableList<ArtistEntity>,
    private val listenerString: String

) :
    RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_artist,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val perfomer = items[position]
        holder.iv_avatar.loadCircularImage(perfomer.imagePath)
        holder.tv_name.text = perfomer.name
        holder.tv_listeners.text = String.format(listenerString, perfomer.listeners)
        holder.itemView.setOnClickListener {
            itemClickCallback.OnItemClick(
                perfomer,
                holder.adapterPosition
            )
        }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val iv_avatar: ImageView = itemView.iv_avatar
        val tv_name: TextView = itemView.tv_name
        val tv_listeners: TextView = itemView.tv_listeners
    }

    fun addItems(list: List<ArtistEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


}
