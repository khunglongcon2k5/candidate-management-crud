/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nghung.model.FresherCandidate;
import com.nghung.util.JDBCutil;

public class FresherCandidateDao implements IDao<FresherCandidate> {

	@Override
	public FresherCandidate getById(int id) throws SQLException {
		FresherCandidate fresherCandidate = null;
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 "SELECT * FROM Candidate c JOIN FresherCandidate f ON c.id = f.id WHERE c.id = ? ")) {
			
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					fresherCandidate = new FresherCandidate();
					fresherCandidate.setfullName(rs.getString("fullName"));
					fresherCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					fresherCandidate.setAddress(rs.getString("address"));
					fresherCandidate.setHometown(rs.getString("hometown"));
					fresherCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					fresherCandidate.setEmailAddress(rs.getString("emailAddress"));
					fresherCandidate.setGraduationDate(rs.getString("graduationDate"));
					fresherCandidate.setGraduationRank(rs.getString("graduationRank"));
					fresherCandidate.setGraduationSchool(rs.getString("graduationSchool"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fresherCandidate;
	}

	@Override
	public List<FresherCandidate> getAll() throws SQLException {
		List<FresherCandidate> data = new ArrayList<>();
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 "SELECT * FROM Candidate c JOIN FresherCandidate f ON c.id = f.id")) {
			
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					FresherCandidate fresherCandidate = new FresherCandidate();
					fresherCandidate.setfullName(rs.getString("fullName"));
					fresherCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					fresherCandidate.setAddress(rs.getString("address"));
					fresherCandidate.setHometown(rs.getString("hometown"));
					fresherCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					fresherCandidate.setEmailAddress(rs.getString("emailAddress"));
					data.add(fresherCandidate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public int update(int id, FresherCandidate candidate) throws SQLException {
		int result = 0;
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 "UPDATE FresherCandidate SET graduationDate = ?, graduationRank = ?, graduationSchool = ? WHERE id = ?")) {
			
			preparedStatement.setString(1, candidate.getGraduationDate());
			preparedStatement.setString(2, candidate.getGraduationRank());
			preparedStatement.setString(3, candidate.getGraduationSchool());
			preparedStatement.setInt(4, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int removeById(int id, FresherCandidate fresherCandidate) throws SQLException {
		int result = 0;
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FresherCandidate WHERE id = ? ")) {
			
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insert(FresherCandidate candidate) throws SQLException {
		int result = 0;
		Connection connection = null;
		
		try {
			connection = JDBCutil.getConnection();
			try (PreparedStatement psCandidate = connection.prepareStatement(
					"INSERT INTO Candidate(name, dateOfBirth, address, hometown, phoneNumber, emailAddress) VALUES(?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
				 PreparedStatement psFresherCandidate = connection.prepareStatement(
					"INSERT INTO FresherCandidate(id, graduationDate, graduationRank, graduationSchool) VALUES(?, ?, ?, ?)")) {
				
				connection.setAutoCommit(false);
				
				psCandidate.setString(1, candidate.getfullName());
				psCandidate.setString(2, candidate.getDateOfBirth());
				psCandidate.setString(3, candidate.getAddress());
				psCandidate.setString(4, candidate.getHometown());
				psCandidate.setString(5, candidate.getPhoneNumber());
				psCandidate.setString(6, candidate.getEmailAddress());
				result = psCandidate.executeUpdate();
				
				try (ResultSet rs = psCandidate.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						psFresherCandidate.setInt(1, id);
						psFresherCandidate.setString(2, candidate.getGraduationDate());
						psFresherCandidate.setString(3, candidate.getGraduationRank());
						psFresherCandidate.setString(4, candidate.getGraduationSchool());
						result = psFresherCandidate.executeUpdate();
					}
				} 
				
				connection.commit();
			} catch (Exception e) {
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeConnection(connection);
		}
		
		return result;
	}
}
