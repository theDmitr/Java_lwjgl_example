package dmitr.lwjgl_test.graphics.render;

public class Color {

    private float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(float r, float g, float b) { this(r, g, b, 1.0f); }

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }
    public float getA() { return a; }

    public void setR(float value) { r = value; }
    public void setG(float value) { g = value; }
    public void setB(float value) { b = value; }
    public void setA(float value) { a = value; }

}