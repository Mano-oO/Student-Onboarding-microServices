package com.mano.page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class spage{
    WebDriver driver;
    WebDriverWait wait;
    public spage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void enterFirstName(String val) {
        driver.findElement(By.name("first_name")).sendKeys(val);
    }
    public void enterLastName(String val) {
        driver.findElement(By.name("last_name")).sendKeys(val);
    }
    public void enterDOB(String val) {
        driver.findElement(By.name("date_of_birth")).sendKeys(val);
    }
    public void enterPhone(String val) {
        driver.findElement(By.name("phone_number")).sendKeys(val);
    }
    public void enterEmail(String val) {
        driver.findElement(By.name("email")).sendKeys(val);
    }
    public void selectGender(String val) {
        driver.findElement(By.name("gender")).sendKeys(val);
    }
    public void selectCourse(String val) {
        driver.findElement(By.name("course_name")).sendKeys(val);
    }
    public void selectBloodGroup(String val) {
        driver.findElement(By.name("blood_group")).sendKeys(val);
    }
    public void enterCGPA(String val) {
        driver.findElement(By.name("cgpa")).sendKeys(val);
    }
    public void clickSubmit() {
        driver.findElement(By.id("submit-btn")).click();
    }

    public boolean isErrorVisible(String message) {
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'" + message + "')]")
                )
        );
        return error.isDisplayed();
    }

    public boolean isStudentPresentInTable(String name) {
        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-testid='student-table']")
                )
        );
        return table.getText().contains(name);
    }
}
