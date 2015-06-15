package cn.lnu.dao;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.print.Doc;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.lnu.domain.Student;
import cn.lnu.exception.StudentNotExistException;
import cn.lnu.utils.XmlUtils;

//�����������ݿ���ص���StudentDao
public class StudentDao {
	//�����ݿ�����ӿ�����Ϣ
	public void add(Student s){
		try {//ʵ�ʿ����ж��쳣�Ĵ���
			Document document=XmlUtils.getDocument();
			
			//��������װѧ����Ϣ�ı�ǩ
			Element student_tag=document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			//�������ڷ�װѧ�����������ڵغͳɼ��ı�ǩ
			Element student_name=document.createElement("name");
			Element student_location=document.createElement("location");
			Element student_grade=document.createElement("grade");
			
			student_name.setTextContent(s.getName());
			student_location.setTextContent(s.getLocation());
			student_grade.setTextContent(s.getGrade()+"");
			//���ӱ�ǩ׷�ӵ�ѧ������ǩ��
			student_tag.appendChild(student_name);
			student_tag.appendChild(student_location);
			student_tag.appendChild(student_grade);
			//�ѷ�װ����Ϣѧ����ǩ���ҵ��ĵ���
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//�����ڴ浽xml�ļ�
			XmlUtils.writeToXml(document);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);//unchecked exception(��ԭʼ����ʱ�쳣ת��������ʱ�쳣����������Ļ�����֪ͨ���ϲ㣬Ҳû�и��ϲ�����鷳���ϲ�Ը�⴦����쳣�ʹ�����������Ͳ�����)
		}//checked exception(����ʱ�쳣�����ֻ�Ǽ򵥵�����һ�����쳣��û�����壬�ϲ㴦����Ҳ�����ף������û�����壬ֻ����ϲ�����鷳��������Բ�ȡ�ڸò�ץȡ�쳣��Ȼ��ת��Ϊ����ʱ�쳣�ķ�������)
	
	}
	//���ݿ���׼��֤�Ų���ѧ��
	public Student find(String examid){
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("student");
			for(int i=0;i<list.getLength();i++){
				Element student=(Element) list.item(i);
				if(student.getAttribute("examid").equals(examid)){
					//��ʾ�ҵ�׼��֤��Ϊexamid��ƥ��Ŀ�����newһ��Student�����װ���ѧ������Ϣ����
					Student s=new Student();
					s.setExamid(examid);
					s.setIdcard(student.getAttribute("idcard"));
				
					s.setName(student.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//���ݿ�������ɾ����Ӧ������Ϣ
	public void delete(String name) throws StudentNotExistException {
		try {
			Document document=XmlUtils.getDocument();
			
			NodeList list=document.getElementsByTagName("name");
			for(int i=0;i<list.getLength();i++){
				Element student=(Element) list.item(i);
				if(student.getTextContent().equals(name)){
					student.getParentNode().getParentNode().removeChild(student.getParentNode());
					XmlUtils.writeToXml(document);
					return;
				}
			}
			throw new StudentNotExistException(name+"�����ڣ�");
			
		} catch (StudentNotExistException e) {//���쳣�Ǳ���ʱ�쳣�����ڷ���������
			// TODO: handle exception
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
