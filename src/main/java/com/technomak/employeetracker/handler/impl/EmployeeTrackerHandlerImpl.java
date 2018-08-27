/**
 * 
 */
package com.technomak.employeetracker.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technomak.employeetracker.common.TradeSummaryPreviewMetaData;
import com.technomak.employeetracker.entity.EmployeeTracker;
import com.technomak.employeetracker.entity.EmployeeTransferRecord;
import com.technomak.employeetracker.entity.TradeSummary;
import com.technomak.employeetracker.exception.EmployeeTrackerDataAccessException;
import com.technomak.employeetracker.exception.EmployeeTrackerServiceException;
import com.technomak.employeetracker.handler.IEmployeeTrackerHandler;
import com.technomak.employeetracker.repo.IEmployeeTrackerRepo;
import com.technomak.employeetracker.repo.IEmployeeTrackerSummaryRepo;
import com.technomak.employeetracker.repo.IEmployeeTrackerTradeSummaryRepo;
import com.technomak.employeetracker.repo.IEmployeeTransferRecordRepo;

/**
 * @author JaY
 *
 */
@Service
public class EmployeeTrackerHandlerImpl implements IEmployeeTrackerHandler {

	public static final Logger log = LoggerFactory.getLogger(EmployeeTrackerHandlerImpl.class);

	@Autowired
	private IEmployeeTrackerRepo employeeTrackerRepo;

	@Autowired
	private IEmployeeTransferRecordRepo employeeTransferRepo;

	@Autowired
	private IEmployeeTrackerSummaryRepo employeeTrackerSummaryRepo;

	@Autowired
	private IEmployeeTrackerTradeSummaryRepo employeeTrackerTradeSummaryRepo;

	@Override
	public List<EmployeeTracker> findAllEmployee() {
		try {
			return employeeTrackerRepo.findAll();
		} catch (Exception exception) {
			log.error("Exception in findAllEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in findAllEmployee", exception);
		}
	}

	@Override
	public EmployeeTracker findEmployeeById(Integer empId) {
		try {
			Optional<EmployeeTracker> employee = employeeTrackerRepo.findById(empId);
			return employee.isPresent() ? employee.get() : null;
		} catch (Exception exception) {
			log.error("Exception in findEmployeeById", exception);
			throw new EmployeeTrackerServiceException("Exception in findEmployeeById", exception);
		}
	}

	@Override
	public EmployeeTracker saveEmployee(EmployeeTracker employee) {
		try {
			return employeeTrackerRepo.save(employee);
		} catch (Exception exception) {
			log.error("Exception in saveEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in saveEmployee", exception);
		}
	}

	@Override
	public void deleteEmployee(Integer empId) {
		try {
			employeeTrackerRepo.deleteById(empId);
		} catch (Exception exception) {
			log.error("Exception in deleteEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in deleteEmployee", exception);
		}
	}

	@Override
	public List<EmployeeTransferRecord> findTransferHistory(Integer empId) {
		try {
			return employeeTransferRepo.findByEmpId(empId);
		} catch (Exception exception) {
			log.error("Exception in findTransferHistory", exception);
			throw new EmployeeTrackerServiceException("Exception in findTransferHistory", exception);
		}
	}

	@Override
	public EmployeeTransferRecord findTransferRecord(Integer transferId) {
		try {
			Optional<EmployeeTransferRecord> record = employeeTransferRepo.findById(transferId);
			return record.isPresent() ? record.get() : null;
		} catch (Exception exception) {
			log.error("Exception in findTransferRecord", exception);
			throw new EmployeeTrackerServiceException("Exception in findTransferRecord", exception);
		}
	}

	@Override
	public EmployeeTransferRecord findCurrentLocation(Integer empId) {
		try {
			List<EmployeeTransferRecord> transferList = employeeTransferRepo
					.findAllByEmpIdOrderByDateOfTransferDesc(empId);
			return transferList.stream().findFirst().orElse(null);
		} catch (Exception exception) {
			log.error("Exception in findCurrentLocation", exception);
			throw new EmployeeTrackerServiceException("Exception in findCurrentLocation", exception);
		}
	}

