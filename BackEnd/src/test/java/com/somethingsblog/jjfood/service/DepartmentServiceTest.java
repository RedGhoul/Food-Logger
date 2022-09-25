package com.somethingsblog.jjfood.service;

import com.somethingsblog.jjfood.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private FoodService departmentService;

    @MockBean
    private FoodRepository departmentRepo;

//    @BeforeEach
//    void setUp() {
//        Department department = Department.builder()
//                .departmentName("IT")
//                .departmentAddress("Toronto")
//                .departmentCode("JJDD")
//                .departmentId(1L)
//                .build();
//
//        Mockito.when(departmentRepo.findDepartmentByDepartmentNameIgnoreCase("IT"))
//                .thenReturn(department);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    @DisplayName("Get Data Based On Valid Name")
//    public void whenValidDepartmentName_thenDepartmentShouldBeFound(){
//        String departmentName = "IT";
//        Department found = departmentService.fetchDepartmentByName(departmentName);
//        assertEquals(departmentName,found.getDepartmentName());
//    }
}