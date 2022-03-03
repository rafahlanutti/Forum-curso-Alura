package br.com.alura.forum.controller;

import br.com.alura.forum.dtos.DetalheTopicoDto;
import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.dtos.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(@RequestParam(required = false) String nomeCurso) {

        List<Topico> topicos;
        if (Strings.isEmpty(nomeCurso)) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.carregarPorNomeDoCurso(nomeCurso);
        }
        return TopicoDto.TopicoDtoConverter.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@Valid @RequestBody TopicoForm obj, UriComponentsBuilder uriBuilder) {
        var topico = TopicoForm.TopicoDomainConverter.converter(cursoRepository, obj);
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable("id") Long id) {
        var finded = topicoRepository.findById(id).get();
        var dto = new DetalheTopicoDto(finded);
        return ResponseEntity.ok(dto);
    }
}