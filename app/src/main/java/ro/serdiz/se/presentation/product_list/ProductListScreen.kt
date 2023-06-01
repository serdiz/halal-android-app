package ro.serdiz.se.presentation.product_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.serdiz.se.R
import ro.serdiz.se.components.ProductListContent
import ro.serdiz.se.components.ProgressBar
import ro.serdiz.se.core.Constants.NO_SEARCH
import ro.serdiz.se.core.Utils.Companion.printMessage
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.domain.model.Response.*
import ro.serdiz.se.presentation.product_list.components.ProductListTopBar
import ro.serdiz.se.presentation.profile.components.NavigationIcon

@Composable
@ExperimentalMaterialApi
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    products: List<ProductItem>,
    navigateToProductSearchScreen: () -> Unit,
    navigateToProductDetailsScreen: (product: ProductItem) -> Unit,

    navigateToProfileScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            ProductListTopBar(
                onSearchIconClick = navigateToProductSearchScreen
            )
        },
        content = { padding ->
            viewModel.getProductList(NO_SEARCH)
            when (val productListResponse = viewModel.productListResponse) {
                else -> {}
            }

                when(val productListResponse = viewModel.productListResponse) {
                is Loading -> ProgressBar()
                is Success -> ProductListContent(
                    padding = padding,
                    productList = products,
                    navigateToProductDetailsScreen = navigateToProductDetailsScreen
                )
                is Error -> printMessage(productListResponse.message)
                is Failure -> TODO()
                Loadings -> TODO()
                is Succes -> TODO()

            }
        }
    )
}