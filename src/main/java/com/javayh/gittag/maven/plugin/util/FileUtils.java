package com.javayh.gittag.maven.plugin.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * 写入到文件
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-23
 */
public class FileUtils {
    public static void writer(BufferedReader reader, String path, String name) {
        try {
            FileWriter writer = new FileWriter(path + "/" + name + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String line;
            while ((line = reader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
