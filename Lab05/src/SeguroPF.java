import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF( Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
    ArrayList<Condutor> listaCondutores, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.setValorMensal(calcularValor());
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

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
        this.setValorMensal(calcularValor());
        return true;
    }
    

    public boolean desautorizarCondutor(String cpf ){
        for(int i = 0; i < this.getListaCondutores().size(); i++){
            if(this.getListaCondutores().get(i).getCpf().equals(cpf)){
                this.getListaCondutores().remove(this.getListaCondutores().get(i));
                this.setValorMensal(calcularValor());
                return true;
            }
        }
        return false;
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
        
    
    public Sinistro buscarSinistro(int id){
        int i;
        for( i =0; this.getListaSinistros().get(i).getId() != id;i++);
        return this.getListaSinistros().get(i);
    }


    public int qtdeVeiculosTotal(){
        int qtde = 0;
        for(int i = 0; i < this.getCliente().getListaVeiculos().size(); i++){
            qtde++;
        }
        return qtde;
    }
   

    public double calcularValor(){
        double valor;
        CalcSeguro fator_idade;
        CalcSeguro fator_base = CalcSeguro.values()[0];
        int ano_atual = (new Date()).getYear(); // Representa a data exata em que o programa for executado;
        int ano_nascimento = this.getCliente().getDataNascimento().getYear();
        int sinistros_cliente = this.getListaSinistros().size();
        int sinistros_condutor = this.quantidadeSinistrosCondutor();
        if (ano_atual - ano_nascimento >= 18 && ano_atual - ano_nascimento < 30 ){
            fator_idade = CalcSeguro.values()[1];
        } else if (ano_atual - ano_nascimento >= 30 && ano_atual - ano_nascimento < 60 ){
            fator_idade = CalcSeguro.values()[2];
        } else if (ano_atual - ano_nascimento >= 60 ){
            fator_idade = CalcSeguro.values()[3];
        } else{
            System.out.println("IMPOSSIVEL CALCULAR O SCORE");
            return 0;
        }
        valor = (fator_base.getNum() * fator_idade.getNum() * ((1 + 1/(this.qtdeVeiculosTotal()) + 2)
                * (2 + (sinistros_cliente/10))
                * (5 + (sinistros_condutor/10))));
        return valor;
    }  


    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "\n----  INFORMAÇÕES BÁSICAS DO SEGURO  ----" + "\n"
                + "ID do seguro: " + this.getId() + "\n"
                + "Nome do Cliente associado: " + this.getCliente().getNome() + "\n"
                + "CPF do Cliente associado: " + this.getCliente().getCPF() + "\n"
                + "Placa do Veiculo associado: " + this.getVeiculo().getPlaca() + "\n"
                + "Data de início do seguro: "+ S.format(this.getDataInicio())  + "\n"
                + "Data do fim do seguro: "+ S.format(this.getDataFim())  + "\n"
                + "Valor Mensal: " + this.getValorMensal() + "\n";

    }

}