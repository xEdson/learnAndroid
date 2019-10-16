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
        onSelecionar();
    }

    @Override
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onSelecionar() {
        saida = view.findViewById(R.id.names);
        String sql = "Select * from usuarios";
        int numAcertos = 0;
        int numErros = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            StringBuilder str = new StringBuilder();
            do {
                numAcertos += cursor.getInt(3);
                numErros += cursor.getInt(2);

            } while (cursor.moveToNext());
            if (numAcertos != 0 || numErros != 0)
                saida.setText((100 * numAcertos) / (numAcertos + numErros));
            else
                saida.setText("sem dados suficienres");
        }
        cursor.close();
    }

}
