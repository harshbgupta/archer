package co.si.main.z.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.si.core.utils.closeKeyboard
import co.si.main.databinding.ZBinding
import co.si.main.z.networking.ZViewModel
import corp.hell.kernel.parent.sup.SuperFragment
import corp.hell.kernel.constants.Args.ARG_PARAM1
import corp.hell.kernel.parent.BaseFragment

/**
 * A simple [SuperFragment] subclass.
 * Copyright © 2022 Hell Corporation. All rights reserved.
 *
 * @author Harsh Gupta aka Lucifer 😈
 * @since January 14, 2022
 */
class ZFragment : BaseFragment<ZBinding, ZViewModel>(ZBinding::inflate), View.OnClickListener {

    override val viewModel by viewModels<ZViewModel>()
    private var mPhone: String? = null

    /**
     * Life cycle method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPhone = it.getString(ARG_PARAM1, null)
        }
    }

    /**
     * Life Cycle Method
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        dataObserver()
        restOfCoding()
    }

    /**
     * Set all click listener here
     */
    private fun setClickListener() {
        binding.root.setOnClickListener(this)
    }

    /**
     * Called when a view has been clicked.
     */
    override fun onClick(v: View) {
        closeKeyboard(binding.root)
        when (v.id) {
            else -> {
            }
        }
    }

    /**
     * Rest of Coding starts here
     */
    private fun restOfCoding() {}

    /**
     * Put live data observer here
     */
    private fun dataObserver() {}
}