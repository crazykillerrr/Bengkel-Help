package com.bengkelhelp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(private var notificationList: List<NotificationItem>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    fun updateData(newList: List<NotificationItem>) {
        notificationList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notificationList[position]
        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title
        holder.message.text = item.message
    }

    override fun getItemCount(): Int = notificationList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.iv_icon)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val message: TextView = itemView.findViewById(R.id.tv_message)
    }
}
