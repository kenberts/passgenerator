import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.security.SecureRandom

class MainActivity : AppCompatActivity() {

    private val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+"
    private val random = SecureRandom()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val passwordTextView: TextView = findViewById(R.id.passwordTextView)
        val generateButton: Button = findViewById(R.id.generateButton)
        val copyButton: Button = findViewById(R.id.copyButton)

        generateButton.setOnClickListener {
            val password = generatePassword(12) // Cambia el número para la longitud deseada
            passwordTextView.text = password
        }

        copyButton.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Password", passwordTextView.text)
            clipboard.setPrimaryClip(clip)
        }
    }

    private fun generatePassword(length: Int): String {
        val password = StringBuilder(length)
        for (i in 0 until length) {
            password.append(chars[random.nextInt(chars.length)])
        }
        return password.toString()
    }
}
