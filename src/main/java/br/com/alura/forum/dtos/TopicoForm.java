package br.com.alura.forum.dtos;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public TopicoForm() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public static class TopicoDomainConverter {

        public static Topico converter(CursoRepository repository, TopicoForm topico) {
            var curso = repository.findByNome(topico.getNomeCurso());
            return new Topico(topico.getMensagem(), topico.getTitulo(), curso);
        }

    }
}
