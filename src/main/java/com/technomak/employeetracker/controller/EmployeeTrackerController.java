/**
 * 
 */
package com.technomak.employeetracker.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.controller.annotation.SwaggerToken;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.EmployeeTransferRecord;
import com.technomak.employeetracker.entity.TradeSummary;
import com.technomak.employeetracker.handler.IEmployeeTrackerHandler;

import io.swagger.annotations.ApiOperation;

/**
 * @author JaY
 *
 */
@RestController
public class EmployeeTrackerController {

	@Autowired
	IEmployeeTrackerHandler employeeTrackerHandler;

	@RequestMapping(method = RequestMethod.GET, value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches employee details from master using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<EmployeeTracker> getEmployee(@PathVariable("empId") Integer empId) {
		EmployeeTracker employee = employeeTrackerHandler.findEmployeeById(empId);
		if (null != employee) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Deletes employee details from master using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.DELETE)
	public ResponseEntity<EmployeeTracker> deleteEmployee(@PathVariable("empId") Integer empId) {
		EmployeeTracker employee = employeeTrackerHandler.findEmployeeById(empId);
		if (null != employee) {
			employeeTrackerHandler.deleteEmployee(empId);
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Saves employee details to master", notes = "Returns 201 OK/204 NO_CONTENT", httpMethod = HttpMethod.POST)
	public ResponseEntity<EmployeeTracker> saveEmployee(@RequestBody EmployeeTracker employeeTracker) {
		EmployeeTracker employee = employeeTrackerHandler.saveEmployee(employeeTracker);
		if (null != employee) {
			return new ResponseEntity<EmployeeTracker>(employee, HttpStatus.CREATED);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTracker>> getAllEmployee() {
		List<EmployeeTracker> employeeList = employeeTrackerHandler.findAllEmployee();
		if (!CollectionUtils.isEmpty(employeeList)) {
			return ResponseEntity.ok(employeeList);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	//
	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches employee details from master using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<EmployeeTransferRecord> getCurrentLocation(@PathVariable("empId") Integer empId) {
		EmployeeTransferRecord employee = employeeTrackerHandler.findCurrentLocation(empId);
		if (null != employee) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/transfer/{transferId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Deletes employee details from master using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.DELETE)
	public ResponseEntity<EmployeeTransferRecord> deleteTransferRecord(@PathVariable("transferId") Integer transferId) {
		EmployeeTransferRecord record=employeeTrackerHandler.findTransferRecord(transferId);
		if(null!=record) {
			employeeTrackerHandler.deleteEmployeeTransferRecord(transferId);
			return ResponseEntity.ok(record);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Saves employee details to master", notes = "Returns 201 OK/204 NO_CONTENT", httpMethod = HttpMethod.POST)
	public ResponseEntity<EmployeeTransferRecord> saveEmployeeTransferRecord(@RequestBody EmployeeTransferRecord employeeTransferRecord) {
		EmployeeTransferRecord employee = employeeTrackerHandler.updateCurrentLocation(employeeTransferRecord);
		if (null != employee) {
			return new ResponseEntity<EmployeeTransferRecord>(employee, HttpStatus.CREATED);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{empId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTransferRecord>> getTransferHistory(@PathVariable("empId") Integer empId) {
		List<EmployeeTransferRecord> employeeTransferList = employeeTrackerHandler.findTransferHistory(empId);
		if (!CollectionUtils.isEmpty(employeeTransferList)) {
			return ResponseEntity.ok(employeeTransferList);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTracker>> getInactiveList() {
		List<EmployeeTracker> inactiveList = employeeTrackerHandler.fetchInactiveEmployee();
		if (!CollectionUtils.isEmpty(inactiveList)) {
			return ResponseEntity.ok(inactiveList);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTracker>> getActiveList() {
		List<EmployeeTracker> activeList = employeeTrackerHandler.fetchActiveEmployee();
		if (!CollectionUtils.isEmpty(activeList)) {
			return ResponseEntity.ok(activeList);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getSummary", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<Map<String, List<TradeSummaryPreviewMetaData>>> getSummary() {
		 Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerHandler.getSummary();
		if (!CollectionUtils.isEmpty(summary)) {
			return ResponseEntity.ok(summary);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/saveSummary", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity< Map<String, TradeSummary>> saveSummary() {
		 Map<String, TradeSummary> summary = employeeTrackerHandler.saveSummary();
		if (!CollectionUtils.isEmpty(summary)) {
			return ResponseEntity.ok(summary);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
