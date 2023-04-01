package com.mycompany.app;

public class Operations {
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
