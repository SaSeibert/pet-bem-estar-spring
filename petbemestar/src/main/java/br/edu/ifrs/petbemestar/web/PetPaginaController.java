package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.petbemestar.dao.PetDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Especie;
import br.edu.ifrs.petbemestar.dominio.Pet;
import br.edu.ifrs.petbemestar.dominio.Tamanho;
import jakarta.validation.Valid;

@Controller
public class PetPaginaController {
	
	private final PetDAO petDAO;
	private final TutorDAO tutorDAO;
	
	
	public PetPaginaController(PetDAO petDAO, TutorDAO tutorDAO) {
		this.petDAO = petDAO;
		this.tutorDAO = tutorDAO;
	}
	
	private void adicionarListasNoModel(Model model) {
		model.addAttribute("tutores", tutorDAO.listarTodos());
		model.addAttribute("especies", Especie.values());
		model.addAttribute("tamanhos", Tamanho.values());
	}
	
	@GetMapping("/paginas/pets")
	public String listar(Model model) {
		model.addAttribute("pets", petDAO.listarTodos());
		return "pets/lista";
	}
	
	@GetMapping("/paginas/pets/novo")
	public String formulario(Model model) {
		model.addAttribute("pet", new Pet());
		adicionarListasNoModel(model);
		return "pets/formulario";
		
	}
	
	@PostMapping("/paginas/pets/novo")
	public String cadastrar(@Valid @ModelAttribute Pet pet, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			adicionarListasNoModel(model);
			return "pets/formulario";
		}
		petDAO.salvar(pet);
		return "redirect:/paginas/pets";
	}
	
	@GetMapping("/paginas/pets/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("pet", petDAO.buscarPorId(id));
		adicionarListasNoModel(model);
		return "pets/edicao";
	}
	
	@PostMapping("/paginas/pets/editar")
	public String atualizar(@Valid @ModelAttribute Pet pet, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			adicionarListasNoModel(model);
			return "pets/edicao";
		}
		petDAO.atualizar(pet);
		return "redirect:/paginas/pets";
	}
	
	@PostMapping("/paginas/pets/{id}/remover")
	public String remover(@PathVariable Long id) {
		petDAO.remover(id);
		return "redirect:/paginas/pets";
	}

}
