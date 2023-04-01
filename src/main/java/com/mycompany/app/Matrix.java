package com.mycompany.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Matrix {

	float[][] matrix;
	int width;
	int height;
	
	public Matrix(int w, int h) {
		this.matrix = new float[h][w];
		this.width = w;
		this.height = h;
	}
	
	public Matrix(Path path) throws IOException {
		try (BufferedReader buf = Files.newBufferedReader(path)) {
			this.width = Integer.valueOf(buf.readLine());
			this.height = Integer.valueOf(buf.readLine());
			this.matrix = new float[height][width];
			String space = " "; // TODO: ideally this won't be constant forever
			String line;
			int x = 0;
			int y = 0;
			while ((line = buf.readLine()) != null) {
				float[] row = new float[width];
				for (String number : line.split(space)) {
					row[x] = Float.valueOf(number);
					x++;
				}
				matrix[y] = row;
				y++;
				x = 0;
			}
		}
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

	public Float[] getRow(int r) {
		Float[] row = new Float[width];
		for (int i = 0; i < width; i++) {
			row[i] = matrix[r][i];
		}
		return row;
	}

	public Float[] getCol(int c) {
		Float[] col = new Float[height];
		for (int i = 0; i < width; i++) {
			col[i] = matrix[i][c];
		}
		return col;
	}
	
	public Float getElement(int x, int y) {
		return matrix[y][x];
	}
	
	public void setElement(int x, int y, float value) {
		if (x < width && y < height) {
			matrix[y][x] = value;
		} else {
			System.err.println("Please choose in-bounds indexes. (Starts at (0, 0) in the top-left corner.)");
		}
	}
	
	/**
	 * Format:
	 * w
	 * h
	 * [x x x x x x]
	 * ...
	 * @param path
	 * @throws IOException
	 */
	public void export(Path path) throws IOException {
		String space = " ";
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.append(String.valueOf(this.width));
			writer.append("\n");
			writer.append(String.valueOf(this.height));
			writer.append("\n");
			for (float[] row : matrix) {
				for (float element : row) {
					writer.append(element + space);
				}
				writer.append("\n");
			}
		}
	}

	// TODO: clear row/column?
	public void clear() {
		int row = 0;
		while (row < height) {
			matrix[row] = new float[width];
			row++;
		}
	}
}
