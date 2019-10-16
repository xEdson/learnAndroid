package e196244_r176519.ft.unicamp.br.aula03.Puzzle2;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import e196244_r176519.ft.unicamp.br.aula03.DataBase.DatabaseHelper;
import e196244_r176519.ft.unicamp.br.aula03.MainActivity;
import e196244_r176519.ft.unicamp.br.aula03.R;
import e196244_r176519.ft.unicamp.br.aula03.alunos.Aluno;
import e196244_r176519.ft.unicamp.br.aula03.alunos.Alunos;

import static e196244_r176519.ft.unicamp.br.aula03.alunos.Alunos.alunos;


public class NameFragment extends Fragment {

    private View lview;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Random random = new Random();
    private String nomeCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_name, container, false);
        }

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));

        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEscolhido = ((Button) v).getText().toString();
                if (nomeEscolhido.equals(nomeCorreto)) {
                    txtFeedback.setText("Correto!!");
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    txtFeedback.setText("Incorreto!!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: " + numTentativas);

                    if (numTentativas == 0) {
                        txtFeedback.setText("Você Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ((MainActivity) getActivity()).showBiografia(positionAluno);
                                    }
                                }, 2000);


                    }
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setOnClickListener(guessButtonListener);
        }

        return lview;
    }

    private void startGame() {
        int guess = random.nextInt(alunos.length);
        positionAluno = guess;
        Aluno aluno = alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            Aluno candidate = alunos[(guess + i) % alunos.length];
            arrayList.add(candidate.getNome().split(" ")[0].toLowerCase());
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i));
        }
    }

    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        if(validadeEmptyDb() == 0){
            for(int x = 0; x < alunos.length; x ++)
                onInserir(x, alunos[x].getNome(), 0,0, 0);
        }
    }

    private int validadeEmptyDb(){
        String sql = "Select * from usuarios";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int aux = cursor.getCount();
        cursor.close();
        return aux;
    }

    public void onInserir(int id, String nome, int acertos, int erros, int FalsoPositivo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("Nome", nome);
        contentValues.put("Erros", erros);
        contentValues.put("Acertos", acertos);
        contentValues.put("FalsoPositivo", FalsoPositivo);

        sqLiteDatabase.insert("usuarios", null, contentValues);
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

}
