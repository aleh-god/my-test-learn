package by.godevelopment.mytestlearn.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.godevelopment.mytestlearn.data.RepositoryImpl
import by.godevelopment.mytestlearn.databinding.FragmentMainBinding
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
        val useCase = ProcessDataUseCase(RepositoryImpl())
        viewModel = ViewModelProvider(this, MainViewModelFactory(useCase))[MainViewModel::class.java]

        setupUi()
        return binding.root
    }

    private fun setupUi() {
        binding.message.text = viewModel.provideMessage()
    }

}