package ro.serdiz.se.presentation.product_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.serdiz.se.presentation.product_details.components.ProductDetailsContent
import ro.serdiz.se.presentation.product_details.components.ProductDetailsTopBar

@Composable
fun ProductDetailsScreen(
    navigateBack: () -> Unit,
    productName: String,
    productDes: String
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
                productName = productName,
                productDes = productDes
            )
        }
    )
}

@Preview
@Composable
fun ProductDetailsScreenPrev() {
    ProductDetailsScreen(navigateBack = {}, productName = "FJNJN", productDes = "fhbr")
}
