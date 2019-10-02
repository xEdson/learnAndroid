package e196244_r176519.ft.unicamp.br.aula03.alunos;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import e196244_r176519.ft.unicamp.br.aula03.MainActivity;
import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {


    public AlunosFragment() {
        // Required empty public constructor
    }

    private View view;
    private RecyclerView recycle;
    private MyFirstAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null)
            view = inflater.inflate(R.layout.fragment_alunos, container, false);

        recycle = view.findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));

        adapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void onMyItemClick(String name) {
                Toast.makeText(getActivity(), name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyItemLongClick(int position) {
                ((MainActivity) getActivity()).showBiografia(position);
            }
        });
        recycle.setAdapter(adapter);
        return view;
    }

}
