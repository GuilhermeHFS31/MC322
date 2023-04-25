import java.util.Scanner;

import java.util.ArrayList;
import java.util.Date;
// import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
        Scanner leitura = new Scanner(System.in);
        int listagem = 1;
     
        System.out.println("-------------------------------------------------------" +"\n");
        System.out.println((" *********** TESTES DOS OBJETOS INSTANCIADOS ***********"));
        
        
        // Intanciando um clientePF aleatorio "carlos"
        Date data_licenca1 = new Date(); // a data de licença representa a hora atual, a hora do cadastro do cliente
        Date data_nascimento1 = new Date("2000/11/15");
        ArrayList<Veiculo> Veiculos_carlos = new ArrayList<>();
        ClientePF carlos = new ClientePF("Carlos Oliveira", "Rua do Carlos",  Veiculos_carlos, "496.987.878-10", 
                                "Masculino", data_licenca1, "Ensino Médio completo", data_nascimento1, "Classe Média");
        Veiculo carro1_carlos = new Veiculo("GBK-0387", "CHEVROLET", "Prisma", 2015);
        Veiculo carro2_carlos = new Veiculo("FUR-3131", "FIAT", "Uno", 1998);
        carlos.adicionaVeiculo(carro1_carlos, Veiculos_carlos);
        carlos.adicionaVeiculo(carro2_carlos, Veiculos_carlos);


        // Intanciando um clientePF aleatorio "Marcos"
        Date data_licenca2 = new Date(); 
        Date data_nascimento2 = new Date("1987/08/02");
        ArrayList<Veiculo> Veiculos_marcos = new ArrayList<>();
        ClientePF marcos = new ClientePF("Marcos da Silva", "Rua do Marcos",  Veiculos_marcos, "496.987.878-10", 
                                "Masculino", data_licenca2, "Ensino Superior ompleto", data_nascimento2, "Classe Alta");
        Veiculo carro1_marcos = new Veiculo("THY-0297", "HONDA", "CIVIC", 2015);
        Veiculo carro2_marcos = new Veiculo("IMP-2299", "VOLKSWAGEN", "GOL", 1998);
        marcos.adicionaVeiculo(carro1_marcos, Veiculos_marcos);
        marcos.adicionaVeiculo(carro2_marcos, Veiculos_marcos);
        

         // Instanciando um clientePJ aleatorio "Loja do André"
         Date fundacao_data = new Date("1980/06/04");
         Veiculo caminhao_andre = new Veiculo("OIE-7777", "MERCEDES", "ACCELO", 2009);
         Veiculo moto_andre = new Veiculo("SIM_4351", "HONDA", "CG160", 2019);
         ArrayList<Veiculo> Veiculos_andre = new ArrayList<>();
         ClientePJ loja_andre = new ClientePJ("Loja do André", "Rua do André", Veiculos_andre, "42.052.523/0001-21", fundacao_data);
         loja_andre.adicionaVeiculo(moto_andre, Veiculos_andre);
         loja_andre.adicionaVeiculo(caminhao_andre, Veiculos_andre);


        // Imprimindo as informações sobre o Cliente Carlos e validando o CPF dado;
        System.out.println(carlos.toString());
        if(carlos.validarCPF(carlos.getCPF()) == true){  // Verifica-se que o método getCpf funciona corretamente //
            System.out.println("** VALIDAÇÃO REALIZADA COM SUCESSO: CPF VÁLIDO **" + "\n");
        } else {
            System.out.println("** ATENÇÃO ** " + "\n"
                                + " CPF INVÁLIDO " + "\n");
        }

        // Imprimindo as informações sobre o Cliente Loja do Andre e validando o CNPJ dado;
        System.out.println(loja_andre.toString());
        if(loja_andre.validarCNPJ(loja_andre.getCNPJ()) == true){  // Verifica-se que o método getCNPJ funciona corretamente //
            System.out.println("** VALIDAÇÃO REALIZADA COM SUCESSO: CNPJ VÁLIDO **" + "\n");
        } else {
            System.out.println("** ATENÇÃO ** " + "\n"
                                + " CNPJ INVÁLIDO " + "\n");
        }
        System.out.println("-------------------------------------------------------" +"\n");


        // Instanciando uma seguradora aleatoria "seguradora legal"
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Sinistro> listaSinistros = new ArrayList<>();
        Seguradora seguradora_legal = new Seguradora("Seguradora Legal", "99123022222", "SeguradoraLegal@hotmail.com",
                                     "Rua da Seguradora", listaSinistros, listaClientes); 


        // CADASTRO do cliente carlos e cliente loja do andre na seguradora_legal;
        System.out.println((" *********** TESTE DO CADASTRAMENTO DOS CLIENTES ***********"));
        if (seguradora_legal.cadastrarCliente(carlos)){
            System.out.println("---- CADASTRO FOI REALIZADO COM SUCESSO ----");
        } else{
            System.out.println("***** ERRO: O CADASTRO NÃO PÔDE SER EXECUTADO! ****");
        }
        if (seguradora_legal.cadastrarCliente(marcos)){
            System.out.println("---- CADASTRO FOI REALIZADO COM SUCESSO ----");
        } else{
            System.out.println("***** ERRO: O CADASTRO NÃO PÔDE SER EXECUTADO! ****");
        }
        if (seguradora_legal.cadastrarCliente(loja_andre)){
            System.out.println("---- CADASTRO FOI REALIZADO COM SUCESSO ----");
        } else{
            System.out.println("***** ERRO: O CADASTRO NÃO PÔDE SER EXECUTADO! ****");
        }
        System.out.print("\n");
        
        System.out.println("-------------------------------------------------------" +"\n");


        // gerando SINISTRO para o cliente carlos
        System.out.println((" *********** TESTE DA GERAÇÃO DOS SINISTROS ***********"));
        if(seguradora_legal.gerarSinistro("27/34/2009", "Rua do sinistro",
            seguradora_legal, carro1_carlos, carlos)){
            System.out.println("---- O SINISTRO FOI GERADO COM SUCESSO ----");
        } else {
            System.out.println("**** ERRO AO GERAR SINISTRO ****" );
        }
        // gerando SINISTRO para o cliente Marcos
        if(seguradora_legal.gerarSinistro("27/34/2009", "Rua do sinistro",
            seguradora_legal, carro1_marcos, marcos)){
            System.out.println("---- O SINISTRO FOI GERADO COM SUCESSO ----" );
        } else {
            System.out.println("**** ERRO AO GERAR SINISTRO ****" );
        }
        System.out.print("\n");
        System.out.println("-------------------------------------------------------" +"\n");
       
        
        // removendo o cliente Carlos da Seguradora
        System.out.println((" *********** TESTE DE REMOÇÃO DE CLIENTE ***********"));
        if (seguradora_legal.removerCliente(carlos.getCPF())){
            System.out.println("* O cliente foi removido com sucesso *" + "\n");
        }
        System.out.println("-------------------------------------------------------" +"\n");

        // Teste do método visualizar sinistro para o cliente marcos;
        System.out.println((" *********** TESTE DA VISUALIZAÇÃO DE UM SINISTRO ***********"));
        if (seguradora_legal.visualizarSinistro(marcos.getCPF())){
            System.out.println("** O SINISTRO EXISTE **" +"\n");
        } else {
            System.out.println("** O SINISTRO NÃO EXISTE **" + "\n");
        }
        System.out.println("-------------------------------------------------------" +"\n");
            
        // Listando clientes da seguradora - O cliente carlos já foi removido
        while(listagem != 0){
            System.out.println(" *********** MENU DE VISUALIZAÇÃO ***********" + "\n"
                                + "* Digite (1) se deseja visualizar os clientes da seguradora *" + "\n"
                                + "* Digite (2) se deseja visualizar os sinistros registrados *" + "\n"
                                + "* Digite (3) se deseja visualizar os veiculos de cada cliente *" + "\n"
                                + "* Digite (4) se deseja visualizar informações básicas da seguradora *" + "\n"
                                + "* Digite (0) se deseja encerrar o menu *");
            listagem =leitura.nextInt();
            if (listagem == 1){
                seguradora_legal.listarClientes();
            } else if (listagem == 2){  
                seguradora_legal.listarSinistros();
            } else if (listagem == 3){  
                seguradora_legal.listarVeiculos();
            } else if (listagem == 4){
                System.out.println(seguradora_legal.toString());
            }
            System.out.println("-------------------------------------------------------" +"\n");
        }
    
        leitura.close();
    }
}
