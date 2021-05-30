package org.sid.TP.web;

import javax.validation.Valid;

import org.sid.TP.entities.Patient;
import org.sid.TP.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PatientController{

	@Autowired
	private PatientRepository patients;

	@GetMapping("/list")
	public String listPatients(Model model, @RequestParam(name = "page", defaultValue = "0") int num,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "") String keyword) {
		Page<Patient> page = patients.findByNameContains(keyword, PageRequest.of(num, size));
		model.addAttribute("patients", page.getContent());
		model.addAttribute("pages", new int[page.getTotalPages()]);
		model.addAttribute("currentPage", num);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", size);
		return "list_patients";
	}

	@GetMapping(path = "/deletePatient")
	public String delete(Long id, int size, int page, String keyword) {
		patients.deleteById(id);
		return "redirect:/list?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}
	
	@GetMapping(path = "/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return "form_patients";
	}
	
	@PostMapping(path = "/savePatient")
	public String savePatient(Model model, @RequestParam() String mode, @Valid Patient patient, BindingResult bindingResult) {
		model.addAttribute("mode", mode);
		if (bindingResult.hasErrors()) {
			return "form_patients";
		}
		patients.save(patient);
		model.addAttribute("patient", patient);
		return "confirmation";
	}
	
	@GetMapping(path = "/editPatient")
	public String editPatient(Model model, Long id) {
		model.addAttribute("patient", patients.findById(id).get());
		model.addAttribute("mode", "edit");
		return "form_patients";
	}

	@GetMapping(path = "/list/{name}")
	@ResponseBody
	public Page<Patient> patients(@PathVariable String name) {
		return patients.findByNameContains(name, PageRequest.of(0, 99));
	}

}