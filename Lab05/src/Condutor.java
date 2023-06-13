import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Condutor {
    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private ArrayList<Sinistro> listaSinistros;


    public Condutor(String cpf, String nome, String telefone, String endereco, String email, Date dataNasc, ArrayList<Sinistro> listaSinistros){
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        this.listaSinistros = listaSinistros;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public void adicionarSinistro(Sinistro sinistro){
        this.listaSinistros.add(sinistro);
    }


    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "Cpf: "+ this.cpf + "\n"
                + "E-mail: "+ this.email + "\n"
                + "Genero: "+ this.telefone + "\n"
                + "Data de nascimento: " + S.format(this.dataNasc) + "\n"
                + "---------------------------------" + "\n";
    }
    
}

