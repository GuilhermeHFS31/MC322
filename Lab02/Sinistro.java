public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public Sinistro(int id, String data, String endereco){
        this.id = id;
        this.data = data;
        this.endereco = endereco;
    }

public int getId(){
    return id;
}

public void setId(int id){
    this.id = id;
    }

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

// Método que gera um ID aleatório entre 0 e 10000;
public void randomID(){
    int random = (int)(Math.random()  * 10000);
    setId(random);
}

public String toString(){
    return "----  INFORMAÇÕES DO SINISTRO  ----" + "\n"
            + "Id: " + this.id + "\n"
            + "Data: "+ this.data + "\n"
            + "Endereço: " + this.endereco + "\n";
}

}
