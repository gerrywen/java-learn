package demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * program: java-learn->TestFile
 * description:
 * author: gerry
 * created: 2020-03-14 09:39
 **/
public class TestFile {

    public static void main(String[] args) {
    }



    public static void test1() {
        List<String> files = getFiles("/Users/gerry/Desktop/document/file");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String filePath = "/Users/gerry/Desktop/document/file/test.md";
                File file = new File(filePath);
                if (file.exists()) {
                    boolean delete = file.delete();
                    System.out.println(delete);
                }
                try {
                    boolean newFile = file.createNewFile();
                    System.out.println(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileWriter fileWriter = new FileWriter(filePath, true);
                    for (String s : files) {
                        fileWriter.write(s + "\n");
                    }
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println("线程运行");
            }
        };
        runnable.run();
    }

    public static List<String> getFiles(String filepath) {
        List<String> files = new ArrayList<>();
        File file = new File(filepath);
        File[] tempLists = file.listFiles();
        if (tempLists == null) {
            return files;
        }
        for (File file1 : tempLists) {
            if (file1.isFile() && file1.toString().endsWith(".txt")) {
                String fileName = file1.getName();
                files.add(fileName.substring(0, fileName.length() - 4));
            }
        }
        for (String s : files) {
            System.out.println(s);
        }
        return files;
    }
}
