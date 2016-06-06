package com.example.germanriveros.androiddb;

/**
 * Created by germanriveros on 19-05-16.
 */
public class User
{
    int id;
    String name;


    public User(){}

    public User(int id, String name)
    {

        this.id = id;
        this.name = name;

    }

    public void setId(int id){ this.id = id; }

    public int getId(){ return id; }

    public void setName(String name){ this.name = name; }

    public String getName(){ return name; }

}
