package com.example.tugasweektiga
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etNim: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnMasuk: Button
    private lateinit var ivTogglePassword: ImageView
    private var isPasswordVisible = false

    private var receivedUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        etNim = findViewById(R.id.inputNIM2)
        etPassword = findViewById(R.id.inputPassword2)
        btnMasuk = findViewById(R.id.btnLogin)
        ivTogglePassword = findViewById(R.id.TogglePassword2)

        @Suppress("DEPRECATION")
        receivedUser = intent.getParcelableExtra("user")

        btnMasuk.setOnClickListener {
            val nim = etNim.text.toString()
            val password = etPassword.text.toString()

            if (nim.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "NIM dan Password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (receivedUser == null) {
                Toast.makeText(this, "User belum terdaftar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nim == receivedUser?.nim && password == receivedUser?.password) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("user", receivedUser)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "NIM atau Password salah", Toast.LENGTH_SHORT).show()
            }
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