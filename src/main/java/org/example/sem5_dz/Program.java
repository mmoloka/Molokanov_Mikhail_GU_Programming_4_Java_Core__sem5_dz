package org.example.sem5_dz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Program {

    private static final Random rnd = new Random();

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            writeFileContent("eng.txt", 30, 97, 122);
            writeFileContent("rus.txt", 30, 1072, 1103);
            writeFileContent("dig.txt", 30, 48, 57);
        }
        createBackup(new File("."));

        Tree.print(new File("."), "", true);
    }

    /**
     * Метод генерирует случайную последовательность символов из заданного промежутка.
     *
     * @param amount     число символов в последовательности
     * @param start_char номер начального символа заданного промежутка
     * @param end_char   номер конечного символа заданного промежутка
     * @return последовательность символов
     */
    public static String generateSymbols(int amount, int start_char, int end_char) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append((char) rnd.nextInt(start_char, end_char));
        }
        sb.append('\n');
        return sb.toString();
    }

    /**
     * Метод записывает в файл случайную последовательность символов из заданного промежутка.
     *
     * @param fileName   имя файла
     * @param length     число символов в последовательности
     * @param start_char номер начального символа заданного промежутка
     * @param end_char   номер конечного символа заданного промежутка
     * @throws IOException
     */
    public static void writeFileContent(String fileName, int length, int start_char, int end_char)
            throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
        fileOutputStream.write(generateSymbols(length, start_char, end_char).getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * Метод создает резервную копию всех файлов из указанной директории во вновь созданную папку "./backup".
     *
     * @param dir директория из которой копируются файлы
     * @throws IOException
     */
    public static void createBackup(File dir) throws IOException {
        File[] files = dir.listFiles();
        Files.createDirectory(Paths.get("./backup"));
        for (File f : files) {
            if (f.isFile()) {
                Path file = Paths.get(f.getPath());
                Files.copy(file, Paths.get("./backup/" + f.getName()));
            }
        }
    }
}
