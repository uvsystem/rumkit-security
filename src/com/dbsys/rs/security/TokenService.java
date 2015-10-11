package com.dbsys.rs.security;

import com.dbsys.rs.lib.OutOfDateEntityException;
import com.dbsys.rs.lib.UnauthenticatedAccessException;
import com.dbsys.rs.lib.entity.Token;

/**
 * Kelas untuk mengelola token.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface TokenService {

	/**
	 * Mengambil data token berdasarkan token string hasil generate.
	 * 
	 * @param tokenString
	 * 
	 * @return data Token
	 * 
	 * @throws OutOfDateEntityException token sudah expire.
	 * @throws UnauthenticatedAccessException  token sudah dikunci
	 */
	Token get(String tokenString) throws OutOfDateEntityException, UnauthenticatedAccessException;

}
