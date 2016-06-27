package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nidhi Mishra on 18/06/16.
 */
@Entity
@Table(name="url_mapping")
public class UrlMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "long_url")
    private String longUrl;

    public UrlMapping(String longUrl) {
        this.longUrl = longUrl;
    }

    public UrlMapping() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
