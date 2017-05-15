package br.com.montreal.provapratica.ProvaPratica;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProvaPraticaApplicationTests {

	@LocalServerPort
	private int port;
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	public void contextLoads() {
		get("/api/produto/").then().statusCode(200).and().contentType(ContentType.JSON).and().body("nome", not(empty()));
		get("/api/produto/imagem/").then().statusCode(200).and().contentType(ContentType.JSON);
		get("/api/produto/1").then().statusCode(200).and().contentType(ContentType.JSON);
		get("/api/produto/imagem/1").then().statusCode(200).and().contentType(ContentType.JSON);
		get("/api/produto/getAllByProdutoPai/1").then().statusCode(200).and().contentType(ContentType.JSON);
		get("/api/produto/imagens/1").then().statusCode(200).and().contentType(ContentType.JSON);

	}
	
	@Test
	public void contextNoExists() {
		get("/api/produto/100").then().statusCode(404).and().contentType(ContentType.JSON);
		get("/api/produto/imagem/100").then().statusCode(404).and().contentType(ContentType.JSON);

	}
	
	/**
	 * 1. Test Recuperar todos os Produtos excluindo os relacionamentos;
	 */
	@Test
	public void checkAllProdutosNoRelationship() {
		get("/api/produto/").then().body("imagens", is(empty()));
		get("/api/produto/").then().body("produtoPai", is(empty()));
	}
	
	/**
	 * 2. Recuperar todos os Produtos incluindo um relacionamento específico Imagem;
	 */
	@Test
	public void checkAllProdutosWithRelationship() {
		get("/api/produto/imagem/").then().body("imagens", is(not(empty())));
		List<Imagem> imagens = get("/api/produto/imagem/").getBody().jsonPath().getList("imagens", Imagem.class);
		assertThat(imagens, everyItem(hasProperty("id", notNullValue())));
	    assertThat(imagens, everyItem(hasProperty("tipo", notNullValue())));
	}
	
	
	/**
	 * 3. Igual ao no 1 utilizando um id de produto específico;
	 */
	@Test
	public void checkByIdProdutoNoRelationship() {
		get("/api/produto/1").then().body("imagens", is(empty()));
	}
	
	/**
	 * 4. Igual ao no 2 utilizando um id de produto específico;
	 */
	@Test
	public void checkByIdProdutoWithRelationship() {
		get("/api/produto/imagem/1").then().body("imagens", is(not(empty())));
		List<Imagem> imagens = get("/api/produto/imagem/").getBody().jsonPath().getList("imagens", Imagem.class);
		assertThat(imagens, everyItem(hasProperty("id", notNullValue())));
	    assertThat(imagens, everyItem(hasProperty("tipo", notNullValue())));
	}
	
	/**
	 * 5. Igual ao no 2 utilizando um id de produto específico;
	 */
	@Test
	public void checkByIdProdutoPai() {
		List<Produto> produtos = get("api/produto/getAllByProdutoPai/1").getBody().jsonPath().getList("produtos", Produto.class);
		assertThat(produtos, everyItem(hasProperty("id", notNullValue())));
	    assertThat(produtos, everyItem(hasProperty("nome", notNullValue())));
	}
	
	/**
	 * 6. Recupera a coleção de Imagens para um id de produto específico;
	 */
	@Test
	public void checkImagensByIdProduto() {
		List<Imagem> imagens = get("/api/produto/imagens/1").getBody().jsonPath().getList("imagens", Imagem.class);
		assertThat(imagens, everyItem(hasProperty("id", notNullValue())));
	    assertThat(imagens, everyItem(hasProperty("tipo", notNullValue())));
	}
	

}
