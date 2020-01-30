package edu.fje.dam2;

import android.graphics.Path;

public class M07_PathDit {

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path ruta;

    public M07_PathDit(int color, boolean emboss, boolean blur, int strokeWidth, Path ruta) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.ruta = ruta;
    }
}
