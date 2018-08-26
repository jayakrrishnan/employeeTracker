/**
 * 
 */
package com.technomak.employeetracker.repo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.common.constant.EmployeeTrackerSQLConstant;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.TradeSummary;
import com.technomak.employeetracker.repo.IEmployeeTrackerSummaryRepo;

/**
 * @author JaY
 *
 */
@Repository
public class EmployeeTrackerSummaryRepoImpl implements IEmployeeTrackerSummaryRepo {

	@Value(EmployeeTrackerSQLConstant.FETCH_INACTIVE_EMPLOYEE)
	private String fetchInactive;
	@Value(EmployeeTrackerSQLConstant.FETCH_ACTIVE_EMPLOYEE)
	private String fetchActive;
	@Value(EmployeeTrackerSQLConstant.FETCH_ACTIVE_COUNT)
	private String fetchInactiveCount;
	@Value(EmployeeTrackerSQLConstant.FETCH_INACTIVE_COUNT)
	private String fetchActiveCount;
	@Value(EmployeeTrackerSQLConstant.FETCH_TRADES)
	private String fetchTrades;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeTracker> fetchInactiveEmployee() {
		List<EmployeeTracker> inactiveList = null;
		inactiveList = jdbcTemplate.query(fetchInactive, rs -> {
			List<EmployeeTracker> list = new ArrayList<EmployeeTracker>();
			while (rs.next()) {
				EmployeeTracker employeeTracker = new EmployeeTracker();
				employeeTracker.setEmpId(rs.getInt(1));
				employeeTracker.setEmpName(rs.getString(2));
				employeeTracker.setDesignation(rs.getString(3));
				employeeTracker.setDepartment(rs.getString(4));
				employeeTracker.setLocation(rs.getString(5));
				employeeTracker.setCurrentProject(rs.getString(6));
				employeeTracker.setNextProject(rs.getString(7));
				employeeTracker.setLoadedUpto(rs.getTimestamp(8));
				employeeTracker.setSkill(rs.getString(9));
				employeeTracker.setWeekNo(rs.getInt(10));
				employeeTracker.setLeaveStatus(rs.getInt(11));
				employeeTracker.setVisaExpiry(rs.getTimestamp(12));
				employeeTracker.setRating(rs.getString(13));
				employeeTracker.setTrade(rs.getString(14));
				employeeTracker.setRemark(rs.getString(15));
				list.add(employeeTracker);
			}
			return list;
		});
		return inactiveList;
	}

