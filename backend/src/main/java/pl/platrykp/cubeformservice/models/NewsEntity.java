package pl.platrykp.cubeformservice.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.platrykp.cubeformservice.requests.NewNewsRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "news")
@EqualsAndHashCode(of = {"id"})
public class NewsEntity {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    @Getter @Setter
    private UUID id;

    @Column
    @Getter @Setter
    private String title;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter
    private String content;

    @Column
    @Getter @Setter
    private Timestamp date;


    public NewsEntity() {
    }

    public NewsEntity(NewNewsRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.date = new Timestamp(request.getDate());
    }

    public NewsEntity(String title, String content, Timestamp date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
