package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val context: MainActivity) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var userList = emptyList<UserData>()

    class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.Title)
        val description: TextView = itemView.findViewById(R.id.Description)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        val viewHolder = MyViewHolder(view)
        view.setOnClickListener {
            context.onItemClicked(userList[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holderMy: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holderMy.title.text = currentItem.title
        holderMy.description.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<UserData>) {
        this.userList = user
        notifyDataSetChanged()
    }

    fun onItemClicked(userData: UserData) {
/*
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(item.url))
        builder.setInstantAppsEnabled(true)
*/
    }
}