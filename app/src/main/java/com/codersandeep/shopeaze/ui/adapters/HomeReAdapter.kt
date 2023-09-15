package com.codersandeep.shopeaze.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.squareup.picasso.Picasso

class HomeReAdapter(private val products: List<ProductsItem>, private val listener: RecyclerViewEvent) : RecyclerView.Adapter<HomeReAdapter.HomeReViewHolder>() {

    interface RecyclerViewEvent{
        fun onItemClick(position: Int)
    }
    inner class HomeReViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val productImage = itemView.findViewById<ImageView>(R.id.product_image)
        val productCategory = itemView.findViewById<TextView>(R.id.product_category)
        val productName = itemView.findViewById<TextView>(R.id.product_name)
        val productPrice = itemView.findViewById<TextView>(R.id.product_price)

        init {
            itemView.setOnClickListener (this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeReViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerview_item_layout, parent, false)
        return HomeReViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size;
    }

    override fun onBindViewHolder(holder: HomeReViewHolder, position: Int) {
        holder.productName.text = products[position].title
        holder.productPrice.text = "$" + products[position].price
        holder.productCategory.text = products[position].category

        Picasso.get()
            .load(products[position].image)
            .error(R.drawable.ic_brokenimage)
            .into(holder.productImage)
    }
}

