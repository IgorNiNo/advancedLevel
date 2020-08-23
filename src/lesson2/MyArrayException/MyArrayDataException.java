package lesson2.MyArrayException;

public class MyArrayDataException extends IllegalArgumentException {
    private int line;
    private int column;
    private String value;

    public MyArrayDataException(String s) {
        super(s);
    }

    public MyArrayDataException(int line, int column, String value) {
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public MyArrayDataException(String s, int line, int column, String value) {
        super(s);
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public void info() {
        System.out.println("В ячейке [" + line + "][" + column + "] массива значение: " + value + " не является числом");
    }
}


