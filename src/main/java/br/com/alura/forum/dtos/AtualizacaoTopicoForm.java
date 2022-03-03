package br.com.alura.forum.dtos;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import javax.validation.constraints.NotBlank;

public class AtualizacaoTopicoForm {


    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;


    public AtualizacaoTopicoForm() {
    }

    public AtualizacaoTopicoForm(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
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

   public Topico atualizar(Long id, TopicoRepository repository) {
        var finded = repository.findById(id).get();
        finded.setTitulo(this.titulo);
        finded.setMensagem(this.mensagem);
        return finded;
   }
}
