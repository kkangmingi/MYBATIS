package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.vo.Student;

public class StudentDao {
	
	public int insertStudent(SqlSession session) {
//		PreparedStatement 안만들어두됨
		//sql문을 실행할 때는 session이 제공하는 메소드를 호출하면 됨.
		//selectOne(), selectList(), insert(), update(), delete()
		//statement인수는 "mapper namespace값.sql id값"
		int result=session.insert("student.insertStudent");
		return result;
	}
	
	public int insertStudentByName(SqlSession session, String name) {
		int result=session.insert("student.insertStudentByName",name);
		return result;
	}
	
	public int insertStudentAll(SqlSession session, Student s) {
		return session.insert("student.insertStudentAll",s);
	}
	
	public int updateStudent(SqlSession session, Student s) {
		return session.update("student.updateStudent",s);
	}
	
	public int deleteStudent(SqlSession session, int no) {
		return session.delete("student.deleteStudent",no);
	}
	
	public int selectStudentCount(SqlSession session) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		int result=session.selectOne("student.selectStudentCount");
		return result;
	}
	public Student selectStudent(SqlSession session, int no) {
		return session.selectOne("student.selectStudent",no);
		
	}
	public List<Student> selectStudentAll(SqlSession session){
		return session.selectList("student.selectStudentAll");
	}
	public List<Student> selectStudentByName(SqlSession session, String name) {
		return session.selectList("student.selectStudentByName",name);
	}
	public Map selectStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectStudentMap",no);
	}
	public List<Map> selectStudentListMap(SqlSession session) {
		return session.selectList("student.selectStudentListMap");
	}
	public List<Student> selectStudentPage(SqlSession session, int cPage, int numPerpage){
		//페이징처리할때는 마이바티스가 제공하는 페이징처리 클래스를 이용
		//RowBounds클래스 이용
		//SelectList호출시 세번째 매개변수에 RowBounds클래스를 생성해서 전달해주면 됨.
		//매개변수있는 생성자를 이용
		//1매개변수 : offset -> 시작 row번호 (cPage-1)*numPerpage
		//2매개변수 : 범위 -> numPerpage
		//new RowBounds((cPage-1)*numPerpage,numPerpage);
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		//session.selectList(sql문,조건,페이징처리); 매개변수 순서가 중요함 !!
		return session.selectList("student.selectStudentPage", null, rb);
	}
}
