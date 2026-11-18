package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.TrechoBo;
import br.com.fiap.waypoint.entities.Trecho;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/trecho")

public class TrechoController {

    TrechoBo trechoBo = new TrechoBo();

    //Selecionar
    @GetMapping
    public ArrayList<Trecho> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Trecho trecho = new Trecho();
        trecho.setIdTrecho(id);
        return trechoBo.SelecionarBo(trecho);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Trecho trecho) throws SQLException, ClassNotFoundException {
        trechoBo.InserirBo(trecho);

        URI location = URI.create("/trecho/" +
                trecho.getIdTrecho());
        return ResponseEntity.created(location).body(trecho.getIdTrecho());
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Trecho trecho) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        trecho.setIdTrecho(id);
        if (trechoBo.buscarPorid(trecho.getIdTrecho()) == null) {
            return ResponseEntity.notFound().build();
        }

        trechoBo.AtualizarBo(trecho);
        return ResponseEntity.ok("Trecho atualizado com sucesso!");
    }

    // Buscar um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Trecho> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Trecho encontrado = trechoBo.buscarPorid(id);
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
        if (trechoBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        trechoBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }


}
