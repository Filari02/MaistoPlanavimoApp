package app.repository;

import app.model.Produktas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduktasRepository extends JpaRepository<Produktas, Long> {
}
