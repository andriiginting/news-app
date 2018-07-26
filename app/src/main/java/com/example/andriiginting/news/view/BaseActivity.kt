package com.example.andriiginting.news.view

import android.annotation.SuppressLint
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.andriiginting.news.R
import com.example.andriiginting.news.utils.ConnectivityCheck.Companion.connectivityCheck
import com.example.andriiginting.news.utils.ConnectivityListener

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(),
        ConnectivityListener,
        BaseView {

    override fun onNetworkChanged(isConnected: Boolean) {
        if (isConnected) {
            Log.d("koneksi", isConnected.toString())
            showToast(resources.getString(R.string.check_your_internet_connection))
        } else {
            hideToast()
        }
    }

    @SuppressLint("WrongConstant")
    override fun showToast(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).duration = -2
    }

    override fun hideToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        connectivityCheck = this
    }

}