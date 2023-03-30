package com.example.shoppinglist_2.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopList(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}
