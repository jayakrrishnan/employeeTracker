/**
 * 
 */
package com.technomak.employeetracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technomak.employeetracker.entity.TradeSummary;

/**
 * @author JaY
 *
 */
@Repository
public interface IEmployeeTrackerTradeSummaryRepo extends JpaRepository<TradeSummary, String>{

}
