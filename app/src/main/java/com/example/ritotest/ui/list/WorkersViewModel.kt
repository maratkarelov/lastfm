package com.example.ritotest.ui.list

import androidx.lifecycle.MutableLiveData
import com.example.ritotest.core.data.UseCase
import com.example.ritotest.core.ui.BaseViewModel
import com.example.ritotest.data.dao.WorkerDao
import com.example.ritotest.data.models.entity.PerfomerEntity
import com.example.ritotest.network.usecases.WorkersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkersViewModel(
    private val workerDao: WorkerDao,
    private val workersUseCase: WorkersUseCase
) : BaseViewModel() {
    val liveWorkers: MutableLiveData<List<PerfomerEntity>> = MutableLiveData()


    fun readPerfomers(country: String) {
        workersUseCase(country) {
            it.either({
                ::handleFailure
                CoroutineScope(Dispatchers.Default).launch {
                    handleWorkers(workerDao.getLocalWorkers())
                }
            },
                { handleWorkers(it.workers) }
            )
        }

    }

    private fun handleWorkers(list: List<PerfomerEntity>) {
        liveWorkers.postValue( list)
    }
}