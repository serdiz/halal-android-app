package ro.serdiz.se.presentation.product_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.presentation.product_details.components.ProductDetailsContent
import ro.serdiz.se.presentation.product_details.components.ProductDetailsTopBar

@Composable
fun ProductDetailsScreen(
    navigateBack: () -> Unit,
    product: ProductItem,
) {
    Scaffold(
        topBar = {
            ProductDetailsTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ProductDetailsContent(
                padding = padding,
                product = product
            )
        }
    )
}

