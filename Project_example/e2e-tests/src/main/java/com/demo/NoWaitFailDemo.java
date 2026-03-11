package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class NoWaitFailDemo {
    public static void main(String[] args) {
        System.out.println("Iniciando demostracion: Fallo SIN Esperas (No Waits)");
        System.out.println("------------------------------------------------------------");
        System.out.println("Este script intentara buscar las tareas inmediatamente.");
        System.out.println("Como el backend tarda 3 segundos en responder, arrojara un NoSuchElementException.");
        System.out.println("------------------------------------------------------------\n");
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        
        try {
            // NO CONFIGURAMOS WAIT
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

            System.out.println("Navegando a http://localhost:5173 ...");
            driver.get("http://localhost:5173");

            System.out.println("Buscando el primer elemento de la tabla de tareas...");
            
            try {
                WebElement task = driver.findElement(By.cssSelector("tbody tr td strong"));
                System.out.println("Si ves esto, algo anduvo mal con la prueba, debio haber fallado.");
            } catch (NoSuchElementException e) {
                System.out.println("\n==================================================");
                System.out.println("ERROR ESPERADO! ");
                System.out.println("Excepcion capturada: NoSuchElementException");
                System.out.println("El elemento no estaba en el DOM al instante de buscarlo.");
                System.out.println("La pagina no ha cargado los datos del backend aun (demora 3s).");
                System.out.println("==================================================\n");
            }
            
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        } finally {
            try {
                System.out.println("Cerrando el navegador en 3 segundos...");
                Thread.sleep(3000);
            } catch (InterruptedException ie) {}
            driver.quit();
        }
    }
}
