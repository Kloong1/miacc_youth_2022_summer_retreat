package com.kloong.retreat.stone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stones {

    private final Stone[] stones;

    private final Map<String, Stone> stoneNameMap = new HashMap<>();
    private final Map<String, Stone> stoneURIMap = new HashMap<>();

    public Stones(List<Stone> stones) {
        this.stones = stones.toArray(new Stone[0]);
        initStoneNameMap();
        initStoneURIMap();
    }

    private void initStoneNameMap() {
        for (Stone stone : stones) {
            stoneNameMap.put(stone.getName(), stone);
        }
    }

    private void initStoneURIMap() {
        for (Stone stone : stones) {
            stoneURIMap.put(stone.getURI(), stone);
        }
    }

    public boolean containsName(String name) {
        return stoneNameMap.containsKey(name);
    }

    public boolean containsURI(String URI) {
        return stoneURIMap.containsKey(URI);
    }

    public String getURI(String name) {
        if (!stoneNameMap.containsKey(name)) {
            return null;
        }
        return stoneNameMap.get(name).getURI();
    }

    public String getName(String URI) {
        if (!stoneURIMap.containsKey(URI)) {
            return null;
        }
        return stoneURIMap.get(URI).getName();
    }

}
