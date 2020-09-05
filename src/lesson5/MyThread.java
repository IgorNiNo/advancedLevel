package lesson5;

public class MyThread extends Thread {

//    private String name;
    private int size;
    private int offset;
    private float[] arr;

    public MyThread(float[] arr, int size, int offset) {
        this.arr = arr;
        this.size = size;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + offset) / 5) * Math.cos(0.2f + (i + offset) / 5) * Math.cos(0.4f + (i + offset) / 2));
        }
    }


}
