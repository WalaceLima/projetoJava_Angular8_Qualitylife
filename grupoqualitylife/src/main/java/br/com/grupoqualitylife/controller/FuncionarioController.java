package br.com.grupoqualitylife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupoqualitylife.entity.Funcionario;
import br.com.grupoqualitylife.repository.FuncionarioRepository;

@ResponseBody
@RestController
@RequestMapping("/api")

public class FuncionarioController {

	@Autowired
	private FuncionarioRepository dao;

	@GetMapping("/funcionarios")
	public List<Funcionario> findAll() {
		return dao.findAll();
	}

	@GetMapping("/buscar_funcionario/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Funcionario funcionario = dao.findById(id).get();
		if (funcionario == null) {
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("erro-findId", "id Nao encontrado");
				}
			};
			return ResponseEntity.status(404).body(mapa);
		} else {
			return ResponseEntity.status(200).body(funcionario);
		}

	}

	@PostMapping("/gravar_funcionario")
	public ResponseEntity<?> save(@RequestBody Funcionario funcionario) {
		try {
			Funcionario f = dao.save(funcionario);
			if (f == null) {
				throw new Exception("Gravação Invalida");
			}
			return ResponseEntity.status(200).body(f);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("error", "Dados nao gravado...");
				}
			};
			return ResponseEntity.status(500).body(mapa);
		}

	}

	@PutMapping("/modificar_funcionario/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
		try {
			Funcionario f = dao.findById(id).get();
			if (f == null) {
				throw new Exception("Nao possivel modificar, Id nao encontrado");
			}
			f.setNomeFuncionario(funcionario.getNomeFuncionario());
			;
			dao.save(f);
			return ResponseEntity.status(200).body(f);
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("error", "Dados nao modificado...");
				}
			};
			return ResponseEntity.status(500).body(mapa);
		}

	}

	@DeleteMapping("excluir_funcionario/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			dao.deleteById(id);
			return ResponseEntity.status(200).body("Excluido com sucesso");
		} catch (Exception ex) {
			Map<String, Object> mapa = new HashMap<String, Object>() {
				{
					put("error", "Dados nao excluido...");
				}
			};
			return ResponseEntity.status(500).body(mapa);
		}
		

	}
}