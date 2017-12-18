package logic;

public interface Storable {

	int getID();

	// updates the attributes of the storable
	boolean update(String... params);

}
