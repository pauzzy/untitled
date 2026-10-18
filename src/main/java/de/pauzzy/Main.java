package de.pauzzy;

import java.util.ArrayList;

public class Main {
    static void main() {
        Layer outputLayer = new Layer(1, null);
        Layer hiddenLayer = new Layer(8, outputLayer);
        Layer inputLayer = new Layer(1, hiddenLayer);

        ArrayList<Layer> layers = new ArrayList<>() {
            {
                add(inputLayer);
                add(hiddenLayer);
                add(outputLayer);
            }
        };

        NeuralNet nn = new NeuralNet(layers);

        PMath.VectorD inputs = new PMath.VectorD(1);
        inputs.set(0, 1.0);

        nn.forward(inputs);

        layers.getLast().getNodes().print("output");
    }
}