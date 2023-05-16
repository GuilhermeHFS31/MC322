import java.util.ArrayList;

public abstract class Cliente{
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;
   
    // Constructor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, double valorSeguro){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.valorSeguro = valorSeguro;
    }


public abstract double calculaScore();

public double getSeguro(){
    return valorSeguro;
}

public void setSeguro(double valorSeguro){
    this.valorSeguro = valorSeguro;
}

public String getNome(){
    return nome;
}

public void setNome(String nome){
    this.nome = nome;
}

public ArrayList<Veiculo> getListaVeiculos(){
    return listaVeiculos;
}

public  void setListaVeiculo(ArrayList<Veiculo> listaVeiculos){
    this.listaVeiculos = listaVeiculos;
}

public String getEndereco(){
    return endereco;
}

public void setEndereco(String endereco){    
    this.endereco = endereco;
}

// verifica se o veiculo informado pelo usuario esta presenta na lista de veiculos do cliente
public boolean verificarVeiculo(String placa){
    if (this.listaVeiculos.isEmpty()){
        return false;
    }
    for (int i = 0; i < this.getListaVeiculos().size(); i++){
        if(this.getListaVeiculos().get(i).getPlaca().equals(placa)){
            return true;
        }
    }
    return false;
}

// busca o veiculo infomado pelo usuario
public Veiculo buscarVeiculo(String placa){
    int i;
    for (i = 0; i < this.getListaVeiculos().size() && this.getListaVeiculos().get(i).getPlaca() != placa; i++);
    return this.getListaVeiculos().get(i - 1);      
}


// lista todos os veiculos cadastros de um cliente
public void listarVeiculos(){
    if (!this.listaVeiculos.isEmpty()){
        for (int i = 0; i < this.getListaVeiculos().size(); i++){
            System.out.println(this.getListaVeiculos().get(i).toString());
        }
    } else {
        System.out.println("O cliente não possui veículos cadastrados na seguradora!!!");
    }
}

public void adicionaVeiculo(Veiculo V){
    this.listaVeiculos.add(V);
}

public void removeVeiculo(Veiculo V){
    this.listaVeiculos.remove(V);
}

@Override
public String toString(){
    return  "\n----  INFORMAÇÕES BÁSICAS DO CLIENTE  ----" + "\n"
            + "Nome: " + this.nome + "\n"
            + "Endereço: " + this.endereco + "\n"
            + "Valor do Seguro: " + this.valorSeguro + "\n";
  
}

}