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
    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String CPF, String genero,
                     Date dataLicenca, String educacao, Date dataNascimento, String classeEconomica){
        super(nome, endereco, listaVeiculos);
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

    
// Método que calcula o primeiro Dígito Verificador;
private int DigitoVerificador1(String cpf){
    int soma = 0; int multiplicador = 10; int digito1;
    for(int i = 0; i < 9; i++){
        soma += ((cpf.charAt(i) - '0') * multiplicador);
        multiplicador--;
    }
    int resto = soma % 11;
    if (resto == 0 || resto == 1){
        digito1 = 0;
    } else {
        digito1 = 11 - resto;
    }
    return digito1;
}


// Método que calcula o segundo Dígito Verificador;
private int DigitoVerificador2(String cpf, int digito1){
    int soma = 0; int multiplicador = 11; 
    int digito2;
    for(int i = 0; i < 9; i++){
        soma += ((cpf.charAt(i) - '0') * multiplicador);
        multiplicador--;
    }
    soma += digito1 * multiplicador;
    int resto = soma % 11;
    if (resto == 0 || resto == 1){
        digito2 = 0;
    } else {
        digito2 = 11 - resto;
    }
    return digito2;
}


public boolean validarCPF(String cpf){
    String novo_cpf;
    int digito1; int digito2; 
    novo_cpf = cpf.replaceAll("\\.+", "");
    novo_cpf = novo_cpf.replaceAll("-", "");
    // verifica se o CPF possui 11 dígitos;
    if (novo_cpf.length() != 11){ 
        return false;
    }
    // verifica se os dígitos do CPF não são todos iguais;
    boolean aux = true;
    int i = 0;
    while(aux && i < 11){ 
        if (novo_cpf.charAt(i) != novo_cpf.charAt(1)){
            aux = false;
        }
        i++;
    }
    if (aux){
        return false;
    }
    // Cálculo e verficaçao dos digitos verificadores 
    digito1 = DigitoVerificador1(novo_cpf);
    digito2 = DigitoVerificador2(novo_cpf, digito1);
    int verify1 = (novo_cpf.charAt(9) - '0');
    int verify2 = (novo_cpf.charAt(10) - '0');
    if (digito1 == verify1 && digito2 == verify2){
        return true;
    } else {
        return false;
    }    
}

    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "Cpf: "+ this.CPF + "\n"
                + "Genero: "+ this.genero + "\n"
                + "Data de Licença: " + S.format(this.dataLicenca) + "\n"
                + "Nível de educação: " + this.educacao + "\n"
                + "Data de nascimento: " + S.format(this.dataNascimento) + "\n"
                + "Classe Econômica: " + this.classeEconomica + "\n";
    }
    

}
