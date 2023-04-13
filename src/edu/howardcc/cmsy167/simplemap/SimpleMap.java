package edu.howardcc.cmsy167.simplemap;

/**
 * Simplified version of the Map<K,V> interface
 */
public interface SimpleMap<K extends Comparable<K>, V> {

    /**
     * Remove all keys and values from the map
     */
    public void clear();

    /**
     * Add a new key value-pair to the map, overwriting the existing value, if it exists
     * @return the previous value, if one existed, otherwise null
     */
    public V put(K key, V value);

    /**
     * Retrieve a value from the map based on the given key
     * @return the value associated with the specified key, otherwise null
     */
    public V get(K key);

    /**
     * Check the map for a specific key
     * @return true if the map contains the given key
     */
    public boolean containsKey(K key);

    /**
     * Checks to see if the map is empty
     * @return true if the map does not contain any elements
     */
    public boolean isEmpty();
        
}
