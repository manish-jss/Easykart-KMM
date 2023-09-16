package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.ProductData


class ProductListViewModel : ViewModel() {
    private val _productList = MutableStateFlow<List<ProductData>>(emptyList())
    val productList = _productList.asStateFlow()
    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        loadProductList()
    }

    private fun loadProductList() {
        viewModelScope.launch {
            _productList.emit(getProductList())
        }
    }

    private suspend fun getProductList(): List<ProductData> {
        return httpClient
            .get("https://fakestoreapi.com/products")
            .body<List<ProductData>>()
    }

    override fun onCleared() {
        httpClient.close()
        super.onCleared()
    }

}