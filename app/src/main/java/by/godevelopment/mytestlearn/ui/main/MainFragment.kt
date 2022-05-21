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
import by.godevelopment.mytestlearn.data.TestDataSource
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
        val useCase = ProcessDataUseCase(RepositoryImpl(TestDataSource), GetCurrentSecondsUseCase())
        viewModel = ViewModelProvider(this, MainViewModelFactory(useCase))[MainViewModel::class.java]

        setupUiWithFlow()
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            updateMessage()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
    }

    private fun updateMessage() {
        viewModel.saveMessage(binding.inputText.text.toString())
        viewModel.provideMessage().also {
            binding.message.text = it
            showToast(it)
        }
    }

    private fun setupUiWithFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                binding.counter.text = it
            }
        }
    }
}
