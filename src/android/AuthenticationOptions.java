package android;


import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class AuthenticationOptions {

    private String authType;
    private String username;
    private String password;

    public AuthenticationOptions(JSONObject options) {
        try {
            if (options.has("authType")) {
                this.setAuthType(options.getString("authType"));
            }
            if (options.has("username")) {
                this.setUsername(options.getString("username"));
            }
            if (options.has("password")) {
                this.setPassword(options.getString("password"));
            }
        } catch (JSONException e){
            // If there is any error then ensure that auth type is unset
            this.setAuthType("");
        }
    }

    /**
     * Flag indicating authentication credentials have been set
     *
     * @return boolean flag indicating if there are authentication credentials
     */
    public boolean hasCredentials() {
        return authType.equals("basic") || authType.equals("bearer");
    }

    public String getEncodedAuthorization() {
        if (authType.equals("bearer")) {
            return "Bearer " + this.password;
        }

        return "Basic " + Base64.encodeToString((this.username + ":" + this.password)
                .getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
