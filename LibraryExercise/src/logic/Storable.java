package logic;

public interface Storable {

	int getID();

	// ids are given by the library
	void setID(int id);

	// updates the attributes of the storable
	boolean update(String... params);

	public String toFileFormat();
}
