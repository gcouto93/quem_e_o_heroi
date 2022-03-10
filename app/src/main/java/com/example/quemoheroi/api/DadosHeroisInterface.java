package com.example.quemoheroi.api;

import com.example.quemoheroi.model.Herois;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DadosHeroisInterface
{
    @GET("all.json")
    Call<List<Herois>> todosHerois();

    @GET("id/{id}.json")
     Call<Herois> recuperarIdHeroi(@Path("id")int id);
}
