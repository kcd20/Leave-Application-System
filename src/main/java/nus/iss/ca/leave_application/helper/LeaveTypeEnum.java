package nus.iss.ca.leave_application.helper;

/** @Author Fusheng Tan @Version 1.0 */
public enum LeaveTypeEnum {
	  ANNUAL_LEAVE("Annual Leave", 1.0),
	  MEDICAL_LEAVE("Medical Leave", 1.0),
	  COMPENSATION_LEAVE("Compensation Leave", 1.0),
	  HALF_COMPENSATION_LEAVE("Half-Day Compensation Leave", 0.5);
	  
	  private Double duration;
	  private final String displayValue;
	  
		private LeaveTypeEnum(String displayValue, Double duration){
			this.displayValue = displayValue;
			this.duration = duration;
		}
	}
