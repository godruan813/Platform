package dao;

import entity.Favo;

public interface FavoDAO {

	void update(Favo favo);

	Favo queryFavosByUsername(String username);

}
