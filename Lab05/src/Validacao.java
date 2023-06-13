public class Validacao {
 
    // Método que calcula o primeiro Dígito Verificador do CPF;
    private static int DigitoVerificador1(String cpf){
        int soma = 0; int multiplicador = 10; int digito1;
        for(int i = 0; i < 9; i++){
            soma += ((cpf.charAt(i) - '0') * multiplicador);
            multiplicador--;
        }
        int resto = soma % 11;
        if (resto == 0 || resto == 1){
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        return digito1;
    }


    // Método que calcula o segundo Dígito Verificador do CPF;
    private static int DigitoVerificador2(String cpf, int digito1){
        int soma = 0; int multiplicador = 11; 
        int digito2;
        for(int i = 0; i < 9; i++){
            soma += ((cpf.charAt(i) - '0') * multiplicador);
            multiplicador--;
        }
        soma += digito1 * multiplicador;
        int resto = soma % 11;
        if (resto == 0 || resto == 1){
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        return digito2;
    }


    public static boolean validaCPF(String cpf){
        String novo_cpf;
        int digito1; int digito2; 
        novo_cpf = cpf.replaceAll("\\.+", "");
        novo_cpf = novo_cpf.replaceAll("-", "");
        // verifica se o CPF possui 11 dígitos;
        if (novo_cpf.length() != 11){ 
            return false;
        }
        // verifica se os dígitos do CPF não são todos iguais;
        boolean aux = true;
        int i = 0;
        while(aux && i < 11){ 
            if (novo_cpf.charAt(i) != novo_cpf.charAt(1)){
                aux = false;
            }
            i++;
        }
        if (aux){
            return false;
        }
        // Cálculo e verficaçao dos digitos verificadores 
        digito1 = DigitoVerificador1(novo_cpf);
        digito2 = DigitoVerificador2(novo_cpf, digito1);
        int verify1 = (novo_cpf.charAt(9) - '0');
        int verify2 = (novo_cpf.charAt(10) - '0');
        if (digito1 == verify1 && digito2 == verify2){
            return true;
        } else {
            return false;
        }    
    }


    public static boolean validaCNPJ(String CNPJ){
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


    // Verifica se o nome do cliente possui apenas letras ou espaço
    public static boolean validaNome(String nome){
        if (nome.matches("[A-Za-záâãéêíóôõúçÁÂÉÍÓÔÚ ]+$")){
            return true;
        } else {
            System.out.println("O nome inserido é inválido!");
            return false;
        }
    }

}
