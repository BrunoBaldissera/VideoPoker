/**
 * Esta classe auxilia na manipulacao de diversas cartas simultaneamente
 * @author Bruno Baldissera 10724351
 * @author Rafael Ceneme 	9898610
 */
public class Baralho {
	private Carta[] mao;
	private boolean[] baralho;
	
	/**
	 * Inicializa as cartas da mao e permite que todas as cartas
	 * estejam no baralho por meio do vetor baralho de booleanos.
	 */
	public Baralho(){
		this.mao = new Carta[5];
		for(int i = 0; i < 5; i++){
			this.mao[i] = new Carta();
		}
		
		baralho = new boolean[52];
		for(int i = 0; i < 52; i++){
			this.baralho[i] = true;
		}
	}
	
	/**
	 * Reseta as cartas disponiveis do baralho
	 */
	public void embaralha(){
		for(int i = 0; i < 52; i++){
			this.baralho[i] = true;
		}
	}
	
	public boolean[] getBaralho(){
		return this.baralho;
	}
	
	public Carta[] getMao(){
		return this.mao;
	}	
	
	public void setBaralho(boolean[] baralho){
		this.baralho = baralho;
	}
	
	public void setMao(Carta[] mao){
		this.mao = mao;
	}
	
	/**
	 * Indisponibiliza uma carta de ser sorteada
	 * @param carta a ser removida do baralho
	 */
	public void RemoveCarta(Carta c){
		baralho[c.getCarta()] = false;
	}
	
	/**
	 * Gera uma mao aleatoria de cartas
	 * @throws InterruptedException
	 */
	public void maoAleatoria() throws InterruptedException {
		for (int i=0; i<5; i++)
			this.mao[i].TrocaCarta();
	
	}
	
	/**
	 * Recebe uma string contendo a posicao das cartas a serem trocadas
	 * e as troca por cartas aleatorias, desde que elas estejam no baralho
	 * @param String contendo as posicoes a serem trocadas
	 * @throws InterruptedException
	 */
	public void TrocaCartas(String s) throws InterruptedException{
		
		boolean[] quais = new boolean[5];
		for(int a = 0; a < 5; a++){
			quais[a] = false;
		}
		
		for(int i = 0; i < s.length(); i +=2){
			quais[(s.charAt(i) - 1) - '0'] = true;
		}
		
		for(int i = 0; i < 5; i++){
			if (quais[i]){
				RemoveCarta(this.mao[i]);
				this.mao[i].TrocaCarta();
				if (!this.baralho[this.mao[i].getCarta()]) i--;
			}
		}
	}
	
	/**
	 * Gera e retorna uma string que representa visualmente o objeto mao do
	 * baralho
	 * @return ascii-art de todas as cartas na mao
	 */
	public java.lang.String toString(){
		
		int nlinhas = 9;
		String[][] s = new String[5][nlinhas];
		String res = new String();
		
		for(int a = 0; a < 5; a++){
			res += "     " + (a+1) + "\t\t\t";
		}
		res += "\n";
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < nlinhas; j++){
				s[i] = this.mao[i].toString().split("\n");
			}
		}
		
		for(int k = 0; k < nlinhas; k++){
			for(int l = 0; l < 5; l++){
				res += s[l][k] + "\t\t";
			}
			res += "\n";
		}
		return res;
	}
}
