package com.project.apf.assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.project.apf.domain.MediaUseCase
import com.project.apf.network.models.MediaItem
import com.project.apf.network.models.MediaResponse
import com.project.apf.network.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MediaVm @Inject constructor(mediaUseCase: MediaUseCase) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    val mediaStates = mediaUseCase().cachedIn(viewModelScope)

}