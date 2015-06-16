package cn.lnu.dom4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


public class Demo1 {
	//��ȡXml�ĵ��ڶ����飺<����>JavaScript����</����>
	@Test
	public void read() throws Exception{
		SAXReader reader = new SAXReader();
		//FileInputStream in = new FileInputStream(new File("src/book.xml"));
		//Document document = reader.read(in,"UTF-8");
        Document document = reader.read(new File("src/book.xml"));
	     
	     //����õ��ڶ����飬������ĵ����ڵ���������Ȼ���ĵ����ڵ�
	     Element root=document.getRootElement();
	     //Ȼ���ҵ��ڶ������ǩ
	     Element book=(Element) root.elements("��").get(1);
	     //�õ��ڶ����������
	     String value=book.elementText("����");
	     //String value2=book.element("����").getText();
	     //System.out.println("�ڶ���������Ϊ��"+value);
	     String attrname=book.element("����").attributeValue("name");
	     System.out.println("�ڶ���������Ϊ��"+value+" name="+attrname);
	}
	//�ڵ�һ���������һ���µ��ۼۣ�<�ۼ�>56Ԫ</�ۼ�>,��������һ��bug��FileWriter���ĵ���д����ʱ��
	//��Ĭ�ϲ��ñ��ص�gb2312�������xml�ĵ���д����(��xml�ĵ���urf-8),��0��1������д,Ϊ�˷�ֹ�������,��ҪʹFileWriter��д����ʱ����utf-8����
	@Test
	public void add() throws Exception{
		 SAXReader reader = new SAXReader();
		 //FileInputStream in = new FileInputStream(new File("src/book.xml"));
		 //Document document = reader.read(in,"UTF-8");
	     Document document = reader.read(new File("src/book.xml"));
	   //����õ���һ���飬������ĵ����ڵ���������Ȼ���ĵ����ڵ�
	     Element root=document.getRootElement();
	     //Ȼ���ҵ���һ�����ǩ
	     Element book=(Element) root.elements("��").get(0);
	     book.addElement("�ۼ�").setText("56Ԫ");
	     //XML��������
	     /*XMLWriter writer = new XMLWriter(new FileWriter( "src/book.xml" ));
	     writer.write( document );
	     writer.close();*/
	     
	     //��õĽ��XML�ĵ���������ķ�ʽ�����ȴ���һ����ʽ�������
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     //Ϊ��ʽ�������������Ҫд�ص�xml�ĵ�����һ�£����book.xml��gb2312���룬���������Ϊgb2312����
	     format.setEncoding("GB2312");
	     //ע��XMLWriter�в�Ҫʹ��new FileWriter(������0,1�ַ���)д����������ʹ��OutputStreamWriter��������ָ������,����ֱ��ʹ��FileOutputStream(�ֽ���)��
	     //����write��ʱ���Ĭ�ϲ��ø�ʽ����������õı���
	     //XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/book.xml"), "UTF-8"),format);
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	
	//�ڵ�һ����ָ��λ�������һ���µ��ۼ�:<�ۼ�>100Ԫ</�ۼ�> ,���ø��ı��������к��ӵ�list���ϵ�˳��
	@Test
	public void add2() throws Exception{
		 SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //����õ���һ���飬������ĵ����ڵ���������Ȼ���ĵ����ڵ�
	     Element root=document.getRootElement();
	     //Ȼ���ҵ���һ����
	     Element book=(Element) root.elements("��").get(0);
	     List list=book.elements();//��ÿ�����������ǩת��Ϊһ���������������ߣ��ۼۣ��ۼۡ�
	     //���ȴ���һ���µ�Ҫ�����ǩ
	     Element price=DocumentHelper.createElement("�ۼ�");
	     price.setText("100Ԫ");
	     //����List��add��������ǩ���뵽ָ��λ��
	     list.add(2,price);
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	//ɾ��������ӵ��ۼ۽��
	@Test
	public void delete() throws Exception, FileNotFoundException{
		SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //����õ���һ���飬������ĵ����ڵ���������Ȼ���ĵ����ڵ�
	     Element root=document.getRootElement();
	     //Ȼ���ҵ���һ����
	     Element book=(Element) root.elements("��").get(0);
	     Element price=book.element("�ۼ�");
	     price.getParent().remove(price);
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
	//����xml�ĵ��еڶ����������
	@Test
	public void Update() throws Exception{
		SAXReader reader = new SAXReader();
	     Document document = reader.read(new File("src/book.xml"));
	   //����õ���һ���飬������ĵ����ڵ���������Ȼ���ĵ����ڵ�
	     Element root=document.getRootElement();
	     //Ȼ���ҵ��ڶ�����
	     Element book=(Element) root.elements("��").get(1);
	     Element author=book.element("����");
	     author.setText("mushroom");
	     
	     OutputFormat format = OutputFormat.createPrettyPrint();
	     format.setEncoding("GB2312");
	     XMLWriter writer = new XMLWriter(new FileOutputStream("src/book.xml"),format);
	     writer.write( document );
	     writer.close();
	}
}
