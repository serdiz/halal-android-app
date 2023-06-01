package ro.serdiz.se.data.repository

import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.Response.Error
import ro.serdiz.se.domain.model.Response.Success
import ro.serdiz.se.domain.repository.ProductListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductListRepositoryImpl @Inject constructor(
    private var productQuery: Query
): ProductListRepository {
    override fun getProductListFromFirestore(searchText: String) = callbackFlow {
        if (searchText.isNotEmpty()) {
            productQuery = productQuery.startAt(searchText).endAt("$searchText\uf8ff")
        }
        val snapshotListener = productQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val productList = snapshot.toObjects(Product::class.java)
                Success(productList)
            } else {
                Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}