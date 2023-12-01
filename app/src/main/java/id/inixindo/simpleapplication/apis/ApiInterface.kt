package id.inixindo.simpleapplication.apis

import id.inixindo.simpleapplication.models.CourseModel
import id.inixindo.simpleapplication.models.MessageModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("courses.php")
    fun courses(): Call<CourseModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("duration") duration: String,
        @Field("description") description: String,
    ): Call<MessageModel>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("duration") duration: String,
        @Field("description") description: String,
    ): Call<MessageModel>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
        @Field("id") id: String,
    ): Call<MessageModel>
}