package mx.tec.myhomeworkout.services

import mx.tec.myhomeworkout.Horario
import mx.tec.myhomeworkout.model.HorarioModel
import retrofit2.Call
import retrofit2.http.*

interface IHorario {

    @GET(value="horario/")
    fun getAllHorarios(): Call<List<HorarioModel>>

    @GET(value="horario/{idHorario}")
    fun getHorarioById(@Path(value="idHorario") id:String): Call<HorarioModel>

    @PUT(value="horario/")
    fun updateHorario(@Body ejercicio: HorarioModel): Call<HorarioModel>

    @POST("horario/{idPersona}")
    fun createHorario(@Path(value="idPersona")id:String,@Body ejercicio: HorarioModel): Call<String>

    @DELETE("horario/{idHorario}/")
    fun deleteHorario(@Path("idHorario") id: Int): Call<String>

}