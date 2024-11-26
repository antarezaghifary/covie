package com.example.coremovie.presentation.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.coremovie.R
import com.example.coremovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        val splashView = LayoutInflater.from(this).inflate(R.layout.splash_screen_layout, null)
        val textView = splashView.findViewById<TextView>(R.id.splash_text)
        textView.postDelayed({
            // Fade in the text after 1 second
            textView.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f).apply {
                duration = 1000  // 1 second fade-in
                start()
            }
        }, 1000)
        setContentView(splashView)

        binding = ActivityMainBinding.inflate(layoutInflater)
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            // Create a fade-out animation for the splash view
            val fadeOut = ObjectAnimator.ofFloat(splashView, "alpha", 1f, 0f)
            fadeOut.duration = 1000
            fadeOut.start()
        }

        initUI()
    }

    private fun initUI() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottem_navigation) as NavHostFragment?
            val navController = navHostFragment?.navController
            navController?.navigate(R.id.nav_graph)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottem_navigation) as NavHostFragment).navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}