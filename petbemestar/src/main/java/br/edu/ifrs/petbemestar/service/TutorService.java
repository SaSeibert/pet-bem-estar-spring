package br.edu.ifrs.petbemestar.service;

import java.util.List;

import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;

public class TutorService {
	
	private final TutorDAO tutordao;

	public TutorService(TutorDAO tutordao) {
		this.tutordao = tutordao;
	}
	
	public List<Tutor> listarTodos(){
		return tutordao.listarTodos();
	}
	
	public Tutor buscarPorId(Long id) {
		return tutordao.buscarPorId(id);
	}
	
	public void salvar(Tutor tutor) {
		if(tutor.getId() == null) {
			if(tutordao.telefoneJaCadastrado(tutor.getTelefone())) {
				throw new RegraDeNegocioException("Telefone ja cadastrado");
			}
			tutordao.salvar(tutor);
		} else {
			 if(tutordao.telefonePertenceAOutro(tutor.getId(), tutor.getTelefone())){
				throw new RegraDeNegocioException("Telefone ja cadastrado");

			}
			tutordao.atualizar(tutor);
		}
	}
	
	public void remover(Long id) {
	    if (tutordao.buscarPorId(id) == null) {
	        throw new RegraDeNegocioException("Tutor não encontrado");
	    }
	    if (tutordao.verificarSeTemPets(id)) {
	        throw new RegraDeNegocioException(
	            "Não é possível remover este tutor com pets cadastrados.");
	    }
	    tutordao.remover(id);
	}

}
