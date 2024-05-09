package benicio.solutions.appmatch.adapter

import android.app.Activity
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import benicio.solutions.appmatch.R
import benicio.solutions.appmatch.model.UserModel

class UserAdapter(
    private val lista: MutableList<UserModel>,
    private val c: Activity
) : Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val itemView: View) : ViewHolder(itemView) {
        val nomeUerText: TextView = itemView.findViewById(R.id.nome_user_text)
        val conteudoUerText: TextView = itemView.findViewById(R.id.conteudo_user_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_user, parent, false)
        )
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: UserModel = lista.get(position)

        holder.nomeUerText.text = user.nome

        var infoStr = StringBuilder()

        infoStr.append("<i>Informações do Usuário</i>").append("<br>")
        infoStr.append("<b>Experiência:</b> ${user.experiencia}").append("<br>")
        infoStr.append("<b>Habilidades:</b> ${user.habilidades}").append("<br>")
        infoStr.append("<b>Áreas de Interesse:</b> ${user.areasInteresse}").append("<br>")
        infoStr.append("<b>Formação Acadêmica:</b> ${user.formacaoAcademica}").append("<br>")
        infoStr.append("<b>Localização:</b> ${user.localizacao}").append("<br>")
        infoStr.append("<b>Disponibilidade:</b> ${user.disponibilidade}").append("<br>")

        holder.conteudoUerText.text = Html.fromHtml(infoStr.toString())
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}