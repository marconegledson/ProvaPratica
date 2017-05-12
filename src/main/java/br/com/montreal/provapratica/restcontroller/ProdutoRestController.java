package br.com.montreal.provapratica.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;
import br.com.montreal.provapratica.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRestController.class);
	
	@Autowired
	private ProdutoService service;
	
	
	@RequestMapping(value = "/{idProduto}/{fetched}", method = RequestMethod.GET)
	public @ResponseBody Produto restGet(	@PathVariable(name = "idProduto", required = true) Long idProduto, 
											@PathVariable(name = "fetched", required = false) Boolean fetched) {
		LOGGER.debug(">> restGet {}", idProduto);
		Produto produto = fetched != null && fetched ? service.findFetchedById(idProduto) : service.findById(idProduto);
		LOGGER.debug("<< restGet {}", produto);
		return produto;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Produto> restList() {
		LOGGER.debug(">> restList");
		List<Produto> produtos =  service.findAll();
		LOGGER.debug("<< restList {}", produtos);
		return produtos;
	}
	
	@RequestMapping(value = "/getAllByProdutoPai/{idProdutoPai}", method = RequestMethod.GET)
	public @ResponseBody List<Produto> restGetByProdutoPai(@PathVariable(name = "idProdutoPai", required = true) Long idProdutoPai) {
		LOGGER.debug(">> restGetByProdutoPai {}", idProdutoPai);
		List<Produto> produtos = service.findChildrenProdutosByIdProdutoPai(idProdutoPai);
		LOGGER.debug("<< restGetByProdutoPai {}", produtos);
		return produtos;
	}
	
	@RequestMapping(value = "/imagens/{idProduto}", method = RequestMethod.GET)
	public @ResponseBody List<Imagem> restImagemListByIdProduto(@PathVariable(name = "idProduto", required = true) Long idProduto) {
		LOGGER.debug(">> restImagemListByIdProduto {}", idProduto);
		List<Imagem> imagens = service.findImagensByIdProduto(idProduto);
		LOGGER.debug("<< restImagemListByIdProduto {}", imagens);
		return imagens;
	}
	

}
