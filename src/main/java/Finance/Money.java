package Finance;

public class Money {
    private final Currency dollar = Currency.DOLLAR;
    private final Currency euro = Currency.EURO;
    private final Currency rub = Currency.RUB;
    private final Currency tenge = Currency.TENGE;
    private final Currency bitcoin = Currency.BITCOIN;
    private Currency currency;

    public void settingCurrency(Currency cur){
        if (cur == dollar) this.currency = dollar;
        else if (cur == euro) this.currency = euro;
        else if (cur == rub) this.currency = rub;
        else if (cur == tenge) this.currency = tenge;
        else if (cur == bitcoin) this.currency = bitcoin;

    }

}
