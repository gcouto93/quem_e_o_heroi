package com.example.quemoheroi.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.quemoheroi.InformacoesCompletas;
import com.example.quemoheroi.api.DadosHeroisInterface;
import com.example.quemoheroi.intfce.OnTaskId;
import com.example.quemoheroi.model.Herois;

import retrofit2.Call;
import retrofit2.Response;

public class TaskBaixarHeroisId extends AsyncTask {

    //define parametro para construtor
    private OnTaskId listener;
    private int id;
    Herois heroiSelecionado;

    public TaskBaixarHeroisId(OnTaskId listener, int id)
    {
        this.listener = listener;
        this.id = id;
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {
        DadosHeroisInterface dadosHeroisInterface = null;
        Call<Herois> call = null;
        Response<Herois> resposta = null;

        try
        {
            call = ChamarApi.chamarTodosHerois(dadosHeroisInterface,ChamarApi.URL).recuperarIdHeroi(id);
            resposta = call.execute();

            if (resposta != null)
            {
                if (resposta.body() != null)
                {
                    heroiSelecionado = resposta.body();
                }
            }
            else
                {
                   Log.d("Erro","Nao carregou dados da API");
                }
        }
        catch (Exception err)
        {
            Log.d("erro: ",err.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        listener.onTaskId(id,heroiSelecionado);
        super.onPostExecute(o);
    }
}
