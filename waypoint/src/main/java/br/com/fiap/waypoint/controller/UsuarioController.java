package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.UsuarioBo;
import br.com.fiap.waypoint.Bo.RelatorioBo;
import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.entities.Relato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/usuario")

public class UsuarioController {

    UsuarioBo usuarioBo = new UsuarioBo();
    RelatorioBo relatoBo = new RelatorioBo();

    //Selecionar
    @GetMapping
    public ArrayList<Usuario> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuarioBo.SelecionarBo(usuario);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Usuario usuario) throws SQLException, ClassNotFoundException {
        usuarioBo.InserirBo(usuario);

        URI location = URI.create("/usuario/" +
                usuario.getId());
        return ResponseEntity.created(location).body(usuario.getId());
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Usuario usuario) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        usuario.setId(id);
        if (usuarioBo.buscarPorid(usuario.getId()) == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioBo.AtualizarBo(usuario);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para excluir.");
        }
        if (usuarioBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Usuario encontrado = usuarioBo.buscarPorid(id);
        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado);
    }


    // Um usuario pode possuir varios relatos (1:N).
    @GetMapping("/{id}/relatos")
    public ResponseEntity<ArrayList<Relato>> listarRelatos(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        if (usuarioBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relatoBo.SelecionarPorUsuarioBo(id));
    }

    @PostMapping("/{id}/relatos")
    public ResponseEntity<Long> inserirRelato(@PathVariable("id") long id, @RequestBody Relato relato) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para cadastrar o relato.");
        }
        if (usuarioBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        relato.setUsuario(usuario);
        relatoBo.InserirBo(relato);
        URI location = URI.create("/relato/" + relato.getIdRelato());
        return ResponseEntity.created(location).body(relato.getIdRelato());
    }

}
