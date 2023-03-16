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

private int validarCPF(String cpf){
    String novo_cpf;
    int validar;
    novo_cpf = cpf.replaceAll(".", "");
    
}

}