package control.services;

import control.models.Person;
import control.repositories.PeopleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true) // для всех методов у кого нет @
public class Services {
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public Services(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public List<Person> findAll(){
        return peopleRepositories.findAll();
    }

    public Person index(int id){
        return peopleRepositories.findById(id).stream().findAny().orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepositories.save(person);

    }

    @Transactional
    public void update(int id,Person person){
        person.setId(id);
        peopleRepositories.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepositories.deleteById(id);
    }

}
