/*
 * Copyright (c) 2015-2016 Filippo Engidashet. All Rights Reserved.
 * <p>
 *  Save to the extent permitted by law, you may not use, copy, modify,
 *  distribute or create derivative works of this material or any part
 *  of it without the prior written consent of Filippo Engidashet.
 *  <p>
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 */

package org.dalol.retrofit2_restapidemo.model.pojo;

import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.io.Serializable;

/**
 id
 isActive
 balance
 picture
 age
 eyeColor
 name
 gender
 company
 email
 phone
 address
 about
 registered
 favoriteFruit

 **/
public class User implements Serializable {

    private static final long serialVersionUID = 111696345129311948L;

    @Expose
    private String id;

    @Expose
    private boolean isActive;

    @Expose
    private String balance;

    @Expose
    private String picture;

    @Expose
    private int age;

    @Expose
    private String eyeColor;

    @Expose
    private String name;

    @Expose
    private String gender;

    @Expose
    private String company;

    @Expose
    private String email;

    @Expose
    private String phone;

    @Expose
    private String address;

    @Expose
    private String about;

    @Expose
    private String registered;

    @Expose
    private String favoriteFruit;

    @Expose
    private Friend[] friends;

    @Expose
    private String[] tags;

    private boolean isFromDatabase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public boolean isFromDatabase() {
        return isFromDatabase;
    }

    public void setFromDatabase(boolean fromDatabase) {
        isFromDatabase = fromDatabase;
    }

    public Friend[] getFriends() {
        return friends;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(id);
        out.writeObject(isActive);
        out.writeObject(balance);
        out.writeObject(picture);
        out.writeObject(age);
        out.writeObject(eyeColor);
        out.writeObject(name);
        out.writeObject(gender);
        out.writeObject(company);
        out.writeObject(email);
        out.writeObject(phone);
        out.writeObject(address);
        out.writeObject(about);
        out.writeObject(registered);
        out.writeObject(favoriteFruit);
        out.writeObject(friends);
        out.writeObject(tags);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        isActive = (boolean) in.readObject();
        balance = (String) in.readObject();
        picture = (String) in.readObject();
        age= (Integer) in.readObject();
        eyeColor = (String) in.readObject();
        name = (String) in.readObject();
        gender = (String) in.readObject();
        company = (String) in.readObject();
        email = (String) in.readObject();
        phone = (String) in.readObject();
        address = (String) in.readObject();
        about = (String) in.readObject();
        registered = (String) in.readObject();
        favoriteFruit = (String) in.readObject();
        friends = (Friend[]) in.readObject();
        tags = (String[]) in.readObject();
    }
}
