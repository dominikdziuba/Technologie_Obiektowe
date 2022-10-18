package collections;
import currency.Currency;
import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    protected List<Currency> currencyList;

    public DataCollection(){
        this.currencyList = new ArrayList<>();
    }


    public String ToString() {
        String dataString=" ";
        for (Currency element : currencyList) {
            dataString=dataString+element.getName()+"\t";
            dataString=dataString+element.getCode()+"\t";
            dataString=dataString+element.getFactor()+"\t";
            dataString=dataString+element.getRate()+"\n";

        }

        return dataString.toString();
    }

    public List<Currency> getCurrencyList() {

        return currencyList;
    }
    public Currency getCurrencyByCode(Currency currency) {
        Currency searchedCurrency = new Currency();
        for (Currency element : currencyList)
            if (element.getCode().equals(currency.getCode()))
                searchedCurrency = element;

        return searchedCurrency;
    }
}
