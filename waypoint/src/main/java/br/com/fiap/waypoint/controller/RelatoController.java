package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.RelatorioBo;
import br.com.fiap.waypoint.entities.Relato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/relato")

public class RelatoController {

    RelatorioBo relatoBo = new RelatorioBo();

    //Selecionar
    @GetMapping
    public ArrayList<Relato> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Relato relato = new Relato();
        relato.setIdRelato(id);
        return relatoBo.SelecionarBo(relato);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Relato relato) throws SQLException, ClassNotFoundException {
        relatoBo.InserirBo(relato);

        URI location = URI.create("/relato/" +
                relato.getIdRelato());
        return ResponseEntity.created(location).body(relato.getIdRelato());

    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Relato relato) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        relato.setIdRelato(id);
        if (relatoBo.buscarPorid(relato.getIdRelato()) == null) {
            return ResponseEntity.notFound().build();
        }

        relatoBo.AtualizarBo(relato);
        return ResponseEntity.ok("Relato atualizado com sucesso!");
    }

    // Buscar um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Relato> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Relato encontrado = relatoBo.buscarPorid(id);
        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para excluir.");
        }
        if (relatoBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        relatoBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }


}
