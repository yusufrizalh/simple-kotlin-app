package id.inixindo.simpleapplication.apis

import id.inixindo.simpleapplication.models.CourseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("courses.php")
    fun courses(): Call<CourseModel>
}