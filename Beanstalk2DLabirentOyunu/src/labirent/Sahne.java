package labirent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import labirent.nesneler.Gubre;
import labirent.nesneler.Kurek;
import labirent.nesneler.Su;
import labirent.nesneler.Tohum;

public class Sahne extends JPanel {
	
	private final int OFFSET = 100;
	private final int BOSLUK = 20;
	
	private final int SOL_TEMAS = 1;
	private final int SAG_TEMAS = 2;
	private final int UST_KONTROL = 3;
	private final int ALT_KONTROL = 4;

	private ArrayList<Duvar> duvarlar;
	private ArrayList<Kutu> kutular;
	private ArrayList<Hedef> hedefler;
	
	private Oyuncu oyuncu;

	private Skor skor = new Skor();
	private int genislik = 0;
	private int yukseklik = 0;
	private int tamamlananKutular = 1;

	private boolean isCompleted = false;
	
	private Image arkaPlan;
	private int arkaPlanWidth;
	private int arkaPlanHeight;

	

	private String level =
			"       \n" +
			"       \n" +
			"   ####\n" + 
			"  ##  #\n" + 
			"  #   #\n" + 
			"###  4##\n" + 
			"#  2 3 #\n" + 
			"# # ## #   #####\n" +  
			"# # ## #####   #\n" + 
			"#  1         . #\n" + 
			"### ### #@##   #\n" + 
			"  #     ########\n" + 
			"  #######\n";


				
	public Sahne() {

		initBoard();
	}

