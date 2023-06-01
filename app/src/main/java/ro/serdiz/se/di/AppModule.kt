package ro.serdiz.se.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.serdiz.se.core.Constants.PRODUCT_NAME
import ro.serdiz.se.core.Constants.PRODUCT
import ro.serdiz.se.data.repository.ProductListRepositoryImpl
import ro.serdiz.se.domain.repository.ProductListRepository
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideProductQuery(
        db: FirebaseFirestore
    ) = db.collection(PRODUCT).orderBy(PRODUCT_NAME)

    @Provides
    fun provideProductListRepository(
        productQuery: Query
    ): ProductListRepository = ProductListRepositoryImpl(productQuery)
}