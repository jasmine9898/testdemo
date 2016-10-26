package redistemplate.dao;

import redistemplate.obj.User;

public interface UserDAO {
	User getUser(final long id);
	void saveUser(final User user);
}
