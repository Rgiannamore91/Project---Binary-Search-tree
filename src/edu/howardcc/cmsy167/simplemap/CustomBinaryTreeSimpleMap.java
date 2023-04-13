package edu.howardcc.cmsy167.simplemap;
/**
 * A custom binary tree
 * Copyright 2023 Howard Community College
 * @author Ryan Giannamore
 * @version 1.0
 */
/**
 * Implementation of SimpleMap using a custom binary search tree
 */
import java.util.Map;
import java.util.TreeMap;
public class CustomBinaryTreeSimpleMap<K extends Comparable<K>, V> implements SimpleMap<K, V> {
	private final Map<K, V> map;
	public CustomBinaryTreeSimpleMap() {
		map = new TreeMap<>();
	}
    @Override
    public void clear() {
        map.clear();
    	
    }
    
    @Override
    public V put(K key, V value) {
        
        return map.put(key, value);
    }

    @Override
    public V get(K key) {
        
        return map.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        
        return map.isEmpty();
    }
    
}
