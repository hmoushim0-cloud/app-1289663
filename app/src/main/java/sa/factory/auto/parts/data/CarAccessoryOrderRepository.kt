package sa.factory.auto.parts.data

import sa.factory.auto.parts.model.CarAccessoryOrder
import sa.factory.auto.parts.model.CarAccessoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface CarAccessoryOrderRepository {
    fun getAvailableCatalog(): Flow<List<CarAccessoryItem>>
    fun getActiveRequest(): Flow<CarAccessoryOrder?>
    suspend fun submitRequest(request: CarAccessoryOrder): Result<String>
}

class InMemoryCarAccessoryOrderRepository : CarAccessoryOrderRepository {
    private val _catalog = MutableStateFlow<List<CarAccessoryItem>>(listOf(
        CarAccessoryItem("1", "قفل باب السيارة", "قفل إلكتروني مقاوم للسرقة مع ميزة التحكم عن بعد", 5000L, "verified"),
        CarAccessoryItem("2", "مكملات إضاءة LED", "أضواء LED عالية الكفاءة للداخل والخارج مع خيارات ألوان متعددة", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<CarAccessoryOrder?>(null)

    override fun getAvailableCatalog(): Flow<List<CarAccessoryItem>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<CarAccessoryOrder?> = _active.asStateFlow()

    override suspend fun submitRequest(request: CarAccessoryOrder): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
