package ro.serdiz.se.data.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.Product
import javax.inject.Inject


class ProductService @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            val products = apiInterface.getAllProducts()
            products.body() ?: emptyList()
        }
    }

    suspend fun getCategories(): List<Category> {
        return withContext(Dispatchers.IO) {
            val categories = apiInterface.getCategories()
            categories.body() ?: emptyList()
        }
    }

}