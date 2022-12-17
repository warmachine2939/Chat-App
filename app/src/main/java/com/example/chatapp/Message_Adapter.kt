package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class Message_Adapter(val context: Context,val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_RECEIVE=1
    val ITEM_SENT=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            //inflate receive
            val view: View= LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return receivedViewHolder(view)
        }
        else{
            //inflate send
            val view: View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return sentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if(holder.javaClass==sentViewHolder::class.java){
            //for sent view holder


            val viewHolder= holder as sentViewHolder
            holder.sentMessage.text=currentMessage.message
        }
        else{
            val viewHolder= holder as receivedViewHolder
            holder.receivedMessage.text=currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage=messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid==currentMessage.senderId){
            return ITEM_SENT
        }
        else
        {
            return ITEM_RECEIVE
        }
    }
    class sentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage= itemView.findViewById<TextView>(R.id.txt_sent_msg)
    }
    class receivedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receivedMessage= itemView.findViewById<TextView>(R.id.txt_received_msg)
    }


}
