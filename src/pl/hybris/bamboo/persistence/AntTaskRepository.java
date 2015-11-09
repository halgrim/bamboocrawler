package pl.hybris.bamboo.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by i323728 on 11/9/15.
 */
public interface AntTaskRepository extends MongoRepository<AntTask, String>
{

    AntTask findByHeader(String Header);
    //List<AntTask> findSomeValue(String someValue);

}

