package pl.hybris.bamboo.persistence;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by i323728 on 11/9/15.
 */
public interface AntTaskRepository extends MongoRepository<AntTask, String>
{

    AntTask findByHeader(String header);
    //List<AntTask> findSomeValue(String someValue);
    List<AntTask> findAllByTargetNotNull();


}

