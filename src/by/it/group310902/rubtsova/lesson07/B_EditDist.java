package by.it.group310902.rubtsova.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //если одна из строк пустая, то считаем расстояние как длину другой строки
        if (one.length()==0){
            return two.length();
        }else if(two.length()==0){
            return one.length();
        }
        //создаём массив для заполнения
        int[][]dp=new int[one.length()+1][two.length()+1];

        // заполняем первую колонку и первую строку (расстояние от пустой строки до другой строки)
        for (int i = 0; i <= one.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= two.length(); j++) {
            dp[0][j] = j;
        }
        //заполняем оставшийся массив
        for(int i=1;i<=one.length();++i){
            for (int j = 1; j <= two.length(); j++){
                // если символы совпадают, то вес = 0
                int s=(one.charAt(i-1)==two.charAt(j-1))?0:1;
                //считаем минимальный вес
                dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+s);
            }
        }

        // вес редактирования
        int result = dp[one.length()][two.length()];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_EditDist.class.getResourceAsStream("dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}