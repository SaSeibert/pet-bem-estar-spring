package br.edu.ifrs.petbemestar.dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "campo obrigatorio")
	private String nome;
	
	@NotBlank(message = "campo obrigatorio")
	private String email;
	
	@NotBlank(message = "campo obrigatorio")
	
	@JsonIgnore
	@OneToMany(mappedBy = "tutor")
	private List<Pet> pets = new ArrayList<Pet>();
	
	private String telefone;
	
	public Tutor() {}
	
	public Tutor(String nome, String email, String telefone) {
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
	}
	
	public void setNome(String nome) {
			this.nome = nome;
	}
	
	public void setEmail(String email) {
			this.email = email;
	}
	
	public void setTelefone(String telefone) {
			this.telefone = telefone;
	
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	public String getTelefone() {
		return this.telefone;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}

	
	
}