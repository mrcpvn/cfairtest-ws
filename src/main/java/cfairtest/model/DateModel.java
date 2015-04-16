package cfairtest.model;

public class DateModel {
	
	private String sysdate;
	private String timeZone;

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sysdate == null) ? 0 : sysdate.hashCode());
		result = prime * result
				+ ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateModel other = (DateModel) obj;
		if (sysdate == null) {
			if (other.sysdate != null)
				return false;
		} else if (!sysdate.equals(other.sysdate))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DateModel [sysdate=");
		builder.append(sysdate);
		builder.append(", timeZone=");
		builder.append(timeZone);
		builder.append("]");
		return builder.toString();
	}

}
