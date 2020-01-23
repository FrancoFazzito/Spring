package com.bolsadeideas.springboot.datajpa.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;
import com.bolsadeideas.springboot.datajpa.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.datajpa.app.models.service.IclienteService;
import com.bolsadeideas.springboot.datajpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	@Qualifier("clienteService")
	private IclienteService clienteService;

	@GetMapping({ "/listar", "/", "" })
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			authentication.getName();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			auth.getName();
		}

		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"");
		if (securityContext.isUserInRole("ROLE_ADMIN")) {

		}
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "listado de cliente");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Detalle cliente: " + cliente.getNombre());
		return "ver";
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "formulario de cliente");
		return "form";
	}

	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
		} else {
			flash.addFlashAttribute("error", "no puede ser cero");
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "editar cliente");
		model.addAttribute("cliente", cliente);
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/form")
	public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile file, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "formulario de Cliente");
			return "form";
		}

		if (!file.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {
				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			cliente.setFoto(uniqueFilename);
		}

		clienteService.Save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "cliente creado con exito");
		return "redirect:/listar";
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito!");
			}

		}
		return "redirect:/listar";
	}

	// obtener roles
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return false;
		}

		java.util.Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));
	}

}
