package br.com.zup.edu.funcionariosmanager.controller;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import br.com.zup.edu.funcionariosmanager.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    @PatchMapping("/{idFuncionario}")
    public ResponseEntity<Void> atualizaSalario(@PathVariable Long idFuncionario){

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND,"funcionário não localizado"));

        funcionario.atualizaSalario();

        funcionarioRepository.save(funcionario);

        return ResponseEntity.noContent().build();


    }
}
