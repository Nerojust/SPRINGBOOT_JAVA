package dao;

import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeDao {
    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * save an employee
     *
     * @param employee
     * @return employee
     */
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    /**
     * gets all employees
     *
     * @return
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * find a single employee
     *
     * @param employeeId
     * @return
     */
    public Employee findAnEmployee(Long employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    /**
     * delete one employee
     * @param employee
     */
    public void deleteAnEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
