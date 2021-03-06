import java.io.*;
import java.util.*;

class  Quiz_Study2 {
	String fname = "Q.txt";
	BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));


	// 1. 팀 이름 입력받기
	void readTeam(){
		try{
			System.out.println("팀 이름 입력하세용");
			String teamName = br1.readLine();
			System.out.println("팀 이름: "+teamName);
		}catch(IOException ie){}
	}
	
	// 2. 문제가 적힌 txt파일 가져와서 읽고, set에 넣는다(중복방지)
	TreeSet<String> tSet = new TreeSet<String>();
	ArrayList<String> list = new ArrayList<String>(); //중복방지한 문제 넣을 리스트
	
	BufferedReader br2;
	FileReader fr;

	void readFile(){
		String line = "";
		try{
			fr = new FileReader(fname);
			br2 = new BufferedReader(fr);
			while( (line=br2.readLine()) != null ){
				if( line != null ){ //문제 띄어쓰기제거
					line = line.trim();
				}
				if( line.length() != 0 ){ //문제가 있다면 treeset에넣기
					tSet.add(line); //중복ㄴㄴ
				}
			}
			for(String item : tSet) list.add(item); //중복되지않는 문제 list에 넣기
		}catch(FileNotFoundException fe){
		}catch(IOException ie){}
	}

	// 3. 문제 랜덤 뽑기를 위해 랜덤수 구한다(1인당 10문제 낼거임)
	// 랜덤수 역시 중복 방지를 위해 TreeSet 사용
	// 4. 문제 맞췄는지 체크 : O, X 값을 입력받고(O일 때 점수 증가)

	TreeSet<Integer> tSetInt = new TreeSet<Integer>();
	int n;
	int score=0;

	void random(){
		Random r = new Random();
		int size = list.size();
		for(int i=0; i<10; i++){ //10문제니까 10번 랜덤으로뽑는다
			n = r.nextInt(size); //리스트에 있는 문제 수 중에서 랜덤수 뽑는다
			tSetInt.add(n); //중복수 제거를 위해 TreeSet에 랜덤수 넣음
			if( tSetInt.size() < 10 ){
				i--;
			}
		}
		for(int j : tSetInt){ //랜덤수에 해당하는 문제 출제
			System.out.println("문제: "+list.get(j));

			BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
			try{
				String line = "";
				line = br3.readLine();
				if(line != null) line = line.trim(); //문제 띄어쓰기 제거
				if(line.equalsIgnoreCase("O")){
					score++; //o 입력하면 점수 증가
					System.out.println("1점 획득!");
					list.remove(j); //맞춘 문제 리스트에서 제거
					write(); //리스트 다시 원본 파일에 덮어쓰기
				} else { //O를 제외한 모든 문자는 점수 획득X
					System.out.println("점수획득 실패ㅠㅠ");
				}
			}catch(IOException ie){}

			if(j == tSetInt.size()){
				System.exit(0); //10문제 모두 출제 완료되면 프로그램 종료
			}
		}
	}
	
	// 5. 리스트 다시 원본 파일에 덮어씌우기
	FileWriter fw;
	PrintWriter pw;
	void write(){
		try{
			fw = new FileWriter(fname, false); //뒤에 나오는 true/false는 autoflush
			pw = new PrintWriter(fw, true);

			overwrite();
			for(String item : list){
				fw.write(item); //파일에 리스트 덮어씌운다
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

		// 6. 10초동안 퀴즈 프로그램이 돌아가게 만든다.(Multi Thread)
		Task t = new Task();
		Thread thread = new Thread(t);
		thread.start();

		qs2.readTeam();
		qs2.readFile();
		qs2.random();

	}
}

