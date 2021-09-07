package com.onboarding.marvel_mvvm.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.marvel_mvvm.R
import com.onboarding.marvel_mvvm.adapter.ComicAdapter
import com.onboarding.marvel_mvvm.databinding.DialogFragmentCharacterInfoBinding
import com.onboarding.marvel_mvvm.utils.ConstantUtils
import com.onboarding.marvel_mvvm.utils.Event
import com.onboarding.marvel_mvvm.viewmodel.CharacterData
import com.onboarding.marvel_mvvm.viewmodel.CharacterInfoViewModel
import com.onboarding.marvel_mvvm.viewmodel.CharacterStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterInfoDialogFragment : DialogFragment() {
    private lateinit var binding: DialogFragmentCharacterInfoBinding
    private val viewModel: CharacterInfoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = DialogFragmentCharacterInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveDataCharacter().observe(this, updateUIObserver)
        viewModel.init(arguments?.getSerializable(ConstantUtils.CHARACTER_TAG) as MarvelCharacter)
        setListener()
    }

    private fun setListener() {
        binding.buttonCharacterInfoDialogFragmentReturn.setOnClickListener { this.dismiss() }
    }

    private val updateUIObserver = Observer<Event<CharacterData<MarvelCharacter>>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.responseType) {
            CharacterStatus.INIT -> {
                eventData.data?.let {
                    setUI(it)
                    binding.textViewCharacterInfoDialogFragmentDescription.text = it.description
                    binding.textViewCharacterInfoDialogFragmentDescription.movementMethod = ScrollingMovementMethod()
                }
            }
            CharacterStatus.INIT_EMPTY_DESCRIPTION -> {
                eventData.data?.let {
                    setUI(it)
                    binding.textViewCharacterInfoDialogFragmentDescription.text =
                        getString(R.string.main_activity_text_view_character_description_empty)
                }
            }
        }
    }

    private fun setUI(character: MarvelCharacter) {
        binding.textViewCharacterInfoDialogFragmentName.text = character.name
        Glide.with(this)
            .load(
                String.format(
                    CHARACTER_THUMBNAIL_URI,
                    character.thumbnail.path,
                    character.thumbnail.extension
                )
            )
            .into(
                binding.imageViewCharacterInfoDialogFragmentThumbnail
            )
        binding.recyclerViewCharacterInfoDialogFragment.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCharacterInfoDialogFragment.adapter = ComicAdapter(character.comics.items)
    }

    companion object {
        fun newInstance(marvelCharacter: MarvelCharacter): DialogFragment {
            val args = Bundle()
            args.putSerializable(ConstantUtils.CHARACTER_TAG, marvelCharacter)
            val fragment = CharacterInfoDialogFragment()
            fragment.arguments = args
            return fragment
        }

        private const val CHARACTER_THUMBNAIL_URI: String = "%s/portrait_xlarge.%s"
    }
}
