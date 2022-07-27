package id.inixindo.kotlinapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("courses.php")
    fun courses(): Call<CourseModel>
}