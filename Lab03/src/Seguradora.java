import java.util.ArrayList;


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
        if (PF.validarCPF(PF.getCPF())){
            listaClientes.add(PF);
            return true;
        } else{
            return false;
        }
    } else {
        ClientePJ PJ = (ClientePJ) cliente;
        if (PJ.validarCNPJ(PJ.getCNPJ())){
            listaClientes.add(PJ);
            return true;
        } else{
            return false;
        }
    }
}


private void removerSinistro(Cliente cliente){
    for(int i = 0; i < listaSinistros.size(); i++){
        if (listaSinistros.get(i).getCliente() == cliente){
            listaSinistros.remove(i);
        }
    }
}


public boolean removerCliente(String identificador){
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF() == identificador){
                listaClientes.remove(i);
                removerSinistro(PF);
                return true;
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ() == identificador){
                listaClientes.remove(i);
                removerSinistro(PJ);
                return true;
            }
        }
    }
    return false;
}


// Printa as informações de cada cliente;
public void listarClientes(){
    if(listaClientes.size() !=0)
        for(int i = 0; i < listaClientes.size(); i++){
            System.out.println(listaClientes.get(i).toString());
            }
}



public boolean gerarSinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
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
            if (PF.getCPF() == identificador){
                System.out.println(listaSinistros.get(i).toString());
                return true;
            }
        } else{
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ() == identificador){
                System.out.println(listaSinistros.get(i).toString());
                return true;
            }
        }
    } 
    return false;
}

public void listarSinistros(){
    if (listaSinistros.size() != 0){
        for(int i = 0; i < listaSinistros.size(); i++){
            Sinistro sinistro = listaSinistros.get(i);
            System.out.println(sinistro.toString());
            }
    }
}

public void listarVeiculos(){
    for(int i =0; i < listaClientes.size();i++){
        for(int j = 0; j < listaClientes.get(i).getListaVeiculos().size(); j++){
            System.out.println(listaClientes.get(i).getListaVeiculos().get(j).toString());
        }
    }
}

public String toString(){
    return  "----  INFORMAÇÕES DA SEGURADORA  ----" + "\n"
            + "Nome: " + this.nome + "\n"
            + "Telefone: "+ this.telefone + "\n"
            + "Email: " + this.email + "\n"
            + "Endereço: " + this.endereco + "\n";
            
}

}