package com.bengkelhelp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.models.Bengkel

class BengkelAdapter(
    private var bengkelList: List<Bengkel>,
    private val onItemClick: ((Bengkel) -> Unit)? = null
) : RecyclerView.Adapter<BengkelAdapter.BengkelViewHolder>() {

    // Untuk update data di adapter
    fun updateData(newList: List<Bengkel>) {
        bengkelList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BengkelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bengkel, parent, false)
        return BengkelViewHolder(view)
    }

    override fun onBindViewHolder(holder: BengkelViewHolder, position: Int) {
        val bengkel = bengkelList[position]
        holder.bind(bengkel)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(bengkel)
        }
    }

    override fun getItemCount(): Int = bengkelList.size

    class BengkelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_bengkel_name)
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_bengkel_image)

        fun bind(bengkel: Bengkel) {
            tvName.text = bengkel.name
            ivImage.setImageResource(bengkel.imageResId)
        }
    }
}
