import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


// Na classe main, além dos testes obrigatórios, há funções que permitem a execução do menu principal;
// O submenu foi dividido em uma nova classe auxiliar "ExecSubmenu"
public class AppMain {
    
    public static void exibirMenu(){
        System.out.println("\n----------- MENU INTERATIVO -----------");
        MenuOperacoes menu[] = MenuOperacoes.values();
        for(int i = 0; i < menu.length; i++){
            System.out.printf("(%d) %s\n", menu[i].getMenuID(), menu[i].getDescricao());
        } 
    }


    // busca a seguradora informada pelo usuario na lista de seguradoras;
    public static Seguradora buscaSeguradora(ArrayList<Seguradora> listaSeguradoras, String nome_leitura){
        int i;
        for(i = 0; i < listaSeguradoras.size() && listaSeguradoras.get(i).getNome() != nome_leitura ; i++);
        return listaSeguradoras.get(i -1);
    }


    //ler opções do menu externo
	private static MenuOperacoes lerOpcaoMenuExterno(int opUsuario) {
		Scanner leitura = new Scanner(System.in);
		MenuOperacoes opUsuarioConst;
		while(opUsuario < 0 || opUsuario > MenuOperacoes.values().length){
            System.out.println("\nErro: Escolha uma opção válida! Digite novamente:");
            opUsuario = leitura.nextInt();
        }
		opUsuarioConst = MenuOperacoes.values()[opUsuario - 1];
		return opUsuarioConst;
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
            return false;
        }
    }
   
    // opcao gerar seguro do menu principal
    public static void gerarSeguro(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o nome da seguradora em que deseja gerar um seguro:");
        String nome_seguradora = leitura.nextLine();
        Date dataInicio = new Date();
        Date dataFim = new Date();
        dataFim.setYear(dataInicio.getYear()+1);
        String identificador;
        Seguradora seguradora;
        ArrayList<Sinistro> listaSinistros = new ArrayList<>();
        ArrayList<Condutor> listaCondutores = new ArrayList<>();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            System.out.println("Deseja gerar um seguro para que tipo de cliente cadastrado? (PF ou PJ)");
            String tipo = leitura.nextLine();
            if (tipo.equals("PF")) {
                System.out.println("Digite o CPF do cliente a que deve ser associado o seguro:");
                identificador = leitura.nextLine();
                if (seguradora.verificaCliente(identificador)){
                    Cliente cliente = seguradora.buscarCliente(identificador);
                    Veiculo V = ExecSubmenu.geraVeiculo();
                    if(V!= null){
                        ((ClientePF) cliente).getListaVeiculos().add(V);
                        SeguroPF seguro = new SeguroPF(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores, V, (ClientePF) cliente);
                        if(seguradora.gerarSeguro(seguro))
                            System.out.println("O seguro foi gerado com sucesso!");
                    }
                } else{
                    System.out.println("O cliente informado não está cadastrado na seguradora!");
                }
            } else if (tipo.equals("PJ")) {
                System.out.println("Digite o CNPJ do cliente a que deve ser associado o seguro:");
                identificador = leitura.nextLine();
                System.out.println("Digite o código verificador da frota em que será gerado o seguro:");
                String code = leitura.nextLine();
                if (seguradora.verificaCliente(identificador)){
                    Cliente cliente = seguradora.buscarCliente(identificador);
                    ExecSubmenu.adicionandoVeiculoFrota(((ClientePJ) cliente), code);
                    Frota F = ((ClientePJ) cliente).getListaFrota().get(((ClientePJ) cliente).getListaFrota().size() - 1);
                    SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores, F, (ClientePJ) cliente);
                    if(seguradora.gerarSeguro(seguro)){
                        System.out.println("O seguro foi gerado com sucesso!");
                    } else{
                        System.out.println("Erro ao gerar seguro!");
                    }
                } else{
                    System.out.println("O cliente informado não está cadastrado na seguradora!");
                }
            } else {
                System.out.println("\nERRO: TIPO INVÁLIDO!");
                System.out.println("- O usuário será redirecionado para o menu de cadastros -");
            }
        }
    }


    // Funcao que realiza a operacao de gerar sinistro do menu principal
    public static void menuSinistro(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora, identificador, endereco;
        Seguradora seguradora;
        Date data_sinistro = new Date();
        System.out.println("Digite o nome da seguradora em que o sinistro será gerado:");
        nome_seguradora = leitura.nextLine();
        System.out.println("Digite o CPF / CNPJ do cliente envolvido:");
        identificador = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            if(seguradora.verificaCliente(identificador)){
                Cliente cliente;
                cliente = seguradora.buscarCliente(identificador);
                System.out.println("Digite as seguintes informações sobre o sinistro:");
                System.out.printf("Endereço: ");
                endereco = leitura.nextLine();
                System.out.printf("CPF do Condutor: ");
                String cpf_condutor = leitura.nextLine();
                System.out.printf("Id do seguro: ");
                String aux = leitura.nextLine();
                int id_seguro = Integer.parseInt(aux);
                SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Data do sinistro ('Dia/Mês/Ano'):");
                try {
                data_sinistro = S.parse(leitura.nextLine());
                Seguro seguro = seguradora.buscarSeguro(id_seguro);
                if(seguro.verificarCondutor(cpf_condutor)){
                    Condutor condutor = seguro.buscaCondutor(cpf_condutor);
                    seguro.gerarSinistro(data_sinistro, endereco, condutor);
                      
                } else{
                    System.out.println("Erro ao gerar sinistro! O condutor não está na lista de condutores autorizados!");
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

    // função rensponsabel para a execução do menu principal
    public static void executarMenuPrincipal(MenuOperacoes Menu_op, ArrayList<Seguradora> listaSeguradoras ){
        switch(Menu_op){
            case GERAR_SEGURO:
                gerarSeguro(listaSeguradoras);
                break;
            case GERAR_SINISTRO:
                menuSinistro(listaSeguradoras);
                break;
            case CALCULAR_RECEITA:
                receitaMenu(listaSeguradoras);
                break;
            case CADASTROS:
            case LISTAR:
            case EXCLUIR:
            case GERENCIAR_CONDUTORES:
                ExecSubmenu.executarSubmenu(Menu_op, listaSeguradoras);
                break;
            case SAIR:
                break;
        }
    }
   

    public static void main(String[] args) throws Exception {
        
        
        // Intanciando um clientePF aleatorio "carlos"
        Date data_nascimento1 = new Date("2000/11/15");
        ArrayList<Veiculo> Veiculos_carlos = new ArrayList<>();
        ClientePF carlos = new ClientePF("Carlos Oliveira", "Rua do Carlos", "1499786987", "carlos@gmail.com", Veiculos_carlos, "496.987.878-10", 
                                "Masculino", "Ensino Médio completo", data_nascimento1);
        Veiculo carro1_carlos = new Veiculo("GBK-0387", "CHEVROLET", "Prisma", 2015);
        Veiculo carro2_carlos = new Veiculo("FUR-3131", "FIAT", "Uno", 1998);
        carlos.cadastrarVeiculo(carro1_carlos);
        carlos.cadastrarVeiculo(carro2_carlos);


        // Intanciando um clientePF aleatorio "Marcos"
        Date data_licenca2 = new Date(); 
        Date data_nascimento2 = new Date("1987/08/02");
        ArrayList<Veiculo> Veiculos_marcos = new ArrayList<>();
        ClientePF marcos = new ClientePF("Marcos da Silva", "Rua do Marcos","15990343434", "marcos@gmail.com", Veiculos_marcos, "447.652.018-99", 
                                "Masculino",  "Ensino Superior ompleto", data_nascimento2);
        Veiculo carro1_marcos = new Veiculo("THY-0297", "HONDA", "CIVIC", 2015);
        Veiculo carro2_marcos = new Veiculo("IMP-2299", "VOLKSWAGEN", "GOL", 1998);
        marcos.cadastrarVeiculo(carro1_marcos);
        marcos.cadastrarVeiculo(carro2_marcos);
        

        // Instanciando um clientePJ aleatorio "Loja do André"
        //  OBS : NOVO atributo "qtdeFuncionarios" em relação ao lab3
        Date fundacao_data = new Date("1980/06/04");
        ArrayList<Frota> Frotas_andre = new ArrayList<>();
        ClientePJ loja_andre = new ClientePJ("Loja do André", "Rua do André","14997838383", "lojaandre@gmail.com",
        "42.052.523/0001-21", fundacao_data, Frotas_andre, 15);


        // instanciando veiculos e frotas para o clientePJ "loja do andre"
        Veiculo caminhonete1_andre = new Veiculo("OIE-7777", "TOYOTA", "HYLUX", 2013);
        Veiculo caminhonete2_andre = new Veiculo("UIU-7582", "CHEVROLET", "S10", 2013);
        Veiculo carro1_andre = new Veiculo("MPE-3413", "CHEVROLET", "CRUZE", 2014);
        Veiculo moto_andre = new Veiculo("SIM_4351", "HONDA", "CG160", 2019);
        ArrayList<Veiculo> lista_veiculos_seguranca = new ArrayList<>();
        ArrayList<Veiculo> lista_veiculos_vendas = new ArrayList<>();
        lista_veiculos_seguranca.add(caminhonete1_andre);
        lista_veiculos_seguranca.add(caminhonete2_andre);
        lista_veiculos_vendas.add(carro1_andre);
        lista_veiculos_vendas.add(moto_andre);
        Frota frota_seguranca_andre = new Frota("seguranca_andre", Veiculos_marcos);
        Frota frota_vendas_andre = new Frota("vendas_andre", Veiculos_marcos);
        loja_andre.cadastrarFrota(frota_seguranca_andre);
        loja_andre.cadastrarFrota(frota_vendas_andre);
        loja_andre.atualizarFrota(frota_seguranca_andre.getCode(), caminhonete1_andre, 2);
        loja_andre.atualizarFrota(frota_seguranca_andre.getCode(), caminhonete2_andre, 2);
        loja_andre.atualizarFrota(frota_vendas_andre.getCode(), moto_andre, 2);
        loja_andre.atualizarFrota(frota_vendas_andre.getCode(), carro1_andre, 2);


        // Instanciando uma seguradora aleatoria "seguradora legal"
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Seguro> listaSeguros = new ArrayList<>();
        Seguradora seguradora_legal = new Seguradora("Seguradora Legal", "1499123022222", "SeguradoraLegal@hotmail.com",
                                    "Rua da Seguradora", listaClientes, listaSeguros); 


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
        


        System.out.println("******************************************************" );
        System.out.println(("              TESTE DA GERAÇÃO DE SEGUROS"));
        // gerando e cadastrando seguro para cliente carlos
        Date data_seguro_carlos = new Date();
        Date dataFimSeguroCarlos = new Date();
        dataFimSeguroCarlos.setYear(data_seguro_carlos.getYear()+1);
        ArrayList<Sinistro> SinistrosSeguroCarlos = new ArrayList<Sinistro>();
        ArrayList<Condutor> CondutoresSeguroCarlos = new ArrayList<Condutor>();
        SeguroPF seguro1_carlos = new SeguroPF(data_seguro_carlos, dataFimSeguroCarlos, seguradora_legal, SinistrosSeguroCarlos, CondutoresSeguroCarlos, carro1_carlos, carlos);
        seguradora_legal.getListaSeguros().add(seguro1_carlos);
        
        // gerando e cadastrando seguro para clientePJ LOJA DO ANDRE
        Date data_seguro_andre = new Date();
        Date dataFimLojaAndre = new Date();
        dataFimLojaAndre.setYear(data_seguro_andre.getYear()+1);
        ArrayList<Sinistro> SinistrosSeguroAndre = new ArrayList<Sinistro>();
        ArrayList<Condutor> CondutoresSeguroAndre = new ArrayList<Condutor>();
        SeguroPJ seguro1_andre = new SeguroPJ(data_seguro_andre, dataFimLojaAndre, seguradora_legal, SinistrosSeguroAndre, CondutoresSeguroAndre, frota_seguranca_andre, loja_andre);
        seguradora_legal.getListaSeguros().add(seguro1_andre);
        
        System.out.println(seguro1_carlos.toString());
        System.out.println(seguro1_andre.toString());
       





        System.out.println("******************************************************");
        System.out.println(("          TESTE DA AUTORIZAÇÃO DE CONDUTORES"));
        //AUTORIZANDO CONDUTORES PARAS OS CLIENTE CARLOS
        ArrayList<Sinistro> SinistrosCondutorCarlos = new ArrayList<Sinistro>();
        ArrayList<Sinistro> SinistrosCondutorCarlosFilho = new ArrayList<Sinistro>();
        ArrayList<Sinistro> SinistrosCondutorCarlosEsposa = new ArrayList<Sinistro>();
        Date data_nascimento_filho = new Date("2003/08/02");
        Date data_nascimento_esposa = new Date("1970/10/02");
        Condutor condutor_carlos = new Condutor("496.987.878-10", "Carlos Oliveira", "1499786987", "Rua do carlos", "carlos@gmail.com", data_nascimento1, SinistrosCondutorCarlos);
        Condutor condutor_carlos_filho = new Condutor("597.172.764-80", "Matheus Oliveira", "1499764747", "Rua do filho do carlos", "filho_carlos@gmail.com", data_nascimento_filho, SinistrosCondutorCarlosFilho);
        Condutor condutor_carlos_esposa = new Condutor("537.113.252-03", "Olga Oliveira", "14997343242", "Rua do carlos", "esposa_carlos@gmail.com", data_nascimento_esposa, SinistrosCondutorCarlosEsposa);
        int idSeguroCarlos = 1;
        Seguro seguro_aux1 = seguradora_legal.buscarSeguro(idSeguroCarlos);
        seguro_aux1.autorizarCondutor(condutor_carlos);
        seguro_aux1.autorizarCondutor(condutor_carlos_filho);
        seguro_aux1.autorizarCondutor(condutor_carlos_esposa);
      

        
        //AUTORIZANDO CONDUTORES PARAS OS CLIENTE LOJA DO ANDRE
        ArrayList<Sinistro> SinistrosCondutorAndre = new ArrayList<Sinistro>();
        ArrayList<Sinistro> SinistrosCondutorSeguranca1 = new ArrayList<Sinistro>();
        ArrayList<Sinistro> SinistrosCondutorSeguranca2 = new ArrayList<Sinistro>();
        Date data_nascimento_seg1 = new Date("1990/07/26");
        Date data_nascimento_seg2 = new Date("1989/11/12");
        Date data_nascimento_andre = new Date("1979/01/03");
        Condutor condutor_seguranca1 = new Condutor("124.535.766-23", "Matos Franca", "1499786577", "Rua do segurança1", "seg1@gmail.com", data_nascimento_seg1, SinistrosCondutorSeguranca1);
        Condutor condutor_segurança2 = new Condutor("046.722.378-50", "Jorge Moretto", "1499111147", "Rua do segurança2", "seg2s@gmail.com", data_nascimento_seg2, SinistrosCondutorSeguranca2);
        Condutor condutor_andre = new Condutor("746.208.477-55", "Andre dono", "14997367676", "Rua do Andre", "andre@gmail.com", data_nascimento_andre, SinistrosCondutorAndre);
        int idSeguroAndre = 2;
        Seguro seguro_aux2 = seguradora_legal.buscarSeguro(idSeguroAndre);
        seguro_aux2.autorizarCondutor(condutor_seguranca1);
        seguro_aux2.autorizarCondutor(condutor_segurança2);
        seguro_aux2.autorizarCondutor(condutor_andre);
      
        seguro1_carlos.listarCondutores();
        seguro1_andre.listarCondutores();
       



        System.out.println("******************************************************");
        System.out.println(("              TESTE DE GERAÇÃO DE SINISTROS "));
        // Gerando um sinistro para o seguro(id = 1) do Cliente carlos
        Date data_sinistro_carlos = new Date();
        seguro1_carlos.gerarSinistro(data_sinistro_carlos, "Rua do sinistro carlos", condutor_carlos);


        // Gerando um sinistro para o seguro(id = 2) do ClientePF Loja do andre
        Date data_sinistro_andre = new Date();
        seguro1_andre.gerarSinistro(data_sinistro_andre, "Rua do sinistro andre", condutor_andre);


        seguro1_carlos.listarSinistros();
        seguro1_andre.listarSinistros();
        System.out.println("******************************************************" +"\n");
        

        // EXECUCAO DO MENU
        int id_leitura;
        MenuOperacoes Menu_op;
        Scanner leitura = new Scanner(System.in);
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
        listaSeguradoras.add(seguradora_legal);
        do {
            exibirMenu();
            System.out.println("Digite uma opção:");
            id_leitura = leitura.nextInt();
            Menu_op = lerOpcaoMenuExterno(id_leitura);
            executarMenuPrincipal(Menu_op, listaSeguradoras);
            } while(Menu_op != MenuOperacoes.SAIR );
        
        leitura.close();
    }
}
