public final class Triangle {
    private final int x;
    private final int y;
    private final int kat;
    private final int height;
    private final int width;

    public Triangle(int x, int y, int kat, int wysokosc, int szerokosc) {
        this.x = x;
        this.y = y;
        this.kat = kat;
        this.height = wysokosc;
        this.width = szerokosc;
    }

    public Triangle ChangeX(int x) {
        return new Triangle(x, y, kat, height, width);
    }

    public Triangle ChangeY(int x) {
        return new Triangle(x, x, kat, height, width);
    }

    public Triangle ChangeHeight(int val) {
        return new Triangle(x, y, kat, val, width);
    }

    public Triangle ChangeWidth(int val) {
        return new Triangle(x, y, kat, height, val);
    }

    public Triangle Rotate() {
        if (kat + 90 >= 360)
            return new Triangle(x, y, 90 - (360 - kat), height, width);
        else
            return new Triangle(x, y, kat + 90, height, width);
    }

    @Override
    public String toString() {
        return "Rectangle of dimensions: X=" + x + ", y=" + y + ", a= " + height + ", b= " + width + ", kat= " + kat
                + "";
    }
}