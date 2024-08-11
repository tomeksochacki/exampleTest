package org.example.pages;

import org.example.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPage extends PageObjectBase {

    @FindBy(name = "__tcfapiLocator")
    private WebElement frame;

    Double startPriceInBasket = 0.0;
    Double monthlyPriceInBasket = 0.0;
    Boolean areEqual = false;
    Boolean isBasket = false;

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkPrices() {
        try {
            driver.switchTo().frame(frame);
            WebElement summary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"styles__StyledBody-sc-1dddh9u-4 hMdXTE\"]")));
            String sPrice = summary.findElement((By.xpath("//span[contains(text(),'79')]"))).getAttribute("innerText");
            WebElement summaryMonthly = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@class=\"styles__StyledSummaryMonthly-sc-17n1tgk-0 cLJECr summaryMonthly\"]")));
            startPriceInBasket = Double.parseDouble(sPrice);
            String mPrice = summaryMonthly.findElement(By.xpath("//span[contains(text(),'80')]")).getAttribute("innerText");
            monthlyPriceInBasket = Double.parseDouble(mPrice);

            if (startPriceInBasket.equals(ProductPage.startFee) & monthlyPriceInBasket.equals(ProductPage.monthlyFee)) {
                System.out.println(ProductPage.startFee);
                System.out.println(ProductPage.monthlyFee);
                areEqual = true;
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: checkPrices()");
            driver.quit();
        }
        return areEqual;
    }

    public Boolean isBasketPage() {
        try {
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"sc-idiyUo hWjlEG dt_loader overlayType_secondary loaderSize_medium\"]")));
            WebElement basketContent = driver.findElement(By.xpath("//section[@class=\"basketContent\"]"));
            String textFromElement = basketContent.findElement(By.xpath("//h1[contains(text(), 'Twój koszyk')]")).getAttribute("innerText");

            if (textFromElement.equals("Twój koszyk")) {
                isBasket = true;
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: isBasketPage()");
            driver.quit();
        }
        return isBasket;
    }

    public BasketPage goToMainPage() {
        try {
            driver.switchTo().frame(frame);
            driver.findElement(By.xpath("//img[@alt=\"T-Mobile - przejdź na stronę główną\"]")).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: goToMainPage()");
            driver.quit();
        }
        return this;
    }
}

