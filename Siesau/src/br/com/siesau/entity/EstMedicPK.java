package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the est_medic database table.
 * 
 */
@Embeddable
public class EstMedicPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cd_est_medic", unique=true, nullable=false)
	private Integer cdEstMedic;

	@Column(name="cd_medicam", unique=true, nullable=false)
	private Integer cdMedicam;

	@Column(name="cd_localest", unique=true, nullable=false)
	private Integer cdLocalest;

	@Column(name="cd_forn", unique=true, nullable=false)
	private Integer cdForn;

	public EstMedicPK() {
	}
	public Integer getCdEstMedic() {
		return this.cdEstMedic;
	}
	public void setCdEstMedic(Integer cdEstMedic) {
		this.cdEstMedic = cdEstMedic;
	}
	public Integer getCdMedicam() {
		return this.cdMedicam;
	}
	public void setCdMedicam(Integer cdMedicam) {
		this.cdMedicam = cdMedicam;
	}
	public Integer getCdLocalest() {
		return this.cdLocalest;
	}
	public void setCdLocalest(Integer cdLocalest) {
		this.cdLocalest = cdLocalest;
	}
	public Integer getCdForn() {
		return this.cdForn;
	}
	public void setCdForn(Integer cdForn) {
		this.cdForn = cdForn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstMedicPK)) {
			return false;
		}
		EstMedicPK castOther = (EstMedicPK)other;
		return 
			this.cdEstMedic.equals(castOther.cdEstMedic)
			&& this.cdMedicam.equals(castOther.cdMedicam)
			&& this.cdLocalest.equals(castOther.cdLocalest)
			&& this.cdForn.equals(castOther.cdForn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEstMedic.hashCode();
		hash = hash * prime + this.cdMedicam.hashCode();
		hash = hash * prime + this.cdLocalest.hashCode();
		hash = hash * prime + this.cdForn.hashCode();
		
		return hash;
	}
}