package com.fighter.newsapp.domain

open class NewsAppException : Exception()
class InternalServerException : NewsAppException()
class BadRequestException : NewsAppException()
class UnAuthorizedException : NewsAppException()
class TooManyRequestsException : NewsAppException()
class NetworkException : NewsAppException()
class NotFoundException : NewsAppException()

sealed interface ErrorHandler {
    data object InternalServer : ErrorHandler
    data object InvalidData : ErrorHandler
    data object UnAuthorized : ErrorHandler
    data object TooManyRequests : ErrorHandler
    data object NoConnection : ErrorHandler
    data object NotFound : ErrorHandler
}

fun handelApplicationExceptions(
    exception: NewsAppException,
    onError: (t: ErrorHandler) -> Unit,
) {
    when (exception) {
        is InternalServerException -> onError(ErrorHandler.InternalServer)
        is BadRequestException -> onError(ErrorHandler.InvalidData)
        is UnAuthorizedException -> onError(ErrorHandler.UnAuthorized)
        is TooManyRequestsException -> onError(ErrorHandler.TooManyRequests)
        is NetworkException -> onError(ErrorHandler.NoConnection)
        is NotFoundException -> onError(ErrorHandler.NotFound)
    }
}