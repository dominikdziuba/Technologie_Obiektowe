package view;

import java.util.Scanner;
import exchange.Exchange;
import collections.DataCollection;
import currency.Currency;
public class CurrencyView {
    Exchange ex;
    DataCollection data;
    Scanner sc = new Scanner(System.in);

    public void setExchange(Exchange exchange) {
        this.ex = exchange;
    }

    public void setDataCollection(DataCollection collection) {
        this.data = collection;
    }

    public void ViewAll(DataCollection coll) {
        System.out.println(coll.ToString());
    }


    public Currency StringToCurrency(String code) {
        Currency chosenCurrency = new Currency();
        chosenCurrency.setCode(code);
        return data.getCurrencyByCode(chosenCurrency);
    }


    public Currency ChooseCurrency(String label) {
        System.out.println("Podaj kod waluty, " + label);
        String code = sc.nextLine();
        return StringToCurrency(code);
    }



    public void exchange() {
        Currency previousCurrency = ChooseCurrency("ktora posiadasz");
        System.out.println("Ile chcesz jej wymienić?");
        String cashString = sc.nextLine();
        double cash = Double.parseDouble(cashString);
        Currency newCurrency = ChooseCurrency("na którą chcesz wymienić");
        double outcome = this.ex.exchange(previousCurrency, newCurrency, cash);
        System.out.println("Posiadasz " + outcome + " " + newCurrency.getName());
    }


    public void menu() {
        System.out.println("0 - Wyświetl kursy\n 1 - Wymień walutę\n 2 - Zakończ\n Wybierz jedną z powyższych opcji: ");
        Scanner sc = new Scanner(System.in);
        int choice_int = sc.nextInt();
        switch (choice_int) {
            case 0:
                ViewAll(data);
                System.out.println("1 - Wymień walutę\n 2 - Zakończ\nWybierz jedną z powyższych opcji: ");
                choice_int = sc.nextInt();
                switch (choice_int) {
                    case 1:
                        exchange();
                        break;
                    case 2:
                        System.out.println("Dziękujemy za skorzystanie z naszej aplikacji.");
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór.");
                        break;
                }
                break;
            case 1:
                exchange();
                break;
            case 2:
                System.out.println("Dziękujemy za skorzystanie z naszej aplikacji.");
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
                break;
        }
    }
}
