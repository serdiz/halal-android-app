package ro.serdiz.se.presentation.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ro.serdiz.se.domain.model.ProductItem

@Composable
fun ProductDetailsContent(
    padding: PaddingValues,
    product: ProductItem,
) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberImagePainter(
                data = product.product_img,
                builder = {
                }
            ),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(200.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 12.dp
                )
        ) {
            product.product_name?.let { productName ->
                Text(
                    text = productName,
                    color = Color.DarkGray,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 12.dp
                )
        ) {
            product.product_description?.let { product_description ->
                Text(
                    text = product_description,
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                )
            }
        }
    }

    }

