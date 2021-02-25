package kr.or.mit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PMDDB {
	static Connection conn = null;
	static PreparedStatement pstmt = null;

	PMDDB() {
		try {
			// 1. 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. DB 접속
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/bigdata", "big", "1234");
		} catch (Exception e) {
			System.out.println("드라이버 오류");
			e.getStackTrace();
		}
	}

	// 잔액 확인
	int printmoney() throws Exception {
		// 3. 쿼리작성
		pstmt = conn.prepareStatement("SELECT SUM(money) from pocketmoney");
		ResultSet rs = pstmt.executeQuery();
		rs.next(); // 첫번째 칼럼 가르키기
		int sum = rs.getInt(1); // 첫번째 컬럼의 데이터 읽기
		rs.close();
		return sum;
	}

	// 상세 내역
	ArrayList<PMDVO> detail() throws Exception {
		ArrayList<PMDVO> list = new ArrayList<>();
		pstmt = conn.prepareStatement("SELECT item, money from pocketmoney");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) { // 첫번째 행 가르키기
			PMDVO vo = new PMDVO();
			vo.setItem(rs.getString(1)); // 첫번째 컬럼의 데이터 읽기
			vo.setMoney(rs.getInt(2));
			list.add(vo);
		}
		return list;
	}

	// 지출
	void spend(PMDVO vo) throws Exception {
		// 3. 쿼리 작성
		pstmt = conn.prepareStatement("INSERT INTO pocketmoney(item,money) VALUES(?,?)");
		pstmt.setString(1, vo.getItem());
		pstmt.setInt(2, -vo.getMoney());
		// 4. 쿼리 실행
		pstmt.executeUpdate();
	}

	// 수입
	void earn(PMDVO vo) throws Exception {
		pstmt = conn.prepareStatement("INSERT INTO pocketmoney(item,money) VALUES(?,?)");
		pstmt.setString(1, vo.getItem());
		pstmt.setInt(2, vo.getMoney());
		// 4. 쿼리 실행
		pstmt.executeUpdate();
	}

	void close() throws Exception {
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

}
