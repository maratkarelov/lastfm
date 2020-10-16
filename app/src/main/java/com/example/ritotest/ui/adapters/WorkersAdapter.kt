package com.example.ritotest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ritotest.R
import com.example.ritotest.core.ui.ItemClickCallback
import com.example.ritotest.core.ui.loadCircularImage
import com.example.ritotest.data.models.entity.PerfomerEntity
import kotlinx.android.synthetic.main.item_perfomer.view.*

class WorkersAdapter(
    private val itemClickCallback: ItemClickCallback<PerfomerEntity>,
    private val items: MutableList<PerfomerEntity>,
    private val listenerString: String

) :
    RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_perfomer,
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

    fun addInteractions(list: List<PerfomerEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


}
