package br.edu.ifrs.petbemestar.dominio;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "campo obrigatorio")
	private String nome;
	
	@NotNull(message = "campo obrigatorio - escolha")
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;
	
	@NotNull(message = "campo obrigatorio - escolha")
	@Enumerated(EnumType.STRING)
	private Especie especie;
	
	
	@NotNull(message = "campo obrigatorio - escolha")
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;
	
	public Pet() {}

	public Pet(String nome, Tamanho tamanho, Especie especie, Tutor tutor) {
		super();
		this.nome = nome;
		this.tamanho = tamanho;
		this.especie = especie;
		this.tutor = tutor;
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

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	

}
