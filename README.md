## ProvaPratica

#### Introdução

Projeto com exemplos de utilização de **rest** utilizando spring boot



#### Exemplos
Este projeto representa um exemplo simples
A classe de rest controller invoca os métodos da classe de serviço com o objetivo de ser **simples, bonito e possuir boa manutenabilidade**.
For example:

rest controller:

```java
	/**
	 * Retorna o produto pelo identificado
	 * 
	 * @param idProduto
	 *            id do produto (obrigatorio)
	 * @return ResponseEntity contendo o httpstatus 200 ou 404 se encontrado. Para 200 retorna os dados da entidade para 404 uma string de aviso
	 */
	@RequestMapping(value = "/{idProduto}", method = RequestMethod.GET)
	public ResponseEntity<?> restGet(@PathVariable(name = "idProduto", required = true) Long idProduto) {
		LOGGER.debug(">> restGet {}", idProduto);
		Produto produto = produtoService.findById(idProduto);
		LOGGER.debug("<< restGet {}", produto);
		return produto == null ? new ResponseEntity<>(PRODUTO_NOT_FOUND, HttpStatus.NOT_FOUND) : new ResponseEntity<>(produto, HttpStatus.OK);
	}
```

camada de serviço :

```java
	/* (non-Javadoc)
	 * @see br.com.montreal.provapratica.service.ReadService#findById(java.lang.Long)
	 */
	@Override
	public Produto findById(Long id) {
		LOGGER.debug(" >> findById [id={}] ", id);
		Produto produto = null;
		try {
			produto =  produtoRepository.findOne(id);
		} catch(Exception e){
			LOGGER.error("Falha ao buscar o produto ", e);
		} finally {
			LOGGER.debug(" << findById");
		}
		
		return produto;
	}
```

A organização do projeto está dividida.
A entidade Produto possui:
* A RestController : a classe ProdutoRestController usada para definir e criar a api rest.
* A RestService : Uma interface usada para definir o contrato com a camada de serviço possuindo as consultas por id e todos
* A ProdutoService : Classe de negocio utilizada para realizar o processamento, intermediando a chamada a base de dados e implementando a interface RestService
* A ProdutoRepository : A interface  que  extende JpaRepository, para possuir as principais ações de crud e realizadar as pesquisa na base de dados
* O Produto : A entidade que representa a tabela no banco de dados

#### Exemplos de utilizacao

O propósito do projeto é ser um rest de leitura que será consumido por qualquer cliente. Exemplos das chamadas:


**http://localhost:8080/api/produto/1**  Traz o produto por um determinado id  
**http://localhost:8080/api/produto/imagem/1**  Traz o produto por um determinado id com o relacionamento com imagem  
**http://localhost:8080/api/produto/**  Traz todos os produtos  
**http://localhost:8080/api/produto/imagem/**  Traz todos os produtos com relacionamento da imagem  
**http://localhost:8080/api/produto/getAllByProdutoPai/1**  Retorna todos os produtos que estao relacionados a um produto pai  
**http://localhost:8080/api/produto/imagens/1**  Traz todas as imagens relacionadas a um produto  

#### Instalation and Configuration

Para a instação o Projeto não requer muito.  
1. A Maven project >= 3.2 
2. Clonar o projeto e executar utilizando o comando **mvn spring-boot:run**.
3. Abrir o browser e realizar as chamadas da api contidas nos **Exemplos de utilizacao**

Para executar os testes.
Para a instação o Projeto não requer muito.  
1. A Maven project >= 3.2 
2. Clonar o projeto e executar utilizando o comando **mvn test**.

**Sobre a configuração**: o arquivo application.properties file tem linhas comentadas sobre a configuração do log.
Para melhor performance, os logs estao comentados e podem ser ativados.
