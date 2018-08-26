/**
 * 
 */
package com.technomak.employeetracker.common;

/**
 * @author JaY
 *
 */
public class TradeSummaryPreviewMetaData {

	private String location;
	
	private int count;

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TradeSummaryPreviewMetaData [location=" + location + ", count=" + count + "]";
	}

}
