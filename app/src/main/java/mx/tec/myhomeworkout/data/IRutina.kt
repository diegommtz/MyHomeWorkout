package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.Rutina
import retrofit2.Call
import retrofit2.http.*

interface IRutina {
    @GET(value="rutina/")
    fun getAllRutinas(): Call<List<Rutina>>

    @GET(value="rutina/{idRutina}")
    fun getRutinaById(@Path(value="idRutina") id:Int): Call<Rutina>

    @PUT(value="rutina/")
    fun updateRutina( @Body rutina:Rutina): Call<Rutina>

    @POST("rutina/")
    fun createRutina(@Body rutina: Rutina?): Call<Rutina>

    @DELETE("rutina/{idRutina}/")
    fun deleteRutina(@Path("idRutina") id: Int): Call<Rutina>

}