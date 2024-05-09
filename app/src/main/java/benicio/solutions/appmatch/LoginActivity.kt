package benicio.solutions.appmatch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import benicio.solutions.appmatch.utils.HackUtils
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {


    var nomeStr: String = ""
    var senhaStr: String = ""

    lateinit var btnLogin: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        HackUtils.configWindows(this)
        btnLogin = findViewById(R.id.btn_fazer_login)


        findViewById<ImageView>(R.id.voltar_para_main).setOnClickListener { finish() }
        findViewById<TextView>(R.id.redict2cad).setOnClickListener {
            finish()
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        btnLogin.setOnClickListener {

            nomeStr = findViewById<TextInputEditText>(R.id.nomeLogin).text.toString().trim()
            senhaStr = findViewById<TextInputEditText>(R.id.senhaLogin).text.toString().trim()

            if (nomeStr.isNotEmpty() && senhaStr.isNotEmpty()) {
                var errado: Boolean = true

                HackUtils.getList(this).forEach { user ->
                    if (user.nome.equals(nomeStr) && user.senha.equals(senhaStr)) {
                        Toast.makeText(this, "Bem vindo de volta!", Toast.LENGTH_LONG).show()
                        finish()
                        startActivity(Intent(this, MatchUserActivity::class.java))
                        HackUtils.savePersistence(this, nomeStr)
                        errado = false
                    }
                }

                if (errado) {
                    Toast.makeText(this, "Senha ou Nome Incorreto!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha Todas Informações!", Toast.LENGTH_LONG).show()
            }


        }

    }
}