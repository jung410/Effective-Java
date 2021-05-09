public class Monitor {

    private String brand;
    private String panel;
    private int inch;
    private String color;
    private int portCount;
    private String ratio;
    private int price;

    public Monitor(String brand, String panel, int inch, String color, int portCount, String ratio, int price) {
        this.brand = brand;
        this.panel = panel;
        this.inch = inch;
        this.color = color;
        this.portCount = portCount;
        this.ratio = ratio;
        this.price = price;
    }

    public static Monitor makeSamsungMonitor() {
        return new Monitor("삼성", "IPS", 32, "black", 4, "16:9", 340000);
    }

    public static Monitor makeLgMonitor() {
        return new Monitor("LG", "IPS", 32, "black", 4, "16:9", 340000);
    }
}
