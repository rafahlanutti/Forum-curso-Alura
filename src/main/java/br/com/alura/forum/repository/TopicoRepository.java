package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);
    /**
     *
     * Quando se tem problema com ambiguidade ex: Topico.cursoNome Topico.curso.nome
     * O "_" diz ao spring data que tem que buscar topico.curso.nome e não topico.cursoNome
     */
    List<Topico> findByCurso_Nome(String nomeCurso);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
