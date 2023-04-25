public class Sinistro {
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    public static int n_sinistros = 0;

    // Constructor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        this.id = randomID();
        this.data = data;
        this.seguradora = seguradora;
        this.endereco = endereco;
        this.veiculo = veiculo;
        this.cliente = cliente;
        n_sinistros++;
    }

public int getId(){
    return id;
}

// public void setId(int id){
//     this.id = id;
//     }

public String getData(){
    return data;
}

public void setData(String data){
    this.data = data;
}

public String getEndereco(){
    return endereco;
}

public void setEndereco(String endereco){    
    this.endereco = endereco;
}

public Veiculo getVeiculo(){
    return veiculo;
}

public void setVeiculo(Veiculo veiculo){
    this.veiculo = veiculo;
}

public Cliente getCliente(){
    return cliente;
}

public void setCliente(Cliente cliente){
    this.cliente = cliente;
}

public Seguradora getSeguradora(){
    return seguradora;
}

public void setSeguradora(Seguradora seguradora){
    this.seguradora = seguradora;
}

// Método que gera um ID aleatório entre 0 e 10000;
private int randomID(){
    int random = (int)(Math.random()  * 10000);
    return random;
}

public String toString(){
    return "----  INFORMAÇÕES DO SINISTRO  ----" + "\n"
            + "Nome do cliente: " + this.getCliente().getNome() + "\n"
            + "Id: " + this.id + "\n"
            + "Data: "+ this.data + "\n"
            + "Modelo do Veículo: "+ this.veiculo.getModelo() + "\n"
            + "Placa do Veículo: "+ this.veiculo.getPlaca() + "\n"
            + "Seguradora: "+ this.getSeguradora().getNome() + "\n"
            + "Endereço: " + this.endereco + "\n";
}

}
