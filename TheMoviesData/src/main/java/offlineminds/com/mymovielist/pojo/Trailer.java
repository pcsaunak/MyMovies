package offlineminds.com.mymovielist.pojo;

/**
 * Created by saunakc on 23/06/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<TrailerDetails> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerDetails> getResults() {
        return results;
    }

    public void setResults(List<TrailerDetails> results) {
        this.results = results;
    }

}

