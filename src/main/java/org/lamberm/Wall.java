package org.lamberm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Wall implements Structure, CompositeBlock {
    private final List<Block> blocks;

    private final Map<String, List<Block>> blocksByMaterial;
    private final Map<String, List<Block>> blocksByColor;

    private final String color;

    private final String material;

    public Wall(List<Block> blocks, String color, String material) {
        // inicjalizacja map za pomocą funkcji
        blocksByColor = getBlocksByColor(blocks);
        blocksByMaterial = getBlocksByMaterial(blocks);
        this.blocks = blocks;
        this.color = color;
        this.material = material;
    }


    @Override
    public Optional<Block> findBlockByColor(String color) {
        // opcja 1 lepsza złożoność obliczeniowa
        // O(n) = 2
        // gdzie:
        // O(n) -Złożoność obliczeniowa
        // n - elementy wejściowe

        if (blocksByColor.containsKey(color)) {
            return blocksByColor.get(color).stream().findFirst();
        } else {
            return Optional.empty();
        }

        // opcja 2 z gorszą złożonością obliczeniową ale lepszą pamięciową
        // O(n) = n
        // gdzie:
        // O(n) -Złożoność obliczeniowa
        // n - elementy wejściowe

//        for (Block block : blocks) {
//            if (block.getColor().equals(color)) {
//                return Optional.of(block);
//            }
//        }
//        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        // opcja 1 lepsza złożoność obliczeniowa
        // O(n) = 2
        // gdzie:
        // O(n) -Złożoność obliczeniowa
        // n - elementy wejściowe

        if (blocksByMaterial.containsKey(material)) {
            return blocksByMaterial.get(material);
        } else {
            return Collections.emptyList();
        }

        // opcja 2 z gorszą złożonością obliczeniową ale lepszą pamięciową
        // O(n) = n
        // gdzie:
        // O(n) -Złożoność obliczeniowa
        // n - elementy wejściowe

//        List<Block> blocksByMaterial = new LinkedList<>();
//        for (Block block : blocks) {
//            if (block.getMaterial().equals(material)) {
//                blocksByMaterial.add(block);
//            }
//        }
//        return blocksByMaterial;
    }

    @Override
    public int count() {
        return blocks.size();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    private Map<String, List<Block>> getBlocksByMaterial(List<Block> blocks) {
        Map<String, List<Block>> blocksMap = new HashMap<>();
        for (Block block : blocks) {
            // dodawanie list
            List<Block> list = new ArrayList<>();
            //sprawdzanie czy posiadamy już klucz
            if (blocksMap.containsKey(block.getMaterial())) {
                // jeśli posiadamy klucz to łączymy starą listę z nową listą + dodanie nowego materiału, dla tego samego klucza
                blocksMap.merge(block.getMaterial(), list, (oldlist, newList) -> {
                    newList.addAll(oldlist);
                    newList.add(block);
                    return newList;
                });
            } else {
                // jeśli nie posiadamy klucza, dodajemy obiekt do listy a następnie do mapy
                list.add(block);
                blocksMap.put(block.getMaterial(), list);
            }
        }
        return blocksMap;
    }

    private Map<String, List<Block>> getBlocksByColor(List<Block> blocks) {
        Map<String, List<Block>> blocksMap = new HashMap<>();
        for (Block block : blocks) {
            // dodawanie list
            List<Block> list = new ArrayList<>();
            //sprawdzanie czy posiadamy już klucz
            if (blocksMap.containsKey(block.getColor())) {
                // jeśli posiadamy klucz to łączymy starą listę z nową listą + dodanie nowego materiału, dla tego samego klucza
                blocksMap.merge(block.getColor(), list, (oldlist, newList) -> {
                    newList.addAll(oldlist);
                    newList.add(block);
                    return newList;
                });
            } else {
                // jeśli nie posiadamy klucza, dodajemy obiekt do listy a następnie do mapy
                list.add(block);
                blocksMap.put(block.getColor(), list);
            }
        }
        return blocksMap;
    }
}