package com.example.orcas.base

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.orcas.MainActivity
import com.example.orcas.ui.dialogs.LoadingIndicatorDialogFragment
import com.example.orcas.data.locale.SharedPreferenceCache
 import com.example.orcas.ui.dialogs.LocalNotificationsDialog
import com.example.orcas.ui.dialogs.LocalNotificationsType
 import com.example.orcas.util.LocalNotificationType
import com.example.orcas.util.NetworkInfo
import com.example.orcas.receiver.NetworkReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

interface LoadingIndicator {
    var loadingIndicator: LoadingIndicatorDialogFragment
    var isLoadingIndicatorOpen: Boolean
    fun showLoadingIndicator()
    fun hideLoadingIndicator()
}

interface OnUnauthorized {
    fun onUnauthorized()
}


@AndroidEntryPoint
open class BaseFragment : Fragment(), LoadingIndicator,
    OnUnauthorized {

    @Inject
    lateinit var sharedPreferenceCache: SharedPreferenceCache

    @Inject
    lateinit var networkReceiver: NetworkReceiver

    @Inject
    lateinit var networkInfo: NetworkInfo

    override fun onDestroy() {
        super.onDestroy()
        this.hideLoadingIndicator()

    }

    override fun onDetach() {
        super.onDetach()
        this.hideLoadingIndicator()
    }

    // LoadingIndicator
    override var loadingIndicator: LoadingIndicatorDialogFragment = LoadingIndicatorDialogFragment.getInstance()
    override var isLoadingIndicatorOpen: Boolean = false

    override fun showLoadingIndicator() {
        if (!loadingIndicator.isAdded) {
            Handler(Looper.getMainLooper()).post {
                runCatching {
                    this.loadingIndicator.showNow(this.parentFragmentManager, "")
                }.onFailure { it.printStackTrace() }
            }
        }
    }

    override fun hideLoadingIndicator() {
        if (loadingIndicator.isAdded) {
            try {
                this.loadingIndicator.dismissAllowingStateLoss()
            } catch (error: Throwable) {
                error.printStackTrace()
            }
        }
    }

    // OnUnauthorized
    override fun onUnauthorized() {
        sharedPreferenceCache.saveAuthToken("")
//        val intent = Intent(requireActivity() , SplashActivity::class.java)
        val intent = Intent(requireActivity() , MainActivity::class.java)
        startActivity(intent)
        (this.requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancelAll()
        this.requireActivity().finish()
    }

    fun showLocalNotification(type: LocalNotificationType, message: CharSequence) {
        this.activity?.let { it as? AppCompatActivity }?.let {
            when (type) {
                LocalNotificationType.SUCCESS -> {
                    LocalNotificationsDialog(
                        LocalNotificationsType.SUCCESS,
                        message.toString()
                    ).show(it.supportFragmentManager, "LocalNotifications")
                }
                LocalNotificationType.INFO -> {
                    LocalNotificationsDialog(
                        LocalNotificationsType.INFO,
                        message.toString()
                    ).show(it.supportFragmentManager, "LocalNotifications")
                }
                LocalNotificationType.WARNING -> {
                    LocalNotificationsDialog(
                        LocalNotificationsType.WARNING,
                        message.toString()
                    ).show(it.supportFragmentManager, "LocalNotifications")
                }
                LocalNotificationType.ERROR -> {
                    LocalNotificationsDialog(
                        LocalNotificationsType.ERROR,
                        message.toString()
                    ).show(it.supportFragmentManager, "LocalNotifications")
                }
            }
        }
    }

    override fun onStart() {

        requireActivity().registerReceiver(networkReceiver , IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        super.onStart()
    }

    override fun onStop() {

        requireActivity().unregisterReceiver(networkReceiver)
        super.onStop()
    }
}