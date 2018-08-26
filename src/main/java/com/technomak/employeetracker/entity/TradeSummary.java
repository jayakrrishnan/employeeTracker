/**
 * 
 */
package com.technomak.employeetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JaY
 *
 */
@Entity
@Table(name="summary-all_trades")
public class TradeSummary {
	@Id
	@Column(name = "trades")
	private String trades;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "Vacation")
	private int vacation;
	
	@Column(name = "JAFZA_ENOC")
	private int jafza_enoc;
	
	@Column(name = "NPCC_AUH")
	private int npcc_auh;
	
	@Column(name = "Outotec")
	private int outotec;
	
	@Column(name = "Petron_EGA")
	private int petron_ega;
	
	@Column(name = "YD_01")
	private int yd01;
	
	@Column(name = "YD_02")
	private int yd02;
	
	@Column(name = "YD_02_Skid_Division")
	private int yd02SkidDivision;
	
	@Column(name = "YD_05")
	private int yd05;
	
	@Column(name = "YD_06")
	private int yd06;
	
	@Column(name = "QA_QC")
	private int qaqc;
	
	@Column(name = "HFZ")
	private int hfz;
	
	@Column(name = "MAK_STYLO")
	private int makstylo;
	
	@Column(name = "MAK_STYLO_AUH")
	private int makstyloauh;
	
	@Column(name = "PETROFAC_AUH")
	private int petrofacauh;
	
	@Column(name = "Abudhabi_Project")
	private int abudhabiproject;
	
	@Column(name = "Maintenance")
	private int maintenance;
	
	@Column(name = "Camp")
	private int camp;

	/**
	 * @return the trades
	 */
	public String getTrades() {
		return trades;
	}

	/**
	 * @param trades the trades to set
	 */
	public void setTrades(String trades) {
		this.trades = trades;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the vacation
	 */
	public int getVacation() {
		return vacation;
	}

	/**
	 * @param vacation the vacation to set
	 */
	public void setVacation(int vacation) {
		this.vacation = vacation;
	}

	/**
	 * @return the jafza_enoc
	 */
	public int getJafza_enoc() {
		return jafza_enoc;
	}

	/**
	 * @param jafza_enoc the jafza_enoc to set
	 */
	public void setJafza_enoc(int jafza_enoc) {
		this.jafza_enoc = jafza_enoc;
	}

	/**
	 * @return the npcc_auh
	 */
	public int getNpcc_auh() {
		return npcc_auh;
	}

	/**
	 * @param npcc_auh the npcc_auh to set
	 */
	public void setNpcc_auh(int npcc_auh) {
		this.npcc_auh = npcc_auh;
	}

	/**
	 * @return the outotec
	 */
	public int getOutotec() {
		return outotec;
	}

	/**
	 * @param outotec the outotec to set
	 */
	public void setOutotec(int outotec) {
		this.outotec = outotec;
	}

	/**
	 * @return the petron_ega
	 */
	public int getPetron_ega() {
		return petron_ega;
	}

	/**
	 * @param petron_ega the petron_ega to set
	 */
	public void setPetron_ega(int petron_ega) {
		this.petron_ega = petron_ega;
	}

	/**
	 * @return the yd01
	 */
	public int getYd01() {
		return yd01;
	}

	/**
	 * @param yd01 the yd01 to set
	 */
	public void setYd01(int yd01) {
		this.yd01 = yd01;
	}

	/**
	 * @return the yd02
	 */
	public int getYd02() {
		return yd02;
	}

	/**
	 * @param yd02 the yd02 to set
	 */
	public void setYd02(int yd02) {
		this.yd02 = yd02;
	}

	/**
	 * @return the yd02SkidDivision
	 */
	public int getYd02SkidDivision() {
		return yd02SkidDivision;
	}

	/**
	 * @param yd02SkidDivision the yd02SkidDivision to set
	 */
	public void setYd02SkidDivision(int yd02SkidDivision) {
		this.yd02SkidDivision = yd02SkidDivision;
	}

	/**
	 * @return the yd05
	 */
	public int getYd05() {
		return yd05;
	}

	/**
	 * @param yd05 the yd05 to set
	 */
	public void setYd05(int yd05) {
		this.yd05 = yd05;
	}

	/**
	 * @return the yd06
	 */
	public int getYd06() {
		return yd06;
	}

	/**
	 * @param yd06 the yd06 to set
	 */
	public void setYd06(int yd06) {
		this.yd06 = yd06;
	}

	/**
	 * @return the qaqc
	 */
	public int getQaqc() {
		return qaqc;
	}

	/**
	 * @param qaqc the qaqc to set
	 */
	public void setQaqc(int qaqc) {
		this.qaqc = qaqc;
	}

	/**
	 * @return the hfz
	 */
	public int getHfz() {
		return hfz;
	}

	/**
	 * @param hfz the hfz to set
	 */
	public void setHfz(int hfz) {
		this.hfz = hfz;
	}

	/**
	 * @return the makstylo
	 */
	public int getMakstylo() {
		return makstylo;
	}

	/**
	 * @param makstylo the makstylo to set
	 */
	public void setMakstylo(int makstylo) {
		this.makstylo = makstylo;
	}

	/**
	 * @return the makstyloauh
	 */
	public int getMakstyloauh() {
		return makstyloauh;
	}

	/**
	 * @param makstyloauh the makstyloauh to set
	 */
	public void setMakstyloauh(int makstyloauh) {
		this.makstyloauh = makstyloauh;
	}

	/**
	 * @return the petrofacauh
	 */
	public int getPetrofacauh() {
		return petrofacauh;
	}

	/**
	 * @param petrofacauh the petrofacauh to set
	 */
	public void setPetrofacauh(int petrofacauh) {
		this.petrofacauh = petrofacauh;
	}

	/**
	 * @return the abudhabiproject
	 */
	public int getAbudhabiproject() {
		return abudhabiproject;
	}

	/**
	 * @param abudhabiproject the abudhabiproject to set
	 */
	public void setAbudhabiproject(int abudhabiproject) {
		this.abudhabiproject = abudhabiproject;
	}

	/**
	 * @return the maintenance
	 */
	public int getMaintenance() {
		return maintenance;
	}

	/**
	 * @param maintenance the maintenance to set
	 */
	public void setMaintenance(int maintenance) {
		this.maintenance = maintenance;
	}

	/**
	 * @return the camp
	 */
	public int getCamp() {
		return camp;
	}

	/**
	 * @param camp the camp to set
	 */
	public void setCamp(int camp) {
		this.camp = camp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TradeSummary [trades=" + trades + ", total=" + total + ", vacation=" + vacation + ", jafza_enoc="
				+ jafza_enoc + ", npcc_auh=" + npcc_auh + ", outotec=" + outotec + ", petron_ega=" + petron_ega
				+ ", yd01=" + yd01 + ", yd02=" + yd02 + ", yd02SkidDivision=" + yd02SkidDivision + ", yd05=" + yd05
				+ ", yd06=" + yd06 + ", qaqc=" + qaqc + ", hfz=" + hfz + ", makstylo=" + makstylo + ", makstyloauh="
				+ makstyloauh + ", petrofacauh=" + petrofacauh + ", abudhabiproject=" + abudhabiproject
				+ ", maintenance=" + maintenance + ", camp=" + camp + "]";
	}

}
