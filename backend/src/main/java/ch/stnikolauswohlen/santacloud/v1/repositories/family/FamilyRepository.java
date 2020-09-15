package ch.stnikolauswohlen.santacloud.v1.repositories.family;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.family.FamilyDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<FamilyDAO, Long>
{
    List<FamilyDAO> findAll();

    List<FamilyDAO> findByStatus(int status);
}
