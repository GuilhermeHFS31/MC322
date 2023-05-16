import java.util.ArrayList;
import java.util.Date;


public class Seguradora{
    private String nome; 
    private String telefone; 
    private String email; 
    private String endereco; 
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    // Constructor
    public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros, ArrayList<Cliente> listaClientes){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }

    
public String getNome(){
    return nome;
}


public void setNome(String nome){
    this.nome = nome;
}


public String getTelefone(){
    return telefone;
}


public void setTelefone(String telefone) {
    this.telefone =telefone;
}


public String getEmail(){
    return email;
}


public void setEmail(String email){
    this.email = email;
}


public String getEndereco(){
    return endereco;
}


public void setEndereco(String endereco){    
    this.endereco = endereco;
}


public ArrayList<Sinistro> getListaSinistros(){
    return listaSinistros;
}


public  void setListaSinistros(ArrayList<Sinistro> listaSinistros){
    this.listaSinistros = listaSinistros;
}


public ArrayList<Cliente> getListaClientes(){
    return listaClientes;
}


public  boolean cadastrarCliente(Cliente cliente){
    if (listaClientes.contains(cliente)){
        System.out.println("* O cliente já está cadastrado na Seguradora *");
        return false;
    }
    if (cliente instanceof ClientePF){
        ClientePF PF = (ClientePF) cliente;
        if (Validacao.validaCPF(PF.getCPF()) && Validacao.validaNome(PF.getNome())){
            listaClientes.add(PF);
            return true;
        } else{
            return false;
        }
    } else {
        ClientePJ PJ = (ClientePJ) cliente;
        if (Validacao.validaCNPJ(PJ.getCNPJ()) && Validacao.validaNome(PJ.getNome())){
            listaClientes.add(PJ);
            return true;
        } else{
            return false;
        }
    }
}


// remove o sinistro a partir de um ID especifico
public void removerSinistro(int id){
    for(int i = 0; i < this.listaSinistros.size(); i++){
        if (this.listaSinistros.get(i).getId() == id){
            listaSinistros.remove(this.listaSinistros.get(i));
        }
    }
}

// Método sobrecarregado para excluirt todos os sinistros de um cliente
public void removerSinistro(Cliente cliente){
    for(int i = 0; i < this.listaSinistros.size(); i++){
        if (this.listaSinistros.get(i).getCliente().equals(cliente)){
            this.removerSinistro(this.listaSinistros.get(i).getId());
        }
    }
}



public boolean removerCliente(String identificador){
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                listaClientes.remove(i);
                this.removerSinistro(PF);
                return true;
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
                listaClientes.remove(i);
                this.removerSinistro(PJ);
                return true;
            }
        }
    }
    return false;
}

public boolean CadastrarVeiculo(String identificador, Veiculo veiculo){
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                PF.adicionaVeiculo(veiculo);
                return true;
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
                PJ.adicionaVeiculo(veiculo);
                return true;
            }
        }
    }
    return false;
}


// verifica se o cliente informado pelo usuario esta cadastrado na seguradora
public boolean verificaCliente(String identificador){
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                return true;
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
               return true;  
            }
        }
    }
    System.out.println("O cliente não está cadastrado na seguradora especificada!");
    return false;
}


// busca o cliente, sempre que ele estiver cadastrado, na seguradora
public Cliente buscarCliente(String identificador){
    Cliente C = listaClientes.get(0);
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                C = (Cliente) PF;
                break;
                
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
                C = (Cliente) PJ;
                break;
                 
            }
        }
        }
    return C;

}


// Printa as informações de cada cliente;
public void listarClientes(){
    if(listaClientes.size() >0){
        for(int i = 0; i < listaClientes.size(); i++){
            System.out.println(listaClientes.get(i).toString());
        }
    } else {
        System.out.println("A seguradora não possui clientes cadastrados!!!");
    }
}


public boolean gerarSinistro(Date data,String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
    Sinistro sinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);
    // verifica se o cliente está na lista de clientes da seguradora;
    if(!listaClientes.contains(sinistro.getCliente())){
        return false;
    } 
    // verifica se os cliente possui o veiculo passado como parametro;
    if (!cliente.getListaVeiculos().contains(veiculo)){
        return false;
    }
    listaSinistros.add(sinistro);
    return true;
}


public boolean visualizarSinistro(String identificador){
    for(int i = 0; i < listaSinistros.size(); i++){
        if (listaSinistros.get(i).getCliente() instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                System.out.println(listaSinistros.get(i).toString());
                return true;
            }
        } else{
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
                System.out.println(listaSinistros.get(i).toString());
                return true;
            }
        }
    } 
    return false;
}


public void listarSinistros(){
    if (this.listaSinistros.size() > 0){
        for(int i = 0; i < this.listaSinistros.size(); i++){
            Sinistro sinistro = this.listaSinistros.get(i);
            System.out.println(sinistro.toString());
            }
    } else {
        System.out.println("A seguradora não tem sinistros gerados!!!");
    }
}


public void listarVeiculos(){
    if (this.listaClientes.size() > 0){
        for(int i =0; i < listaClientes.size();i++){
            for(int j = 0; j < listaClientes.get(i).getListaVeiculos().size(); j++){
                System.out.println(listaClientes.get(i).getListaVeiculos().get(j).toString());
            }
        }
    } else {
        System.out.println("A seguradora não possui clientes e veículos associados!!!");
    }
}


public void CalcularPrecoSeguroCliente(){
    for(int i =0; i < listaClientes.size();i++){
        listaClientes.get(i).setSeguro(listaClientes.get(i).calculaScore() * (1 + (this.quantidadeSinistrosCliente(listaClientes.get(i)))));
    }
}


// Calcula quantos sinistros um cliente possui em uma seguradora
public int quantidadeSinistrosCliente(Cliente cliente){
    int qtde = 0;
    for (int i =0; i < this.listaSinistros.size(); i++){
        if(this.listaSinistros.get(i).getCliente().equals(cliente)){
            qtde++;
        }
    }
    return qtde;
}


// Calcula a receita de uma seguradora
public void calcularReceita(){
    double receita = 0;
    for(int i =0; i < listaClientes.size();i++){
        receita += listaClientes.get(i).getSeguro();
    }
    System.out.println(("Valor da receita " + receita));
}



public String toString(){
    return  "\n----  INFORMAÇÕES DA SEGURADORA  ----" + "\n"
            + "Nome: " + this.nome + "\n"
            + "Telefone: "+ this.telefone + "\n"
            + "Email: " + this.email + "\n"
            + "Endereço: " + this.endereco + "\n";
            
}

}