/**
 * 
 */
package com.technomak.employeetracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technomak.employeetracker.entity.EmployeeTracker;

/**
 * @author JaY
 *
 */
@Repository
public interface IEmployeeTrackerRepo extends JpaRepository<EmployeeTracker	, Integer>{

}
