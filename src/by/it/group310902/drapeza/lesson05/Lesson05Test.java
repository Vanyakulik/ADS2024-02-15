package by.it.group310902.drapeza.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson05Test {
    @Test
    public void checkA() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        boolean ok = Arrays.equals(result, new int[]{1, 0, 0});
        assertTrue("A failed", ok);
    }


    @Test
    public void checkB() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        boolean ok = Arrays.equals(result, new int[]{2, 2, 3, 9, 9});
        assertTrue("B failed", ok);
    }


    @Test
    public void checkC() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        boolean ok = Arrays.equals(result, new int[]{1, 0, 0});
        assertTrue("C failed", ok);
    }

}
