package com.example.shoppinglist_2.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist_2.data.ShopListRepositoryImpl
import com.example.shoppinglist_2.domain.*

class MainViewModel : ViewModel() {

    private val repo = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repo)
    private val editShopItemUseCase = EditShopItemUseCase(repo)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repo)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}
