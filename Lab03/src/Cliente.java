import java.util.ArrayList;

public class Cliente{
    private String nome;
    private String endereco;
    public ArrayList<Veiculo> listaVeiculos;
   
    // Constructor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
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

public void adicionaVeiculo(Veiculo V, ArrayList<Veiculo> listaVeiculos){
    listaVeiculos.add(V);
}

public void removeVeiculo(Veiculo V, ArrayList<Veiculo> listaVeiculos){
    listaVeiculos.remove(V);
}

public String toString(){
    return  "----  INFORMAÇÕES BÁSICAS DO CLIENTE  ----" + "\n"
            + "Nome: " + this.nome + "\n"
            + "Endereço: " + this.endereco + "\n";
  
}

}