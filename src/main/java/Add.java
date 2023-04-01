public class Add {
    Matrix a;
    Matrix b;
    Matrix result;

    public Add(Matrix x, Matrix y) {
        if ((x.getRows() != y.getRows()) || (x.getColumns() != y.getColumns())) {
            throw new Exception();
        }
        else {
            a = x;
            b = y;
        }
    }

    public Matrix add() {
        Float aVal;
        Float bVal;
        Float sum;
        for (int i = 0; i < a.getRows; i++) {
            for (int j = 0; j < a.getColumns; j++) {
                aVal = a.getElement(i, j);
                bVal = b.getElement(i, j);
                sum = aVal + bVal;
                result.setElement(i, j, sum);
            }
        }
        return result;
    }
}
