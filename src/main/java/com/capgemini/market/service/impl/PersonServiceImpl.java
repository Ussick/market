package com.capgemini.market.service.impl;

import com.capgemini.market.model.Person;
import com.capgemini.market.repository.PersonRepository;
import com.capgemini.market.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Boolean addPerson(Person person) {
        if (person == null || person.getLogin() == null) {
            return false;
        } else {
            if (personExist(person)) {
                return false;
            }
        }
        String password = getSaltedHashedPassword(person.getPassword());
        person.setPassword(password);
        String status = (person.getStatus()).toLowerCase();
        if ((!status.equals("admin")) && (!status.equals("user"))) {
            person.setStatus("unknown");
        } else {
            person.setStatus(status);
        }
        personRepository.save(person);
        return true;
    }

    @Override
    public String checkPerson(String login, String password) {
        password = getSaltedHashedPassword(password);
        Person tmpPerson = personRepository.checkPerson(login, password);
        if (tmpPerson != null) {
            return tmpPerson.getName() + ", " + getAnswerForCheck(tmpPerson.getStatus());
        } else {
            return "User doesn't exist";
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> all = personRepository.findAll();
        return (all != null) ? all : new ArrayList<>();
    }

    @Override
    public Person getById(int id) {
        Optional<Person> byId = personRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return new Person();
        }
    }

    @Override
    public String changedPerson(Person person) {
        if (person == null){
            return "This user doesn't exist";
        }
        if (!personExist(person)) {
            return "This user doesn't exist";
        }
        Person tmpPerson = getById(person.getId());
        String answer;
        if (tmpPerson.getId() == 0) {
            answer = "This user doesn't exist";
        } else {
            String password = getSaltedHashedPassword(person.getPassword());
            person.setPassword(password);
            personRepository.save(person);
            answer = "User with id " + person.getId() + " has been updated";
        }
        return answer;
    }

    @Override
    public String deletePerson(int id) {
        Person person = getById(id);
        if (person.getId() != 0) {
            personRepository.delete(person);
            return "User with id " + id + " has been deleted";
        } else {
            return "User with id " + id + " doesn't exist";
        }
    }

    private String getAnswerForCheck(String status) {
        switch (status) {
            case "admin":
                return "you have successfully logged in as an admin.";
            case "user":
                return "you have successfully logged in as a user.";
            default:
                return "you have successfully logged in at the user level. If you are not a user, please contact " +
                        "the main administrator.";
        }
    }

    private boolean personExist(Person person) {
        boolean exist = false;
        List<Person> personList = findAll();
        for (Person tmp : personList) {
            if (tmp.getLogin().equals(person.getLogin())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    private String getSaltedHashedPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String passphrase = "myspecialspice" + password;
        md.update(passphrase.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, md.digest()));
    }
}