/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author dungi
 */
public class FileUtil {
    public static void saveToFile(String data, String fileName) throws Exception {
        FileWriter writer = new FileWriter(new File(fileName));
        writer.write(data);
        writer.close();
    }

    public static String readFromFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
