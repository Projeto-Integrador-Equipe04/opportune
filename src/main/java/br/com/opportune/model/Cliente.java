package br.com.opportune.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name ="tb_clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String nome;
	
	@NotBlank(message= "O atributo cnpj/cpf é Obrigatório")
	@Size(min= 11, max = 14)
	private String cnpjCpf;
	
	@NotNull(message = "O atributo telefone é Obrigatório!")
	private String tel;
	
	@NotNull(message = "O atributo endereço é Obrigatório!")
	private String endereco;
	
	//@Schema(example = "email@email.com.br")
	@NotNull(message = "O atributo email é obrigatório")
	@Email(message = "O atributo email deve ser um email válido")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("cliente")
	private List<Oportunidade> oportunidade;

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

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Oportunidade> getOportunidade() {
		return oportunidade;
	}

	public void setOportunidade(List<Oportunidade> oportunidade) {
		this.oportunidade = oportunidade;
	}
}
