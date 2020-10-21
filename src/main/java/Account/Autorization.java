package Account;

import java.util.Scanner;

public class Autorization {

    public static boolean autorization(){

        return false;
    }
    static void printResultAutorization(boolean bool){
        if (bool == true){
            System.out.println("Вы успешно авторизовались!");
        }
        else {
            System.out.println("Произошла ошибка!");
        }
    }

}
