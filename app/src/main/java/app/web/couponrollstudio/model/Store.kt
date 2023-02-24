package app.web.couponrollstudio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Store(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("is_public") val isPublic: String,
    @SerialName("icon_url") val iconUrl: String,
    @SerialName("img_url") val imgUrl: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)