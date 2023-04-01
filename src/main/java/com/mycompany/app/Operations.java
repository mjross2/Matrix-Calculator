package com.mycompany.app;

public class Operations {
	
	// TODO
	public static Matrix transpose(Matrix matrix) {
		return matrix;
	}
	
	/**
	 * Inverts a matrix
	 * @param matrix
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static float[][] invert(float[][] matrix) throws IllegalArgumentException {
		        
		// Check if matrix is square
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (rows != cols) {
		    throw new IllegalArgumentException("Matrix must be square");
		}
		
		// Create an identity matrix of the same size as the input matrix
		float[][] identity = new float[rows][cols];
		for (int i = 0; i < rows; i++) {
		    identity[i][i] = 1;
		}
		
		// TODO: make Gaussian elimination its own function
		// Perform Gaussian elimination to transform the input matrix into the identity matrix
		for (int i = 0; i < rows; i++) {
		    // Find pivot element
		    float pivot = matrix[i][i];
		    if (pivot == 0) {
		        throw new IllegalArgumentException("Matrix is singular and cannot be inverted");
		    }
		    // Divide row by pivot
		    for (int j = 0; j < cols; j++) {
		        matrix[i][j] /= pivot;
		        identity[i][j] /= pivot;
		    }
		    // Subtract multiples of the pivot row from all other rows to eliminate non-zero entries in the i-th column
		    for (int k = 0; k < rows; k++) {
		    	if (k != i) {
		            float factor = matrix[k][i];
		            for (int j = 0; j < cols; j++) {
		                matrix[k][j] -= factor * matrix[i][j];
		                identity[k][j] -= factor * identity[i][j];
		            }
	            }
	        }
		}
		    
		 return identity;
	}

    Matrix multiply(Matrix a, Matrix b) throws Exception {
        Float element;
        Matrix result = new Matrix(a.getHeight(), b.getWidth());
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                element = dotProduct(a.getRow(i), b.getCol(j));
                result.setElement(i, j, element); 
            }
        }
        return result;
    }

    public Float dotProduct(Float[] row, Float[] col) throws Exception {
        if (row.length != col.length) {
            throw new Exception("Size must match");
        }
        Float sum = (float) 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i] * col[i];
        }
        return sum;
    }

    public Matrix add(Matrix a, Matrix b) {
        Float aVal;
        Float bVal;
        Float sum;
        Matrix result = new Matrix(a.getHeight(), a.getWidth());
        for (int i = 0; i < a.getHeight(); i++) {
            for (int j = 0; j < a.getWidth(); j++) {
                aVal = a.getElement(i, j);
                bVal = b.getElement(i, j);
                sum = aVal + bVal;
                result.setElement(i, j, sum);
            }
        }
        return result;
    }
	
}
