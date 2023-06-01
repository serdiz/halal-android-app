package ro.serdiz.se.presentation.product_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDetailsContent(
    padding: PaddingValues,
    productName: String,
    productDes: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = productName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = productDes,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ProductDetailsContentPrev() {
    ProductDetailsContent(padding = PaddingValues(20.dp, 20.dp), productName = "fgt", productDes = "gnt")
    
}