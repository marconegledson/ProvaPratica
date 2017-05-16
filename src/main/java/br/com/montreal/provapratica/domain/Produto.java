package br.com.montreal.provapratica.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.montreal.provapratica.json.viewer.View;

@Entity
@Table(name = "produto")
@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto implements Serializable {

	@Id
	@Column(name = "idProduto")
	@JsonView(View.Summary.class)
	private Long id;

	@NotBlank
	@Column(name = "nome", length = 25)
	@JsonView(View.Summary.class)
	private String nome;

	@Column(name = "descricao", length = 100)
	@JsonView(View.Summary.class)
	private String descricao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProdutoPai")
	@JsonView(View.Father.class)
	private Produto produtoPai;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", targetEntity = Imagem.class)
	@JsonView(View.Children.class)
	private List<Imagem> imagens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Produto getProdutoPai() {
		return produtoPai;
	}

	public void setProdutoPai(Produto produtoPai) {
		this.produtoPai = produtoPai;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", produtoPai=" + produtoPai + ", imagens=" + imagens + "]";
	}

	

}
