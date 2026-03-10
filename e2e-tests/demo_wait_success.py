import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

def main():
    print("✅ Iniciando demostración: Éxito CON Esperas Implícitas")
    print("------------------------------------------------------------")
    print("Este script configurará una espera implícita de 10 segundos.")
    print("Aunque el backend tarda 3 segundos, Selenium esperará pacientemente.")
    print("------------------------------------------------------------\n")
    
    options = webdriver.ChromeOptions()
    # options.add_argument('--headless')
    
    try:
        driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    except Exception as e:
        driver = webdriver.Chrome(options=options)

    try:
        # CONFIGURAR ESPERA IMPLÍCITA
        print("⏳ Configurando espera implícita de 10 segundos...")
        driver.implicitly_wait(10)

        print("🌐 Navegando a http://localhost:5173 ...")
        driver.get("http://localhost:5173")

        print("👀 Buscando el primer elemento de la tabla... Selenium esperará hasta que aparezca.")
        start_time = time.time()
        
        # Esto esperará internamente hasta 10 segundos 
        first_task = driver.find_element(By.CSS_SELECTOR, "tbody tr td strong")
        
        end_time = time.time()
        
        print("\n" + "="*50)
        print("🎉 ¡ÉXITO! 🎉")
        print(f"Elemento encontrado después de {end_time - start_time:.2f} segundos.")
        print(f"Primera tarea encontrada: '{first_task.text}'")
        print("El implicit wait funcionó maravillosamente.")
        print("="*50 + "\n")
        
    except Exception as e:
        print(f"❌ Ocurrió un error: {e}")
    finally:
        print("🛑 Cerrando el navegador en 3 segundos...")
        time.sleep(3)
        driver.quit()

if __name__ == "__main__":
    main()
