package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.CategoriaBo;
import br.com.fiap.waypoint.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {

    CategoriaBo categoriaBo = new CategoriaBo();

    //Selecionar
    @GetMapping
    public ArrayList<Categoria> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(id);
        return categoriaBo.SelecionarBo(categoria);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Categoria categoria) throws SQLException, ClassNotFoundException {
        categoriaBo.InserirBo(categoria);

        URI location = URI.create("/categoria/" +
                categoria.getIdCategoria());
        return ResponseEntity.created(location).body(categoria.getIdCategoria());

    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Categoria categoria) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        categoria.setIdCategoria(id);
        if (categoriaBo.buscarPorid(categoria.getIdCategoria()) == null) {
            return ResponseEntity.notFound().build();
        }

        categoriaBo.AtualizarBo(categoria);
        return ResponseEntity.ok("Categoria atualizada com sucesso!");
    }
    // Buscar um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Categoria encontrado = categoriaBo.buscarPorid(id);
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
        if (categoriaBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }


}
