package id.inixindo.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var listCourses: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var fabCreate: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        setupCourseLists()
        setupViews()
        setupListener()
    }

    private fun setupListener() {
        fabCreate.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }

    private fun setupViews() {
        listCourses = findViewById(R.id.listCourses)
        fabCreate = findViewById(R.id.fabCreate)
    }

    private fun setupCourseLists() {
        listCourses = findViewById(R.id.listCourses)
        courseAdapter = CourseAdapter(arrayListOf(), object : CourseAdapter.OnAdapterListener {
            override fun onClick(course: CourseModel.Data) {
                startActivity(
                    Intent(this@ProductActivity, EditActivity::class.java)
                        /*.putExtra("courseName", course.name)
                        .putExtra("coursePrice", course.price)
                        .putExtra("courseDuration", course.duration)
                        .putExtra("courseDescription", course.description)*/
                        .putExtra("course", course)
                )
            }
        })
        listCourses.adapter = courseAdapter
    }

    private fun getCourses() {
        api.courses().enqueue(object : Callback<CourseModel> {
            override fun onResponse(call: Call<CourseModel>, response: Response<CourseModel>) {
                if (response.isSuccessful) {
                    val listCourses = response.body()!!.courses
                    /*listCourses.forEach {
                        Log.d("ProductActivity", "Product: ${it.name}")
                    }*/
                    courseAdapter.setData(listCourses)
                }
            }

            override fun onFailure(call: Call<CourseModel>, t: Throwable) {
                Log.e("ProductActivity", t.toString())
            }
        })
    }

    override fun onStart() {
        super.onStart()
        getCourses()
    }
}