package com.example.quemoheroi.Tasks;
import com.example.quemoheroi.api.DadosHeroisInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChamarApi  {

    public static final String URL = "https://akabab.github.io/superhero-api/api/";

    public static DadosHeroisInterface chamarTodosHerois(DadosHeroisInterface heroisInterface, String sURL){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        heroisInterface = retrofit.create(DadosHeroisInterface.class);

        return heroisInterface;
    }
}
