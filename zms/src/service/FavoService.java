package service;

import entity.Favo;


public interface FavoService {
	
	public void update(Favo favo);

	public Favo getFavosByUsername(String username);
}
