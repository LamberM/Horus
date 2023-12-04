package org.lamberm;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Block> list = new ArrayList<>();
        list.add(0, new Block() {
            @Override
            public String getColor() {
                return "silver";
            }

            @Override
            public String getMaterial() {
                return "metal";
            }
        });
        list.add(1, new Block() {
            @Override
            public String getColor() {
                return "brown";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        list.add(2, new Block() {
            @Override
            public String getColor() {
                return "brown";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        var wall = new Wall(list, "red", "wood");
        System.out.println("color:" + wall.findBlockByColor("silver").toString());
        System.out.println("material:" + wall.findBlocksByMaterial("wood").toString());
        System.out.println("count: "+ wall.count());
        System.out.println("blocks:" + wall.getBlocks());
    }
}