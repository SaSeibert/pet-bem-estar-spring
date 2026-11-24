package br.edu.ifrs.petbemestar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.PetDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Pet;
import br.edu.ifrs.petbemestar.dominio.Tutor;

@Service
public class PetService {

	
	private final PetDAO petdao;
	private final TutorDAO tutordao;
	
	public PetService(TutorDAO tutordao, PetDAO petdao) {
		this.tutordao = tutordao;
		this.petdao = petdao;
	}
	
	public List<Pet> listarTodos(){
		return petdao.listarTodos();
	}
	
	public List<Pet> buscarPorTutor(Long idTutor){
		return petdao.buscarPorTutor(idTutor);}
	}

	public Pet buscarPorId(Long id) {
		return petdao.buscarPorId(id);
	}
	
	public void salvar(Pet pet) {
		exigirTutorValido(pet);
		petdao.salvar(pet);
	}
	
	public void atualizar(Pet pet) {
		exigirTutorValido(pet);
		petdao.atualizar(pet);
	}
	
	public void remover(Long id) {
		petdao.remover(id);
	}
	
	private void exigirTutorValido(Pet pet) {
		if(pet.getTutor() == null || pet.getTutor().getId() == null) {
			throw new RegraDeNegocioException("O campo Tutor é obrigatório");
		}
		
		Tutor tutorEncontrado = tutordao.buscarPorId(pet.getTutor().getId());
		
		if(tutorEncontrado == null) {
			throw new RegraDeNegocioException("Tutor não encontrado no sistema");

		}
		pet.setTutor(tutorEncontrado);
	}
	

}
