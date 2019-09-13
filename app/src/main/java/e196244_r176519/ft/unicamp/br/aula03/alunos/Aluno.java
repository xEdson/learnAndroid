package e196244_r176519.ft.unicamp.br.aula03.alunos;

public class Aluno {
    String nome;
    int foto;
    String descricao;

    public Aluno(String nome, int foto, String descricao) {
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
}
    public int getFoto() {
        return foto;
    }
    public String getDescricao() {
        return descricao;
    }

}
