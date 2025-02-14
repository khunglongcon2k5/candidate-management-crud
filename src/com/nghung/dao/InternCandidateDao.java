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

import com.nghung.model.InternCandidate;
import com.nghung.util.JDBCutil;

public class InternCandidateDao implements IDao<InternCandidate> {

	@Override
	public InternCandidate getById(int id) throws SQLException {
		InternCandidate internCandidate = null;
		String sql = "SELECT * FROM Candidate c JOIN InternCandidate i ON c.id = i.id WHERE c.id = ?";
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, id);
			
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					internCandidate = new InternCandidate();
					internCandidate.setfullName(rs.getString("fullName"));
					internCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					internCandidate.setAddress(rs.getString("address"));
					internCandidate.setHometown(rs.getString("hometown"));
					internCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					internCandidate.setEmailAddress(rs.getString("emailAddress"));
					internCandidate.setMajor(rs.getString("major"));
					internCandidate.setCurrentSemester(rs.getInt("currentSemester"));
					internCandidate.setSchoolName(rs.getString("schoolName"));
					internCandidate.setExpectedGraduationDate(rs.getString("expectedGraduationDate"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return internCandidate;
	}

	@Override
	public List<InternCandidate> getAll() throws SQLException {
		List<InternCandidate> data = new ArrayList<>();
		String sql = "SELECT * FROM Candidate c JOIN InternCandidate i ON c.id = i.id";
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					InternCandidate internCandidate = new InternCandidate();
					internCandidate.setfullName(rs.getString("name"));
					internCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					internCandidate.setAddress(rs.getString("address"));
					internCandidate.setHometown(rs.getString("hometown"));
					internCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					internCandidate.setEmailAddress(rs.getString("emailAddress"));
					internCandidate.setMajor(rs.getString("major"));
					internCandidate.setCurrentSemester(rs.getInt("currentSemester"));
					internCandidate.setSchoolName(rs.getString("schoolName"));
					internCandidate.setExpectedGraduationDate(rs.getString("expectedGraduationDate"));
					data.add(internCandidate);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public int update(int id, InternCandidate candidate) throws SQLException {
		int result = 0;
		String sql = "UPDATE InternCandidate SET major = ?, currentSemester = ?, schoolName = ?, expectedGraduationDate = ? WHERE id = ?";
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, candidate.getMajor());
			preparedStatement.setInt(2, candidate.getCurrentSemester());
			preparedStatement.setString(3, candidate.getSchoolName());
			preparedStatement.setString(4, candidate.getExpectedGraduationDate());
			preparedStatement.setInt(5, id);
			
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int removeById(int id, InternCandidate candidate) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM InternCandidate WHERE id = ?";
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insert(InternCandidate internCandidate) throws SQLException {
		int result = 0;
		Connection connection = null;
		String insertCandidate = "INSERT INTO Candidate(name, dateOfBirth, address, hometown, phoneNumber, emailAddress) VALUES(?, ?, ?, ?, ?, ?)";
		String insertInternCandidate = "INSERT INTO InternCandidate(id, major, currentSemester, schoolName, expectedGraduationDate) VALUES(?, ?, ?, ?)";
		
		try {
			connection = JDBCutil.getConnection();
			try (PreparedStatement psCandidate = connection.prepareStatement(insertCandidate, PreparedStatement.RETURN_GENERATED_KEYS);
				 PreparedStatement psInternCandidate = connection.prepareStatement(insertInternCandidate)) {
				
				connection.setAutoCommit(false);
				
				psCandidate.setString(1, internCandidate.getfullName());
				psCandidate.setString(2, internCandidate.getDateOfBirth());
				psCandidate.setString(3, internCandidate.getAddress());
				psCandidate.setString(4, internCandidate.getHometown());
				psCandidate.setString(5, internCandidate.getPhoneNumber());
				psCandidate.setString(6, internCandidate.getEmailAddress());
				result = psCandidate.executeUpdate();
				
				try (ResultSet rs = psCandidate.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						psInternCandidate.setInt(1, id);
						psInternCandidate.setString(2, internCandidate.getMajor());
						psInternCandidate.setInt(3, internCandidate.getCurrentSemester());
						psInternCandidate.setString(4, internCandidate.getSchoolName());
						psInternCandidate.setString(5, internCandidate.getExpectedGraduationDate());
						result = psInternCandidate.executeUpdate();
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
