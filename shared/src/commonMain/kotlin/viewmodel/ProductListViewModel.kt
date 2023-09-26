package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ktor.EasykartApiImp
import model.ProductData


class ProductListViewModel : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductData>>(emptyList())
    val productList = _productList.asStateFlow()


    init {
        loadProductList()
    }

    private fun loadProductList() {
        viewModelScope.launch {
            _productList.emit(EasykartApiImp().getProductList())
        }
    }


}