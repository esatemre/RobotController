

import java.io.IOException;




public class KonumGuncelle implements Runnable {


	public Robot r;
	
	public String sonKonum;
	public String x;
	public String y;
	public String z;

	public synchronized String getKonum() {
		return sonKonum;

	}
	
	Object kilit;
	CharSequence csl = "Location";
	String[] dizi;
	String[] ydizi;

	@Override
	public synchronized void run() {

		while (true) {
			try {
				r.setSonKonum(getKonum());
				Thread.sleep(1000);
		
				sonKonum = r.dataInput.readUTF();

				synchronized (sonKonum) {				
					dizi = sonKonum.split("[{]");
					String[] ydizi;
					for (int i = 0; i < dizi.length; i++) {
						if (dizi[i].contains("Location")) {
							ydizi = dizi[i].replace("Location", "")
									.replace('}', '\0').split("[,]");
							if (ydizi.length == 3) {
//								for (int j = 0; j < 3; j++)
//									System.out.println(ydizi[j].trim());

								r.x = ydizi[0].trim();
								r.y = ydizi[1].trim();
								r.z = ydizi[2].trim();
							}
						}
					}

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
