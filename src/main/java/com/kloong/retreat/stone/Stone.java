package com.kloong.retreat.stone;

public class Stone {

    private final String name;
    private final String URI;

    public Stone(String name) {
        this.name = name;
        this.URI = name + "-stone";
    }

    public String getName() {
        return name;
    }

    public String getURI() {
        return URI;
    }
}
