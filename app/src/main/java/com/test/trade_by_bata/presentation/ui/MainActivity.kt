package com.test.trade_by_bata.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.test.domain.entities.Account
import com.test.trade_by_bata.R
import com.test.trade_by_bata.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationMenu.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.signInFragment -> hideNavigationMenu(binding)
                else -> showNavigationMenu(binding)
            }
        }
    }

    //    скрыть меню навигации и тулбар для фрагментов Login и SignIn
    private fun hideNavigationMenu(binding: ActivityMainBinding) {
        binding.bottomNavigationMenu.visibility = View.GONE
    }

    //    показать меню навигации и тулбар для всех фрагментов, кроме Login и SignIn
    private fun showNavigationMenu(binding: ActivityMainBinding) {
        binding.bottomNavigationMenu.visibility = View.VISIBLE
    }
}