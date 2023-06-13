public enum MenuOperacoes {
    
	CADASTROS(1, "Cadastrar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.CADASTRAR_CLIENTE,
		SubmenuOperacoes.CADASTRAR_VEICULO_CLIENTEPF,
		SubmenuOperacoes.CADASTRAR_VEICULO_FROTA,
		SubmenuOperacoes.CADASTRAR_FROTA,
		SubmenuOperacoes.CADASTRAR_SEGURADORA}),
		

	LISTAR(2, "Listar", new SubmenuOperacoes[]{
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.LISTAR_CLIENTES,
		SubmenuOperacoes.LISTAR_SINISTROS_SEGURO,
		SubmenuOperacoes.LISTAR_VEICULOS_CLIENTEPF,
		SubmenuOperacoes.LISTAR_VEICULOS_FROTA,
		SubmenuOperacoes.LISTAR_CONDUTORES_SEGURO,
		SubmenuOperacoes.LISTAR_SEGUROS_CLIENTE,
		SubmenuOperacoes.LISTAR_SEGUROS_SEGURADORA}),
		
	
	EXCLUIR(3, "Excluir", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.EXCLUIR_CLIENTE,
		SubmenuOperacoes.EXCLUIR_VEICULO_CLIENTEPF,
		SubmenuOperacoes.EXCLUIR_VEICULO_FROTA,
		SubmenuOperacoes.EXCLUIR_FROTA,
		SubmenuOperacoes.EXCLUIR_SINISTRO,
		SubmenuOperacoes.EXCLUIR_SEGURO}),

	GERENCIAR_CONDUTORES(4, "Gerenciar Condutores", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.AUTORIZAR_CONDUTOR,
		SubmenuOperacoes.REMOVER_CONDUTOR}),
	GERAR_SEGURO(5,"Gerar Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	GERAR_SINISTRO(6,"Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	CALCULAR_RECEITA(7,"Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	SAIR(8, "Sair", new SubmenuOperacoes[] {}); 

	private final int menuID;
	private final String descricao;
	private final SubmenuOperacoes[] submenu;
    
	MenuOperacoes(int menuID, String descricao, SubmenuOperacoes[] submenu){
		this.menuID = menuID;
		this.descricao = descricao;
		this.submenu = submenu;
	}


	
	public int getMenuID(){
		return menuID;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return submenu;
	}
}