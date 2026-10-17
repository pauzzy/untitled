package de.pauzzy;

public class PMath {
    public static class VectorD {
        private final double[] data;

        public VectorD(int size) {
            data = new double[size];
        }

        public void set(int index, double value) {
            data[index] = value;
        }

        public double get(int index) {
            return data[index];
        }

        public int getDimension() {
            return data.length;
        }

        public double getLength() {
            double result = 0;
            for (int i = 0; i < data.length; i++) {
                result += Math.pow(this.get(i), 2.0);
            }
            return Math.sqrt(result);
        }

        public void scale(double scalar) {
            for (int i = 0; i < data.length; i++) {
                data[i] *= scalar;
            }
        }

        public void add(VectorD other) {
            if (this.getDimension() != other.getDimension()) {
                throw new IllegalArgumentException("Vector dimensions don't match");
            }

            for  (int i = 0; i < data.length; i++) {
                data[i] += other.get(i);
            }
        }

        public void subtract(VectorD other) {
            if (this.getDimension() != other.getDimension()) {
                throw new IllegalArgumentException("Vector dimensions don't match");
            }

            for (int i = 0; i < data.length; i++) {
                data[i] -= other.get(i);
            }
        }

        public double dot(VectorD other) {
            if (this.getDimension() != other.getDimension()) {
                throw new IllegalArgumentException("Vector dimensions don't match");
            }

            double result = 0;
            for (int i = 0; i < data.length; i++) {
                result += this.get(i) * other.get(i);
            }

            return result;
        }

        public void normalize() {
            this.scale(1.0/this.getLength());
        }

        public static double dot(VectorD a, VectorD b) {
            if  (a.getDimension() != b.getDimension()) {
                throw new IllegalArgumentException("Vector dimensions don't match");
            }

            double result = 0;
            for (int i = 0; i < a.getDimension(); i++) {
                result += a.get(i) * b.get(i);
            }

            return result;
        }

        public static VectorD cross(VectorD a,  VectorD b) {
            if (a.getDimension() != 3 || b.getDimension() != 3) {
                throw new IllegalArgumentException("Vector dimensions must be 3");
            }

            VectorD result = new VectorD(3);
            result.set(0, a.get(1) * b.get(2) - b.get(1) * a.get(2));
            result.set(1, a.get(2) * b.get(0) - b.get(2) * a.get(0));
            result.set(2, a.get(0) * b.get(1) - b.get(0) * a.get(1));

            return result;
        }

        public static VectorD normalize(VectorD a) {
            double length = a.getLength();

            if (length == 0) {
                throw new IllegalArgumentException("Cannot normalize a zero Vector");
            }

            VectorD result = new VectorD(a.getDimension());

            for (int i = 0; i < a.getDimension(); i++) {
                result.set(i, a.get(i) / length);
            }

            return result;
        }
    }

    public static class MatrixD {
        private final double[] data;
        private final int rows;
        private final int cols;

        public MatrixD(int rows, int cols) {
            data =  new double[rows*cols];
            this.rows = rows;
            this.cols = cols;
        }

        public void set(int row, int col,  double value) {
            data[row*cols + col] = value;
        }

        public double get(int row, int col) {
            return data[row*cols + col];
        }

        public int getCols() {
            return cols;
        }

        public int getRows() {
            return rows;
        }

        public static VectorD mulMV(MatrixD matrix, VectorD vector) {
            if (matrix.getCols() != vector.getDimension()) {
                throw  new IllegalArgumentException("Matrix cols and Vector dimension don't match");
            }

            VectorD result = new VectorD(matrix.getRows());

            for (int i = 0; i < matrix.getRows(); i++) {
                double sum = 0;
                for (int j = 0; j < matrix.getCols(); j++) {
                    sum += matrix.get(i, j) * vector.get(j);
                }
                result.set(i, sum);
            }

            return result;
        }
    }
}
