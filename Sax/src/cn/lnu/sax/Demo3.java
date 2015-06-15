package cn.lnu.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

//sax����xml�ĵ�
public class Demo3 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2.�õ�sax������
		SAXParser sp=factory.newSAXParser();
		//3.�õ�sax��ȡ��
		XMLReader reader=sp.getXMLReader();
		//4.�������ݴ�����,һ��Ҫ�ڶ�ȡ֮ǰ��������д��
		BeanListHandler handler=new BeanListHandler();
		reader.setContentHandler(handler);
		//5.��ȡxml�ĵ�����
		reader.parse("src/book.xml");
		
		//�������н����Ķ����ӡ����
		List list=handler.getList();
		for(int i=0;i<list.size();i++){
			Book book=(Book)list.get(i);
			System.out.println("������"+book.getBookName()+" "+" ���ߣ�"+book.getBookAuthor()+" "+" �ۼۣ�"+book.getBookPrice());
		}
	}
}
//ʵ�ʿ����ж��ǽ�xml�е�ÿһ�����װ��һ��book�����У����Ѷ��book�������һ��list�����з��أ�������������ݽ�����һ�������л���
class BeanListHandler extends DefaultHandler{

	public List getList() {
		return list;
	}

	private List list=new ArrayList();
	private Book book;
	private String currentName;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		currentName=qName;
		if("��".equals(currentName)){
			book=new Book();
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if("����".equals(currentName)){
			String bookName=new String(ch,start,length);
			book.setBookName(bookName);
		}
		if("����".equals(currentName)){
			String bookAuthor=new String(ch,start,length);
			book.setBookAuthor(bookAuthor);
		}
		if("�ۼ�".equals(currentName)){
			String bookPrice=new String(ch,start,length);
			book.setBookPrice(bookPrice);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("��")){//���������Ľ�����ǩ
			list.add(book);
			book=null;
		}
		currentName=null;
	}
	
	
}
