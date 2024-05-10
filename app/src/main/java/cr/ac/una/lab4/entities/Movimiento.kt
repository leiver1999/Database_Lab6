package cr.ac.una.lab4.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity
data class Movimiento(
//    var _uuid: String?,
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var monto: Double,
    var tipo: String,
    var fecha: Date,
    //var fotoPath: String? = null,
    var imagen64: String? = null
) : Serializable
