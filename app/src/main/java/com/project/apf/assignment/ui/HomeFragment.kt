package com.project.apf.assignment.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.project.apf.assignment.R
import com.project.apf.assignment.adapter.MediaAdapter
import com.project.apf.assignment.base.BaseFragment
import com.project.apf.assignment.databinding.FragmentFirstBinding
import com.project.apf.network.result.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {


    override fun bindView(view: View): FragmentFirstBinding = FragmentFirstBinding.bind(view)
    private val viewModel by viewModels<MediaVm>()

    @Inject
    lateinit var adapter: MediaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMedia.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.mediaStates.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                    .collectLatest {
                        adapter.submitData(it)
                    }
            }
            launch {
                adapter.loadStateFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                    .collectLatest {
                        binding.progress.visibility =
                            if (it.refresh is LoadState.Loading) VISIBLE else GONE
                    }

            }
        }

    }

}