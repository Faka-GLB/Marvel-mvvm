package com.onboarding.marvel_mvvm.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.onboarding.domain.entity.MarvelData
import com.onboarding.marvel_mvvm.R
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
        when (event?.getContentIfNotHandled()?.responseType) {
            Status.SUCCESSFUL -> {
                event.peekContent().data?.total?.let { successState(it) }
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
        binding.buttonMainActivityGetInfo.visibility = View.INVISIBLE
        binding.textViewMainActivityWelcomeMessage.visibility = View.INVISIBLE
        binding.progressBarMainActivity.visibility = View.VISIBLE
    }

    private fun successState(response: Int) {
        binding.buttonMainActivityGetInfo.visibility = View.VISIBLE
        binding.textViewMainActivityWelcomeMessage.visibility = View.VISIBLE
        binding.progressBarMainActivity.visibility = View.GONE
        makeToast(message = getString(R.string.main_activity_toast_get_characters, response), Toast.LENGTH_LONG)
    }

    private fun errorState() {
        binding.buttonMainActivityGetInfo.visibility = View.VISIBLE
        binding.textViewMainActivityWelcomeMessage.visibility = View.VISIBLE
        binding.progressBarMainActivity.visibility = View.GONE
        makeToast(message = getString(R.string.main_activity_toast_get_characters_error), Toast.LENGTH_SHORT)
    }

    private fun makeToast(message: String, length: Int) {
        Toast.makeText(this, message, length).show()
    }

    private fun onGetButtonPressed() {
        viewModel.onGetButtonPressed()
    }

    private fun setListener() {
        binding.buttonMainActivityGetInfo.setOnClickListener { this.onGetButtonPressed() }
    }
}
