package koks.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @RequestMapping(value = "/less/{funds}", method = RequestMethod.GET)
    public List<Person> getFunds(@PathVariable double funds){
        return personRepository.findByPersonalFundsLessThan(funds);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  List<Person> create(@RequestBody Person person){
        personRepository.save(person);

        return personRepository.findAll();
    }

}
