package com.project.apf.assignment.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class  BaseActivity<T:ViewBinding>:AppCompatActivity() {

    private var _binding:T?=null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=inflateLayout()
        setContentView(binding.root)
    }

    abstract  fun inflateLayout(): T
}