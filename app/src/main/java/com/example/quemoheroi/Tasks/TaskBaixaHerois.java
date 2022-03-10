package com.example.quemoheroi.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.quemoheroi.api.DadosHeroisInterface;
import com.example.quemoheroi.intfce.OnTaskCompleted;
import com.example.quemoheroi.model.Herois;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class TaskBaixaHerois extends AsyncTask
{
    private OnTaskCompleted listener = null;
    private List<Herois> listaHerois;

    public TaskBaixaHerois(OnTaskCompleted listener)

    {
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        DadosHeroisInterface heroisInterface = null;
        Call<List<Herois>> call = null;
        Response<List<Herois>> resposta = null;

        try
        {
            //faz chamada do serviço
            call = ChamarApi.chamarTodosHerois(heroisInterface,ChamarApi.URL).todosHerois();
            //pega resposta do chamado
            resposta = call.execute();
            if (resposta != null)
            {
                if (resposta.body() != null)
                {
                    listaHerois = resposta.body();
                }
            }
        }
        catch (Exception err)
        {
            Log.d("err",err.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        listener.onTaskCompleted(listaHerois);
    }
}
