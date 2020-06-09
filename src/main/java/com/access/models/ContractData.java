package com.access.models;

import java.io.Serializable;

public class ContractData implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long contractId;
	private String contractName;
	private String cdl;
	private String offshoreMD;
	private String dul;
	private String projectLead;
	private String masterClient;
	
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getCdl() {
		return cdl;
	}
	public void setCdl(String cdl) {
		this.cdl = cdl;
	}
	public String getOffshoreMD() {
		return offshoreMD;
	}
	public void setOffshoreMD(String offshoreMD) {
		this.offshoreMD = offshoreMD;
	}
	public String getDul() {
		return dul;
	}
	public void setDul(String dul) {
		this.dul = dul;
	}
	public String getProjectLead() {
		return projectLead;
	}
	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}
	public String getMasterClient() {
		return masterClient;
	}
	public void setMasterClient(String masterClient) {
		this.masterClient = masterClient;
	}
	
	@Override
	public String toString() {
		return "ContractData [contractId=" + contractId + ", contractName=" + contractName + ", cdl=" + cdl
				+ ", offshoreMD=" + offshoreMD + ", dul=" + dul + ", projectLead=" + projectLead + ", masterClient="
				+ masterClient + "]";
	}	
}
