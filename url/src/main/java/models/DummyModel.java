package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nidhi Mishra on 10/02/16.
 */
public class DummyModel {

    @JsonProperty("id")
    public String id;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DummyModel(String id) {
        this.id = id;
    }

    public DummyModel() {
    }

}
