package com.animehub.otakuvortex.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OtakuVortex)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        Handler().postDelayed({
            binding.initialActivity.foreground = null
        }, 4000)

        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragmentContainerView))
    }
}