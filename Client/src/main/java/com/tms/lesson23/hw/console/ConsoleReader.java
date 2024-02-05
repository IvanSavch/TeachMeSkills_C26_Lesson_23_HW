package com.tms.lesson23.hw.console;

import java.util.Scanner;

public class ConsoleReader {
    private static Scanner scanner = new Scanner(System.in);
    public static String readOperationType() {
        return scanner.next();
    }
    public static double readNum() {
        return scanner.nextDouble();
    }
}
