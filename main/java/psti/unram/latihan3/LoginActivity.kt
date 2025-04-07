package psti.unram.latihan3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.editTextEmail)
        val passwordInput = findViewById<EditText>(R.id.editTextPassword)
        val loginBtn = findViewById<Button>(R.id.buttonLogin)
        val registerBtn = findViewById<Button>(R.id.buttonGoToRegister)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            ) {
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val storedEmail = sharedPref.getString("email", null)
                val storedPassword = sharedPref.getString("password", null)
                val storedName = sharedPref.getString("name", "User")

                if (email == storedEmail && password == storedPassword) {
                    val user = User(storedName ?: "User", email)
                    Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LandingActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }


        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}