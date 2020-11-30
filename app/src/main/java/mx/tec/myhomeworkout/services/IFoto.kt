package mx.tec.myhomeworkout.services

import mx.tec.myhomeworkout.model.Foto
import retrofit2.Call
import retrofit2.http.*

interface IFoto {
    @POST("foto/{idPersona}")
    fun CreateFoto(@Path(value="idPersona") idPersona: String, @Body foto: Foto) : Call<Foto>

    @GET(value="foto/{idPersona}/{idFoto}")
    fun GetFotoPersonaById(@Path(value="idPersona") idPersona: String, @Path(value="idFoto") idFoto: String) : Call<Foto>

    @GET(value="foto/{idPersona}")
    fun GetFotosPersona(@Path(value="idPersona") idPersona: String): Call<List<Foto>>

    @DELETE(value="foto/{idPersona}")
    fun DeleteFotosPersona(@Path(value="idPersona") idPersona: String)

    @DELETE(value="foto/{idPersona}/{idFoto}")
    fun DeleteFoto(@Path(value="idPersona") idPersona: String, @Path(value="idFoto") idFoto: String)
}