import java.util.Random;
import java.util.Scanner;

public class finalBlackJack {
	final static String NL = System.getProperty("line.separator");
	final static int ROWS = 2;
	final static int COLS = 52;
	static char playAgain;
	static double money;
	static double bet;
	static int playerTotal, dealerTotal;
	static char hit;
	static boolean win;
	static double payoutRate;

	public static void main(String[] args) {

		int[][] deck = deck();

		Scanner reader = new Scanner(System.in);
		System.out
				.println("Welcome to Black Jack!"
						+ NL
						+ NL
						+ NL
						+ "To begin, please input how much money you would like to start with...");
		money = reader.nextDouble();

		System.out
				.println("Please input your pay out rate.. ex: set to 1 for gain = to bet, 2 for double, etc...");
		payoutRate = reader.nextDouble();

		do {
			win = false;

			System.out.println("Place your bet! There is currently a "
					+ payoutRate + " to 1 payout");
			bet = reader.nextDouble();

			start(deck);

			System.out.println("Would you like to hit? y/n");
			hit = reader.next().charAt(0);

			if (hit == 'y') {
				playerHit(deck);
			}
			if(win == true){
				System.out.println("Thank you for playing, care for another round? y/n");
				playAgain = reader.next().charAt(0);
			}else{

				dealer(deck);
				testWin();
				
				if(money <= 0){
					System.out.println("You lose you faggot...");
					System.exit(0);
				}

			System.out.println("Thank you for playing, care for another round? y/n");
			playAgain = reader.next().charAt(0);
			}

			deck = deck();
		} while (playAgain == 'y');
	}

	public static void start(int deck[][]) {
		int playerCard1, playerCard2, dealerCard1, dealerCard2;
		int playerCard1Suit, playerCard2Suit, dealerCard1Suit;
		int w = 0;
		int x = 1;
		int y = 2;
		int z = 3;

		playerCard1 = deck[0][w];
		playerCard2 = deck[0][x];
		dealerCard1 = deck[0][y];
		dealerCard2 = deck[0][z];
		playerCard1Suit = deck[1][w];
		playerCard2Suit = deck[1][x];
		dealerCard1Suit = deck[1][y];

		System.out.print(NL + NL
				+ "The dealer has 2 cards... the one showing is ");
		printHand(dealerCard1, dealerCard1Suit);

		System.out.print("Your first card is ");
		printHand(playerCard1, playerCard1Suit);

		System.out.print("Your second card is ");
		printHand(playerCard2, playerCard2Suit);

		if (playerCard1 > 10) {
			playerCard1 = 10;
		}
		if (playerCard1 == 1) {
			playerCard1 = 11;
		}
		if (playerCard2 > 10) {
			playerCard2 = 10;
		}
		if (playerCard2 == 1) {
			playerCard2 = 11;
		}
		if (dealerCard1 > 10) {
			dealerCard1 = 10;
		}
		if (dealerCard1 == 1) {
			dealerCard1 = 11;
		}
		if (dealerCard2 > 10) {
			dealerCard2 = 10;
		}
		if (dealerCard2 == 1) {
			dealerCard2 = 11;
		}
		playerTotal = playerCard1 + playerCard2;
		dealerTotal = dealerCard1 + dealerCard2;

		System.out.println("Your total is " + playerTotal);

	}

	public static void playerHit(int deck[][]) {
		int playerCard, playerCardSuit;
		int x = 0;
		Scanner reader = new Scanner(System.in);
		do {
			playerCard = deck[0][x];
			playerCardSuit = deck[1][x];

			System.out.print("You were dealt ");
			printHand(playerCard, playerCardSuit);

			if (playerCard > 10) {
				playerCard = 10;
			}
			if (playerCard == 1) {
				playerCard = 11;
			}

			playerTotal = playerCard + playerTotal;

			if (playerTotal > 21 && playerCard == 11) {
				playerTotal = playerTotal - 10;
				System.out
						.println("To prevent you from going over 21 your Ace changes to a 1"
								+ NL + "Your new total is " + playerTotal);
			} else {
				System.out.println("Your total is " + playerTotal);
			}

			if (playerTotal > 21) {
				testWin();
				break;
			}
			if (playerTotal == 21) {
				break;
			}

			System.out.println("Would you like to hit? y/n");
			hit = reader.next().charAt(0);

			x++;
		} while (hit == 'y');

	}

	public static void dealer(int deck[][]) {
		int dealerCard, dealerCardSuit;
		int x = 0;
		if(dealerTotal > 17){
			System.out.println("The dealers total is " + dealerTotal);
		}
		while (dealerTotal <= 17) {
			dealerCard = deck[0][x];
			dealerCardSuit = deck[1][x];

			System.out.print("The dealer gets ");
			printHand(dealerCard, dealerCardSuit);

			if (dealerCard > 10) {
				dealerCard = 10;
			}
			if (dealerCard == 1) {
				dealerCard = 11;
			}

			dealerTotal = dealerCard + dealerTotal;

			if (dealerTotal > 21 && dealerCard == 11) {
				dealerTotal = dealerTotal - 10;
				System.out
						.println("To prevent from going over 21 the dealers Ace changes to a 1"
								+ NL + "His new total is " + dealerTotal);
			} else {
				System.out.println("The dealers total is " + dealerTotal);
			}
			if (dealerTotal > 21 && win == false) {
				testWin();
				break;
			}

			x++;
		}
	}

