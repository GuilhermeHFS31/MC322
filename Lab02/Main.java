public class Main {
    
    public static void main(String[] args){

        // Instanciado objetos de cada classe criada;
        Cliente Cliente1 = new Cliente("Carlos", "496.987.878-10", "15/08/2003", "Rua do Cliente", 19);
        Veiculo Veiculo1 = new Veiculo("GBK-0387", "CHEVROLET", "Prisma");
        Seguradora Seguro1 = new Seguradora("Kaire Seguros", "14 491418366", "seguros.kaire2006@hotmail.com", "Rua do seguro");
        Sinistro Acidente1 = new Sinistro(0, "01/10/2058", "Rua do acidente");
        
        // Atribuindo um id aleatório para o objeto "Acidente1";
        Acidente1.randomID();


        // Imprimindo as informações sobre o Cliente e validando o CPF dado;
        System.out.println(Cliente1.toString());
        if(Cliente1.validarCPF(Cliente1.getCpf()) == true){  // Verifica-se que o método getCpf funciona corretamente //
            System.out.println("** VALIDAÇÃO REALIZADA COM SUCESSO: CPF VÁLIDO **" + "\n");
        } else {
            System.out.println("** ATENÇÃO ** " + "\n"
                                + " CPF INVÁLIDO " + "\n");
        }

        // Alterando o CPF, pelo método setCpf, para verificar o funcionamento do método validarCPF com um CPF inválido;
        Cliente1.setCpf("999999999999");
        if(Cliente1.validarCPF(Cliente1.getCpf()) == true){ 
            System.out.println(" ** VALIDAÇÃO REALIZADA COM SUCESSO: CPF VÁLIDO ** " + "\n");
        } else{
            System.out.println("** ATENÇÃO ** " + "\n"
                                + " CPF INVÁLIDO " + "\n");
        }
        
        // Imprimindo as informações dos objetos instanciados (das classes Veiculo, Seguradora e Sinistro) por meio do método toString;
        System.out.println(Veiculo1.toString());
        System.out.println(Seguro1.toString());
        System.out.println(Acidente1.toString());
        
    }
}
