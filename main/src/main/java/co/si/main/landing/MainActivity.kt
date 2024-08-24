package co.si.main.landing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import corp.hell.kernel.utils.genDeviceToken
import co.si.main.R
import co.si.main.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import corp.hell.kernel.parent.BaseActivity
import corp.hell.kernel.parent.imp.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var navView: BottomNavigationView
    private val mainViewModel by viewModels<MainViewModel>()

    /**
     * Lifecycle Method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCrashPopUpListener()
        setClickListener()
        restOfCoding()
    }

    /**
     * Put all click listener
     */
    private fun setClickListener() {}

    /**
     * Rest of Coding
     */
    private fun restOfCoding() {
        binding.viewModelMain = mainViewModel
        genDeviceToken()
        commonSetUp()
        navView = binding.navView
        val navController = findNavController(R.id.navHostFragmentActivityLanding)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            destinationChanger(destination)
        }
    }

    private fun commonSetUp() {
        loaderSetUp()
        snackBarSetUp()
    }

    private fun destinationChanger(destination: NavDestination) {
        when (destination.id) {
            R.id.loginFragment -> navView.visibility =
                View.VISIBLE

            else -> navView.visibility = View.GONE
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Basic set up
    ///////////////////////////////////////////////////////////////////////////
    private fun snackBarSetUp() {
        mainViewModel.showSnackbar.observe(this) { dto ->
            showCustomSnackBar(dto.state, dto.message)
        }
    }

    private fun loaderSetUp() {
        mainViewModel.showProgress.observe(this) { showLoader ->
            binding.loaderLayout.visibility = if (showLoader) View.VISIBLE else View.GONE
        }
    }
}