package com.example.startactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val MAIN_ACTIVITY_VALUE01_EXTRA_ID = "value01"
        val MAIN_ACTIVITY_VALUE02_EXTRA_ID = "value02"
        val MAIN_ACTIVITY_SUM_REQUEST_CODE = 1
        val MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID = "sum_result_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener {

            if (this.isValid(this.value_1.text.toString()) || this.isValid(this.value_2.text.toString())) {
                val value1 = value_1.text.toString().toInt()
                val value2 = value_2.text.toString().toInt()

                val activity2 = Intent(this, Activity2::class.java)
                activity2.putExtra(MAIN_ACTIVITY_VALUE01_EXTRA_ID, value1)
                activity2.putExtra(MAIN_ACTIVITY_VALUE02_EXTRA_ID, value2)
                startActivityForResult(activity2, MAIN_ACTIVITY_SUM_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val result = data?.getIntExtra(MAIN_ACTIVITY_SUM_RESULT_EXTRA_ID, -1)
            Toast.makeText(this, "Result: $result", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValid(name: String): Boolean = !name.isNullOrEmpty()
}