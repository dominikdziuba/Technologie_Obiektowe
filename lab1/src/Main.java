import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import collections.DataCollection;
import providers.XMLCurrencyCollectionProvider;
import data.RemoteDataProvider;
import exchange.Exchange;
import view.CurrencyView;


public class Main {
    static RemoteDataProvider provider;
    static DataCollection LastA;
    static XMLCurrencyCollectionProvider xmlProvider;
    static Exchange exchange;
    static CurrencyView view;
    public static void main(String[] args) {
        provider = new RemoteDataProvider();
        xmlProvider = new XMLCurrencyCollectionProvider();

        LastA = new DataCollection();
        exchange = new Exchange();
        view = new CurrencyView();
        try {
            String result = provider.acquireRemoteData("https://www.nbp.pl/kursy/xml/LastA.xml");
            xmlProvider.provide(result,LastA);

            view.setDataCollection(LastA);
            view.setExchange(exchange);
            view.menu();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}
