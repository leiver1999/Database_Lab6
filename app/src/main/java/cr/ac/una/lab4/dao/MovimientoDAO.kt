package cr.ac.una.lab4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cr.ac.una.lab4.entities.Movimiento
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

@Dao
interface MovimientoDAO {

    //endpoints
//    @GET("movimientos")
//    suspend fun getItems(): Movimientos
//
//    @GET("movimientos/{uuid}")
//    suspend fun getItem(@Path("uuid") uuid: String): Movimiento
//
//    @POST("movimientos")
//    suspend fun createItem(@Body movimientos: List<Movimiento>): Movimientos
//
//    @PUT("movimientos/{uuid}")
//    suspend fun updateItem(
//        @Path("uuid") uuid: String,
//        @Body item: Movimiento
//    ): Movimiento //TODO ojo
//
//    @DELETE("movimientos/{uuid}")
//    suspend fun deleteItem(@Path("uuid") uuid: String)

    @Insert
    fun insert(entity: Movimiento)

    @Query("SELECT * FROM movimiento")
    fun getAll(): List<Movimiento?>?

    @Update
    fun update(entity: Movimiento)

    @Delete
    fun delete(entity: Movimiento)
}