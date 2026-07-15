package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@Story("Тесты навигации")
public class NavigationTest extends BaseTest {

    @Test
    @Description("Проверка перехода на главную страницу по клику на логотип 'Самокат'")
    public void clickSamokatLogoTest() {
        homePage.clickOrderButtonTop();
        homePage.clickSamokatLogo();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isHomePage = wait.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));
        assertTrue("Не удалось перейти на главную страницу", isHomePage);
    }

    @Test
    @Description("Проверка перехода на Дзен по клику на логотип 'Яндекс'")
    public void clickYandexLogoTest() {
        homePage.clickYandexLogo();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isDzenOpened = wait.until(ExpectedConditions.urlContains("dzen.ru"));
        assertTrue("Не удалось перейти на Дзен", isDzenOpened);

        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}