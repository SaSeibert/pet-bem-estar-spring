package br.edu.ifrs.petbemestar.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.petbemestar.dao.PetDAO;
import br.edu.ifrs.petbemestar.dominio.Pet;

@RestController
public class PetController {
	
	private final PetDAO petDAO;
	
	public PetController(PetDAO petDAO) {
		this.petDAO = petDAO;
	}
	
	@PostMapping("/pets")
	@ResponseStatus(HttpStatus.CREATED)
	public Pet salvar(@RequestBody Pet pet) {
		petDAO.salvar(pet);
		return pet;
	}
	
	@GetMapping("/pets/{id}")
	public Pet buscarPorId(@PathVariable Long id) {
		return petDAO.buscarPorId(id);
	}
	
	@GetMapping("/pets")
	public List <Pet> listarTodos() {
		return petDAO.listarTodos();
	}
	
	@PutMapping("/pets/{id}")
	public Pet atualizar(@PathVariable Long id, @RequestBody Pet dados) {
		Pet pet = petDAO.buscarPorId(id);
		
		pet.setNome(dados.getNome());
		pet.setEspecie(dados.getEspecie());
		pet.setTamanho(dados.getTamanho());
		pet.setTutor(dados.getTutor());
		
		petDAO.atualizar(pet);
		return pet;
	}
	
	@DeleteMapping("/bichos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		petDAO.remover(id);
	}
	
	

}
