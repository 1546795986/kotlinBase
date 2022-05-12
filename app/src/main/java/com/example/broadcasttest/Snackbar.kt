package com.example.broadcasttest

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.time.Duration


fun View.showSnackbar(text:String,duration: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this,text,duration).show()
}
fun View.showSnackbar(resId:Int,duration:Int = Snackbar.LENGTH_LONG)
{
    Snackbar.make(this,resId,duration).show()
}
fun View.showSnackbar(text:String,actionText:String? = null,
        duration: Int = Snackbar.LENGTH_SHORT,block:(()->Unit)?=null){
    val snackbar = Snackbar.make(this,text,duration)
    if (actionText!=null && block!=null     )
    {
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}
fun View.showSnackbar(resId:Int,actionResId:Int?=null,
        duration: Int = Snackbar.LENGTH_LONG,block:(()->Unit)? = null){
    val snackbar = Snackbar.make(this,resId,duration)
    if (actionResId != null && block != null){
        snackbar.setAction(actionResId){
            block()
        }
    }
    snackbar.show()
}


