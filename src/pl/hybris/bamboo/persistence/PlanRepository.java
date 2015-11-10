package pl.hybris.bamboo.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by i323728 on 11/10/15.
 */
public interface PlanRepository extends MongoRepository<Plan, String>
{

    Plan findByUrl(String url);
    List<Plan> findByParsed(String parsed);

}
