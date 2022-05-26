package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class htmlValideTest {
    private WebDriver driver;
    private String url = "http://cyclone3.uclllabs.be:8081/Hamelryck_Robyn4_war/";

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
       driver.quit();
    }

    // Home
    @Test
    public void test_Valid_home_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url);

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Toevoegen
    @Test
    public void test_Valid_toevoegen_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=toevoegen");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Overzicht
    @Test
    public void test_Valid_overzicht_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=overzicht");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Edit
    @Test
    public void test_Valid_edit_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=update&id=1");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Edit
    @Test
    public void test_Valid_verwijder_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=verwijderBevestiging&id=1");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Zoek
    @Test
    public void test_Valid_zoek_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=zoek");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

    // Logboek
    @Test
    public void test_Valid_gevonden_pagina() {
        driver.get("https://html5.validator.nu/");
        WebElement invulveld = driver.findElement(By.id("doc"));

        // verander naar de url die je wil valideren
        invulveld.sendKeys(url + "Controller?command=logboek");

        WebElement button = driver.findElement(By.id("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("#results .success"));
        assertEquals("The document is valid HTML5 + ARIA + SVG 1.1 + MathML 2.0 (subject to the utter previewness of this service).", pass.getText());
    }

}
