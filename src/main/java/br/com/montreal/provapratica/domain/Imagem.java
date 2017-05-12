package br.com.montreal.provapratica.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.montreal.provapratica.domain.enumerators.TipoImagem;

@Entity
@Table(name = "imagem")
@SuppressWarnings("serial")
public class Imagem implements Serializable{
	
	@Id
	@Column(name = "idImagem")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = 10)
	private TipoImagem tipoImagem;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduto")
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoImagem getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(TipoImagem tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", tipoImagem=" + tipoImagem + ", produto=" + produto + "]";
	}
}
