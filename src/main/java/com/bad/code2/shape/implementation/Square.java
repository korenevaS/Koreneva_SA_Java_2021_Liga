package com.bad.code2.shape.implementation;

import com.bad.code2.shape.interfaces.Shape2D;

public class Square implements Shape2D {
    private final double centerX;
    private final double centerY;
    private final double edgeLength;

    public Square(Double centerX, Double centerY, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.edgeLength = edgeSize;
    }

    @Override
    public Double getCenterX() {
        return centerX;
    }

    @Override
    public Double getCenterY() {
        return centerY;
    }

    @Override
    public Double getArea() {
        return Math.pow(edgeLength, 2);
    }
}
