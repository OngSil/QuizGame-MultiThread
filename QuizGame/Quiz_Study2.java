import java.io.*;
import java.util.*;

class  Quiz_Study2 {
	String fname = "Q.txt";
	BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));


	// 1. �� �̸� �Է¹ޱ�
	void readTeam(){
		try{
			System.out.println("�� �̸� �Է��ϼ���");
			String teamName = br1.readLine();
			System.out.println("�� �̸�: "+teamName);
		}catch(IOException ie){}
	}
	
	// 2. ������ ���� txt���� �����ͼ� �а�, set�� �ִ´�(�ߺ�����)
	TreeSet<String> tSet = new TreeSet<String>();
	ArrayList<String> list = new ArrayList<String>(); //�ߺ������� ���� ���� ����Ʈ
	
	BufferedReader br2;
	FileReader fr;

	void readFile(){
		String line = "";
		try{
			fr = new FileReader(fname);
			br2 = new BufferedReader(fr);
			while( (line=br2.readLine()) != null ){
				if( line != null ){ //���� ��������
					line = line.trim();
				}
				if( line.length() != 0 ){ //������ �ִٸ� treeset���ֱ�
					tSet.add(line); //�ߺ�����
				}
			}
			for(String item : tSet) list.add(item); //�ߺ������ʴ� ���� list�� �ֱ�
		}catch(FileNotFoundException fe){
		}catch(IOException ie){}
	}

	// 3. ���� ���� �̱⸦ ���� ������ ���Ѵ�(1�δ� 10���� ������)
	// ������ ���� �ߺ� ������ ���� TreeSet ���
	// 4. ���� ������� üũ : O, X ���� �Է¹ް�(O�� �� ���� ����)

	TreeSet<Integer> tSetInt = new TreeSet<Integer>();
	int n;
	int score=0;

	void random(){
		Random r = new Random();
		int size = list.size();
		for(int i=0; i<10; i++){ //10�����ϱ� 10�� �������λ̴´�
			n = r.nextInt(size); //����Ʈ�� �ִ� ���� �� �߿��� ������ �̴´�
			tSetInt.add(n); //�ߺ��� ���Ÿ� ���� TreeSet�� ������ ����
			if( tSetInt.size() < 10 ){
				i--;
			}
		}
		for(int j : tSetInt){ //�������� �ش��ϴ� ���� ����
			System.out.println("����: "+list.get(j));

			BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
			try{
				String line = "";
				line = br3.readLine();
				if(line != null) line = line.trim(); //���� ���� ����
				if(line.equalsIgnoreCase("O")){
					score++; //o �Է��ϸ� ���� ����
					System.out.println("1�� ȹ��!");
					list.remove(j); //���� ���� ����Ʈ���� ����
					write(); //����Ʈ �ٽ� ���� ���Ͽ� �����
				} else { //O�� ������ ��� ���ڴ� ���� ȹ��X
					System.out.println("����ȹ�� ���ФФ�");
				}
			}catch(IOException ie){}

			if(j == tSetInt.size()){
				System.exit(0); //10���� ��� ���� �Ϸ�Ǹ� ���α׷� ����
			}
		}
	}
	
	// 5. ����Ʈ �ٽ� ���� ���Ͽ� ������
	FileWriter fw;
	PrintWriter pw;
	void write(){
		try{
			fw = new FileWriter(fname, false); //�ڿ� ������ true/false�� autoflush
			pw = new PrintWriter(fw, true);

			overwrite();
			for(String item : list){
				fw.write(item); //���Ͽ� ����Ʈ ������
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){}
	}

	void overwrite(){
		for(String item : list){
			pw.println(item);
			pw.flush();
		}
	}

}
class Quiz_Study2_User2 {
	public static void main(String[] args) {
		Quiz_Study2 qs2 = new Quiz_Study2();

		// 6. 10�ʵ��� ���� ���α׷��� ���ư��� �����.(Multi Thread)
		Task t = new Task();
		Thread thread = new Thread(t);
		thread.start();

		qs2.readTeam();
		qs2.readFile();
		qs2.random();

	}
}
