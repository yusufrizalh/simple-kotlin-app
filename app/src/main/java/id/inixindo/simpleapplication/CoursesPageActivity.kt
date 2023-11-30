package id.inixindo.simpleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.inixindo.simpleapplication.adapters.CourseAdapter
import id.inixindo.simpleapplication.apis.ApiRetrofit
import id.inixindo.simpleapplication.models.CourseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesPageActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().apiInterface }
    private lateinit var listCourses: RecyclerView      // nullable
    private lateinit var courseAdapter: CourseAdapter   // nullable
    private lateinit var swiperefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_page)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // mengenali seluruh komponen dalam layout
        listCourses = findViewById(R.id.listCourses)
        swiperefresh = findViewById(R.id.swiperefresh)

        // memanggil course adapter
        configCourseListAdapter()

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            getAllCourses()
        }
    }

    private fun configCourseListAdapter() {
        courseAdapter = CourseAdapter(arrayListOf(), object : CourseAdapter.OnAdapterListener {
            override fun onClickAdapterListener(course: CourseModel.Data) {
                // membuka detail course
            }
        })
        listCourses.adapter = courseAdapter
    }

    override fun onStart() {
        super.onStart()
        getAllCourses() // otomatis dipanggil ketika aplikasi sudah muncul
    }

    private fun getAllCourses() {
        api.courses().enqueue(object : Callback<CourseModel> {
            override fun onResponse(call: Call<CourseModel>, response: Response<CourseModel>) {
                if (response.isSuccessful) {
                    val listCourses = response.body()!!.courses
                    courseAdapter.setCourseData(listCourses)
                }
            }

            override fun onFailure(call: Call<CourseModel>, t: Throwable) {
                Log.e("GET COURSES ERROR! ", t.toString())
            }

        })

        if (swiperefresh.isRefreshing) {
            swiperefresh.isRefreshing = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}