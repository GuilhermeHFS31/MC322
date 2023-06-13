import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExecSubmenu {
    

    public static void exibirSubmenu(MenuOperacoes Menu_op){
        System.out.printf("\n----------- SUBMENU -> %s -----------\n", Menu_op.getDescricao()); 
        SubmenuOperacoes submenu[] = Menu_op.getSubmenu();
        for(int i = 0; i < submenu.length; i++){
            System.out.printf("(%d) %s\n", submenu[i].getSubmenuID(), submenu[i].getDescricao());
        } 
    }


       // Lê a opção escolhida no submenu
    private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes Menu_op) {
		Scanner leitura = new Scanner(System.in);
        int id_leitura;
		SubmenuOperacoes id_submenu;
		System.out.println("Digite uma opcao: ");
        id_leitura = leitura.nextInt();
	    while(id_leitura < 0 || id_leitura > Menu_op.getSubmenu().length - 1){
            System.out.println("\nErro: Escolha uma opção válida! Digite novamente:");
            id_leitura = leitura.nextInt();
        }
        
        id_submenu = Menu_op.getSubmenu()[id_leitura];
     
		return id_submenu;
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
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Seguro> listaSeguros = new ArrayList<>();
        System.out.println("Digite as informações da Seguradora que deseja cadastrar:");
        System.out.printf("Nome:");
        String nome = leitura.nextLine();
        System.out.printf("Telefone:");
        String telefone = leitura.nextLine();
        System.out.printf("e-mail:");
        String email = leitura.nextLine();
        System.out.printf("Endereço:");
        String endereco = leitura.nextLine();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco, listaClientes, listaSeguros);
        listaSeguradoras.add(seguradora);
        System.out.printf("\nO cadastro da seguradora %s foi realizado com sucesso!\n", seguradora.getNome());
    }


    public static void adicionandoVeiculoFrota(ClientePJ PJ, String code){
        Scanner leitura = new Scanner(System.in);
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
        PJ.atualizarFrota(code, veiculo, 2); // 2 é uma flag estabelecida no metodo atualizar frota
        System.out.println("------------------------------------------");
    }


    public static void CadastramentoVeiculoFrota(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        String identificador;
        Seguradora seguradora;
        System.out.println("Informe o CNPJ do cliente a que deseja cadastrar o veículo:");
        // System.out.println("CPF / CNPJ: ");
        identificador= leitura.nextLine();
        System.out.println("Informe o nome da seguradora do cliente especificado:");
        System.out.printf("Nome da Seguradora: ");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            Cliente cliente = seguradora.buscarCliente(identificador);
            if (seguradora.verificaCliente(identificador) && cliente instanceof ClientePJ){
                ClientePJ PJ = (ClientePJ) cliente;
                System.out.println("Informe o código da frota em que deseja cadastrar o veículo:");
                String code = leitura.nextLine();
                if(PJ.verificaFrota(code)){
                    adicionandoVeiculoFrota(PJ, code);
                    Seguro seguro = seguradora.buscarSeguro(cliente);
                    seguro.setValorMensal(seguro.calcularValor());
                }
            } else if (cliente instanceof ClientePJ){
                System.out.println("\nERRO: O cliente informado é do tipo PF!");
                
            } else if(!seguradora.verificaCliente(identificador)){
                System.out.println("Erro: O cliente informado não está cadastrado na seguradora!");
            }
        } else{
            System.out.println("Erro: A seguradora informada não está cadastrada no sistema!");
        }
    } 
    
    
    public static void cadastramentoFrota(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora;
        String identificador;
        Seguradora seguradora;
        ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
        System.out.println("Informe o CNPJ do cliente a que deseja cadastrar uma frota:");
        // System.out.println("CPF / CNPJ: ");
        identificador= leitura.nextLine();
        System.out.println("Informe o nome da seguradora do cliente especificado:");
        System.out.printf("Nome da Seguradora: ");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            Cliente cliente = seguradora.buscarCliente(identificador);
            if (seguradora.verificaCliente(identificador) && cliente instanceof ClientePJ){
                ClientePJ PJ = (ClientePJ) cliente;
                String code;
                System.out.println("Informe um código identificador para a sua frota:");
                code = leitura.nextLine();
                while(PJ.verificaFrota(code)){
                    System.out.println("O código informado já existe!");
                    code = leitura.nextLine(); 
                }
                int n_veiculos;
                System.out.println("Informe o número de veículos que deseja adicionar na frota");
                String aux = leitura.nextLine();
                n_veiculos = Integer.parseInt(aux);
                System.out.println("\n------- Adicionanndo veículo(s) na frota -------");
                Frota frota = new Frota(code, listaVeiculos);
                if(PJ.cadastrarFrota(frota)){
                    for(int i =0; i < n_veiculos; i++){
                        adicionandoVeiculoFrota(PJ, code);
                    }
                    System.out.println("A frota foi cadastrada com sucesso !!!");
                    
                    Date dataInicio = new Date();
                    Date dataFim = new Date();
                    dataFim.setYear(dataInicio.getYear()+1);
                    ArrayList<Sinistro> listaSinistros = new ArrayList<>();
                    ArrayList<Condutor> listaCondutores = new ArrayList<>();
                    SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores, frota, PJ);
                    if(seguradora.gerarSeguro(seguro)){
                        System.out.println("Foi gerado um seguro para a frota cadastrada!");
                    } else{
                        if(PJ.atualizarFrota(code)){
                            System.out.println("Erro ao gerar seguro para a nova frota! A frota será descadastrada!");
                        }
                    }
                } else {
                    System.out.println("Erro: Não foi possível cadastrar a frota");
                }
            } else if (cliente instanceof ClientePJ){
                System.out.println("\nERRO: O cliente informado é do tipo PF!");
                
            } else if(!seguradora.verificaCliente(identificador)){
                System.out.println("Erro: O cliente informado não está cadastrado na seguradora!");
            }
        } else{
            System.out.println("Erro: A seguradora informada não está cadastrada no sistema!");
        }
        
    }      
        
    // gera um veiculo a partir de informacoes do teclado
    public static Veiculo geraVeiculo(){
        Scanner leitura = new Scanner(System.in);
        String placa, modelo, marca;
        int ano_fabricacao;
        System.out.println("Digite as seguintes informações do Veiculo que deseja cadastrar:");
        System.out.printf("Placa:");
        placa = leitura.nextLine();
        System.out.printf("Marca:");
        marca = leitura.nextLine();
        System.out.printf("Modelo:");
        modelo = leitura.nextLine();
        try {
            System.out.printf("Ano de fabricação:");
            ano_fabricacao= leitura.nextInt();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, ano_fabricacao);
            return veiculo;
        } catch (InputMismatchException e) {
            System.out.println("Ano de fabricação informado inválido, lembre-se de informar apenas o ano!");
            return null;
        }    
    }
    
    // efetua p cadastramento de veiculo de um cientepf
    public static void CadastramentoVeiculoPF(ArrayList<Seguradora> listaSeguradoras, String nome_seguradora, String identificador){
        Seguradora seguradora;
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
            Cliente cliente = seguradora.buscarCliente(identificador);
            if (seguradora.verificaCliente(identificador) && cliente instanceof ClientePF){
                Veiculo veiculo = geraVeiculo();
                ClientePF PF = (ClientePF) cliente;
                if(veiculo != null && PF.cadastrarVeiculo(veiculo)){
                    System.out.println("O veículo foi cadastrado com sucesso !!!");
                    Date dataInicio = new Date();
                    Date dataFim = new Date();
                    dataFim.setYear(dataInicio.getYear()+1);
                    ArrayList<Sinistro> listaSinistros = new ArrayList<>();
                    ArrayList<Condutor> listaCondutores = new ArrayList<>();
                    SeguroPF seguro = new SeguroPF(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores, veiculo, PF);
                    if(seguradora.gerarSeguro(seguro)){
                        System.out.println("Foi gerado um seguro para o veículo cadastrado!");
                    } else{
                        System.out.println("Erro ao gerar seguro para o novo veiculo! O veículo será descadastrado!");
                        PF.removerVeiculo(veiculo);
                    }
                }
            } else if (cliente instanceof ClientePJ){
                System.out.println("\nERRO: O cliente informado é do tipo PJ!");
                
            } else if(!seguradora.verificaCliente(identificador)){
                System.out.println("Erro: O cliente informado não está cadastrado na seguradora!");
            }
        } else{
            System.out.println("Erro: A seguradora informada não está cadastrada no sistema!");
        }
        
    }
    
    // efetua o cadastramento de cientepf
    public static void CadastramentoPF( Seguradora seguradora){
        Scanner leitura = new Scanner(System.in);
        String nome, endereco, CPF, genero, educacao, telefone, email;
        ArrayList<Veiculo> Veiculos = new ArrayList<>();
        Date nascimento;
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
        System.out.printf("Telefone:");
        telefone = leitura.nextLine();
        System.out.printf("E-mail:");
        email = leitura.nextLine();
        System.out.println("Data de nascimento ('Dia/Mês/Ano'):");
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        try {
            nascimento = S.parse(leitura.nextLine());
            ClientePF PF = new ClientePF(nome, endereco, telefone, email, Veiculos, CPF, genero, educacao, nascimento);
            if (seguradora.cadastrarCliente(PF)){
                System.out.printf("\nO cliente foi cadastrado com sucesso na %s !!!\n", seguradora.getNome());
                
            } else {
                System.out.println("\nErro ao realizar cadastro!");
            }
        } catch (ParseException e) {
            System.out.println("\nERRO: A data inserida é inválida.");
            System.out.println("\nPor favor, escreva no formato 'dd/MM/yyyy'");
        }
        
    }
    
    // efetua o cadastramento de clientepj
    public static void CadastramentoPJ( Seguradora seguradora){
        Scanner leitura = new Scanner(System.in);
        String nome, endereco, CNPJ, telefone, email;
        int qtdeFuncionarios;
        String aux;
        ArrayList<Frota> listaFrotas = new ArrayList<>();
        Date fundacao_data =new Date();
        System.out.println("Digite as seguintes informações do Cliente que deseja inserir:");
        System.out.printf("CNPJ:");
        CNPJ = leitura.nextLine();
        System.out.printf("Nome:");
        nome = leitura.nextLine();
        System.out.printf("Endereço:");
        endereco = leitura.nextLine();
        System.out.printf("Quantidade de funcionários:");
        aux= leitura.nextLine();
        qtdeFuncionarios = Integer.parseInt(aux);
        System.out.printf("Telefone:");
        telefone = leitura.nextLine();
        System.out.printf("E-mail:");
        email = leitura.nextLine();
        System.out.println("Data de fundação ('Dia/Mês/Ano'):");
        SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fundacao_data = S.parse(leitura.nextLine());
            ClientePJ PJ = new ClientePJ(nome, endereco, telefone, email, CNPJ, fundacao_data, listaFrotas, qtdeFuncionarios);
            if (seguradora.cadastrarCliente(PJ)){
                System.out.printf("O cliente foi cadastrado com sucesso na %s !!!\n", seguradora.getNome());
                
            } else {
                System.out.println("\nErro ao realizar cadastro!");
            }
        } catch (ParseException e) {
            System.out.println("\nERRO: A data inserida é inválida.");
            System.out.println("\nPor favor, escreva no formato 'dd/MM/yyyy'");
        }
        
    }
        
    // funçao resposavel para identificar que tipo de cliente sera cadastrado
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
                CadastramentoPF(seguradora);
            } else if (tipo.equals("PJ")) {
                CadastramentoPJ( seguradora);
            } else {
                System.out.println("\nERRO: TIPO INVÁLIDO!");
                System.out.println("- O usuário será redirecionado para o menu de cadastros -");
            }
        }
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
            
            } else if(submenu_op.getSubmenuID() == 2){
                System.out.println("Digite o ID do seguro que deseja listar os sinistros:");
                String aux = leitura.nextLine();
                int id = Integer.parseInt(aux);
                if(seguradora.verificaSeguro(id)){
                    Seguro seguro = seguradora.buscarSeguro(id);
                    seguro.listarSinistros();
                }
            
            } else if(submenu_op.getSubmenuID() == 3){
                System.out.println("Informe o CPF do cliente cujo(s) veículo(s) deseja listar:");
                identificador = leitura.nextLine();
                if(seguradora.verificaCliente(identificador) && seguradora.buscarCliente(identificador) instanceof ClientePF){
                    cliente = seguradora.buscarCliente(identificador);
                    ClientePF PF = (ClientePF) cliente;
                    PF.listarVeiculos();
                } else if(!seguradora.verificaCliente(identificador)){
                    System.out.println("Erro: O cliente informado não está cadastrado na seguradora!");
                }else if(seguradora.buscarCliente(identificador) instanceof ClientePJ){
                    System.out.println("Erro: O cliente informado é do tipo PJ");
                }
            
            } else if(submenu_op.getSubmenuID() == 4){
                System.out.println("Digite o CNPJ que possui a frota que deseja listar:");
                identificador = leitura.nextLine();
                if (seguradora.verificaCliente(identificador) && seguradora.buscarCliente(identificador) instanceof ClientePJ){
                    System.out.println("Digite o codigo identificador da frota que deseja listar:");
                    String code = leitura.nextLine();
                    cliente = seguradora.buscarCliente(identificador);
                    if(((ClientePJ) cliente).verificaFrota(code)){
                        Frota frota = ((ClientePJ) cliente).buscarFrota(code);
                        frota.imprimeFrota();
                    }
                } else if(!seguradora.verificaCliente(identificador)){
                    System.out.println("Erro: O cliente informado não está cadastrado na seguradora!");
                }else if(seguradora.buscarCliente(identificador) instanceof ClientePF){
                    System.out.println("Erro: O cliente informado é do tipo PF");
                }
            
            } else if(submenu_op.getSubmenuID() == 5){
                System.out.println("Digite o ID do seguro que deseja listar os condutores:");
                String aux = leitura.nextLine();
                int id = Integer.parseInt(aux);
                if(seguradora.verificaSeguro(id)){

                    Seguro seguro = seguradora.buscarSeguro(id);
                    seguro.listarCondutores();
                } else{
                    System.out.println("Não foi possível encontrar um seguro com o id informado");
                }
            
            } else if(submenu_op.getSubmenuID() == 6){
                System.out.println("Informe o CPF/CNPJ do cliente que deseja listar os seguros");
                System.out.println("CPF/CNPJ: ");
                String cpf = leitura.nextLine();
                if(seguradora.verificaCliente(cpf)){
                    ArrayList<Seguro> SegurosCliente = seguradora.getSegurosPorCliente(seguradora.buscarCliente(cpf));
                    for(int i =0; i < SegurosCliente.size(); i++)
                        System.out.println(SegurosCliente.get(i).toString());
                    
                } else {
                    System.out.println("Erro: Não foi possível encontrar o CPF/CNPJ!");
                }
            
            } else if(submenu_op.getSubmenuID() == 7){
                for(int i = 0; i < seguradora.getListaSeguros().size(); i++){
                    System.out.println(seguradora.getListaSeguros().get(i).toString());
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
                System.out.println("Digite o CPF do cliente:");
                identificador = leitura.nextLine();
                System.out.println("Digite a placa do véiculo a ser excluído:");
                String placa = leitura.nextLine();
                if(seguradora.verificaCliente(identificador) && seguradora.buscarCliente(identificador) instanceof ClientePF){
                    cliente = seguradora.buscarCliente(identificador);
                    ClientePF PF = (ClientePF) cliente;
                    Veiculo V = PF.buscarVeiculo(placa);
                    PF.removerVeiculo(V);
                    seguradora.getListaSeguros().remove(seguradora.buscarSeguro(cliente));
                }
            }
        } else if(submenu_op.getSubmenuID() == 3){
            System.out.println("Digite o nome da seguradora do Cliente em que se encontra o veículo a ser excluído:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o CNPJ do cliente associado ao veículo a ser exclúido:");
                identificador = leitura.nextLine();
                System.out.println("Digite o código identificador da frota em que se encontra o veículo");
                String code = leitura.nextLine();
                if(seguradora.verificaCliente(identificador) && seguradora.buscarCliente(identificador) instanceof ClientePJ){
                    cliente = seguradora.buscarCliente(identificador);
                    ClientePJ PJ = (ClientePJ) cliente;
                    if(PJ.verificaFrota(code)){
                        Frota frota = PJ.buscarFrota(code);
                        System.out.println("Digite a placa do véiculo a ser excluído:");
                        String placa = leitura.nextLine();
                        if(frota.verificaVeiculo(placa)){
                            Veiculo V = frota.buscaVeiculo(placa);
                            PJ.atualizarFrota(code, V, 3);
                            Seguro seguro = seguradora.buscarSeguro(cliente);
                            seguro.setValorMensal(seguro.calcularValor());
                        }
                    }
                }
            }
        } else if(submenu_op.getSubmenuID() == 4){
            System.out.println("Digite o nome da seguradora relacionada à frota a ser excluída:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o CNPJ do cliente que possui a frota a ser exclúida:");
                identificador = leitura.nextLine();
                System.out.println("Digite o código identificador da frota:");
                String code = leitura.nextLine();
                if(seguradora.verificaCliente(identificador) && seguradora.buscarCliente(identificador) instanceof ClientePJ){
                    cliente = seguradora.buscarCliente(identificador);
                    ClientePJ PJ = (ClientePJ) cliente;
                    if(PJ.verificaFrota(code)){
                        PJ.atualizarFrota(code);
                        seguradora.getListaSeguros().remove(seguradora.buscarSeguro(cliente));
                    }
                }
                
            }
        } else if(submenu_op.getSubmenuID() == 5){
            System.out.println("Digite o nome da seguradora do sinistro a ser excluído:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o ID do seguro que possui tal sinistro:");
                String aux = leitura.nextLine();
                int id_seguro= Integer.parseInt(aux);
                Seguro seguro = seguradora.buscarSeguro(id_seguro);
                System.out.println("Agora, digite o ID do sinistro que deseja excluir");
                aux = leitura.nextLine();
                int id_sinistro = Integer.parseInt(aux);
                Sinistro sinistro = seguro.buscarSinistro(id_sinistro);
                seguro.getListaSinistros().remove(sinistro);
            }
        } else if(submenu_op.getSubmenuID() == 5){
            System.out.println("Digite o nome da seguradora do sinistro a ser excluído:");
            nome_seguradora = leitura.nextLine();
            if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
                seguradora = buscaSeguradora(listaSeguradoras, nome_seguradora);
                System.out.println("Digite o ID do seguro que deseja excluir:");
                String aux = leitura.nextLine();
                int id_seguro= Integer.parseInt(aux);
                Seguro seguro = seguradora.buscarSeguro(id_seguro);
                seguradora.getListaSeguros().remove(seguro);
            }
        }
    }


    public static void opcaoAutorizarCondutor(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome, endereco, CPF, telefone, email;
        int id;
        String aux_id;
        ArrayList<Sinistro> listaSinistros = new ArrayList<>();
        Date  nascimento;
        nascimento = new Date();
        String nome_seguradora;
        System.out.println("Informe o nome da seguradora do cliente especificado:");
        System.out.printf("Nome da Seguradora: ");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            Seguradora seguradora = buscaSeguradora(listaSeguradoras,nome_seguradora);
            System.out.println("Digite as seguintes informações do condutor que deseja autorizar:");
            System.out.printf("Nome:");
            nome = leitura.nextLine();
            System.out.printf("Endereço:");
            endereco = leitura.nextLine();
            System.out.printf("CPF:");
            CPF = leitura.nextLine();
            System.out.printf("Telefone:");
            telefone = leitura.nextLine();
            System.out.printf("E-mail:");
            email = leitura.nextLine();
            System.out.println("Data de nascimento ('Dia/Mês/Ano'):");
            SimpleDateFormat S = new SimpleDateFormat("dd/MM/yyyy");
            try {
                nascimento = S.parse(leitura.nextLine());
                Condutor condutor = new Condutor(CPF, nome, telefone, endereco, email, nascimento, listaSinistros);
                System.out.println("Digite o Id do seguro em que deseja autorizar o condutor:");
                aux_id= leitura.nextLine();
                id = Integer.parseInt(aux_id);
                // id =leitura.nextInt();
                if(seguradora.verificaSeguro(id)){
                    Seguro seguro = seguradora.buscarSeguro(id);
                    seguro.autorizarCondutor(condutor);
                } else {
                    System.out.println("\nErro ao autorizar condutor! O seguro informado não pôde ser encontrado!");
                }
            } catch (ParseException e) {
                System.out.println("\nERRO: A data inserida é inválida.");
                System.out.println("\nPor favor, escreva no formato 'dd/MM/yyyy'");
            }
        }
    }


    public static void opcaoRemoverCondutor(ArrayList<Seguradora> listaSeguradoras){
        Scanner leitura = new Scanner(System.in);
        String nome_seguradora, cpf;
        int id;
        String aux_id;
        System.out.println("Digite o nome da seguradora associada ao condutor a ser removido:");
        nome_seguradora = leitura.nextLine();
        if(verificaSeguradoras(listaSeguradoras, nome_seguradora)){
            Seguradora seguradora = buscaSeguradora(listaSeguradoras,nome_seguradora);
            System.out.println("Digite o Id do seguro associado ao condutor:");
            aux_id= leitura.nextLine();
            id = Integer.parseInt(aux_id);
            if(seguradora.verificaSeguro(id)){
                Seguro seguro = seguradora.buscarSeguro(id);
                System.out.println("Digite o CPF do condutor a ser removido");
                cpf = leitura.nextLine();
                if (seguro.verificarCondutor(cpf))
                    seguro.removerSinistrosCondutor(cpf);
                if(seguro.desautorizarCondutor(cpf)){
                    System.out.println("O condutor foi desvinculado com sucesso!");
                } else{
                    System.out.println("Erro: o condutor não está associado ao seguro especificado!");
                }
            } else {
                System.out.println("Erro: o seguro não foi encontrado!");
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
                case CADASTRAR_VEICULO_CLIENTEPF:
                    System.out.println("Digite o nome da seguradora em que deseja gerar um seguro:");
                    String nome_seguradora = leitura.nextLine();
                    System.out.println("Digite o CPF do cliente a que deve ser associado o seguro:");
                    String identificador = leitura.nextLine();
                    CadastramentoVeiculoPF(listaSeguradoras, nome_seguradora, identificador);
                    break;
                case CADASTRAR_VEICULO_FROTA:
                    CadastramentoVeiculoFrota(listaSeguradoras);
                    break;
                case CADASTRAR_FROTA:
                    cadastramentoFrota(listaSeguradoras);
                    break;
                case CADASTRAR_SEGURADORA:
                    CadastramentoSeguradora(listaSeguradoras);
                    break;
                case LISTAR_CLIENTES:
                case LISTAR_SINISTROS_SEGURO:
                case LISTAR_VEICULOS_CLIENTEPF:
                case LISTAR_VEICULOS_FROTA:
                case LISTAR_CONDUTORES_SEGURO:
                case LISTAR_SEGUROS_CLIENTE:
                case LISTAR_SEGUROS_SEGURADORA:
                    listarSubmenu(listaSeguradoras, submenu_op);
                    break;
                case EXCLUIR_CLIENTE:
                case EXCLUIR_VEICULO_CLIENTEPF:
                case EXCLUIR_VEICULO_FROTA:
                case EXCLUIR_FROTA:
                case EXCLUIR_SINISTRO:
                case EXCLUIR_SEGURO:
                    excluirSubmenu(listaSeguradoras, submenu_op);
                    break;
                case AUTORIZAR_CONDUTOR:
                    opcaoAutorizarCondutor(listaSeguradoras);
                    break;
                case REMOVER_CONDUTOR:
                    opcaoRemoverCondutor(listaSeguradoras);
                    break;
                
                case VOLTAR:
                    break;
            }

        } while(submenu_op != SubmenuOperacoes.VOLTAR && submenu_op.getSubmenuID() < 0 || submenu_op.getSubmenuID() > SubmenuOperacoes.VOLTAR.getSubmenuID());
    }
}
