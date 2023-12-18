package org.example;

import java.util.Arrays;

public class methodOne {
    static final int size = 50_000_002;
    public void methodOne() {
        // Создаем массив указанного размера и заполняем его единицами
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);
        // Записываем начало отсчета времени
        long time = System.currentTimeMillis();
        // Выполняем определенные математические операции над каждым элементом массива
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        // Выводим первый и последний элементы массива
        System.out.println(array[0]);
        System.out.println(array[array.length - 1]);
        //System.out.println(Arrays.toString(array));
        // Выводим время выполнения метода
        System.out.println("Время выполнения первого метода: " + (System.currentTimeMillis() - time));
    }
}
