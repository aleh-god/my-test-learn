package by.godevelopment.mytestlearn.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.godevelopment.mytestlearn.R
import by.godevelopment.mytestlearn.data.RepositoryImpl
import by.godevelopment.mytestlearn.data.TestDataSource
import by.godevelopment.mytestlearn.databinding.FragmentMainBinding
import by.godevelopment.mytestlearn.domain.GetCurrentSecondsUseCase
import by.godevelopment.mytestlearn.domain.ProcessDataUseCase
import com.google.android.material.snackbar.Snackbar

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

        binding.message.text = requireContext().getText(R.string.message_hello)
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
        Snackbar.make(binding.root, message,Snackbar.LENGTH_SHORT).show()
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
