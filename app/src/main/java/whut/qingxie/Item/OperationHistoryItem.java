package whut.qingxie.Item;

/**
 * 操作历史
 */

public class OperationHistoryItem {
    private String title, date, text;

    public OperationHistoryItem(String title, String date, String text) {
        this.title = title;
        this.date = date;
        if (text.length() >= 70)
            this.text = text.substring(0, 70) + "...";
        else
            this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
