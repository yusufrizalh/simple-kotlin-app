package id.inixindo.simpleapplication.apis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {
    val apiInterface: ApiInterface
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val clientBuilder = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://inixkotlin.000webhostapp.com/courses/")
                .client(clientBuilder).addConverterFactory(GsonConverterFactory.create()).build()

            return retrofitBuilder.create(ApiInterface::class.java)
        }
}