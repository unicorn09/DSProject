package com.harshraj.dsproject;

public class Model {

    private String word,meaning;

    public Model(String word,String meaning)
    {
        this.word=word;
        this.meaning=meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}
