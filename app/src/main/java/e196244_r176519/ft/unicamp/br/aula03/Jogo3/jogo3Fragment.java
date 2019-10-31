package e196244_r176519.ft.unicamp.br.aula03.Jogo3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class jogo3Fragment extends Fragment {

    View view;
    String nomeCorreto;
    RadioButton b1;
    RadioButton b2;
    RadioButton b3;
    RadioButton b4;
    RadioButton b5;
    RadioGroup rg;
    TextView textView;
    TextView saida;

    public jogo3Fragment() {
        // Required empty public constructor
        nomeCorreto = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_jogo3, container, false);

        textView = view.findViewById(R.id.frase);
        rg = view.findViewById(R.id.rdGroup);
        b1 = view.findViewById(R.id.nome1);
        b2 = view.findViewById(R.id.nome2);
        b3 = view.findViewById(R.id.nome3);
        b4 = view.findViewById(R.id.nome4);
        b5 = view.findViewById(R.id.nome5);

        view.findViewById(R.id.btnatt).setOnClickListener(
                new gameItemClick(this));

        view.findViewById(R.id.btnChutar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        validateCorrect();
                    }
                }
        );

        return view;
    }

    public void setCorrectName(String nome) {
        this.nomeCorreto = nome;
    }

    public void setPhraseAndNames(String frase, String n1, String n2, String n3, String n4, String n5) {
        textView.setText(frase);
        b1.setText(n1);
        b2.setText(n2);
        b3.setText(n3);
        b4.setText(n4);
        b5.setText(n5);
    }

    public void validateCorrect() {
        int id = rg.getCheckedRadioButtonId();
        String typo;
        if (getCorrect(id)) {
            Toast.makeText(getContext(), "Acertou", Toast.LENGTH_SHORT).show();
            typo = "Acertos";
        } else {
            Toast.makeText(getContext(), "Errou", Toast.LENGTH_SHORT).show();
            typo = "Erros";
        }
        rg.clearCheck();
        new MyViaCepAsyncTask(this).execute();
        new MyFirstWebClient(nomeCorreto, typo).execute();
    }

    private boolean getCorrect(int id) {
        return ((RadioButton) view.findViewById(id)).getText().equals(this.nomeCorreto);
    }

    public class gameItemClick implements View.OnClickListener {
        jogo3Fragment pg;

        public gameItemClick(jogo3Fragment pg) {
            new MyViaCepAsyncTask(pg).execute();
            this.pg = pg;
        }

        @Override
        public void onClick(View v) {
            if (pg != null) {
                new MyViaCepAsyncTask(pg).execute();
            }
        }

    }

}
