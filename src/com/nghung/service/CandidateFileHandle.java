/*
 * 
 * @author NguyenHung
 * @date Jan 8, 2025
 * @version 1.0
 *
 */

package com.nghung.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nghung.util.Constants;
import com.nghung.util.IncorrectDataType;

public class CandidateFileHandle {
	public List<String> getAll() {
		List<String> result = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FILE_INPUT_PATH)))) {
			String line;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void exportInvalidCandidateInfor(LinkedHashMap<Integer, Set<IncorrectDataType>> checkDataResult) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constants.FILE_OUTPUT_PATH), StandardCharsets.UTF_8))) {
			Iterator<Map.Entry<Integer, Set<IncorrectDataType>>> iterator = checkDataResult.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, Set<IncorrectDataType>> data = iterator.next();
				
				if (data.getValue().contains(IncorrectDataType.DATE_OF_BIRTH_FORMAT)) {
					bw.write("Dòng " + data.getKey() + ": Sai định dạng ngày tháng năm sinh");
					bw.newLine();
				}
				
				if (data.getValue().contains(IncorrectDataType.EMAIL_ADDRESS_FORMAT)) {
					bw.write("Dòng " + data.getKey() + ": Sai định dạng địa chỉ email");
					bw.newLine();
				}
				
				if (data.getValue().contains(IncorrectDataType.YEARS_OF_EXPERIENCE_FORMAT)) {
					bw.write("Dòng " + data.getKey() + ": Sai định dạng số năm kinh nghiệm");
					bw.newLine();
				}
				
				if (data.getValue().contains(IncorrectDataType.PHONE_NUMBER_FORMAT)) {
					bw.write("Dòng " + data.getKey() + ": Sai định dạng số điện thoại");
					bw.newLine();
				}
				
				if (data.getValue().contains(IncorrectDataType.EXCESS_DATA)) {
					bw.write("Dòng " + data.getKey() + ": Thừa dữ liệu");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