	@Override
	public EmployeeTransferRecord updateCurrentLocation(EmployeeTransferRecord employeeTransfer) {
		try {
			return employeeTransferRepo.save(employeeTransfer);
		} catch (Exception exception) {
			log.error("Exception in updateCurrentLocation", exception);
			throw new EmployeeTrackerServiceException("Exception in updateCurrentLocation", exception);
		}
	}

	@Override
	public void deleteEmployeeTransferRecord(Integer trasnferId) {
		try {
			employeeTransferRepo.deleteById(trasnferId);
		} catch (Exception exception) {
			log.error("Exception in deleteEmployeeTransferRecord", exception);
			throw new EmployeeTrackerServiceException("Exception in deleteEmployeeTransferRecord", exception);
		}
	}

	@Override
	public List<EmployeeTracker> fetchInactiveEmployee() {
		try {
		return employeeTrackerSummaryRepo.fetchInactiveEmployee();
		} catch (EmployeeTrackerDataAccessException exception) {
			log.error("Exception in fetchInactiveEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in fetchInactiveEmployee", exception);
		}
	}

	@Override
	public List<EmployeeTracker> fetchActiveEmployee() {
		try {
		return employeeTrackerSummaryRepo.fetchActiveEmployee();
		} catch (EmployeeTrackerDataAccessException exception) {
			log.error("Exception in fetchActiveEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in fetchActiveEmployee", exception);
		}
	}

	@Override
	public Map<String, List<TradeSummaryPreviewMetaData>> getSummary() {
		try {
		Map<String, List<TradeSummaryPreviewMetaData>> inactive = employeeTrackerSummaryRepo.fetchInactiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> active = employeeTrackerSummaryRepo.fetchActiveCount();
		Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerSummaryRepo.mergeMaps(inactive, active);
		return summary;
		} catch (EmployeeTrackerDataAccessException exception) {
			log.error("Exception in getSummary", exception);
			throw new EmployeeTrackerServiceException("Exception in getSummary", exception);
		}
	}

