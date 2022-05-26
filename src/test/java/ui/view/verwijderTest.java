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

import static org.junit.Assert.*;

public class verwijderTest {
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

    // Verwijder: succes
    @Test
    public void test_Verwijderen_gelukt_Toont_tabel_zonder_lied(){
        // 1. Ga naar overzicht
        driver.get(url + "Controller?command=overzicht");
        // 2. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 3. Klik verwijder
        driver.findElement(By.id("verwijder1")).click();
        // 4. Juiste pagina?
        assertEquals("Verwijder | Liedjes", driver.getTitle());
        // 5. Klik verwijder
        driver.findElement(By.id("verwijder")).click();
        // 6. Item niet meer in tabel
        assertFalse(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "Stay Beautiful"));
        assertFalse(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "Taylor Swift"));
    }

    // Verwijder: cancel
    @Test
    public void test_Verwijderen_gecancelt_Toont_tabel_met_lied() {
        // 1. Ga naar overzicht
        driver.get(url + "Controller?command=overzicht");
        // 2. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 3. Klik verwijder
        driver.findElement(By.id("verwijder1")).click();
        // 4. Juiste pagina?
        assertEquals("Verwijder | Liedjes", driver.getTitle());
        // 5. Klik cancel
        driver.findElement(By.id("cancel")).click();
        // 6. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 7. Item in lijst? --> Originaal tweede uit lijst als eerste verwijderd is bij test hierboven
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "I Wonder (Departure)"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "ABBA"));
    }

    // HULP
    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst){
        for(WebElement item: items){
            if(item.getText().equalsIgnoreCase(tekst)) return true;
        }
        return false;
    }
}
