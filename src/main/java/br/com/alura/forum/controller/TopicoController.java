package br.com.alura.forum.controller;

import br.com.alura.forum.dtos.AtualizacaoTopicoForm;
import br.com.alura.forum.dtos.DetalheTopicoDto;
import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.dtos.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
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
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction =  Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao ) {

        Page<Topico> topicos;
        if (Strings.isEmpty(nomeCurso)) {
            topicos = topicoRepository.findAll(paginacao);
        } else {
            topicos = topicoRepository.carregarPorNomeDoCurso(nomeCurso, paginacao);
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable("id") Long id, @Valid @RequestBody AtualizacaoTopicoForm obj) {
        var topico = obj.atualizar(id, topicoRepository);
        topicoRepository.save(topico);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        var topico = this.topicoRepository.findById(id).get();
        this.topicoRepository.delete(topico);
        return ResponseEntity.ok().build();
    }
}