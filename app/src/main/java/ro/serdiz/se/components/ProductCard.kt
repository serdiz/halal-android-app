package ro.serdiz.se.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem

@Composable

@ExperimentalMaterialApi

fun ProductCard(
    product: ProductItem,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            ),
        elevation = 3.dp,
        onClick = onClick
    ) {
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
                    color = DarkGray,
                    fontSize = 25.sp
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
                product.product_description?.let { imageUrl ->
                    CoilImage(
                        data = imageUrl,
                        contentDescription = product.product_img ?: "",
                        modifier = Modifier.size(100 .dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }




@Composable
fun CoilImage(
    data: String,
    contentDescription: String,
    modifier: Modifier,
    contentScale: Any
) {}

//@Composable
//
//fun CoilImage(data: String, contentDescription: String, modifier: Modifier, contentScale: Any) {
//    TODO("Not yet implemented")
//}



//@OptIn(ExperimentalMaterialApi::class)
//@androidx.compose.ui.tooling.preview.Preview
//@Composable
//fun ProductCardPreview() {
//
//        ProductCard(product = Product()) {
//
//        }
//
//}