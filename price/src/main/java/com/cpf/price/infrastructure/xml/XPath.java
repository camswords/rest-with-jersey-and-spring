package com.cpf.price.infrastructure.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.math.BigDecimal;

import static java.lang.String.format;

public class XPath {

    private String xml;
    private Document document;

    public XPath(String xml) {
        this.xml = xml;
        this.document = null;
    }

    public String getString(String xPath) {
        return evaluateRequired(xPath, XPathConstants.STRING);
    }

    public BigDecimal getBigDecimal(String xPath) {
        return new BigDecimal(getString(xPath));
    }

    private String evaluateRequired(String xPath, QName type) {
        String value = evaluateOptional(xPath, type);

        if (value == null || "".equals(value)) {
            String message = format("failed to get required element identified by xPath expression %s. Xml is [%s].", xPath, xml);
            throw new RuntimeException(message);
        }

        return value;
    }

    private String evaluateOptional(String xPath, QName type) {
        try {
            if (document == null) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                document = documentBuilder.parse(new InputSource(new StringReader(xml)));
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));

            XPathFactory xPathFactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathFactory.newXPath();
            XPathExpression xPathExpression = xpath.compile(xPath);

            return (String) xPathExpression.evaluate(document, type);

        } catch (Exception e) {
            throw new RuntimeException(format("failed to parse xml: [%s]", xml), e);
        }
    }
}
