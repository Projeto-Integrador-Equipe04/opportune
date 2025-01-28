package br.com.opportune.model;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_empresa")
public class Empresa {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull (message = "O campo NOME é obrigatório.")
	private String nome;

	@NotNull(message = "O Atributo CPNJ é obrigatório!")
	@Size(min = 14, max = 14, message = "O Atributo CPNJ deve ter o tamanho de 14 caracteres!")
	private String cpnj;
	
	@NotNull(message = "O Atributo Usuário é Obrigatório!")
	@Email(message = "O Atributo Usuário deve ser um email válido!")
	private String email;
	
	@NotBlank(message = "O Atributo Senha é Obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	@Column(nullable = false, updatable = false)
	private LocalDate data;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("empresa")
	private List<Plano> plano;

	public Empresa(){}
	public Empresa(Long id, String nome, String cpnj, String email, String senha, LocalDate data, List<Plano> plano) {
		this.id = id;
		this.nome = nome;
		this.cpnj = cpnj;
		this.email = email;
		this.senha = senha;
		this.data = data;
		this.plano = plano;
	}

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

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<Plano> getPlano() {
		return plano;
	}

	public void setPlano(List<Plano> plano) {
		this.plano = plano;
	}

	@PrePersist
    protected void onCreate() {
        this.data = LocalDate.now();
    }
}
