import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    // Constructor
    public ClientePF(String nome, String endereco, String telefone, String email,ArrayList<Veiculo> listaVeiculos, String CPF,
                    String genero, String educacao, Date dataNascimento){
        super(nome, endereco, telefone, email);
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = listaVeiculos;
    }

    public String getCPF(){
        return CPF;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public Date getDataLicenca(){
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca){
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao(){
        return educacao;
    }

    public void setEducacao(String educacao){
        this.educacao = educacao;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }


    public ArrayList<Veiculo> getListaVeiculos(){
        return listaVeiculos;
    }
    
    public  void setListaVeiculo(ArrayList<Veiculo> listaVeiculos){
        this.listaVeiculos = listaVeiculos;
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

        
// // lista todos os veiculos cadastros de um cliente
    public void listarVeiculos(){
        if (!this.listaVeiculos.isEmpty()){
            for (int i = 0; i < this.getListaVeiculos().size(); i++){
                System.out.println(this.getListaVeiculos().get(i).toString());
            }
        } else {
            System.out.println("O cliente não possui veículos cadastrados na seguradora!!!");
        }
    }


    public boolean cadastrarVeiculo(Veiculo veiculo){
        for(int i = 0; i < listaVeiculos.size();i++){
            if (listaVeiculos.get(i).getPlaca().equals(veiculo.getPlaca()));

        }
            if (!this.listaVeiculos.contains(veiculo)){
                listaVeiculos.add(veiculo);
                return true;
            }
        return false;
    }        
    
    public boolean removerVeiculo(Veiculo veiculo){
        if (this.listaVeiculos.contains(veiculo)){
            listaVeiculos.remove(veiculo);
            return true;
        }
        return false;
    }        
    
    // calcula o seguro do clientePF
    // Observar que o metodo getYear retorna o Ano em questão subtraído 1900
    public double calculaScore(){
        CalcSeguro fator_idade;
        CalcSeguro fator_base = CalcSeguro.values()[0];
        int ano_atual = (new Date()).getYear(); // Representa a data exata em que o programa for executado;
        int ano_nascimento = this.getDataNascimento().getYear();
        if (ano_atual - ano_nascimento >= 18 && ano_atual - ano_nascimento < 30 ){
            fator_idade = CalcSeguro.values()[1];
        } else if (ano_atual - ano_nascimento >= 30 && ano_atual - ano_nascimento < 60 ){
            fator_idade = CalcSeguro.values()[2];
        } else if (ano_atual - ano_nascimento >= 60 && ano_atual - ano_nascimento < 90 ){
            fator_idade = CalcSeguro.values()[3];
        } else{
            System.out.println("IMPOSSIVEL CALCULAR O SCORE");
            return 0;
        }
        double score = fator_base.getNum() * fator_idade.getNum() * this.getListaVeiculos().size();
        return score;
        
    }


    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Cpf: "+ this.CPF + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "Telefone: " + this.getTelefone() + "\n"
                + "E-mail: " + this.getEmail() + "\n"
                + "Genero: "+ this.genero + "\n"
                + "Data de nascimento: " + S.format(this.dataNascimento) + "\n"
                + "---------------------------------" + "\n";
    }
    

}
