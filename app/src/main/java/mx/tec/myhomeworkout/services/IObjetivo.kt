package mx.tec.myhomeworkout.services

import mx.tec.myhomeworkout.model.ObjetivoModel
import retrofit2.Call
import retrofit2.http.*

interface IObjetivo {

    @GET(value="objetivo/")
    fun getallObjetivos(): Call<List<ObjetivoModel>>

    @GET(value="objetivo/{idObjetivo}")
    fun getObjetivoById(@Path(value="idObjetivo") id:Int): Call<ObjetivoModel>

    @PUT(value="objetivo/")
    fun updateObjetivo(@Body ejercicio: ObjetivoModel): Call<ObjetivoModel>

    @POST("objetivo/")
    fun createObjetivo(@Body ejercicio: ObjetivoModel?): Call<String>

    @DELETE("objetivo/{idObjetivo}/")
    fun deleteObjetivo(@Path("idObjetivo") id: Int): Call<String>

}