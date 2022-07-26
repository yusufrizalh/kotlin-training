package id.inixindo.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
    }

    override fun onClick(button: View?) {
        val number1 = editNumber1.text
        val number2 = editNumber2.text

        when (button?.id) {
            R.id.btnPlus -> {
                txtResult.text =
                    ((number1.toString().toDouble() + number2.toString().toDouble()).toString())
            }
            R.id.btnMinus -> {
                txtResult.text =
                    ((number1.toString().toDouble() - number2.toString().toDouble()).toString())
            }
            R.id.btnMultiply -> {
                txtResult.text =
                    ((number1.toString().toDouble() * number2.toString().toDouble()).toString())
            }
            R.id.btnDivide -> {
                txtResult.text =
                    ((number1.toString().toDouble() / number2.toString().toDouble()).toString())
            }
        }
    }

    // ketika radiobutton atau checkbox dipilih maka otomatis mengakumulasikan nilai
    fun akumulasiNilai(view: View) {
        var genreFilm = 0.0
        var releaseFilm = 0.0

        when {
            radioAction.isChecked -> genreFilm = 12.0
            radioRomantic.isChecked -> genreFilm = 10.0
            radioDrama.isChecked -> genreFilm = 7.0
        }

        if (check1.isChecked) {
            releaseFilm += 10.0
        }
        if (check2.isChecked) {
            releaseFilm += 20.0
        }
        if (check3.isChecked) {
            releaseFilm += 30.0
        }

        txtTotalNilai.text = "Total: " + (genreFilm + releaseFilm)
    }

}