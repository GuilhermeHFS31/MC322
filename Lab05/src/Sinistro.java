import java.text.SimpleDateFormat;
import java.util.Date;

public class Sinistro {
    private final int id;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    // Constructor
    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro){
        this.id = randomID();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

public int getId(){
    return id;
}

// public void setId(int id){
//     this.id = id;
//     }

public Seguro getSeguro() {
    return seguro;
}

public void setSeguro(Seguro seguro) {
    this.seguro = seguro;
}


public Date getData(){
    return data;
}

public void setData(Date data){
    this.data = data;
}

public String getEndereco(){
    return endereco;
}

public void setEndereco(String endereco){    
    this.endereco = endereco;
}


public Condutor getCondutor(){
    return condutor;
}

public void setCondutor(Condutor condutor){
    this.condutor = condutor;
}


// Método que gera um ID aleatório entre 0 e 10000;
private int randomID(){
    int random = (int)(Math.random()  * 10000);
    return random;
}

public String toString(){
    SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
    return "\n----  INFORMAÇÕES DO SINISTRO  ----" + "\n"
            + "Nome do condutor: " + this.getCondutor().getNome() + "\n"
            + "CPF do condutor: " + this.getCondutor().getCpf() + "\n"
            + "Id do sinistro: " + this.id + "\n"
            + "Id do seguro: "+ this.getSeguro().getId() + "\n"
            + "Data: "+ S.format(this.data)  + "\n"
            + "Endereço: " + this.endereco + "\n";
}

}
