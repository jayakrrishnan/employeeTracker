/**
 * 
 */
package com.technomak.employeetracker.handler.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.EmployeeTransferRecord;
import com.technomak.employeetracker.entity.TradeSummary;
import com.technomak.employeetracker.handler.IEmployeeTrackerHandler;
import com.technomak.employeetracker.repo.IEmployeeTrackerRepo;
import com.technomak.employeetracker.repo.IEmployeeTrackerSummaryRepo;
import com.technomak.employeetracker.repo.IEmployeeTransferRecordRepo;

/**
 * @author JaY
 *
 */
@Service
public class EmployeeTrackerHandlerImpl implements IEmployeeTrackerHandler {

	@Autowired
	private IEmployeeTrackerRepo employeeTrackerRepo;

	@Autowired
	private IEmployeeTransferRecordRepo employeeTransferRepo;

	@Autowired
	private IEmployeeTrackerSummaryRepo employeeTrackerSummaryRepo;

	@Override
	public List<EmployeeTracker> findAllEmployee() {
		return employeeTrackerRepo.findAll();
	}

	@Override
	public EmployeeTracker findEmployeeById(Integer empId) {
		Optional<EmployeeTracker> employee = employeeTrackerRepo.findById(empId);
		return employee.isPresent() ? employee.get() : null;
	}

	@Override
	public EmployeeTracker saveEmployee(EmployeeTracker employee) {
		return employeeTrackerRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Integer empId) {
		employeeTrackerRepo.deleteById(empId);

	}

	@Override
	public List<EmployeeTransferRecord> findTransferHistory(Integer empId) {
		return employeeTransferRepo.findByEmpId(empId);
	}

	@Override
	public EmployeeTransferRecord findTransferRecord(Integer transferId) {
		Optional<EmployeeTransferRecord> record = employeeTransferRepo.findById(transferId);
		return record.isPresent() ? record.get() : null;
	}

	@Override
	public EmployeeTransferRecord findCurrentLocation(Integer empId) {
		List<EmployeeTransferRecord> transferList = employeeTransferRepo.findAllByEmpIdOrderByDateOfTransferDesc(empId);
		return transferList.stream().findFirst().orElse(null);
	}

	@Override
	public EmployeeTransferRecord updateCurrentLocation(EmployeeTransferRecord employeeTransfer) {
		return employeeTransferRepo.save(employeeTransfer);
	}

	@Override
	public void deleteEmployeeTransferRecord(Integer trasnferId) {
		employeeTransferRepo.deleteById(trasnferId);
	}

	@Override
	public List<EmployeeTracker> fetchInactiveEmployee() {
		return employeeTrackerSummaryRepo.fetchInactiveEmployee();
	}

	@Override
	public List<EmployeeTracker> fetchActiveEmployee() {
		return employeeTrackerSummaryRepo.fetchActiveEmployee();
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> getSummary() {
		Map<String, List<TradeSummaryPreviewMetaData>> inactive = employeeTrackerSummaryRepo.fetchInactiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> active = employeeTrackerSummaryRepo.fetchActiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerSummaryRepo.mergeMaps(inactive, active);
		return summary;
	}

	/**
	 * @param summary
	 */
	@Override
	public Map<String, TradeSummary> saveSummary() {
		Map<String, List<TradeSummaryPreviewMetaData>> inactive = employeeTrackerSummaryRepo.fetchInactiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> active = employeeTrackerSummaryRepo.fetchActiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerSummaryRepo.mergeMaps(inactive, active);
		Map<String, TradeSummary> tradesMap = employeeTrackerSummaryRepo.fetchTrades();
		System.out.println(tradesMap.keySet());
		List<TradeSummary> list = new ArrayList<TradeSummary>();
		summary.entrySet().stream().forEach(trade -> {
			List<TradeSummaryPreviewMetaData> summaryList = trade.getValue();
			System.out.println("summ "+summaryList);
			System.out.println("key "+trade.getKey());
			System.out.println(tradesMap.get(trade.getKey()));
			
			summaryList.forEach(preview -> {
				TradeSummary tradeSummary =tradesMap.get(trade.getKey());
				if(null==tradeSummary) {
					tradeSummary=new TradeSummary();
					tradesMap.put(trade.getKey(), tradeSummary);
				}
				tradeSummary.setTrades(trade.getKey());
				switch (preview.getLocation()) {
				case "Vacation":
					tradeSummary.setVacation(preview.getCount());
					break;
				case "Camp":
					tradeSummary.setCamp(preview.getCount());
					break;
				case "JAFZA_ENOC":
					tradeSummary.setJafza_enoc(preview.getCount());
					break;
				case "NPCC_AUH":
					tradeSummary.setNpcc_auh(preview.getCount());
					break;
				case "Outotec":
					tradeSummary.setOutotec(preview.getCount());
					break;
				case "Petron_EGA":
					tradeSummary.setPetron_ega(preview.getCount());
					break;
				case "YD_01":
					tradeSummary.setYd01(preview.getCount());
					break;
				case "YD_02":
					tradeSummary.setYd02(preview.getCount());
					break;
				case "YD_02_Skid_Division":
					tradeSummary.setYd02SkidDivision(preview.getCount());
					break;
				case "YD_05":
					tradeSummary.setYd05(preview.getCount());
					break;
				case "YD_06":
					tradeSummary.setYd06(preview.getCount());
					break;
				case "QA_QC":
					tradeSummary.setQaqc(preview.getCount());
					break;
				case "HFZ":
					tradeSummary.setHfz(preview.getCount());
					break;
				case "MAK_STYLO":
					tradeSummary.setMakstylo(preview.getCount());
					break;
				case "MAK_STYLO_AUH":
					tradeSummary.setMakstyloauh(preview.getCount());
					break;
				case "PETROFAC_AUH":
					tradeSummary.setPetrofacauh(preview.getCount());
					break;
				case "Abudhabi_Project":
					tradeSummary.setAbudhabiproject(preview.getCount());
					break;
				case "Maintenance":
					tradeSummary.setMaintenance(preview.getCount());
					break;
				}
				tradesMap.put(trade.getKey(), tradeSummary);
				list.add(tradeSummary);
			});

		});
		Set<String> resultSet = new HashSet<String>();
		list.removeIf(t -> !resultSet.add(t.getTrades()));
		System.out.println(list.toString());
		System.out.println("size=" + list.size());
		return tradesMap;
	}

}
