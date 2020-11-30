package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.HorarioModel
import retrofit2.Call
import retrofit2.http.*

interface IHorario {

    @GET(value="horario/")
    fun getAllHorarios(): Call<List<HorarioModel>>

    @GET(value="horario/{idHorario}")
    fun getHorarioById(@Path(value="idHorario") id:Int): Call<HorarioModel>

    @PUT(value="horario/")
    fun updateHorario(@Body ejercicio: HorarioModel): Call<HorarioModel>

    @POST("horario/")
    fun createHorario(@Body ejercicio: HorarioModel?): Call<HorarioModel>

    @DELETE("horario/{idHorario}/")
    fun deleteHorario(@Path("idHorario") id: Int): Call<HorarioModel>

}