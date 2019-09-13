package e196244_r176519.ft.unicamp.br.aula03;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import e196244_r176519.ft.unicamp.br.aula03.MainActivity;
import e196244_r176519.ft.unicamp.br.aula03.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class mailFragment extends Fragment {


    private View view;
    private EditText Para;
    private EditText Destinatario;
    private EditText Mensagemes;
    private Button Send;

    public mailFragment() {
        // Required empty public constructor
        view = null;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null)
            view =  inflater.inflate(R.layout.fragment_mail, container, false);


        Para = view.findViewById(R.id.edtpara);
        Destinatario = view.findViewById(R.id.edtlogin);
        Mensagemes = view.findViewById(R.id.edtmessage);
        Send = view.findViewById(R.id.btnenviar);
        Send.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        String msg = mailFragment.this.Mensagemes.getText().toString();
                        ((MainActivity)getActivity()).doSomething(msg);
                    }
                }
                );

        return view;
    }


}
