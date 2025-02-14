/*
 * 
 * @author NguyenHung
 * @date Jan 6, 2025
 * @version 1.0
 *
 */

package com.nghung.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.nghung.dao.ExperienceCandidateDao;
import com.nghung.dao.FresherCandidateDao;
import com.nghung.dao.IDao;
import com.nghung.dao.InternCandidateDao;
import com.nghung.exception.IncorrectFormatException;
import com.nghung.model.ExperienceCandidate;
import com.nghung.model.FresherCandidate;
import com.nghung.model.InternCandidate;
import com.nghung.service.CandidateFileHandle;
import com.nghung.service.CandidateService;
import com.nghung.service.ExperienceCandidateService;
import com.nghung.service.FresherCandidateService;
import com.nghung.service.InternCandidateService;
import com.nghung.util.IncorrectDataType;

public class Program {
	private static CandidateFileHandle candidateFileHandle = new CandidateFileHandle();
	private static CandidateService expCandidateService = new ExperienceCandidateService();
	private static CandidateService fresherCandidateService = new FresherCandidateService();
	private static CandidateService internCandidateService = new InternCandidateService();
	
	private static IDao<ExperienceCandidate> expDao = new ExperienceCandidateDao();
	private static IDao<FresherCandidate> fresherDao = new FresherCandidateDao();
	private static IDao<InternCandidate> internDao = new InternCandidateDao();
	
	private static ExperienceCandidate experienceCandidate = new ExperienceCandidate();
	private static FresherCandidate fresherCandidate = new FresherCandidate();
	private static InternCandidate internCandidate = new InternCandidate();
	
	public static void main(String[] args) {
		LinkedHashMap<Integer, Set<IncorrectDataType>> checkDataResults = new LinkedHashMap<>();
		try {
			List<String> candidateData = candidateFileHandle.getAll();
			for (int i = 0; i < candidateData.size(); i++) {
				String data = candidateData.get(i);
				String[] fields = data.split(", ");
				int candidateType = Integer.parseInt(fields[0]);
				Set<IncorrectDataType> checkResult = null;
				switch (candidateType) {
				case 1:
					checkResult = expCandidateService.isValidCandidateData(data);
					if (checkResult.isEmpty()) {
						ExperienceCandidate experienceCandidate = (ExperienceCandidate) expCandidateService
								.createCandidate(data);
						expDao.insert(experienceCandidate);
					} else {
						checkDataResults.put(i + 1, checkResult);
					}
					break;
				case 2:
					checkResult = fresherCandidateService.isValidCandidateData(data);
					if (checkResult.isEmpty()) {
						FresherCandidate fresherCandidate = (FresherCandidate) fresherCandidateService
								.createCandidate(data);
						fresherDao.insert(fresherCandidate);
					} else {
						checkDataResults.put(i + 1, checkResult);
					}
					break;
				case 3:
					checkResult = internCandidateService.isValidCandidateData(data);
					if (checkResult.isEmpty()) {
						InternCandidate internCandidate = (InternCandidate) internCandidateService
								.createCandidate(data);
						internDao.insert(internCandidate);
					} else {
						checkDataResults.put(i + 1, checkResult);
					}
				default:
					break;
				}
			}

			candidateFileHandle.exportInvalidCandidateInfor(checkDataResults);
			
//			// CRUD With ExperienceCandidate
//			try {
//				ExperienceCandidate candidate = expDao.getById(1); 
//				if (candidate != null) {
//					System.out.println(candidate.getfullName());
//				} else {
//					System.out.println("Không tìm thấy ứng viên");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				experienceCandidate = new ExperienceCandidate();
//				experienceCandidate.setYearsOfExperience(6.5);
//				experienceCandidate.setSpecializedSkills("Java, Spring");
//				experienceCandidate.setLastWorkplace("TechCorp");
//				int updateResult = expDao.update(1, experienceCandidate);
//				if (updateResult > 0) {
//					System.out.println("Cập nhật thông tin ứng viên thành công.");
//				} else {
//					System.out.println("Cập nhật thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//          
//			try {
//				int deleteResult = expDao.removeById(1, experienceCandidate);
//				if (deleteResult > 0) {
//					System.out.println("Xóa ứng viên thành công.");
//				} else {
//					System.out.println("Xóa ứng viên thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			// CRUD with FresherCandidate
//			try {
//				FresherCandidate fresherCandidate = fresherDao.getById(1); 
//				if (fresherCandidate != null) {
//					System.out.println(fresherCandidate.getfullName());
//				} else {
//					System.out.println("Không tìm thấy ứng viên");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				fresherCandidate = new FresherCandidate();
//				fresherCandidate.setGraduationDate("2029/12/12");
//				fresherCandidate.setGraduationRank("Giỏi");
//				fresherCandidate.setGraduationSchool("HCMUS University");
//				int updateResult = fresherDao.update(1, fresherCandidate);
//				if (updateResult > 0) {
//					System.err.println("Cập nhật thông tin thành công.");
//				} else {
//					System.err.println("Cập nhật thông tin thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			try {
//				int deleteResult = fresherDao.removeById(1, fresherCandidate);
//				if (deleteResult > 0) {
//					System.err.println("Xóa thành công.");
//				} else {
//					System.err.println("Xóa thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			// CRUD with InternCandidate
//			try {
//				InternCandidate internCandidate = internDao.getById(1); 
//				if (internCandidate != null) {
//					System.out.println(internCandidate.getfullName());
//				} else {
//					System.out.println("Không tìm thấy thông tin ứng viên");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				internCandidate = new InternCandidate();
//				internCandidate.setMajor("Information Technology");
//				internCandidate.setCurrentSemester(5);
//				internCandidate.setSchoolName("FTU University");
//				internCandidate.setExpectedGraduationDate("2020/10/09");
//				int updateResult = internDao.update(1, internCandidate);
//				if (updateResult > 0) {
//					System.out.println("Cập nhật thông tin thành công.");
//				} else {
//					System.out.println("Cập nhật thông tin thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			try {
//				int deleteResult = expDao.removeById(1, experienceCandidate);
//				if (deleteResult > 0) {
//					System.out.println("Xóa thành công.");
//				} else {
//					System.out.println("Xóa thất bại.");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		} catch (SQLException | IOException | IncorrectFormatException e) {
			e.printStackTrace();
		}
	}

}
