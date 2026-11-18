package br.com.fiap.waypoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.ErrorResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

@RestControllerAdvice
public class TratamentoExcecoes {


    // Erros do banco Oracle
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> erroBanco(SQLException erro) {

        // Registro duplicado
        if (erro.getErrorCode() == 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro: registro ja cadastrado no banco!");
        }

        // Campo obrigatorio nao preenchido
        if (erro.getErrorCode() == 1400) {
            return ResponseEntity.badRequest()
                    .body("Erro: preencha todos os campos obrigatorios!");
        }

        // Valor que nao respeita uma regra do banco, como voto ou acessibilidade
        if (erro.getErrorCode() == 2290) {
            return ResponseEntity.badRequest()
                    .body("Erro: um campo possui um valor nao permitido!");
        }

        // Data inexistente ou formato de data incorreto
        if (erro.getErrorCode() == 1839 || erro.getErrorCode() == 1843 ||
                erro.getErrorCode() == 1847 || erro.getErrorCode() == 1861) {
            return ResponseEntity.badRequest()
                    .body("Erro: informe uma data valida no formato DD/MM/AAAA!");
        }

        // Valor maior que o permitido
        if (erro.getErrorCode() == 12899 || erro.getErrorCode() == 1438) {
            return ResponseEntity.badRequest()
                    .body("Erro: valor maior que o permitido no banco!");
        }

        // Erros de relacionamento
        if (erro.getErrorCode() == 2291 ||
                erro.getErrorCode() == 2292) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro: problema de relacionamento entre registros!");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao acessar o banco de dados!");
    }

    // Erros de valores invalidos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> erroDados(IllegalArgumentException erro) {

        return ResponseEntity.badRequest()
                .body("Erro: " + erro.getMessage());
    }

    // JSON malformado ou valor de tipo incorreto, como texto no lugar de numero
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> erroJson(HttpMessageNotReadableException erro) {
        return ResponseEntity.badRequest()
                .body("Erro: confira o formato do JSON e os tipos dos campos!");
    }

    // Parametro numerico preenchido com texto
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> erroTipo(MethodArgumentTypeMismatchException erro) {
        return ResponseEntity.badRequest()
                .body("Erro: informe um numero valido no parametro!");
    }

    // Parametro obrigatorio ausente na URL
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> erroParametro(MissingServletRequestParameterException erro) {
        return ResponseEntity.badRequest()
                .body("Erro: informe o parametro " + erro.getParameterName() + "!");
    }

    // Erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> erroGeral(Exception erro) {

        // Mantem o codigo dos erros HTTP do Spring, como 404, 405 e 415.
        if (erro instanceof ErrorResponse) {
            ErrorResponse erroHttp = (ErrorResponse) erro;
            return ResponseEntity.status(erroHttp.getStatusCode())
                    .body("Erro: requisicao nao atendida. Confira a URL, o metodo e o tipo de conteudo!");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servidor!");
    }

}
