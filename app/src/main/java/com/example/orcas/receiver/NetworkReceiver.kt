package com.example.orcas.receiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import com.example.orcas.util.NetworkInfo
import com.thecode.aestheticdialogs.*

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver @Inject constructor(private val networkInfo: NetworkInfo)  : BroadcastReceiver(){
    var status: Int = 0
    var dialog: AestheticDialog.Builder? = null
    override fun onReceive(context: Context?, p1: Intent?) {

//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast. throw new UnsupportedOperationException("Not yet implemented");
        if (dialog == null)
            dialog = getErrorDialog(context)

        status = networkInfo.getConnectionType()
        if (status == 0) {
            dialog?.show()
        } else if (status != 0 && try {
                dialog?.alertDialog != null && dialog?.alertDialog?.isShowing!!
            }catch (e: Exception){
                    false
            })
            dialog?.dismiss()


        try {
            dialog?.alertDialog != null && dialog?.alertDialog?.isShowing!!
        }catch (e: Exception){

        }
    }

    private fun getErrorDialog(context: Context?): AestheticDialog.Builder {
        return AestheticDialog.Builder(context as Activity, DialogStyle.FLAT, DialogType.ERROR)
            .setTitle("حدث خطأ")
            .setMessage("من فضلك تأكد من الأتصال بالأنترنت اولا")
            .setCancelable(false)
            .setGravity(Gravity.CENTER)
            .setAnimation(DialogAnimation.SHRINK)
            .setOnClickListener(object : OnDialogClickListener {
                override fun onClick(dialog: AestheticDialog.Builder) {
                    dialog.dismiss()
                    if (status == 0)
                        (context as Activity).finish()
                }
            })

    }
}