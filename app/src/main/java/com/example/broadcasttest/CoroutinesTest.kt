package com.example.broadcasttest

import kotlinx.coroutines.*

fun main(){
//基本上相当于val result = async{ 5 + 5 }.await()的写法
    runBlocking {
        val result = withContext(Dispatchers.Default){
            5+3
        }
        println(result)

    }

    runBlocking { val start = System.currentTimeMillis()
    val result1 = async {
        delay(1000)
        5+5
    }.await()
        val result2 = async {
            delay(1000)
            4+4
        }.await()
        println("result is ${result1}")
        println("cost${System.currentTimeMillis() - start}")

    }
    runBlocking {
        val result = async {
            5+5
        }.await()
        println(result)

    }

    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5+5
        }
        val deferred2 = async {
            delay(1000)
            3+2
        }
        println("result is ${deferred1.await()+deferred2.await()}")

    }




//    val job = GlobalScope.launch {
//        //
//    }
//    job.cancel()
    val job = Job()
    val scope = CoroutineScope((job))
    scope.launch {
        //
    }
    job.cancel()

    runBlocking {
        coroutineScope {
            launch {
                for (i in 1..10 ){
                    print(i)
                    delay(1000)
                }
            }
            println("coroutineScope finished")
        }
        println("runBlocking finished")

    }

    suspend fun printDotted() = coroutineScope{
        launch {
            println(".")
            delay(1000)
        }
    }
    suspend fun printDot(){
        println(".")
        delay(1000)
    }

    val start = System.currentTimeMillis()
    runBlocking {
        repeat(10000){
            launch {
                println(".")
            }
        }
    }
    val end = System.currentTimeMillis()
    println(end - start)

    GlobalScope.launch {
        println("codes run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")

    }
    Thread.sleep(100000)
    runBlocking {
        launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }
    }
}