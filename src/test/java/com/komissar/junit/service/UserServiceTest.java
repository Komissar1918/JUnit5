package com.komissar.junit.service;

import com.komissar.junit.dto.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
 class UserServiceTest {
    private UserService userService;

    @BeforeAll //используются для указания о том, что аннотированный метод должен быть выполнен перед всеми @Test, @RepeatedTest, @ParameterizedTest и @TestFactory методами в текущем классе.
    static void init() {
        System.out.println("Before all: ");
    }

    @BeforeEach //помечаются методы, которые будут выполняться перед стартом каждого из тестовых методов. В этих методах не обязательно создаются переменные. Возможно, инициализация заключается в подготовке файловой системы, например, созданию файлов. Но если метод должен создать данные и сделать их доступными в тестах, то придётся использовать поля класса.
    void prepare() {
        System.out.println("Before each: " + this);
        userService = new UserService();
    }

    @Test
    void userEmptyIfNotUserAdded() {
        System.out.println("Test 1: " + this);
        userService = new UserService();
        List<User> users = userService.getAll();
        assertTrue(users.isEmpty());  //тип assert - boolean.
        // input ->[box == func] -> actual output
    }

    @Test
    void usersSizeIfUserAdded() {
        System.out.println("Test 2: " + this);
        userService = new UserService();
        userService.add(new User());
        userService.add(new User());
        List<User> users = userService.getAll();
        assertEquals(2, users.size());
    }

    @AfterEach //используется для обозначения того, что аннотированный метод должен выполняться после каждого метода, помеченного @Test, @RepeatedTest, @ParameterizedTest, или @TestFactory в текущем классе
    void deleteDataFromDataBase() {
        System.out.println("After each: " + this);
    }

    @AfterAll //используется для обозначения того, что аннотированный метод должен быть выполнен после всех тестов в текущем тестовом классе.
    static void close() {
        System.out.println("After all: ");
    }
}
