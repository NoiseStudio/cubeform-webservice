package pl.platrykp.cubeformservice.models;

import pl.platrykp.cubeformservice.requests.NewNewsRequest;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_news", updatable = false, nullable = false)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Timestamp date;


    public News() {
    }

    public News(NewNewsRequest request){
        this.title = request.title;
        this.content = request.content;
        this.date = new Timestamp(request.date);
    }

    public News(String title, String content, Timestamp date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
