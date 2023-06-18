package destino;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes web do modulo de destino")
public class DestinoTest {
    private WebDriver navegador;
    @Test
    public void Testando(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Maximizar a tela
        this.navegador.manage().window().maximize();

        //Definindo um tempo de espera de 05 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a lojinha Web
        this.navegador.get("https://www.trivago.com.br/");

    }
}
