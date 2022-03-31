package com.Lavoro.Piano;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PianoRepository extends JpaRepository<Piano, Long> {

    @Transactional
    void deleteByCodiceLavoro(Timestamp codiceLavoro);

    //JPQL query
    @Query(value = "select p " +
            "from Piano p " +
            "where p.fase.stato <> com.Lavoro.Fasi.Concluso " +
            "order by p.codiceLavoro desc ")
    List<Piano> findNotComplitedPiani();

    @Query(value = "" +
            "Select case when COUNT(p) = 1 THEN " + // Fa un case con i risultati, se trova oiù di un risultato ritorna true, altrimenti falso
            "TRUE ELSE FALSE END " +
            "FROM Piano p " +
            "WHERE p.codiceLavoro = ?1")
    boolean selectExistPiano (Timestamp existLavoro);

    @Query(value = "" +
            "Select p " +
            "FROM Piano p " +
            "WHERE p.cliente.id_clifor = :id_clifor and p.archivio = :archivio " +
            "order by p.codiceLavoro desc ")
    List<Piano> selectPianoByClienteAndArchivio (@Param("id_clifor") Long id_clifor, @Param("archivio") String archivio);

    @Query(value = "" +
            "Select p " +
            "FROM Piano p " +
            "WHERE p.codiceLavoro = :codiceLavoro ")
    Optional<Piano> selectByCodiceLavoro(@Param("codiceLavoro") Timestamp codiceLavoro);

}
