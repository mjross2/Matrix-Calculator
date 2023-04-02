package com.mycompany.app;

public class Operations {
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public static Matrix gaussJordanElimination(Matrix matrix) {
		int height = matrix.getHeight();
	    int width = matrix.getWidth();
	    float[][] augmented = new float[height][width*2];
	    
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            augmented[i][j] = matrix.getElement(j, i);
	        }
	        for (int j = width; j < width*2; j++) {
	            augmented[i][j] = (j - width == i) ? 1 : 0;
	        }
	    }

	    for (int i = 0; i < height; i++) {
	        // find the pivot element
	        int pivot = i;
	        for (int j = i+1; j < height; j++) {
	            if (Math.abs(augmented[j][i]) > Math.abs(augmented[pivot][i])) {
	                pivot = j;
	            }
	        }
	        float[] temp = augmented[i];
	        augmented[i] = augmented[pivot];
	        augmented[pivot] = temp;

	        float divisor = augmented[i][i];
	        for (int j = 0; j < width*2; j++) {
	            augmented[i][j] /= divisor;
	        }

	        for (int j = 0; j < height; j++) {
	            if (j == i) {
	                continue;
	            }
	            float factor = augmented[j][i];
	            for (int k = 0; k < width*2; k++) {
	                augmented[j][k] -= factor * augmented[i][k];
	            }
	        }
	    }

	    Matrix reduced = new Matrix(width, height);
	    for (int i = 0; i < height; i++) {
	        for (int j = width; j < width*2; j++) {
	            reduced.setElement(j - width, i, augmented[i][j]);
	        }
	    }
	    
	    return reduced;
	}
		
	/**
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @return
	 */
	public static Matrix subtract(Matrix matrix1, Matrix matrix2) {

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
	public static Matrix transpose(Matrix matrix) {

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
	public static Matrix invert(Matrix matrix) throws Exception {
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
    public static Matrix multiply(Matrix a, Matrix b) throws Exception {
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
    public static float dotProduct(float[] row, float[] col) throws Exception {
        if (row.length != col.length) {
            throw new Exception("Size must match");
        }
        float sum = (float) 0;
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
    public static Matrix add(Matrix a, Matrix b) {
        float aVal;
        float bVal;
        float sum;
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
    
    public static float determinant(Matrix matrix) {
    	int width = matrix.getWidth();
    	int height = matrix.getHeight();
    	if (width != height) {
    		System.err.println("Only square matricies have determinants, returning 0.");
    		return 0;
    	} else if (width == 1) {
    		return matrix.getElement(0, 0);
    	} else if (width == 2) {
    		float a = matrix.getElement(0, 0);
    		float b = matrix.getElement(1, 0);
    		float c = matrix.getElement(0, 1);
    		float d = matrix.getElement(1, 1);
    		return (a*d)-(b*c);
    	}
    	float result = 0;
    	// for each element (e) in the first row
    	for (int col = 0; col < width; col++) {
    		// result += e * Cofactor expansion of e's location    		
    		result += matrix.getElement(col, 0) * cofactor(matrix, col, 0);
    	}
    	return result;
    }

	public static float cofactor(Matrix matrix, int x, int y) {
		float result = (float) Math.pow(-1, x + y) * minor(matrix, x, y);
		return result;
	}

	public static float minor(Matrix matrix, int x, int y) {
		// remove x's column and y's row
		int ogLen = matrix.getWidth();
		if (ogLen != matrix.getHeight()) {
			System.err.println("Matrix must be square, returning 0.");
			return 0;
		}
		
		Matrix reduced = new Matrix(ogLen - 1, ogLen -1);
		int reducedX = 0;
		int reducedY = 0;
		
		for (int row = 0; row < ogLen; row++) {
			if (row != y) {
				for (int col = 0; col < ogLen; col++) {
					if (col != x) {
						reduced.setElement(reducedX, reducedY, matrix.getElement(col, row));
						reducedX++;
					}
				}
				reducedY++;
				reducedX = 0;
			}
		}
				
		if (ogLen - 1 > 2) {
			return determinant(reduced);
		}
		
		float a = reduced.getElement(0, 0);
		float b = reduced.getElement(1, 0);
		float c = reduced.getElement(0, 1);
		float d = reduced.getElement(1, 1);
		return (a*d)-(b*c);
		
	}
}
