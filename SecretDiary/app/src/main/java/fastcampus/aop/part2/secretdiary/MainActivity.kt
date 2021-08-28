package fastcampus.aop.part2.secretdiary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val firstPassword: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.firstPassword)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val secondPassword: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.secondPassword)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val thirdPassword: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.thirdPassword)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstPassword
        secondPassword
        thirdPassword

        openButton.setOnClickListener {
            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${firstPassword.value}${secondPassword.value}${thirdPassword.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${firstPassword.value}${secondPassword.value}${thirdPassword.value}"

            if (changePasswordMode) {
                passwordPreference.edit(true) {
                    putString("password", passwordFromUser)
                }
                /*
                passwordPreference.edit {
                    putString("password", passwordFromUser)
                    commit()
                }
                 */

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)
                Toast.makeText(this, "패스워드 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
            } else {
                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    changePasswordButton.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    showErrorAlertDialog()
                }
            }
        }
    }

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패!!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("닫기") { _, _ -> }
            .create()
            .show()
    }
}