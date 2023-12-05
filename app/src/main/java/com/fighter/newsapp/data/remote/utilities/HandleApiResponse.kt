package com.fighter.newsapp.data.remote.utilities

import com.fighter.newsapp.domain.utility.BadRequestException
import com.fighter.newsapp.domain.utility.InternalServerException
import com.fighter.newsapp.domain.utility.NetworkException
import com.fighter.newsapp.domain.utility.NotFoundException
import com.fighter.newsapp.domain.utility.TooManyRequestsException
import com.fighter.newsapp.domain.utility.UnAuthorizedException
import retrofit2.Response

fun <T> handleApiResponse(response: Response<T>): T {
    if (response.isSuccessful) {
        return response.body() ?: throw NotFoundException()
    } else {
        when (response.code()) {
            400 -> throw BadRequestException()
            401 -> throw UnAuthorizedException()
            429 -> throw TooManyRequestsException()
            500 -> throw InternalServerException()
            else -> throw NetworkException()
        }
    }
}