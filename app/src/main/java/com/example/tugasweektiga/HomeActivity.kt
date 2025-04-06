package com.example.tugasweektiga
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvNim: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPassword: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        tvNama = findViewById(R.id.tampilNama1)
        tvNim = findViewById(R.id.tampilNIM1)
        tvEmail = findViewById(R.id.tampilEmail1)
        tvPassword = findViewById(R.id.tampilPassword1)
        btnLogout = findViewById(R.id.btnLogout)

        @Suppress("DEPRECATION")
        val user = intent.getParcelableExtra<User>("user")

        if (user != null) {
            tvNama.text = getString(R.string.nama_format, user.nama)
            tvNim.text = getString(R.string.nim_format, user.nim)
            tvEmail.text = getString(R.string.email_format, user.email)
            tvPassword.text = getString(R.string.password_format, user.password)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
