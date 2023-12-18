package org.example;

import java.util.Arrays;

public class methodTwo {
    static final int size = 50_000_002;
    // Половина размера массива
    static final int half = size / 2;
    // Метод для работы с массивом с помощью двух потоков
    public static void methodTwo() {
        // Создаем и заполняем массив, также создаем два подмассива
        float[] array = new float[size];
        float[] firstHalf = new float[half];
        float[] secondHalf = new float[half];
        Arrays.fill(array, 1.0f);
        // Отмечаем время начала выполнения
        long time = System.currentTimeMillis();
        // Разбиваем массив на два подмассива
        System.arraycopy(array, 0, firstHalf, 0, half);
        System.arraycopy(array, half, secondHalf, 0, half);

        // Создаем и запускаем первый поток, который работает с первой половиной массива
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            // Копируем обработанные данные обратно в основной массив
            System.arraycopy(firstHalf, 0, array, 0, firstHalf.length);
        });
        threadOne.start();

        // То же самое делаем для второго потока
        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < secondHalf.length; i++) {
                // Обратите внимание, что здесь при расчете используется сдвиг на половину массива
                secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + (half + i) / 5) * Math.cos(0.2f + (half + i) / 5) * Math.cos(0.4f + (half + i) / 2));
            }
            System.arraycopy(secondHalf, 0, array, half, secondHalf.length);
        });
        threadTwo.start();

        // Ждем выполнения обоих потоков
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Создаем новый массив для объединения firstHalf и secondHalf
        float[] combined = new float[size];

        // Копируем firstHalf и secondHalf в combined
        System.arraycopy(firstHalf, 0, combined, 0, half);
        System.arraycopy(secondHalf, 0, combined, half, half);


        // Выводим элементы и время выполнения
        System.out.println(combined[0]);
        System.out.println(combined[combined.length - 1]);
        //System.out.println(Arrays.toString(combined));
        System.out.println("Время выполнения второго метода: " + (System.currentTimeMillis() - time));
    }
}
