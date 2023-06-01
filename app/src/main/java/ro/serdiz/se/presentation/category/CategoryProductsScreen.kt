package ro.serdiz.se.presentation.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ro.serdiz.se.components.ProductCard
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.presentation.product_details.components.ProductDetailsTopBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryProductsScreen(
    navigateBack: () -> Unit,
    id: String,
    products: List<ProductItem>,
    navigateToProductDetailsScreen: (product: ProductItem) -> Unit,
    ) {
    Scaffold(
        topBar = {
            ProductDetailsTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(
                    items = products ,
                ) { product ->
                    ProductCard(
                        product = product,
                        onClick = {
                            navigateToProductDetailsScreen(product)
                        }
                    )
                }
            }
        }
    )
}

