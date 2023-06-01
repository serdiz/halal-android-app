package ro.serdiz.se.domain.model

data class ProductItem (
    var id: Long? = null,
    var halal_status: Long? = null,
    var product_name: String? = null,
    var product_img: String? = null,
    var product_description: String?= null,
    var subcategory_id: Long?= null,
    )

fun Product.toProductItem() = ProductItem(id, halal_status, product_name, product_img, product_description,subcategory_id)