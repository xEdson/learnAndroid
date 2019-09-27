package e196244_r176519.ft.unicamp.br.aula03.transacoes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import e196244_r176519.ft.unicamp.br.aula03.R;

public class TransacoesAdapter extends RecyclerView.Adapter {

    private ArrayList<Transacao> transacoes;
    private TransacaoItemClickListener transacaoItemClickListener;

    public TransacoesAdapter(ArrayList arrayList) {
        transacoes = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transacao_adapter_layout, parent, false);
        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(transacaoItemClickListener != null){
                    TextView txt = v.findViewById(R.id.name);
                    transacaoItemClickListener.onMyItemClick(txt.getText().toString());
                }
            }
        });
        return new TransacoesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Transacao transacao = transacoes.get(position);
        ((TransacoesViewHolder) holder).onBind(transacao);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (transacaoItemClickListener != null) {
                    transacaoItemClickListener.onMyItemLongClick(position);
                    return true;
                }
                return true;
            }
        });
    }

    public void removeItem(int position){
        transacoes.remove(position);
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    class TransacoesViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView valor;
        private TextView descricao;
        private TextView data;
        public TransacoesViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.valor = itemView.findViewById(R.id.valor);
            this.descricao = itemView.findViewById(R.id.descricao);
            this.data = itemView.findViewById(R.id.data);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(Transacao transacao){
            name.setText("transação: " + transacao.getNome());
            valor.setText("valor da transação: " + transacao.getValor());
            descricao.setText("descrição: " + transacao.getDefinicao());
            data.setText("tipo de transação: " + transacao.getData());
        }
    }

    public interface TransacaoItemClickListener {
        void onMyItemClick(String name);
        void onMyItemLongClick(int position);
    }

    public void setTransacaoItemClickListener(TransacaoItemClickListener transacaoItemClickListener) {
        this.transacaoItemClickListener = transacaoItemClickListener;
    }
}
