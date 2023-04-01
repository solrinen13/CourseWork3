package my.works.courseworknumber3.Model;

public enum Color {
    RED("Красный"), BLUE("Голубой"), GREEN("Зеленый"), BLACK("Черный"), WHITE("Хобби");
    private final String text;

    Color(String text) {
        this.text = text;
    }
    public String getText(){
        return text;
    }
}
