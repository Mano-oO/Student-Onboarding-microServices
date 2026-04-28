package com.mano.tests;

import com.mano.page.spage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
public class stest {
    WebDriver driver;
    spage page;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:3000");
        page = new spage(driver);
    }
    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testInvalidEmail() {

        page.enterFirstName("mano");
        page.enterLastName("k");
        page.enterDOB("2005-06-13");
        page.enterPhone("9876543210");
        page.enterEmail("abc"); // invalid
        page.selectGender("Male");
        page.selectCourse("AI");
        page.selectBloodGroup("O+");
        page.enterCGPA("8.5");
        page.clickSubmit();
        Assertions.assertTrue(page.isErrorVisible("Invalid email"));
    }

    @Test
    void testInvalidPhone() {
        page.enterFirstName("mano");
        page.enterLastName("k");
        page.enterDOB("2005-06-13");
        page.enterPhone("910");
        page.enterEmail("abc");
        page.selectGender("Male");
        page.selectCourse("AI");
        page.selectBloodGroup("O+");
        page.enterCGPA("8.5");
        page.clickSubmit();
        Assertions.assertTrue(page.isErrorVisible("Phone must be 10 digits"));
    }

    @Test
    void testValidStudent() {
        page.enterFirstName("Mano");
        page.enterLastName("K");
        page.enterDOB("13-06-2002");
        page.enterPhone("9876543210");
        page.enterEmail("mano123@gmail.com");
        page.selectGender("Male");
        page.selectCourse("AI");
        page.selectBloodGroup("O+");
        page.enterCGPA("8.5");
        page.clickSubmit();
        Assertions.assertTrue(page.isStudentPresentInTable("Mano"));
    }
}
