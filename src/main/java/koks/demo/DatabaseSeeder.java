package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    public DatabaseSeeder(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    private PersonRepository personRepository;

    @Override
    public void run(String... strings) throws Exception {

        List<Person> person = new ArrayList<>();

        person.add(new Person(1, "Marko",  500));
        person.add(new Person(2, "Stevo" , 1875));
        person.add(new Person(3, "Ivo", 2500));

        personRepository.saveAll(person);

    }

}
