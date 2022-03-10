package com.example.quemoheroi;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quemoheroi.Tasks.TaskBaixarHeroisId;
import com.example.quemoheroi.api.DadosHeroisInterface;
import com.example.quemoheroi.intfce.OnTaskId;
import com.example.quemoheroi.model.Herois;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InformacoesCompletas extends AppCompatActivity implements OnTaskId {

    private ImageView fotoHeroi;
    private TextView txtNome,inteligencia,velocidade,forca,durabilidade,potencia,combate,origem,editora,
            nomeCompleto,alterEgo,aliases,occupation,base,groupAffiliation,relatives,gender,race,height,weight;

    private int idHeroi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_completas);

        iniciaControles();

        //Recupera ID do item clicado
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int idHeroiRecuperado = bundle.getInt("infoIdHeroi");

            idHeroi = idHeroiRecuperado;

            //chama task indicando contexto e ID
            new TaskBaixarHeroisId(this,idHeroi).execute();
        }
    }

    public void iniciaControles()
    {
        fotoHeroi = findViewById(R.id.imageHeroisInfo);
        txtNome = findViewById(R.id.txtNomeInfo);
        inteligencia = findViewById(R.id.intelligence);
        velocidade = findViewById(R.id.speed);
        forca = findViewById(R.id.strength);
        durabilidade = findViewById(R.id.durability);
        potencia = findViewById(R.id.power);
        combate = findViewById(R.id.combat);
        origem = findViewById(R.id.placeOfBirth);
        editora = findViewById(R.id.publisher);
        nomeCompleto = findViewById(R.id.fullName);
        alterEgo = findViewById(R.id.alterEgos);
        occupation = findViewById(R.id.occupation);
        base = findViewById(R.id.base);
        groupAffiliation = findViewById(R.id.groupAffiliation);
        relatives = findViewById(R.id.relatives);
        gender = findViewById(R.id.gender);
        race = findViewById(R.id.race);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        aliases = findViewById(R.id.aliases);
    }

    @Override
    public void onTaskId(int id, Herois infoHeroi)
    {
        if (infoHeroi.getImages().getLg() != null)
        {
            Uri uri =Uri.parse(infoHeroi.getImages().getLg()) ;
            Glide.with(InformacoesCompletas.this).load(uri).into(fotoHeroi);
        }

        txtNome.setText("Nome: "+infoHeroi.getName());
        origem.setText("Origem: "+infoHeroi.getBiography().getPlaceOfBirth());
        editora.setText("Editora: "+infoHeroi.getBiography().getPublisher());
        inteligencia.setText("Inteligência: "+infoHeroi.getPowerStats().getIntelligence());
        forca.setText("Força: "+infoHeroi.getPowerStats().getStrength());
        velocidade.setText("Velocidade: "+infoHeroi.getPowerStats().getSpeed());
        durabilidade.setText("Durabilidade: "+infoHeroi.getPowerStats().getDurability());
        potencia.setText("Pontência: "+infoHeroi.getPowerStats().getPower());
        combate.setText("Combate: "+infoHeroi.getPowerStats().getCombat());
        nomeCompleto.setText("Nome completo: "+infoHeroi.getBiography().getFullName());
        alterEgo.setText("Alter Ego: "+infoHeroi.getBiography().getAlterEgos());
        aliases.setText("Aliases: "+infoHeroi.getBiography().getAliases());
        occupation.setText("Ocupação: "+infoHeroi.getWork().getOccupation());
        base.setText("Base: "+infoHeroi.getWork().getBase());
        groupAffiliation.setText("Faz parte: "+infoHeroi.getConnections().getGroupAffiliation());
        relatives.setText("Parentes: "+infoHeroi.getConnections().getRelatives());
        gender.setText("Genêro: "+infoHeroi.getAppearance().getGender());
        race.setText("Raça: "+infoHeroi.getAppearance().getRace());
        height.setText("Altura: "+infoHeroi.getAppearance().getHeight());
        weight.setText("Peso: "+infoHeroi.getAppearance().getWeight());
    }
}