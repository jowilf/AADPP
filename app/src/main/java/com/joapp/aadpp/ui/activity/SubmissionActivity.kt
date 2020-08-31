package com.joapp.aadpp.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joapp.aadpp.R
import com.joapp.aadpp.network.FormService
import com.joapp.aadpp.ui.fragment.ConfirmationDialogFragment
import com.joapp.aadpp.ui.fragment.SubmitMsgDialogFragment
import kotlinx.android.synthetic.main.activity_submission.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmissionActivity : AppCompatActivity(), ConfirmationDialogFragment.ConfirmationListener {
    lateinit var confirmationDialog: ConfirmationDialogFragment
    lateinit var progressDialog: ProgressDialog
    lateinit var sucessDialog: SubmitMsgDialogFragment
    lateinit var failureDialog: SubmitMsgDialogFragment
    val formService = FormService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)
        init()
    }

    fun init() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false);
        confirmationDialog = ConfirmationDialogFragment.newInstance()
        sucessDialog = SubmitMsgDialogFragment.newInstance(SubmitMsgDialogFragment.SUCCESS)
        failureDialog = SubmitMsgDialogFragment.newInstance(SubmitMsgDialogFragment.FAILURE)
        backIcon.setOnClickListener { onBackPressed() }
        btnSubmit.setOnClickListener {
            if (validate())
                confirmationDialog.show(supportFragmentManager, ConfirmationDialogFragment.TAG)
            else
                Toast.makeText(this, getString(R.string.invalid_data), Toast.LENGTH_LONG).show()
        }
    }

    fun validate(): Boolean {
        if (firstName.text.isNullOrEmpty()) return false
        if (lastName.text.isNullOrEmpty()) return false
        if (emailAdress.text.isNullOrEmpty()) return false
        if (githubLink.text.isNullOrEmpty()) return false
        return true
    }

    override fun onConfirm() {
        progressDialog.show()
        formService.submit(
            emailAdress.text.toString(),
            firstName.text.toString(),
            lastName.text.toString(),
            githubLink.text.toString()
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                progressDialog.dismiss()
                if (response.isSuccessful)
                    sucessDialog.show(supportFragmentManager, "success")
                else
                    failureDialog.show(supportFragmentManager, "failure")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                progressDialog.dismiss()
                failureDialog.show(supportFragmentManager, "failure")
                Log.e("submit error", t.message, t)
            }
        })
    }

}