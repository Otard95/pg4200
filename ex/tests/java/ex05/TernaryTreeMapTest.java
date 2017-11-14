package ex05;

import org.pg4200.datastructure.map.MyMap;
import org.pg4200.datastructure.map.MyMapTestTemplate;

public class TernaryTreeMapTest extends MyMapTestTemplate {
  
  @Override
  protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
    return new TernaryTreeMap<>();
  }
  
}
