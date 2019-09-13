package e196244_r176519.ft.unicamp.br.aula03.alunos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import e196244_r176519.ft.unicamp.br.aula03.R;

public class MyFirstAdapter extends RecyclerView.Adapter{

    private ArrayList<Aluno> alunos;
    private MyOnItemClickListener myOnItemClickListener;

    public  MyFirstAdapter(ArrayList al){
        alunos = al;
    }


    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myOnItemClickListener != null){
                    TextView txt = view.findViewById(R.id.name);
                    myOnItemClickListener.onMyItemClick(txt.getText().toString());
                }
            }
        });
        return new MyFistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Aluno aluno = alunos.get(position);
        ((MyFistViewHolder) holder).onBind(aluno);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (myOnItemClickListener != null) {
                    removeItem(position);
                    notifyDataSetChanged();
                    return true;
                }
                return true;
            }
        });
    }

    public void removeItem(int position){
        alunos.remove(position);
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    class MyFistViewHolder extends ViewHolder {

        private TextView nome;
        private ImageView img;
        private TextView bio;


        public MyFistViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            bio = itemView.findViewById(R.id.bio);

        }

        public void onBind(Aluno aluno){
            nome.setText(aluno.getNome());
            img.setImageResource(aluno.getFoto());
            bio.setText(aluno.getDescricao());
        }
    }

    public interface MyOnItemClickListener {
        void onMyItemClick(String name);
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
}