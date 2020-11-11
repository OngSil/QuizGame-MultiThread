public class Task implements Runnable {
	//
	public void run(){
		int time = 10;
		while(true){
		System.out.println(time-- + "√ ");
		try{
			Thread.sleep(1000);
		}catch(Exception e){
		}
		if(time == 0) System.exit(-1);
		}
	}
}
