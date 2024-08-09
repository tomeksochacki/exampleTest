package org.example;

import org.example.pages.BasketPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;

public class BaseStepDefs {

    public RunCucumberTest run = new RunCucumberTest();

    public MainPage mainPage = new MainPage(run.getDriver());
    public ProductPage productPage = new ProductPage(mainPage.driver);
    public BasketPage basketPage = new BasketPage(mainPage.driver);
}
