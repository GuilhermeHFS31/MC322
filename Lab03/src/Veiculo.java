public class Veiculo{
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    public static int n_veiculos = 0;

    // Constructor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        n_veiculos++;
    }

public int getAnoFabricacao(){
    return anoFabricacao;
}

public void setAnoFabricacao(int anoFabricacao){
    this.anoFabricacao = anoFabricacao;
}

public String getPlaca(){
    return placa;
}

public void setPlaca(String placa){
    this.placa = placa;
}

public String getMarca(){
    return marca;
}

public void setMarca(String marca){
    this.marca = marca;
}

public String getModelo(){
    return modelo;
}

public void setModelo(String modelo){
    this.modelo = modelo;
}

public String toString(){
    return  "----  INFORMAÇÕES DO VEÍCULO  ----" + "\n"
            + "Placa: " + this.placa + "\n"
            + "Marca: "+ this.marca + "\n"
            + "Modelo: "  + this.modelo + "\n"
            + "Ano de Fabricação: "  + this.anoFabricacao + "\n";
}

}