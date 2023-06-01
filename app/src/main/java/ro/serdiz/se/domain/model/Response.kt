package ro.serdiz.se.domain.model

sealed class Response<out T> {
    object Loading: Response<Nothing>()
    object Loadings: Response<Nothing>()


    data class Success<out T>(
        val data: T
    ): Response<T>()
    data class  Succes<out T>(
        val data: T?
    ): Response<T>()

    data class Error(
        val message: String
    ): Response<Nothing>()
    data class Failure(
        val e: Exception
    ): Response<Nothing>()
}