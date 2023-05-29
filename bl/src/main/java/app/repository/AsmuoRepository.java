package app.repository;

import app.model.Asmuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsmuoRepository extends JpaRepository<Asmuo, Long> {
    Asmuo findByElPastas(String elPastas);
}
