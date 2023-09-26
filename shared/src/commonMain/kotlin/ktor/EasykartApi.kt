package ktor

import model.ProductData

interface EasykartApi {
    suspend fun getProductList(): List<ProductData>
}