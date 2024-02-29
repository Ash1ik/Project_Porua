package com.example.porua
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyBookAdapter(private val currentUserId: String, private val booklist: ArrayList<Book>) :
    RecyclerView.Adapter<MyBookAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val BookName: TextView = itemView.findViewById(R.id.BookName)
        val WriterName: TextView = itemView.findViewById(R.id.WriterName)
        val Description: TextView = itemView.findViewById(R.id.Description)
        val Feedback: TextView = itemView.findViewById(R.id.Feedback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.BookName.text = booklist[position].bookName
        holder.WriterName.text = booklist[position].writerName
        holder.Description.text = booklist[position].bookDescription
        holder.Feedback.text = booklist[position].bookFeedback
    }

    override fun getItemCount(): Int {
        return booklist.size
    }
}