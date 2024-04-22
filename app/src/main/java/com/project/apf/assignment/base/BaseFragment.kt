package com.project.apf.assignment.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T:ViewBinding>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private var _binding:T?=null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=bindView(view)
    }

    abstract fun bindView(view: View): T

}