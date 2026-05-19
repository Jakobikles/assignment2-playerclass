// Yzerman Scukanec, Jakob WIley
// XML Parser for Deadwood
package ParseXML;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import objects.*;
import Locations.*;
//import Action.*;

public class XMLParser {

    public Board parseBoard(String file) {
        Document doc = loadDocument(file);

        if (doc == null) {
            return null;
        }

        Board board = new Board();
        Map<String, List<String>> neighborNames = new HashMap<>();
        Map<String, Location> setsByName = new HashMap<>();
        Element root = doc.getDocumentElement();

        // Building all set nodes
        NodeList sets = root.getElementsByTagName("set");
        for (int i = 0; i < sets.getLength(); i++) {
            Element setE1 = (Element) sets.item(i);
            Set gameSet = parseSet(setE1);
            setsByName.put(gameSet.getName(), gameSet);
            neighborNames.put(gameSet.getName(), parseNeighborNames(setE1));
            board.addSet(gameSet);
        }

        // Parse Trailer
        NodeList trailerList = root.getElementsByTagName("trailer");
        if (trailerList.getLength() > 0) {
            Element trailerE1 = (Element) trailerList.item(0);
            Trailers trailer = new Trailers();
            setsByName.put("trailer", trailer);
            setsByName.put("Trailers", trailer);
            neighborNames.put("Trailers", parseNeighborNames(trailerE1));
            board.setTrailers(trailer);
            board.addSet(trailer);
        }

        // Parse Casting Office
        NodeList officeList = root.getElementsByTagName("office");
        if (officeList.getLength() > 0) {
            Element officeEl = (Element) officeList.item(0);
            CastingOffice office = parseOffice(officeEl);
            setsByName.put("office", office);
            setsByName.put("Casting Office", office);
            neighborNames.put("Casting Office", parseNeighborNames(officeEl));
            board.setCastingOffice(office);
            board.addSet(office);
        }

        // Resolve neighboors references
        for (Map.Entry<String, List<String>> entry : neighborNames.entrySet()) {
            Location thisSet = setsByName.get(entry.getKey());
            if (thisSet == null)
                continue;
            for (String neighborName : entry.getValue()) {
                Location neighbor = setsByName.get(neighborName);
                if (neighbor != null) {
                    thisSet.addNeighbor(neighbor);
                } else {
                    System.out.println("[Parser] Warning: neighbor '" + neighborName
                            + "' not found for set '" + entry.getKey() + "'");
                }
            }
        }
        System.out.println("[Parser] Board loaded: " + board.getLocations().size() + " locations.");
        return board;
    }

    // Parses cards and returns a deck
    public Deck parseDeck(String filename) {

        Document doc = loadDocument(filename);
        if (doc == null)
            return null;
        Deck deck = new Deck();
        NodeList cards = doc.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++) {
            Element cardEl = (Element) cards.item(i);
            Scene card = parseCard(cardEl);
            deck.addCard(card);
        }
        System.out.println("[Parser] Deck loaded: " + deck.size() + " scene cards.");
        return deck;
    }

    // Builds gameSet from set element
    private Set parseSet(Element setEl) {
        String name = setEl.getAttribute("name");
        Set gameSet = new Set(name);
        NodeList takes = setEl.getElementsByTagName("take");
        gameSet.setShotCounters(takes.getLength());

        NodeList parts = setEl.getElementsByTagName("part");
        for (int i = 0; i < parts.getLength(); i++) {
            Element partEl = (Element) parts.item(i);
            Role role = parsePart(partEl, false);
            gameSet.addSideRole(role);
        }
        return gameSet;
    }

    // Builds Casting Office from effice element
    private CastingOffice parseOffice(Element officeEl) {
        CastingOffice office = new CastingOffice();
        NodeList upgrades = officeEl.getElementsByTagName("upgrade");

        for (int i = 0; i < upgrades.getLength(); i++) {
            Element upEl = (Element) upgrades.item(i);
            int level = Integer.parseInt(upEl.getAttribute("level"));
            String currency = upEl.getAttribute("currency");
            int amount = Integer.parseInt(upEl.getAttribute("amt"));
            office.setUpgradeCost(level, currency, amount);
        }
        return office;
    }

    // Returns a list of neighboors strings
    private List<String> parseNeighborNames(Element setEl) {
        List<String> names = new ArrayList<>();
        NodeList neighbors = setEl.getElementsByTagName("neighbor");
        for (int i = 0; i < neighbors.getLength(); i++) {
            Element n = (Element) neighbors.item(i);
            names.add(n.getAttribute("name"));
        }
        return names;
    }

    // Builds SceneCard
    private Scene parseCard(Element cardEl) {
        String cardName = cardEl.getAttribute("name");
        int budget = Integer.parseInt(cardEl.getAttribute("budget"));
        int sceneNum = 0;
        String sceneDesc = "";
        NodeList sceneNodes = cardEl.getElementsByTagName("scene");
        if (sceneNodes.getLength() > 0) {
            Element sceneEl = (Element) sceneNodes.item(0);
            sceneNum = Integer.parseInt(sceneEl.getAttribute("number"));
            sceneDesc = sceneEl.getTextContent().trim();
        }

        Scene card = new Scene(sceneNum, cardName, budget);
        card.setDescription(sceneDesc);
        NodeList parts = cardEl.getElementsByTagName("part");

        // Starring Roles
        for (int i = 0; i < parts.getLength(); i++) {
            Element partEl = (Element) parts.item(i);
            Role role = parsePart(partEl, true);
            role.setScene(card);
            card.addStarRole(role);
        }
        return card;
    }

    // Builds Role
    private Role parsePart(Element partEl, boolean isStar) {
        String partName = partEl.getAttribute("name");
        int level = Integer.parseInt(partEl.getAttribute("level"));
        String line = "";
        NodeList lines = partEl.getElementsByTagName("line");

        if (lines.getLength() > 0) {
            line = lines.item(0).getTextContent().trim();
        }

        Role role = new Role(partName, level, isStar);
        role.setLine(line);
        return role;
    }

    // Document Loader
    private Document loadDocument(String filename) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(new File(filename));
        } catch (ParserConfigurationException e) {
            System.out.println("[Parser] Configuration error.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[Parser] Failed to parse: " + filename);
            e.printStackTrace();
        }
        return null;
    }
}