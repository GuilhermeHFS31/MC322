import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    
    // constructor
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String CNPJ, Date dataFundacao){
        super(nome, endereco, listaVeiculos);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }

    public String getCNPJ(){
        return CNPJ;
    }

    public Date getDataFundacao(){
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao){
        this.dataFundacao = dataFundacao;
    }

    public boolean validarCNPJ(String CNPJ){
        String novo_cnpj; 
        int soma1 = 0, soma2 = 0;
        int[] multiplicadores1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] multiplicadores2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        novo_cnpj = CNPJ.replaceAll("\\.+", "");
        novo_cnpj = novo_cnpj.replaceAll("-", "");
        novo_cnpj = novo_cnpj.replaceAll("/", "");
        if (novo_cnpj.length() != 14){ 
            return false;
        }
        boolean aux = true;
        int p = 0;
        while(aux && p < 13){ 
            if (novo_cnpj.charAt(p) != novo_cnpj.charAt(1)){
                aux = false;
            }
            p++;
        }
        if (aux){
            return false;
        }

        for(int i =0; i < 12; i++){
            soma1 += (novo_cnpj.charAt(i) - '0') * multiplicadores1[i];
        }
        for(int j =0; j < 13; j++){
            soma2 += (novo_cnpj.charAt(j) - '0') * multiplicadores2[j];
        }
        int resto1 = soma1 % 11;
        int resto2 = soma2 % 11;
        int digito1, digito2;
        if (resto1 == 1 || resto1 == 0){
            digito1 = 0;
        } else {
            digito1 = 11 - resto1;
        }
        if (resto2 == 1 || resto2 == 0){
            digito2 = 0;
        } else{
            digito2 = 11 - resto2;
        }
         int digito1_real = (novo_cnpj.charAt(12) -'0');
         int digito2_real = (novo_cnpj.charAt(13) -'0');
        if (digito1 == digito1_real && digito2 == digito2_real){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        return  "----  INFORMAÇÕES DO CLIENTE  ----" + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Endereço: " + this.getEndereco() + "\n"
                + "CNPJ: "+ this.CNPJ + "\n"
                + "Data de fundação: "+ S.format(this.dataFundacao)  + "\n";
    }
}