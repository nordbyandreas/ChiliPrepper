package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 *
 * Interface for generic CRUD operations on the repository for the Alternative type.
 *
 * Because of -Enabled JPARepository(DataConfig)  and  - extended Crudrepository (from the spring data library)
 * Spring data will generate the implemented class automatically upon Application Boot
 *
 * uses "SMART METHOD NAMING"
 *
 * example for the UserDao:
 * Spring Data would know by a method named "User findByUsername(String Username),"
 * that the implementation it generates needs to return a User-object that matches the username passed
 *
 */

@Repository
public interface AlternativeDao extends CrudRepository<Alternative, Long> {

    /**
     * Searches teh database for all Alternatives with the given QuestionId
     *
     * @param id
     * @return a List of all Alternatives matching the given QuestionId
     */
    List<Alternative> findAllByQuestion_Id(Long id);


    /**
     * Deletes all Alternatives in the database with the given QuestionId
     *
     * @param id
     */
    void deleteAllByQuestion_Id(Long id);

}
