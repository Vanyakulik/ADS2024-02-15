package by.it.group310901.eremeiko.lesson14;

import java.util.*;
public class PointsA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distanceRequired = scanner.nextInt(); // Вводим максимальное расстояние, при котором точки объединяются
        int count = scanner.nextInt();  // Вводим количество точек
        DSU<Point> dsu = new DSU<>();  // Создаем экземпляр DSU для точек

        // Проходим по всем точкам
        for (int i = 0; i < count; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            Point point = new Point(x, y, z);  // Создаем объект точки с координатами
            dsu.makeSet(point);  // Добавляем точку в DSU

            // Сравниваем текущую точку с каждой уже добавленной в DSU
            for (Point existingPoint : dsu) {
                if (point.distanceTo(existingPoint) <= distanceRequired) {
                    dsu.union(point, existingPoint);
                }
            }
        }

        // Составляем список размеров кластеров
        List<Integer> clusterSizes = new ArrayList<>();
        HashSet<Point> set = new HashSet<>();
        for (Point existingPoint : dsu) {
            Point root = dsu.findSet(existingPoint);
            if (set.contains(root))
                continue;
            set.add(root);
            int size = dsu.getClusterSize(root);
            clusterSizes.add(size);
        }
        // Сортируем размеры кластеров по убыванию
        Collections.sort(clusterSizes);
        Collections.reverse(clusterSizes);
        for (int size : clusterSizes) {
            System.out.print(size + " ");
        }
    }
    static class Point {   // Вспомогательный класс для представления точки в 3D пространстве
        int x, y, z;
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        // Метод для вычисления расстояния между двумя точками в 3D пространстве
        double distanceTo(Point other) {
            return Math.hypot(Math.hypot(x - other.x, y - other.y), z - other.z);
        }
    }
}
