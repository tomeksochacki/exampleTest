@AddingDeviceTest
Feature: Scenariusz testowy dodania jednego produktu do koszyka

  @dodanieJednegoProduktu
  Scenario: Dodanie produktu
    Given Jako użytkownik otwieram przeglądarkę chrome
    When Przechodzę na stronę https: www.t-mobile.pl
    Then Strona główna jest widoczna
    When Z górnej belki wybieram Urządzenia
    Then Jest widoczna rozwijana lista
    When Klikam "Bez abonamentu" z kolumny "Smartwatche i opaski"
    Then Jest widoczna lista smartwatchy
    When Klikam w pierwszy element z listy
    Then Jest widoczna strona produktu
    When Dodaję produkt do koszyka
    Then Jest widoczna strona Twój koszyk
    And Kwoty Cena na start oraz Rata miesięczna zgadzają się z kwotami z poprzedniej strony
    Then Przechodzę na stronę główną T-Mobile
    And Jest widoczna strona główna. W prawym górnym rogu widoczna jest ikonka koszyka z liczbą produktów w koszyku.