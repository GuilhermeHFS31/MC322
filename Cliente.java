
public class Cliente{
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private int idade;

   // Constructor
    public Cliente(String nome, String cpf, String dataNascimento, String endereco, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.idade = idade;
    }


public String getNome(){
    return nome;
}

public void setNome(String nome){
    this.nome = nome;
}

public String getCpf(){
    return cpf;
}

public void setCpf(String cpf){
    this.cpf = cpf;
}

public String getDataNascimento(){
    return dataNascimento;
}

public void setDataNascimento(String dataNascimento){
    this.dataNascimento = dataNascimento;
}

public String getEndereco(){
    return endereco;
}

public void setEndereco(String endereco){    
    this.endereco = endereco;
}

public int getIdade(){
    return idade;
}

public void setIdade(int idade){
    this.idade= idade;
}

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
    if (novo_cpf.length() != 11){
        return false;
    }
    boolean aux = true;
    int i = 0;
    while(aux && i < 10){
        if (novo_cpf.charAt(i) != novo_cpf.charAt(1)){
            aux = false;
        }
        i++;
    }
    if (aux){
        return false;
    }
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
    
public String toString(){
    return "Nome: " + this.nome + "\n"
            + "Cpf: "+ this.cpf + "\n"
            + "Data de nascimento: " + this.dataNascimento + "\n"
            + "Endereço: " + this.endereco + "\n"
            + "Idade: " + this.idade;


}
}