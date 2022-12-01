import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public class NewPageTest {

  WebDriver webdriver;

  @BeforeAll
  public void setupAll(){
    System.setProperty("webdriver.chrome.driver",
        "src/test/resources/chromedriver_linux64/chromedriver.exe");
  }

  @BeforeEach
  public void setup(){
    webdriver = new ChromeDriver();
    webdriver.manage().window().maximize();
  }

  @AfterEach
  public void closeDriver(){
    webdriver.close();
  }

  //Site do Condomínio Vivenda Beira Rio
  @Test
  public void openNewPage(){
    webdriver.get("https://www.vivendabeirario.com/");
    Assertions.assertEquals("https://www.vivendabeirario.com/", webdriver.getCurrentUrl());
  }

  @Test
  public void openNemPageFalse() {
    webdriver.get("https://www.vivendabeirario.com/");
    boolean tagTitle = webdriver.getTitle().equalsIgnoreCase("Condomínio Vivenda Beira Rio");
    Assertions.assertFalse(tagTitle);
  }

  @Test
  public void FindBoletoButton(){
    webdriver.get("https://www.vivendabeirario.com/");
    WebElement boletoButton = webdriver.findElement(
        By.className("_1QoRU"));
    boletoButton.click();
  }

  @Test
  public void FindLink(){
    webdriver.get("https://www.vivendabeirario.com/");
    WebElement Link = webdriver.findElement(
            By.xpath("/html/body/div/div/div[3]/div/main/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/div[10]/a/wix-image/img"));
  }

  @Test
  public void MoveToListItemMenu(){
    webdriver.get("https://www.vivendabeirario.com/");
    WebElement menuLink = webdriver.findElement(By.xpath("/html/body/div/div/div[3]/div/header/div/div[2]/div[2]/div/div/wix-dropdown-menu/nav/ul/li[3]"));
    new Actions(webdriver).moveToElement(menuLink).perform();
  }

  @Test
  public void FindLinks(){
    webdriver.get("https://www.vivendabeirario.com/");
    List<WebElement> elements = webdriver.findElements(By.tagName("a"));
    System.out.println("Number of elements:" +elements.size());
  }

  //Site da Prefeitura de Altinho

  @Test
  public void openPrefeituraPage(){
    webdriver.get("http://altinho.pe.gov.br/v1");
    Assertions.assertEquals("https://www.vivendabeirario.com/",
            webdriver.getCurrentUrl());
  }

  @Test
  public void assertTruePrefeituraPage (){
    webdriver.get("http://altinho.pe.gov.br/v1/");
    Actions actions = new Actions(webdriver);
    WebElement servicesLink = webdriver.findElement(By.xpath("/html/body/div[8]/div[3]/div/div[1]/div[2]/div[1]/p[4]/a"));
    actions.moveToElement(servicesLink).perform();
    Assertions.assertTrue(webdriver.getPageSource().contains(servicesLink.getAttribute("href")));
  }

  @Test
  public void searchInPrefeituraPage (){
    webdriver.get("http://altinho.pe.gov.br/v1/");
    WebElement search = webdriver.findElement(By.id("s"));
    search.sendKeys("vacina");
    search.submit();
  }

  @Test
  public void openPrefeituraPageFalse() {
    webdriver.get("http://altinho.pe.gov.br/v1/");
    boolean verifyTagTitle = webdriver.getTitle().equalsIgnoreCase("Prefeitura de Altinho");
    Assertions.assertFalse(verifyTagTitle);
  }

  //Site Sebrae
  @Test
  public void selectIndexElement() {
    webdriver.get("https://sebrae.com.br/sites/PortalSebrae");
    Select menuItem = new Select(webdriver.findElement(By.id("selectModalLocation")));
    menuItem.selectByIndex(1);
  }
}


