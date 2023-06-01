package ro.serdiz.se.components
import ro.serdiz.se.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

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
        Row() {
            Image(
                painter = rememberImagePainter(
                    data = product.product_img,
                    builder = {
                    }
                ),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(80.dp),
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
                        color = DarkGray,
                        fontSize = 20.sp
                    )
                }
            }


        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconRes = when (product.halal_status?.toInt()) {
                1 -> R.drawable.img_15
                2 -> R.drawable.img_19
                else -> R.drawable.img_16
            }
            Icon(
                painter = painterResource(iconRes),
                contentDescription = "Halal Status",
                modifier = Modifier.padding(
                    top = 8.dp,
                    end = 8.dp).size(20.dp)
            )
        }

    }
    }



