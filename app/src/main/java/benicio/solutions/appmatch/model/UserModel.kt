package benicio.solutions.appmatch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val nome: String?,
    val senha: String?,
    val experiencia: String?,
    val habilidades: String?,
    val areasInteresse: String?,
    val formacaoAcademica: String?,
    val localizacao: String?,
    val disponibilidade: String?,
) : Parcelable
