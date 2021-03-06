package sernet.gs.reveng;

// Generated Jun 5, 2015 1:28:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * FilterInnerjoin generated by hbm2java
 */
public class FilterInnerjoin implements java.io.Serializable {

	private int ftrId;
	private String ftrTarget;
	private String ftrJoin;
	private String kommentar;
	private Set filterSuchenFelders = new HashSet(0);

	public FilterInnerjoin() {
	}

	public FilterInnerjoin(int ftrId) {
		this.ftrId = ftrId;
	}

	public FilterInnerjoin(int ftrId, String ftrTarget, String ftrJoin,
			String kommentar, Set filterSuchenFelders) {
		this.ftrId = ftrId;
		this.ftrTarget = ftrTarget;
		this.ftrJoin = ftrJoin;
		this.kommentar = kommentar;
		this.filterSuchenFelders = filterSuchenFelders;
	}

	public int getFtrId() {
		return this.ftrId;
	}

	public void setFtrId(int ftrId) {
		this.ftrId = ftrId;
	}

	public String getFtrTarget() {
		return this.ftrTarget;
	}

	public void setFtrTarget(String ftrTarget) {
		this.ftrTarget = ftrTarget;
	}

	public String getFtrJoin() {
		return this.ftrJoin;
	}

	public void setFtrJoin(String ftrJoin) {
		this.ftrJoin = ftrJoin;
	}

	public String getKommentar() {
		return this.kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public Set getFilterSuchenFelders() {
		return this.filterSuchenFelders;
	}

	public void setFilterSuchenFelders(Set filterSuchenFelders) {
		this.filterSuchenFelders = filterSuchenFelders;
	}

}
