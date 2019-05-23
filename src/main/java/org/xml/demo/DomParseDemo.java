package org.xml.demo;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DomParseDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        InputStream is =  DomParseDemo.class.getClassLoader().getResourceAsStream("test.xml");
        DocumentBuilder docBuilder =factory.newDocumentBuilder();
        Document document = docBuilder.parse(is);

        Element rootElement = document.getDocumentElement();
        printAllXmlNodes(rootElement.getChildNodes(), "\t");
    }

    private static void printAllXmlNodes(NodeList childs, String prefix) {
        for (int i = 0; i< childs.getLength(); i++) {
            Node node = childs.item(i);
            if (node instanceof Element) {
                System.out.println("Node name is " + prefix + node.getNodeName());
                if (node.hasAttributes()) {
                    NamedNodeMap attributes = node.getAttributes();
                    for (int j = 0 ; j<attributes.getLength(); j++) {
                        Node attributeNode = attributes.item(j);
                        System.out.println("Attribute class " + prefix + " " + attributeNode.getClass().getName());
                    }
                }
                printAllXmlNodes(node.getChildNodes(), prefix + "\t");
            }
        }
    }
}
