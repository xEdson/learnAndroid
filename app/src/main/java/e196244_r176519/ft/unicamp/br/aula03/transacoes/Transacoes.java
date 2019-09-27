package e196244_r176519.ft.unicamp.br.aula03.transacoes;

import java.util.ArrayList;
import java.util.List;

public class Transacoes {

    public static ArrayList<Transacao> listTransaction = new ArrayList<>();

    public static Transacao getTransacao(int position){
        if(listTransaction.size() > position)
            return listTransaction.get(position);
        else
            return new Transacao("Sem transacao", "Sem transacao", "Sem transacao", 0, 1);
    }
}
