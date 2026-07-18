from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from pages.base_page import BasePage

class OrderStatusPage(BasePage):
    ORDER_STATUS_TITLE = (By.XPATH, "//div[contains(text(), 'Статус заказа')]")

    def is_order_status_page(self):
        try:
            self.wait.until(EC.visibility_of_element_located(self.ORDER_STATUS_TITLE))
            return True
        except:
            return False
