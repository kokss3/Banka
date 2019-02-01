package koks.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  Većinom u kontroler stavljaš početak putanje koji će važiti za sve metode unutar kontroler klase.
  U ovom slučaju se metode u PersonController klasi koriste za updavljanje podacima Person objekata
  pa se u @RequestMapping od klase može staviti putanja 'persons' ->
  @RestController
  @RequestMapping("persons")
 */
@RestController
@RequestMapping()
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    /*
    Ako dohvaćaš neku listu Person objekata u PersonControlleru dovoljno je imati putanju "/persons"
    koja je automatski dodana gore na klasi, tako da tu ne treba biti '/all' nego je dovoljno samo @GetMapping.
    - kao što vidiš za GET zahtjev postoji @GetMapping, isto tako vrijedi za sve tipove zahtjeva (@PostMapping, @PutMapping...)
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @RequestMapping(value = "/less/{funds}", method = RequestMethod.GET)
    public List<Person> getFunds(@PathVariable double funds){
        return personRepository.findByPersonalFundsLessThan(funds);
    }

    /*
    Isto kao i za prvu metodu, za dodavanje novog Person-a dovoljno je imati putanju '/persons' i POST metodu,
    na temelju tog se odmah zna da se radi o insertu.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  List<Person> create(@RequestBody Person person){
        personRepository.save(person);
        return personRepository.findAll();
    }

    /*
    Za brisanje postoji DELETE zahtjev i u njemu je, kao što si i napravio, dovoljno poslati samo 'id' objekta
    koji želiš obrisati, ali ti '/remove' nije potreban. Dakle trebalo bi biti -> @DeleteMapping("/{id}")
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public  List<Person> remove(@PathVariable long id){
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    /*
    DRUGE SMJERNICE:
    - u ovakvim projektima se najčešće koriste 3 layera kako sam i rekao na početku - Controller, Service i Repository
          - kontroleri primaju zahtjeve i argumente koje pretvaraju u odgovarajući oblik i šalju u neki servis
          - servisi rade obradu podataka (Business logic) i pozivaju metode iz repozitorija
          - repozitoriji izvode upite nad bazom i vraćaju rezultate u servis
       - ok, ovo što imaš trenutno ne zahtjeva servisni sloj, ali će kasnije biti potreban
    - što se tiče testiranja endpointa, skini si Postman - program s kojim možeš kreirati zahtjeve na svoj backend jer
       pošto je to REST servis, nije potrebno raditi nikakve view-ove i pogotovo se ne treba s Angularom zezat :)
     */
}
