package com.service.ServiceB.ServiceL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import com.service.ServiceB.DTO.StudentDTO;
import com.service.ServiceB.entity.Student;
import com.service.ServiceB.repository.StudentRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository repository;
    @InjectMocks
    private StudentService service;
    @Captor
    private ArgumentCaptor<List<Student>> captor;

    @Test
    void testSaveAll() {
        StudentDTO dto = new StudentDTO();
        dto.setFirst_name("Manoj");
        dto.setLast_name("Kumar");
        dto.setDate_of_birth("2000-01-01");
        dto.setGender("Male");
        dto.setPhone_number("1234567890");
        dto.setEmail("manoj@test.com");
        dto.setCourse_name("CS");
        dto.setBlood_group("O+");
        dto.setCgpa("8.5");
        service.saveAll(List.of(dto));

        verify(repository, times(1)).saveAll(captor.capture());
        List<Student> savedList = captor.getValue();
        assertEquals(1, savedList.size());
        Student s = savedList.get(0);
        assertEquals("Manoj", s.getFirstName());
        assertEquals("Kumar", s.getLastName());
        assertEquals("CS", s.getCourseName());
        assertEquals(8.5, s.getCgpa());
    }
}