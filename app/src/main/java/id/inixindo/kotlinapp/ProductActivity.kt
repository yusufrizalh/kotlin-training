package id.inixindo.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        api.courses().enqueue(object : Callback<CourseModel> {
            override fun onResponse(call: Call<CourseModel>, response: Response<CourseModel>) {
                if (response.isSuccessful) {
                    val listCourses = response.body()!!.courses
                    listCourses.forEach {
                        Log.d("ProductActivity", "Product: ${it.name}")
                    }
                }
            }

            override fun onFailure(call: Call<CourseModel>, t: Throwable) {
                Log.e("ProductActivity", t.toString())
            }

        })
    }
}