package com.example.quemoheroi.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quemoheroi.InterfaceClick;
import com.example.quemoheroi.R;
import com.example.quemoheroi.model.Herois;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaHeroi extends RecyclerView.Adapter implements View.OnClickListener
{

    private Context context;
    private List<Herois> arrHerois;
    private boolean bOcorrencia;
    private InterfaceClick interfaceClick;

    /**
     * Construtor da classe
     * @param ctx contexto da tela
     */
    public AdapterListaHeroi(Context ctx, List<Herois> arrHeroisParam, InterfaceClick interfaceClickParam)
    {
        this.context = ctx;
        this.arrHerois = arrHeroisParam;
        this.interfaceClick = interfaceClickParam;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;
        AdapterListaHeroi.ViewHolder holder = null;

        try
        {
            //Pega a viel
            view = LayoutInflater.from(context).inflate(R.layout.item_lista_herois, parent, false);

            //Instancia o holder
            holder = new AdapterListaHeroi.ViewHolder(view);
        }
        catch (Exception err)
        {
            //Tratar o erro aqui
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int iPosicao)
    {
        ViewHolder viewHolder = null;
        Herois heroi = null;

        //Pega o viewHolder
        viewHolder = (ViewHolder) holder;

        heroi = arrHerois.get(iPosicao);

        viewHolder.txtApelido.setText( heroi.getName());
        viewHolder.txtNome.setText("Nome: "+heroi.getBiography().getFullName());
        viewHolder.txtOrigem.setText("Origem: "+heroi.getBiography().getPlaceOfBirth());
        viewHolder.txtRaca.setText("Raça: "+heroi.getAppearance().getRace());



        if (heroi.getImages().getXs() != null)
        {
            Uri uri = Uri.parse(heroi.getImages().getMd());
            Glide.with(context).load(uri).into(viewHolder.imgHeroi);
        }
        viewHolder.itemView.setTag(heroi.getId());
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount()
    {
        return arrHerois.size();
    }

    @Override
    public void onClick(View view)
    {
        interfaceClick.onItemClick((Integer) view.getTag());
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtNome,txtApelido,txtOrigem,txtRaca;

        ImageView imgHeroi;

        public ViewHolder(View view)
        {
            super(view);

            txtNome = view.findViewById(R.id.nomeHeroi);
            txtApelido = view.findViewById(R.id.apelidoHeroi);
            txtOrigem = view.findViewById(R.id.origemHeroi);
            txtRaca = view.findViewById(R.id.bioHeroi);
            imgHeroi = view.findViewById(R.id.imageHeroi);
        }
    }
}
