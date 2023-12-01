package id.inixindo.simpleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.inixindo.simpleapplication.apis.ApiRetrofit
import id.inixindo.simpleapplication.models.CourseModel
import id.inixindo.simpleapplication.models.MessageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditCourseActivity : AppCompatActivity() {
    private lateinit var editTxtCourseName: EditText
    private lateinit var editTxtCoursePrice: EditText
    private lateinit var editTxtCourseDuration: EditText
    private lateinit var editTxtCourseDescription: EditText
    private lateinit var btnEditCourse: Button

    private val api by lazy { ApiRetrofit().apiInterface }
    private val selectedCourse by lazy { intent.getSerializableExtra("selectedCourse") as CourseModel.Data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_course)

        editTxtCourseName = findViewById(R.id.editTxtCourseName)
        editTxtCoursePrice = findViewById(R.id.editTxtCoursePrice)
        editTxtCourseDuration = findViewById(R.id.editTxtCourseDuration)
        editTxtCourseDescription = findViewById(R.id.editTxtCourseDescription)
        btnEditCourse = findViewById(R.id.btnEditCourse)

        // mengisi form untuk edit corse
        editTxtCourseName.setText(selectedCourse.name)
        editTxtCoursePrice.setText(selectedCourse.price)
        editTxtCourseDuration.setText(selectedCourse.duration)
        editTxtCourseDescription.setText(selectedCourse.description)

        btnEditCourse.setOnClickListener(View.OnClickListener {
            api.update(
                selectedCourse.id!!, editTxtCourseName.text.toString(),
                editTxtCoursePrice.text.toString(), editTxtCourseDuration.text.toString(),
                editTxtCourseDescription.text.toString()
            ).enqueue(object : Callback<MessageModel> {
                override fun onResponse(
                    call: Call<MessageModel>,
                    response: Response<MessageModel>
                ) {
                    if (response.isSuccessful) {
                        val updateCourse = response.body()
                        Toast.makeText(
                            applicationContext,
                            updateCourse!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                    Log.e("UPDATE COURSE ERROR! ", t.toString())
                }

            })
        })
    }
}