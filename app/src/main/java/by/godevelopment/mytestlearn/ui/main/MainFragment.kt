package by.godevelopment.mytestlearn.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import by.godevelopment.mytestlearn.data.RepositoryImpl
import by.godevelopment.mytestlearn.databinding.FragmentMainBinding
import by.godevelopment.mytestlearn.domain.GetCurrentSecondsUseCase
import by.godevelopment.mytestlearn.domain.ProcessDataUseCase

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val useCase = ProcessDataUseCase(RepositoryImpl(), GetCurrentSecondsUseCase())
        viewModel = ViewModelProvider(this, MainViewModelFactory(useCase))[MainViewModel::class.java]

        setupUiWithFlow()
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            val currentMessage = binding.inputText.text.toString()
            viewModel.saveMessage(currentMessage)
            val newData = viewModel.provideMessage()
            showToast(newData)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
    }

    private fun setupUiWithFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                binding.message.text = it
            }
        }
    }
}
