package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class WaitSuccessDemo {
    public static void main(String[] args) {
        System.out.println("Iniciando demostracion: Exito CON Esperas Implicitas");
        System.out.println("------------------------------------------------------------");
        System.out.println("Este script configurara una espera implicita de 10 segundos.");
        System.out.println("Aunque el backend tarda 3 segundos, Selenium esperara pacientemente.");
        System.out.println("------------------------------------------------------------\n");
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        
        try {
            // CONFIGURAR ESPERA IMPLÍCITA
            System.out.println("Configurando espera implicita de 10 segundos...");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("Navegando a http://localhost:5173 ...");
            driver.get("http://localhost:5173");

            System.out.println("Buscando el primer elemento de la tabla... Selenium esperara hasta que aparezca.");
            long startTime = System.currentTimeMillis();
            
            // Esto esperará internamente hasta 10 segundos
            WebElement firstTask = driver.findElement(By.cssSelector("tbody tr td strong"));
            
            long endTime = System.currentTimeMillis();
            
            System.out.println("\n==================================================");
            System.out.println("¡Exito! ");
            System.out.println(String.format("Elemento encontrado despues de %.2f segundos.", (endTime - startTime) / 1000.0));
            System.out.println("Primera tarea encontrada: '" + firstTask.getText() + "'");
            System.out.println("El implicit wait funciono maravillosamente.");
            System.out.println("==================================================\n");
            
        } catch (Exception e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        } finally {
            try {
                System.out.println("Cerrando el navegador en 3 segundos...");
                Thread.sleep(3000);
            } catch (InterruptedException ie) {}
            driver.quit();
        }
    }
}
