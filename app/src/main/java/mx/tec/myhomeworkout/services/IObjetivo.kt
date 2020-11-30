package mx.tec.myhomeworkout.services

import mx.tec.myhomeworkout.model.Objetivo
import retrofit2.Call
import retrofit2.http.*

interface IObjetivo {

    @GET(value="objetivo/")
    fun getallObjetivos(): Call<List<Objetivo>>

    @GET(value="objetivo/{idObjetivo}")
    fun getObjetivoById(@Path(value="idObjetivo") id:Int): Call<Objetivo>

    @PUT(value="objetivo/")
    fun updateObjetivo(@Body ejercicio: Objetivo): Call<Objetivo>

    @POST("objetivo/")
    fun createObjetivo(@Body ejercicio: Objetivo?): Call<String>

    @DELETE("objetivo/{idObjetivo}/")
    fun deleteObjetivo(@Path("idObjetivo") id: Int): Call<String>

}