import pytest
from selenium import webdriver
from selenium.webdriver.firefox.service import Service
from pages.home_page import HomePage
import os

@pytest.fixture
def driver():
    # Используем локальный geckodriver.exe
    gecko_path = os.path.join(os.path.dirname(os.path.dirname(__file__)), "geckodriver.exe")
    service = Service(gecko_path)
    
    # ПРАВИЛЬНЫЙ путь к Firefox (замени на тот, который нашла)
    options = webdriver.FirefoxOptions()
    options.binary_location = r"C:\Program Files\Mozilla Firefox\firefox.exe"  # ИЛИ C:\Program Files (x86)\Mozilla Firefox\firefox.exe
    
    driver = webdriver.Firefox(service=service, options=options)
    driver.maximize_window()
    driver.implicitly_wait(5)
    yield driver
    driver.quit()

@pytest.fixture
def home_page(driver):
    home_page = HomePage(driver)
    home_page.open()
    home_page.accept_cookies()
    return home_page
