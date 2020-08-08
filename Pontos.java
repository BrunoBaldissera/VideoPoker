/**
 * Esta classe identifica combinacoes validas de cartas e 
 * calcula os ganhos das apostas
 * @author Bruno Baldissera 10724351
 * @author Rafael Ceneme 	9898610
 */
public class Pontos {

	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "dois pares"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean doisPares(Carta[] mao) {
		boolean par1 = false;
		boolean par2 = false;
		int[] bucket = new int[15];
		for (int i = 0; i < mao.length; i++){
			mao[i].setTipo();
			bucket[mao[i].getTipo()]++;
		}
		
		int aux = 15;
		for (int i = 0; i < bucket.length; i++){
			if (bucket[i] == 2) {
				par1 = true;
				aux = i;
				break;
			}
		}
		
		for (aux = aux+1; aux < bucket.length; aux++){
			if (bucket[aux] == 2) {
				par2 = true;
			}
		}
		
		if (par1 && par2) return true;
		else return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "trinca"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean trinca(Carta[] mao) {
		
		int[] bucket = new int[15];
		
		for (int i = 0; i < mao.length; i++){
			mao[i].setTipo();
			bucket[mao[i].getTipo()]++;
		}
		
		for (int i = 0; i < bucket.length; i++){
			if (bucket[i] == 3) return true;
		}
		return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "straight"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean straight(Carta[] mao) {
		
		int[] bucket = new int[15];
		
		for (int i = 0; i < mao.length; i++){
			mao[i].setTipo();
			bucket[mao[i].getTipo()]++;
		}
		
		for (int i = 0; i < bucket.length; i++){
			if(bucket[i] == 1) {
				if (i == 10) break;
				if (bucket[i+1]==1 && bucket[i+2]==1 && bucket[i+3]==1
						&& bucket[i+4]==1) {
					return true;
				}
				else return false;
			}	
		}
		return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "flush"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean flush(Carta[] mao) {
		mao[0].setNaipe();
		int naipe = mao[0].getNaipe();
		
		for (int i = 1; i < mao.length; i++){
			mao[i].setNaipe();
			if (mao[i].getNaipe() != naipe) return false;
		}
		
		return true;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "full hand"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean fullHand(Carta[] mao) {
		if (trinca(mao)){
			int[] bucket = new int[15];
			
			for (int i = 0; i < mao.length; i++){
				mao[i].setTipo();
				bucket[mao[i].getTipo()]++;
			}

			for (int i = 0; i < bucket.length; i++){
				if (bucket[i] == 2) return true;
			}
			return false;	
		}
		else return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "quadra"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean quadra(Carta[] mao) {
		int[] bucket = new int[15];
		
		for (int i = 0; i < mao.length; i++){
			mao[i].setTipo();
			bucket[mao[i].getTipo()]++;
		}
		
		for (int i = 0; i < bucket.length; i++){
			if (bucket[i] == 4) return true;
		}
		
		return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "straight flush"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean straightFlush(Carta[] mao) {
		if (flush(mao)){
			boolean straight = straight(mao);
			if (straight) return true;
			else return false;
		}
		else return false;
	}
	
	/**
	 * Recebe cartas via parametro e verifica se constituem a combinacao
	 * "royal straight flush"
	 * @param mao Vetor de cartas
	 * @return True caso contenha a combinacao e false caso contrario
	 */
	public boolean royalStraightFlush(Carta[] mao) {
		if (straightFlush(mao)){
			boolean ace = false;
			boolean ten = false;
			
			for(int i=0; i < mao.length; i++){
				mao[i].setTipo();
				if (mao[i].getTipo() == 10) ten = true;
				if (mao[i].getTipo() == 14) ace = true;
			}
			
			if (ten && ace) return true;
			else return false;
		}
		else return false;
	}
	
	/**
	 * Recebe um inteiro representando o valor apostado e um vetor
	 * de cartas representando as cartas na mao atual do jogador.
	 * Verifica se existe alguma combinacao e retorna o valor obtido
	 * pela aposta.
	 * @param bet Valor da aposta
	 * @param mao Cartas da mao atual
	 * @return Ganhos obtidos
	 */
	public int calcularGanhos(int bet, Carta[] mao) {
		if (royalStraightFlush(mao)){
			System.out.println("Parabens, vc obteve um royal straight flush!");
			return bet * 200;
		}
		else if (straightFlush(mao)){
			System.out.println("Parabens, vc obteve um straight flush!");
			return bet * 100;
		}
		else if (quadra(mao)){
			System.out.println("Parabens, vc obteve uma quadra!");
			return bet * 50;
		}
		else if (fullHand(mao)){
			System.out.println("Parabens, vc obteve uma full hand!");
			return bet * 20;
		}
		else if (flush(mao)){
			System.out.println("Parabens, vc obteve um flush!");
			return bet * 10;
		}
		else if (straight(mao)){
			System.out.println("Parabens, vc obteve um straight!");
			return bet * 5;
		}
		else if (trinca(mao)){
			System.out.println("Parabens, vc obteve uma trinca!");
			return bet * 2;
		}
		else if (doisPares(mao)){
			System.out.println("Parabens, vc obteve dois pares!");
			return bet * 1;
		}
		else {
			System.out.println("Voce nao obteve nenhuma combinacao. . .");
			return 0;
		}
	}
}
