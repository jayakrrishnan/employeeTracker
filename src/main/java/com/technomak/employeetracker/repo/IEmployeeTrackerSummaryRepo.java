/**
 * 
 */
package com.technomak.employeetracker.repo;

import java.util.List;
import java.util.Map;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.TradeSummary;

/**
 * @author JaY
 *
 */

public interface IEmployeeTrackerSummaryRepo {

	List<EmployeeTracker> fetchInactiveEmployee();

	List<EmployeeTracker> fetchActiveEmployee();
	
	Map<String,List<TradeSummaryPreviewMetaData>> fetchInactiveCount();
	
	Map<String,List<TradeSummaryPreviewMetaData>> fetchActiveCount();
	
	Map<String, List<TradeSummaryPreviewMetaData>> mergeMaps(Map<String, List<TradeSummaryPreviewMetaData>> inactiveSummaryMap,Map<String, List<TradeSummaryPreviewMetaData>> activeSummaryMap);
	
	Map<String,TradeSummary> fetchTrades();
	
}
