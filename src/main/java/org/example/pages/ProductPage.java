package org.example.pages;

import org.example.PageObjectBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends PageObjectBase {

    @FindBy(name = "__tcfapiLocator")
    private WebElement frame;

    Boolean isRightPage = false;
    static Double startFee = 0.0;
    static Double monthlyFee = 0.0;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isProductPage() {
        try {
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"sc-jqUVSM cVAcTC dt_overlay\"]")));
            WebElement mainElement = driver.findElement(By.xpath("//main[@class=\"styles__StyledProductDetailed-sc-bkaa9n-1 kBEUKW\"]"));
            WebElement gridElement = mainElement.findElement(By.xpath("//div[@class=\"sc-kGhOqx gACUdf os-grid \"]"));
            WebElement sectionElement = gridElement.findElement(By.xpath("//section[@class=\"styles__StyledProductSignatureWrapper-sc-1aywbtd-0 cxDaEU\"]"));
            String textFromSection = sectionElement.findElement(By.xpath("//h1[@data-qa=\"PRD_ProductName\"]")).getAttribute("innerText");
            if (textFromSection.equals("Samsung Galaxy Watch6 Classic LTE 43mm")) {
                isRightPage = true;
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: isProductPage()");
            driver.quit();
        }
        return isRightPage;
    }

    public ProductPage addToBasket() {
        try {
            driver.switchTo().frame(frame);
            WebElement grid = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"sc-kGhOqx iprXOr os-grid \"]")));
            WebElement gridChild = grid.findElement(By.xpath("//span[@class=\"styles__StyledHideElementConditionally-sc-9zidlv-0 grid-child\"]"));
            gridChild.findElement(By.cssSelector("div[class*=\"style_ctaSection\"]")).click();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: addToBasket()");
            driver.quit();
        }
        return this;
    }

    public ProductPage getPrices() {
        try {
            driver.switchTo().frame(frame);
            WebElement gridChild = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"styles__StyledHideElementConditionally-sc-9zidlv-0 grid-child\"]")));
            String startPrice = gridChild.findElement(By.xpath("//div[contains(text(),'79 zł')]")).getAttribute("innerText");
            String startPriceInString = startPrice.substring(0, 2);
            startFee = Double.parseDouble(startPriceInString);
            String monthlyPrice = gridChild.findElement(By.xpath("//div[contains(text(),'80 zł')]")).getAttribute("innerText");
            String monthlyPriceInString = monthlyPrice.substring(0, 2);
            monthlyFee = Double.parseDouble(monthlyPriceInString);
        } catch (Exception e) {
            System.out.println("Wystąpił błąd w metodzie: getPrices()");
            driver.quit();
        }
        return this;
    }
}
