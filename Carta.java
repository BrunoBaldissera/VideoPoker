/**
 * Esta classe representa uma unica carta, usando primariamente um numero
 * entre 1 e 52 para identifica-la, mas tambem usando os atributos tipo e 
 * naipe para auxilio.
 * @author Bruno Baldissera
 * @author Rafael Ceneme
 */

public class Carta {
	private int carta;
	
	private int tipo;
	private char naipe;
	
	public int getCarta(){
		return this.carta;
	}
	
	public void setCarta(int carta){
		this.carta = carta;
	}
	
	/**
	 * Inicializa a carta como um 2 de espadas (1)
	 */
	public Carta(){
		this.carta = 1;
	}
	
	/**
	 * Inicializa a carta de acordo com o parametro carta
	 * @param Valor entre 1 e 52
	 */
	public Carta(int carta){
		this.carta = carta;
	}
	
	/**
	 * Gera um numero aleatorio entre 1 e 52 que representa uma carta
	 * @throws InterruptedException
	 */
	public void TrocaCarta() throws InterruptedException{
		Random r = new Random();
		this.setCarta(r.getIntRand(1, 52));
		//---------------DELAY---------------------
				Thread.sleep(10);
		//-----------------------------------------
	}

	/**
	 * A partir do numero da carta, faz os calculos necessarios para
	 * descobrir o naipe, em seguida atribui um char especifico para cada
	 */
	public void setNaipe(){
		if (this.carta >= 1 && this.carta <= 13) this.naipe = '♠';
		else if (this.carta > 13 && this.carta <= 26) this.naipe = '♥';
		else if (this.carta > 26 && this.carta <= 39) this.naipe = '♦';
		else this.naipe = '♣';
	}
	
	public char getNaipe(){
		return this.naipe;
	}
	
	/**
	 * A partir do numero da carta, faz os calculos necessarios para
	 * descobrir o numero da carta, entre 2 e 14, representando 2 ate as
	 */
	public void setTipo(){
		if (this.carta >= 1 && this.carta <= 13) this.tipo = this.carta + 1;
		else if (this.carta > 13 && this.carta <= 26) this.tipo = this.carta - 12;
		else if (this.carta > 26 && this.carta <= 39) this.tipo = this.carta - 25;
		else this.tipo = this.carta -38;
	}
	
	public int getTipo(){
		return this.tipo;
	}
	
	/**
	 * Gera uma string representando o objeto visualmente carta e retorna
	 * @return ascii-art da carta em string
	 */
	public java.lang.String toString(){
		this.setTipo();
		this.setNaipe();
		
		if(this.getTipo() == 10){
			return  "+---------+\n"+
					"|         |\n"+
					"|   "+this.getTipo()+"    |\n"+
					"|         |\n"+
					"|    "+this.getNaipe()+"    |\n"+  
					"|         |\n"+
					"|         |\n"+
					"|         |\n"+
					"+---------+\n";
		}
		
		else if(this.getTipo() < 10){
			return  "+---------+\n"+
					"|         |\n"+
					"|    "+this.getTipo()+"    |\n"+
					"|         |\n"+
					"|    "+this.getNaipe()+"    |\n"+  
					"|         |\n"+
					"|         |\n"+
					"|         |\n"+
					"+---------+\n";
		}
		else{
			char c = 'x';
			if (this.getTipo() == 11) c = 'J';
			else if (this.getTipo() == 12) c = 'Q';
			else if (this.getTipo() == 13) c = 'K';
			else if (this.getTipo() == 14) c = 'A';
			
			return  "+---------+\n"+
					"|         |\n"+
					"|    "+c+"    |\n"+
					"|         |\n"+
					"|    "+this.getNaipe()+"    |\n"+  
					"|         |\n"+
					"|         |\n"+
					"|         |\n"+
					"+---------+\n";
		}
	}
}
