package br.edu.ifrs.petbemestar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.petbemestar.dominio.Pet;
import br.edu.ifrs.petbemestar.dominio.Tutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class PetDAOJPA implements PetDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvar(Pet pet) {
		em.persist(pet);
		
	}

	@Override
	public Pet buscarPorId(Long id) {
		return em.find(Pet.class, id);
	}

	@Override
	public List<Pet> listarTodos() {
		return em.createQuery("Select p from Pet p JOIN FETCH p.tutor ORDER BY p.nome", Pet.class).getResultList();
	}

	@Override
	public void atualizar(Pet pet) {
		em.merge(pet);
		
	}

	@Override
	public void remover(Long id) {
		Pet pet = em.find(Pet.class, id);
		
		if(pet != null) {
			em.remove(pet);
		}
	}
	
	@Override
	public List<Pet> buscarPorTutor(Long idTutor){
		return em.createQuery("Select p From Pet p WHERE p.tutor.id = :idTutor", Pet.class).setParameter("idTutor", idTutor).getResultList();
	}

}
