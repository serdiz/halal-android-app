package ro.serdiz.se.domain.model

data class Product (
    var halal_status: Long? = null,
    var product_name: String? = null,
    var product_img: String? = null,
    var product_description: String?= null,
    var subcategory_id: String?= null,
)
// id name price
