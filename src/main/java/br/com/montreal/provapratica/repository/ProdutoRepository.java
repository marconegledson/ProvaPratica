package br.com.montreal.provapratica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.montreal.provapratica.domain.Imagem;
import br.com.montreal.provapratica.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("Select p From Produto p Inner Join Fetch p.imagens")
	public Produto findFetchedAll();
	
	@Query("Select p From Produto p Inner Join Fetch p.imagens Where p.id = :idProduto")
	public Produto findFetchedById(@Param("idProduto") Long idProduto);
	
	@Query("Select p From Produto p Where p.produto.id = :idProdutoPai")
	public List<Produto> findChildrenProdutosByIdProdutoPai(@Param("idProdutoPai") Long idProdutoPai);
	
	@Query("Select i From Imagem i Where i.produto.id = :idProduto")
	public List<Imagem> findImagensByIdProduto(@Param("idProduto") Long idProduto);
}
