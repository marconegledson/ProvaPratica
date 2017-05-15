package br.com.montreal.provapratica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;
import br.com.montreal.provapratica.repository.ProdutoRepository;

@Service
@Transactional(readOnly = true)
public class ProdutoService implements ReadService<Produto>{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	/* (non-Javadoc)
	 * @see br.com.montreal.provapratica.service.ReadService#findById(java.lang.Long)
	 */
	@Override
	public Produto findById(Long id) {
		LOGGER.debug(" >> findById [id={}] ", id);
		Produto produto = null;
		try {
			produto =  produtoRepository.getOne(id);
		} catch(Exception e){
			LOGGER.error("Falha ao buscar o produto ", e);
		} finally {
			LOGGER.debug(" << findById");
		}
		
		return produto;
	}

	/* (non-Javadoc)
	 * @see br.com.montreal.provapratica.service.ReadService#findAll()
	 */
	@Override
	public List<Produto> findAll() {
		LOGGER.debug(" >> findAll");
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = produtoRepository.findAll();
		} catch (Exception e) {
			LOGGER.error("Falha ao buscar os produtos ", e);
		} finally {
			LOGGER.debug(" << findAll");
		}
		
		return produtos;
	}
	
	/**
	 * Busca pelo produto por um id especifico com o relacionamento de imagem
	 * @param idProduto a ser pesquisado
	 * @return O objeto do produto
	 */
	public Produto findFetchedById(Long idProduto){
		LOGGER.debug(" >> findFetchedById [id={}] ", idProduto);
		Optional<Produto> produto = null;
		try {
			produto = Optional.ofNullable(produtoRepository.findFetchedById(idProduto));
		} catch(Exception e){
			LOGGER.error("Falha ao buscar o produto ", e);
		} finally {
			LOGGER.debug(" << findFetchedById");
		}
		return produto.orElseThrow(() -> new EntityNotFoundException());
	}
	
	/**
	 * Busca por todos os filhos de um determinado projeto pai pelo seu id
	 * @param idProdutoPai a ser pesquisado
	 * @return Uma colecao de projetos filhos
	 */
	public List<Produto> findChildrenProdutosByIdProdutoPai(Long idProdutoPai){
		LOGGER.debug(" >> findChildrenProdutosByIdProdutoPai [id={}] ", idProdutoPai);
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos =   produtoRepository.findChildrenProdutosByIdProdutoPai(idProdutoPai);
		} catch(Exception e){
			LOGGER.error("Falha ao buscar o produto ", e);
		} finally {
			LOGGER.debug(" << findChildrenProdutosByIdProdutoPai");
		}
		return produtos;
	}
	
	/**
	 * Busca por todas as imagens de um determinado produto pelo seu id
	 * @param idProduto a ser pesquisado
	 * @return Uma colecao de imagens do produto
	 */
	public List<Imagem> findImagensByIdProduto(Long idProduto){
		LOGGER.debug(" >> findImagensByIdProduto [id={}] ", idProduto);
		List<Imagem> imagens = new ArrayList<>();
		try {
			imagens = produtoRepository.findImagensByIdProduto(idProduto);
		} catch(Exception e){
			LOGGER.error("Falha ao buscar o produto ", e);
		} finally {
			LOGGER.debug(" << findImagensByIdProduto");
		}
		return imagens;
	}

	/**
	 * Busca por todos os produtos com a imagem
	 * @return retorna todos os produtos com as suas imagens
	 */
	public List<Produto> findFetchedAll() {
		LOGGER.debug(" >> findAll");
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = produtoRepository.findFetchedAll();
		} catch (Exception e) {
			LOGGER.error("Falha ao buscar os produtos ", e);
		} finally {
			LOGGER.debug(" << findAll");
		}
		
		return produtos;
	}
	
	

}
