package ro.serdiz.se.data.api

import retrofit2.Response
import retrofit2.http.GET
import ro.serdiz.se.core.Constants.CATEGORIES_ENDPOINT
import ro.serdiz.se.core.Constants.PRODUCTS_ENDPOINT
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.Product

interface ApiInterface {
    @GET(PRODUCTS_ENDPOINT)
    suspend fun getAllProducts(): Response<List<Product>>

    @GET(CATEGORIES_ENDPOINT)
    suspend fun getCategories(): Response<List<Category>>
}