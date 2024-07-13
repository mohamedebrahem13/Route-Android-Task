package com.example.route_android_task.domain.inetractor

import com.example.route_android_task.common.Resource
import com.example.route_android_task.domain.models.Dimensions
import com.example.route_android_task.domain.models.Meta
import com.example.route_android_task.domain.models.Product
import com.example.route_android_task.domain.models.Review
import com.example.route_android_task.domain.repository.IProductRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetProductsUseCaseTest {

    private lateinit var productRepository: IProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setUp() {
        productRepository = mock(IProductRepository::class.java)
        getProductsUseCase = GetProductsUseCase(productRepository)
    }

    @Test
    fun `invoke should return Loading and then Success with products`() = runTest {
        // Given
        val mockProducts = listOf(
            Product(
                id = 1,
                title = "Product 1",
                description = "Description 1",
                category = "Category 1",
                price = 100.0,
                discountPercentage = 10.0,
                discountedPrice = 90.0,
                rating = 4.5,
                stock = 10,
                tags = listOf("tag1", "tag2"),
                brand = "Brand 1",
                sku = "SKU001",
                weight = 500,
                dimensions = Dimensions(10.0, 5.0, 20.0),
                warrantyInformation = "1 year",
                shippingInformation = "Free shipping",
                availabilityStatus = "In stock",
                reviews = listOf(
                    Review(4, "Great product", "2024-07-13", "User1", "user1@example.com")
                ),
                returnPolicy = "No return policy",
                minimumOrderQuantity = 1,
                meta = Meta(
                    createdAt = "2024-07-01",
                    updatedAt = "2024-07-10",
                    barcode = "1234567890",
                    qrCode = "abcdef123456"
                ),
                images = listOf("image1.jpg"),
                thumbnail = "thumbnail1.jpg"
            ),
            Product(
                id = 2,
                title = "Product 2",
                description = "Description 2",
                category = "Category 2",
                price = 120.0,
                discountPercentage = 15.0,
                discountedPrice = 100.0,
                rating = 4.0,
                stock = 20,
                tags = listOf("tag3", "tag4"),
                brand = "Brand 2",
                sku = "SKU002",
                weight = 600,
                dimensions = Dimensions(15.0, 6.0, 25.0),
                warrantyInformation = "2 years",
                shippingInformation = "Standard shipping",
                availabilityStatus = "Out of stock",
                reviews = listOf(
                    Review(4, "Good product", "2024-07-14", "User2", "user2@example.com")
                ),
                returnPolicy = "Limited return policy",
                minimumOrderQuantity = 2,
                meta = Meta(
                    createdAt = "2024-06-01",
                    updatedAt = "2024-07-12",
                    barcode = "0987654321",
                    qrCode = "123456abcdef"
                ),
                images = listOf("image2.jpg"),
                thumbnail = "thumbnail2.jpg"
            )
        )
        `when`(productRepository.getProducts()).thenReturn(mockProducts)

        // When
        val flowResult = getProductsUseCase.invoke()

        // Then
        val result = flowResult.toList()
        assertEquals(result.size, 2)

        val loadingState = result[0]
        assertEquals(loadingState, Resource.loading<List<Product>>())

        val successState = result[1]
        assertEquals(successState, Resource.success(mockProducts))
    }

    @Test
    fun `invoke should return Loading and then Error with message`() = runTest {
        // Given
        val errorMessage = "Network error"
        `when`(productRepository.getProducts()).thenThrow(RuntimeException(errorMessage))

        // When
        val flowResult = getProductsUseCase.invoke()

        // Then
        val result = flowResult.toList()
        assertEquals(result.size, 2)

        val loadingState = result[0]
        assertEquals(loadingState, Resource.loading<List<Product>>())

        val errorState = result[1]
        assertEquals(errorState, Resource.error<List<Product>>(errorMessage))
    }
}