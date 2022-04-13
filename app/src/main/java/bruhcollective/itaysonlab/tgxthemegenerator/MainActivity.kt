package bruhcollective.itaysonlab.tgxthemegenerator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    ViewCompat.setOnApplyWindowInsetsListener(requireViewById(R.id.rootView)) { v, insets ->
      requireViewById<View>(R.id.appbar).updatePadding(top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top)
      insets
    }

    WindowCompat.setDecorFitsSystemWindows(window, false)

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    val appBarConfiguration = AppBarConfiguration(setOf(R.id.generatorFragment, R.id.aboutFragment))
    findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
    findViewById<BottomNavigationView>(R.id.bottomnav).setupWithNavController(navController)
  }
}