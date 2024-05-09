package benicio.solutions.appmatch

import MyFirebaseMessagingService
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import benicio.solutions.appmatch.adapter.UserAdapter
import benicio.solutions.appmatch.model.UserModel
import benicio.solutions.appmatch.utils.HackUtils
import com.google.android.material.textfield.TextInputEditText

class MatchUserActivity : AppCompatActivity() {

    private lateinit var btnSair: Button
    private lateinit var saudacaoText: TextView
    private lateinit var rv_users: RecyclerView
    private lateinit var adapterUser: UserAdapter
    private val listaUsuarios: MutableList<UserModel> = mutableListOf()


    private lateinit var btnSearch: ImageButton
    private lateinit var edtSearch: TextInputEditText

    @SuppressLint("MissingInflatedId", "SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_user)

        HackUtils.configWindows(this, false)

        var listCompareMatch: MutableList<UserModel> = mutableListOf()
        var nomeUserAtual: String = HackUtils.returnPersistence(this)
        lateinit var objetoUserAtual: UserModel

        HackUtils.getList(this).forEach { userModel: UserModel ->
            if (!userModel.nome.equals(nomeUserAtual)) {
                listCompareMatch.add(userModel)
            } else {
                objetoUserAtual = userModel
            }
        }

        calcularPorcentagemDeMatching(objetoUserAtual, listCompareMatch)






        btnSair = findViewById(R.id.btn_sair_app)
        saudacaoText = findViewById(R.id.saudacao_text)
        btnSearch = findViewById(R.id.btn_search)
        edtSearch = findViewById(R.id.edit_search)

        saudacaoText.text = "Olá, ${HackUtils.returnPersistence(this)}"

        btnSair.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
            HackUtils.savePersistence(this, "")
        }

        btnSearch.setOnClickListener {
            val query: String = edtSearch.text.toString().trim().lowercase()
            listaUsuarios.clear()

            if (query.isNotEmpty() && query.isNotBlank()) {


                HackUtils.getList(this).forEach { item: UserModel ->

                    if (
                        item.nome?.lowercase()?.contains(query) == true ||
                        item.experiencia?.lowercase()?.contains(query) == true ||
                        item.habilidades?.lowercase()?.contains(query) == true ||
                        item.areasInteresse?.lowercase()?.contains(query) == true ||
                        item.formacaoAcademica?.lowercase()?.contains(query) == true ||
                        item.localizacao?.lowercase()?.contains(query) == true ||
                        item.disponibilidade?.lowercase()?.contains(query) == true
                    ) {
                        listaUsuarios.add(item)
                    }

                }

                adapterUser.notifyDataSetChanged()
            } else {
                listaUsuarios.addAll(HackUtils.getList(this))
                adapterUser.notifyDataSetChanged()
            }

        }

        configurarRecycler()
    }

    fun calcularPorcentagemDeMatching(objetoComparar: UserModel, listaObjetos: List<UserModel>) {
        val experienciaComparar = objetoComparar.experiencia?.split(", ")?.toSet() ?: emptySet()
        val habilidadesComparar = objetoComparar.habilidades?.split(", ")?.toSet() ?: emptySet()
        val areasInteresseComparar =
            objetoComparar.areasInteresse?.split(", ")?.toSet() ?: emptySet()
        val formacaoAcademicaComparar =
            objetoComparar.formacaoAcademica?.split(", ")?.toSet() ?: emptySet()
        val localizacaoComparar = objetoComparar.localizacao?.split(", ")?.toSet() ?: emptySet()
        val disponibilidadeComparar =
            objetoComparar.disponibilidade?.split(", ")?.toSet() ?: emptySet()

        for ((index, objeto) in listaObjetos.withIndex()) {
            val experienciaObjeto = objeto.experiencia?.split(", ")?.toSet() ?: emptySet()
            val habilidadesObjeto = objeto.habilidades?.split(", ")?.toSet() ?: emptySet()
            val areasInteresseObjeto = objeto.areasInteresse?.split(", ")?.toSet() ?: emptySet()
            val formacaoAcademicaObjeto =
                objeto.formacaoAcademica?.split(", ")?.toSet() ?: emptySet()
            val localizacaoObjeto = objeto.localizacao?.split(", ")?.toSet() ?: emptySet()
            val disponibilidadeObjeto = objeto.disponibilidade?.split(", ")?.toSet() ?: emptySet()

            val experienciaMatching =
                experienciaObjeto.intersect(experienciaComparar).size.toDouble()
            val porcentagemExperiencia = (experienciaMatching / experienciaComparar.size) * 100

            val habilidadesMatching =
                habilidadesObjeto.intersect(habilidadesComparar).size.toDouble()
            val porcentagemHabilidades = (habilidadesMatching / habilidadesComparar.size) * 100

            val areasInteresseMatching =
                areasInteresseObjeto.intersect(areasInteresseComparar).size.toDouble()
            val porcentagemAreasInteresse =
                (areasInteresseMatching / areasInteresseComparar.size) * 100

            val formacaoAcademicaMatching =
                formacaoAcademicaObjeto.intersect(formacaoAcademicaComparar).size.toDouble()
            val porcentagemFormacaoAcademica =
                (formacaoAcademicaMatching / formacaoAcademicaComparar.size) * 100

            val localizacaoMatching =
                localizacaoObjeto.intersect(localizacaoComparar).size.toDouble()
            val porcentagemLocalizacao = (localizacaoMatching / localizacaoComparar.size) * 100

            val disponibilidadeMatching =
                disponibilidadeObjeto.intersect(disponibilidadeComparar).size.toDouble()
            val porcentagemDisponibilidade =
                (disponibilidadeMatching / disponibilidadeComparar.size) * 100

            val porcentagemMatching =
                (porcentagemExperiencia + porcentagemHabilidades + porcentagemAreasInteresse +
                        porcentagemFormacaoAcademica + porcentagemLocalizacao + porcentagemDisponibilidade) / 6


            val porcentagemFormatada:String = "%.2f".format(porcentagemMatching)
            val title = "$porcentagemFormatada% de Matchmaking com ${objeto.nome}"
            val body =
                "Você tem $porcentagemFormatada% de Matchmaking com  o instrutor ${objeto.nome}"
            MyFirebaseMessagingService.showNotification(title, body, this)
        }
    }


    private fun configurarRecycler() {
        rv_users = findViewById(R.id.rv_users)
        rv_users.setHasFixedSize(true)
        rv_users.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_users.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaUsuarios.addAll(HackUtils.getList(this))
        adapterUser = UserAdapter(listaUsuarios, this)
        rv_users.adapter = adapterUser

    }
}