package com.example.broadcasttest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.broadcasttest.databinding.ActivityMainBinding

class LoginActivity:BaseActivitu() {
    private lateinit var binding : ActivityShareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding!!.getRoot());
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}