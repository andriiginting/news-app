package com.example.andriiginting.news.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.andriiginting.news.NewsApp


class ConnectivityCheck : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnected = context?.let { checkConnection(it) }
        if (connectivityCheck != null){
            isConnected?.let { connectivityCheck!!.onNetworkChanged(isConnected = it) }
        }


        isConnected?.let { connectivityCheck?.onNetworkChanged(it) }
    }


    private fun checkConnection(context: Context): Boolean{
        val conn: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conn.activeNetworkInfo

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting)

    }



    companion object {
        var connectivityCheck: ConnectivityListener? = null
    }

}