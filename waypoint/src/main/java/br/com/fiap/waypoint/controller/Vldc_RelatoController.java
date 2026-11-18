package br.com.fiap.waypoint.controller;

import br.com.fiap.waypoint.Bo.Vldc_RelatoBo;
import br.com.fiap.waypoint.entities.Vldc_Relato;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/validacao")

public class Vldc_RelatoController {

    Vldc_RelatoBo vldc_RelatoBo = new Vldc_RelatoBo();

    //Selecionar
    @GetMapping
    public ArrayList<Vldc_Relato> Selecionar(@RequestParam(value = "id", required = false) Long id) throws SQLException, ClassNotFoundException {
        Vldc_Relato vldc_Relato = new Vldc_Relato();
        vldc_Relato.setIdVoto(id);
        return vldc_RelatoBo.SelecionarBo(vldc_Relato);
    }

    //Inserir
    @PostMapping
    public ResponseEntity<Long> inserir(@RequestBody Vldc_Relato vldc_Relato) throws SQLException, ClassNotFoundException {
        vldc_RelatoBo.InserirBo(vldc_Relato);

        URI location = URI.create("/validacao/" +
                vldc_Relato.getIdVoto());
        return ResponseEntity.created(location).body(vldc_Relato.getIdVoto());
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") long id, @RequestBody Vldc_Relato vldc_Relato) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para atualizar.");
        }
        vldc_Relato.setIdVoto(id);
        if (vldc_RelatoBo.buscarPorid(vldc_Relato.getIdVoto()) == null) {
            return ResponseEntity.notFound().build();
        }

        vldc_RelatoBo.AtualizarBo(vldc_Relato);
        return ResponseEntity.ok("Validacao do relato atualizada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vldc_Relato> buscarPorid(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para consultar.");
        }
        Vldc_Relato encontrado = vldc_RelatoBo.buscarPorid(id);
        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para excluir.");
        }
        if (vldc_RelatoBo.buscarPorid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vldc_RelatoBo.deletarBo(id);
        return ResponseEntity.noContent().build();
    }

}
