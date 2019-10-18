package e196244_r176519.ft.unicamp.br.aula03.Puzzle2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import e196244_r176519.ft.unicamp.br.aula03.DataBase.DatabaseHelper;
import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Assets extends Fragment {

    private View view;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private TextView saida;

    public Assets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_assets, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        onSelecionarPorcentagem();
        onSelecionarNomeMaisErrado();
        onSelecionarPessoaMaisErrada();
    }

    @Override
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onSelecionarPorcentagem() {
        saida = view.findViewById(R.id.maiorPorcentagem);
        String sql = "Select * from usuarios where Acertos > 0 or Erros > 0";
        int numAcertos = 0;
        int numErros = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                numAcertos += cursor.getInt(3);
                numErros += cursor.getInt(2);

            } while (cursor.moveToNext());
            if (numAcertos != 0 || numErros != 0)
                saida.setText("Porcentagem de Acertos: " + (100 * numAcertos) / (numAcertos + numErros) + "%");

        }else{
            saida.setText("Porcentagem de erros: sem dados suficienres");
        }
        cursor.close();
    }

    public void onSelecionarNomeMaisErrado() {
        saida = view.findViewById(R.id.nomeMaisErrado);
        String sql = "Select Nome from usuarios where FalsoPositivo > 0 order by FalsoPositivo";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst())
            saida.setText("Nome que mais fez o jogador errar: " + cursor.getString(cursor.getColumnIndex("Nome")));
        else
            saida.setText("Nome que mais fez o jogador errar: sem dados suficienres");
        cursor.close();
    }
    public void onSelecionarPessoaMaisErrada() {
        saida = view.findViewById(R.id.pessoaMaisErrada);
        String sql = "Select Nome from usuarios where Erros > 0 order by Erros";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst())
            saida.setText("Pessoa mais errada: " + cursor.getString(cursor.getColumnIndex("Nome")));
        else
            saida.setText("Pessoa mais errada: sem dados suficienres");
        cursor.close();
    }

}
