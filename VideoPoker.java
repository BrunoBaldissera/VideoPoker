import java.io.IOException;

/**
 * Classe principal do programa, contendo os atributos do jogador
 * e operando o jogo
 * @author Bruno Baldissera 10724351
 * @author Rafael Ceneme 	9898610
 */
public class VideoPoker {

	private int creditos;
	private int aposta;
	
	/**
	 * Inicializa os creditos do jogador como 200
	 */
	public VideoPoker() {
		this.creditos = 200;
		this.aposta = 0;
	}
	
	public void setCreditos(int x) {
		this.creditos = x;
	}
	
	public int getCreditos() {
		return this.creditos;
	}
	
	public void setAposta(int x) {
		this.aposta = x;
	}
	
	public int getAposta() {
		return this.aposta;
	}
	
	/**
	 * Metodo main que opera o programa
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		VideoPoker player = new VideoPoker();
		Baralho b = new Baralho();
		Pontos p = new Pontos();
		int op = -1;
		boolean inv = true;
		String quais = new String();
		
		System.out.println("$$\\    $$\\ $$$$$$\\ $$$$$$$\\  $$$$$$$$\\  $$$$$$\\        $$$$$$$\\   $$$$$$\\  $$\\   $$\\ $$$$$$$$\\ $$$$$$$\\  ");
		System.out.println("$$ |   $$ |\\_$$  _|$$  __$$\\ $$  _____|$$  __$$\\       $$  __$$\\ $$  __$$\\ $$ | $$  |$$  _____|$$  __$$\\ ");
		System.out.println("$$ |   $$ |  $$ |  $$ |  $$ |$$ |      $$ /  $$ |      $$ |  $$ |$$ /  $$ |$$ |$$  / $$ |      $$ |  $$ |");
		System.out.println("\\$$\\  $$  |  $$ |  $$ |  $$ |$$$$$\\    $$ |  $$ |      $$$$$$$  |$$ |  $$ |$$$$$  /  $$$$$\\    $$$$$$$  |");
		System.out.println(" \\$$\\$$  /   $$ |  $$ |  $$ |$$  __|   $$ |  $$ |      $$  ____/ $$ |  $$ |$$  $$<   $$  __|   $$  __$$< ");
		System.out.println("  \\$$$  /    $$ |  $$ |  $$ |$$ |      $$ |  $$ |      $$ |      $$ |  $$ |$$ |\\$$\\  $$ |      $$ |  $$ |");
		System.out.println("   \\$  /   $$$$$$\\ $$$$$$$  |$$$$$$$$\\  $$$$$$  |      $$ |       $$$$$$  |$$ | \\$$\\ $$$$$$$$\\ $$ |  $$ |");
		System.out.println("    \\_/    \\______|\\_______/ \\________| \\______/       \\__|       \\______/ \\__|  \\__|\\________|\\__|  \\__|\n\n");
		
		System.out.println("Seja bem-vindo a esta eletrizante jornada pelo universo do VideoPoker!\n");
		System.out.println("Voce possui 200 creditos\n");
		
		while ( (op != 0) && (player.getCreditos() > 0) ){
			b.embaralha();
			System.out.println("Novo round!\n"); 
			while (inv == true){
				System.out.println("Insira um valor para apostar");
				player.setAposta(EntradaTeclado.leInt());
				
				if (player.getAposta() <= 0 || player.getAposta() > player.getCreditos()) {
					System.out.println("Valor invalido para aposta, tente novamente");
				}
				else inv = false;
			}
			
			player.setCreditos(player.getCreditos() - player.getAposta());
			
			b.maoAleatoria();	//gera mao inicial
			System.out.println("Sua mao inicial:");
			System.out.println(b.toString());

			System.out.println("Insira a posicao das cartas que quer trocar, separadas por espacos (ex: 1 3 5)");
			System.out.println("Se nao quiser trocar, de [ENTER]");
			quais = EntradaTeclado.leString();
			
			b.TrocaCartas(quais);
			System.out.println("Novas cartas:");
			System.out.println(b.toString());
			
			System.out.println("Troque novamente, se quiser");
			System.out.println("Se nao quiser trocar, de [ENTER]");
			quais = EntradaTeclado.leString();
			
			b.TrocaCartas(quais);
			System.out.println("Suas cartas:");
			System.out.println(b.toString());
			
			player.setCreditos( player.getCreditos() + (p.calcularGanhos(player.getAposta(), b.getMao())) );
			
			System.out.println("\nVocê possui " + player.getCreditos() + " creditos");
			
			if (player.getCreditos() <= 0) {
				System.out.println("Acabaram-se os creditos\n");
				break;
			}
			
			System.out.println("\nDeseja jogar mais uma rodada?\n\n0-Nao\n1-Sim");
			op = EntradaTeclado.leInt();
			inv = true;
		}
		System.out.println("Até a próxima, jogador.");
	}
}