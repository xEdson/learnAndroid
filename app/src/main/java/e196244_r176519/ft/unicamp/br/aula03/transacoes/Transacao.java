package e196244_r176519.ft.unicamp.br.aula03.transacoes;

public class Transacao {

    String nome, definicao, dat, typo;

    float valor;

    int id;

    public Transacao(String nome, String definicao, String dat, float valor, int id) {
        this.nome = nome;
        this.definicao = definicao;
        this.dat = dat;
        this.valor = valor;
        this.id = id;
        this.typo = typo;
    }

    public String getNome() {
        return nome;
    }

    public String getDefinicao() {
        return definicao;
    }

    public String getData() {
        return dat;
    }

    public float getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
