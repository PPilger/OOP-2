import java.io.Serializable;
import java.util.ArrayList;


public class Ort implements Serializable{

	private String bezeichnung;
	private ArrayList<String> infrastruktur;
	
	public String toString()
	{
		return bezeichnung;
	}
	
	public String toDetailString()
	{
		return "Bezeichnung: " + bezeichnung + infrastruktur.toString();
	}
	
	private Ort(){bezeichnung = "";infrastruktur = new ArrayList<String>();}
	
	public Ort(String bezeichnung, ArrayList<String> infrastruktur)
	{
		this.bezeichnung = bezeichnung; this.infrastruktur = infrastruktur;
	}
	
	/**
	 * 
	 * @author Koegler Alexander
	 *
	 */
	public static class BezeichnungSelektor implements Selektor<Ort>{

		private String name;
		private boolean enthaelt;
		
		/**
		 * Stellt den Selektor so ein, dass Elemente erfolgreich verglichen werden, wenn die bezeichnung einander eins zu eins gleichen
		 * @param bezeichnung die zu pruefende bezeichnung
		 */
		public BezeichnungSelektor(String bezeichnung)
		{
			this.name = bezeichnung; enthaelt = false;
		}
		
		/**
		 * Stellt den Selektor so ein, dass Elemente erfolgreich verglichen werden, sobald die bezeichnung den im Parameter angegeben String enthaelt
		 * @param bezeichnung die zu pruefende bezeichnung
		 * @param okIfContains Wenn True so wird nur darauf geachtet das die bezeichnung enthalten ist, bei False muessen die bezeichnungn komplett gleich sein. 
		 */
		public BezeichnungSelektor(String bezeichnung, boolean okIfContains)
		{
			this.name = bezeichnung; this.enthaelt = okIfContains;			
		}
		
		@Override
		public boolean select(Ort item) {
			if(enthaelt)
			{
				//kommt der String nicht vor, wird -1 zurückgegeben
				return item.bezeichnung.indexOf(name) != -1;
			}else
			{
				return item.bezeichnung.equalsIgnoreCase(name);
			}
		}	
	}

	
	public static class InfrastrukturSelektor implements Selektor<Ort>
	{
		private String name;
		
		public InfrastrukturSelektor(String einrichtung){
			this.name = einrichtung;
		}

		@Override
		/**
		 * ueberprueft ob die Liste der infrastrukuren ein element mit der selben bezeichnung enthaelt
		 */
		public boolean select(Ort item) {
			for(String str : item.infrastruktur)
			{
				if(str.equalsIgnoreCase(name))
				{
					return true;
				}
			}
			return false;
		}
		
	}
}
