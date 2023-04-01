package com.mycompany.app;

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
				}
				m.print();
			}
		} catch (NumberFormatException e) {
			System.out.println("Format: set x y val");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index is out of the matrix boundaries");
		}
	
	}

}
