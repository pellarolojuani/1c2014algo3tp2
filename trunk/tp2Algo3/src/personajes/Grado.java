package personajes;

/**
 * Created by chris on 06/06/2014.
 */
public enum Grado { NOVATO,DETECTIVE, INVESTIGADOR,SARGENTO;

	public Grado getNext(){
		if ( this.ordinal() < Grado.values().length - 1 ){
			return Grado.values()[this.ordinal()+1];
		}
		else {
			return this;
		}
	}
}
