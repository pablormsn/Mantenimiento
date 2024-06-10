package es.uma.informatica.mps.examen2023.classcounter.dictionary;

import es.uma.informatica.mps.examen2023.classcounter.KeyNotFoundException;
import java.util.stream.Stream;

public interface Dictionary<K,V> {
	  void put(K key, V value) ;
	  V get(K value) throws KeyNotFoundException;
	  boolean isEmpty() ;
	  int numberOfItems() ;
	  Stream<K> keyStream();
	}
