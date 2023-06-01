@file:OptIn(ExperimentalFoundationApi::class)

package ro.serdiz.se.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ro.serdiz.se.R
import ro.serdiz.se.components.ProductCard
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.presentation.category.CategoryCard
import ro.serdiz.se.presentation.profile.components.NavigationIcon

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    categories: List<Category>,
    navigateToProductSearchScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToCategoryProducts: (category: Category) -> Unit

) {
    val pagerState = androidx.compose.foundation.pager.rememberPagerState()
    val ad = listOf(
        R.drawable.img_10,
        R.drawable.img_9,
        R.drawable.img_8,
        R.drawable.img_7
    )
    val Green = Color(0xFF17C734)
    val scope = rememberCoroutineScope()
    Scaffold(

        bottomBar = {
            BottomAppBar(
                backgroundColor = Green,
                content = {
                    Row(
                        modifier = Modifier.padding(start = 45.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NavigationIcon(
                            icon = R.drawable.img_14,
                            text = "Поиск",
                            onClick = navigateToProductSearchScreen
                        )
                        NavigationIcon(
                            icon = R.drawable.ic_user,
                            text = "Профиль",
                            onClick = navigateToProfileScreen
                        )
                        NavigationIcon(
                            icon = R.drawable.ic_user,
                            text = "Home",
                            onClick = {}                        )
                    }
                }
            )
        },
        content = { padding ->

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "dr.halal",
                    style = MaterialTheme.typography.h6.copy(Green),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    text = "Kazakhstan",
                    style = MaterialTheme.typography.h6.copy(Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                )

                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    HorizontalPager(
                        pageCount = ad.size,
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { index ->
                        Image(
                            painter = painterResource(id = ad[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Go back"
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Go forward"
                            )
                        }
                    }
                }
                Text(
                    text = "Категории",
                    style = MaterialTheme.typography.h6.copy(Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(
                        items = categories ,
                    ) { category ->
                        CategoryCard(
                            category = category,
                            onClick = {
                                navigateToCategoryProducts(category)
                            }
                        )
                    }
                }
            }

        }
    )
        }






