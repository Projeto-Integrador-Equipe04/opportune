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
@Table(name ="tb_opportune_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String nome;
	
	@NotBlank(message= "O atributo cnpj/cpf é Obrigatório")
	@Size(min= 11, max = 11)
	private String cpf;
	
	@NotNull(message = "O atributo telefone é Obrigatório!")
	private String tel;
	
	@NotNull(message = "O atributo endereço é Obrigatório!")
	private String endereco;

	@NotBlank(message = "O atributo foto é Obrigatório!")
	private String foto;
	
	//@Schema(example = "email@email.com.br")
	@NotNull(message = "O atributo email é obrigatório")
	@Email(message = "O atributo email deve ser um email válido")
	private String email;

	@NotBlank(message = "O Atributo Senha é Obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("cliente")
	private List<Plano> plano;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public void setPlano(List<Plano> plano) {
		this.plano = plano;
	}
}
