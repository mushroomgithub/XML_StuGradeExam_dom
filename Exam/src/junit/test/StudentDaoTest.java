package junit.test;

import org.junit.Test;

import cn.lnu.dao.StudentDao;
import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;

//���StudentDaoģ��Ĳ����࣬��Ҫ�ǲ���StudentDao�����������
public class StudentDaoTest {
	@Test
	public void testAdd(){
		StudentDao dao=new StudentDao();
		Student s=new Student();
		s.setExamid("3624");
		s.setIdcard("3231");
		s.setName("mushroom");
		s.setLocation("֣��");
		s.setGrade(90);
		
		dao.add(s);
	}
	
	@Test
	public void testFind(){
		StudentDao dao=new StudentDao();
		Student s=dao.find("222");
		System.out.print("name:"+s.getName()+" "+"idcard:"+s.getIdcard()+" "+"exmaid:"+s.getExamid()+" "+"Location:"+s.getLocation()+" "+"Grade:"+s.getGrade());
	}
	@Test
	public void testDelete(){
		StudentDao dao=new StudentDao();
		try {
			dao.delete("mushroom");
		} catch (StudentNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
