package com.onboarding.marvel_mvvm.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.entity.MarvelData
import com.onboarding.marvel_mvvm.R
import com.onboarding.marvel_mvvm.adapter.CharacterAdapter
import com.onboarding.marvel_mvvm.databinding.ActivityMainBinding
import com.onboarding.marvel_mvvm.utils.Event
import com.onboarding.marvel_mvvm.viewmodel.Data
import com.onboarding.marvel_mvvm.viewmodel.MainViewModel
import com.onboarding.marvel_mvvm.viewmodel.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLiveDataCharacters().observe(this, updateUIObserver)
        setListener()
    }

    private val updateUIObserver = Observer<Event<Data<MarvelData>>> { event ->
        when (event?.peekContent()?.responseType) {
            Status.SUCCESSFUL -> {
                event.getContentIfNotHandled()?.data?.character?.let { successState(it) }
            }
            Status.ERROR -> {
                errorState()
            }
            Status.LOADING -> {
                loadingState()
            }
        }
    }

    private fun loadingState() {
        binding.buttonMainActivityGetInfo.visibility = View.GONE
        binding.textViewMainActivityWelcomeMessage.visibility = View.GONE
        binding.progressBarMainActivity.visibility = View.VISIBLE
        binding.recyclerViewMainActivity.visibility = View.INVISIBLE
    }

    private fun successState(response: List<MarvelCharacter>) {
        binding.progressBarMainActivity.visibility = View.GONE
        binding.recyclerViewMainActivity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewMainActivity.adapter = CharacterAdapter(response)
        binding.buttonMainActivityGetInfo.visibility = View.GONE
        binding.textViewMainActivityWelcomeMessage.visibility = View.GONE
        binding.recyclerViewMainActivity.visibility = View.VISIBLE
        binding.textViewMainActivityTitle.visibility = View.GONE
    }

    private fun errorState() {
        binding.buttonMainActivityGetInfo.visibility = View.VISIBLE
        binding.textViewMainActivityWelcomeMessage.visibility = View.VISIBLE
        binding.progressBarMainActivity.visibility = View.GONE
        binding.recyclerViewMainActivity.visibility = View.INVISIBLE
        Toast.makeText(this, getString(R.string.main_activity_toast_get_characters_error), Toast.LENGTH_SHORT).show()
    }

    private fun onGetButtonPressed() {
        viewModel.onGetButtonPressed()
    }

    private fun setListener() {
        binding.buttonMainActivityGetInfo.setOnClickListener { this.onGetButtonPressed() }
    }
}
