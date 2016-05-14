package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the local_estoque database table.
 * 
 */
@Entity
@Table(name="local_estoque")
@NamedQuery(name="LocalEstoque.findAll", query="SELECT l FROM LocalEstoque l")
public class LocalEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCAL_ESTOQUE_CDLOCALEST_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCAL_ESTOQUE_CDLOCALEST_GENERATOR")
	@Column(name="cd_localest", unique=true, nullable=false)
	private Integer cdLocalest;

	@Column
	private String descricao;

	public LocalEstoque() {
	}

	public Integer getCdLocalest() {
		return this.cdLocalest;
	}

	public void setCdLocalest(Integer cdLocalest) {
		this.cdLocalest = cdLocalest;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}