	private void initBoard() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}

	public int getSahneGenisligi() {
		return this.genislik;
	}

	public int getSahneYuksekligi() {
		return this.yukseklik;
	}

	private void initWorld() {

		duvarlar = new ArrayList<>();
		kutular = new ArrayList<>();
		hedefler = new ArrayList<>();
			

		int x = OFFSET;
		int y = OFFSET;

		Duvar duvar;
		Hedef hdf;
		Kurek krk;
		Tohum thm;
		Gubre gbr;
		Su su;
		
		
		
		
		for (int i = 0; i < level.length(); i++) {

			char nesne = level.charAt(i);

			switch (nesne) {

			case '\n':
				y += BOSLUK;

				if (this.genislik < x) {
					this.genislik = x;
				}

				x = OFFSET;
				break;

			case '#':
				duvar = new Duvar(x, y);
				duvarlar.add(duvar);
				x += BOSLUK;
				break;

			case '1':
				krk = new Kurek(x, y);
				kutular.add(krk);
				x += BOSLUK;
				break;

			case '2':
				thm = new Tohum(x,y);
				kutular.add(thm);
				x += BOSLUK;
				break;

			case '3':
				gbr = new Gubre(x, y);
				kutular.add(gbr);
				x += BOSLUK;
				break;
				
			case '4':
				su = new Su(x, y);
				kutular.add(su);
				x += BOSLUK;
				break;

				
			case '.':
				hdf = new Hedef(x, y);
				hedefler.add(hdf);
				x += BOSLUK;
				break;

			case '@':
				oyuncu = new Oyuncu(x, y);
				x += BOSLUK;
				break;
	
			case ' ':
				x += BOSLUK;
				break;

			default:
				break;
			}

			yukseklik = y;
		} 
	}

	private void buildWorld(Graphics g) {
	
		
		try {
		    arkaPlan = ImageIO.read(new File("src/resources/arkaplan.png"));
		    arkaPlanWidth = arkaPlan.getWidth(null);
		    arkaPlanHeight = arkaPlan.getHeight(null);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
		ArrayList<Hucre> labirentAlani = new ArrayList<>();

		labirentAlani.addAll(duvarlar);
		labirentAlani.addAll(hedefler);
		labirentAlani.addAll(kutular);
		labirentAlani.add(oyuncu);

		for (int i = 0; i < labirentAlani.size(); i++) {

			Hucre nesne = labirentAlani.get(i);

			if (nesne instanceof Oyuncu || nesne instanceof Kutu) {

				g.drawImage(nesne.getImage(), nesne.x() + 2, nesne.y() + 2, this);
				
				
				
			} else {

				g.drawImage(nesne.getImage(), nesne.x(), nesne.y(), this);
			}

			if (isCompleted) {

				g.setColor(new Color(0, 0, 0));
				g.drawString("Oyun Tamamlandı", 200, 55);
			}
			
			g.setColor(new Color(0, 0, 0));
			g.drawString("Toplam Adım", 65, 20);
			g.drawString(Integer.toString(skor.getSkor()), 100, 40);
			
			g.drawString("Level", 10, 20);
			g.drawString("1", 25, 40);
			
	
			g.drawString("Yandaki sırayla kutuları hedefe bırakınız: Kürek, Tohum, Su, Gübre", 20, 450);

			
		}
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.drawImage(arkaPlan, 0, 0, arkaPlanWidth, arkaPlanHeight, null);

		buildWorld(g);
	}

	private class TAdapter extends KeyAdapter {

		
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			
			
			
			
			if (isCompleted) {
				return;
			}

			int key = e.getKeyCode();
			

			switch (key) {

			case KeyEvent.VK_LEFT:

				if (duvarTemasKontrolu(oyuncu, SOL_TEMAS)) {
					return;
				}

				if (kutuTemasKontrolu(SOL_TEMAS)) {
					return;
				}
	
				oyuncu.move(-BOSLUK, 0);
				skor.toplamSkor();
				
				
				break;

			case KeyEvent.VK_RIGHT:

				if (duvarTemasKontrolu(oyuncu, SAG_TEMAS)) {
					return;
				}

				if (kutuTemasKontrolu(SAG_TEMAS)) {
					return;
				}
		
				oyuncu.move(BOSLUK, 0);
				skor.toplamSkor();

				break;

			case KeyEvent.VK_UP:

				if (duvarTemasKontrolu(oyuncu, UST_KONTROL)) {
					return;
				}

				if (kutuTemasKontrolu(UST_KONTROL)) {
					return;
				}
						
				oyuncu.move(0, -BOSLUK);
				skor.toplamSkor();

				break;

			case KeyEvent.VK_DOWN:

				if (duvarTemasKontrolu(oyuncu, ALT_KONTROL)) {
					return;
				}

				if (kutuTemasKontrolu(ALT_KONTROL)) {
					return;
				}	
				
				oyuncu.move(0, BOSLUK);
				skor.toplamSkor();

				break;

			case KeyEvent.VK_R:

				restartLevel();

				break;

			default:
				break;
			}

			repaint();
		}
	}

	private boolean duvarTemasKontrolu(Hucre adam, int type) {

		switch (type) {

		case SOL_TEMAS:

			for (int i = 0; i < duvarlar.size(); i++) {

				Duvar duvar = duvarlar.get(i);

				if (adam.solTemasVarmi(duvar)) {

					return true;
				}
			}

			return false;

		case SAG_TEMAS:

			for (int i = 0; i < duvarlar.size(); i++) {

				Duvar duvar = duvarlar.get(i);

				if (adam.sagTemasVarmi(duvar)) {
					return true;
				}
			}

			return false;

		case UST_KONTROL:

			for (int i = 0; i < duvarlar.size(); i++) {

				Duvar duvar = duvarlar.get(i);

				if (adam.ustTemasVarmi(duvar)) {

					return true;
				}
			}

			return false;

		case ALT_KONTROL:

			for (int i = 0; i < duvarlar.size(); i++) {

				Duvar duvar = duvarlar.get(i);

				if (adam.altTemasVarmi(duvar)) {

					return true;
				}
			}

			return false;

		default:
			break;
		}

		return false;
	}

	private boolean kutuTemasKontrolu(int type) {

		switch (type) {

		case SOL_TEMAS:

			for (int i = 0; i < kutular.size(); i++) {

				Kutu kutu = kutular.get(i);

				if (oyuncu.solTemasVarmi(kutu)) {

					for (int j = 0; j < kutular.size(); j++) {

						Kutu nesne = kutular.get(j);

						if (!kutu.equals(nesne)) {

							if (kutu.solTemasVarmi(nesne)) {
								return true;
							}
						}

						if (duvarTemasKontrolu(kutu, SOL_TEMAS)) {
							return true;
						}
					}
					
					kutu.move(-BOSLUK, 0);
					
					System.out.println("Kutunun x: " + kutu.x() + "\n Kutunun y: " + kutu.y());
					isCompleted();
				}
			}

			return false;

		case SAG_TEMAS:

			for (int i = 0; i < kutular.size(); i++) {

				Kutu kutu = kutular.get(i);

				if (oyuncu.sagTemasVarmi(kutu)) {

					for (int j = 0; j < kutular.size(); j++) {

						Kutu nesne = kutular.get(j);

						if (!kutu.equals(nesne)) {

							if (kutu.sagTemasVarmi(nesne)) {
								return true;
							}
						}

						if (duvarTemasKontrolu(kutu, SAG_TEMAS)) {
							return true;
						}
					}
					kutu.move(BOSLUK, 0);
					System.out.println("Kutunun x: " + kutu.x() + "\n Kutunun y: " + kutu.y());
					isCompleted();
				}
			}
			return false;

		case UST_KONTROL:

			for (int i = 0; i < kutular.size(); i++) {

				Kutu kutu = kutular.get(i);

				if (oyuncu.ustTemasVarmi(kutu)) {

					for (int j = 0; j < kutular.size(); j++) {

						Kutu nesne = kutular.get(j);

						if (!kutu.equals(nesne)) {

							if (kutu.ustTemasVarmi(nesne)) {
								return true;
							}
						}

						if (duvarTemasKontrolu(kutu, UST_KONTROL)) {
							return true;
						}
					}

					kutu.move(0, -BOSLUK);
					System.out.println("Kutunun x: " + kutu.x() + "\n Kutunun y: " + kutu.y());
					isCompleted();
				}
			}

			return false;

		case ALT_KONTROL:

			for (int i = 0; i < kutular.size(); i++) {

				Kutu kutu = kutular.get(i);

				if (oyuncu.altTemasVarmi(kutu)) {

					for (int j = 0; j < kutular.size(); j++) {

						Kutu nesne = kutular.get(j);

						if (!kutu.equals(nesne)) {

							if (kutu.altTemasVarmi(nesne)) {
								return true;
							}
						}

						if (duvarTemasKontrolu(kutu, ALT_KONTROL)) {

							return true;
						}
					}

					
					kutu.move(0, BOSLUK);
					System.out.println("Kutunun x: " + kutu.x() + "\n Kutunun y: " + kutu.y());
					
					isCompleted();
				}
			}

			break;

		default:
			break;
		}

		return false;
	}

	
	public void isCompleted() {
	
		final int toplamKutu = 4;
		int kutuSayisi = kutular.size();
	
	
		System.out.println();

		for (int i = 0; i < kutuSayisi; i++) {

			Kutu kutu = kutular.get(i);
			
			Hedef hedefAlan = hedefler.get(0); 
			int siralama =kutular.get(i).toInt();
			
			if (kutu.x() == hedefAlan.x() && kutu.y() == hedefAlan.y() && siralama == tamamlananKutular) {
			
				System.out.println("Sıralama: "+ siralama);
				
				System.out.println("mevcut kutu no:" + kutular.get(i));
			
				tamamlananKutular ++;
				kutular.remove(i);
				
				System.out.println(" Sildikten sonra kutular kısmının değeri: " + kutular.size());

				kutuSayisi--; 
				
				if (kutuSayisi == 0) {
					System.out.println("Kalan Kutu Sayısı: "+ kutuSayisi);
					break;
				}
				System.out.println("Hedef Alan" + hedefAlan);
			}
		}
		
		if (tamamlananKutular > toplamKutu) {
			System.out.println(tamamlananKutular);
			isCompleted = true;
			System.out.println(kutuSayisi);
			repaint();
		}
	}
	

	public void restartLevel() {

		hedefler.clear();
		kutular.clear();
		duvarlar.clear();

		initWorld();

		if (isCompleted) {
			isCompleted = false;
		}
	}
}
