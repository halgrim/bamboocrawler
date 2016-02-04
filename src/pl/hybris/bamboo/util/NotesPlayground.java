package pl.hybris.bamboo.util;

/**
 * Created by i323728 on 11/6/15.
 */
public class NotesPlayground
{
    /*
    Be carefull when using xPath to find class
    http://stackoverflow.com/questions/8808921/selecting-a-css-class-with-xpath
    console.log(document.evaluate('//*[contains(concat(" ", normalize-space(@class), " "), " item ")]', list, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);

    Natomiast jeżeli chodzi o początek wyrażenia xPath to 3 poniższe zwracają to samo
    console.log(document.evaluate('//*[contains(concat(" ", normalize-space(@class), " "), " item ")]', list, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);
    console.log(document.evaluate('.//li[contains(concat(" ", normalize-space(@class), " "), " item ")]', list, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);
    console.log(document.evaluate('//li[contains(concat(" ", normalize-space(@class), " "), " item ")]', list, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);

    Pierwsze primo
    "." - kropka z przodu każe szukać xPath w kontekście podanego noda (that is a descendant of the node), bez niej szuka zawsze w całości dokumentu

    Drugie primo
    console.log(document.evaluate('./div', listNode, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);
    / - root path, zwraca sztywnie node z tagiem div bezpośrednio od referencyjnego listNode, liczy path od listNode
    więc jeżeli będzie jakiś div dalej w drzewie to go nie znajdzie bo potrzebuje sztywnej ścieżki od roota '/'

    // - gdziekolwiek w dokumencie, ale zwraca pierwszy div w hierarchii liczac od "góry" więc jeżeli dziecko noda, które
    jest wcześniej ma inny tag niż div ale zawiera tag typu div to właśnie ten wewnętrzny div
    zostanie zwrócony ponieważ jest nad/wcześniej/od góry niż div zwrócony przez przypadek wyżej '/'


	class item jest zbędny bo łatwiej szukać po elemencie listy 'li' który czasem ma więcej klas niż tylko item
	wtedy użyć tego mega xPath a tak dla czytelności wystarczy element li
	public static final By AntTaskItem = By.xpath(".//ul/li[contains(concat(\" \", normalize-space(@class), \" \"), \" item \")/a/h3]");
	public static final By AntTaskItem = By.xpath(".//ul/li/a/h3");

	wyszukiwanie tekstu
	public static final By AntTaskItem = By.xpath(".//ul/li/a/h3[contains(text(),'Ant')]]");
	http://stackoverflow.com/questions/3655549/xpath-containstext-some-string-doesnt-work-when-used-with-node-with-more

	To powinno zrócić wszytkie nody z antem!
	//*[text()[contains(.,'Ant')]]

    * is a selector that matches any element (i.e. tag) -- it returns a node-set.
    The outer [] are a conditional that operates on each individual node in that node set -- here it operates on each element in the document.
    text() is a selector that matches all of the text nodes that are children of the context node -- it returns a node set.
    The inner [] are a conditional that operates on each node in that node set -- here each individual text node. Each individual text node is the starting point for any path in the brackets, and can also be referred to explicitly as . within the brackets. It matches if any of the individual nodes it operates on match the conditions inside the brackets.
    contains is a function that operates on a string. Here it is passed an individual text node (.). Since it is passed the second text node in the <Comment> tag individually, it will see the 'ABC' string and be able to match it.


    The string './/ul/li/a/h3[contains(text(),'Ant')]]' is not a valid XPath expression.
    text() gets you a set of text nodes. I tend to use it more in a context of //span//text() or something.
    If you are trying to check if the text inside an element contains something you should use contains on the element rather than the result of text() like this:
    span[contains(., 'testuser')]

    first select li node second h3 node
    public static final By AntTaskItem    = By.xpath(".//ul/li[a/h3[contains(.,'Ant')]]");
	public static final By AntTaskItem    = By.xpath(".//ul/li/a/h3[contains(.,'Ant')]");

    */
}
