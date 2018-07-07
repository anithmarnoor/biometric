package com.siddhrans.biometric.model;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="BIOMETRIC_MACHINE")
public class BiometricMachine implements Serializable{
 
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MACHINE_ID")
    private Integer machineId;
 
    @NotEmpty
    @Column(name="MACHINE_NAME", unique=true, nullable=false)
    private String machineName;
     
    @NotEmpty
    @Column(name="MACHINE_MODEL", nullable=false)
    private String machineModel;
    
    @Column(name="ORACLE_SUPPORT", nullable=false)
    private String oracleSupport;  

    public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineModel() {
		return machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public String getOracleSupport() {
		return oracleSupport;
	}

	public void setOracleSupport(String oracleSupport) {
		this.oracleSupport = oracleSupport;
	}

	@Override
    public String toString() {
        return "Machine [id=" + machineId + ", machineName=" + machineName + ", machineModel=" + machineModel + ", oracleSupport=" + oracleSupport +"]";
    }
}