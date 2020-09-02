package lesson5;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        long a;
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];


        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        //Первый метод
        System.out.println("Первый метод в один поток.");
        a = System.currentTimeMillis();
        MyThread t1 = new MyThread(arr, SIZE, 0 );
        t1.run();
        System.out.println("Время работы метода = " + (System.currentTimeMillis() - a) + " ms");
        //выведем несколько контрольных элементов массива
        System.out.println("arr[0]       = " + arr[0]);
        System.out.println("arr[4999999] = " + arr[4999999]);
        System.out.println("arr[5000000] = " + arr[5000000]);
        System.out.println("arr[9999999] = " + arr[9999999]);


        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        //Второй метод
        System.out.println();
        System.out.println("Второй метод в два потока.");
        a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        MyThread t2 = new MyThread(a1, HALF, 0 );
        MyThread t3 = new MyThread(a2, HALF, HALF );
        t2.start();
        t3.start();
        try {
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);
        System.out.println("Время работы метода = " + (System.currentTimeMillis() - a) + " ms");
        //выведем несколько контрольных элементов массива
        System.out.println("arr[0]       = " + arr[0]);
        System.out.println("arr[4999999] = " + arr[4999999]);
        System.out.println("arr[5000000] = " + arr[5000000]);
        System.out.println("arr[9999999] = " + arr[9999999]);

    }
}
