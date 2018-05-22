package operation;

public enum Type {
	Normal, Prof, All;
	public static Type filter(boolean a, boolean b) {
		Type type;

		if (a && b) {
			type = Type.All;
		} else if (a) {
			type = Type.Prof;
		} else if (b) {
			type = Type.Normal;
		} else {
			type = Type.All;
		}

		return type;
	}
}
