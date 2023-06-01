package ro.serdiz.se.presentation.product_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.serdiz.se.R
import ro.serdiz.se.components.Message
import ro.serdiz.se.components.ProductListContent
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Constants.NO_PRODUCTS_FOUND
import ro.serdiz.se.core.Constants.NO_SEARCH
import ro.serdiz.se.core.Utils.Companion.printMessage
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.domain.model.Response.*
import ro.serdiz.se.navigation.Screenl
import ro.serdiz.se.presentation.product_list.ProductListViewModel
import ro.serdiz.se.presentation.product_search.components.ProductSearchTopBar
import ro.serdiz.se.presentation.profile.components.NavigationIcon

@Composable
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun ProductSearchScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    products: List<ProductItem>,
    navigateToProductDetailsScreen: (product: ProductItem) -> Unit,
    navigateToProductListScreen: () -> Unit,
    navigateToProductSearchScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit

) {
    var search by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(NO_SEARCH))

    }
    val Green = Color(0xFF17C734)


    Scaffold(
        topBar = {
            ProductSearchTopBar(
                search = search,
                onSearchTextChanged = { newSearchText ->
                    search = newSearchText
                },
                onCloseIconClick = {
                    search = TextFieldValue(NO_SEARCH)
                },
                navigateBack = navigateBack
            )
        },
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
                            onClick = navigateToHomeScreen
                        )
                    }
                }
            )

        },
        content = { padding ->
            val searchText = search.text
            var filtered_products: List<ProductItem> by remember(searchText, products) {
                mutableStateOf(products.filter { it.product_name?.contains(searchText, ignoreCase = true) == true })
            }

            LaunchedEffect(searchText) {
                println("Searching");
                viewModel.getProductList(searchText)
                filtered_products = products.filter { it.product_name?.contains(searchText, ignoreCase = true) == true }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                when (val productListResponse = viewModel.productListResponse) {
                    is Loading -> ProgressBar()
                    is Success -> {
                        if (filtered_products.isNotEmpty()) {
                            ProductListContent(
                                padding = padding,
                                productList = filtered_products,
                                navigateToProductDetailsScreen = navigateToProductDetailsScreen
                            )
                        } else {
                            if (searchText.isNotEmpty()) {
                                Message(
                                    text = NO_PRODUCTS_FOUND
                                )
                            }
                        }
                    }
                    is Error -> printMessage(productListResponse.message)
                    is Failure -> TODO()
                    else -> TODO()
                }
            }
        }
    )
}






