package vnu.uet.tuan.myuet.Models;

/**
 * Created by Admin on 15/9/2016.
 */
public class Noti_data {
    private String title;
    private String content;
    private String link;

    public Noti_data(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Noti_data(String title, String content, String link) {
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
