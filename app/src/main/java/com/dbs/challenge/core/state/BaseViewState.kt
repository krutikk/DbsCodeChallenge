package com.dbs.challenge.core.state

open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun isEmpty() = baseStatus == Status.EMPTY
    fun isSuccess() = baseStatus == Status.SUCCESS
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}