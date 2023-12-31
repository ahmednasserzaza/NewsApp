package com.fighter.newsapp.domain.utility

open class NewsAppException : Exception()
class InternalServerException : NewsAppException()
class BadRequestException : NewsAppException()
class UnAuthorizedException : NewsAppException()
class TooManyRequestsException : NewsAppException()
class NetworkException : NewsAppException()
class NotFoundException : NewsAppException()