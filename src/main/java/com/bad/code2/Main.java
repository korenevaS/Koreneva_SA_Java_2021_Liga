package com.bad.code2;

import com.bad.code2.shape.implementation.Cube;
import com.bad.code2.shape.implementation.Square;

import static com.bad.code2.constant.values.StringConstants.*;
import static com.bad.code2.constant.values.ValueConstants.*;

public class Main {
    public static void main(String[] args) {
        qubeVolume();
        squareArea();
    }

    private static void qubeVolume() {
        Cube qube = new Cube(CENTRE_X, CENTRE_Y, CENTRE_Z, EDGE_SIZE_MAX);
        System.out.println(CUBE_VOLUME + qube.getVolume());
    }

    private static void squareArea() {
        Square square = new Square(CENTRE_X, CENTRE_Y, EDGE_SIZE_MIN);
        System.out.println(SQUARE_AREA + square.getArea());
    }
}

