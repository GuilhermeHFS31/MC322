import java.util.ArrayList;
import java.util.Date;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;
    
    // Constructor
    public SeguroPJ( Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listaCondutores, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.frota =frota;
        this.cliente = cliente;
        this.setValorMensal(calcularValor());
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }


    // public boolean autorizarCondutor(String cpf, String nome, String telefone, String endereco,
    // String email, Date dataNasc, ArrayList<Sinistro> listaSinistros){
            
    //     return true;
    // }

    public void gerarSinistro(Date data,String endereco, Condutor condutor){
        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
        if(this.getListaCondutores().contains(sinistro.getCondutor())){
            this.getListaSinistros().add(sinistro);
            condutor.adicionarSinistro(sinistro);
        }
    }


    public boolean autorizarCondutor(Condutor condutor){
        if (this.getListaCondutores().contains(condutor)){
            System.out.println("Erro: o condutor já está autorizado!");
            return false;
        } else if(!Validacao.validaCPF(condutor.getCpf())){
            System.out.println("Erro: o cpf dado é inválido!");
            return false;
        }
        this.getListaCondutores().add(condutor);
        return true;
    }
    
    public boolean desautorizarCondutor(String cpf){
        for(int i = 0; i < this.getListaCondutores().size(); i++){
            if(this.getListaCondutores().get(i).getCpf().equals(cpf)){
                this.getListaCondutores().remove(this.getListaCondutores().get(i));
                return true;
            }
        }
        return false;
    }

    public Sinistro buscarSinistro(int id){
        int i;
        for( i =0; this.getListaSinistros().get(i).getId() != id;i++);
        return this.getListaSinistros().get(i);
    }

    public void removerSinistrosCondutor(String cpf){
        int i;
        for( i = 0; !this.getListaCondutores().get(i).getCpf().equals(cpf); i++);
        Condutor condutor = this.getListaCondutores().get(i);
        for(int j = 0; j< this.getListaSinistros().size();j++){
            if(this.getListaSinistros().get(j).getCondutor().equals(condutor)){
                this.getListaSinistros().remove(this.getListaSinistros().get(j));
            }
        }
    }
        
    


    public int qtdeVeiculosTotal(){
        int qtde = 0;
        for(int i = 0; i < this.getCliente().getListaFrota().size(); i++){
            for (int j = 0; j < this.getCliente().getListaFrota().get(i).getListaVeiculos().size(); j++){
                qtde++;
            }
        }
        return qtde;
    }
   

    public double calcularValor(){
        double valor;
        CalcSeguro fator_base = CalcSeguro.values()[0];
        int ano_atual = (new Date()).getYear(); // Representa a data exata em que o programa for executado;
        int ano_fund = this.getCliente().getDataFundacao().getYear();
        int anosPosFundacao = ano_atual - ano_fund;
        int sinistros_cliente = this.getListaSinistros().size();
        int sinistros_condutor = this.quantidadeSinistrosCondutor();

        valor = (fator_base.getNum() * (10 + (this.getCliente().getQtdeFuncionarios())/10)
                * (1 + 1/(this.qtdeVeiculosTotal()) + 2)
                * (1 + 1/(anosPosFundacao + 2))
                * (2 + (sinistros_cliente/10))
                * (5 + (sinistros_condutor/10)));
        return valor;
        // if (ano_atual - ano_nascimento >= 18 && ano_atual - ano_nascimento < 30 ){
        //     fator_idade = CalcSeguro.values()[1];
        // } else if (ano_atual - ano_nascimento >= 30 && ano_atual - ano_nascimento < 60 ){
        //     fator_idade = CalcSeguro.values()[2];
        // } else if (ano_atual - ano_nascimento >= 60 && ano_atual - ano_nascimento < 90 ){
        //     fator_idade = CalcSeguro.values()[3];
        // } else{
        //     System.out.println("IMPOSSIVEL CALCULAR O SCORE");
        //     return 0;
        // }
        // double score = fator_base.getNum() * fator_idade.getNum() * this.getListaVeiculos().size();
        // return score;
        
    }

    @Override
    public String toString(){
        return  "\n----  INFORMAÇÕES BÁSICAS DO CLIENTE  ----" + "\n"
                + "ID do seguro: " + this.getId() + "\n"
                + "Nome do Cliente associado: " + this.getCliente().getNome() + "\n"
                + "Id da frota associada: " + this.getFrota().getCode() + "\n"
                + "Valor Mensal: " + this.getValorMensal() + "\n";

    }

}