	public static void printHand(int x, int y) {
		int[][] deck = deck();
		int totalx;
		int totaly;
		boolean ace = false;
		boolean jack = false;
		boolean queen = false;
		boolean king = false;
		boolean hearts = false;
		boolean diamonds = false;
		boolean clubs = false;
		boolean spades = false;
		totalx = x;
		totaly = y;

		if (totalx == 1)
			ace = true;
		if (totalx == 11)
			jack = true;
		if (totalx == 12)
			queen = true;
		if (totalx == 13)
			king = true;

		if (totaly == 1)
			hearts = true;
		if (totaly == 2)
			diamonds = true;
		if (totaly == 3)
			spades = true;
		if (totaly == 4)
			clubs = true;

		if (totalx == 1 || totalx > 10) {

			if (ace == true & hearts == true)
				System.out.println("an Ace of Hearts");
			if (ace == true & diamonds == true)
				System.out.println("an Ace of Diamonds");
			if (ace == true & clubs == true)
				System.out.println("an Ace of Clubs");
			if (ace == true & spades == true)
				System.out.println("an Ace of Spades");
			if (jack == true & hearts == true)
				System.out.println("a Jack of Hearts");
			if (jack == true & diamonds == true)
				System.out.println("a Jack of Diamonds");
			if (jack == true & clubs == true)
				System.out.println("a Jack of Clubs");
			if (jack == true & spades == true)
				System.out.println("a Jack of Spades");
			if (queen == true & hearts == true)
				System.out.println("a Queen of Hearts");
			if (queen == true & diamonds == true)
				System.out.println("a Queen of Diamonds");
			if (queen == true & clubs == true)
				System.out.println("a Queen of Clubs");
			if (queen == true & spades == true)
				System.out.println("a Queen of Spades");
			if (king == true & hearts == true)
				System.out.println("a King of Hearts");
			if (king == true & diamonds == true)
				System.out.println("a King of Diamonds");
			if (king == true & clubs == true)
				System.out.println("a King of Clubs");
			if (king == true & spades == true)
				System.out.println("a King of Spades");
		} else if (totalx > 1 || totalx <= 10) {
			if (hearts == true)
				System.out.println("a " + totalx + " of Hearts");
			if (diamonds == true)
				System.out.println("a " + totalx + " of Diamonds");
			if (clubs == true)
				System.out.println("a " + totalx + " of Clubs");
			if (spades == true)
				System.out.println("a " + totalx + " of Spades");
		}
	}

	public static void testWin() {
		if (dealerTotal == 21 && playerTotal == 21) {
			win = true;
			System.out.println("You both have 21, the dealer wins ties" + NL
					+ "You lose " + bet + " dollars;");
			money = money - (bet * payoutRate);
		}
		if (playerTotal > 21 || dealerTotal >= playerTotal && dealerTotal <= 21
				&& win != true) {
			win = true;
			System.out.println("You lose!" + NL + "You lost " + bet
					+ " Dollars, better luck next time!");
			money = money - (bet * payoutRate);
		}
		if (playerTotal <= 21 && playerTotal < dealerTotal && win != true) {
			win = true;
			System.out.println("You win!");
			System.out.println("You gain " + bet + " dollars!");
			money = money + (bet * payoutRate);
		}
	}

	public static int[][] deck() {
		int[][] cards = cards();
		int i;
		int j;
		int x;
		int[] deck = new int[COLS];
		int[][] shuffledCards = new int[ROWS][COLS];

		for (i = 0; i < COLS; i++) {
			deck[i] = (52 * cards[0][i]) + cards[1][i];
		}

		shuffleArray(deck);

		for (i = 0; i < ROWS; i++) {
			if (i == 0) {
				for (j = 0; j < COLS; j++) {
					x = deck[j];
					while (x > 4) {
						x = x - 52;

					}
					shuffledCards[1][j] = x;

				}

			} else {
				for (j = 0; j < COLS; j++) {
					shuffledCards[0][j] = (deck[j] - shuffledCards[1][j]) / 52;
				}

			}
		}
		return shuffledCards;

	}

	public static int[][] cards() {
		int[][] cards = new int[ROWS][COLS];
		int c = 0;
		int i;
		int j;
		for (i = 0; i < ROWS; i++) {

			if (i == 0) {

				for (j = 0; j < COLS; j++) {
					c = 0;
					if (j <= 3)
						cards[i][j] = 1;
					if (j > 3 && j <= 7)
						cards[i][j] = 2;
					if (j > 7 && j <= 11)
						cards[i][j] = 3;
					if (j > 11 && j <= 15)
						cards[i][j] = 4;
					if (j > 15 && j <= 19)
						cards[i][j] = 5;
					if (j > 19 && j <= 23)
						cards[i][j] = 6;
					if (j > 23 && j <= 27)
						cards[i][j] = 7;
					if (j > 27 && j <= 31)
						cards[i][j] = 8;
					if (j > 31 && j <= 35)
						cards[i][j] = 9;
					if (j > 35 && j <= 39)
						cards[i][j] = 10;
					if (j > 39 && j <= 43)
						cards[i][j] = 11;
					if (j > 43 && j <= 47)
						cards[i][j] = 12;
					if (j > 47 && j <= 51)
						cards[i][j] = 13;
					c++;

				}
			} else
				for (j = 0; j < COLS; j++) {
					if (c == 5)
						c = 1;
					if (c == 1)
						cards[i][j] = 1; // Hearts
					if (c == 2)
						cards[i][j] = 2; // Diamonds
					if (c == 3)
						cards[i][j] = 3; // clubs
					if (c == 4)
						cards[i][j] = 4; // Spades
					c++;
				}
		}
		return cards;
	}

	static void shuffleArray(int[] deckCards) {

		Random rnd = new Random();
		for (int i = deckCards.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Swap
			int a = deckCards[index];
			deckCards[index] = deckCards[i];
			deckCards[i] = a;
		}
	}

}