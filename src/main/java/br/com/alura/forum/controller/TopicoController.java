package br.com.alura.forum.controller;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    @Autowired
    TopicoRepository topicoRepository;

  @GetMapping
    public List<TopicoDto> lista() {
        var topicos = topicoRepository.findAll();
        return TopicoDto.TopicoDtoConverter.converter(topicos);
    }

}