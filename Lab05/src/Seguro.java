import java.util.ArrayList;
import java.util.Date;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;
    private static int quantidade_seguros = 0;
    


    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listaCondutores) {
        this.id = quantidade_seguros + 1;
        quantidade_seguros++;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listaCondutores = listaCondutores;
        // this.valorMensal = calcularValor();
    }

    // método abstrado, implementado nas classes filhas  
    public abstract boolean autorizarCondutor(Condutor condutor);
    
    // método abstrado, implementado nas classes filhas  
    public abstract boolean desautorizarCondutor(String cpf);

    public abstract double calcularValor();

    public abstract void gerarSinistro(Date data,String endereco, Condutor condutor);

    public abstract void removerSinistrosCondutor(String cpf);

    public abstract Sinistro buscarSinistro(int id);


    public int quantidadeSinistrosCondutor(){
        int qtd = 0;
        for(int i = 0; i < this.getListaCondutores().size(); i++){
            for (int j = 0; j < this.getListaCondutores().get(i).getListaSinistros().size();j ++){
                qtd++;
            }
        }
        return qtd;
    }


    public boolean verificarCondutor(String cpf){
        for (int i = 0; i < listaCondutores.size(); i++){
            if (listaCondutores.get(i).getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }

    public void listarCondutores(){
        for(int i = 0; i < listaCondutores.size(); i++){
            System.out.println(listaCondutores.get(i).toString());
        }
    }

    public Condutor buscaCondutor(String cpf){
        int i;
        for(i = 0; !listaCondutores.get(i).getCpf().equals(cpf); i++){
        } return listaCondutores.get(i);
    }


    public void listarSinistros(){
        for(int i =0; i< listaSinistros.size();i++)
            System.out.println(listaSinistros.get(i).toString());
    }


    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
        

}
