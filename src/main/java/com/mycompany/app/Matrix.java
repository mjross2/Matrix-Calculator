package com.mycompany.app;

public class Matrix {

	float[][] matrix;
	int width;
	int height;
	
	public Matrix(int w, int h) {
		this.matrix = new float[h][w];
		this.width = w;
		this.height = h;
	}
	
	public void print() {
		// TODO: make the size of space dependent on highest value
		String space = " ";
		for (float[] row : matrix) {
			System.out.print("[");
			for (float element : row) {
				System.out.print(space + element + space);
			}
			System.out.println("]");
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public double getElement(int x, int y) {
		return matrix[y][x];
	}
	
	public void setElement(int x, int y, float value) {
		matrix[y][x] = value;
	}
}
