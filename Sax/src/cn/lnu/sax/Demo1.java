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

//sax����xml�ĵ�
public class Demo1 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2.�õ�sax������
		SAXParser sp=factory.newSAXParser();
		//3.�õ�sax��ȡ��
		XMLReader reader=sp.getXMLReader();
		//4.�������ݴ�����,һ��Ҫ�ڶ�ȡ֮ǰ��������д��
		reader.setContentHandler(new ListHandler());
		//5.��ȡxml�ĵ�����
		reader.parse("src/book.xml");
	}
}
//���xml�ĵ���������
class ListHandler implements ContentHandler{

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		System.out.print("<"+qName+">");
		
		//��ӡ��ʼ��ǩ�е����Ժ�����ֵ
		for(int i=0;atts!=null&&i<atts.getLength();i++){
			String attrName=atts.getQName(i);
			String attrValue=atts.getValue(i);
			System.out.println(attrName+"="+attrValue);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.println(new String(ch,start,length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.print("</"+qName+">");
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}