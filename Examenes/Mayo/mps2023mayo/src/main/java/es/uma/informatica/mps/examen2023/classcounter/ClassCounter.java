package es.uma.informatica.mps.examen2023.classcounter;

import es.uma.informatica.mps.examen2023.classcounter.dictionary.Dictionary;

/**
 * Class that counts the number of classes in the packages of a Java project. The packages are
 * stored in a dictionary, being package names the keys and the values are the number of classes in
 * the packages. An example of entry would be: ("org.uma.mps2023.factorial", 2).
 *
 * The class provides a count() method that, taking as a parameter a package name, returns the number
 * of classes of the sub-packages it contains. For example, let us assume that the dictionary
 * contains following elements: ("org.uma.mps2023.factorial", 2), ("org.uma.mps2023.person", 4).
 * If the count() method is invoked with the parameter "org.uma.mps2023", the result should be 6.
 *
 * The class considers that the there is a limit in the number of packages to be processed in the
 * count() method.
 */
public class ClassCounter {
	
	private Dictionary<String, Long> classesInPackages;
	private Long processingLimit;
	
	public ClassCounter(Dictionary<String, Long> classesInPackages, Long processingLimit) {
		this.classesInPackages = classesInPackages;
		this.processingLimit = processingLimit;
	}
	
	public long count (String packageName) {
		if (packageName == null) {
			throw new IllegalArgumentException("The package name should not be null");
		}
		
		if (classesInPackages.isEmpty()) {
			return 0;
		}
		
		if (processingLimit != null && classesInPackages.numberOfItems() > processingLimit) {
			throw new IllegalStateException("Too many packages to process");
		}
		
		return classesInPackages.keyStream()
				.filter(key->key.startsWith(packageName))
				.mapToLong(key->{
					try {
						return classesInPackages.get(key);
					} catch (KeyNotFoundException e) {
						throw new IllegalStateException(e);
					}
			})
			.sum();
	}
}
