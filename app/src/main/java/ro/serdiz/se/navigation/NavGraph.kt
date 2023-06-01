import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ro.serdiz.se.core.Constants.CATEGORY_ID
import ro.serdiz.se.core.Constants.NO_PRODUCT_NAME
import ro.serdiz.se.core.Constants.PRODUCT_ID
import ro.serdiz.se.core.Constants.PRODUCT_NAME
import ro.serdiz.se.navigation.Direction
import ro.serdiz.se.navigation.Screenl.*
import ro.serdiz.se.presentation.HomeScreen
import ro.serdiz.se.presentation.category.CategoryListView
import ro.serdiz.se.presentation.category.CategoryProductsScreen

import ro.serdiz.se.presentation.forgot_password.ForgotPasswordScreen
import ro.serdiz.se.presentation.product_details.ProductDetailsScreen
import ro.serdiz.se.presentation.product_list.ProductListScreen
import ro.serdiz.se.presentation.product_list.ProductListView
import ro.serdiz.se.presentation.product_search.ProductSearchScreen
import ro.serdiz.se.presentation.profile.ProfileScreenl
import ro.serdiz.se.presentation.sign_in.SignInScreen
import ro.serdiz.se.presentation.sign_up.SignUpScreen
import ro.serdiz.se.presentation.verify_email.VerifyEmailScreen
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun NavGraph(     navController: NavHostController


) {
    val direction = remember(navController) { Direction(navController) }
    val productViewModel = viewModel(modelClass = ProductListView::class.java)
    val productsState by productViewModel.products.collectAsState()
    println("----------------------------------------------")
    println(productsState)

    val categoryViewModel = viewModel(modelClass = CategoryListView::class.java)
    val categoriesState by categoryViewModel.categories.collectAsState()


    AnimatedNavHost(
        navController = navController,
        startDestination = SignInScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                },
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreenl.route)
                },
                navigateToProductSearchScreen = {
                    navController.navigate(ProductSearchScreen.route)
                }
            )
        }
        composable(
            route = ProductListScreen.route
        ) {
            ProductListScreen(
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                products = productsState,
                navigateToProductDetailsScreen = { productName ->
                    direction.navigateToProductDetailsScreen(productName)
                },
                navigateToProfileScreen = {direction.navigateToProfileScreen()
                },
            )
        }
        composable(
            route = ProductSearchScreen.route
        ) {
            ProductSearchScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                products = productsState,
                navigateToProductDetailsScreen = { productName ->
                    direction.navigateToProductDetailsScreen(productName)
                },
                navigateToProductListScreen = {},
                navigateToProfileScreen = {direction.navigateToProfileScreen()} ,
                        navigateToProductSearchScreen = {},
                navigateToHomeScreen = {direction.navigateToHomeScreen()}
            )
        }
        composable(
            route = "${ProductDetailsScreen.route}/{$PRODUCT_ID}",
            arguments = listOf(
                navArgument(PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(PRODUCT_ID) ?: NO_PRODUCT_NAME
            val product = productsState.filter { product -> product.id == productId.toLong() }[0]
            ProductDetailsScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                product = product,
            )
        }
        composable(
            route = "${CategoryProductScreen.route}/{$CATEGORY_ID}",
            arguments = listOf(
                navArgument(CATEGORY_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString(CATEGORY_ID) ?: NO_PRODUCT_NAME
            val filteredProducts = productsState.filter { product ->
                product.subcategory_id.toString().startsWith(categoryId, ignoreCase = true) == true
            }
            CategoryProductsScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                id = categoryId,
                products = filteredProducts,
                navigateToProductDetailsScreen = { productName ->
                    direction.navigateToProductDetailsScreen(productName)
                },
            )
        }
        composable(
            route = ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = HomeScreen.route
        ) {
            HomeScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                categories = categoriesState,
                navigateToProfileScreen = {direction.navigateToProfileScreen()} ,
                navigateToProductSearchScreen = {direction.navigateToProductSearchScreen()},
                navigateToCategoryProducts = { category ->
                    direction.navigateToCategoriesProducts(category)
                }
            )
        }
        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = VerifyEmailScreen.route
        ) {
            VerifyEmailScreen(
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreenl.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = ProfileScreenl.route) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            ProfileScreenl(
                username = email ?: "",
                navigateToForgotPasswordScreen = {
                    navController.navigate(ForgotPasswordScreen.route)
                },
                navigateToProductSearchScreen = {
                    navController.navigate(ProductSearchScreen.route)
                },
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreenl.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(HomeScreen.route)
                },
            )
        }
    }
}
