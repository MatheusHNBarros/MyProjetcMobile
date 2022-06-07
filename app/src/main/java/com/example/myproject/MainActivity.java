package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText enviarMensagem = null;
    CursoService requestCurso = null;
    CursoResponse idCurso = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CursoPost cursoPost = new CursoPost();
        //cursoPost.setName("Nome do curso");

        requestCurso = new RetrofitConfig()
                .criarService();

        enviarMensagem = findViewById(R.id.etMensagem);
        Button botaoEnviar = findViewById(R.id.btEnviar);

        botaoEnviar.setOnClickListener(view -> {
            String conteudo = enviarMensagem.getText().toString();

            CursoPost novoCurso = new CursoPost();
            novoCurso.setName(conteudo);

            requestCurso.createRequestPost(novoCurso).enqueue(new Callback<CursoResponse>() {
                @Override
                public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                    idCurso = response.body();
                    Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<CursoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
                }
            });
        });

        //  requestCurso.enqueue(new Callback<CursoResponse>() {
        //     @Override
        //   public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
        //     Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
        //}

        //@Override
        //public void onFailure(Call<CursoResponse> call, Throwable t) {
        //  Toast.makeText(getApplicationContext(), "Falha na request", Toast.LENGTH_LONG).show();
        //}
        //});

        //try {
        //requestCurso.execute();
        //} catch (IOException e) {
        // e.printStackTrace();
        //}

        alterarCurso();

    }
        private void alterarCurso() {

            Button botaoEditar = findViewById(R.id.btEditar);

            botaoEditar.setOnClickListener(view -> {
                String editarCurso = enviarMensagem.getText().toString();

                CursoPost alterarNome = new CursoPost();
                alterarNome.setName(editarCurso);

                requestCurso.createRequestPut(alterarNome, idCurso.getId()).enqueue(new Callback<CursoResponse>() {
                    @Override
                    public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                        Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CursoResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha de comunicação", Toast.LENGTH_SHORT).show();
                    }
                });

            });

        }

    }
