package com.example.testkvartira.ui.flatlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkvartira.R
import com.example.testkvartira.pojo.Flat
import com.example.testkvartira.ui.activity.FlatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.flat_list_item.view.*

class FlatListAdapter(private val context: Context) :
    RecyclerView.Adapter<FlatListAdapter.FlatViewHolder>() {

    var flatList: List<Flat> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FlatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flatDefaultPhotoIMV: ImageView = itemView.flat_default_photoIMV
        val flatPriceTV: TextView = itemView.flat_priceTV
        val flatAddressTV: TextView = itemView.flat_addressTV

        init {
            itemView.setOnClickListener {
                context.startActivity(
                    FlatActivity.newIntent(
                        context,
                        flatList[adapterPosition].id!!
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.flat_list_item,
            parent,
            false
        )
        return FlatViewHolder(view)
    }

    override fun getItemCount(): Int = flatList.size

    override fun onBindViewHolder(holder: FlatViewHolder, position: Int) {
        with(holder) {
            Picasso.get()
                .load(flatList[position].photoDefault?.url)
                .placeholder(R.drawable.ic_baseline_house_24)
                .into(flatDefaultPhotoIMV)
            flatPriceTV.text = flatList[position].prices?.day.toString()
            flatAddressTV.text = flatList[position].address?.trim()
        }
    }
}