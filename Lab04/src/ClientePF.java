import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    // Constructor
    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, double valorSeguro, String CPF, String genero,
                     Date dataLicenca, String educacao, Date dataNascimento, String classeEconomica){
        super(nome, endereco, listaVeiculos, valorSeguro);
        this.CPF = CPF;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
    }

    public String getCPF(){
        return CPF;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public Date getDataLicenca(){
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca){
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao(){
        return educacao;
    }

    public void setEducacao(String educacao){
        this.educacao = educacao;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica(){
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica){
        this.classeEconomica = classeEconomica;
    }

    
    // calcula o seguro do clientePF
    // Observar que o metodo getYear retorna o Ano em questão subtraído 1900
    public double calculaScore(){
        CalcSeguro fator_idade;
        CalcSeguro fator_base = CalcSeguro.values()[0];
        int ano_atual = (new Date()).getYear(); // Representa a data exata em que o programa for executado;
        int ano_nascimento = this.getDataNascimento().getYear();
        if (ano_atual - ano_nascimento >= 18 && ano_atual - ano_nascimento < 30 ){
            fator_idade = CalcSeguro.values()[1];
        } else if (ano_atual - ano_nascimento >= 30 && ano_atual - ano_nascimento < 60 ){
            fator_idade = CalcSeguro.values()[2];
        } else if (ano_atual - ano_nascimento >= 60 && ano_atual - ano_nascimento < 90 ){
            fator_idade = CalcSeguro.values()[3];
        } else{
            System.out.println("IMPOSSIVEL CALCULAR O SCORE");
            return 0;
        }
        double score = fator_base.getNum() * fator_idade.getNum() * this.getListaVeiculos().size();
        return score;
        
    }


    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "Cpf: "+ this.CPF + "\n"
                + "Genero: "+ this.genero + "\n"
                + "Data de Licença: " + S.format(this.dataLicenca) + "\n"
                + "Nível de educação: " + this.educacao + "\n"
                + "Data de nascimento: " + S.format(this.dataNascimento) + "\n"
                + "Classe Econômica: " + this.classeEconomica + "\n"
                + "Valor do seguro: " + this.getSeguro() + "\n"
                + "---------------------------------" + "\n";
    }
    

}
