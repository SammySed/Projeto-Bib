package com.biblioteca.biblioteca.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.biblioteca.biblioteca.modelos.Leitor;
import com.biblioteca.biblioteca.rdn.LeitorRdn;


@RestController
public class LeitorController {
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/leitores")
    public List<Leitor> Get() {

        LeitorRdn rdn = new LeitorRdn();
        return rdn.obterTodos();

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/leitores/{id}")
    public Leitor GetById(@PathVariable("id") int id) {

        LeitorRdn rdn = new LeitorRdn();
        return rdn.obterPorId(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/leitores")
    public int Post(@RequestBody Leitor pcli) throws SQLException {

        LeitorRdn rdn = new LeitorRdn();
        return rdn.inserir(pcli);

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("leitores/{id}")
    public int Put(@PathVariable(value = "id") int id, @RequestBody Leitor pLeitor) {
        LeitorRdn rdn = new LeitorRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.alterar(pLeitor);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "leitor não encontrado");
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("leitores/{id}")
    public int Delete(@PathVariable(value = "id") int id) {

        LeitorRdn rdn = new LeitorRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.excluir(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "leitor não encontrado");
        }
    }
}
