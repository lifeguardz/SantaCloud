package ch.stnikolauswohlen.santacloud.v1.repositories.helper;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.helper.HelperDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelperRepository extends JpaRepository<HelperDAO, Long>
{
    List<HelperDAO> findAll();
}
