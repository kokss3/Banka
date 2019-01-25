package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private PersonRepository personRepository;

    @Autowired
    public DatabaseSeeder(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<Person> person = new ArrayList<>();

        person.add(new Person("Marko", 1, 500));
        person.add(new Person("Stevo", 2, 1875));
        person.add(new Person("Ivo", 3, 2500));

        personRepository.saveAll(person);

    }

}
