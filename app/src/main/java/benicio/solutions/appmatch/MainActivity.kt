package benicio.solutions.appmatch

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import benicio.solutions.appmatch.utils.HackUtils

class MainActivity : AppCompatActivity() {

    lateinit var btnCadastro: Button
    lateinit var btnEntrar: Button

    companion object {
        private const val PERMISSAO_NOTIFICACAO_REQUEST_CODE = 123
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HackUtils.configWindows(this)


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão não foi concedida, solicita ao usuário
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                PERMISSAO_NOTIFICACAO_REQUEST_CODE
            )
        }


        if ( HackUtils.returnPersistence(this).isNotEmpty() ){
            finish()
            startActivity(Intent(this, MatchUserActivity::class.java))
        }

        btnCadastro = findViewById(R.id.btn_cadastro)
        btnEntrar = findViewById(R.id.btn_entrar)

        btnCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        btnEntrar.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}