package de.pauzzy;

public class Layer {
    private PMath.VectorD nodes;
    private PMath.VectorD biases;
    private PMath.MatrixD weights;

    public Layer(int numNodes, Layer nextLayer) {
        nodes = new PMath.VectorD(numNodes);
        biases = new PMath.VectorD(numNodes);

        if (nextLayer != null) {
            weights = PMath.randMatrix(
                nextLayer.getNumNodes(), this.getNumNodes(),
                -1.0, 1.0
            );
        }
    }

    public PMath.VectorD getNodes() {
        return nodes;
    }

    public void setNodes(PMath.VectorD v) {
        nodes.set(v);
    }

    public PMath.MatrixD getWeights() {
        return weights;
    }

    public PMath.VectorD getBiases() {
        return biases;
    }

    public int getNumNodes() {
        return nodes.getDimension();
    }
}
