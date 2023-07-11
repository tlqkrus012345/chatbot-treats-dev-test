package com.stchatbot.api.brand;

public enum Status implements EnumMapperType{

    ACTIVE("공개"),
    INACTIVE("비공개");

    public String text;

    Status(String text) {
        this.text = text;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getText() {
        return text;
    }
}
