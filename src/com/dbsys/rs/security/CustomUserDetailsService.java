package com.dbsys.rs.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbsys.rs.lib.OutOfDateEntityException;
import com.dbsys.rs.lib.UnauthenticatedAccessException;
import com.dbsys.rs.lib.entity.Operator;
import com.dbsys.rs.lib.entity.Operator.Role;
import com.dbsys.rs.lib.entity.Token;

/**
 * Custom Authentication Provider.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Service("authService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private TokenService tokenService;

	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Token token = tokenService.get(username);
			Operator operator = token.getOperator();
			
			return new CustomUser(operator, username, getAuthorities(operator.getRole()));
		} catch (OutOfDateEntityException | UnauthenticatedAccessException e) {
			throw new UsernameNotFoundException("Username atau password salah");
		}
	}

	/**
	 * Mengambil role/authority operator.
	 * 
	 * @param role
	 * 
	 * @return daftar authority operator
	 */
	public static List<GrantedAuthority> getAuthorities(Role role) {
		List<GrantedAuthority> authList = new ArrayList<>();

		if (role.equals(Role.OPERATOR)) {
			authList.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));
		} else if (role.equals(Role.ADMIN)) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authList;
	}
}
