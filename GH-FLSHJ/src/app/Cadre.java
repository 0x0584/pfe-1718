package app;

public enum Cadre {
		C_0("Engenieur 1er Grade"),
	
		C_1("Technicien 1er Grade"),
	
		C_2("Technicien 2eme Grade");
	
		private String title;
	
		private Cadre(String title) {
			this.setTitle(title);
		}
	
		public String getTitle( ) {
			return title;
		}
	
		private void setTitle(String title) {
			this.title = title;
		}
	
		public static Cadre parseCadre(String cstr) {
			for (Cadre c : values( )) {
				if (c.title.compareTo(cstr) == 0) return c;
			}
	
			return C_0;
		}
	
		public String toString( ) {
			return title;
		}
	}