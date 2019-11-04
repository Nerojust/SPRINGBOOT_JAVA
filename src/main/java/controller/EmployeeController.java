package controller;

import dao.EmployeeDao;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    /**
     * create a new employee
     *
     * @param employee
     * @return
     */
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    /**
     * get all employees
     *
     * @return
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    /**
     * get a single employee
     *
     * @param employeeId
     * @return
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Employee emp = employeeDao.findAnEmployee(employeeId);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(emp);
    }

    /**
     * update an employee
     * @param empId
     * @param employeeDetails
     * @return
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateAnEmployee(@PathVariable(value = "id") Long empId, @Valid @RequestBody Employee employeeDetails) {
        Employee emp = employeeDao.findAnEmployee(empId);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        emp.setName(employeeDetails.getName());
        emp.setDesignation(employeeDetails.getDesignation());
        emp.setExpertise(employeeDetails.getExpertise());

        Employee updateEmployee = employeeDao.saveEmployee(emp);

        return ResponseEntity.ok().body(updateEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteAnEmployee(@PathVariable(value = "id") Long id){
        Employee emp = employeeDao.findAnEmployee(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        employeeDao.deleteAnEmployee(emp);
        return ResponseEntity.ok().build();
    }
}
