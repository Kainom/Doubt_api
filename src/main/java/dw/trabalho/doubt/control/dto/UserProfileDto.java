package dw.trabalho.doubt.control.dto;

public class UserProfileDto {
  private String username;
  private String about;
  private String country;

  public UserProfileDto() {
  }

  public UserProfileDto(String username, String about, String country) {
    this.username = username;
    this.about = about;
    this.country = country;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
