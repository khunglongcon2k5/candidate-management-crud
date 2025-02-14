/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
	T getById(int id) throws SQLException;
	List<T> getAll() throws SQLException;
	int update(int id, T candidate) throws SQLException;
	int removeById(int id, T candidate) throws SQLException;
	int insert(T candidate) throws SQLException;
}
