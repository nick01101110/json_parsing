package org.dalol.retrofit2_restapidemo.model.pojo;

import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.io.Serializable;

public class Friend implements Serializable {

    @Expose
    private int id;

    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = (Integer) in.readObject();
        name = (String) in.readObject();
    }
}
