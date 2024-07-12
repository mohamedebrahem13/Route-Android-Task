package com.example.route_android_task.data.mapper

import com.example.route_android_task.common.Mapper
import com.example.route_android_task.data.models.ProductDTO
import com.example.route_android_task.domain.models.Dimensions
import com.example.route_android_task.domain.models.Meta
import com.example.route_android_task.domain.models.Product
import com.example.route_android_task.domain.models.Review

object ProductMapper: Mapper<ProductDTO, Product, Unit>(){
    override fun dtoToDomain(model: ProductDTO): Product {
        return Product(
            id = model.id ?: 0,
            title = model.title.orEmpty(),
            description = model.description.orEmpty(),
            category = model.category.orEmpty(),
            price = model.price ?: 0.0,
            discountPercentage = model.discountPercentage ?: 0.0,
            rating = model.rating ?: 0.0,
            stock = model.stock ?: 0,
            tags = model.tags.orEmpty(),
            brand = model.brand.orEmpty(),
            sku = model.sku.orEmpty(),
            weight = model.weight ?: 0,
            dimensions = Dimensions(
                width = model.dimensions?.width ?: 0.0,
                height = model.dimensions?.height ?: 0.0,
                depth = model.dimensions?.depth ?: 0.0
            ),
            warrantyInformation = model.warrantyInformation.orEmpty(),
            shippingInformation = model.shippingInformation.orEmpty(),
            availabilityStatus = model.availabilityStatus.orEmpty(),
            reviews = model.reviews?.map { review ->
                Review(
                    rating = review.rating ?: 0,
                    comment = review.comment.orEmpty(),
                    date = review.date.orEmpty(),
                    reviewerName = review.reviewerName.orEmpty(),
                    reviewerEmail = review.reviewerEmail.orEmpty()
                )
            } ?: emptyList(),
            returnPolicy = model.returnPolicy.orEmpty(),
            minimumOrderQuantity = model.minimumOrderQuantity ?: 0,
            meta = Meta(
                createdAt = model.meta?.createdAt.orEmpty(),
                updatedAt = model.meta?.updatedAt.orEmpty(),
                barcode = model.meta?.barcode.orEmpty(),
                qrCode = model.meta?.qrCode.orEmpty()
            ),
            images = model.images.orEmpty(),
            thumbnail = model.thumbnail.orEmpty()
        )
    }
    fun dtoListToDomainList(dtoList: List<ProductDTO>?): List<Product> {
        return dtoList?.map { dtoToDomain(it) } ?: emptyList()
    }
}