	/**
	 * @param summary
	 */
	@Override
	public Map<String, TradeSummary> createSummary() {
		try {
			Map<String, List<TradeSummaryPreviewMetaData>> inactive = employeeTrackerSummaryRepo.fetchInactiveCount();
			Map<String, List<TradeSummaryPreviewMetaData>> active = employeeTrackerSummaryRepo.fetchActiveCount();
			Map<String, List<TradeSummaryPreviewMetaData>> summary = employeeTrackerSummaryRepo.mergeMaps(inactive,
					active);
			Map<String, TradeSummary> tradesMap = employeeTrackerSummaryRepo.fetchTrades();
			System.out.println(tradesMap.keySet());
			List<TradeSummary> list = new ArrayList<TradeSummary>();
			summary.entrySet().stream().forEach(trade -> {
				List<TradeSummaryPreviewMetaData> summaryList = trade.getValue();
				summaryList.forEach(preview -> {
					TradeSummary tradeSummary = tradesMap.get(trade.getKey());
					if (null == tradeSummary) {
						tradeSummary = new TradeSummary();
						tradesMap.put(trade.getKey(), tradeSummary);
					}
					tradeSummary.setTrades(trade.getKey());
					switch (preview.getLocation()) {
					case "Vacation":
						tradeSummary.setVacation(tradeSummary.getVacation() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "Camp":
						tradeSummary.setCamp(tradeSummary.getCamp() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "JAFZA_ENOC":
						tradeSummary.setJafza_enoc(tradeSummary.getJafza_enoc() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "NPCC_AUH":
						tradeSummary.setNpcc_auh(tradeSummary.getNpcc_auh() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "Outotec":
						tradeSummary.setOutotec(tradeSummary.getOutotec() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "Petron_EGA":
						tradeSummary.setPetron_ega(tradeSummary.getPetron_ega() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "YD_01":
						tradeSummary.setYd01(tradeSummary.getYd01() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "YD_02":
						tradeSummary.setYd02(tradeSummary.getYd02() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "YD_02_Skid_Division":
						tradeSummary.setYd02SkidDivision(tradeSummary.getYd02SkidDivision() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "YD_05":
						tradeSummary.setYd05(tradeSummary.getYd05() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "YD_06":
						tradeSummary.setYd06(tradeSummary.getYd06() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "QA_QC":
						tradeSummary.setQaqc(tradeSummary.getQaqc() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "HFZ":
						tradeSummary.setHfz(tradeSummary.getHfz() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "MAK_STYLO":
						tradeSummary.setMakstylo(tradeSummary.getMakstylo() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "MAK_STYLO_AUH":
						tradeSummary.setMakstyloauh(tradeSummary.getMakstyloauh() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "PETROFAC_AUH":
						tradeSummary.setPetrofacauh(tradeSummary.getPetrofacauh() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "Abudhabi_Project":
						tradeSummary.setAbudhabiproject(tradeSummary.getAbudhabiproject() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					case "Maintenance":
						tradeSummary.setMaintenance(tradeSummary.getMaintenance() + preview.getCount());
						tradeSummary.setTotal(tradeSummary.getTotal() + preview.getCount());
						break;
					}
					tradesMap.put(trade.getKey(), tradeSummary);
					list.add(tradeSummary);
				});
			});
			TradeSummary tradeSummary = tradesMap.get("XTotal Location Vise");
			tradeSummary.setTotal(tradesMap.values().stream().mapToInt(TradeSummary::getTotal).sum());
			tradeSummary
					.setAbudhabiproject(tradesMap.values().stream().mapToInt(TradeSummary::getAbudhabiproject).sum());
			tradeSummary.setCamp(tradesMap.values().stream().mapToInt(TradeSummary::getCamp).sum());
			tradeSummary.setHfz(tradesMap.values().stream().mapToInt(TradeSummary::getHfz).sum());
			tradeSummary.setJafza_enoc(tradesMap.values().stream().mapToInt(TradeSummary::getJafza_enoc).sum());
			tradeSummary.setMaintenance(tradesMap.values().stream().mapToInt(TradeSummary::getMaintenance).sum());
			tradeSummary.setMakstylo(tradesMap.values().stream().mapToInt(TradeSummary::getMakstylo).sum());
			tradeSummary.setMakstyloauh(tradesMap.values().stream().mapToInt(TradeSummary::getMakstyloauh).sum());
			tradeSummary.setNpcc_auh(tradesMap.values().stream().mapToInt(TradeSummary::getNpcc_auh).sum());
			tradeSummary.setOutotec(tradesMap.values().stream().mapToInt(TradeSummary::getOutotec).sum());
			tradeSummary.setPetrofacauh(tradesMap.values().stream().mapToInt(TradeSummary::getPetrofacauh).sum());
			tradeSummary.setPetron_ega(tradesMap.values().stream().mapToInt(TradeSummary::getPetron_ega).sum());
			tradeSummary.setQaqc(tradesMap.values().stream().mapToInt(TradeSummary::getQaqc).sum());
			tradeSummary.setVacation(tradesMap.values().stream().mapToInt(TradeSummary::getVacation).sum());
			tradeSummary.setYd01(tradesMap.values().stream().mapToInt(TradeSummary::getYd01).sum());
			tradeSummary.setYd02(tradesMap.values().stream().mapToInt(TradeSummary::getYd02).sum());
			tradeSummary
					.setYd02SkidDivision(tradesMap.values().stream().mapToInt(TradeSummary::getYd02SkidDivision).sum());
			tradeSummary.setYd05(tradesMap.values().stream().mapToInt(TradeSummary::getYd05).sum());
			tradeSummary.setYd06(tradesMap.values().stream().mapToInt(TradeSummary::getYd06).sum());
			tradesMap.put("XTotal Location Vise", tradeSummary);
			employeeTrackerTradeSummaryRepo.saveAll(tradesMap.values());
			return tradesMap;
		} catch (EmployeeTrackerDataAccessException exception) {
			log.error("Exception in fetchInactiveEmployee", exception);
			throw new EmployeeTrackerServiceException("Exception in fetchInactiveEmployee", exception);
		}
	}

	@Override
	public Map<String, TradeSummary> saveSummary() {
		return null;
	}

}
