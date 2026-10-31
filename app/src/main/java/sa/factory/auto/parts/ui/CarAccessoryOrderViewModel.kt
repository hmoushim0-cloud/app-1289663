package sa.factory.auto.parts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.auto.parts.data.CarAccessoryOrderRepository
import sa.factory.auto.parts.model.CarAccessoryOrder
import sa.factory.auto.parts.model.CarAccessoryItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarAccessoryOrderViewModel(
    private val repository: CarAccessoryOrderRepository
) : ViewModel() {
    val catalog: StateFlow<List<CarAccessoryItem>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<CarAccessoryOrder?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: CarAccessoryOrder) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
