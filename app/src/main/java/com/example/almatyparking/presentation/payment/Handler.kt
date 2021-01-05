package com.example.almatyparking.presentation.payment

interface Handler<T> {

    fun handle(result: T)

    fun error()

}