package com.example.broadcasttest

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun String.showToast(context: Context)
{
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()
}
fun Int.showToast(context: Context)
{
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()

}

