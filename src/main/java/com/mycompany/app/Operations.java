package com.mycompany.app;

public class Operations {
	
	// TODO
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public static Matrix transpose(Matrix matrix) {
		return matrix;
	}
	
	/**
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @return
	 */
	public static Matrix matrixSubtraction(Matrix matrix1, Matrix matrix2) {

	    // Check that the matrices have the same dimensions
	    int rows = matrix1.getHeight();
	    int cols = matrix1.getWidth();
	    if (rows != matrix2.getHeight() || cols != matrix2.getWidth()) {
	        throw new IllegalArgumentException("Matrices must have the same dimensions");
	    }

	    // Create a new matrix to store the result
	    Matrix result = new Matrix(cols, rows);

	    // Subtract the corresponding elements of the input matrices
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            float diff = matrix1.getElement(j, i) - matrix2.getElement(j, i);
	            result.setElement(j, i, diff);
	        }
	    }

	    return result;
	}
	
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public static Matrix matrixTranspose(Matrix matrix) {

	    // Get the number of rows and columns in the original matrix
	    int rows = matrix.getHeight();
	    int cols = matrix.getWidth();

	    // Create a new matrix to store the transpose
	    Matrix transpose = new Matrix(cols, rows);

	    // Transpose the original matrix by swapping rows and columns
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            transpose.setElement(j, i, matrix.getElement(i, j));
	        }
	    }

	    return transpose;
	}
	
	/**
	 * Inverse a matrix
	 * @param matrix
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Matrix inverse(Matrix matrix) throws Exception {
	    int n = matrix.getWidth();
	    if (n != matrix.getHeight()) {
	        throw new Exception("Matrix must be square");
	    }
	    
	    Matrix identity = new Matrix(n, n);
	    for (int i = 0; i < n; i++) {
	        identity.setElement(i, i, 1.0f);
	    }
	    
	    Matrix augmented = new Matrix(n, 2 * n);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            augmented.setElement(i, j, matrix.getElement(i, j));
	            augmented.setElement(i, j + n, identity.getElement(i, j));
	        }
	    }
	    
	    for (int i = 0; i < n; i++) {
	        float pivot = augmented.getElement(i, i);
	        if (pivot == 0) {
	            throw new Exception("Matrix is singular");
	        }
	        
	        for (int j = 0; j < 2 * n; j++) {
	            augmented.setElement(i, j, augmented.getElement(i, j) / pivot);
	        }
	        
	        for (int j = 0; j < n; j++) {
	            if (i != j) {
	                float factor = augmented.getElement(j, i);
	                for (int k = 0; k < 2 * n; k++) {
	                    augmented.setElement(j, k, augmented.getElement(j, k) - factor * augmented.getElement(i, k));
	                }
	            }
	        }
	    }
	    
	    Matrix result = new Matrix(n, n);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            result.setElement(i, j, augmented.getElement(i, j + n));
	        }
	    }
	    
	    return result;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @throws Exception
	 */
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

    /**
     * 
     * @param row
     * @param col
     * @return
     * @throws Exception
     */
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

    /**
     * 
     * @param a
     * @param b
     * @return
     */
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
