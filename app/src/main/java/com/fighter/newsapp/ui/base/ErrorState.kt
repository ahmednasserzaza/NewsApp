package com.fighter.newsapp.ui.base

import com.fighter.newsapp.domain.utility.BadRequestException
import com.fighter.newsapp.domain.utility.InternalServerException
import com.fighter.newsapp.domain.utility.NetworkException
import com.fighter.newsapp.domain.utility.NewsAppException
import com.fighter.newsapp.domain.utility.NotFoundException
import com.fighter.newsapp.domain.utility.TooManyRequestsException
import com.fighter.newsapp.domain.utility.UnAuthorizedException

sealed interface ErrorState {
    data object InternalServer : ErrorState
    data object InvalidData : ErrorState
    data object UnAuthorized : ErrorState
    data object TooManyRequests : ErrorState
    data object NoConnection : ErrorState
    data object NotFound : ErrorState
}

fun handelApplicationExceptions(
    exception: NewsAppException,
    onError: (t: ErrorState) -> Unit,
) {
    when (exception) {
        is InternalServerException -> onError(ErrorState.InternalServer)
        is BadRequestException -> onError(ErrorState.InvalidData)
        is UnAuthorizedException -> onError(ErrorState.UnAuthorized)
        is TooManyRequestsException -> onError(ErrorState.TooManyRequests)
        is NetworkException -> onError(ErrorState.NoConnection)
        is NotFoundException -> onError(ErrorState.NotFound)
    }
}