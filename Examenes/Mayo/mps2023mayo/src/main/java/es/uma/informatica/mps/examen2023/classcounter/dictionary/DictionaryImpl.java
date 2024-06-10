package es.uma.informatica.mps.examen2023.classcounter.dictionary;

import es.uma.informatica.mps.examen2023.classcounter.KeyNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DictionaryImpl<K,V> implements Dictionary<K, V> {
	
	private Map<K,V> map = new HashMap<>();

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public V get(K value) throws KeyNotFoundException {
		if (map.containsKey(value)) {
			return map.get(value);
		} else {
			throw new KeyNotFoundException();
		}
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public int numberOfItems() {
		return map.size();
	}

	@Override
	public Stream<K> keyStream() {
		return map.keySet().stream();
	}
	

}
