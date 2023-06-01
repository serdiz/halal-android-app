package ro.serdiz.se.domain
import ro.serdiz.se.data.repository.ProductRepo
import ro.serdiz.se.domain.model.ProductItem
import javax.inject.Inject

class getProductUseCase @Inject constructor(private val productRepo: ProductRepo) {

    suspend operator fun invoke(): List<ProductItem> {
        return productRepo.getProducts().shuffled()
    }

}