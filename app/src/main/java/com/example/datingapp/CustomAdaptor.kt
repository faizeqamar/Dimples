package com.example.datingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdaptor (data:ArrayList<DemoItemList>, var context: Context):RecyclerView.Adapter<CustomAdaptor.viewHolder>(){

    var data:List<DemoItemList>
    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var layout = LayoutInflater.from(context).inflate(R.layout.dumy_item_list,parent,false)
        return viewHolder(layout)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.tvName.text = data[position].tvName
        holder.tvLocation.text = data[position].tvLocation
        holder.profile_img.setImageResource(data[position].profile_img)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class viewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {


        var tvName:TextView
        var tvLocation:TextView
        var profile_img:ImageView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvLocation = itemView.findViewById(R.id.tvLocation)
            profile_img = itemView.findViewById(R.id.profile_img)

        }
    }
}