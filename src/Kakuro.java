import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Kakuro extends Frame implements ActionListener {
	Button gumb[][] = new Button[8][6];
	Button restart, preveri, exit, resi;
	Panel plosca = new Panel();
	Panel plosca1 = new Panel();
	TextField tekst;
	int stevec = 1;
	int stevilka;
	Random b = new Random();

	public Kakuro() {
		//naredi celotno okno
		setTitle("Kakuro");
		setLayout(new BorderLayout());
		add(plosca, BorderLayout.CENTER);
		plosca.setLayout(new GridLayout(8, 6));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				gumb[i][j] = new Button("");
				plosca.add(gumb[i][j]);
				if (i == 0 | j == 0) {
					gumb[i][j].setBackground(Color.gray);
				} else {
					gumb[i][j].addActionListener(this);

				}
			}
		}
		zacetek();// tvori igor

		while (!(previri())) {
			//preveri da se ista �tevilka ne ponovi v vrstici in stolpcu
			vrstica();
			stolpec();
		}

		vsote();

		setSize(500, 500);
		poslusalec bine = new poslusalec();
		plosca1.setLayout(new GridLayout(1, 2));
		plosca1.add(tekst = new TextField("Poskus 1"));
		plosca1.add(restart = new Button("Poskusi znova"));
		restart.addActionListener(bine);
		plosca1.add(preveri = new Button("Preveri re�itev"));
		preveri.addActionListener(bine);
		plosca1.add(resi = new Button("HAX"));
		resi.addActionListener(bine);
		plosca1.add(exit = new Button("\"Dost mam!\""));
		exit.addActionListener(bine);
		add(plosca, BorderLayout.CENTER);
		add(plosca1, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void zacetek() {
		//generira za�etno polje naklju�nih �tevilk
		int stvor;
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 6; j++) {
				stvor = (int) (b.nextDouble() * 9 + 1);
				gumb[i][j].setName("" + stvor);
			}
		}
	}

	public void vrstica() {
		//poskrbi da se v vrstici ne ponovita 2 isti �tevilki
		int stvor1;
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 5; j++) {
				for (int x = j + 1; x < 6; x++) {
					if (gumb[i][j].getName().equals(gumb[i][x].getName())) {
						stvor1 = (int) (b.nextDouble() * 9 + 1);
						gumb[i][x].setName("" + stvor1);
						j = 1;
					}
				}
			}
		}
	}

	public void stolpec() {
		//poskrbi da se v stolpcu ne ponovita 2 isti �tevilki
		int stvor2;
		for (int j = 1; j < 6; j++) {
			for (int i = 1; i < 7; i++) {
				for (int x = i + 1; x < 8; x++) {
					if (gumb[i][j].getName().equals(gumb[x][j].getName())) {
						stvor2 = (int) (b.nextDouble() * 9 + 1);
						gumb[x][j].setName("" + stvor2);
						i = 1;
					}
				}
			}
		}
	}

	public boolean previri() {
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 5; j++) {
				for (int x = j + 1; x < 6; x++) {
					if (gumb[i][j].getName().equals(gumb[i][x].getName())) {
						return false;
					}
				}
			}
		}

		for (int j = 1; j < 6; j++) {
			for (int i = 1; i < 7; i++) {
				for (int x = i + 1; x < 8; x++) {
					if (gumb[i][j].getName().equals(gumb[x][j].getName())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void vsote() {
		//napolni siva polja z vsotami vrstic/stolpcov
		int vrsta = 0;
		int stolp = 0;
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 6; j++) {
				vrsta = vrsta + Integer.parseInt(gumb[i][j].getName());
			}
			gumb[i][0].setLabel("" + vrsta);
			vrsta = 0;
		}
		for (int j = 1; j < 6; j++) {
			for (int i = 1; i < 8; i++) {
				stolp = stolp + Integer.parseInt(gumb[i][j].getName());
			}
			gumb[0][j].setLabel("" + stolp);
			stolp = 0;
		}
	}

	public boolean resitev() {
		//preveri da kak�ne �tevilke nismo vpisali dvakrat
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 5; j++) {
				for (int x = j + 1; x < 6; x++) {
					if (gumb[i][j].getLabel().equals(gumb[i][x].getLabel())) {
						return false;
					}
				}
			}
		}

		for (int j = 1; j < 6; j++) {
			for (int i = 1; i < 7; i++) {
				for (int x = i + 1; x < 8; x++) {
					if (gumb[i][j].getLabel().equals(gumb[x][j].getLabel())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean previri1() {
		//preverja pravilnost na�e re�itve
		int vrsta = 0;
		int stolp = 0;
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 6; j++) {
				vrsta = vrsta + Integer.parseInt(gumb[i][j].getLabel());
			}
			if (vrsta != Integer.parseInt(gumb[i][0].getLabel())) {
				return false;
			}
			vrsta = 0;
		}
		
		for (int j = 1; j < 6; j++) {
			for (int i = 1; i < 8; i++) {
				stolp = stolp + Integer.parseInt(gumb[i][j].getLabel());
			}
			if (stolp != Integer.parseInt(gumb[0][j].getLabel())) {
				return false;
			}
			stolp = 0;
		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		//skrbi da gumbi delajo to kar ho�em
		String niz;
		niz = ((Button) e.getSource()).getLabel();
		if (niz.equals("")) {
			niz = "0";
		}
		stevec = Integer.parseInt(niz);
		stevec++;
		if (stevec > 9){
			stevec = 1;
			}
		((Button) e.getSource()).setLabel("" + stevec);
	}

	class poslusalec implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//spodnjim gumbom dodeli funkcije
			if (e.getSource() == resi) {
				for (int i = 1; i < 8; i++) {
					for (int j = 1; j < 6; j++) {
						gumb[i][j].setLabel(gumb[i][j].getName());
					}
				}
			}
			
			if (e.getSource() == exit) {
				System.exit(0);
			}

			if (e.getSource() == restart) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 6; j++) {
						if (!(i == 0 | j == 0))
							gumb[i][j].setLabel("");
					}
				}
				tekst.setText("V n-to gre rado.");
			}


			if (e.getSource() == preveri) {
				if (resitev() && previri1()) {
					System.out.println("Re�itev je pravilna. Dovol �asa si zgubil s tem zdaj lahko dela� kaj bol�ega.");
				} else {
					System.out.println("Poskusi znova.");
				}

			}

		} 

	}
}
