/**
 * 
 */
package com.technomak.employeetracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.technomak.employeetracker.entity.EmployeeTransferRecord;

/**
 * @author JaY
 *
 */
@Repository
public interface IEmployeeTransferRecordRepo extends JpaRepository<EmployeeTransferRecord, Integer> {

	List<EmployeeTransferRecord> findByEmpId(@Param("empId") Integer empId);

	List<EmployeeTransferRecord> findAllByEmpIdOrderByDateOfTransferDesc(@Param("empId") Integer empId);

}
