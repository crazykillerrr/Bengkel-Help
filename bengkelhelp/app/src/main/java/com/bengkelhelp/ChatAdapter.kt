package com.bengkelhelp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private var chatList: List<ChatItem>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    fun updateData(newList: List<ChatItem>) {
        chatList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.tvName.text = chat.name
        holder.tvMessage.text = if (chat.lastMessage.isNotEmpty()) chat.lastMessage else "Belum ada pesan"
        holder.tvDate.text = chat.date
        holder.ivAvatar.setImageResource(chat.imageResId)
    }

    override fun getItemCount(): Int = chatList.size

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvMessage: TextView = itemView.findViewById(R.id.tv_message)
        val tvDate: TextView = itemView.findViewById(R.id.tv_time)
        val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
    }
}
