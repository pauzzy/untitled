package de.pauzzy;

import java.util.ArrayList;

public class NeuralNet {
    private ArrayList<Layer> layers;

    public NeuralNet(ArrayList<Layer> layers) {
        this.layers = layers;
    }

    ArrayList<Layer> getLayers() {
        return layers;
    }

    void forward(PMath.VectorD input) {
        layers.getFirst().setNodes(input);

        for (int i = 0; i < layers.size() - 1; i++) {
            Layer currentLayer = layers.get(i);

            PMath.VectorD currentLayerNodes =
                currentLayer.getNodes();

            PMath.MatrixD currentLayerWeights =
                currentLayer.getWeights();

            Layer nextLayer = layers.get(i + 1);
            PMath.VectorD nextLayerNodes = PMath.mulMV(
                currentLayerWeights, currentLayerNodes
            );
            nextLayerNodes.add(nextLayer.getBiases());

            nextLayer.setNodes(nextLayerNodes);
        }
    }
}