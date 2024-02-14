package chat8;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

//CRUD 중 입력을 위한 클래스
public class SQLinsert extends MyConnection{
	/*
	생성자에서 부모클래스의 생성자를 호출하여 DB에 연결한다.
	연결한 계정의 아이디와 비번을 매개변수로 전달하고 있다. 
	*/
	public SQLinsert(String user, String pass) {
		super(user, pass);
	}
	
	//멤버변수
	String query;//쿼리문 저장
	int result;//insert후 결과를 저장
	
	@Override
	public void dbExecute() {
		MultiServer multiServer = new MultiServer();
		String name = "";
		String s = "";
		PrintWriter out = null;
		try {
			query = " INSERT INTO chat_talking "
					+ " VALUES (?, ?, ?, sysdate) ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, multiServer.clientMap.toString());
			psmt.setString(2, multiServer.toString());
//			psmt.setString(2, String.join(",", multiServer.clientMap.keySet()));
			//쿼리문 실행 및 결과 반환
			int result = psmt.executeUpdate();
			//insert 쿼리문이므로 성공시 1, 실패시 0이 반환된다.
			System.out.println(result + "행 입력됨");
			
			
		} catch (SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new SQLinsert("study", "1234").dbExecute();
	}

}



