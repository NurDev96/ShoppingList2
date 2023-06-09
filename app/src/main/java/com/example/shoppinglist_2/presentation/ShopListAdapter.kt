package com.example.shoppinglist_2.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_2.R
import com.example.shoppinglist_2.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var count = 0
    var shopItemList = listOf<ShopItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            val callback = ShopListDiffUtil(shopItemList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("else not found $viewType")
        }
        val view =
            LayoutInflater.from(parent.context).inflate(
                layout,
                parent,
                false
            )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        Log.d("ShopListAdapter", "onBindViewHolder: ${++count}")
        val shopItem = shopItemList[position]
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
            true
        }
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()

    }

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""

    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)!!
        val tvCount = view.findViewById<TextView>(R.id.tv_count)!!
    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}
