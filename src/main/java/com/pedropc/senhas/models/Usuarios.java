package com.pedropc.senhas.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usuarioId;
	@Column(nullable = false, unique = true)
	private String usuario;
	@Column(nullable = false)
	private String senha;
	
	@ManyToMany
    @JoinTable(name = "USUARIOS_FUNCOES",
    		joinColumns = @JoinColumn(name = "usuarios_usuario_id"),
    		inverseJoinColumns = @JoinColumn(name = "funcoes_id"))
	private List<Funcao> funcoes;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	
	

}
