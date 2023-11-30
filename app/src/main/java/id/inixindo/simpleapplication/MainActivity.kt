package id.inixindo.simpleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setiap komponen dalam layout harus dikenali
        val txtValue = findViewById<TextView>(R.id.txtValue)
        val btnIncrease = findViewById<Button>(R.id.btnIncrease)
        val btnDecrease = findViewById<Button>(R.id.btnDecrease)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // nilai awal dari txtValue adalah 0
        var nilaiAwal: Int = 0

        // membuat event handling untuk btnIncrease
        btnIncrease.setOnClickListener(View.OnClickListener {
            nilaiAwal++     // +1
            txtValue.setText(nilaiAwal.toString())  // dikonversi menjadi string
        })

        // membuat event handling untuk btnDecrease
        btnDecrease.setOnClickListener(View.OnClickListener {
            nilaiAwal--     // -1
            txtValue.setText(nilaiAwal.toString())  // dikonversi menjadi string
        })

        btnReset.setOnClickListener(View.OnClickListener {
            nilaiAwal = 0
            txtValue.setText(nilaiAwal.toString())  // dikonversi menjadi string
        })

        // membuat event handling untuk btnSend
        btnSend.setOnClickListener(View.OnClickListener {
            // perintah untuk berpindah activity sambil membawa data
            // bila datanya lebih dari satu dapat menggunakan Bundle
            val bundle = Bundle()
            bundle.putString("value", txtValue.text.toString()) // membungkus isi dari txtValue

            val myIntent = Intent(this@MainActivity, DisplayPageActivity::class.java)
            myIntent.putExtras(bundle)
            startActivity(myIntent)
        })

        // khusus custom toolbar
        setSupportActionBar(myToolbar)
        // toggle adalah icon burger
        var toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            myToolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled
        toggle.syncState()

        // bila menu pada drawer dipilih
        navigationView.setNavigationItemSelectedListener(this)
    }

    // menu sebelah kanan atas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.simple_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_new -> {
                // berpindah activity ke halaman New Page
                val myIntent = Intent(this@MainActivity, NewPageActivity::class.java)
                startActivity(myIntent)
                Toast.makeText(applicationContext, "Menu New", Toast.LENGTH_LONG).show(); true
            }

            R.id.menu_help -> {
                Toast.makeText(applicationContext, "Menu Help", Toast.LENGTH_LONG).show(); true
            }

            R.id.menu_search -> {
                Toast.makeText(applicationContext, "Menu Search", Toast.LENGTH_LONG).show(); true
            }

            R.id.menu_settings -> {
                Toast.makeText(applicationContext, "Menu Settings", Toast.LENGTH_LONG).show(); true
            }

            R.id.submenu_profile -> {
                Toast.makeText(applicationContext, "Submenu Profile", Toast.LENGTH_LONG)
                    .show(); true
            }

            R.id.submenu_gallery -> {
                // startActivity(Intent(this@MainActivity, AccessCameraActivity::class.java))
                Toast.makeText(applicationContext, "Submenu Gallery", Toast.LENGTH_LONG)
                    .show(); true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawer_courses -> {
                startActivity(Intent(this@MainActivity, CoursesPageActivity::class.java))
            }
        }
        return true
    }
}