import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private int qtdeFuncionarios;
    
    // constructor
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, double valorSeguro, String CNPJ, 
                    Date dataFundacao, int qtdeFuncionarios){
        super(nome, endereco, listaVeiculos, valorSeguro);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
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
        double score = base.getNum() * (1 + (this.qtdeFuncionarios)/100) * this.getListaVeiculos().size();
        return score;
    }

    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "CNPJ: "+ this.CNPJ + "\n"
                + "Data de fundação: "+ S.format(this.dataFundacao)  + "\n"
                + "Quantidade de funcionários: " + this.qtdeFuncionarios +"\n"
                + "Valor do seguro: " + this.getSeguro() + "\n"
                + "---------------------------------" + "\n";

    }
    
}