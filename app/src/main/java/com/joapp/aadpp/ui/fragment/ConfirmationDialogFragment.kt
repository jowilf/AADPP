package com.joapp.aadpp.ui.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.joapp.aadpp.R
import kotlinx.android.synthetic.main.fragment_confirmation.*


class ConfirmationDialogFragment : DialogFragment() {

    var mListener: ConfirmationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        //setStyle(STYLE_NORMAL, R.style.AppDialogTheme)
    }

    override fun onStart() {
        super.onStart()
        initDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    fun initDialog() {
        val dialog = getDialog()!!
        val lp = dialog.window!!.attributes.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT//Utils.dp2px(150f)
            height = WindowManager.LayoutParams.MATCH_PARENT//Utils.dp2px(60f)
            //windowAnimations = R.style.LoadingDialog
        }
        dialog.window!!.attributes = lp
        dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun init() {
        btnYes.setOnClickListener {
            dismiss()
            mListener?.onConfirm()
        }
        btnClose.setOnClickListener { dismiss() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ConfirmationListener)
            mListener = context
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface ConfirmationListener {
        fun onConfirm()
    }

    companion object {
        const val TAG = "confirmationDialog"

        @JvmStatic
        fun newInstance() =
            ConfirmationDialogFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}