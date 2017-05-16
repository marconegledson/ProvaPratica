package br.com.montreal.provapratica.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;
import br.com.montreal.provapratica.json.viewer.View;
import br.com.montreal.provapratica.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRestController.class);

	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * Retorna o produto pelo identificado
	 * 
	 * @param idProduto
	 *            id do produto (obrigatorio)
	 * @return ResponseEntity contendo o httpstatus 200 ou 404 se encontrado. Para 200 retorna os dados da entidade para 404 uma string de aviso
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/{idProduto}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(View.Summary.class)
	public ResponseEntity<?> restGet(@PathVariable(name = "idProduto", required = true) Long idProduto) throws JsonProcessingException {
		LOGGER.debug(">> restGet {}", idProduto);
		Produto produto = produtoService.findById(idProduto);
		LOGGER.debug("<< restGet {}", produto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	/**
	 * Retorna o produto pelo identificador junto com o relacionamento de imagem
	 * 
	 * @param idProduto
	 *            id do produto (obrigatorio)
	 * @return ResponseEntity contendo o httpstatus 200 ou 404 se encontrado. Para 200 retorna os dados da entidade para 404 uma string de aviso
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/imagem/{idProduto}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(View.Children.class)
	public  ResponseEntity<?> restImagemGet(@PathVariable(name = "idProduto", required = true) Long idProduto) throws JsonProcessingException {
		LOGGER.debug(">> restImagemGet {}", idProduto);
		Produto produto = produtoService.findFetchedById(idProduto);
		LOGGER.debug("<< restImagemGet {}", produto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	/**
	 * Retorna todos os produtos
	 * 
	 * @return Collecao contendo todos os produtos
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(View.Summary.class)
	public ResponseEntity<?> restList() throws JsonProcessingException {
		LOGGER.debug(">> restList");
		List<Produto> produtos = produtoService.findAll();
        LOGGER.debug("<< restList {}", produtos);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	/**
	 * Retorna todos os produtos com o relacionamento com imagem
	 * 
	 * @return Collecao contendo todos os produtos
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/imagem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(View.Children.class)
	public ResponseEntity<?> resImagemtList() throws JsonProcessingException {
		LOGGER.debug(">> restList");
		List<Produto> produtos = produtoService.findFetchedAll();
		LOGGER.debug("<< restList {}", produtos);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	/**
	 * Retorna todos os produtos que estao relacionados a um produto pai
	 * 
	 * @param idProdutoPai
	 *            o id do produto pai (obrigatorio)
	 * @return A collecao de produtos que estao relacionados a um produto pai
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/getAllByProdutoPai/{idProdutoPai}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(View.Summary.class)
	public ResponseEntity<?> restGetByProdutoPai(@PathVariable(name = "idProdutoPai", required = true) Long idProdutoPai) throws JsonProcessingException {
		LOGGER.debug(">> restGetByProdutoPai {}", idProdutoPai);
		List<Produto> produtos = produtoService.findChildrenProdutosByIdProdutoPai(idProdutoPai);
		LOGGER.debug("<< restGetByProdutoPai {}", produtos);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	/**
	 * Retorna todas as imagens relacionadas a um produto
	 * 
	 * @param idProduto
	 *            id do produto (obrigatorio)
	 * @return A collecao de imagens que estao relacionados a um produto
	 */
	@RequestMapping(value = "/imagens/{idProduto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Imagem> restImagemListByIdProduto(@PathVariable(name = "idProduto", required = true) Long idProduto) {
		LOGGER.debug(">> restImagemListByIdProduto {}", idProduto);
		List<Imagem> imagens = produtoService.findImagensByIdProduto(idProduto);
		LOGGER.debug("<< restImagemListByIdProduto {}", imagens);
		return imagens;
	}

}
