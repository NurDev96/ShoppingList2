package com.example.shoppinglist_2.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItemId: Int)

    fun editShopItem(shopItem: ShopItem)

    fun getShopList(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}
