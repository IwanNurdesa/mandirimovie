package co.id.bankmandiri.mandirimovie.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class Genre {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
