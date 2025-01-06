package by.it.group351003.sologub.lesson13;

import by.it.HomeWork;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13 extends HomeWork {

    @Test
    public void testGraphA() {
        run("0 -> 1", true).include("0 1");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("0 -> 2, 1 -> 2, 0 -> 1", true).include("0 1 2");
        run("0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1", true).include("0 1 2 3");
        run("1 -> 3, 2 -> 3, 2 -> 3, 0 -> 1, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("0 1 2 3");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, A -> D", true).include("A B C D");
        run("0 -> 1, 1 -> 0", true).include("Ошибка: граф содержит цикл."); // Цикл
        run("A -> B, B -> A", true).include("Ошибка: граф содержит цикл."); // Цикл в буквенном графе

// Пример с большим количеством вершин и рёбер
        run("0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3, 2 -> 4, 3 -> 5, 4 -> 5", true).include("0 1 2 3 4 5"); // Длинная цепочка
        run("1 -> 2, 0 -> 2, 0 -> 3, 3 -> 4, 2 -> 4, 4 -> 5", true).include("0 1 2 3 4 5"); // Другой порядок рёбер
        run("A -> B, B -> C, C -> D, D -> E, E -> F", true).include("A B C D E F"); // Линейный граф с буквами

// Пример с несколькими независимыми компонентами
        run("0 -> 1, 2 -> 3", true).include("0 1 2 3"); // Независимые компоненты
        run("A -> B, C -> D", true).include("A B C D"); // Независимые компоненты с буквами

// Пример с циклом и независимыми вершинами
        run("0 -> 1, 1 -> 2, 2 -> 0, 3 -> 4", true).include("Ошибка: граф содержит цикл."); // Цикл и независимая вершина
        run("A -> B, B -> C, C -> A, D -> E", true).include("Ошибка: граф содержит цикл."); // Цикл с буквами и независимая компонента

// Пример с множественными возможными топологиями
        run("0 -> 1, 1 -> 2, 0 -> 2", true).include("0 1 2"); // Множественная сортировка для одинаковых рёбер
        run("A -> C, B -> C, A -> B", true).include("A B C"); // Множественная сортировка для буквенных рёбер

// Пример с повторяющимися рёбрами и независимыми вершинами
        run("0 -> 1, 1 -> 2, 1 -> 2, 3 -> 4", true).include("0 1 2 3 4"); // Повторяющиеся рёбра и независимая вершина

// Пример с вершинами, представленными числами и буквами
        run("0 -> A, A -> B, B -> C", true).include("0 A B C"); // Смешанный граф с буквами и числами
        run("A -> 1, 1 -> B, B -> 2", true).include("A 1 B 2");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
    }
    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}