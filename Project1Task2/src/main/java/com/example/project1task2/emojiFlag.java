package com.example.project1task2;

public class emojiFlag {
    private String name;
    private String code;
    private String emoji;
    private String unicode;
    private String image;

    //constructor
    public emojiFlag(String name, String code, String emoji, String unicode, String image) {
        this.name = name;
        this.code = code;
        this.emoji = emoji;
        this.unicode = unicode;
        this.image = image;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

