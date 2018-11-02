package com.example.firdaychallenge.Main;


import com.example.firdaychallenge.Classes.Department;
import com.example.firdaychallenge.Classes.Employee;
import com.example.firdaychallenge.Repo.DepartmentRepo;
import com.example.firdaychallenge.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;


    @RequestMapping("/")
    public String homePage(){


        return "homepage";
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(Model model){

        model.addAttribute("employee" ,new Employee());
        model.addAttribute("department", departmentRepo.findAll() );
        return "employeeform";


    }

    @PostMapping("/processemployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult result){

        if(result.hasErrors()){
            return "emloyeeform";
        }
        employeeRepo.save(employee);

        return "homepage";
    }

    @RequestMapping("/addDepartment")
    public String addDepartment(Model model){

        model.addAttribute("department", new Department());


        return "departmentform";
    }

    @PostMapping("/processdepartment")
    public String saveDepartment(@ModelAttribute("department") Department department, BindingResult result){

        if(result.hasErrors()){
            return "departmentform";
        }
        departmentRepo.save(department);

        return "homepage";
    }

    @RequestMapping("/departments")
    public String showDepartments(Model model){
        model.addAttribute("departments", departmentRepo.findAll());

        return "listdepartments";
    }

    @RequestMapping("/employees")
    public String showEmployees(Model model){
        model.addAttribute("employees", employeeRepo.findAll());
        model.addAttribute("department", departmentRepo.findAll());
        return "listemployee";

    }

    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeRepo.findById(id).get());

        return "detailpage";
    }
/*
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", departmentRepo.findById(id).get());
        model.addAttribute("departments", departmentRepo.findAll());

        return "employeeform";

    }*/

}
