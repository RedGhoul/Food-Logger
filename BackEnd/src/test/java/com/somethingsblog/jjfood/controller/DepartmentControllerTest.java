package com.somethingsblog.jjfood.controller;

import com.somethingsblog.jjfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FoodController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService departmentService;

//    private Department department;
//
//    @BeforeEach
//    void setUp() {
//        department = Department.builder()
//                .departmentAddress("11 napfasdfasdfa")
//                .departmentCode("9989")
//                .departmentName("2442")
//                .departmentId(1L)
//                .active(true)
//                .build();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void saveDepartment() throws Exception {
//        Department inputDepartment = Department.builder()
//                .departmentAddress("Hamilton")
//                .departmentCode("IT-06")
//                .departmentName("IT")
//                .departmentId(1L)
//                .build();
//
//        Mockito.when(departmentService.saveDepartment(inputDepartment))
//                .thenReturn(department);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "    \"departmentName\": \"2442\",\n" +
//                        "    \"departmentAddress\": \"11 napfasdfasdfa\",\n" +
//                        "    \"departmentCode\":\"9989\",\n" +
//                        "    \"active\":true\n" +
//                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void getDepartmentById() throws Exception {
//        Mockito.when(departmentService.getDepartmentById(1L))
//                .thenReturn(department);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers
//                        .jsonPath("$.departmentName")
//                        .value(department.getDepartmentName()));
//    }
}