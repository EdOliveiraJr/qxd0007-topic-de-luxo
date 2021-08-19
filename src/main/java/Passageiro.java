public class Passageiro {
    private String nome;
    private int idade;

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public boolean ePrioritario() {
        if( idade >= 65 ) 
            return true;
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}