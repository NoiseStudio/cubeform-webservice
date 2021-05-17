package pl.platrykp.cubeformservice.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "public", catalog = "cubeform")
public class NewsEntity {
    private int idNews;
    private String title;
    private String content;
    private Timestamp date;

    public NewsEntity() {
    }

    public NewsEntity(String title, String content, Timestamp date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Id
    @Column(name = "id_news")
    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return idNews == that.idNews && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNews, title, content, date);
    }
}
