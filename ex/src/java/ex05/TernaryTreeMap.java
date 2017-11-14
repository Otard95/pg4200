package ex05;

import org.pg4200.datastructure.map.tree.MyTreeBasedMap;

public class TernaryTreeMap<K extends Comparable<K>, V> implements MyTreeBasedMap<K, V> {
  
  private class Node {
    
    Node left;
    Node middle;
    Node right;
    
    K KeyHigh;
    K KeyLow;
    V ValueHigh;
    V ValueLow;
    
    public void Add (K key, V value) {
      if (KeyHigh == null) { KeyHigh = key; ValueHigh = value; }
      if (KeyLow == null) { KeyLow = key; ValueLow = value; }
    }
    
    public void Swap () {
      K tempK = KeyLow;
      V tempV = ValueLow;
      KeyLow = KeyHigh;
      ValueLow = ValueHigh;
      KeyHigh = tempK;
      ValueHigh = tempV;
    }
    
    public V Get (K key) {
      if (KeyHigh == key) return ValueHigh;
      if (KeyLow == key) return ValueLow;
      return null;
    }
  
    public K GetKeyMax() {
      if (KeyHigh != null) return KeyHigh;
      return KeyLow;
    }
  
    public K GetKeyMin() {
      if (KeyLow != null) return KeyLow;
      return KeyHigh;
    }
    
    public void Delete (K key) {
      if (key == KeyHigh) { KeyHigh = null; ValueHigh = null; }
      if (key == KeyLow) { KeyLow = null; ValueLow = null; }
    }
    
    public boolean HasKey(K key) {
      return key == KeyLow || key == KeyHigh;
    }
    
    public boolean HasChildren() {
      return left != null || middle != null || right != null;
    }
    
    public boolean IsEmpty () {
      return KeyLow == null && KeyHigh == null;
    }
    
  }
  
  protected Node root;
  
  protected int size = 0;
  
  @Override
  public int getMaxTreeDepth() {
    return 0;
  }
  
  @Override
  public void put(K key, V value) {
    root = put(key, value, root);
  }
  
  private Node put(K key, V value, Node subRoot) {
    
    // If subRoot doesn't exist create it
    if (subRoot == null) {
      Node node = new Node();
      node.KeyHigh = key;
      node.ValueHigh = value;
      size++;
      return node;
    }
    
    // If key is equal to any of the subRoot's keys overwrite the value
    if (subRoot.KeyLow == key) {
      subRoot.ValueLow = value;
      return subRoot;
    }
  
    if (subRoot.KeyHigh == key) {
      subRoot.ValueHigh = value;
      return subRoot;
    }
    
    // If the subRoot contains only one key-value pair:
    // add the key and value to this node and swap the values if necessary
    if (subRoot.KeyHigh == null || subRoot.KeyLow == null) {
      subRoot.Add(key, value);
      if (subRoot.KeyLow.compareTo(subRoot.KeyHigh) > 0) subRoot.Swap();
      size++;
      return subRoot;
    }
    
    // If we get to this point the subRoot has two values so we need to recurse down the correct pointer
    if (key.compareTo(subRoot.KeyLow) < 0) {
      subRoot.left = put(key, value, subRoot.left);
      return subRoot;
    }
    
    if (key.compareTo(subRoot.KeyHigh) > 0) {
      subRoot.right = put(key, value, subRoot.right);
      return subRoot;
    }
    
    subRoot.middle = put(key, value, subRoot.middle);
    return subRoot;
    
  }
  
  @Override
  public void delete(K key) {
    root = delete(key, root);
  }
  
