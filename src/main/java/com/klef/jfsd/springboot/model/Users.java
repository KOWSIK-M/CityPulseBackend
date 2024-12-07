package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class Users{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "photo_url", length = 500)
    private String photoUrl = "https://res.cloudinary.com/dlgwz9tj9/image/upload/v1732813755/default%20profile.jpg";

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String contact;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(length = 255)
    private String address = "none";

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Boolean paid = false;

    @Column(nullable = false)
    private int feedbackCount = 0;

    @Column(nullable = false)
    private int ratingsGiven = 0;

    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public int getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(int feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public int getRatingsGiven() {
		return ratingsGiven;
	}

	public void setRatingsGiven(int ratingsGiven) {
		this.ratingsGiven = ratingsGiven;
	}

	
}
