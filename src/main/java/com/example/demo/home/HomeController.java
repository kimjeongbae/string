package com.example.demo.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    List<Person> personList = new ArrayList<>();
    public int lastid = 1;
    public int i = 1;

    @GetMapping("/home/increase")
    @ResponseBody
    public int index() {
        return i++;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int plus(@RequestParam(value = "a", defaultValue = "0") int a, @RequestParam(value = "b") int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        int id = lastid;
        Person person = new Person(lastid, name, age);
        personList.add(person);

        lastid++;

        return String.format("%d 번째 사람이 추가 되었습니다.", id);
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam("id") int id){
        boolean isRemove = personList.removeIf(person -> person.getId() == id);

        if(!isRemove){
                return id+"번 사람이 삭제 되었습니다";
        }

        return id+"번 사람이 존재하지 않습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(@RequestParam("id") int id,@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {

        Person modifyPerson = null;

        for (Person person : personList){
            if(person.getId() == id){
                modifyPerson = person;
            }
        }
        if(modifyPerson == null){
            return id+"번 사람이 존재하지 않습니다" ;
        }

        person.setName(name);
        person.setAge(age);
        return id+"번 사람이 수정 되었습니다.";
    }


    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> people () {

        return personList;
    }

}

