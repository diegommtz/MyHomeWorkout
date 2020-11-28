package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.Objetivo
import retrofit2.Call
import retrofit2.http.*

interface IObjetivo {

    @GET(value="objetivo/")
    fun getEjercicio(): Call<List<Objetivo>>

    @GET(value="objetivo/{idObjetivo}")
    fun getObjetivoById(@Path(value="idObjetivo") id:Int): Call<Objetivo>

    @PUT(value="objetivo/")
    fun updateObjetivo(@Body ejercicio: Objetivo): Call<Objetivo>

    @POST("objetivo/")
    fun createObjetivo(@Body ejercicio: Objetivo?): Call<Objetivo>

    @DELETE("objetivo/{idObjetivo}/")
    fun deleteObjetivo(@Path("idObjetivo") id: Int): Call<Objetivo>

}