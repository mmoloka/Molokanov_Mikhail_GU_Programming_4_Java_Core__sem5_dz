package org.example.sem5_dz;

import java.io.File;

public class Tree {
    /**
     * Метод выводит на печать в виде "дерева" иерархию директорий и файлов по указанному пути.
     *
     * @param file   путь для вывода
     * @param indent отступ
     * @param isLast определяет является ли элемент последним
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        int subFileTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
            else if (files[i].isFile())
                subFileTotal++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                print(files[i], indent, subDirCounter == subDirTotal - 1);
                subDirCounter++;
            } else if (files[i].isFile()) {
                print(files[i], indent, subFileCounter == subFileTotal - 1);
                subFileCounter++;
            }
        }


    }
}
