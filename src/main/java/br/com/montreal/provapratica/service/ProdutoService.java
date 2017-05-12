package br.com.montreal.provapratica.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;
import br.com.montreal.provapratica.repository.ProdutoRepository;

@Service
@Transactional(readOnly = true)
public class ProdutoService implements CrudService<Produto>{

	@Autowired
	private ProdutoRepository repository;
	
	
	@Override
	public Produto findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findFetchedById(Long idProduto){
		return repository.findFetchedById(idProduto);
	}
	
	public List<Produto> findChildrenProdutosByIdProdutoPai(Long idProdutoPai){
		return repository.findChildrenProdutosByIdProdutoPai(idProdutoPai);
	}
	
	public List<Imagem> findImagensByIdProduto(Long idProduto){
		return repository.findImagensByIdProduto(idProduto);
	}
	
	

}
