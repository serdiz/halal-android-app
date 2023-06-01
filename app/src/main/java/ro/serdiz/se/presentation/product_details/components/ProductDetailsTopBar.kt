package ro.serdiz.se.presentation.product_details.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import ro.serdiz.se.core.Constants.PRODUCT_DETAILS_SCREEN

@Composable
fun ProductDetailsTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = PRODUCT_DETAILS_SCREEN
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}