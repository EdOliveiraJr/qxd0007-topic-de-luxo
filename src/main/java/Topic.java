import java.util.List;
import java.util.ArrayList;

public class Topic {
    private final int capacidade;
    private int qtdPrioritarios;
    private int qtdNormais;
    private int vagas;
    private final List <Passageiro> prioritarios = new ArrayList<>(); 
    private final List <Passageiro> normais = new ArrayList<>();

    public Topic(int capacidade, int qtdPrioritarios) {
        if (qtdPrioritarios > capacidade)
            throw new IllegalArgumentException("Mais assentos prioritários do que o que é permitido");
        this.capacidade = capacidade;
        this.qtdPrioritarios = qtdPrioritarios;
        this.qtdNormais = capacidade - qtdPrioritarios;
        this.vagas = capacidade;
        criarListaVazia();
    }

    public List<Passageiro> getAssentosNormais(){
        return normais;
    }

    public List<Passageiro> getAssentosPrioritarios(){
        return prioritarios;
    }

    public int getNumeroAssentosPrioritarios() {
        return qtdPrioritarios;
    }
    
    public int getNumeroAssentosNormais() {
        return qtdNormais;
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        if(normais.get(lugar).getNome().equals("=")){
            return null;
        }
        return normais.get(lugar);
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        if(prioritarios.get(lugar).getNome().equals("@")){
            return null;
        }
        return prioritarios.get(lugar);
    }

    public int getVagas() {
        return vagas;
    }

    private void criarListaVazia() {
        for(int i=0; i < qtdPrioritarios; i++){
            prioritarios.add(new Passageiro("@", 0));
        }
        for (int i = qtdPrioritarios + 1;  i <= capacidade; i++) {
            normais.add(new Passageiro("=", 0));
        }
    }

    private void incluirPrioritarios(Passageiro passageiro){
        for (Passageiro e : prioritarios) {
            if(e.getNome().equals("@")){
                prioritarios.set(prioritarios.indexOf(e), passageiro);
                break;
            }
        }
    }

    private void incluirNormais(Passageiro passageiro){
        for (Passageiro e : normais) {
            //int index = 
            if(e.getNome().equals("=")){
                normais.set(normais.indexOf(e), passageiro);
                break;
            }
        }
    }

    public boolean subir(Passageiro passageiro) {
        if(getVagas() == 0){
            return false;
        }else{
            if(passageiro.ePrioritario()){
                if(getNumeroAssentosPrioritarios() > 0){
                    qtdPrioritarios --;
                    vagas--;
                    incluirPrioritarios(passageiro);
                    return true;
                }else{
                    if(getNumeroAssentosNormais() > 0){
                        qtdNormais --;
                        vagas--;
                        incluirNormais(passageiro);
                    return true;
                    }
                }
            }else{
                if(getNumeroAssentosNormais() > 0){
                    qtdNormais --;
                    vagas--;
                    incluirNormais(passageiro);
                    return true;
                }else{
                    if(getNumeroAssentosPrioritarios() > 0){
                        qtdPrioritarios--;
                        vagas--;
                        incluirPrioritarios(passageiro);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean descer(String nome) {
        for (Passageiro passageiro : prioritarios) {    
            if(passageiro.getNome().equals(nome)){
                prioritarios.set(prioritarios.indexOf(passageiro), new Passageiro("@", 0));
                qtdPrioritarios++;
                vagas++;
                return true;
            }
        }
        for (Passageiro passageiro2 : normais) {
            if(passageiro2.getNome().equals(nome)){
                normais.set(normais.indexOf(passageiro2), new Passageiro("=", 0));
                qtdNormais++;
                vagas++;
                return true;
            }
        }
        return false;
    }

    public String toString(){
        StringBuilder lista = new StringBuilder(); 

        for (Passageiro passageiro : prioritarios) {
            if(passageiro.getNome().equals("@")){
                lista.append("@ ");
            }else{
                lista.append("@" + passageiro.getNome() + " ");
            }
        }

        for (Passageiro passageiro : normais) {
            if(passageiro.getNome().equals("=")){
                lista.append("= ");
            }else{
                lista.append("=" + passageiro.getNome() + " ");
            }
        }
        return "[" + lista + "]";
    }

}
