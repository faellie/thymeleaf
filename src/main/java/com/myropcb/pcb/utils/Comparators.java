package com.myropcb.pcb.utils;


import com.myropcb.pcb.model.PcbBoard;
import com.myropcb.pcb.model.Shape;

import java.util.Comparator;

public class Comparators {
    public static Comparator<PcbBoard> compareCount = (PcbBoard s1, PcbBoard s2) -> {
        return s1.getCount() -s2.getCount();
    };

    public static Comparator<Shape> compareArea = (Shape s1, Shape s2) -> {
        return Double.compare(s1.getArea(), s2.getArea());
    };
}
