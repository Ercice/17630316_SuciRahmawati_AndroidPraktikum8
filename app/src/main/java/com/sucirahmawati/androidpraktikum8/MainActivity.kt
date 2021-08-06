package com.sucirahmawati.androidpraktikum8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sucirahmawati.androidpraktikum8.ui.user.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE

        viewModel.getUsers()
        viewModel.response.observe(this, {
            binding.progressBar.visibility = View.INVISIBLE
            binding.tvMainActivity.text = it.data[0].first_name
        })

    }
}
