package com.example.shoppinglist_2.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem) {
    shopListRepository.editShopItem(shopItem)
    }
}