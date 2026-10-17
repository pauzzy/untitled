package de.pauzzy;

public class Layer {
    PMath.VectorD nodes;
    PMath.VectorD biases;
    PMath.MatrixD weights;

    public Layer(int numNodes, Layer nextLayer) {
        nodes = new PMath.VectorD(numNodes);
        biases = new PMath.VectorD(numNodes);

        if (nextLayer != null) {
            weights = new PMath.MatrixD(nextLayer.getNodes(), this.getNodes());
        }
    }

    public int getNodes() {
        return nodes.getDimension();
    }
}
