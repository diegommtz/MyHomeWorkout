package mx.tec.myhomeworkout.data

import mx.tec.myhomeworkout.model.Musculo
import retrofit2.Call
import retrofit2.http.*

interface IMusculo {
    @POST("musculo/")
    fun CreateMusculo(@Body musculo: Musculo) : Call<Musculo>

    @GET(value="musculo/{idMusculo}")
    fun GetMusculo(@Path(value="idMusculo") idMusculo: String) : Call<Musculo>

    @GET(value="musculo/")
    fun GetAllMusculos(): Call<List<Musculo>>

    @PUT(value="musculo/")
    fun UpdateMusculo(@Body musculo: Musculo) : Call<Musculo>

    @DELETE(value="musculo/{idMusculo}")
    fun DeleteMusculo(@Path(value="idMusculo") idMusculo: String)
}