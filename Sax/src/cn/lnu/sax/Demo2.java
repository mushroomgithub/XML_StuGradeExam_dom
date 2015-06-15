package cn.lnu.sax;

import java.io.IOException;

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
public class Demo2 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2.�õ�sax������
		SAXParser sp=factory.newSAXParser();
		//3.�õ�sax��ȡ��
		XMLReader reader=sp.getXMLReader();
		//4.�������ݴ�����,һ��Ҫ�ڶ�ȡ֮ǰ��������д��
		reader.setContentHandler(new TagValueHandler());
		//5.��ȡxml�ĵ�����
		reader.parse("src/book.xml");
	}
}
//���xml�ĵ�ָ����ǩ������
class TagValueHandler extends DefaultHandler{
	private String currentTag;//��ס��ǰsax����������ʲô��ǩ
	private int needTagIndex=2;//��ס���ȡ�ڼ������߱�ǩ��ֵ�ı�ǩ
	private int currentTagIndex;//��ǰ���������ǵڼ�����ǩ
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		currentTag=qName;
		if("����".equals(currentTag)){
			currentTagIndex++;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		
		if("����".equals(currentTag)&&currentTagIndex==needTagIndex){
			System.out.println(new String(ch,start,length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		currentTag=null;
	}

}