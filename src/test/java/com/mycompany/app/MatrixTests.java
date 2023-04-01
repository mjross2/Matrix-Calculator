package com.mycompany.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class MatrixTests {

	public static void main(String[] args) {
		
		Matrix m = new Matrix(3, 3);
		m.print();
		
		try (Scanner scan = new Scanner(System.in)) {
			while (true) {
				String command = scan.nextLine();
				if (command.startsWith("set")) {
					String[] commandSplit = command.split(" ");
					int x = Integer.valueOf(commandSplit[1]);
					int y = Integer.valueOf(commandSplit[2]);
					float value = Float.valueOf(commandSplit[3]);
					m.setElement(x, y, value);
				} else if (command.startsWith("save")) {
					try {
						m.export(Path.of("saved_matrix.txt"));
						System.out.println("saved!");
					} catch (IOException e) {
						System.err.println("IOException: " + e);
					}
				} else if (command.startsWith("clear")) {
					m.clear();
				} else if (command.startsWith("load")) {
					try {
						m = new Matrix(Path.of("saved_matrix.txt"));
					} catch (IOException e) {
						System.err.println("IOException: " + e);
					}
				}
				m.print();
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid number format (this may have occured in the Matrix class).");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Index is out of the matrix boundaries.");
		}
	
	}

}
