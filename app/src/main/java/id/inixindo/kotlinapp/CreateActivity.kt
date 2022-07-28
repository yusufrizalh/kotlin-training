package id.inixindo.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    private lateinit var editCourseName: EditText
    private lateinit var editCoursePrice: EditText
    private lateinit var editCourseDuration: EditText
    private lateinit var editCourseDescription: EditText
    private lateinit var buttonCreate: Button

    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar!!.title = "Create New Course"
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        editCourseName = findViewById(R.id.edit_course_name)
        editCoursePrice = findViewById(R.id.edit_course_price)
        editCourseDuration = findViewById(R.id.edit_course_duration)
        editCourseDescription = findViewById(R.id.edit_course_description)
        buttonCreate = findViewById(R.id.btn_create)
    }

    private fun setupListeners() {
        buttonCreate.setOnClickListener {
            if (editCourseName.text.toString().isNotEmpty() &&
                editCoursePrice.text.toString().isNotEmpty() &&
                editCourseDuration.text.toString().isNotEmpty() &&
                editCourseDescription.text.toString().isNotEmpty()
            ) {
                Log.d(
                    "CreateActivity",
                    editCourseName.text.toString()
                )
                api.create(
                    editCourseName.text.toString(),
                    editCoursePrice.text.toString(),
                    editCourseDuration.text.toString(),
                    editCourseDescription.text.toString()
                ).enqueue(object : Callback<CreateModel> {
                    override fun onResponse(
                        call: Call<CreateModel>,
                        response: Response<CreateModel>
                    ) {
                        if (response.isSuccessful) {
                            val create = response.body()
                            Toast.makeText(
                                applicationContext, create!!.message,
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<CreateModel>, t: Throwable) {

                    }

                })
            } else {
                Toast.makeText(
                    applicationContext, "Fields cannot be empty!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}