package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class updateTest {
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

    // Update: gelukt
    @Test
    public void test_Update_lied_nieuwe_waarden_Verandert_waarden_in_tabel(){
        // 1. Ga naar overzicht
        driver.get(url + "Controller?command=overzicht");
        // 2. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 3. Klik update
        driver.findElement(By.id("update1")).click();
        // 4. Juiste pagina?
        assertEquals("Edit | Liedjes", driver.getTitle());
        // 5. Verander waarden
        driver.findElement(By.id("genre")).clear();
        driver.findElement(By.id("rating")).clear();
        driver.findElement(By.id("genre")).sendKeys("abcdef");
        driver.findElement(By.id("rating")).sendKeys("2");
        driver.findElement(By.id("submit")).click();
        // 6. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        assertEquals("De aanpassing is gelukt!", driver.findElement(By.id("message")).getText());
        // 7. Waarden veranderd?
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "abcdef"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "2"));
    }

    // Update: niet gelukt
    @Test
    public void test_Update_lied_ongeldige_waarden_Geeft_boodschap(){
        // 1. Ga naar overzicht
        driver.get(url + "Controller?command=overzicht");
        // 2. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 3. Klik update
        driver.findElement(By.id("update1")).click();
        // 4. Juiste pagina?
        assertEquals("Edit | Liedjes", driver.getTitle());
        // 5. Verander waarden
        driver.findElement(By.id("genre")).clear();
        driver.findElement(By.id("genre")).sendKeys("   ");
        driver.findElement(By.id("submit")).click();
        // 6. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        assertEquals("Er was een probleem bij het aanpassen van de waarden", driver.findElement(By.id("message")).getText());
    }

    // HULP
    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst){
        for(WebElement item: items){
            if(item.getText().equalsIgnoreCase(tekst)) return true;
        }
        return false;
    }
}
