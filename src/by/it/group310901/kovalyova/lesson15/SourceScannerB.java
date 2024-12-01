package by.it.group310901.kovalyova.lesson15;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.MalformedInputException;

public class SourceScannerB {
    public static void main(String[] args) {
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        try {
            List<FileInfo> filesInfo = new ArrayList<>();
            Files.walk(Paths.get(src))
                    .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))
                    .forEach(path -> {
                        try {

                            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

                            // Пропускаем файлы с @Test или org.junit.Test
                            if (content.contains("@Test") || content.contains("org.junit.Test")) {
                                return;
                            }

                            // Удаляем строку package и все импорты
                            content = content.replaceAll("(?m)^\\s*package.*;\\s*$", "")
                                    .replaceAll("(?m)^\\s*import.*;\\s*$", "");

                            // Удаляем комментарии (однострочные и многострочные)
                            content = content.replaceAll("(?s)/\\*.*?\\*/", "")
                                    .replaceAll("//.*$", "");

                            // Удаляем символы с кодом < 33 в начале и конце
                            content = content.replaceAll("^[\\x00-\\x1F]+|[\\x00-\\x1F]+$", "");

                            // Удаляем пустые строки
                            content = content.replaceAll("(?m)^\\s*$", "");

                            // Получаем размер в байтах
                            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
                            if (bytes.length > 0) {
                                filesInfo.add(new FileInfo(path.toString().substring(src.length()), bytes.length));
                            }
                        } catch (MalformedInputException e) {
                            // Игнорируем ошибки MalformedInputException
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            // Сортируем по размеру и лексикографически
            filesInfo.sort(Comparator.comparingInt(FileInfo::getSize)
                    .thenComparing(FileInfo::getPath));

            // Выводим результаты
            for (FileInfo info : filesInfo) {
                System.out.println(info.getSize() + " " + info.getPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FileInfo {
        private final String path;
        private final int size;

        public FileInfo(String path, int size) {
            this.path = path;
            this.size = size;
        }

        public String getPath() {
            return path;
        }

        public int getSize() {
            return size;
        }
    }
}

