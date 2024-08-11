package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BaseStepDefs;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AddingDeviceStepsDefinitions extends BaseStepDefs {

    private static final Logger log = LoggerFactory.getLogger(AddingDeviceStepsDefinitions.class);

    @Given("Jako użytkownik otwieram przeglądarkę chrome")
    public void jako_użytkownik_otwieram_przeglądarkę_chrome() {
        Assertions.assertTrue(mainPage.driver != null);
        log.info("Otwieranie przeglądarki.");
    }

    @When("Przechodzę na stronę https: www.t-mobile.pl")
    public void przechodzęNaStronęHttpsWwwTMobilePl() {
        mainPage.goToPage().confirmConsent();
        log.info("Otwieranie strony www.t-mobile.pl");
    }

    @Then("Strona główna jest widoczna")
    public void strona_główna_jest_widoczna() {
        Assertions.assertEquals("https://www.t-mobile.pl/", mainPage.driver.getCurrentUrl());
        log.info("Strona główna jest widoczna.");
    }

    @When("Z górnej belki wybieram Urządzenia")
    public void z_górnej_belki_wybierz_urządzenia() {
        mainPage.expandList();
        log.info("Wybieranie zakładki Urządzenia.");
    }

    @Then("Jest widoczna rozwijana lista")
    public void jest_widoczna_rozwijana_lista() {
        Assertions.assertEquals("Bez abonamentu", mainPage.driver.findElement(By.xpath("//p[contains(text(),'Bez abonamentu')]")).getText());
        log.info("Pokazanie listy rozwijalnej z propozycjami ofert i urządzeniami.");
    }

    @When("Klikam \"Bez abonamentu\" z kolumny \"Smartwatche i opaski\"")
    public void klikam_bez_abonamentu_z_kolumny_smartwatche_i_opaski() {
        mainPage.chooseSmartwatch();
        log.info("Kliknięcie w Bez abonamentu - Smartwatche.");
    }

    @Then("Jest widoczna lista smartwatchy")
    public void jest_widoczna_lista_smartwatchy() {
        Assertions.assertEquals(",,,\n" +
                "MULTISIM\n" +
                "Samsung Galaxy Watch6 Classic LTE 43mm\n" +
                "Pamięć 16 GB\n" +
                "Cena urządzenia płatna w 24 ratach 0%\n" +
                "1999 zł", mainPage.getOneDeviceFromList());
        Assertions.assertTrue(mainPage.getElementsCount().equals(18));
        log.info("Pokazanie listy urządzeń.");

    }

    @When("Klikam w pierwszy element z listy")
    public void klikam_w_pierwszy_element_z_listy() {
        mainPage.clickFirstProduct();
        log.info("Kliknięcie w pierwsze urządzenie z przedstawionej listy.");
    }

    @Then("Jest widoczna strona produktu")
    public void jest_widoczna_strona_produktu() {
        Assertions.assertTrue(productPage.isProductPage());
        log.info("Otwieranie strony wybranego produktu.");
    }

    @When("Dodaję produkt do koszyka")
    public void dodaję_produkt_do_koszyka() {
        productPage.getPrices().addToBasket();
        log.info("Kliknięcie przycisku Dodaj do koszyka.");
    }

    @Then("Jest widoczna strona Twój koszyk")
    public void jest_widoczna_strona_twój_koszyk() {
        Assertions.assertTrue(basketPage.isBasketPage());
        log.info("Otwieranie strony koszyka.");
    }

    @And("Kwoty Cena na start oraz Rata miesięczna zgadzają się z kwotami z poprzedniej strony")
    public void kwoty_cena_na_start_oraz_rata_miesięczna_zgadzają_się_z_kwotami_z_poprzedniej_strony() {
        Assertions.assertTrue(basketPage.checkPrices());
        log.info("Sprawdzenie kwoty z poprzedniej strony z kwotami w koszyku.");
    }

    @Then("Przechodzę na stronę główną T-Mobile")
    public void przechodzę_na_stronę_główną_t_mobile() {
        basketPage.goToMainPage();
        log.info("Powrót do strony głównej.");
    }

    @And("Jest widoczna strona główna. W prawym górnym rogu widoczna jest ikonka koszyka z liczbą produktów w koszyku.")
    public void jest_widoczna_strona_główna_w_prawym_górnym_rogu_widoczna_jest_ikonka_koszyka_z_liczbą_produktów_w_koszyku() {
        Assertions.assertEquals(1.0, mainPage.checkQuantityInBasket());
        log.info("Otwieranie strony głównej oraz porównanie ilości produktów.");
        mainPage.driver.quit();
    }
}
