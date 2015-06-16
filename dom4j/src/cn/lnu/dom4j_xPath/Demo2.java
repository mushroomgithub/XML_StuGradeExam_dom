package cn.lnu.dom4j_xPath;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

//���xPath���ٶ�λ������dom4j���������Կ�����ȡ����Ҫ�����Ľ����߱�ǩ������
public class Demo2 {
	public static void main(String[] args) throws Exception{
		 SAXReader reader = new SAXReader();
		 Document document=reader.read(new File("src/book.xml"));
		 //��ȡxml�ĵ��е�һ�����߽�����ݣ�//����ΪxPath���ʽ����ʾ�������߽��
		 String Author=document.selectSingleNode("//����").getText();
		 System.out.println("Xml�ĵ��е�һ�����ߵ������ǣ�"+Author);
	}
}
