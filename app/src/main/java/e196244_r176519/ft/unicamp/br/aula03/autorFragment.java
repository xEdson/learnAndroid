package e196244_r176519.ft.unicamp.br.aula003;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e196244_r176519.ft.unicamp.br.aula03.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class autorFragment extends Fragment {

    private View view;
    public autorFragment() {
        // Required empty public constructor
    }

    private TextView text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null)
            view = inflater.inflate(R.layout.fragment_my_fist, container, false);
        text = view.findViewById(R.id.text);
        return view;
    }


    public void setText(String msg){
        this.text.setText(msg);
    }

}
