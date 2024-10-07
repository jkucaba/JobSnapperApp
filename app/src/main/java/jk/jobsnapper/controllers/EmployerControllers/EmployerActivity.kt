package jk.jobsnapper.controllers.EmployerControllers

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import jk.jobsnapper.R
import jk.jobsnapper.databinding.ActivityEmployerBinding

class EmployerActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private  lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityEmployerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        fragmentManager = supportFragmentManager
        //openFragment(UserListFragment())
        binding.navigationDrawer.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_set->{}
            R.id.job_offers->{}
            R.id.your_offers->{}
            R.id.profileSettings->{}
            R.id.report->{}
            R.id.logout->finish()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}