package com.example.ritotest.network.usecases

import com.example.ritotest.data.models.WorkersWrapper
import com.example.ritotest.network.Failure
import com.example.ritotest.network.ServerAPI
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class WorkersUseCase @Inject constructor(
    private val api: ServerAPI
) : UseCase<WorkersWrapper, Any>() {
    override suspend fun run(params: Any): Either<Failure, WorkersWrapper> {
        try {
            val response = api.readInteractions().execute()
            return if (response.isSuccessful) {
                val wrapper = response.body() as WorkersWrapper
                Either.Right(wrapper)
            } else {
                return Either.Left(
                    Failure.UnknownError(response.message())
                )
            }
        } catch (e: Exception) {
            if (e is UnknownHostException || e is ConnectException) {
                return Either.Left(Failure.NetworkConnection())
            } else {
                return Either.Left(Failure.UnknownError(e.message))
            }
        }
    }


}