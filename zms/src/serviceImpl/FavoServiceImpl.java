package serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import dao.FavoDAO;

import entity.Favo;
import service.FavoService;

public class FavoServiceImpl implements FavoService {

	@Autowired
	FavoDAO favoDAO;
	
	public void update(Favo favo) {
		favoDAO.update(favo);
		
	}

	public Favo getFavosByUsername(String username) {
		return 	favoDAO.queryFavosByUsername(username);
	}

}
