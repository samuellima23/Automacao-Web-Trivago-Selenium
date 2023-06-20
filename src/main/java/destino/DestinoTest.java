package destino;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes web do modulo de destino")
public class DestinoTest {
    private WebDriver navegador;
    @BeforeEach
    public void beforeEach(){
        //Abrir o navegador
        WebDriverManager.chromedriver().setup();
        this.navegador = new ChromeDriver();

        //Maximizar a tela
        this.navegador.manage().window().maximize();

        //Definindo um tempo de espera de 05 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

        //Navegar para o site trivago
        this.navegador.get("https://www.trivago.com.br/pt-BR");

    }

    @Test
    @DisplayName("Busca por nome da cidade Manaus e ordenação por avaliação e sugestão")
    public void testBuscaPorManauseOrdenaPorAvaliacaoESugestao() {
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(40));

        navegador.findElement(By.id("onetrust-accept-btn-handler")).click();

        navegador.findElement(By.id("input-auto-complete")).click();
        navegador.findElement(By.id("input-auto-complete")).clear();

        navegador.findElement(By.id("input-auto-complete")).sendKeys("Man");

        navegador.findElement(By.xpath("//span[text()='Pesquisar']")).click();

        WebElement elementoLista = navegador.findElement(By.xpath("//span[text()='Cidade · Amazonas, Brasil']"));
        elementoLista.click();

        //navegador.findElement(By.cssSelector("button[data-testid='calendar-button-close']")).click();
        navegador.findElement(By.xpath("//span[text()='Pesquisar']")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='A processar o seu pedido. Se esta página não atualizar automaticamente, volte a submeter o seu pedido.']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("sec-overlay")));

        navegador.findElement(By.id("sorting-selector")).click();

        navegador.findElement(By.cssSelector("option[value='6']")).click();

        WebElement accommodationList = navegador.findElement(By.cssSelector("ol[data-testid='accommodation-list']"));

        WebElement firstItem = accommodationList.findElement(By.cssSelector("li[data-testid='accommodation-list-element']"));

        // Obter o nome do primeiro item
        WebElement nameElement = firstItem.findElement(By.cssSelector("button[data-testid='item-name']"));
        String name = nameElement.getText();

        // Obter a avaliação do primeiro item
        WebElement ratingElement = firstItem.findElement(By.cssSelector("span[itemprop='ratingValue']"));
        String rating = ratingElement.getText();

        // Obter o valor do primeiro item
        WebElement priceElement = firstItem.findElement(By.cssSelector("p[itemprop='price']"));
        String price = priceElement.getText();

        // Verificar o nome do primeiro item
        Assertions.assertEquals(name, name);
        System.out.println(name);

        // Verificar a avaliação do primeiro item
        Assertions.assertEquals(rating, rating);
        System.out.println(rating);

        // Verificar o preço do primeiro item
        Assertions.assertEquals(price, price);
        System.out.println(price);


    }
}
