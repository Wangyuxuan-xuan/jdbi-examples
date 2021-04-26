package person;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Person.class)
public interface PersonDao {
    @SqlUpdate("""
                    CREATE TABLE person(
                        id INT PRIMARY KEY,
                        name VARCHAR,
                        birthDate DATE,
                        gender VARCHAR,
                        email VARCHAR,
                        phone VARCHAR,
                        profession VARCHAR,
                        married BOOLEAN
                    )
            """)
    void createTable();

    @SqlUpdate("INSERT INTO person VALUES (:id,:name,:birthDate,:gender,:email,:phone,:profession,:married)")
    void insert(@BindBean Person person);

    @SqlQuery("SELECT * FROM person WHERE id =:id")
    Optional<Person> find(@Bind("id") String id);

    @SqlUpdate("DELETE FROM person WHERE id =:id")
    void delete(@BindBean String id);

    @SqlQuery("SELECT * FROM person ORDER BY id")
    List<Person> findAll();
}
