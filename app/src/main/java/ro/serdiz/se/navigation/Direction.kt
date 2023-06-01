package ro.serdiz.se.navigation

import androidx.navigation.NavHostController
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.Product
import ro.serdiz.se.domain.model.ProductItem
import ro.serdiz.se.navigation.Screenl.ProductDetailsScreen
import ro.serdiz.se.navigation.Screenl.ProductSearchScreen
import ro.serdiz.se.presentation.HomeScreen

class Direction(
    navController: NavHostController
) {

    val navigateToProductSearchScreen: () -> Unit = {
        navController.navigate(ProductSearchScreen.route)
    }
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(Screenl.HomeScreen.route)
    }
    val navigateToProfileScreen: () -> Unit = {
        navController.navigate(Screenl.ProfileScreenl.route)
    }

    val navigateToProductDetailsScreen: (ProductItem) -> Unit = { product ->
        navController.navigate("${ProductDetailsScreen.route}/${product.id}"
        )
    }

    val navigateToCategoriesProducts: (Category) -> Unit = { category ->
        navController.navigate("${Screenl.CategoryProductScreen.route}/${category.id}"
        )
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}