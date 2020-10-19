package com.example.lastfm.core.data


sealed class Failure {
    class UnknownError(val message: String?) : Failure()
    class NetworkConnection() : Failure()
}