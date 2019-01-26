package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "app.init-db", havingValue = "true")
public class DatabaseSeeder implements CommandLineRunner {

    private PersonRepository personRepository;

    @Autowired
    public DatabaseSeeder(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<Person> person = new ArrayList<>();

        person.add(new Person(1, "Marko",  500));
        person.add(new Person(2, "Stevo" , 1875));
        person.add(new Person(3, "Ivo", 2500));

        personRepository.saveAll(person);

    }

}
