package com.codersandeep.shopeaze.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.squareup.picasso.Picasso

class CartReAdapter(private val products: List<ProductsItem>, private val listener: RecyclerViewEvent) : RecyclerView.Adapter<CartReAdapter.CartReViewHolder>() {
    interface RecyclerViewEvent{
        fun onItemClick(productId: Int)
    }
    inner class CartReViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val productImage = itemView.findViewById<ImageView>(R.id.product_image_cart)
        val productName = itemView.findViewById<TextView>(R.id.product_name_cart)
        val productPrice = itemView.findViewById<TextView>(R.id.product_price_cart)
        val deleteButton = itemView.findViewById<AppCompatImageButton>(R.id.delete_from_cart_button)

        init {
            deleteButton.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(products[adapterPosition].id)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartReViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_recyclerview_item, parent, false)
        return CartReViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CartReViewHolder, position: Int) {

        Log.d(LOG_TAG,position.toString())
        holder.productName.text = products[position].title
        holder.productPrice.text = "$" + products[position].price

        Picasso.get()
            .load(products[position].image)
            .error(R.drawable.ic_brokenimage)
            .into(holder.productImage)
    }
}