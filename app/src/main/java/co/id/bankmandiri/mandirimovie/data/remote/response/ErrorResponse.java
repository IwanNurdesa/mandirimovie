package co.id.bankmandiri.mandirimovie.data.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Iwan Nurdesa on 24/06/2019
 */
public class ErrorResponse {

    @SerializedName("status_code")
    private Integer statusCode;
    @SerializedName("status_message")
    private String statusMessage;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
