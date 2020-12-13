package br.com.grupoqualitylife.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@SequenceGenerator(sequenceName = "seq_funcionario"
,name = "seq_funcionario",initialValue = 1)
@Entity
@Table(name="tblfuncionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(generator = "seq_funcionario")
	@Column(name = "id")
	@JsonProperty("id")
	private long id;
	
	@Column(name = "nome_funcionario")
	@JsonProperty("nome_funcionario")
	private String nomeFuncionario;
	
	
		
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nomeFuncionario=" + nomeFuncionario + "]";
	}
	public Funcionario() {
		super();
	}
	public Funcionario(long id, String nomeFuncionario) {
		super();
		this.id = id;
		this.nomeFuncionario = nomeFuncionario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

}
