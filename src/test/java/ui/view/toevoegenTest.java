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

public class toevoegenTest {
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

    // Succes: nieuw lied
    @Test
    public void test_Toevoegen_met_geldige_waarden_Voegt_toe_aan_tabel(){
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes" , driver.getTitle());
        // 3. Vul velden in
        voegItemToe("titel", "artist", "genre", 3);
        // 4. Juiste pagina?
        assertEquals("Overzicht | Liedjes", driver.getTitle());
        // 5. Item in lijst?
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "titel"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "artist"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "genre"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), "3"));
    }

    // Succes: lied al in lijst

    // Fail: titel leeg
    @Test
    public void test_Toevoegen_met_ongeldige_titel_Geeft_foutboodschap() {
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 3. Vul velden in
        voegItemToe("", "artist", "genre", 3);
        // 4. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 5. Foutboodschap
        assertEquals("Titel mag niet leeg zijn", driver.findElement(By.id("error")).getText());
    }

    // Fail: artist leeg
    @Test
    public void test_Toevoegen_met_ongeldige_artist_Geeft_foutboodschap() {
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 3. Vul velden in
        voegItemToe("titel", "      ", "genre", 3);
        // 4. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 5. Foutboodschap
        assertEquals("Artist mag niet leeg zijn", driver.findElement(By.id("error")).getText());
    }

    // Fail: genre leeg
    @Test
    public void test_Toevoegen_met_ongeldige_genre_Geeft_foutboodschap() {
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 3. Vul velden in
        voegItemToe("titel", "artist", "   ", 3);
        // 4. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 5. Foutboodschap
        assertEquals("Genre mag niet leeg zijn", driver.findElement(By.id("error")).getText());
    }

    // Fail: rating < 0
    @Test
    public void test_Toevoegen_met_rating_negatief_Geeft_foutboodschap() {
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 3. Vul velden in
        voegItemToe("titel", "artist", "genre", -2);
        // 4. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 5. Foutboodschap
        assertEquals("Rating moet tussen 0 en 10 zijn", driver.findElement(By.id("error")).getText());
    }

    // Fail: rating > 10
    @Test
    public void test_Toevoegen_met_rating_groter_dan_10_Geeft_foutboodschap() {
        // 1. Ga naar toevoegen
        driver.get(url + "Controller?command=toevoegen");
        // 2. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 3. Vul velden in
        voegItemToe("titel", "artist", "genre", 12);
        // 4. Juiste pagina?
        assertEquals("Toevoegen | Liedjes", driver.getTitle());
        // 5. Foutboodschap
        assertEquals("Rating moet tussen 0 en 10 zijn", driver.findElement(By.id("error")).getText());
    }

    // HULP
    private void voegItemToe(String s1, String s2, String s3, int s4){
        driver.findElement(By.id("titel")).sendKeys(s1);
        driver.findElement(By.id("artist")).sendKeys(s2);
        driver.findElement(By.id("genre")).sendKeys(s3);
        driver.findElement(By.id("rating")).sendKeys(s4 + "");
        driver.findElement(By.id("submit")).click();
    }

    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst){
        for(WebElement item: items){
            if(item.getText().equalsIgnoreCase(tekst)) return true;
        }
        return false;
    }

}
