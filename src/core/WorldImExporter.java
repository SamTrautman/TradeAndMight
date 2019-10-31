package core;

import java.io.File;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import core.terrain.TerrainType;

public class WorldImExporter {

	private static WorldImExporter Instance;
	
	public static WorldImExporter getInstance() {
		if (Instance == null) {
			Instance = new WorldImExporter();
		}
		return Instance;
	}
	
	public void saveWorldToFile(String FileName) {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("TradeAndMight");
            document.appendChild(root);
            
            Element size = document.createElement("Size");
            size.setAttribute("SizeX", Integer.toString(World.getInstance().getSizeX()));
            size.setAttribute("SizeY", Integer.toString(World.getInstance().getSizeY()));
            
            root.appendChild(size);
            
            Element map = document.createElement("Map");
            
            for (int i = 0; i < World.getInstance().getSizeX(); i++) {
            	for (int j = 0; j < World.getInstance().getSizeY(); j++) {
            		Element mapTile = document.createElement("Tile");
            		mapTile.setAttribute("PosX", Integer.toString(i));
            		mapTile.setAttribute("PosY", Integer.toString(j));
            		mapTile.setAttribute("TerrainType", Integer.toString(World.getInstance().getTerrain(i, j).getTerrainType().ordinal()));
            		
            		map.appendChild(mapTile);
            	}
            }
            
            root.appendChild(map);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(FileName));
            
            transformer.transform(domSource, streamResult);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public void loadWorldFromFile(String FileName) {
		File fXmlFile = new File(FileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			Element size = (Element) doc.getElementsByTagName("Size").item(0);
			World.getInstance().createNewWorld(Integer.parseInt(size.getAttribute("SizeX")), Integer.parseInt(size.getAttribute("SizeY")));
			
			NodeList nList = doc.getElementsByTagName("Tile");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element Tile = (Element) nNode;
					World.getInstance().setTerrain(
							Integer.parseInt(Tile.getAttribute("PosX")), 
							Integer.parseInt(Tile.getAttribute("PosY")), 
							TerrainType.values()[Integer.parseInt(Tile.getAttribute("TerrainType"))]
							);
					
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
