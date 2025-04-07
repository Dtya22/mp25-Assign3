package psti.unram.latihan3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val emailInput = findViewById<EditText>(R.id.editTextEmail)
        val passwordInput = findViewById<EditText>(R.id.editTextPassword)
        val registerBtn = findViewById<Button>(R.id.buttonRegister)

        registerBtn.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            ) {
                // Simpan data ke SharedPreferences
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("name", name)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                val user = User(name, email)
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }

    }
}