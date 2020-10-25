
import Account.Account;
import Database.Database;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyAppMain {
    public static void main(String[] args) {
        Database.DATABASE_ON();
        Database.WRITEALLDATA();
    }
}
