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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.common.constant.EmployeeTrackerSQLConstant;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.TradeSummary;
import com.technomak.employeetracker.exception.EmployeeTrackerDataAccessException;
import com.technomak.employeetracker.repo.IEmployeeTrackerSummaryRepo;

/**
 * @author JaY
 *
 */
@Repository
public class EmployeeTrackerSummaryRepoImpl implements IEmployeeTrackerSummaryRepo {

	public static final Logger log = LoggerFactory.getLogger(EmployeeTrackerSummaryRepoImpl.class);

	@Value(EmployeeTrackerSQLConstant.FETCH_INACTIVE_EMPLOYEE)
	private String fetchInactive;
	@Value(EmployeeTrackerSQLConstant.FETCH_ACTIVE_EMPLOYEE)
	private String fetchActive;
	@Value(EmployeeTrackerSQLConstant.FETCH_ACTIVE_COUNT)
	private String fetchActiveCount;
	@Value(EmployeeTrackerSQLConstant.FETCH_INACTIVE_COUNT)
	private String fetchInactiveCount;
	@Value(EmployeeTrackerSQLConstant.FETCH_TRADES)
	private String fetchTrades;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeTracker> fetchInactiveEmployee() {
		try {
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
		} catch (Exception exception) {
			log.error("Exception in fetchInactiveEmployee", exception);
			throw new EmployeeTrackerDataAccessException("Exception in fetchInactiveEmployee", exception);
		}
	}

	@Override
	public List<EmployeeTracker> fetchActiveEmployee() {
		try {
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
		} catch (Exception exception) {
			log.error("Exception in fetchActiveEmployee", exception);
			throw new EmployeeTrackerDataAccessException("Exception in fetchActiveEmployee", exception);
		}
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> fetchActiveCount() {
		try {
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
			log.info("ActiveMap: " + activeSummaryMap);
			return activeSummaryMap;
		} catch (Exception exception) {
			log.error("Exception in fetchActiveCount", exception);
			throw new EmployeeTrackerDataAccessException("Exception in fetchActiveCount", exception);
		}
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> fetchInactiveCount() {
		try {
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
			log.info("InActiveMap: " + inactiveSummaryMap);
			return inactiveSummaryMap;
		} catch (Exception exception) {
			log.error("Exception in fetchInactiveCount", exception);
			throw new EmployeeTrackerDataAccessException("Exception in fetchInactiveCount", exception);
		}

	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> mergeMaps(
			Map<String, List<TradeSummaryPreviewMetaData>> inactiveSummaryMap,
			Map<String, List<TradeSummaryPreviewMetaData>> activeSummaryMap) {
		try {
			Map<String, List<TradeSummaryPreviewMetaData>> summaryMap = Stream
					.concat(activeSummaryMap.entrySet().stream(), inactiveSummaryMap.entrySet().stream())
					.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (a, b) -> {
						a.addAll(b);
						return a;
					}));
			log.info("SummaryMap:" + summaryMap);
			return summaryMap;
		} catch (Exception exception) {
			log.error("Exception in mergeMaps", exception);
			throw new EmployeeTrackerDataAccessException("Exception in mergeMaps", exception);
		}
	}

	@Override
	public Map<String, TradeSummary> fetchTrades() {
		try {
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
		} catch (Exception exception) {
			log.error("Exception in fetchTrades", exception);
			throw new EmployeeTrackerDataAccessException("Exception in fetchTrades", exception);
		}
	}

}
