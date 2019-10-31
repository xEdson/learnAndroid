package e196244_r176519.ft.unicamp.br.aula03.Jogo3;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.function.Consumer;

public class GetPeopleByWebClient extends AsyncTask<String, Void, String> {

    private AssetsGame3Fragments txt;
    private StringBuilder sb;
    private StringBuilder bd;


    public GetPeopleByWebClient(AssetsGame3Fragments txt) {
        this.txt = txt;
    }

    @Override
    protected void onPreExecute() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... args) {
        return getValue(args);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NotNull
    private String getValue(String[] args) {
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


            sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int value;
            try {
                String name = "";
                String total = "";
                JSONObject jsn = new JSONObject(sb.toString());
                bd = new StringBuilder();
                Iterator<String> obj = jsn.keys();
                for (int x = 0; obj.hasNext(); x++) {
                    name = obj.next();
                    total = name +
                            "\n" +
                            " ACERTOS : " + (jsn.getJSONObject(name).getInt("Acertos") / (jsn.getJSONObject(name).getInt("Acertos") + jsn.getJSONObject(name).getInt("Erros"))) * 100 +
                            " ERROS : "   + (jsn.getJSONObject(name).getInt("Erros") / (jsn.getJSONObject(name).getInt("Acertos") + jsn.getJSONObject(name).getInt("Erros"))) * 100 +
                            "\n<---------------->\n";
                    bd.append(total);
                }
                return bd.toString();
            } catch (Exception e) {

            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        txt.put(bd.toString());

    }
}