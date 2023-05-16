public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE(1,"Cadastrar cliente"),
	CADASTRAR_VEICULO(2,"Cadastrar veiculo"),
	CADASTRAR_SEGURADORA(3,"Cadastrar seguradora"),
	
	LISTAR_CLIENTES(1,"Listar cliente"),
	LISTAR_SINISTROS(2,"Listar sinistros"),
	LISTAR_VEICULOS(3,"Listar veiculo"),
	
	EXCLUIR_CLIENTE(1,"Excluir cliente"),
	EXCLUIR_VEICULO(2,"Excluir veiculo"),
	EXCLUIR_SINISTRO(3,"Excluir sininstro"),
	
	VOLTAR(4, "Voltar");
	
	//atributo
	private final int submenuID;
	private final String descricao;
	
	//Construtor
	SubmenuOperacoes(int submenuID, String descricao){
		this.submenuID = submenuID;
		this.descricao = descricao;
	}

	
	public String getDescricao() {
		return descricao;
	}


	public int getSubmenuID(){
		return submenuID;
	}
}
