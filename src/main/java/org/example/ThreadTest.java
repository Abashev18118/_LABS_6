package org.example;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Arrays;
// Класс для тестирования многопоточности
public class ThreadTest {
    // Статическая константа размера массива
    static final int size = 50_000_002;
    // Метод для работы с массивом, число потоков задается динамически
    public void methodThree(int n) {
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);
        long time = System.currentTimeMillis();
        Thread[] threads = new Thread[n];
        float[][] results = new float[n][];
        int partSize = size / n;
        int remainder = size % n;
        LinkedList<Float>[] lists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            final int startIndex;
            final int endIndex;
            if (i == 0) {
                startIndex = 0;
                endIndex = partSize + remainder;
            } else {
                startIndex = i * partSize + remainder;
                endIndex = startIndex + partSize;
            }
            final int index = i;

            threads[i] = new Thread(() -> {
                float[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
                for (int j = 0; j < subArray.length; j++) {
                    subArray[j] = (float) (subArray[j] * Math.sin(0.2f + (startIndex + j) / 5) * Math.cos(0.2f + (startIndex + j) / 5) * Math.cos(0.4f + (startIndex + j) / 2));
                }
                results[index] = subArray;
            });
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Инициализируем потоки и добавляем в них элементы
        for (int i = 0; i < n; i++) {
            lists[i] = new LinkedList<>();
            for (int j = 0; j < results[i].length; j++) {
                lists[i].add(results[i][j]);
            }
            //System.out.println("LinkedList " + (i+1) + ": " + lists[i]);
        }
        // Создаем новый массив для объединения результатов всех потоков
        float[] methodThree = new float[size];

        // Объединяем результаты обратно в массив methodThree
        for (int i = 0; i < n; i++) {
            final int startIndex;
            if (i == 0) {
                startIndex = 0;
            } else {
                startIndex = i * partSize + remainder;
            }
            System.arraycopy(results[i], 0, methodThree, startIndex, results[i].length);
        }
        System.out.println("Первый элемент: " + methodThree[0]);
        System.out.println("Последний элемент: " + methodThree[size-1]);
        //System.out.println(Arrays.toString(methodThree));
        System.out.println("Время выполнения третьего метода: " + (System.currentTimeMillis() - time));
    }

}
