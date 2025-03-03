package br.com.opportune.model;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_opportune_plano")
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é Obrigatório!")
	@Size (min = 5, max = 100, message = "O atributo deve ter no mínimo 05 e no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo descrição é Obrigatório!")
	@Size (min = 5, max = 100, message = "O atributo deve ter no mínimo 05 e no máximo 100 caracteres")
	private String descricao;
	
	@NotNull(message = "O atributo status é obrigatório!")
	private boolean status;
	
	@Min(value = 0, message = "O preço não pode ser negativo.")
    @Max(value = 500000, message = "O preço não pode ser superior a 500.000.")
	private Double valor;
	
	@UpdateTimestamp
	private LocalDate data;
	
	@ManyToOne
	@JsonIgnoreProperties("plano")
	private Empresa empresa;
	
	@ManyToOne
	@JsonIgnoreProperties("plano")
	private Cliente cliente;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
