package com.example.quemoheroi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.quemoheroi.Tasks.TaskBaixaHerois;
import com.example.quemoheroi.adapter.AdapterListaHeroi;
import com.example.quemoheroi.api.DadosHeroisInterface;
import com.example.quemoheroi.helper.RecyclerItemClickListener;
import com.example.quemoheroi.intfce.OnTaskCompleted;
import com.example.quemoheroi.model.Herois;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted, TextWatcher, InterfaceClick
{
    private RecyclerView rcvHerois;
    public AdapterListaHeroi adapterListaHeroi = null;
    public List<Herois> arrayListGeral = null;

    private EditText edtPesquisa;
    private RelativeLayout rtlPesquisaHeroi;
    private boolean bPesquisarHeroi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TaskBaixaHerois(this).execute();

        rtlPesquisaHeroi = findViewById(R.id.rltPesquisaHeroi);
        rcvHerois = findViewById(R.id.rcvListaHerois);
        edtPesquisa = findViewById(R.id.edtPesquisaBiblia);

        edtPesquisa.addTextChangedListener(this);

    }

    @Override
    public void onTaskCompleted(Object o)
    {
        arrayListGeral = (List<Herois>) o;
        preencheListaHerois(arrayListGeral);
    }

    public void preencheListaHerois(List<Herois> list)
    {
        LinearLayoutManager linearLayoutManager = null;
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rcvHerois.setLayoutManager(linearLayoutManager);

        adapterListaHeroi = new AdapterListaHeroi(MainActivity.this, list, this);
        rcvHerois.setAdapter(adapterListaHeroi);
        adapterListaHeroi.notifyDataSetChanged();

//        //trata o clique
//        rcvHerois.addOnItemTouchListener(
//                new RecyclerItemClickListener(
//                        MainActivity.this,
//                        rcvHerois,
//                        new RecyclerItemClickListener.OnItemClickListener()
//                        {
//                            @Override
//                            public void onItemClick(View view, int position)
//                            {
//                                //identifica posição clicada na lista
//                                Herois heroiSelecionado = list.get(position);
//
//
//                                Intent i = new Intent(MainActivity.this,InformacoesCompletas.class);
//                                //envia ID do item clicado
//                                i.putExtra("infosHerois",String.valueOf(heroiSelecionado.getId()));
//                                startActivity(i);


//                                for (Herois listaAuxHerois : list)
//                                {
//                                    Log.d("nomeHerois",listaAuxHerois.getSlug());
//                                }
//                            }
//
//                            @Override
//                            public void onLongItemClick(View view, int position)
//                            {
//                            }
//
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//                            {
//                            }
//                        }));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.item_pesquisa)
        {
            if (bPesquisarHeroi)
            {
                bPesquisarHeroi = false;
                rtlPesquisaHeroi.setVisibility(View.GONE);
            }
            else
            {
                bPesquisarHeroi = true;
                rtlPesquisaHeroi.setVisibility(View.VISIBLE);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_herois,menu);
        MenuItem pesquisarHeroiItem = menu.findItem(R.id.item_pesquisa);

        Drawable newIcon = pesquisarHeroiItem.getIcon();

        newIcon.mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        pesquisarHeroiItem.setIcon(newIcon);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence letrasDigitadas, int i, int i1, int i2)
    {
        try
        {
            if (letrasDigitadas.length() > 1)
            {
                List<Herois> arrHeroisAux = new ArrayList<>();

                for (Herois herois : arrayListGeral)
                {
                    if (herois.getSlug().toLowerCase().contains(letrasDigitadas.toString().toLowerCase()))
                    {
                        arrHeroisAux.add(herois);
                    }
                    preencheListaHerois(arrHeroisAux);
                }

            }
        }
        catch (Exception e)
        {

        }

    }

    @Override
    public void afterTextChanged(Editable editable)
    {

    }

    @Override
    public void onItemClick(int id)
    {
        Intent i= new Intent(MainActivity.this,InformacoesCompletas.class);
        i.putExtra("infoIdHeroi",id);
        startActivity(i);
    }
}

