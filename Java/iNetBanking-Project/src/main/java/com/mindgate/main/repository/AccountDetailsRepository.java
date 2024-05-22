package com.mindgate.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.exception.UserDetailsDoesNotExist;

@Repository
public class AccountDetailsRepository implements AccountDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	UserDetailsRepoInterface userRepo;

	private static final String GET_ALL_ACCOUNT = "select u.*,acc.* from user_details u inner join account_details acc on u.user_id = acc.user_id where acc.user_id=?";
	private static final String INSERT_ACCOUNT = "INSERT INTO account_details (user_id, account_type, actual_balance, overderaft_opted, overdraft_balance, overdraft_charges) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_ACCOUNT = "UPDATE account_details SET actual_balance = ?, overdraft_balance = ?, overdraft_charges=? WHERE account_number = ?";
	private static final String GET_BY_ACCOUNT="select u.*,acc.* from user_details u inner join account_details acc on u.user_id = acc.user_id where acc.account_number=?";

	private static final double OVERDRAFT_BALANCE = 50000.00;

	@Override
	public AccountDetails addAccount(AccountDetails accountDetails) {
		double overdraft = 0, odCharge = 0;

		if (accountDetails.getOvredraftedOpted().equals("yes")) {
			overdraft = OVERDRAFT_BALANCE;
		}else {
			accountDetails.setOvredraftedOpted("no");
		}

		// UserDetails userDetails =
		// userRepo.getByUserId(accountDetails.getUserDetails().getUserId());
		Object[] args = { accountDetails.getUserDetails().getUserId(), accountDetails.getAccountType(),
				accountDetails.getActualBalance(), accountDetails.getOvredraftedOpted(), overdraft, odCharge };

		int res = jdbcTemplate.update(INSERT_ACCOUNT, args);
		if (res > 0)
			return accountDetails;
		else
			return null;
	}

	@Override
	public List<AccountDetails> getById(int userId) {
		try {
			return jdbcTemplate.query(GET_ALL_ACCOUNT, new AccountDetailsRowMapper(), userId);
		} catch (Exception e) {
			// throw exception
			throw new UserDetailsDoesNotExist();
		}

	}
	
	
	@Override
    public AccountDetails getByAccount(long accounNumber) {
        // TODO Auto-generated method stub
        System.out.println("in Repso" +jdbcTemplate);
        
        return jdbcTemplate.queryForObject(GET_BY_ACCOUNT, new AccountDetailsRowMapper(), accounNumber);
    }
	
	

	@Override
	public AccountDetails updateAccount(AccountDetails accountDetails) {
		Object[] args = { accountDetails.getActualBalance(), accountDetails.getOverdraftBalance(),
				accountDetails.getCharges(), accountDetails.getAccountNumber() };
	//	System.out.println("----- JDBC TEMPLATE ------"+jdbcTemplate);
		int res = jdbcTemplate.update(UPDATE_ACCOUNT, args);
		
		//System.out.println("----- IN ACCOUNT UPDATE ------");
		
		if(res > 0) {
			return accountDetails;
		}
		return null;
	}

	

	
}
