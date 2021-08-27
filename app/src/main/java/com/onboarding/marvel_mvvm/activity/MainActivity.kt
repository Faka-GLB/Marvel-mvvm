package com.onboarding.marvel_mvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onboarding.marvel_mvvm.databinding.ActivityMainBinding
import com.onboarding.marvel_mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
