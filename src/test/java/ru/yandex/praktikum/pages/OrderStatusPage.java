package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderStatusPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By orderStatusTitle = By.xpath("//div[contains(text(), 'Статус заказа')]");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isOrderStatusPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderStatusTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}