  private Node delete(K key, Node subRoot) {
    
    // if node is null, key was not found
    if (subRoot == null) return null;
  
    // ## check if key is less than KeyLow or grater than KeyHigh
    // if less than low recurse to the left
    if (subRoot.KeyLow != null && key.compareTo(subRoot.KeyLow) < 0) {
      subRoot.left = delete(key, subRoot.left);
      return subRoot;
    }
    // if grater than high recurse to the right
    if (subRoot.KeyHigh != null && key.compareTo(subRoot.KeyHigh) > 0) {
      subRoot.right = delete(key, subRoot.right);
      return subRoot;
    }
  
    // the key is somewhere in the middle check if this node has the key
    // check if this this node has the key
    if (subRoot.HasKey(key)) {
      // delete the key
      subRoot.Delete(key);
      // if this node has no keys left delete the node
      if (subRoot.IsEmpty()) { size--; return null; }
      // if this node has no children return here
      if (!subRoot.HasChildren()) { size--; return subRoot; }
      
      fill(subRoot);
      
      return subRoot;
      
    } // END subRoot has key
    
    subRoot.middle = delete(key, subRoot.middle);
    return subRoot;
  }
  
  private void fill (Node node) {
  
    int route = 1; // 0 = left => 2 = right
    K keyToReplace;
    
    // The process of filling the node depends on what key-value is missing
    if (node.KeyHigh == null) { // If KeyHigh is missing
      
      Node replacement = getLargest(node.middle); // We try getting the greatest node in the centre
      if (replacement == null) {
        replacement = getSmallest(node.right); // if its null we ty the smallest on the right
        route = 2;
      }
      if (replacement == null) {
        replacement = getLargest(node.left); // and last the greatest on the left
        route = 0;
      }
      
      if (route != 2) {
        node.KeyHigh = replacement.GetKeyMax();
        node.ValueHigh = replacement.Get(node.KeyHigh);
      } else {
        node.KeyHigh = replacement.GetKeyMin();
        node.ValueHigh = replacement.Get(node.KeyHigh);
      }
  
      keyToReplace = node.KeyHigh;
      
    } else { // If KeyLow is missing
  
      Node replacement = getSmallest(node.middle); // We try getting the smallest node in the centre
      if (replacement == null) {
        replacement = getLargest(node.left); // if its null we try the greatest on the left
        route = 0;
      }
      if (replacement == null) {
        replacement = getSmallest(node.right); // and last the smallest on the right
        route = 2;
      }
  
      if (route != 0) {
        node.KeyLow = replacement.GetKeyMin();
        node.ValueLow = replacement.Get(node.KeyLow);
      } else {
        node.KeyLow = replacement.GetKeyMax();
        node.ValueLow = replacement.Get(node.KeyLow);
      }
  
      keyToReplace = node.KeyLow;
    
    }
    
    switch (route) {
      case 0:
        node.left = delete(keyToReplace, node.left);
        break;
      case 1:
        node.middle = delete(keyToReplace, node.middle);
        break;
      case 2:
        node.right = delete(keyToReplace, node.right);
        break;
      default:
        throw new IndexOutOfBoundsException();
    }
    
  }
  
  private Node getSmallest(Node subRoot) {
  
    if (subRoot == null) return null;
    
    // if subRoot has left recurse down to the left
    if (subRoot.left != null) return getSmallest(subRoot.left);
    // No left node, return this
    return subRoot;
    
  }
  
  private Node getLargest(Node subRoot) {
    
    if (subRoot == null) return null;
    
    // if subRoot has left recurse down to the left
    if (subRoot.right != null) return getSmallest(subRoot.right);
    // No left node, return this
    return subRoot;
    
  }
  
  @Override
  public V get(K key) {
    
    return get(key, root);
    
  }
  
  private V get (K key, Node subRoot) {
    
    // if this node is null the key is not in the tree
    if (subRoot == null) return null;
    
    // ## check if key is less than KeyLow or grater than KeyHigh
    // if less than low recurse to the left
    if (subRoot.KeyLow != null && key.compareTo(subRoot.KeyLow) < 0) return get(key, subRoot.left);
    // if grater than high recurse to the right
    if (subRoot.KeyHigh != null && key.compareTo(subRoot.KeyHigh) > 0) return get(key, subRoot.right);
    
    // the value is somewhere in the middle check if this node has the value
    V value = subRoot.Get(key);
    if (value != null) return value; // return if not null
    
    // this node doesn't have the value recurse down the middle
    return get(key, subRoot.middle);
    
  }
  
  @Override
  public int size() {
    return size;
  }
  
  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}
