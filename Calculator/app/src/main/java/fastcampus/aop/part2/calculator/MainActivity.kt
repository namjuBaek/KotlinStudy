package fastcampus.aop.part2.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private val expressionTextView: TextView by lazy {
        findViewById<TextView>(R.id.expressionTextView)
    }
    private val resultTextView: TextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var isOperator = false
    private var hasOperator = false

    fun buttonClicked(v: View) {
        Log.d("MainActivity", "buttonClicked")

        when(v.id) {
            R.id.zeroButton -> numberButtonClicked("0")
            R.id.oneButton -> numberButtonClicked("1")
            R.id.twoButton -> numberButtonClicked("2")
            R.id.threeButton -> numberButtonClicked("3")
            R.id.fourButton -> numberButtonClicked("4")
            R.id.fiveButton -> numberButtonClicked("5")
            R.id.sixButton -> numberButtonClicked("6")
            R.id.sevenButton -> numberButtonClicked("7")
            R.id.eightButton -> numberButtonClicked("8")
            R.id.nineButton -> numberButtonClicked("9")
            R.id.plusButton -> operatorButtonClicked("+")
            R.id.minusButton -> operatorButtonClicked("-")
            R.id.multiButton -> operatorButtonClicked("*")
            R.id.dividerButton -> operatorButtonClicked("/")
            R.id.moduloButton -> operatorButtonClicked("%")
        }
    }



    private fun numberButtonClicked(number: String) {
        if (isOperator) {
            expressionTextView.append(" ")
        }
        isOperator = false

        val expressionText = expressionTextView.text.split(" ")
        if (expressionText.isNotEmpty() && expressionText.last().length > 15) {
            Toast.makeText(this, "15자리까지만 사용할 수 있습니다.", Toast.LENGTH_SHORT)
            return
        } else if (expressionText.last().isEmpty() && number == "0") {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT)
            return
        }

        expressionTextView.append(number)

        //TODO resultTextView 실시간으로 계산 결과를 넣어야 하는 기능
    }

    private fun operatorButtonClicked(operator: String) {
        if (expressionTextView.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this, "연산자는 한 번만 사용할 수 있습니다.", Toast.LENGTH_SHORT)
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }

        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            expressionTextView.text.length - 1,
            expressionTextView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        expressionTextView.text = ssb

        isOperator = true
        hasOperator = true
    }

    fun resultButtonClicked(v: View) {
        Log.d("MainActivity", "resultButtonClicked")
    }

    fun clearButtonClicked(v: View) {
        Log.d("MainActivity", "clearButtonClicked")
    }

    fun historyButtonClicked(v: View) {
        Log.d("MainActivity", "historyButtonClicked")
    }
}