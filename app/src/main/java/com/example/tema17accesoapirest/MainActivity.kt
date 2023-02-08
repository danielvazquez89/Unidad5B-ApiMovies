package com.example.tema17accesoapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavigationController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp (): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController .navigateUp() || super.onSupportNavigateUp()
    }
}

fun FragmentActivity.findNavigationController (@IdRes host: Int): NavController {
    try {
        val navHostFragment = supportFragmentManager.findFragmentById(host) as NavHostFragment
        return navHostFragment .findNavController()
    } catch (e: Exception) {
        throw IllegalStateException( "Activity $this does not have a NavController set on $host")
    }
}