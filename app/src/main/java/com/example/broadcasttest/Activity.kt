package com.example.broadcasttest

import android.app.Activity

object ActivityCollector {

    inline fun num1AndNum2(num1:Int,num2:Int,operation:(Int,Int) ->Int):Int{
        val result = operation(num1,num2)
        return result
    }
    inline fun runRunnable(crossinline block:() -> Unit){
        val runnable = Runnable {
            block()
        }
        runnable.run()
    }
    private val activities = ArrayList<Activity>()
    fun addActivity(activity: Activity){
        activities.add(activity)
    }
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }
    fun finish(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }


}