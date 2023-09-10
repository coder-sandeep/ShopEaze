package com.codersandeep.shopeaze.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.models.Products
import com.squareup.picasso.Picasso

class HomeReAdapter(private val products: Products) : RecyclerView.Adapter<HomeReViewHolder>() {
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

class HomeReViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val productImage = itemView.findViewById<ImageView>(R.id.product_image)
    val productCategory = itemView.findViewById<TextView>(R.id.product_category)
    val productName = itemView.findViewById<TextView>(R.id.product_name)
    val productPrice = itemView.findViewById<TextView>(R.id.product_price)
}