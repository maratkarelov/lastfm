package com.example.ritotest.network


sealed class Failure {
    class UnknownError(val message: String?) : Failure()
    class NetworkConnection() : Failure()
}