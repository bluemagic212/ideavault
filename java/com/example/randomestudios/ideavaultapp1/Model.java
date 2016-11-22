package com.example.randomestudios.ideavaultapp1;

/**
 * Created by Mrinali on 11/10/2016.
 */
public class Model {


    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;

    public int type;
    public int data;
    public String text;
    public String text2;


    public Model(int type, String text,String text2, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
        this.text2=text2;

    }

}

