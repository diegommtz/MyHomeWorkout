package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.Horario
import retrofit2.Call
import retrofit2.http.*

interface IHorario {

    @GET(value="horario/")
    fun getHorario(): Call<List<Horario>>

    @GET(value="horario/{idHorario}")
    fun getHorarioById(@Path(value="idHorario") id:Int): Call<Horario>

    @PUT(value="horario/")
    fun updateHorario(@Body ejercicio: Horario): Call<Horario>

    @POST("horario/")
    fun createHorario(@Body ejercicio: Horario?): Call<Horario>

    @DELETE("horario/{idHorario}/")
    fun deleteHorario(@Path("idHorario") id: Int): Call<Horario>

}