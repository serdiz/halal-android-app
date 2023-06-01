package ro.serdiz.se.presentation.category

import android.widget.ImageView
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
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem

@Composable

@ExperimentalMaterialApi

fun CategoryCard(
    category: Category,
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
                    data = category.img_url,
                    builder = {
                        // Optional: Add any additional configuration for image loading
                    }
                ),
                contentDescription = "Category Image",
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
                category.name?.let { categoryName ->
                    Text(
                        text = categoryName,
                        color = DarkGray,
                        fontSize = 25.sp
                    )
                }
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