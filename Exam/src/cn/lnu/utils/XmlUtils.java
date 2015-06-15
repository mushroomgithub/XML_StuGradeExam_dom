package cn.lnu.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//���StudentDao���з����������ݿ���Ҫʹ�õĹ������룬������Ƴ�һ�����߰�utils,���еķ���һ�����Ϊ��̬����
public class XmlUtils {
	private static String filename="src/exam.xml";
	public static Document getDocument() throws Exception{
		//��ó��󹤳�ʵ��
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//���ù���ʵ����newDocumentBuilder()��������ĵ�������
		DocumentBuilder builder=factory.newDocumentBuilder();
		//���ý�������parse���������ĵ��������ĵ�����
		Document document=builder.parse(filename);
		return document;
	}
	public static void writeToXml(Document document) throws Exception{
		//���ڴ��е�document ����д�ص�exam.xml�ļ���ȥ��ͨ��һ��ת�������
		//���Ȼ��ת����������ʵ��
		TransformerFactory tfactory=TransformerFactory.newInstance();
		//����ת��������ʵ����newTransformer()�������ת������ʵ��
		Transformer tf=tfactory.newTransformer();
		//����ת������transform������documentת����xml�ļ���ȥ,���ǲ������ܵ���source,������Ҫ�Ƚ�documentת��Ϊһ��source,Ŀ���ļ��Ǹ�StreamResult
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filename)));
	}
}
