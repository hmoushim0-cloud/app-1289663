package sa.factory.auto.parts.model

import kotlinx.serialization.Serializable

@Serializable
data class CarAccessoryItem(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class CarAccessoryOrder(
    val requestId: String,
    val items: List<CarAccessoryItem>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: CarAccessoryOrderStatus = CarAccessoryOrderStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class CarAccessoryOrderStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
