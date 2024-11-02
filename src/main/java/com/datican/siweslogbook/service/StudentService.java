package com.datican.siweslogbook.service;

import com.datican.siweslogbook.model.Student;
import com.datican.siweslogbook.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



//    @Autowired
//    private LogbookEntryRepository logbookEntryRepository;


//    public AuthService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }


    @Transactional
    public Student registerUser(
            Long matricNumber,
            String firstName,
            String middleName,
            String lastName,
            Long phoneNumber,
            String email,
            String residentialAddress,
            String password,
            String faculty,
            String department,
            String companyName,
            String companyLocation,
            String companyCustomerCare,
            String supervisorId,
            String supervisorName,
            String supervisorPhone
    ) {
        Student student = new Student();
        student.setMatricNumber(matricNumber);
        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setLastName(lastName);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);
        student.setResidentialAddress(residentialAddress);
        student.setPassword(password);
        student.setFaculty(faculty);
        student.setDepartment(department);
        student.setCompanyName(companyName);
        student.setCompanyLocation(companyLocation);
        student.setCompanyCustomerCare(companyCustomerCare);
        student.setSupervisorId(supervisorId);
        student.setSupervisorName(supervisorName);
        student.setSupervisorPhone(supervisorPhone);
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            System.out.println("Error saving student: " + e.getMessage());
            return null;
        }
    }

    public Student authenticate(Long matricNumber, String password) {
        return studentRepository.findByMatricNumberAndPassword(matricNumber, password)
                .orElse(null);
    }


    public Student findByMatricNumber(Long matricNumber) {
        return studentRepository.findByMatricNumber(matricNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
