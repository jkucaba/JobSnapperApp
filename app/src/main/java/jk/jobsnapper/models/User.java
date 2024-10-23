package jk.jobsnapper.models;

public class User {

    private Long iduser;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private String role;
    private String sex;
    private int phone;
    private String abilities;
    private String profile;
    private byte[] profileImage;

    public User(Long iduser, String firstName, String lastName, String email, String birthday, String role, String sex, int phone, String abilities, String profile, byte[] profileImage) {
        this.iduser = iduser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.sex = sex;
        this.phone = phone;
        this.abilities = abilities;
        this.profile = profile;
        this.profileImage = profileImage;
    }
    public User(Long iduser, String firstName, String lastName, String email, String birthday, String role, String sex, int phone, String abilities, String profile) {
        this.iduser = iduser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.sex = sex;
        this.phone = phone;
        this.abilities = abilities;
        this.profile = profile;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
