package com.example.practico3gm190689

import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.practico3gm190689.db.HelperDB
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    private var MarcaFragment: MarcaFragment = MarcaFragment()
    private var ColoresFragment: ColoresFragment = ColoresFragment()
    private var TipoAutoFragment: TipoAutoFragment = TipoAutoFragment()
    private var AutosFragment: AutosFragment = AutosFragment()
    private var UsuariosFragment: UsuariosFragment = UsuariosFragment()

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase

        toolbar = findViewById(R.id.toolbar_main)
        this.setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawerContainer)

        toggle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        loadFragment(MarcaFragment)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.marcas -> {
                loadFragment(MarcaFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.colores -> {
                loadFragment(ColoresFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.tipoauto -> {
                loadFragment(TipoAutoFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.autos -> {
                loadFragment(AutosFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.usuarios -> {
                loadFragment(UsuariosFragment)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
        }

        return false
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment)
    {
        var newFragment: FragmentTransaction = supportFragmentManager.beginTransaction()
        newFragment.replace(R.id.frameContainer, fragment)
        newFragment.commit()
    }
}