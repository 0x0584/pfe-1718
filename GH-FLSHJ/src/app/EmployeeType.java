package app;

public enum EmployeeType {
	Normal, Prof, All;
	public static EmployeeType filter(boolean a, boolean b) {
		EmployeeType type;

		if (a && b) {
			type = EmployeeType.All;
		} else if (a) {
			type = EmployeeType.Prof;
		} else if (b) {
			type = EmployeeType.Normal;
		} else {
			type = EmployeeType.All;
		}

		return type;
	}
}
