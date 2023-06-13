public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE(1,"Cadastrar cliente"),
	CADASTRAR_VEICULO_CLIENTEPF(2,"Cadastrar veiculo para um cliente 'PF'"),
	CADASTRAR_VEICULO_FROTA(3,"Cadastrar veiculo em uma frota de cliente 'PJ'"),
	CADASTRAR_FROTA(4, "Cadastrar frota para um cliente 'PJ'"),
	CADASTRAR_SEGURADORA(5,"Cadastrar seguradora"),
	
	
	LISTAR_CLIENTES(1,"Listar cliente"),
	LISTAR_SINISTROS_SEGURO(2,"Listar sinistros"),
	LISTAR_VEICULOS_CLIENTEPF(3,"Listar veículos de um cliente 'PF'"),
	LISTAR_VEICULOS_FROTA(4, "Listar veículos de uma frota"),
	LISTAR_CONDUTORES_SEGURO(5, "Listar condutores asscoiados a um seguro"),
	LISTAR_SEGUROS_CLIENTE(6, "Listar seguros de um cliente"),
	LISTAR_SEGUROS_SEGURADORA(7, "Listar seguros de uma seguradora"),

	
	EXCLUIR_CLIENTE(1,"Excluir cliente"),
	EXCLUIR_VEICULO_CLIENTEPF(2,"Excluir veiculo"),
	EXCLUIR_VEICULO_FROTA(3,"Excluir veiculo"),
	EXCLUIR_FROTA(4,"Excluir veiculo"),
	EXCLUIR_SINISTRO(5,"Excluir sininstro"),
	
	
	AUTORIZAR_CONDUTOR(1, "Autorizar condutor"),
	REMOVER_CONDUTOR(2,"Remover condutor"),

	

	VOLTAR(0, "Voltar");
	
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
