package com.example.broadcasttest

import java.lang.RuntimeException

fun max(vararg nums:Int):Int{
    var maxNum = Int.MIN_VALUE
    for (nums in nums ){
        maxNum = kotlin.math.max(maxNum,nums)
    }
    return  maxNum
}
fun <T:Comparable<T>>max (vararg  nums:T):T{
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty")
    var maxNum = nums[0]
    for (num in nums)
    {
        if (num>maxNum)
        {
            maxNum = num
        }
    }
    return maxNum
}
fun <T:Comparable<T>> min(vararg nums:T):T{
    if (nums.isEmpty()) throw RuntimeException("no Params")
    var minNUm = nums[0]
    for (num in nums)
    {
        if (num<minNUm)
            minNUm = num
    }
    return minNUm
}