package ro.serdiz.se.presentation.category


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.serdiz.se.domain.getCategoryUseCase
import ro.serdiz.se.domain.getProductUseCase
import ro.serdiz.se.domain.model.Category
import ro.serdiz.se.domain.model.ProductItem
import javax.inject.Inject


@HiltViewModel
class CategoryListView @Inject constructor(private val getCategoryUseCase: getCategoryUseCase) : ViewModel() {
    private val _categories = MutableStateFlow(emptyList<Category>())
    val categories: StateFlow<List<Category>> get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {

        viewModelScope.launch {

            try {

                val categories = getCategoryUseCase()
                _categories.value = categories
                println(_categories.value + " list view")

            } catch (_E: Exception) {
                println(_E)
            }

        }

    }
}