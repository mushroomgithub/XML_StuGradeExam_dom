package cn.lnu.dom4j_xPath;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo3 {

	/**
	 * ����users.xml�ĵ����Ƿ��к��û���ƥ����û���������,��������û��������½�ɹ��������½���ɹ�
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//���迪ʼ�û����������Ѿ����ݹ�����������ֱ�Ӷ�������ʾ���ݹ������û���������
		String username="����";
		String passwd="123456";
		//���xml���Ƿ���ƥ����û���������
		SAXReader reader = new SAXReader();
		 Document document=reader.read(new File("src/users.xml"));
		 //���xml�ĵ�����user��ǩ�������������û���������ƥ�䴫�ݹ������û���������Ľ��,ע�������xPath���ʽ��д���������Ų����٣�'',�Լ���ζ��ַ�����"",���'""',֮����""������++���'"+����+"'
		 Node node=document.selectSingleNode("//user[@username='"+username+"' and @passwd='"+passwd+"']");
		 if(node!=null){
			 System.out.println("��½�ɹ���");
		 }else{
			 System.out.println("�û����������벻ƥ�䣡");
		 }
	}

}
