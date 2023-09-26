package ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import model.ProductData

class EasykartApiImp : EasykartApi {
    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getProductList(): List<ProductData> {
        return httpClient
            .get {
                easykart("products")
            }
            .body()
    }

    private fun HttpRequestBuilder.easykart(path: String) {
        url {
            takeFrom("https://fakestoreapi.com/")
            encodedPath = path
        }
    }

}