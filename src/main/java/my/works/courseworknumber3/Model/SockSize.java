package my.works.courseworknumber3.Model;

public enum SockSize {
    SIZE_36(36.0),
    SIZE_36_5(36.5),
    SIZE_37(37.0);

    private final double value;

    SockSize(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static SockSize getSockSize(double value) {
        for (SockSize sockSize : SockSize.values()) {
            if (sockSize.getValue() == value) {
                return sockSize;
            }
        }
        return null;
    }
}
