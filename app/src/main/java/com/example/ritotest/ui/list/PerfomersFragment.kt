package com.example.ritotest.ui.list

import android.widget.ArrayAdapter
import com.example.ritotest.R
import com.example.ritotest.core.ui.BaseFragment
import com.example.ritotest.core.ui.ItemClickCallback
import com.example.ritotest.data.models.entity.PerfomerEntity
import com.example.ritotest.data.models.response.Perfomer
import com.example.ritotest.ui.adapters.WorkersAdapter
import kotlinx.android.synthetic.main.perfomers.*
import org.koin.android.ext.android.inject

class PerfomersFragment : BaseFragment(), ItemClickCallback<PerfomerEntity> {
    private val viewModel: WorkersViewModel by inject()
    private lateinit var workersAdapter: WorkersAdapter
    override fun layoutId(): Int = R.layout.perfomers

    override fun initViews() {
        val countries : Array<String> = arrayOf("Italy","Spain", "Ukraine")
        spinner_country.adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, countries)
        workersAdapter =
            WorkersAdapter(this, mutableListOf(), getString(R.string.listeners_counter))

    }

    override fun observeViewModel() {
        viewModel.liveWorkers.observe(viewLifecycleOwner) {
        }
    }

    override fun OnItemClick(item: PerfomerEntity, position: Int) {
    }
}