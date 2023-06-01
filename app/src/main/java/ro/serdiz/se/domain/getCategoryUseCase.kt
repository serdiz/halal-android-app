package ro.serdiz.se.domain
import ro.serdiz.se.data.repository.ProductRepo
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.ProductItem
import javax.inject.Inject

class getCategoryUseCase @Inject constructor(private val productRepo: ProductRepo) {

    suspend operator fun invoke(): List<Category> {
        return productRepo.getCategories().shuffled()
    }

}