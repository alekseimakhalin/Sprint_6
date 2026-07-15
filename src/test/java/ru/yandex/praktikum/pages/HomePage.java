package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonBottom = By.xpath("//div[contains(@class, 'Home_FinishButton__1_cWm')]/button");
    private final By faqQuestions = By.className("accordion__button");
    private final By logoSamokat = By.className("Header_LogoScooter__3lsAR");
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void acceptCookies() {
        try {
            WebElement cookie = driver.findElement(cookieButton);
            if (cookie.isDisplayed()) {
                cookie.click();
            }
        } catch (Exception e) {
            // Куки уже приняты
        }
    }

    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void clickFAQQuestion(int index) throws Exception {
        // Ждем загрузки всех вопросов
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(faqQuestions));
        WebElement question = driver.findElements(faqQuestions).get(index);
        // Скроллим к элементу по центру
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        Thread.sleep(500);
        // Кликаем через JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);
    }

    public String getFAQAnswer(int index) {
        By answerLocator = By.xpath("//div[@id='accordion__panel-" + index + "']/p");
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

    public void clickSamokatLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoSamokat)).click();
    }

    public void clickYandexLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoYandex)).click();
    }
}