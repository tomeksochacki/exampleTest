package org.example.pages;

import org.example.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends PageObjectBase {

    @FindBy(name = "__tcfapiLocator")
    private WebElement frame;

    final String URL = "https://www.t-mobile.pl/";

    WebElement firstProductFromList;
    Double numberOfProductsInBasket = 0.0;
    Integer numberOfElements = 0;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage goToPage() {
        try {
            driver.navigate().to(URL);
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: goToPage()");
            driver.quit();
        }
        return this;
    }

    public MainPage confirmConsent() {
        try {
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label*=\"Zaakceptuj i zamknij: Wyraź zgodę na nasze przetwarzanie danych i zamknij\"]"))).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: confirmConsent()");
            driver.quit();
        }
        return this;
    }

    public MainPage expandList() {
        try {
            driver.switchTo().frame(frame);
            driver.findElement(By.xpath("//button[contains(text(),'Urządzenia')]")).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: expandList()");
            driver.quit();
        }
        return this;
    }

    public MainPage chooseSmartwatch() {
        try {
            driver.switchTo().frame(frame);
            driver.findElement(By.cssSelector("a[data-ga-ea*=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]")).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: chooseSmartwatch()");
            driver.quit();
        }
        return this;
    }

    public String getOneDeviceFromList() {
        try {
            driver.switchTo().frame(frame);
            firstProductFromList = driver.findElement(By.cssSelector("div[data-qa^=\"LST_ProductCard\"]"));
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: getOneDeviceFromList()");
            driver.quit();
        }
        return firstProductFromList.getText();
    }

    public MainPage clickFirstProduct() {
        try {
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@id=\"portal-root-bottom\"]")));
            WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(500));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
            WebElement container = driver.findElement(By.id("osAppInnerContainer"));
            WebElement main = container.findElement(By.xpath("//main[@class=\"styles__StyledProductList-sc-6f8q5n-0 ExPtB\"]"));
            WebElement grid = main.findElement(By.xpath("//div[@class=\"sc-kxCoLp fltSXB os-grid \"]"));
            WebElement simpleTable = grid.findElement(By.cssSelector("div[class^=\"styles__StyledCards-sc-176tmlw-0\"]"));
            List<WebElement> rows = simpleTable.findElements(By.cssSelector("div[data-qa^=\"LST_ProductCard\"]"));
            List<WebElement> allProducts = new ArrayList<>();
            for (int i = 0; i < 18; i++) {
                allProducts.add(rows.get(i));
            }
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id=\"snrs_chat-bubble\"]")));
            myWait.until(ExpectedConditions.elementToBeClickable(allProducts.get(0))).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: clickFirstProduct()");
            driver.quit();
        }
        return this;
    }

    public Integer getElementsCount() {
        try {
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@id=\"portal-root-bottom\"]")));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
            WebElement container = driver.findElement(By.id("osAppInnerContainer"));
            WebElement main = container.findElement(By.xpath("//main[@class=\"styles__StyledProductList-sc-6f8q5n-0 ExPtB\"]"));
            WebElement grid = main.findElement(By.xpath("//div[@class=\"sc-kxCoLp fltSXB os-grid \"]"));
            WebElement simpleTable = grid.findElement(By.cssSelector("div[class^=\"styles__StyledCards-sc-176tmlw-0\"]"));
            List<WebElement> rows = simpleTable.findElements(By.cssSelector("div[data-qa^=\"LST_ProductCard\"]"));
            numberOfElements = rows.size();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: getElementsCount()");
            driver.quit();
        }
        return numberOfElements;
    }

    public Double checkQuantityInBasket() {
        try {
            driver.switchTo().frame(frame);
            WebElement header = driver.findElement(By.cssSelector("header[class*=\"mobileBannerDisplay\"]"));
            WebElement shrankHeader = header.findElement(By.xpath("//div[@class=\"flex ml-auto lg:mt-auto group-[.shrank-header]/header:lg:mt-0\"]"));
            String productCount = shrankHeader.findElement(By.cssSelector("div[class*=\"rounded-full\"]")).getAttribute("innerText");
            numberOfProductsInBasket = Double.parseDouble(productCount);
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: checkQuantityInBasket()");
            driver.quit();
        }
        return numberOfProductsInBasket;
    }
}