	@Override
	public List<EmployeeTracker> fetchActiveEmployee() {
		List<EmployeeTracker> activeList = null;
		activeList = jdbcTemplate.query(fetchActive, rs -> {
			List<EmployeeTracker> list = new ArrayList<EmployeeTracker>();
			while (rs.next()) {
				EmployeeTracker employeeTracker = new EmployeeTracker();
				employeeTracker.setEmpId(rs.getInt(1));
				employeeTracker.setEmpName(rs.getString(2));
				employeeTracker.setDesignation(rs.getString(3));
				employeeTracker.setDepartment(rs.getString(4));
				employeeTracker.setLocation(rs.getString(5));
				employeeTracker.setCurrentProject(rs.getString(6));
				employeeTracker.setNextProject(rs.getString(7));
				employeeTracker.setLoadedUpto(rs.getTimestamp(8));
				employeeTracker.setSkill(rs.getString(9));
				employeeTracker.setWeekNo(rs.getInt(10));
				employeeTracker.setLeaveStatus(rs.getInt(11));
				employeeTracker.setVisaExpiry(rs.getTimestamp(12));
				employeeTracker.setRating(rs.getString(13));
				employeeTracker.setTrade(rs.getString(14));
				employeeTracker.setRemark(rs.getString(15));
				list.add(employeeTracker);
			}
			return list;
		});
		return activeList;
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> fetchInactiveCount() {
		Map<String, List<TradeSummaryPreviewMetaData>> inactiveSummaryMap = null;
		inactiveSummaryMap = jdbcTemplate.query(fetchInactiveCount, rs -> {
			Map<String, List<TradeSummaryPreviewMetaData>> map = new HashMap<String, List<TradeSummaryPreviewMetaData>>();
			List<TradeSummaryPreviewMetaData> list = null;
			while (rs.next()) {
				list = map.get(rs.getString(1).trim());
				TradeSummaryPreviewMetaData preview = new TradeSummaryPreviewMetaData();
				preview.setLocation(rs.getString(2).trim());
				preview.setCount(rs.getInt(3));
				if (null != list) {
					list.add(preview);
				} else {
					list = new ArrayList<TradeSummaryPreviewMetaData>();
					list.add(preview);
				}
				map.put(rs.getString(1).trim(), list);
			}
			return map;
		});

		return inactiveSummaryMap;
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> fetchActiveCount() {
		Map<String, List<TradeSummaryPreviewMetaData>> activeSummaryMap = null;
		activeSummaryMap = jdbcTemplate.query(fetchActiveCount, rs -> {
			Map<String, List<TradeSummaryPreviewMetaData>> map = new HashMap<String, List<TradeSummaryPreviewMetaData>>();
			List<TradeSummaryPreviewMetaData> list = null;
			while (rs.next()) {
				list = map.get(rs.getString(1).trim());
				TradeSummaryPreviewMetaData preview = new TradeSummaryPreviewMetaData();
				preview.setLocation(rs.getString(2).trim());
				preview.setCount(rs.getInt(3));
				if (null != list) {
					list.add(preview);
				} else {
					list = new ArrayList<TradeSummaryPreviewMetaData>();
					list.add(preview);
				}
				map.put(rs.getString(1).trim(), list);
			}
			return map;
		});
		return activeSummaryMap;
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> mergeMaps(
			Map<String, List<TradeSummaryPreviewMetaData>> inactiveSummaryMap,
			Map<String, List<TradeSummaryPreviewMetaData>> activeSummaryMap) {
		
		Map<String, List<TradeSummaryPreviewMetaData>> summaryMap = Stream
				.concat(inactiveSummaryMap.entrySet().stream(), activeSummaryMap.entrySet().stream())
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (a, b) -> {
					/*a.addAll(b);*/
					/*List<TradeSummaryPreviewMetaData>list= Stream
						.concat(a.stream(), b.stream()).collect(Collectors.toList(c->{
							
						}));
					return list;*/
					
					List<TradeSummaryPreviewMetaData> list=a.stream().filter(f1 ->
						 b.stream().anyMatch(f2 -> f1.getLocation() == f2.getLocation()))
	                  .collect(Collectors.toList());
					
					return list;
					/*List<TradeSummaryPreviewMetaData> list=new ArrayList<TradeSummaryPreviewMetaData>(a);
					list.forEach(f1-> b.forEach(f2->{
						if(f1.getLocation().equalsIgnoreCase(f2.getLocation())) {
							f1.setCount(f1.getCount()+f2.getCount());
						}
						else {
							list.add(f2);
						}
					}));
					return list;*/
					
				}));
		return summaryMap;
	}

	@Override
	public Map<String, TradeSummary> fetchTrades() {
		Map<String, TradeSummary> tradeSummaryMap = null;
		tradeSummaryMap = jdbcTemplate.query(fetchTrades, rs -> {
			Map<String, TradeSummary> map = new HashMap<String, TradeSummary>();
			TradeSummary tradeSummary = null;
			while (rs.next()) {
				tradeSummary = new TradeSummary();
				tradeSummary.setTrades(rs.getString(1).trim());
				map.put(rs.getString(1).trim(), tradeSummary);
			}
			return map;
		});
		TradeSummary tradeSummary = new TradeSummary();
		tradeSummary.setTrades("XTotal Location Vise");
		tradeSummaryMap.put("XTotal Location Vise", tradeSummary);
		return tradeSummaryMap;
	}

}
