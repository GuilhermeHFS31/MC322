import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private ArrayList<Frota> ListaFrota;
    private int qtdeFuncionarios;

    
    // constructor
    public ClientePJ(String nome, String endereco, String telefone, String email, String CNPJ, 
                    Date dataFundacao, ArrayList<Frota> ListaFrota, int qtdeFuncionarios){
        super(nome, endereco, telefone, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.ListaFrota = ListaFrota;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }


    public String getCNPJ(){
        return CNPJ;
    }

    public Date getDataFundacao(){
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao){
        this.dataFundacao = dataFundacao;
    }

    // Calcula o seguro do clientePJ
    public double calculaScore(){
        CalcSeguro base = CalcSeguro.values()[0];
        double score = base.getNum() * (1 + (this.qtdeFuncionarios)/100) * this.getListaFrota().size();
        return score;
    }

    public ArrayList<Frota> getListaFrota() {
        return ListaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        ListaFrota = listaFrota;
    }

    


    public boolean verificaFrota(String code){
        for(int i = 0; i < ListaFrota.size(); i++){
            if (ListaFrota.get(i).getCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    public Frota buscarFrota(String code){
        int i;
        for(i = 0; !ListaFrota.get(i).getCode().equals(code);i++);
        return ListaFrota.get(i);
    }

    public boolean cadastrarFrota(Frota frota){
        if (!this.ListaFrota.contains(frota)){
            ListaFrota.add(frota);
            return true;
        }
        
        return false;
    }


    public boolean atualizarFrota( String code){
        for(int i = 0; i < this.ListaFrota.size(); i++){
            if(ListaFrota.get(i).getCode().equals(code)){
            
                ListaFrota.remove(ListaFrota.get(i));
                System.out.println("A frota foi removida com sucesso!");
                return true;
            }
        }
        System.out.println("A frota especificada não está cadastrada!");
        return false;
    }

    

    public boolean atualizarFrota(String code, Veiculo veiculo, int operacao){
        for(int i = 0; i < this.ListaFrota.size(); i++){
            if(ListaFrota.get(i).getCode().equals(code)){
                
                if (operacao == 2){
                    ListaFrota.get(i).getListaVeiculos().add(veiculo);
                    return true;
                } else if (operacao == 3){
                    ListaFrota.get(i).getListaVeiculos().remove(veiculo);
                    System.out.println("O veículo foi removido com sucesso!");
                    return true;
                }
                
            }
        }
        System.out.println("A frota especificada não está cadastrada!");
        return false;
    }


    public boolean getVeiculosPorFrota(String code){
        for(int i = 0; i < this.ListaFrota.size(); i++){
            if(ListaFrota.get(i).getCode().equals(code)){
                ListaFrota.get(i).imprimeFrota();
                return true;
            }
        }    
        return false;
    }  

    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "CNPJ: "+ this.CNPJ + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "Telefone: " + this.getTelefone() + "\n"
                + "E-mail: " + this.getEmail() + "\n"
                + "Data de fundação: "+ S.format(this.dataFundacao)  + "\n"
                + "Quantidade de funcionários: " + this.qtdeFuncionarios +"\n"
                + "---------------------------------" + "\n";

    }
    
}