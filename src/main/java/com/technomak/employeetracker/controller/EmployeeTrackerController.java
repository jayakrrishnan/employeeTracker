/**
 * 
 */
package com.technomak.employeetracker.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import com.technomak.employeetracker.common.EmployeeTrackerDTO;
import com.technomak.employeetracker.common.EmployeeTransferRecordDTO;
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

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeTrackerController.class);
	@Autowired
	IEmployeeTrackerHandler employeeTrackerHandler;

	@RequestMapping(method = RequestMethod.GET, value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches employee details from master using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<EmployeeTracker> getEmployee(@PathVariable("empId") Integer empId) {
		EmployeeTracker employee = employeeTrackerHandler.findEmployeeById(empId);
		if (null != employee) {
			LOG.info("Status:200 EmpId:" + empId + " Response:", employee);
			return ResponseEntity.ok(employee);
		} else {
			LOG.error("Status:204 empId:", empId);
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
			LOG.info("Status:200 deleted EmpId:" + empId + " Response:", employee);
			return ResponseEntity.ok(employee);
		} else {
			LOG.error("Status:204 empId:", empId);
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Saves employee details to master", notes = "Returns 201 CREATED/204 NO_CONTENT", httpMethod = HttpMethod.POST)
	public ResponseEntity<EmployeeTracker> saveEmployee(@RequestBody EmployeeTrackerDTO employeeTrackerDTO) {
		EmployeeTracker employeeTracker=new EmployeeTracker();
		BeanUtils.copyProperties(employeeTrackerDTO, employeeTracker);
		EmployeeTracker employee = employeeTrackerHandler.saveEmployee(employeeTracker);
		if (null != employee) {
			LOG.info("Status:201  Response:", employee);
			return new ResponseEntity<EmployeeTracker>(employee, HttpStatus.CREATED);
		} else {
			LOG.error("Status:204");
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
			LOG.info("Status:200  Response:", employeeList);
			return ResponseEntity.ok(employeeList);
		} else {
			LOG.error("Status:204 ");
			return ResponseEntity.noContent().build();
		}
	}

	//
	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches transfer details from transfer record using empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<EmployeeTransferRecord> getCurrentLocation(@PathVariable("empId") Integer empId) {
		EmployeeTransferRecord employee = employeeTrackerHandler.findCurrentLocation(empId);
		if (null != employee) {
			LOG.info("Status:200 EmpId:" + empId + " Response:", employee);
			return ResponseEntity.ok(employee);
		} else {
			LOG.error("Status:204 empId:", empId);
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/transfer/{transferId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Deletes transfer details from master using transferId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.DELETE)
	public ResponseEntity<EmployeeTransferRecord> deleteTransferRecord(@PathVariable("transferId") Integer transferId) {
		EmployeeTransferRecord record = employeeTrackerHandler.findTransferRecord(transferId);
		if (null != record) {
			employeeTrackerHandler.deleteEmployeeTransferRecord(transferId);
			LOG.info("Status:200 transferId:" + transferId + " Response:", record);
			return ResponseEntity.ok(record);
		} else {
			LOG.error("Status:204 transferId:", transferId);
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Saves transfer details to transfer record", notes = "Returns 201 CREATED/204 NO_CONTENT", httpMethod = HttpMethod.POST)
	public ResponseEntity<EmployeeTransferRecord> saveEmployeeTransferRecord(
			@RequestBody EmployeeTransferRecordDTO employeeTransferRecordDTO) {
		EmployeeTransferRecord employeeTransferRecord=new EmployeeTransferRecord();
		BeanUtils.copyProperties(employeeTransferRecordDTO, employeeTransferRecord);
		EmployeeTracker employee = employeeTrackerHandler.findEmployeeById(employeeTransferRecord.getEmpId());
		if (null != employee) {
			employee.setLocation(employeeTransferRecord.getTransferedTo());
			employeeTrackerHandler.saveEmployee(employee);
			EmployeeTransferRecord newRecord = employeeTrackerHandler.updateCurrentLocation(employeeTransferRecord);
			if (null != newRecord) {
				LOG.info("Status:201 transferId:" + newRecord.getEmpTransRecordId() + " Response:", newRecord);
				return new ResponseEntity<EmployeeTransferRecord>(newRecord, HttpStatus.CREATED);
			} else {
				return ResponseEntity.noContent().build();
			}
		} else {
			LOG.error("Status:204 empId:", employeeTransferRecord.getEmpId());
			return ResponseEntity.noContent().build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{empId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all transfer history details of employee by empId", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTransferRecord>> getTransferHistory(@PathVariable("empId") Integer empId) {
		List<EmployeeTransferRecord> employeeTransferList = employeeTrackerHandler.findTransferHistory(empId);
		if (!CollectionUtils.isEmpty(employeeTransferList)) {
			LOG.info("Status:200 empId:" + empId + " Response:", employeeTransferList);
			return ResponseEntity.ok(employeeTransferList);
		} else {
			LOG.error("Status:204 empId:", empId);
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all inactive employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTracker>> getInactiveList() {
		List<EmployeeTracker> inactiveList = employeeTrackerHandler.fetchInactiveEmployee();
		if (!CollectionUtils.isEmpty(inactiveList)) {
			LOG.info("Status:200  Response:", inactiveList);
			return ResponseEntity.ok(inactiveList);
		} else {
			LOG.error("Status:204 ");
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches all active employee details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<List<EmployeeTracker>> getActiveList() {
		List<EmployeeTracker> activeList = employeeTrackerHandler.fetchActiveEmployee();
		if (!CollectionUtils.isEmpty(activeList)) {
			LOG.info("Status:200  Response:", activeList);
			return ResponseEntity.ok(activeList);
		} else {
			LOG.error("Status:204 ");
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Fetches active-inactivr summary details from master", notes = "Returns 200 OK/204 NO_CONTENT", httpMethod = HttpMethod.GET)
	public ResponseEntity<Map<String, List<TradeSummaryPreviewMetaData>>> getSummary() {
		Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerHandler.getSummary();
		if (!CollectionUtils.isEmpty(summary)) {
			LOG.info("Status:200  Response:", summary);
			return ResponseEntity.ok(summary);
		} else {
			LOG.error("Status:204 ");
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SwaggerToken
	@ApiOperation(value = "Saves summary details to table", notes = "Returns 201 CREATED/204 NO_CONTENT", httpMethod = HttpMethod.POST)
	public ResponseEntity<Map<String, TradeSummary>> saveSummary() {
		Map<String, TradeSummary> summary = employeeTrackerHandler.createSummary();
		if (!CollectionUtils.isEmpty(summary)) {
			LOG.info("Status:201  Response:", summary);
			return new ResponseEntity<Map<String, TradeSummary>>(summary, HttpStatus.CREATED);
		} else {
			LOG.error("Status:204 ");
			return ResponseEntity.noContent().build();
		}
	}
}
