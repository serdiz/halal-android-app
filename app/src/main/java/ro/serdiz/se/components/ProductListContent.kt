package ro.serdiz.se.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem


@Composable
@ExperimentalMaterialApi
fun ProductListContent(
    padding: PaddingValues,
    productList: List<ProductItem>,
    navigateToProductDetailsScreen: (product: ProductItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(
            items = productList ,
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

