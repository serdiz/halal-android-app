package ro.serdiz.se.domain.model

sealed class Respons<out T> {
    object Loadingl: Respons<Nothing>()

    data class Successl<out T>(
        val data: T
    ): Respons<T>()

    data class Failurel(
        val el: Exception
    ): Respons<Nothing>()
}