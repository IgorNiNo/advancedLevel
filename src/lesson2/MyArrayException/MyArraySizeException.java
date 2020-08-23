package lesson2.MyArrayException;

public class MyArraySizeException extends IllegalArgumentException {
    private int line;
    private int column;
//    private String str;

    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public void info() {
        System.out.println("Анализируемый массив не является массивом размера: " + line + "X" + column);
    }

}
