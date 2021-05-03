package sabinabaghiu.plannerzen.ui.lists;

public class SpinnerRow {
    private String text;
    private int iconId;

    public SpinnerRow(String text, int iconId) {
        this.text = text;
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public int getIconId() {
        return iconId;
    }
}
