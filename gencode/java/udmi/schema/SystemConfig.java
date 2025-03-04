
package udmi.schema;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * System Config
 * <p>
 * [System Config Documentation](../docs/messages/system.md#config)
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "min_loglevel",
    "metrics_rate_sec"
})
@Generated("jsonschema2pojo")
public class SystemConfig {

    /**
     * The minimum loglevel for reporting log messages below which log entries should not be sent. Default to 300.
     * 
     */
    @JsonProperty("min_loglevel")
    @JsonPropertyDescription("The minimum loglevel for reporting log messages below which log entries should not be sent. Default to 300.")
    public Integer min_loglevel = 300;
    /**
     * The rate at which the system should send system event metric updates. 0 indicates no updates.
     * 
     */
    @JsonProperty("metrics_rate_sec")
    @JsonPropertyDescription("The rate at which the system should send system event metric updates. 0 indicates no updates.")
    public Integer metrics_rate_sec = 600;

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.metrics_rate_sec == null)? 0 :this.metrics_rate_sec.hashCode()));
        result = ((result* 31)+((this.min_loglevel == null)? 0 :this.min_loglevel.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SystemConfig) == false) {
            return false;
        }
        SystemConfig rhs = ((SystemConfig) other);
        return (((this.metrics_rate_sec == rhs.metrics_rate_sec)||((this.metrics_rate_sec!= null)&&this.metrics_rate_sec.equals(rhs.metrics_rate_sec)))&&((this.min_loglevel == rhs.min_loglevel)||((this.min_loglevel!= null)&&this.min_loglevel.equals(rhs.min_loglevel))));
    }

}
