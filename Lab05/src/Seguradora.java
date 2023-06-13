import java.util.ArrayList;



public class Seguradora{
    private String nome; 
    private String telefone; 
    private String email; 
    private String endereco; 
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;


    // Constructor
    public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Cliente> listaClientes, ArrayList<Seguro> listaSeguros){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = listaClientes;
        this.listaSeguros = listaSeguros;
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


public ArrayList<Cliente> getListaClientes(){
    return listaClientes;
}

public ArrayList<Seguro> getListaSeguros() {
    return listaSeguros;
}


public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
    this.listaSeguros = listaSeguros;
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


public boolean removerCliente(String identificador){
    for(int i = 0; i < listaClientes.size(); i++){
        if (listaClientes.get(i) instanceof ClientePF){
            ClientePF PF = (ClientePF) listaClientes.get(i);
            if (PF.getCPF().equals(identificador)){
                listaClientes.remove(i);
                return true;
            }
        } else {
            ClientePJ PJ = (ClientePJ) listaClientes.get(i);
            if (PJ.getCNPJ().equals(identificador)){
                listaClientes.remove(i);
                return true;
            }
        }
    }
    return false;
}


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


public boolean gerarSeguro(Seguro seguro){
    int i;
    for( i = 0; i < listaSeguros.size(); i++){
        if(listaSeguros.get(i).getId() == seguro.getId()){
            return false;
        }
    }
    
    listaSeguros.add(seguro);
    return true;
}

public boolean cancelarSeguro(int id){
    for(int i = 0; i < listaSeguros.size(); i++){
        if(listaSeguros.get(i).getId() == id){
            listaSeguros.remove(listaSeguros.get(i));
            return true;
        }
    }
    return false;
}

// buscas o seguro a partir do ID do seguro - método sobrecarregado
public Seguro buscarSeguro(int id){
    int i;
    for (i = 0; listaSeguros.get(i).getId() != id; i++){
    } return listaSeguros.get(i);
}

// busca o seguro a partir de um cliente - metodo sobrecarregado
public Seguro buscarSeguro(Cliente cliente){
    int i;
    for(i = 0; i < this.getListaSeguros().size(); i++){
        if (this.getListaSeguros().get(i) instanceof SeguroPF && ((SeguroPF)this.getListaSeguros().get(i)).getCliente().equals(cliente)){
            break;
        } else if (this.getListaSeguros().get(i) instanceof SeguroPJ && ((SeguroPJ)this.getListaSeguros().get(i)).getCliente().equals(cliente)){
            break;
        }
    }
    return this.getListaSeguros().get(i);
}

public boolean verificaSeguro(int id){
    int i;
    for (i = 0; i < listaSeguros.size(); i++){
        if(listaSeguros.get(i).getId() == id){
            return true;
        }
    } 
    return false;
}

public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
    ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
    for(int i = 0; i < listaSeguros.size(); i++){
        if (listaSeguros.get(i) instanceof SeguroPF){
            SeguroPF seguropf = (SeguroPF) listaSeguros.get(i);
            if(seguropf.getCliente().equals(cliente)){
                segurosCliente.add(seguropf);
            }else{
                SeguroPJ seguropj = (SeguroPJ) listaSeguros.get(i);
                if(seguropj.getCliente().equals(cliente)){
                    segurosCliente.add(seguropj);
                }
            }
        }
    }
    return segurosCliente;
}


// Calcula a receita de uma seguradora
public void calcularReceita(){
    double receita = 0;
    for(int i =0; i < listaSeguros.size();i++){
        receita += listaSeguros.get(i).getValorMensal();
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