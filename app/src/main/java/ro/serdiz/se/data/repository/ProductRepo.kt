package ro.serdiz.se.data.repository


import ro.serdiz.se.data.api.ApiInterface
import ro.serdiz.se.data.api.ProductService
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.domain.model.toProductItem
import javax.inject.Inject



class ProductRepo @Inject constructor(private val productService: ProductService) {

    suspend fun getProducts(): List<ProductItem> {
        return productService.getProducts().map {
            it.toProductItem()
        }
    }

    suspend fun getCategories(): List<Category> {
        return productService.getCategories()
    }

}