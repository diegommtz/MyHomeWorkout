package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.Persona
import retrofit2.Call
import retrofit2.http.*

interface IPersona {
    @GET(value="persona/")
    fun listPersonas(): Call<List<Persona>>

    @GET(value="persona/{id}")
    fun getPersona(@Path(value="id") id: Int) : Call<Persona>

    @POST("persona/")
    fun createPersona(@Body persona: Persona?) : Call<Boolean>

    @PUT("persona/{id}")
    fun updatePersona(@Path(value="id") id: Int, @Body persona: Persona?) : Call<Boolean>

    @DELETE("persona/{id}")
    fun deletePersona(@Path(value="id") id: Int) : Call<Boolean>
}