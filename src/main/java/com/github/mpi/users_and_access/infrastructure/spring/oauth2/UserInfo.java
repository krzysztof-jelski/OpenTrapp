package com.github.mpi.users_and_access.infrastructure.spring.oauth2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonAutoDetect(fieldVisibility=Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("given_name")
    private String givenName;
    
    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("picture")
    private String picture;
    
    @JsonProperty("link")
    private String link;
    
    @JsonProperty("email")
    private String email;
                   
    @JsonProperty("verified_email")
    private Boolean verifiedEmail;
    
    @JsonProperty("hd")
    private String hd;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGender() {
        return gender;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public String getHd() {
        return hd;
    }
    
}