package e196244_r176519.ft.unicamp.br.aula03.Jogo3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MyFirstWebClient extends AsyncTask<String, Void, String> {

    private String nome;
    private String typo;
    private int acertos;
    private int erros;


    public MyFirstWebClient(String nome, String typo) {
        this.nome = nome;
        this.typo = typo;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... args) {
        getValue(args);
        if (typo.equals("Acertos")) {
            acertos++;
        } else {
            erros++;
        }
        return setValue();
    }

    @NotNull
    private String getValue(String[] args) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://aula11-ddae5.firebaseio.com/" + nome + "/.json";

        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL("https://aula11-ddae5.firebaseio.com/.json");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");

            if (args.length == 3) {
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                outputStreamWriter.write(args[2]);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            /*

          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int value;
            try {
                JSONObject obj = new JSONObject(sb.toString());
                acertos = obj.getJSONObject(nome).getInt("Acertos");
                erros = obj.getJSONObject(nome).getInt("Erros");
            } catch (Exception e) {
                acertos = 0;
                erros = 0;
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @NotNull
    private String setValue() {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://aula11-ddae5.firebaseio.com/.json";

        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL("https://aula11-ddae5.firebaseio.com/.json");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("PATCH");

            String json = "{\"" + nome + "\": {\"Acertos\":\"" + acertos + "\",\"Erros\" : \"" + erros + "\"}}";


            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(json);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            /*

          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int value;
            try {
                JSONObject obj = new JSONObject(sb.toString());
                acertos = obj.getJSONObject(nome).getInt("Acertos");
                erros = obj.getJSONObject(nome).getInt("Erros");
            } catch (Exception e) {
                acertos = 0;
                erros = 0;
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }
}