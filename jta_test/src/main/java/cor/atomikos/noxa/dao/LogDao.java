package cor.atomikos.noxa.dao;

import cor.atomikos.noxa.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class LogDao {

	@Resource(name="jdbcTemplateB")
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user){
		jdbcTemplate.update("insert into a_log(name, age) VALUES (?, ?)", user.getName(), user.getAge());
	}
}
