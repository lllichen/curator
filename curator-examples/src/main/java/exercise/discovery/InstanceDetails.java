package exercise.discovery;

/**
 * Created by lichen@daojia.com on 2018-5-31.
 */

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * In a real application, the Service  payload will most likely
 * be more detailed than this,but, this gives a good example
 */
@JsonRootName( "details" )
public class InstanceDetails {

    private String description;

    public InstanceDetails(String description) {
        this.description = description;
    }

    public InstanceDetails() {
        this("");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
