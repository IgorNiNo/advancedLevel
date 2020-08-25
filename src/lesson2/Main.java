package lesson2;

import lesson2.MyArrayException.MyArrayDataException;
import lesson2.MyArrayException.MyArraySizeException;

public class Main {
    static int line;
    static int column;
    static String value;
    static int LENGTH_ARR = 4;

    public static void main(String[] args) {

        String[][] strArr = {
                {"123", "234", "345", "456"},
//                {"123", "234", "345", "ahu456"},
                {"1234", "2345", "3456", "4567"},
                {"12", "23", "34", "45"},
//                {"12", "23", "34"},
                {"12345", "23456", "34567", "45678"},
        };

        try {
            arrayAnalyzer(strArr, LENGTH_ARR);
        } catch (MyArraySizeException e) {
            e.info();
        }catch (NumberFormatException e) {
            MyArrayDataException myArrayDataException = new MyArrayDataException(line, column, value);
            myArrayDataException.info();
        }
        System.out.println();
        System.out.println("Успешное завершение метода main");
    }

    static void arrayAnalyzer(String[][] str, int lengthOk) throws MyArraySizeException, NumberFormatException {
        boolean arrOk = true;

        if(str.length == lengthOk) {
            for (int i = 0; i < str.length; i++) {
                if(str[i].length != lengthOk) {
                    arrOk = false;
                    break;
                }
             }
        } else {
            arrOk = false;
        }

        if(!arrOk) {
            MyArraySizeException myArraySizeException = new MyArraySizeException(lengthOk, lengthOk);
            throw myArraySizeException;
        } else {
            int summOfElements = 0;
            for (int i = 0; i < str.length; i++) {
                for (int j = 0; j < str[i].length; j++) {
                    line = i;
                    column = j;
                    value = str[i][j];
                    int x = Integer.parseInt(str[i][j]);
                    summOfElements += x;
                }
            }
            System.out.println("Сумма всех элементов массива " + LENGTH_ARR + "X" + LENGTH_ARR + " = " + summOfElements);
        }
    }

}
