package ro.serdiz.se.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.Response

typealias ProductList = List<Product>
typealias ProductListResponse = Response<ProductList>

interface ProductListRepository {
    fun getProductListFromFirestore(searchText: String): Flow<ProductListResponse>
}