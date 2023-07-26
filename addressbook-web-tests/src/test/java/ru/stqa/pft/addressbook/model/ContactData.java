package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    @Type(type = "string")
    private String firstName;
    @Expose
    @Column(name = "lastname")
    @Type(type = "string")
    private String lastName;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;
    @Transient
    private String allEmails;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public ContactData() {

    }

    public File getPhoto() {
        try {
            return new File(photo);
        } catch (NullPointerException exception) {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                ", secondEmail='" + secondEmail + '\'' +
                ", thirdEmail='" + thirdEmail + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", groups=" + groups +
                ", allPhones='" + allPhones + '\'' +
                '}';
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String phoneNumber) {
        this.homePhone = phoneNumber;
        return this;
    }

    public ContactData withMobilePhone(String phoneNumber) {
        this.mobilePhone = phoneNumber;
        return this;
    }

    public ContactData withWorkPhone(String phoneNumber) {
        this.workPhone = phoneNumber;
        return this;
    }

    public ContactData withAllPhones(String phones) {
        this.allPhones = phones;
        return this;
    }

    public ContactData withFirstEmail(String email) {
        this.firstEmail = email;
        return this;
    }

    public ContactData withSecondEmail(String email) {
        this.secondEmail = email;
        return this;
    }

    public ContactData withThirdEmail(String email) {
        this.thirdEmail = email;
        return this;
    }

    public ContactData withAllEmails(String email) {
        this.allEmails = email;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(homePhone, that.homePhone)) return false;
        if (!Objects.equals(firstEmail, that.firstEmail)) return false;
        return Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (firstEmail != null ? firstEmail.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
