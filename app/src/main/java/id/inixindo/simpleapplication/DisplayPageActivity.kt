package id.inixindo.simpleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class DisplayPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_page)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // mengenali seluruh komponen yang terdapat di layout
        var txtDisplay = findViewById<TextView>(R.id.txtDisplay)

        // menerima data
        val bundle = intent.extras  // data telah diterima
        txtDisplay.setText(bundle?.getString("value"))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}