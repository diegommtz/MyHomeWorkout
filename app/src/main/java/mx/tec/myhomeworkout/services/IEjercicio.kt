package mx.tec.myhomeworkout.services

import mx.tec.myhomeworkout.model.Ejercicio
import retrofit2.Call
import retrofit2.http.*

interface IEjercicio {
    @GET(value="ejercicio/")
    fun getAllEjercicios(): Call<List<Ejercicio>>

    @GET(value="ejercicio/{idEjercicio}")
    fun getEjercicioById(@Path(value="idEjercicio") id:Int):Call<Ejercicio>

    @PUT(value="ejercicio/")
    fun updateEjercicio(@Body ejercicio:Ejercicio):Call<Ejercicio>

    @POST("ejercicio/")
    fun createEjercicio(@Body ejercicio: Ejercicio?): Call<Ejercicio>

    @DELETE("ejercicio/{idEjercicio}/")
    fun deleteEjercicio(@Path("idEjercicio") id: Int): Call<Ejercicio>

}