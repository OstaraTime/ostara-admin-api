package ostara2.api.admin.repository;

import ostara2.api.admin.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("""
	    SELECT p FROM Person p
	    JOIN FETCH p.dept
	    WHERE p.personId IN :ids
	""")
	List<Person> findAllByIdInWithDept(@Param("ids") List<Integer> ids);

}
