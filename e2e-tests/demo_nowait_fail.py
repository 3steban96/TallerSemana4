import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.common.exceptions import NoSuchElementException

def main():
    print("❌ Iniciando demostración: Fallo SIN Esperas (No Waits)")
    print("------------------------------------------------------------")
    print("Este script intentará buscar las tareas inmediatamente.")
    print("Como el backend tarda 3 segundos en responder, arrojará un NoSuchElementException.")
    print("------------------------------------------------------------\n")
    
    options = webdriver.ChromeOptions()
    # options.add_argument('--headless')
    
    try:
        driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    except Exception as e:
        driver = webdriver.Chrome(options=options)

    try:
        # NO CONFIGURAMOS WAIT
        driver.implicitly_wait(0) # 0 Segundos de espera

        print("🌐 Navegando a http://localhost:5173 ...")
        driver.get("http://localhost:5173")

        print("👀 Buscando elementos de la tabla inmediatamente...")
        
        # Esto va a fallar inmediatamente
        task_titles = driver.find_elements(By.CSS_SELECTOR, "tbody tr td strong")
        
        # find_elements devuelve una lista vacía si no encuentra nada en vez de excepción, 
        # así que para forzar la excepción NoSuchElementException como querías, 
        # usaremos find_element (singular).
        print("Intentando encontrar el primer elemento de la tarea usando find_element...")
        first_task = driver.find_element(By.CSS_SELECTOR, "tbody tr td strong")
        
        print("⚠️ Si ves esto, algo anduvo mal con la prueba, debió haber fallado.")
        
    except NoSuchElementException as e:
        print("\n" + "="*50)
        print("🚨 ¡ERROR ESPERADO! 🚨")
        print(f"Excepción capturada: {type(e).__name__}")
        print("El elemento no estaba en el DOM al instante de buscarlo.")
        print("La página no ha cargado los datos del backend aún (demora 3s).")
        print("="*50 + "\n")
    except Exception as e:
        print(f"❌ Ocurrió un error inesperado: {e}")
    finally:
        print("🛑 Cerrando el navegador en 3 segundos...")
        time.sleep(3)
        driver.quit()

if __name__ == "__main__":
    main()
