import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AppMain {
    
    public static void exibirMenu(){
        System.out.println("\n----------- MENU INTERATIVO -----------");
        MenuOperacoes menu[] = MenuOperacoes.values();
        for(int i = 0; i < menu.length; i++){
            System.out.printf("(%d) %s\n", menu[i].getMenuID(), menu[i].getDescricao());
        } 
    }


    public static void exibirSubmenu(MenuOperacoes Menu_op){
        System.out.printf("\n----------- SUBMENU -> %s -----------\n", Menu_op.getDescricao()); 
        SubmenuOperacoes submenu[] = Menu_op.getSubmenu();
        for(int i = 0; i < submenu.length; i++){
            System.out.printf("(%d) %s\n", submenu[i].getSubmenuID(), submenu[i].getDescricao());
        } 
    }

    // busca a seguradora informada pelo usuario na lista de seguradoras;
    public static Seguradora buscaSeguradora(ArrayList<Seguradora> listaSeguradoras, String nome_leitura){
        int i;
        for(i = 0; i < listaSeguradoras.size() && listaSeguradoras.get(i).getNome() != nome_leitura ; i++);
        return listaSeguradoras.get(i -1);
    }


    // Verifica se a seguradora informada pelo usuario está cadastrada no sistema;
    public static boolean verificaSeguradoras(ArrayList<Seguradora> listaSeguradoras, String seguradora_leitura){
        if (listaSeguradoras.isEmpty()){
            System.out.println("\nNão há nenhuma seguradora cadastrada no sistema!");
            return false;
        } else{
            for(int i =0; i < listaSeguradoras.size(); i++){
                if(listaSeguradoras.get(i).getNome().equals(seguradora_leitura)){
                    return true;
                }
            }
            System.out.println("\nA Seguradora informada não está cadastrada no sistema!");
            return false;
        }
    }


    public static void CadastramentoSeguradora(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        ArrayList<Sinistro> listaSinistros = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        System.out.println("Digite as informações da Seguradora que deseja cadastrar:");
        System.out.printf("Nome:");
        String nome = leitura.nextLine();
        System.out.printf("Telefone:");
        String telefone = leitura.nextLine();
        System.out.printf("e-mail:");
        String email = leitura.nextLine();
        System.out.printf("Endereço:");
        String endereco = leitura.nextLine();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco, listaSinistros, listaClientes);
        listaSeguradoras.add(seguradora);
        System.out.printf("\nO cadastro da seguradora %s foi realizado com sucesso!\n", seguradora.getNome());
    }


    public static void CadastramentoVeiculo(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        String identificador;
        Seguradora seguradora;
        System.out.println("Informe o CPF ou CNPJ do cliente a que deseja cadastrar o veículo:");
        // System.out.println("CPF / CNPJ: ");
        identificador= leitura.nextLine();
        System.out.println("Informe o nome da seguradora do cliente especificado:");
        System.out.printf("Nome da Seguradora: ");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            String placa, modelo, marca;
            int ano_fabricacao;
            System.out.println("Digite as seguintes informações do Veiculo que deseja cadastrar:");
            System.out.printf("Placa:");
            placa = leitura.nextLine();
            System.out.printf("Modelo:");
            modelo = leitura.nextLine();
            System.out.printf("Marca:");
            marca = leitura.nextLine();
            System.out.printf("Ano de fabricação:");
            ano_fabricacao= leitura.nextInt();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano_fabricacao);
            if (seguradora.CadastrarVeiculo(identificador, veiculo)){
                System.out.println("\nO veículo foi cadastrado com sucesso !!!");
                seguradora.CalcularPrecoSeguroCliente();
            } else {
                System.out.println("\nERRO AO CADASTRAR VEICULO!");
            }
        }
    }
    

    public static void CadastramentoPF(SubmenuOperacoes submenu_op, Seguradora seguradora){
        Scanner leitura = new Scanner(System.in);
        String nome, endereco, CPF, genero, educacao, classeEconomica;
        ArrayList<Veiculo> Veiculos = new ArrayList<>();
        Date licenca, nascimento;
        licenca = new Date();
        nascimento = new Date();
        System.out.println("Digite as seguintes informações do Cliente que deseja inserir:");
        System.out.printf("Nome:");
        nome = leitura.nextLine();
        System.out.printf("Endereço:");
        endereco = leitura.nextLine();
        System.out.printf("CPF:");
        CPF = leitura.nextLine();
        System.out.printf("Gênero:");
        genero = leitura.nextLine();
        System.out.printf("Nivel Educacional:");
        educacao = leitura.nextLine();
        System.out.printf("Classe Econômica:");
        classeEconomica = leitura.nextLine();
        System.out.println("Data de nascimento ('Dia/Mês/Ano'):");
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        try {
            nascimento = S.parse(leitura.nextLine());
            ClientePF PF = new ClientePF(nome, endereco, Veiculos, 0, CPF, genero, licenca, educacao, nascimento, classeEconomica);
            if (seguradora.cadastrarCliente(PF)){
                System.out.printf("\nO cliente foi cadastrado com sucesso na %s !!!\n", seguradora.getNome());
                seguradora.CalcularPrecoSeguroCliente();
            } else {
                System.out.println("\nErro ao realizar cadastro!");
            }
        } catch (ParseException e) {
            System.out.println("\nERRO: A data inserida é inválida.");
            System.out.println("\nPor favor, escreva no formato 'dd/MM/yyyy'");
        }
        
    }
    
    
    public static void CadastramentoPJ(SubmenuOperacoes submenu_op, Seguradora seguradora){
        Scanner leitura = new Scanner(System.in);
        String nome, endereco, CNPJ;
        int qtdeFuncionarios;
        String aux;
        ArrayList<Veiculo> Veiculos = new ArrayList<>();
        Date fundacao_data =new Date();
        System.out.println("Digite as seguintes informações do Cliente que deseja inserir:");
        System.out.printf("Nome:");
        nome = leitura.nextLine();
        System.out.printf("Endereço:");
        endereco = leitura.nextLine();
        System.out.printf("Quantidade de funcionários:");
        aux= leitura.nextLine();
        qtdeFuncionarios = Integer.parseInt(aux);
        System.out.printf("CNPJ:");
        CNPJ = leitura.nextLine();
        System.out.println("Data de fundação ('Dia/Mês/Ano'):");
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fundacao_data = S.parse(leitura.nextLine());
            ClientePJ PJ = new ClientePJ(nome, endereco, Veiculos, 0, CNPJ, fundacao_data, qtdeFuncionarios);
            if (seguradora.cadastrarCliente(PJ)){
                System.out.printf("O cliente foi cadastrado com sucesso na %s !!!\n", seguradora.getNome());
                seguradora.CalcularPrecoSeguroCliente();
            } else {
                System.out.println("\nErro ao realizar cadastro!");
            }
        } catch (ParseException e) {
            System.out.println("\nERRO: A data inserida é inválida.");
            System.out.println("\nPor favor, escreva no formato 'dd/MM/yyyy'");
        }
        
    }
        

    public static void CadastramentoClientes(ArrayList<Seguradora> listaSeguradoras, SubmenuOperacoes submenu_op){
        Scanner leitura = new Scanner(System.in);
        String tipo;
        String nome_seguradora;
        Seguradora seguradora;
        System.out.println("Digite o nome da Seguradora em que deseja realizar o cadastro do cliente:");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras,nome_seguradora);
            System.out.println("Qual o tipo de Cliente que deseja cadastrar? (PF ou PJ)");
            tipo = leitura.nextLine();
            if (tipo.equals("PF")) {
                CadastramentoPF(submenu_op, seguradora);
            } else if (tipo.equals("PJ")) {
                CadastramentoPJ(submenu_op, seguradora);
            } else {
                System.out.println("\nERRO: TIPO INVÁLIDO!");
                System.out.println("- O usuário será redirecionado para o menu de cadastros -");
            }
        }
    }
    
    // Lê a opção escolhida no submenu
    private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes Menu_op) {
		Scanner leitura = new Scanner(System.in);
        int id_leitura;
		SubmenuOperacoes id_submenu;
		System.out.println("Digite uma opcao: ");
        id_leitura = leitura.nextInt();
	    while(id_leitura < 0 || id_leitura > Menu_op.getSubmenu().length){
            System.out.println("\nErro: Escolha uma opção válida! Digite novamente:");
            id_leitura = leitura.nextInt();
        }
		id_submenu = Menu_op.getSubmenu()[id_leitura - 1];
		return id_submenu;
	}

    // Realiza a listagem definida pelo usuario
    private static void listarSubmenu(ArrayList<Seguradora> listaSeguradoras, SubmenuOperacoes submenu_op){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        Seguradora seguradora;
        Cliente cliente;
        String identificador;
        System.out.println("Informe o nome da Seguradora referente a listagem:");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            if(submenu_op.getSubmenuID() == 1){
                seguradora.listarClientes();
            }else if(submenu_op.getSubmenuID() == 2){
                seguradora.listarSinistros();
            }else if(submenu_op.getSubmenuID() == 3){
                System.out.println("Informe o CPF / CNPJ do cliente cujo(s) veículo(s) deseja listar:");
                identificador = leitura.nextLine();
                if(seguradora.verificaCliente(identificador)){
                    cliente = seguradora.buscarCliente(identificador);
                    cliente.listarVeiculos();
                }
            }
        }
    }

    // Realizar a exclusao definida pelo usuario
    public static void excluirSubmenu(ArrayList<Seguradora> listaSeguradoras, SubmenuOperacoes submenu_op){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        String identificador;
        Seguradora seguradora;
        Cliente cliente;
        if(submenu_op.getSubmenuID() == 1){
            System.out.println("Digite o nome da seguradora do Cliente a ser excluído:");
            nome_seguradora = leitura.nextLine();
            System.out.println("Digite o CPF / CNPJ do cliente:");
            identificador = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                if(seguradora.verificaCliente(identificador)){
                    seguradora.removerCliente(identificador);
                }
            }
        } else if(submenu_op.getSubmenuID() == 2){
            System.out.println("Digite o nome da seguradora do Cliente cujo veiculo deve ser excluído:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o CPF / CNPJ do cliente:");
                identificador = leitura.nextLine();
                System.out.println("Digite a placa do véiculo a ser excluído:");
                String placa = leitura.nextLine();
                if(seguradora.verificaCliente(identificador)){
                    cliente = seguradora.buscarCliente(identificador);
                    Veiculo V = cliente.buscarVeiculo(placa);
                    cliente.removeVeiculo(V);
                }
            }
        } else if(submenu_op.getSubmenuID() == 3){
            System.out.println("Digite o nome da seguradora do sinistro a ser excluído:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o ID do sinistro que deseja excluir:");
                int id = leitura.nextInt();
                for(int i = 0; i < seguradora.getListaSinistros().size(); i++){
                    if (seguradora.getListaSinistros().get(i).getId() == id){
                        seguradora.removerSinistro(id);
                        break;
                    }
                }
            }
        }


    }

    
    public static void executarSubmenu(MenuOperacoes Menu_op, ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        SubmenuOperacoes submenu_op;
        do {
            exibirSubmenu(Menu_op);
            submenu_op = lerOpcaoSubmenu(Menu_op);
            switch(submenu_op){
                case CADASTRAR_CLIENTE:
                    CadastramentoClientes(listaSeguradoras, submenu_op);
                    break;
                case CADASTRAR_VEICULO:
                     CadastramentoVeiculo(listaSeguradoras);
                     break;
                case CADASTRAR_SEGURADORA:
                    CadastramentoSeguradora(listaSeguradoras);
                    break;
                case LISTAR_CLIENTES:
                case LISTAR_SINISTROS:
                case LISTAR_VEICULOS:
                    listarSubmenu(listaSeguradoras, submenu_op);
                    break;
                case EXCLUIR_CLIENTE:
                case EXCLUIR_VEICULO:
                case EXCLUIR_SINISTRO:
                    excluirSubmenu(listaSeguradoras, submenu_op);
                    break;
                case VOLTAR:
                    break;
            }

        } while(submenu_op != SubmenuOperacoes.VOLTAR && submenu_op.getSubmenuID() < 0 || submenu_op.getSubmenuID() > SubmenuOperacoes.VOLTAR.getSubmenuID());
    }
    
    // Funcao que realiza a operacao de gerar sinistro do menu principal
    public static void menuSinistro(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora, identificador, endereco, placa;
        Cliente cliente;
        Seguradora seguradora;
        Date data_sinistro = new Date();
        System.out.println("Digite o nome da seguradora em que o sinistro será gerado:");
        nome_seguradora = leitura.nextLine();
        System.out.println("Digite o CPF / CNPJ do cliente envolvido:");
        identificador = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            if(seguradora.verificaCliente(identificador)){
                cliente = seguradora.buscarCliente(identificador);
                System.out.println("Digite as seguintes informações sobre o sinistro:");
                System.out.printf("Endereço: ");
                endereco = leitura.nextLine();
                System.out.printf("Placa do veículo: ");
                placa = leitura.nextLine();
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Data do sinistro ('Dia/Mês/Ano'):");
                try {
                    data_sinistro = S.parse(leitura.nextLine());
                    if(cliente.verificarVeiculo(placa)){
                        Veiculo V = cliente.buscarVeiculo(placa);
                        seguradora.gerarSinistro(data_sinistro, endereco, seguradora, V, cliente);
                    } else{
                        System.out.println("Erro ao gerar sinistro! O cliente não tem veículo informado em seu cadastro!");
                    }
                } catch (ParseException e) {
                    System.out.println("ERRO: A data inserida é inválida.");
                    System.out.println("Por favor, escreva no formato 'dd/MM/yyyy'");
                }
            }
        }
    }

    // funcao que realiza a operacao calcular receita do menu principal
    public static void receitaMenu(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        Seguradora seguradora;
        System.out.println("Digite o nome da seguradora que deseja calcular a receita");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            seguradora.calcularReceita();
        }

    }

    // funcao que realiza a operacao de transferencia de seguro do menu principal
    public static void transfereciaMenu(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        Seguradora seguradora;
        Cliente cliente1, cliente2;
        String identificador_possuidor;
        String identificador_transferir;
        System.out.println("Digite o nome da seguradora em que deseja realizar uma Transferêncai de seguro:");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            if(seguradora.getListaClientes().size() >= 2){
                System.out.println("Digite o CPF / CNPJ do cliente que terá seu seguro transnferido:");
                identificador_possuidor = leitura.nextLine();
                System.out.println("Digite o CPF / CNPJ do cliente que receberá o seguro transferido:");
                identificador_transferir = leitura.nextLine();
                if(seguradora.verificaCliente(identificador_possuidor) && seguradora.verificaCliente(identificador_transferir) ){
                    cliente1 = seguradora.buscarCliente(identificador_possuidor);
                    cliente2= seguradora.buscarCliente(identificador_transferir);
                    for(int i = 0; i < cliente1.getListaVeiculos().size();i++){
                        cliente2.adicionaVeiculo(cliente1.getListaVeiculos().get(i));
                    }
                    ArrayList<Veiculo> nova_lista = new ArrayList<>();
                    cliente1.setListaVeiculo(nova_lista);
                    seguradora.CalcularPrecoSeguroCliente();
                    System.out.println("O seguro foi transferido com sucesso!");
                } else{
                    System.out.println("ERRO: Verifique se ambos os clientes estão cadastrados na seguradora.");
                }
            } else {
                System.out.println("ERRO: Não há clientes suficientes cadastrados para transferir seguro.");
            }
                     
        }
    }


    public static void main(String[] args) throws Exception {
        // Intanciando um clientePF aleatorio "carlos"
        Date data_licenca1 = new Date(); // a data de licença representa a hora atual, a hora do cadastro do cliente
        Date data_nascimento1 = new Date("2000/11/15");
        ArrayList<Veiculo> Veiculos_carlos = new ArrayList<>();
        ClientePF carlos = new ClientePF("Carlos Oliveira", "Rua do Carlos",  Veiculos_carlos, 0, "496.987.878-10", 
                                "Masculino", data_licenca1, "Ensino Médio completo", data_nascimento1, "Classe Média");
        Veiculo carro1_carlos = new Veiculo("GBK-0387", "CHEVROLET", "Prisma", 2015);
        Veiculo carro2_carlos = new Veiculo("FUR-3131", "FIAT", "Uno", 1998);
        carlos.adicionaVeiculo(carro1_carlos);
        carlos.adicionaVeiculo(carro2_carlos);


        // Intanciando um clientePF aleatorio "Marcos"
        Date data_licenca2 = new Date(); 
        Date data_nascimento2 = new Date("1987/08/02");
        ArrayList<Veiculo> Veiculos_marcos = new ArrayList<>();
        ClientePF marcos = new ClientePF("Marcos da Silva", "Rua do Marcos",  Veiculos_marcos,0, "447.652.018-99", 
                                "Masculino", data_licenca2, "Ensino Superior ompleto", data_nascimento2, "Classe Alta");
        Veiculo carro1_marcos = new Veiculo("THY-0297", "HONDA", "CIVIC", 2015);
        Veiculo carro2_marcos = new Veiculo("IMP-2299", "VOLKSWAGEN", "GOL", 1998);
        marcos.adicionaVeiculo(carro1_marcos);
        marcos.adicionaVeiculo(carro2_marcos);
        

        // Instanciando um clientePJ aleatorio "Loja do André"
        //  OBS : NOVO atributo "qtdeFuncionarios" em relação ao lab3
        Date fundacao_data = new Date("1980/06/04");
        Veiculo caminhao_andre = new Veiculo("OIE-7777", "MERCEDES", "ACCELO", 2009);
        Veiculo moto_andre = new Veiculo("SIM_4351", "HONDA", "CG160", 2019);
        ArrayList<Veiculo> Veiculos_andre = new ArrayList<>();
        ClientePJ loja_andre = new ClientePJ("Loja do André", "Rua do André", Veiculos_andre, 0, "42.052.523/0001-21", fundacao_data, 15);
        loja_andre.adicionaVeiculo(moto_andre);
        loja_andre.adicionaVeiculo(caminhao_andre);


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
        if(seguradora_legal.gerarSinistro(new Date("2009/06/02"), "Rua do sinistro",
            seguradora_legal, carro1_carlos, carlos)){
            System.out.println("---- O SINISTRO FOI GERADO COM SUCESSO ----");
        } else {
            System.out.println("**** ERRO AO GERAR SINISTRO ****" );
        }
        // gerando SINISTRO para o cliente Marcos
        if(seguradora_legal.gerarSinistro(new Date("1998/05/14"), "Rua do sinistro",
            seguradora_legal, carro1_marcos, marcos)){
            System.out.println("---- O SINISTRO FOI GERADO COM SUCESSO ----" );
        } else {
            System.out.println("**** ERRO AO GERAR SINISTRO ****" );
        }
        System.out.print("\n");
        System.out.println("-------------------------------------------------------" +"\n");
        

        System.out.println("*********** TESTE - ATUALIZANDO SEGURO ***********");
        System.out.println(carlos.toString());
        seguradora_legal.CalcularPrecoSeguroCliente();
        System.out.println(carlos.toString());
        System.out.println("-------------------------------------------------------" +"\n");


        System.out.println("*********** TESTE LISTAGEM DE CLIENTES DA SEGURADORA ***********");
        seguradora_legal.listarClientes();
        System.out.println("-------------------------------------------------------" +"\n");
        
        
        System.out.println("*********** TESTE LISTAGEM DE SINISTROS DA SEGURADORA ***********");
        seguradora_legal.listarSinistros();
        System.out.println("-------------------------------------------------------" +"\n");
        
        
        System.out.println("*********** TESTE FUNÇÃO VISUZALIZAR SINISTROS DE UM CLIENTE ***********");
        seguradora_legal.visualizarSinistro(carlos.getCPF());
        System.out.println("-------------------------------------------------------" +"\n");


        // Execuçao do menu principal
        // O menu interativo inicia com a "Seguradira legal" já cadastrada;
        int id_leitura;
        MenuOperacoes Menu_op;
        Scanner leitura = new Scanner(System.in);
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
        listaSeguradoras.add(seguradora_legal);
        do {
            exibirMenu();
            System.out.println("Digite uma opção:");
            id_leitura = leitura.nextInt();
            Menu_op = MenuOperacoes.values()[id_leitura - 1]; // Id_leitura - 1 devido aos indices do array values;
            switch(Menu_op){
                case CADASTROS:
                case LISTAR:
                case EXCLUIR:
                    executarSubmenu(Menu_op, listaSeguradoras);
                    break;
                case GERAR_SINISTRO:
                    menuSinistro(listaSeguradoras);
                    break;
                case TRANSFERIR_SEGURO:
                    transfereciaMenu(listaSeguradoras);
                    break;
                case CALCULAR_RECEITA:
                    receitaMenu(listaSeguradoras);
                    break;
                case SAIR:
                    break;
                }
            } while(Menu_op != MenuOperacoes.SAIR);
        
        leitura.close();
    }
}
