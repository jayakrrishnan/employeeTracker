/**
 * 
 */
package com.technomak.employeetracker.handler;

import java.util.List;
import java.util.Map;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.EmployeeTransferRecord;
import com.technomak.employeetracker.entity.TradeSummary;

/**
 * @author JaY
 *
 */
public interface IEmployeeTrackerHandler {

	List<EmployeeTracker> findAllEmployee();

	EmployeeTracker findEmployeeById(Integer empId);

	EmployeeTracker saveEmployee(EmployeeTracker employee);

	void deleteEmployee(Integer empId);

	List<EmployeeTransferRecord> findTransferHistory(Integer empId);

	EmployeeTransferRecord findTransferRecord(Integer transferId);

	EmployeeTransferRecord findCurrentLocation(Integer empId);

	EmployeeTransferRecord updateCurrentLocation(EmployeeTransferRecord employeeTransfer);

	void deleteEmployeeTransferRecord(Integer trasnferId);

	List<EmployeeTracker> fetchInactiveEmployee();

	List<EmployeeTracker> fetchActiveEmployee();

	Map<String, TradeSummary> saveSummary();

	Map<String, List<TradeSummaryPreviewMetaData>> getSummary();
}
