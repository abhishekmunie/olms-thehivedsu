/**
 * 
 */
package cf.thehivedsu.olms.leaveApplication;

/**
 * @author abhishekmunie
 *
 */
public enum LeaveType {

	MEDICAL_LEAVE("ML"), CASUAL_LEAVE("CL"), VACAION_LEAVE("VL"), STUDY_LEAVE("SL"), PREGNANCY_CHILD_CARE_LEAVE(
			"PC"), FAMILY_RESPONSIBILITY_LEAVE("FR"), UNPAID_LEAVE("UL");

	private String code;

	LeaveType(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public static boolean isValidCode(String code) {
		for (LeaveType leaveType : values()) {
			if (leaveType.getCode().equals(code)) {
				return true;
			}
		}

		return false;
	}
}
