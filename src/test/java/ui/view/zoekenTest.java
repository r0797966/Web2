package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class zoekenTest {
    private WebDriver driver;
    private String url = "http://localhost:8080/website_war_exploded/";

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    // Zoeken: gevonden
    @Test
    public void test_Zoek_Met_geldige_waarde_Toont_resultaat() {
        // 1. Ga naar zoeken
        driver.get(url + "Controller?command=zoek");
        // 2. Juiste pagina?
        assertEquals("Zoek | Liedjes", driver.getTitle());
        // 3. Vul in
        driver.findElement(By.id("zoek")).sendKeys("tool");
        driver.findElement(By.id("submit")).click();
        // 4. Juiste pagina?
        assertEquals("Gevonden | Liedjes", driver.getTitle());
        // 5. Tabel bevat resultaat
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "tool"));
    }

    // Zoeken: gevonden
    @Test
    public void test_Zoek_Met_ongeldige_waarde_Toont_niet_gevonden_pagina() {
        // 1. Ga naar zoeken
        driver.get(url + "Controller?command=zoek");
        // 2. Juiste pagina?
        assertEquals("Zoek | Liedjes", driver.getTitle());
        // 3. Vul in
        driver.findElement(By.id("zoek")).sendKeys("azerty");
        driver.findElement(By.id("submit")).click();
        // 4. Juiste pagina?
        assertEquals("Niet gevonden | Liedjes", driver.getTitle());
        // 5. Niet gevonden boodschap
        assertEquals("Je zoektocht naar azerty leverde geen resultaten op", driver.findElement(By.id("message")).getText());
    }


    // HULP
    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst){
        for(WebElement item: items){
            if(item.getText().equalsIgnoreCase(tekst)) return true;
        }
        return false;
    }
}
