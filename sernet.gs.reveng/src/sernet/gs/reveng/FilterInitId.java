package sernet.gs.reveng;

// Generated Jun 5, 2015 1:28:30 PM by Hibernate Tools 3.4.0.CR1

/**
 * FilterInitId generated by hbm2java
 */
public class FilterInitId implements java.io.Serializable {

	private String sucheId;
	private byte levelId;

	public FilterInitId() {
	}

	public FilterInitId(String sucheId, byte levelId) {
		this.sucheId = sucheId;
		this.levelId = levelId;
	}

	public String getSucheId() {
		return this.sucheId;
	}

	public void setSucheId(String sucheId) {
		this.sucheId = sucheId;
	}

	public byte getLevelId() {
		return this.levelId;
	}

	public void setLevelId(byte levelId) {
		this.levelId = levelId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FilterInitId))
			return false;
		FilterInitId castOther = (FilterInitId) other;

		return ((this.getSucheId() == castOther.getSucheId()) || (this
				.getSucheId() != null && castOther.getSucheId() != null && this
				.getSucheId().equals(castOther.getSucheId())))
				&& (this.getLevelId() == castOther.getLevelId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSucheId() == null ? 0 : this.getSucheId().hashCode());
		result = 37 * result + this.getLevelId();
		return result;
	}

}