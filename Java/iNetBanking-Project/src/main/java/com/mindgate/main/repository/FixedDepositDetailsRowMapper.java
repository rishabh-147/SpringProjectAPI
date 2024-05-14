package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.domain.UserDetails;

public class FixedDepositDetailsRowMapper implements RowMapper<FixedDepositDetails> {
    @Override
    public FixedDepositDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
        
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(rs.getInt("user_id"));
        
       AccountDetails accountDetails = new AccountDetails();
       accountDetails.setAccountNumber(rs.getInt("account_number"));

        fixedDepositDetails.setFixedDepositId(rs.getInt("fd_id"));
        fixedDepositDetails.setUserDetails(userDetails); // Assuming you have UserDetails constructor with userId
        fixedDepositDetails.setFixedDepositCreationDate(rs.getDate("fd_creation_date"));
        fixedDepositDetails.setFixedDepositAmount(rs.getDouble("fd_amount"));
        fixedDepositDetails.setFixedDepositTenure(rs.getDouble("fd_tenure"));
        fixedDepositDetails.setFixedDepositMaturityDate(rs.getDate("fd_maturity_date"));
        fixedDepositDetails.setRateOfInterest(rs.getDouble("rate_of_interest"));
        fixedDepositDetails.setAccountDetails(accountDetails); // Assuming you have AccountDetails constructor with accountId

        return fixedDepositDetails;
    }
}

