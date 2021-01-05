package com.example.almatyparking.data

interface Handler<T> {

    fun handle(result: T)

    fun error()
}