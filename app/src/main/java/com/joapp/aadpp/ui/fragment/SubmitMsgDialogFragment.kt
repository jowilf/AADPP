package com.joapp.aadpp.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.IntDef
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import com.joapp.aadpp.R
import kotlinx.android.synthetic.main.fragment_submit_dialog.*


class SubmitMsgDialogFragment : DialogFragment() {

    @IntDef(SUCCESS, FAILURE)
    annotation class MsgDialogType

    var type = SUCCESS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt("type", SUCCESS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        val rId = if (type == SUCCESS) R.drawable.ic_check else R.drawable.ic_warning
        imgView.setImageDrawable(AppCompatResources.getDrawable(requireContext(), rId))
        msg.setText(if (type == SUCCESS) R.string.submission_successful else R.string.submission_not_successful)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onStart() {
        super.onStart()
        initDialog()
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
        dialog.setCanceledOnTouchOutside(true)
    }


    companion object {
        const val SUCCESS = 0
        const val FAILURE = 1

        @JvmStatic
        fun newInstance(@MsgDialogType type: Int) =
            SubmitMsgDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt("type", type)
                }
            }
    }
}