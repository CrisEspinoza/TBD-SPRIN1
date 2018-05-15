package Taller1db.TBD.Respository;

import Taller1db.TBD.Entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics,Long> {

    Statistics findStatisticsById (Long id);
    List<Statistics> findByOrOrderByLastUpdateDes();
}
