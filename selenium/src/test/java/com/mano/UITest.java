package com.mano;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class UITest {
    WebDriver driver;
    @BeforeEach
    void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:3000");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testInvalidEmail() throws InterruptedException {
        driver.findElement(By.name("first_name")).sendKeys("mano");
        driver.findElement(By.name("last_name")).sendKeys("k");
        driver.findElement(By.name("date_of_birth")).sendKeys("2005-06-13");
        driver.findElement(By.name("phone_number")).sendKeys("9876543210");
        driver.findElement(By.name("email")).sendKeys("abc");
        driver.findElement(By.name("gender")).sendKeys("Male");
        driver.findElement(By.name("course_name")).sendKeys("AI");
        driver.findElement(By.name("blood_group")).sendKeys("O+");
        driver.findElement(By.name("cgpa")).sendKeys("8.5");
        driver.findElement(By.id("submit-btn")).click();
        Thread.sleep(2000);
        String pageText = driver.findElement(By.tagName("body")).getText();
        if (pageText.contains("Invalid email")) {
            System.out.println("ERROR FOUND: Invalid email");
        } else {
            System.out.println("ERROR NOT FOUND");
        }
        Assertions.assertTrue(pageText.contains("Invalid email"));
    }

    @Test
    void testInvalidPhone() throws InterruptedException {
        driver.findElement(By.name("first_name")).sendKeys("John");
        driver.findElement(By.name("email")).sendKeys("john@gmail.com");
        driver.findElement(By.name("phone_number")).sendKeys("123");
        driver.findElement(By.id("submit-btn")).click();
        Thread.sleep(2000);
        String pageText = driver.findElement(By.tagName("body")).getText();
        Assertions.assertTrue(pageText.contains("Phone must be 10 digits"));
    }

    @Test
    void testValidStudent() throws InterruptedException {
        driver.findElement(By.name("first_name")).sendKeys("Mano");
        driver.findElement(By.name("last_name")).sendKeys("Kumar");
        driver.findElement(By.name("date_of_birth")).sendKeys("20-05-2010");
        driver.findElement(By.name("phone_number")).sendKeys("9876543210");
        driver.findElement(By.name("email")).sendKeys("mano123@gmail.com");
        driver.findElement(By.name("gender")).sendKeys("Male");
        driver.findElement(By.name("course_name")).sendKeys("AI");
        driver.findElement(By.name("blood_group")).sendKeys("O+");
        driver.findElement(By.name("cgpa")).sendKeys("8.5");

        driver.findElement(By.id("submit-btn")).click();

        Thread.sleep(3000);

        WebElement table = driver.findElement(By.cssSelector("[data-testid='student-table']"));
        Assertions.assertTrue(table.getText().contains("Mano"));
    }
}