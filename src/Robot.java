import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;

public class Robot {
	private String IP;
	private String robotAdi;
	private String ilkKonum;
	private Thread takipci;
	private Socket soket;
	private DataOutputStream dataOutput;
	public DataInputStream dataInput;
	public PrintStream pStream;
	public DataInputStream inputLine;
	private String robotSinifi;
	private String sonKonum;

	public String x;
	public String y;
	public String z;

	public void Olustur() {
		try {
			soket = new Socket(getIP(), 3000);
			dataOutput = new DataOutputStream(soket.getOutputStream());
			pStream = new PrintStream(soket.getOutputStream());
			dataInput = new DataInputStream(soket.getInputStream());
			inputLine = new DataInputStream(new BufferedInputStream(System.in));

			pStream.print("INIT {ClassName " + robotSinifi + "} {Name "
					+ getRobotAdi() + "} {Location " + ilkKonum + "}\r\n");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				konumTakip();

			}
		});
		t.start();
	}

	public void konumTakip() {

		KonumGuncelle a = new KonumGuncelle();

		a.r = this;
		
//		a.x = x;
//		a.y = y;
//		a.z = z;

		takipci = new Thread(a);
		takipci.start();

		setSonKonum(a.getKonum());
		System.out.println("said2--> "+ getSonKonum());
	}

	public synchronized void hareketEt(int durum) {

		switch (durum) {
		case 1: // ileri
			pStream.print("DRIVE {Left 1.0} {Right 1.0}\r\n");
			break;
		case 2: // saga don
			pStream.print("DRIVE {Left 1.0} {Right -1.0}\r\n");
			break;
		case 3: // sola dön
			pStream.print("DRIVE {Left -1.0} {Right 1.0}\r\n");
			break;
		case 4: // geri gel
			pStream.print("DRIVE {Left -1.0} {Right -1.0}\r\n");
			break;
		case 5: // dur
			pStream.print("DRIVE {Left 0.0} {Right 0.0}\r\n");
			break;
		default:
			break;
		}
	}

	public void ileriHareket() {
		pStream.print("DRIVE {Left 1.0} {Right 1.0}\r\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pStream.print("DRIVE {Left 0.0} {Right 0.0}\r\n");

	}

	public Robot(String IP, String robotAdi, String ilkKonum, String robotSinifi) {
		this.IP = IP;
		this.robotAdi = robotAdi;
		this.ilkKonum = ilkKonum;
		this.robotSinifi = robotSinifi;

		System.out.println("said");

		Olustur();
	}

	public String getIP() {
		return IP;
	}

	public String getRobotAdi() {
		return robotAdi;
	}

	public String getIlkKonum() {
		return ilkKonum;
	}

	public Thread getSensor() {
		return takipci;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return robotAdi;
	}

	public String getSonKonum() {
		return sonKonum;
	}

	public void setSonKonum(String sonKonum) {
		this.sonKonum = sonKonum;
	}	

}
