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

import com.nghung.model.ExperienceCandidate;
import com.nghung.util.JDBCutil;

public class ExperienceCandidateDao implements IDao<ExperienceCandidate> {

	@Override
	public ExperienceCandidate getById(int id) throws SQLException {
		ExperienceCandidate experienceCandidate = null;

		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM ExperienceCandidate E JOIN Candidate C ON E.id = C.id WHERE C.id = ? ")) {

			preparedStatement.setInt(1, id);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					experienceCandidate = new ExperienceCandidate();
					experienceCandidate.setfullName(rs.getString("fullName"));
					experienceCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					experienceCandidate.setAddress(rs.getString("address"));
					experienceCandidate.setHometown(rs.getString("hometown"));
					experienceCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					experienceCandidate.setEmailAddress(rs.getString("emailAddress"));
					experienceCandidate.setYearsOfExperience(rs.getDouble("yearsOfExperience"));
					experienceCandidate.setSpecializedSkills(rs.getString("specializedSkills"));
					experienceCandidate.setLastWorkplace(rs.getString("lastWorkplace"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return experienceCandidate;
	}

	@Override
	public List<ExperienceCandidate> getAll() throws SQLException {
		List<ExperienceCandidate> data = new ArrayList<>();
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ExperienceCandidate E JOIN Candidate C ON E.id = C.id")) {

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					ExperienceCandidate experienceCandidate = new ExperienceCandidate();
					experienceCandidate.setfullName(rs.getString("fullName"));
					experienceCandidate.setDateOfBirth(rs.getString("dateOfBirth"));
					experienceCandidate.setAddress(rs.getString("address"));
					experienceCandidate.setHometown(rs.getString("hometown"));
					experienceCandidate.setPhoneNumber(rs.getString("phoneNumber"));
					experienceCandidate.setEmailAddress(rs.getString("emailAddress"));
					experienceCandidate.setYearsOfExperience(rs.getDouble("yearsOfExperience"));
					experienceCandidate.setSpecializedSkills(rs.getString("specializedSkills"));
					experienceCandidate.setLastWorkplace(rs.getString("lastWorkplace"));
					data.add(experienceCandidate);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public int update(int id, ExperienceCandidate expCandidate) throws SQLException {
		int result = 0;
		
		try (Connection connection = JDBCutil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE ExperienceCandidate SET yearsOfExperience = ?, specializedSkills = ?, lastWorkplace = ? WHERE id = ?")){
			
			preparedStatement.setDouble(1, expCandidate.getYearsOfExperience());
			preparedStatement.setString(2, expCandidate.getSpecializedSkills());
			preparedStatement.setString(3, expCandidate.getLastWorkplace());
			preparedStatement.setInt(4, id);
			
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int removeById(int id, ExperienceCandidate expCandidate) throws SQLException {
		int result = 0;
		Connection connection = null;
		
		try {
			connection = JDBCutil.getConnection();
			connection.setAutoCommit(false);
			try (PreparedStatement pSexpCandidate = connection.prepareStatement("DELETE FROM ExperienceCandidate WHERE id = ?");
				 PreparedStatement pScandidate = connection.prepareStatement("DELETE FROM Candidate WHERE id = ?")) {
				pSexpCandidate.setInt(1, id);
				pSexpCandidate.executeUpdate();
				
				pScandidate.setInt(1, id);
				pScandidate.executeUpdate();
				
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

	@Override
	public int insert(ExperienceCandidate candidate) throws SQLException {
		int result = 0;
		Connection connection = null;

		try {
			connection = JDBCutil.getConnection();
			try (PreparedStatement psCandidate = connection.prepareStatement(
					"INSERT INTO Candidate(fullName, dateOfBirth, address, hometown, phoneNumber, emailAddress) VALUES(?, ?, ?, ?, ?, ?)",
				  PreparedStatement.RETURN_GENERATED_KEYS);
				 PreparedStatement psExpCandidate = connection.prepareStatement(
					"INSERT INTO ExperienceCandidate(id, yearsOfExperience, specializedSkills, lastWorkplace) VALUES(?, ?, ?, ?)")) {

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
						int generatedId = rs.getInt(1);
						psExpCandidate.setInt(1, generatedId);
						psExpCandidate.setDouble(2, candidate.getYearsOfExperience());
						psExpCandidate.setString(3, candidate.getSpecializedSkills());
						psExpCandidate.setString(4, candidate.getLastWorkplace());
						result = psExpCandidate.executeUpdate();
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
