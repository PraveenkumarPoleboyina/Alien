package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Student {

    @Autowired
    private SchoolRepository schoolRepository;
    @GetMapping("/")
    public String home() {
        return "mainloginpage";  // loads templates/loginpage.html
    }

    @GetMapping("/submit")
    public String submit(
            @RequestParam("username") String s1,
            @RequestParam("password") String s2,
            Model m) {

        if (s1.equals("praveenkumar") && s2.equals("bluefox")) {
            return "loginpage";  // loads templates/result.html
        } else {
            m.addAttribute("error", "Invalid username or password");
            return "error";   // loads templates/error.html
        }
    }

//    @GetMapping("/hi")
//    public String showHome() {
//        return "loginpage"; // or redirect to your form page if needed
//    }

    @PostMapping("/add")
    public String add(@RequestParam Long id,
                      @RequestParam String fullname,
                      @RequestParam String jobtitle,
                      @RequestParam String email,
                      @RequestParam String salary,
                      @RequestParam String gender,
                      Model model) {

        if (schoolRepository.findById(id).isPresent()) {
            // ID already exists, return error page
           // model.addAttribute("message", "ID already exists! Please use a different ID.");
            return "idexists";
        } else {
            // ID not found, save new student
            School school = new School(id, fullname, jobtitle, email, salary, gender);
            schoolRepository.save(school);
            return "output";
        }
    }



    @GetMapping("/byid")
    public String getById(@RequestParam("id1") Long id, Model model) {
        School school = schoolRepository.findById(id).orElse(null);
        if (school != null) {
            model.addAttribute("school", school);
            return "edit";
        } else {
            return "notfound";
        }
    }


    @PostMapping("/update")
    public String update(@RequestParam Long id,
                         @RequestParam String fullname,
                         @RequestParam String jobtitle,
                         @RequestParam String email,
                         @RequestParam String salary,
                         @RequestParam String gender) {

        School updatedSchool = new School(id,fullname,jobtitle, email, salary, gender);
        schoolRepository.save(updatedSchool);
        return "output";
    }
    

    @GetMapping("/delete")
    public String delete(@RequestParam("id1") Long id) {
        schoolRepository.deleteById(id);
        return "output";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<School> schools = schoolRepository.findAll();
        model.addAttribute("schools", schools);
        return "list";
    }
}
