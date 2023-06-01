package ro.serdiz.se.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.getProductUseCase
import ro.serdiz.se.domain.model.ProductItem
import javax.inject.Inject


@HiltViewModel
class ProductListView @Inject constructor(private val getProductUseCase: getProductUseCase) : ViewModel() {
    private val _products = MutableStateFlow(emptyList<ProductItem>())
    val products: StateFlow<List<ProductItem>> get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch {

            try {

                val products = getProductUseCase()
                _products.value = products
                println(_products.value + " list view")

            } catch (_E: Exception) {
                println(_E)
            }

        }

    }
}