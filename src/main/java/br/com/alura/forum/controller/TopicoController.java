package br.com.alura.forum.controller;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    @Autowired
    TopicoRepository topicoRepository;

    @GetMapping
    public List<TopicoDto> lista(@RequestParam String nomeCurso) {

        List<Topico> topicos;
        if (Strings.isEmpty(nomeCurso)) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }

        return TopicoDto.TopicoDtoConverter.converter(topicos);
    }

}