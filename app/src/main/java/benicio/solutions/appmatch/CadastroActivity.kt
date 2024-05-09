package benicio.solutions.appmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import benicio.solutions.appmatch.model.UserModel
import benicio.solutions.appmatch.utils.HackUtils
import com.google.android.material.textfield.TextInputEditText

class CadastroActivity : AppCompatActivity() {

    lateinit var btnVolta: ImageView
    lateinit var btnCadastrar: Button

    var nomeStr: String = ""
    var senhaStr: String = ""
    var experienciaStr: String = ""
    var habilidadeStr: String = ""
    var areaInteresseStr: String = ""
    var formacaoAcademicaStr: String = ""
    var localizacaoStr: String = ""
    var disponibilidadeStr: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        HackUtils.configWindows(this)

        btnVolta = findViewById(R.id.voltar_para_main)
        btnCadastrar = findViewById(R.id.btn_cadastrar)

        btnVolta.setOnClickListener {
            finish()
        }

        btnCadastrar.setOnClickListener {

            nomeStr = findViewById<TextInputEditText>(R.id.nomeCad).text.toString().trim()
            senhaStr = findViewById<TextInputEditText>(R.id.senhaCad).text.toString().trim()
            experienciaStr = findViewById<TextInputEditText>(R.id.expCad).text.toString().trim()
            habilidadeStr = findViewById<TextInputEditText>(R.id.skillCad).text.toString().trim()
            areaInteresseStr = findViewById<TextInputEditText>(R.id.areaLeadCad).text.toString().trim()
            formacaoAcademicaStr = findViewById<TextInputEditText>(R.id.academicCad).text.toString().trim()
            localizacaoStr = findViewById<TextInputEditText>(R.id.localCad).text.toString().trim()
            disponibilidadeStr = findViewById<TextInputEditText>(R.id.dispCad).text.toString().trim()

            if (
                nomeStr.isNotEmpty() &&
                senhaStr.isNotEmpty() &&
                experienciaStr.isNotEmpty() &&
                habilidadeStr.isNotEmpty() &&
                areaInteresseStr.isNotEmpty() &&
                formacaoAcademicaStr.isNotEmpty() &&
                localizacaoStr.isNotEmpty() &&
                disponibilidadeStr.isNotEmpty()
            ) {
                val listaExistente = HackUtils.getList(this)

                listaExistente.add(
                    UserModel(
                        nomeStr,
                        senhaStr,
                        experienciaStr,
                        habilidadeStr,
                        areaInteresseStr,
                        formacaoAcademicaStr,
                        localizacaoStr,
                        disponibilidadeStr
                    )
                )

                HackUtils.saveList(this, listaExistente)
                HackUtils.savePersistence(this, nomeStr)
                startActivity(Intent(this, MatchUserActivity::class.java))
                Toast.makeText(this, "Usuário Criado com Sucesso!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Preencha Todas as Informações!", Toast.LENGTH_LONG).show()
            }
        }


    }
}