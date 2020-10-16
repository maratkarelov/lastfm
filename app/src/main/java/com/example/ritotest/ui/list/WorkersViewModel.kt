package com.example.ritotest.ui.list

import androidx.lifecycle.MutableLiveData
import com.example.ritotest.core.data.UseCase
import com.example.ritotest.core.ui.BaseViewModel
import com.example.ritotest.data.dao.WorkerDao
import com.example.ritotest.data.models.entity.PerfomerEntity
import com.example.ritotest.data.models.response.Perfomer
import com.example.ritotest.network.usecases.WorkersUseCase

class WorkersViewModel(
    private val workerDao: WorkerDao,
    private val workersUseCase: WorkersUseCase
) : BaseViewModel() {
    val liveWorkers:MutableLiveData<List<PerfomerEntity>> = MutableLiveData()

    init {
        workersUseCase(UseCase.None()) {
            it.either({
                ::handleFailure
                handleWorkers(workerDao.getLocalWorkers())
            },
                { handleWorkers(it.workers) }
            )
        }
    }

    private fun handleWorkers(list: List<PerfomerEntity>) {
        liveWorkers.value = list
    }
}