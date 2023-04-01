package com.mycompany.app;

import java.util.Scanner;

public class MatrixTests {

	public static void main(String[] args) {
		
		System.out.println("Matrix 1:");
		Matrix m1 = new Matrix(2, 2);
		Matrix m2 = new Matrix(2, 2);
		m1.setElement(0, 0, 3);
		m1.setElement(0, 1, 6);
		m1.setElement(1, 0, 1);
		m1.setElement(1, 1, 8);
		m1.print();
		System.out.println("\nMatrix 2:");
		m2.setElement(0, 0, 7);
		m2.setElement(0, 1, 2);
		m2.setElement(1, 0, 9);
		m2.setElement(1, 1, 5);
		m2.print();
		
		try {
			System.out.println("\nMatrix inverse;");
			Matrix inverse = new Matrix(2,2);
			inverse = Operations.matrixInverse(m1);
			inverse.print();	
			
			System.out.println("\nMatrix transpose:");
			Matrix transpose = new Matrix(2,2);
			transpose = Operations.matrixTranspose(m1);
			transpose.print();	
			
			System.out.println("\nMatrix subtraction:");
			Matrix subtract = new Matrix(2,2);
			subtract = Operations.matrixSubtraction(m1, m2);
			subtract.print();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try (Scanner scan = new Scanner(System.in)) {
//			while (true) {
//				String command = scan.nextLine();
//				if (command.startsWith("set")) {
//					String[] commandSplit = command.split(" ");
//					int x = Integer.valueOf(commandSplit[1]);
//					int y = Integer.valueOf(commandSplit[2]);
//					float value = Float.valueOf(commandSplit[3]);
//					m.setElement(x, y, value);
//				}
//				m.print();
//			}
//		} catch (NumberFormatException e) {
//			System.out.println("Format: set x y val");
//		} catch (IndexOutOfBoundsException e) {
//			System.out.println("Index is out of the matrix boundaries");
//		}
	
	}

}
