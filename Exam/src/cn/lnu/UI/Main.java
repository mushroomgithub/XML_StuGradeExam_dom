package cn.lnu.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.lnu.dao.StudentDao;
import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Usage:����û���(a)  ɾ���û���(b)  ��ѯ�û���(c)");
		System.out.print("������������ͣ�");
		//���������뱣�浽buffer��
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		try {
			String inputType=bf.readLine();
			
			if("a".equals(inputType)){
				System.out.print("������ѧ��������");
				String name=bf.readLine();
				
				System.out.print("������ѧ�����֤�ţ�");
				String idcard=bf.readLine();
				
				System.out.print("������ѧ��׼��֤�ţ�");
				String examid=bf.readLine();
				
				System.out.print("������ѧ�����ڵأ�");
				String location=bf.readLine();
				System.out.print("������ѧ���ɼ���");
				String grade=bf.readLine();
				
				Student s=new Student();
				s.setExamid(examid);
				s.setIdcard(idcard);
				s.setName(name);
				s.setLocation(location);
				s.setGrade(Double.parseDouble(grade));
				
				StudentDao dao=new StudentDao();
				try{
					dao.add(s);
					System.out.println("�����ѧ���ɹ���");
				}catch(Exception e){
					e.printStackTrace();
					System.out.print("xml���ݿ����Ѿ����ڸ��û���");
				}
				
			}else if("b".equals(inputType)){
				System.out.print("������Ҫɾ��ѧ����������");
				String name=bf.readLine();
				
				StudentDao dao=new StudentDao();
				try {
					dao.delete(name);
					System.out.println("ɾ���ɹ���");
				} catch (StudentNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ѧ��"+name+"�����ڣ�");
				}
			}else if("c".equals(inputType)){
				System.out.print("������Ҫ��ѯ��ѧ��׼��֤�ţ�");
				String examid=bf.readLine();
				
				StudentDao dao=new StudentDao();
				Student s=dao.find(examid);
				System.out.print("name:"+s.getName()+" "+" idcard:"+s.getIdcard()+" "+" exmaid:"+s.getExamid()+" "+" Location:"+s.getLocation()+" "+"   Grade:"+s.getGrade());
				System.out.println("��ѯ�ɹ���");
			}else{
				System.out.println("��֧�ָò�����");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�Բ��𣬳�������ˣ�");
			
		}
	}

}
