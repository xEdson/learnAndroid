package e196244_r176519.ft.unicamp.br.aula03.Jogo3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssetsGame3Fragments extends Fragment {


    View view;

    public AssetsGame3Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_blank, container, false);

        new GetPeopleByWebClient(this).execute();

        return view;
    }

    public void put(String val){
        ((TextView) view.findViewById(R.id.lista3)).setText(val);

    }
}
