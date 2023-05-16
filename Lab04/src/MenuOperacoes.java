public enum MenuOperacoes {
    
	CADASTROS(1, "Cadastrar", new SubmenuOperacoes[] {
		SubmenuOperacoes.CADASTRAR_CLIENTE,
		SubmenuOperacoes.CADASTRAR_VEICULO,
		SubmenuOperacoes.CADASTRAR_SEGURADORA,
		SubmenuOperacoes.VOLTAR}),
		

	LISTAR(2, "Listar", new SubmenuOperacoes[]{
		SubmenuOperacoes.LISTAR_CLIENTES,
		SubmenuOperacoes.LISTAR_SINISTROS,
		SubmenuOperacoes.LISTAR_VEICULOS,
		SubmenuOperacoes.VOLTAR}),
		
	
	EXCLUIR(3, "Excluir", new SubmenuOperacoes[] {
		SubmenuOperacoes.EXCLUIR_CLIENTE,
		SubmenuOperacoes.EXCLUIR_SINISTRO,
		SubmenuOperacoes.EXCLUIR_VEICULO,
		SubmenuOperacoes.VOLTAR}),
	
	GERAR_SINISTRO(4,"Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	TRANSFERIR_SEGURO(5,"Transferir Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	CALCULAR_RECEITA(6,"Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	SAIR(7, "Sair", new SubmenuOperacoes[] {}); 

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