package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.RotaBo;
import br.com.fiap.waypoint.entities.Rota;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/rota")

public class RotaController {

    RotaBo rotaBo = new RotaBo();

    //Selecionar
    @GetMapping
    public ArrayList<Rota> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Rota rota = new Rota();
        rota.setIdRota(id);
        return rotaBo.SelecionarBo(rota);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Rota rota) throws SQLException, ClassNotFoundException {
        rotaBo.InserirBo(rota);

        URI location = URI.create("/rota/" +
                rota.getIdRota());
        return ResponseEntity.created(location).body(rota.getIdRota());
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Rota rota) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        rota.setIdRota(id);
        if (rotaBo.buscarPorid(rota.getIdRota()) == null) {
            return ResponseEntity.notFound().build();
        }

        rotaBo.AtualizarBo(rota);
        return ResponseEntity.ok("Rota atualizada com sucesso!");
    }

    // Buscar um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Rota> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Rota encontrado = rotaBo.buscarPorid(id);
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
        if (rotaBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        rotaBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }


}
