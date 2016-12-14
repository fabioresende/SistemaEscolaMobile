package com.example.a13626488683.prova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView saidaValorConverter = (TextView) findViewById(R.id.resposta);
        final ImageView imagens = (ImageView) findViewById(R.id.imagens);
        Retrofit service = Servico.createService(Retrofit.class);

        Call<Objeto> call = service.converterIMC();

        call.enqueue(new Callback<Objeto>() {
            @Override
            public void onResponse(Call<Objeto> call, Response<Objeto> response) {

                if (response.isSuccessful()) {

                    Objeto respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {



                            Double peso = respostaServidor.getPeso();
                            Double altura = respostaServidor.getAltura();
                            Double imc = peso/(altura*altura);
                            saidaValorConverter.setText(imc.toString());
                            if(imc<18.5){
                                imagens.setImageResource(R.drawable.imagem2);
                            }
                            else{
                                if(imc<=18.5 && imc<25){
                                    imagens.setImageResource(R.drawable.imagem3);
                                }
                                else {
                                    if(imc>=25 && imc<30){
                                        imagens.setImageResource(R.drawable.imagem4);
                                    }
                                    else{
                                        if(imc>=30){
                                            imagens.setImageResource(R.drawable.imagem5);
                                        }
                                    }
                                }

                            }

                    } else {

                        Toast.makeText(getApplicationContext(),"Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Objeto> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
