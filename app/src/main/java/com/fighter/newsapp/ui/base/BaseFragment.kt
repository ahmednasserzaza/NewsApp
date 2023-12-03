package com.fighter.newsapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel

    private lateinit var _binding: VDB
    protected val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    override fun onPause() {
        super.onPause()
        if (isRemoving)
            _binding.unbind()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()
    }
}