package id.inixindo.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    private lateinit var editCourseName: EditText
    private lateinit var editCoursePrice: EditText
    private lateinit var editCourseDuration: EditText
    private lateinit var editCourseDescription: EditText
    private lateinit var buttonUpdate: Button

    private val api by lazy { ApiRetrofit().endpoint }

    /*private val courseName by lazy { intent.getStringExtra("courseName") }
    private val coursePrice by lazy { intent.getStringExtra("coursePrice") }
    private val courseDuration by lazy { intent.getStringExtra("courseDuration") }
    private val courseDescription by lazy { intent.getStringExtra("courseDescription") }*/
    private val course by lazy { intent.getSerializableExtra("course") as CourseModel.Data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        supportActionBar!!.title = "Update Course"
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        editCourseName = findViewById(R.id.edit_course_name)
        editCoursePrice = findViewById(R.id.edit_course_price)
        editCourseDuration = findViewById(R.id.edit_course_duration)
        editCourseDescription = findViewById(R.id.edit_course_description)
        buttonUpdate = findViewById(R.id.btn_update)

        editCourseName.setText(course.name)
        editCoursePrice.setText(course.price)
        editCourseDuration.setText(course.duration)
        editCourseDescription.setText(course.description)
    }

    private fun setupListeners() {
        buttonUpdate.setOnClickListener {
            api.update(
                course.id!!,
                editCourseName.text.toString(),
                editCoursePrice.text.toString(),
                editCourseDuration.text.toString(),
                editCourseDescription.text.toString()
            ).enqueue(object : Callback<CreateModel> {
                override fun onResponse(call: Call<CreateModel>, response: Response<CreateModel>) {
                    if (response.isSuccessful) {
                        val update = response.body()
                        Toast.makeText(applicationContext, update!!.message, Toast.LENGTH_LONG)
                            .show()
                        finish()
                    }
                }
                override fun onFailure(call: Call<CreateModel>, t: Throwable) {}
            })
        }
    }
}