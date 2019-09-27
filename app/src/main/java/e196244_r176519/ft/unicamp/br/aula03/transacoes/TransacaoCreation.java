package e196244_r176519.ft.unicamp.br.aula03.transacoes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransacaoCreation extends Fragment {

    private View view;

    private EditText nome;
    private EditText descricao;
    private EditText valor;
    private Button btnCriar;
    private Button btndeletar;
    private CheckBox tipoPagamento;
    private int id;
    private Transacao transacao;
    private Spinner spinner;
    String todo;
    // TODO: Rename and change types of parameters

    public TransacaoCreation() {
        // Required empty public constructor
        transacao = new Transacao("", "", "", 0, Transacoes.listTransaction.size());
        todo = "Criar";
    }

    public TransacaoCreation(String nome, String descricao, String data, float valor, int id) {
        todo = "Atualizar";
        transacao = new Transacao(nome, descricao, data, valor, id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null)
            view = inflater.inflate(R.layout.fragment_transacao_creation, container, false);

        nome = view.findViewById(R.id.nome);
        descricao = view.findViewById(R.id.descricao);
        valor = view.findViewById(R.id.valor);
        btnCriar = view.findViewById(R.id.create);
        btndeletar = view.findViewById(R.id.delete);
        tipoPagamento = view.findViewById(R.id.tipoPagamento);

        this.nome.setText(transacao.nome);
        this.descricao.setText(transacao.definicao);
        this.tipoPagamento.setChecked(transacao.getValor() < 0);
        this.valor.setText(String.valueOf(Math.abs(transacao.getValor())));
        this.id = transacao.getId();
        this.btnCriar.setText(todo);
        spinner = view.findViewById(R.id.spinnertypo);

        if(todo.equals("Criar"))
            btndeletar.setEnabled(false);

        if(todo.equals("Atualizar"))
            btndeletar.setEnabled(true);

        List<String> list = new ArrayList<String>();
        list.add("Trabalho");
        list.add("Lazer");
        list.add("Alimentacao");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        if(transacao.getData().equals("Trabalho"))
            spinner.setSelection(0);
        if(transacao.getData().equals("Lazer"))
            spinner.setSelection(1);
        if(transacao.getData().equals("Alimentacao"))
            spinner.setSelection(2);

        btnCriar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(todo.equals("Atualizar")){
                    for(int x = 0; x < Transacoes.listTransaction.size(); x ++)
                        if(Transacoes.listTransaction.get(x).getId() == id){
                            Transacoes.listTransaction.set(x, new Transacao(nome.getText().toString(), descricao.getText().toString(), spinner.getSelectedItem().toString(), Float.valueOf(valor.getText().toString()) * (tipoPagamento.isChecked() ? -1 : 1),id));
                            return;
                        }
                }
                if(todo.equals("Criar")){
                    Transacoes.listTransaction.add(transacao.getId(), new Transacao(nome.getText().toString(), descricao.getText().toString(), spinner.getSelectedItem().toString(), Float.valueOf(valor.getText().toString()) * (tipoPagamento.isChecked() ? -1 : 1), id));
                }
            }
        });

        btndeletar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                for(int x = 0; x < Transacoes.listTransaction.size(); x ++)
                    if(Transacoes.listTransaction.get(x).getId() == id){
                        Transacoes.listTransaction.remove(x);
                        return;
                    }
            }
        });

        return view;
    }


}
