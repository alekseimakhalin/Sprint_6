from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from pages.base_page import BasePage
import time

class HomePage(BasePage):
    # Локаторы
    COOKIE_BUTTON = (By.ID, "rcc-confirm-button")
    ORDER_BUTTON_TOP = (By.CLASS_NAME, "Button_Button__ra12g")
    ORDER_BUTTON_BOTTOM = (By.XPATH, "//div[contains(@class, 'Home_FinishButton__1_cWm')]/button")
    FAQ_QUESTIONS = (By.CLASS_NAME, "accordion__button")
    LOGO_SAMOKAT = (By.CLASS_NAME, "Header_LogoScooter__3lsAR")
    LOGO_YANDEX = (By.CLASS_NAME, "Header_LogoYandex__3TSOI")

    def open(self):
        self.driver.get("https://qa-scooter.praktikum-services.ru/")

    def accept_cookies(self):
        try:
            cookie_button = self.driver.find_element(*self.COOKIE_BUTTON)
            if cookie_button.is_displayed():
                cookie_button.click()
        except:
            pass

    def click_order_button_top(self):
        self.click_element(self.ORDER_BUTTON_TOP)

    def click_order_button_bottom(self):
        button = self.driver.find_element(*self.ORDER_BUTTON_BOTTOM)
        self.scroll_to_element(button)
        self.wait.until(EC.element_to_be_clickable(self.ORDER_BUTTON_BOTTOM))
        button.click()

    def click_faq_question(self, index):
        questions = self.find_elements(self.FAQ_QUESTIONS)
        question = questions[index]
        self.scroll_to_element(question)
        time.sleep(0.5)
        self.driver.execute_script("arguments[0].click();", question)

    def get_faq_answer(self, index):
        answer_locator = (By.XPATH, f"//div[@id='accordion__panel-{index}']/p")
        answer = self.wait.until(EC.visibility_of_element_located(answer_locator))
        return answer.text

    def click_samokat_logo(self):
        self.click_element(self.LOGO_SAMOKAT)

    def click_yandex_logo(self):
        self.click_element(self.LOGO_YANDEX)
