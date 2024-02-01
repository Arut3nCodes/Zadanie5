package com.example.zadanie5

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navHostFragment:NavHostFragment
    lateinit var navController: NavController
    lateinit var navView: NavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var bottom_navigation: BottomNavigationView
    lateinit var appBarConfig: AppBarConfiguration

//    private var myAM: ActionMode? = null

//    val myAMCallback: ActionMode.Callback = object: ActionMode.Callback {
//        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
//            getMenuInflater().inflate(R.menu.cam_view, menu)
//            return true
//        }
//
//        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
//            return when (item.itemId) {
//                R.id.red -> {
//                    findViewById<View>(R.id.textView7).setBackgroundResource(R.color.md_theme_light_primary)
//                    mode.finish()
//                    return true
//                }
//                R.id.blue -> {
//                    findViewById<View>(R.id.textView7).setBackgroundResource(R.color.md1_theme_light_primary)
//                    mode.finish()
//                    return true
//                }
//                R.id.green -> {
//                    findViewById<View>(R.id.textView7).setBackgroundResource(R.color.md2_theme_light_primary)
//                    mode.finish()
//                    return true
//                }
//                else -> true
//            }
//        }
//
//        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
//            return false
//        }
//
//        override fun onDestroyActionMode(mode: ActionMode) {
//            myAM = null
//        }
//    }

    private fun setPrefs(themeNum : Int){
        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putInt("theme_num", themeNum)
        editor.apply()
    }

//    private fun applyTheme(){
//        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//        var themeNum = data.getInt("theme_num", 0)
//        when (themeNum) {
//            1 -> setTheme(R.style.Base_Theme_Zadanie3)
//            2 -> setTheme(R.style.AppTheme)
//            3 -> setTheme(R.style.AppTheme2)
//            4 -> setTheme(R.style.AppTheme3)
//            else -> setTheme(R.style.Theme_Zadanie3)
//        }
//    }

//    @SuppressLint("RestrictedApi")
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        getMenuInflater().inflate(R.menu.menu, menu)
//        if (menu is MenuBuilder) {
//            menu.setOptionalIconsVisible(true)
//        }
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menuItem1 -> {
//                setPrefs(1)
//                recreate()
//                return true
//            }
//            R.id.menuItem2 -> {
//                setPrefs(2)
//                recreate()
//                return true
//            }
//            R.id.menuItem3 -> {
//                setPrefs(3)
//                recreate()
//                return true
//            }
//            R.id.menuItem4 -> {
//                setPrefs(4)
//                recreate()
//                return true
//            }
//
//            else -> super.onContextItemSelected(item)
//        }
//    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        //applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottom_navigation = findViewById(R.id.botNavView)
        drawerLayout= findViewById(R.id.drawer_layout)
        navView= findViewById(R.id.nav_view)

        appBarConfig =
            AppBarConfiguration(setOf(R.id.fragmentLeft,R.id.fragmentCenter,R.id.list4Fragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)
        bottom_navigation.setupWithNavController(navController)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

}