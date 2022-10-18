package exchange;

import currency.Currency;

public class Exchange {
    public double exchange(Currency src, Currency tgt, double amt) {
        double exchanged = (amt * src.getRate() / src.getFactor()) / (tgt.getRate() / tgt.getFactor());
        return exchanged;
    }
}
