package com.example.tugasweektiga
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnDaftar: Button
    private lateinit var ivTogglePassword: ImageView
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        etNama = findViewById(R.id.inputNama1)
        etNim = findViewById(R.id.inputNIM1)
        etEmail = findViewById(R.id.inputEmail1)
        etPassword = findViewById(R.id.inputPassword1)
        btnDaftar = findViewById(R.id.btnSignUp)
        ivTogglePassword = findViewById(R.id.TogglePassword1)

        btnDaftar.setOnClickListener {
            val nama = etNama.text.toString()
            val nim = etNim.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (nama.isEmpty() || nim.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(nama, nim, email, password)

            Toast.makeText(this, "Akun berhasil dibuat, silakan login", Toast.LENGTH_SHORT).show()

            etNama.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
                finish()
            }, 7000)
        }

        ivTogglePassword.setOnClickListener {
            if (isPasswordVisible) {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivTogglePassword.setImageResource(R.drawable.ic_visibility_of)
            } else {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT
                ivTogglePassword.setImageResource(R.drawable.ic_visibility)
            }
            etPassword.setSelection(etPassword.text.length)
            isPasswordVisible = !isPasswordVisible
        }
    }
}
