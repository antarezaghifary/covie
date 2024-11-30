package com.example.coremovie.presentation.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.coremovie.R
import com.example.coremovie.databinding.FragmentSplashBinding
import com.example.coremovie.presentation.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            splashText.postDelayed({
                splashText.visibility = View.VISIBLE
                ObjectAnimator.ofFloat(splashText, "alpha", 0f, 1f).apply {
                    duration = 1000
                    start()
                }
            }, 1000)

            lifecycleScope.launch(Dispatchers.Main) {
                delay(3000)
                if (findNavController().currentDestination?.id == R.id.splashFragment)
                    findNavController().navigate(
                        R.id.action_splashFragment_to_homeFragment
                    )
            }
        }
    }

    override fun initOnClick() {

    }

    override fun collectState() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}