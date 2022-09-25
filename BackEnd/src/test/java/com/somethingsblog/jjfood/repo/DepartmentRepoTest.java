package com.somethingsblog.jjfood.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
// have to use this to stop it from defaulting to H2
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepoTest {
    @Autowired
    private FoodRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Long valueId;
//    @BeforeEach
//    void setUp() {
//        Department department =
//                Department.builder()
//                        .departmentName("Mechanical Engineering")
//                        .departmentCode("ME - 011")
//                        .departmentAddress("Delhi")
//                        .build();
//
//        var createdDepartment = entityManager.persist(department);
//        valueId = createdDepartment.getDepartmentId();
//    }
//
//    @Test
//    public void whenFindById_thenReturnDepartment() {
//        Department department = departmentRepository.findById(valueId).get();
//        assertEquals("Mechanical Engineering", department.getDepartmentName());
//    }

}