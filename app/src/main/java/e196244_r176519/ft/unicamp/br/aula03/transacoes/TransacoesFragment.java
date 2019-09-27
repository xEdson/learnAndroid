package e196244_r176519.ft.unicamp.br.aula03.transacoes;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import e196244_r176519.ft.unicamp.br.aula03.MainActivity;
import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransacoesFragment extends Fragment {

    private View view;
    private RecyclerView recycle;
    private TransacoesAdapter adapter;


    public TransacoesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(view == null)
            view = inflater.inflate(R.layout.fragment_transacoes, container, false);

        recycle = view.findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new TransacoesAdapter(Transacoes.listTransaction);


        adapter.setTransacaoItemClickListener(new TransacoesAdapter.TransacaoItemClickListener() {
            @Override
            public void onMyItemClick(String name) {
                Toast.makeText(getActivity(), name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyItemLongClick(int position) {
                ((MainActivity) Objects.requireNonNull(getActivity())).showTransaction(position);
            }

        });
        recycle.setAdapter(adapter);
        return view;
    }

}
