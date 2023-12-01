package id.inixindo.simpleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import id.inixindo.simpleapplication.apis.ApiRetrofit
import id.inixindo.simpleapplication.models.MessageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateCourseActivity : AppCompatActivity() {
    private lateinit var editTxtCourseName: EditText
    private lateinit var editTxtCoursePrice: EditText
    private lateinit var editTxtCourseDuration: EditText
    private lateinit var editTxtCourseDescription: EditText
    private lateinit var btnCreateCourse: Button

    private val api by lazy { ApiRetrofit().apiInterface }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        editTxtCourseName = findViewById(R.id.editTxtCourseName)
        editTxtCoursePrice = findViewById(R.id.editTxtCoursePrice)
        editTxtCourseDuration = findViewById(R.id.editTxtCourseDuration)
        editTxtCourseDescription = findViewById(R.id.editTxtCourseDescription)
        btnCreateCourse = findViewById(R.id.btnCreateCourse)

        btnCreateCourse.setOnClickListener(View.OnClickListener {
            if (editTxtCourseName.text.toString().isNotEmpty() &&
                editTxtCoursePrice.text.toString().isNotEmpty() &&
                editTxtCourseDuration.text.toString().isNotEmpty() &&
                editTxtCourseDescription.text.toString().isNotEmpty()
            ) {
                api.create(
                    editTxtCourseName.text.toString(),
                    editTxtCoursePrice.text.toString(),
                    editTxtCourseDuration.text.toString(),
                    editTxtCourseDescription.text.toString()
                ).enqueue(object : Callback<MessageModel> {
                    override fun onResponse(
                        call: Call<MessageModel>,
                        response: Response<MessageModel>
                    ) {
                        if (response.isSuccessful) {
                            val createCourse = response.body()
                            Toast.makeText(
                                applicationContext,
                                createCourse!!.message,
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                        Log.e("POST COURSE ERROR! ", t.toString())
                    }

                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "All fields must be not empty!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}