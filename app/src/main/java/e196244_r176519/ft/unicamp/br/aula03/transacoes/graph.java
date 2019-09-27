package e196244_r176519.ft.unicamp.br.aula03.transacoes;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import e196244_r176519.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class graph extends Fragment {

    View view;

    public graph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null)
            view = inflater.inflate(R.layout.fragment_graph, container, false);

        GraphView graph = view.findViewById(R.id.graph);
        graph.setVisibility(View.VISIBLE);

        DataPoint[] dt = new DataPoint[Transacoes.listTransaction.size()];

        graph.removeAllSeries();


        float total = 0;
        for(int x = 0; x < Transacoes.listTransaction.size(); x++){
            Transacao tra = Transacoes.getTransacao(x);
            total += tra.getValor();
            dt[x] = new DataPoint(x, total);
        }


        LineGraphSeries < DataPoint > series = new LineGraphSeries < > (dt);

        graph.addSeries(series);


        graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        graph.getViewport().setScalable(true);  // activate horizontal zooming and scrolling
        graph.getViewport().setScrollable(true);  // activate horizontal scrolling
        return view;
    }

}