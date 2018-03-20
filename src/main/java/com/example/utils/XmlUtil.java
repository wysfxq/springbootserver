package com.example.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public final class XmlUtil {
    private static final Logger log = LoggerFactory.getLogger(XmlUtil.class);
    private static final String XMLNS_XSI = "xmlns:xsi";
    private static final String XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
    private static final String LOGIC_YES = "yes";
    private static final String DEFAULT_CHARSET = "UTF-8";

    public XmlUtil() {
    }

    public static Document newDocument() throws IOException {
        Document doc = null;

        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            return doc;
        } catch (ParserConfigurationException var2) {
            throw new IOException(var2.getMessage(), var2);
        }
    }

    public static Document getDocument(File file) throws IOException {
        InputStream in = getInputStream(file);
        return getDocument(new InputSource(in), (InputStream) null);
    }

    public static Document getDocument(InputSource xml, InputStream xsd) throws IOException {
        Document doc = null;

        try {
            DocumentBuilderFactory e = DocumentBuilderFactory.newInstance();
            if (xsd != null) {
                e.setNamespaceAware(true);
            }

            DocumentBuilder builder = e.newDocumentBuilder();
            doc = builder.parse(xml);
            if (xsd != null) {
                validateXml((Node) doc, xsd);
            }
        } catch (ParserConfigurationException var10) {
            throw new IOException("XML_PARSE_ERROR", var10);
        } catch (SAXException var11) {
            throw new IOException("XML_PARSE_ERROR", var11);
        } catch (IOException var12) {
            throw new IOException("XML_READ_ERROR", var12);
        } finally {
            closeStream(xml.getByteStream());
        }

        return doc;
    }

    public static Element createRootElement(String tagName) throws IOException {
        Document doc = newDocument();
        Element root = doc.createElement(tagName);
        doc.appendChild(root);
        return root;
    }

    public static Element getRootElementFromStream(InputStream xml) throws IOException {
        return getDocument(new InputSource(xml), (InputStream) null).getDocumentElement();
    }

    public static Element getRootElementFromStream(InputStream xml, InputStream xsd) throws IOException {
        return getDocument(new InputSource(xml), xsd).getDocumentElement();
    }

    public static Element getRootElementFromFile(File xml) throws IOException {
        return getDocument(xml).getDocumentElement();
    }

    public static Element getRootElementFromString(String payload) throws IOException {
        if (payload != null && payload.length() >= 1) {
            StringReader sr = new StringReader(escapeXml(payload));
            InputSource source = new InputSource(sr);
            return getDocument(source, (InputStream) null).getDocumentElement();
        } else {
            throw new IOException("XML_PAYLOAD_EMPTY");
        }
    }

    public static List<Element> getElements(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        ArrayList elements = new ArrayList();

        for (int i = 0; i < nodes.getLength(); ++i) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    public static Element getElement(Element parent, String tagName) {
        List children = getElements(parent, tagName);
        return children.isEmpty() ? null : (Element) children.get(0);
    }

    public static List<Element> getChildElements(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        ArrayList elements = new ArrayList();

        for (int i = 0; i < nodes.getLength(); ++i) {
            Node node = nodes.item(i);
            if (node instanceof Element && node.getParentNode() == parent) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    public static List<Element> getChildElements(Element parent) {
        NodeList nodes = parent.getChildNodes();
        ArrayList elements = new ArrayList();

        for (int i = 0; i < nodes.getLength(); ++i) {
            Node node = nodes.item(i);
            if (node instanceof Element && node.getParentNode() == parent) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    public static Element getChildElement(Element parent, String tagName) {
        List children = getChildElements(parent, tagName);
        return children.isEmpty() ? null : (Element) children.get(0);
    }

    public static String getElementValue(Element parent, String tagName) {
        Element element = getElement(parent, tagName);
        return element != null ? element.getTextContent() : null;
    }

    public static String getChildElementValue(Element parent, String tagName) {
        Element element = getChildElement(parent, tagName);
        return element != null ? element.getTextContent() : null;
    }

    public static String getElementValue(Element element) {
        if (element != null) {
            NodeList nodes = element.getChildNodes();
            if (nodes != null && nodes.getLength() > 0) {
                for (int i = 0; i < nodes.getLength(); ++i) {
                    Node node = nodes.item(i);
                    if (node instanceof Text) {
                        return ((Text) node).getData();
                    }
                }
            }
        }

        return null;
    }

    public static String getAttributeValue(Element current, String attrName) {
        return current.hasAttribute(attrName) ? current.getAttribute(attrName) : null;
    }

    public static Element appendElement(Element parent, String tagName) {
        Element child = parent.getOwnerDocument().createElement(tagName);
        parent.appendChild(child);
        return child;
    }

    public static Element appendElement(Element parent, String tagName, String value) {
        Element child = appendElement(parent, tagName);
        child.setTextContent(value);
        return child;
    }

    public static void appendElement(Element parent, Element child) {
        Node tmp = parent.getOwnerDocument().importNode(child, true);
        parent.appendChild(tmp);
    }

    public static Element appendCDATAElement(Element parent, String tagName, String value) {
        Element child = appendElement(parent, tagName);
        if (value == null) {
            value = "";
        }

        CDATASection cdata = child.getOwnerDocument().createCDATASection(value);
        child.appendChild(cdata);
        return child;
    }

    public static String childNodeToString(Node node) throws IOException {
        String payload = null;

        try {
            Transformer e = TransformerFactory.newInstance().newTransformer();
            Properties props = e.getOutputProperties();
            props.setProperty("omit-xml-declaration", "yes");
            props.setProperty("encoding", "UTF-8");
            e.setOutputProperties(props);
            StringWriter writer = new StringWriter();
            e.transform(new DOMSource(node), new StreamResult(writer));
            payload = escapeXml(writer.toString());
            return payload;
        } catch (TransformerException var5) {
            throw new IOException("XML_TRANSFORM_ERROR", var5);
        }
    }

    public static String nodeToString(Node node) throws IOException {
        String payload = null;

        try {
            Transformer e = TransformerFactory.newInstance().newTransformer();
            Properties props = e.getOutputProperties();
            props.setProperty("encoding", "UTF-8");
            props.setProperty("indent", "yes");
            e.setOutputProperties(props);
            StringWriter writer = new StringWriter();
            e.transform(new DOMSource(node), new StreamResult(writer));
            payload = escapeXml(writer.toString());
            return payload;
        } catch (TransformerException var5) {
            throw new IOException("XML_TRANSFORM_ERROR", var5);
        }
    }

    public static String escapeXml(String payload) {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < payload.length(); ++i) {
            char c = payload.charAt(i);
            if (c == 9 || c == 10 || c == 13 || c >= 32 && c <= '\ud7ff' || c >= '\ue000' && c <= '�') {
                out.append(c);
            }
        }

        return out.toString();
    }

    public static String xmlToString(File file) throws IOException {
        Element root = getRootElementFromFile(file);
        return nodeToString(root);
    }

    public static String xmlToString(InputStream in) throws IOException {
        Element root = getRootElementFromStream(in);
        return nodeToString(root);
    }

    public static void saveToXml(Node doc, File file) throws IOException {
        saveToXml(doc, file, "UTF-8");
    }

    public static void saveToXml(Node doc, File file, String charset) throws IOException {
        OutputStreamWriter writer = null;

        try {
            Transformer e = TransformerFactory.newInstance().newTransformer();
            Properties props = e.getOutputProperties();
            props.setProperty("method", "xml");
            props.setProperty("indent", "yes");
            e.setOutputProperties(props);
            DOMSource dom = new DOMSource(doc);
            writer = new OutputStreamWriter(getOutputStream(file), charset);
            StreamResult result = new StreamResult(writer);
            e.transform(dom, result);
        } catch (Exception var11) {
            throw new IOException("XML_WRITE_FILE_ERROR", var11);
        } finally {
            closeStream(writer);
        }

    }

    public static void validateXml(InputStream xml, InputStream xsd) throws IOException {
        try {
            DocumentBuilderFactory e = DocumentBuilderFactory.newInstance();
            e.setNamespaceAware(true);
            Document doc = e.newDocumentBuilder().parse(xml);
            validateXml((Node) doc, xsd);
        } catch (SAXException var8) {
            throw new IOException("XML_VALIDATE_ERROR", var8);
        } catch (Exception var9) {
            throw new IOException("XML_READ_ERROR", var9);
        } finally {
            closeStream(xml);
            closeStream(xsd);
        }

    }

    public static void validateXml(Node root, InputStream xsd) throws IOException {
        try {
            StreamSource e = new StreamSource(xsd);
            Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(e);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(root));
        } catch (SAXException var9) {
            if (log.isErrorEnabled()) {
                log.error("检验֤XML出错\n" + nodeToString(root));
            }

            throw new IOException("XML_VALIDATE_ERROR", var9);
        } catch (Exception var10) {
            throw new IOException("XML_READ_ERROR", var10);
        } finally {
            closeStream(xsd);
        }

    }

    public static String xmlToHtml(String payload, File xsltFile) throws IOException {
        String result = null;

        try {
            StreamSource e = new StreamSource(xsltFile);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(e);
            Properties props = transformer.getOutputProperties();
            props.setProperty("omit-xml-declaration", "yes");
            transformer.setOutputProperties(props);
            StreamSource source = new StreamSource(new StringReader(payload));
            StreamResult sr = new StreamResult(new StringWriter());
            transformer.transform(source, sr);
            result = sr.getWriter().toString();
            return result;
        } catch (TransformerException var8) {
            throw new IOException("XML_TRANSFORM_ERROR", var8);
        }
    }

    public static void setNamespace(Element element, String namespace, String schemaLocation) {
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", namespace);
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        element.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", schemaLocation);
    }

    public static String encodeXml(String payload) throws IOException {
        Element root = createRootElement("xml");
        root.setTextContent(payload);
        return childNodeToString(root.getFirstChild());
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    private static InputStream getInputStream(File file) throws IOException {
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            return in;
        } catch (FileNotFoundException var3) {
            throw new IOException("XML_FILE_NOT_FOUND", var3);
        }
    }

    private static OutputStream getOutputStream(File file) throws IOException {
        FileOutputStream in = null;

        try {
            in = new FileOutputStream(file);
            return in;
        } catch (FileNotFoundException var3) {
            throw new IOException("XML_FILE_NOT_FOUND", var3);
        }
    }

    public static String XmlFromObject(Object obj) throws Exception {
        return XmlFromObject("xmlData", obj);
    }

    public static String XmlFromObject(String rootName, Object obj) throws Exception {
        return XmlFromObject(rootName, obj, true);
    }

    public static String XmlFromObject(String rootName, Object obj, boolean appendVersion) throws Exception {
        String rtn = "";
        if (appendVersion) {
            rtn = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
        }

        rtn = rtn + "<" + rootName + ">" + getXmlString(obj) + "</" + rootName + ">";
        return rtn;
    }

    public static String getXmlString(Object obj) throws Exception {
        return obj == null ? "" : (obj instanceof Number ? numberToString((Number) obj) : (obj instanceof Boolean ? obj.toString() : (obj instanceof String ? obj.toString().replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;").replace("\'", "&apos;").replace("\"", "&quot;") : (obj instanceof Map ? mapToString((Map) obj) : (obj instanceof Class ? ((Class) obj).getSimpleName() : (obj instanceof Enum ? obj.toString() : (obj instanceof Date ? DateUtil.toDateString((Date) obj) : (!(obj instanceof Collection) && !obj.getClass().isArray() ? reflectObject(obj) : getXmlArray(obj)))))))));
    }

    private static String mapToString(Map map) {
        try {
            Iterator e = map.keySet().iterator();
            StringBuffer sb = new StringBuffer("");

            while (e.hasNext()) {
                Object o = e.next();
                sb.append("<" + o.toString() + ">");
                sb.append(getXmlString(map.get(o)));
                sb.append("</" + o.toString() + "> \n");
            }

            return sb.toString();
        } catch (Exception var4) {
            return "";
        }
    }

    private static String getXmlArray(Object obj) throws Exception {
        if (obj == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer("");
            if (obj instanceof Collection) {
                Iterator arrayLength = ((Collection) obj).iterator();

                while (arrayLength.hasNext()) {
                    Object i = arrayLength.next();
                    sb.append(getXmlString(i));
                }
            }

            if (obj.getClass().isArray()) {
                int var5 = Array.getLength(obj);

                for (int var6 = 0; var6 < var5; ++var6) {
                    Object rowObj = Array.get(obj, var6);
                    sb.append(getXmlString(rowObj));
                }
            }

            return sb.toString();
        }
    }

    private static String reflectObject(Object bean) {
        Class c = bean.getClass();
        Method[] methods = c.getMethods();
        String ss = bean.getClass().getSimpleName();
        if (Character.isUpperCase(ss.charAt(0))) {
            if (ss.length() == 1) {
                ss = ss.toLowerCase();
            } else if (Character.isUpperCase(ss.charAt(1))) {
                ss = ss.substring(0, 2).toLowerCase() + ss.substring(2);
            } else {
                ss = ss.substring(0, 1).toLowerCase() + ss.substring(1);
            }
        }

        StringBuffer sb = new StringBuffer("<" + ss + "> \n");

        for (int i = 0; i < methods.length; ++i) {
            try {
                Method e = methods[i];
                String name = e.getName();
                String key = "";
                if (name.startsWith("get")) {
                    key = name.substring(3);
                } else if (name.startsWith("is")) {
                    key = name.substring(2);
                }

                if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && e.getParameterTypes().length == 0) {
                    if (key.length() == 1) {
                        key = key.toLowerCase();
                    } else if (!Character.isUpperCase(key.charAt(1))) {
                        key = key.substring(0, 1).toLowerCase() + key.substring(1);
                    }

                    Object elementObj = e.invoke(bean, (Object[]) null);
                    if (!(elementObj instanceof Class)) {
                        String str = getXmlString(elementObj);
                        if (!StringUtil.isEmpty(str) && str.length() > 0) {
                            sb.append("<" + key + ">");
                            sb.append(str);
                            sb.append("</" + key + "> \n");
                        }
                    }
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        sb.append("</" + ss + "> \n");
        return sb.toString();
    }

    private static String numberToString(Number number) throws Exception {
        if (number == null) {
            return "";
        } else {
            String string = number.toString();
            if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
                while (string.endsWith("0")) {
                    string = string.substring(0, string.length() - 1);
                }

                if (string.endsWith(".")) {
                    string = string.substring(0, string.length() - 1);
                }
            }

            return string;
        }
    }
}
