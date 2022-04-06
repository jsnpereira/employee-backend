package com.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> findAllEmployees(){
       List<Employee> employees = employeeRepository.findAll();
       return employees.stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO findById(String id) {
        Optional<Employee> result = employeeRepository.findById(Integer.valueOf(id));
        return EmployeeMapper.toDTO(result.get());
    }

    public void save(EmployeeDTO employeeDTO) {
        employeeRepository.save(EmployeeMapper.toModel(employeeDTO));
    }

    public  void delete(int id){
        employeeRepository.deleteById(id);
    }
}
