package com.kcode.utils

import android.app.Activity
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.kcode.R
import com.kcode.databinding.DialogAlertBinding
import com.kcode.utils.Utils.getDisplayWidthPixels


object DialogUtils {

    fun showNoInternetDialog(activity: Activity) {

        val width = (getDisplayWidthPixels(activity) * 0.80).toInt()
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        val binding = DialogAlertBinding.inflate(activity.layoutInflater)
        binding.txtAlertMsg.text = activity.getString(R.string.msg_no_internet)
        binding.imgAlert.setImageDrawable(activity.getDrawable(R.drawable.ic_round_wifi_off_24))
        binding.btnAlertOk.setOnClickListener {
            activity.finishAffinity()
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        val dialog: AlertDialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialog.window?.setLayout(width, height)
        dialog.show()

    }

    fun showInfoDialog(
        activity: Activity,
        img: Int = R.drawable.ic_round_help_outline_24,
        msg: String = activity.getString(R.string.msg_something_went_wrong),
        finish: Boolean = false
    ) {

        val width = (getDisplayWidthPixels(activity) * 0.80).toInt()
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        val binding = DialogAlertBinding.inflate(activity.layoutInflater)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        val dialog: AlertDialog = builder.create()

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialog.window?.setLayout(width, height)

        binding.txtAlertMsg.text = msg
        binding.imgAlert.setImageDrawable(activity.getDrawable(img))
        binding.btnAlertOk.setOnClickListener {
            if (finish)
                activity.finish()
            else
                dialog.dismiss()
        }

        dialog.show()

    }

}