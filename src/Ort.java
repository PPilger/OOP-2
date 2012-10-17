import java.util.ArrayList;


public class Ort {

	private String name;
	private String adresse;
	private ArrayList<String> infrastruktur;
	
	public String toString()
	{
		return name;
	}
	
	public String toDetailString()
	{
		return "Name: " + name + " Adresse: " + adresse + infrastruktur.toString();
	}
		
	public Ort(String name, String adresse, ArrayList<String> infrastruktur)
	{
		this.name = name; this.adresse = adresse; this.infrastruktur = infrastruktur;
	}
	
	/**
	 * 
	 * @author Koegler Alexander
	 *
	 */
	public static class NameSelektor implements Selektor<Ort>{

		private String name;
		public NameSelektor(String name)
		{
			this.name = name;
		}
		@Override
		public boolean select(Ort item) {
			return item.name.equalsIgnoreCase(name);
		}
	}
	
	/**
	 * 
	 * @author Koegler Alexander
	 *
	 */
	public static class AdresseSelektor implements Selektor<Ort>{

		private String name;
		private boolean enthaelt;
		/**
		 * Stellt den Selektor so ein, dass Elemente erfolgreich verglichen werden, wenn die Adresse einander eins zu eins gleichen
		 * @param adresse die zu pruefende Adresse
		 */
		public AdresseSelektor(String adresse)
		{
			this.name = adresse;
			this.enthaelt = false;
		}
		
		/**
		 * Stellt den Selektor so ein, dass Elemente erfolgreich verglichen werden, sobald die Adresse den im Parameter angegeben String enthaelt
		 * @param adresse die zu pruefende Adresse
		 * @param okIfContains Wenn True so wird nur darauf geachtet das die Adresse enthalten ist, bei False muessen die Adressen komplett gleich sein. 
		 */
		public AdresseSelektor(String adresse, boolean okIfContains)
		{
			this.name = adresse;
			this.enthaelt = okIfContains;
		}
		
		@Override
		public boolean select(Ort item) {
			if(enthaelt)
			{
				//kommt der String nicht vor, wird -1 zurückgegeben
				return item.adresse.indexOf(name) != -1;
			}else
			{
				return item.adresse.equalsIgnoreCase(name);
			}
		}		
	}
	
	//TODO: infrastruktur Selektor
}
