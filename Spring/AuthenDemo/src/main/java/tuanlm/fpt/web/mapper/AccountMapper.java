package tuanlm.fpt.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tuanlm.fpt.web.model.Account;

@Mapper
public interface AccountMapper {
	@Select(
		"SELECT " +
			"USERNAME 	AS username, " +
			"PASSWORD 	AS password, " +
			"FULLNAME 	AS fullname, " +
			"EMAIL 		AS email " +
		"FROM " +
			"ACCOUNT AS A " +
		"WHERE " +
			"A.STATUS_ID = 1 " +
		"AND " +
			"A.USERNAME = #{username}"
	)
	Account getAccountByUsername(String username);
	
	@Select(
			"SELECT " +
				"R.ROLE " +
			"FROM " +
				"ACCOUNT_ROLE 	AS AR " +
			"INNER JOIN " +
				"ROLE 			AS R " +
			"ON " +
				"AR.ROLE_ID = R.ID " +
			"WHERE " +
				"AR.USERNAME = #{username}"
		)
	List<String> getRoleList(String username);
}
