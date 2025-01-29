package br.com.opportune.model;

import java.time.LocalDate;
import java.util.List;

public class EmpresaLogin {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private List<Plano> plano;
	private LocalDate data;
	private String cnpj;
	private String token;
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Plano> getPlano() {
		return plano;
	}
	public void setPlano(List<Plano> list) {
		this.plano = list;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setId(Long id) {
		this.id = id;
	}


}
