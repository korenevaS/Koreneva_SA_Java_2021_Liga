package com.bad.code2.shape.implementation;

import com.bad.code2.shape.interfaces.Shape3D;

public class Cube implements Shape3D {
    private final double centerX;
    private final double centerY;
    private final double centerZ;
    private final double edgeSize;

    public Cube(Double centerX, Double centerY, Double centerZ, Double edgeLength) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.edgeSize = edgeLength;
    }

    @Override
    public double getCenterX() {
        return centerX;
    }

    @Override
    public double getCenterY() {
        return centerY;
    }

    @Override
    public double getCenterZ() {
        return centerZ;
    }


    @Override
    public double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
