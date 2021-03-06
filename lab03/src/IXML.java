// lab 03
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab3.html
// Ex 02

import tester.Tester;

// an XML is a ListOf<XMLFragment>

//REPRESENTS: a XML
interface IXML{
	// RETURNS: the length of content in this document
	int contentLength();
	
	// RETURNS: the maximum number of tag nesting in a document
	int maxNest();
	
	// RETURNS: true iff this document has the given tag
	boolean hasTag(Tag tag);
	
	// RETURNS: true iff this document has the given attribute
	boolean hasAttribute(String attr);
	
	// RETURNS: true iff this document hat the given attribute in the given tag
	boolean hasAttributeInTag(String attr, Tag tag);
	
	// RETURNS: a XML like this one but with all volume attribute set to 0db
	IXML mute();
	
	// RETURNS: a XML like this one but with all volume attribute in yell 
	// tag set to 0db
	IXML muteYell();
	
	// RETURNS: a XML like this one but with all attribute that equals to attr
	// and occurs in an tag equals to the given tag set to the given value
	IXML setAttrInTag(Tag tag, String attr, String value);	
	
	// RETURNS: a XML like this one but with all attribute equals to attr update
	// to the given value
	IXML updateAttribute(String attr, String value);
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	String renderAsString();
	
	// RETURNS: a XML like this one but change all Tag equals to the given src 
	// tag to the dst tag
	IXML renameTag(Tag src, Tag dst);
}

abstract class XML implements IXML {
	// RETURNS: the length of content in this document
	abstract public int contentLength();
	
	// RETURNS: the maximum number of tag nesting in a document
	abstract public int maxNest();
	
	// RETURNS: true iff this document has the given tag
	abstract public boolean hasTag(Tag tag);
	
	// RETURNS: true iff this document has the given attribute
	abstract public boolean hasAttribute(String attr);
	
	// RETURNS: true iff this document hat the given attribute in the given tag
	abstract public boolean hasAttributeInTag(String attr, Tag tag);
	
	// RETURNS: a XML like this one but with all volume attribute set to 0db
	public IXML mute() {
		return this.updateAttribute("volume", "0db");
	}

	// RETURNS: a XML like this one but with all attribute equals to attr set
	// to the given value
	abstract public IXML updateAttribute(String attr, String value);
	
	// RETURNS: a XML like this one but with all volume attribute in yell 
	// tag set to 0db
	public IXML muteYell() {
		return this.setAttrInTag(new Tag("yell"), "volume", "0db");
	}
	
	// RETURNS: a XML like this one but with all attribute that equals to attr
	// and occurs in an tag equals to the given tag set to the given value
	abstract public IXML setAttrInTag(Tag tag, String attr, String value);	

	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	abstract public String renderAsString();
	
	// RETURNS: a XML like this one but change all Tag equals to the given src 
	// tag to the dst tag
	abstract public IXML renameTag(Tag src, Tag dst);
}

//REPRESENTS: an empty list of XML fragment
class MTLoXMLFragment extends XML {
	public MTLoXMLFragment() {}
	
	// RETURNS: the length of content in this document
	public int contentLength() {
		return 0;
	}
	
	// RETURNS: the maximum number of tag nesting in a document
	public int maxNest() {
		return 0;
	}
	
	// RETURNS: true iff this document has the given tag
	public boolean hasTag(Tag tag) {
		return false;
	}
	
	// RETURNS: true iff this document has the given attribute
	public boolean hasAttribute(String attr) {
		return false;
	}
	
	// RETURNS: true iff this document has the given attribute in the given tag
	public boolean hasAttributeInTag(String attr, Tag tag) {
		return false;
	}
	
	public String toString() {
		return "";
	}
	
	// RETURNS: a XML like this one but with all attribute equals to attr set
	// to the given value
	public IXML updateAttribute(String attr, String value) {
		return this;
	}
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	public String renderAsString() {
		return "";
	}
	
	// RETURNS: a XML like this one but with all attribute that equals to attr
	// and occurs in an tag equals to the given tag set to the given value
	public IXML setAttrInTag(Tag tag, String attr, String value) {
		return this;
	}
	
	// RETURNS: a XML like this one but change all Tag equals to the given src 
	// tag to the dst tag
	public IXML renameTag(Tag src, Tag dst) {
		return this;
	}
}

//REPRESENTS: a non-empty list of XML fragments
class ConsLoXMLFragment extends XML {
	IXMLFragment fst;
	IXML rst;
	
	public ConsLoXMLFragment(IXMLFragment fst, IXML rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: the length of content in this document
	public int contentLength() {
		return this.fst.contentLength() + this.rst.contentLength();
	}
	
	// RETURNS: the maximum number of tag nesting in a document
	public int maxNest() {
		return Math.max(this.fst.maxNest(), this.rst.maxNest());
	}
	
	// RETURNS: true iff this document has the given tag
	public boolean hasTag(Tag tag) {
		return this.fst.hasTag(tag) || this.rst.hasTag(tag);
	}
	
	// RETURNS: true iff this document has the given attribute
	public boolean hasAttribute(String attr) {
		return this.fst.hasAttribute(attr) || this.rst.hasAttribute(attr);
	}
	
	// RETURNS: true iff this document has the given attribute in the given tag
	public boolean hasAttributeInTag(String attr, Tag tag) {
		return this.fst.hasAttributeInTag(attr, tag) ||
				this.rst.hasAttributeInTag(attr, tag);
	}
	
	public String toString() {
		return this.fst.toString() + this.rst.toString();
	}
	
	// RETURNS: a XML like this one but with all attribute equals to attr set
	// to the given value
	public IXML updateAttribute(String attr, String value) {
		return new ConsLoXMLFragment(this.fst.setAllAttribute(attr, value),
									 this.rst.updateAttribute(attr, value));
	}
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	public String renderAsString() {
		return this.fst.renderAsString() + this.rst.renderAsString();
	}
	
	// RETURNS: a XML like this one but with all attribute that equals to attr
	// and occurs in an tag equals to the given tag set to the given value
	public IXML setAttrInTag(Tag tag, String attr, String value) {
		return new ConsLoXMLFragment(this.fst.setAttrInTag(tag, attr, value),
				this.rst.setAttrInTag(tag, attr, value));
	}
	
	// RETURNS: a XML like this one but change all Tag equals to the given src 
	// tag to the dst tag
	public IXML renameTag(Tag src, Tag dst) {
		return 
			new ConsLoXMLFragment(this.fst.renameTag(src, dst),
					this.rst.renameTag(src, dst));
	}
}


// REPRESENTS: a XML fragment
interface IXMLFragment{
	// RETURNS: the length of content in this XML fragment
	int contentLength();
	
	// RETURNS: the maximum number of tag nesting this XML fragment
	int maxNest();
	
	// RETURNS: true iff this XML fragment has the given tag
	boolean hasTag(Tag tag);
	
	// RETURNS: true iff this XML fragment has the given attribute
	boolean hasAttribute(String attr);
	
	// RETURNS: true iff this XML fragment has the given attribute in the given tag
	boolean hasAttributeInTag(String attr, Tag tag);
	
	// RETURNS: a XML fragment like this one but with all attribute equals to 
	// attr set to the given value
	IXMLFragment setAllAttribute(String attr, String value);
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	String renderAsString();
	
	// RETURNS: a XML fragment like this one but with all attribute that equals
	// to attr and occurs in an tag equals to the given tag set to the given value
	IXMLFragment setAttrInTag(Tag tag, String attr, String value);
	
	// RETURNS: a XML fragment like this one but change all Tag equals to the 
	// given src tag to the dst tag
	IXMLFragment renameTag(Tag src, Tag dst);
}

// REPRESENTS: a non tagged XML fragment
class NoTaggedXMLFragment implements IXMLFragment {
	String planContent; // plan content
	
	public NoTaggedXMLFragment(String planContent) {
		this.planContent = planContent;
	}
	
	// RETURNS: the length of content in this XML fragment
	public int contentLength() {
		return this.planContent.length();
	}
	
	// RETURNS: the maximum number of tag nesting this XML fragment
	public int maxNest() {
		return 0;
	}
	
	// RETURNS: true iff this XML fragment has the given tag
	public boolean hasTag(Tag tag) {
		return false;
	}
	
	// RETURNS: true iff this XML fragment has the given attribute
	public boolean hasAttribute(String attr) {
		return false;
	}
	
	// RETURNS: true iff this XML fragment has the given attribute in the given tag
	public boolean hasAttributeInTag(String attr, Tag tag) {
		return false;
	}
	
	public String toString() {
		return this.planContent;
	}
	
	// RETURNS: a XML fragment like this one but with all attribute equals to 
	// attr set to the given value
	public IXMLFragment setAllAttribute(String attr, String value) {
		return this;
	}
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	public String renderAsString() {
		return this.planContent;
	}
	
	// RETURNS: a XML fragment like this one but with all attribute that equals
	// to attr and occurs in an tag equals to the given tag set to the given value
	public IXMLFragment setAttrInTag(Tag tag, String attr, String value) {
		return this;
	}
	
	// RETURNS: a XML fragment like this one but change all Tag equals to the 
	// given src tag to the dst tag
	public IXMLFragment renameTag(Tag src, Tag dst) {
		return this;
	}
}

// REPRESETNS: a tagged XML fragment
class TaggedXMLFragment implements IXMLFragment {
	Tag tag;
	ILoAttributeValuePair attributesValuePairs;
	IXML xml;
	
	TaggedXMLFragment(Tag tag, ILoAttributeValuePair attributesAttributeValuePairs, 
				IXML xml) {
		this.tag = tag;
		this.attributesValuePairs = attributesAttributeValuePairs;
		this.xml = xml;
	}
	
	// RETURNS: the length of content in this XML fragment
	public int contentLength() {
		return this.xml.contentLength();
	}
	
	// RETURNS: the maximum number of tag nesting this XML fragment
	public int maxNest() {
		return 1 + this.xml.maxNest();
	}
	
	// RETURNS: true iff this XML fragment has the given tag
	public boolean hasTag(Tag tag) {
		return this.tag.equals(tag) || this.xml.hasTag(tag);
	}
	
	// RETURNS: true iff this XML fragment has the given attribute
	public boolean hasAttribute(String attr) {
		return this.attributesValuePairs.hasAttribute(attr) ||
				this.xml.hasAttribute(attr);
	}
	
	// RETURNS: true iff this XML fragment has the given attribute in the given tag
	public boolean hasAttributeInTag(String attr, Tag tag) {
		if(this.tag.equals(tag)) {
			return this.attributesValuePairs.hasAttribute(attr) ||
					this.xml.hasAttributeInTag(attr, tag);
		}
		else {
			return this.xml.hasAttributeInTag(attr, tag);
		}		
	}
	
	public String toString() {
		return "<" + this.tag.toString() + 
				this.attributesValuePairs.toString() + ">" + 
				this.xml.toString() +
				"<" + this.tag.toString() + "/>";
	}
	
	// RETURNS: a XML fragment like this one but with all attribute equals to 
	// attr set to the given value
	public IXMLFragment setAllAttribute(String attr, String value) {
		return 
			new TaggedXMLFragment(this.tag, 
					this.attributesValuePairs.setAllAttribute(attr, value),
					this.xml.updateAttribute(attr, value));
	}
	
	// RETURNS: a String converted from this XML with all of the tags and 
	// attributes removed 
	public String renderAsString() {
		return this.xml.renderAsString();
	}
	
	
	// RETURNS: a XML fragment like this one but with all attribute that equals
	// to attr and occurs in an tag equals to the given tag set to the given value
	public IXMLFragment setAttrInTag(Tag tag, String attr, String value) {
		if(this.tag.equals(tag)) {
			return new TaggedXMLFragment(this.tag, 
					this.attributesValuePairs.setAllAttribute(attr, value),
					this.xml.setAttrInTag(tag, attr, value));
		}
		return this;
	}
	
	// RETURNS: a XML fragment like this one but change all Tag equals to the 
	// given src tag to the dst tag
	public IXMLFragment renameTag(Tag src, Tag dst) {
		if(this.tag.equals(src)) {
			return 
				new TaggedXMLFragment(dst, this.attributesValuePairs, this.xml);
		}
		else {
			return this;
		}
	}
}

class Tag {
	String tag;
	
	Tag(String tag) {
		this.tag = tag;
	}
	
	// RETURNS: true iff this tag equals to that tag
	public boolean equals(Tag that) {
		return this.tag.equals(that.tag);
	}
	
	public String toString() {
		return this.tag;
	}
}

interface ILoAttributeValuePair {
	// RETURNS: true iff this list  has the given attribute
	boolean hasAttribute(String attr);
	
	// RETURNS: a list of attribute value pair like this one but with all 
	// attribute equals to attr set to the given value
	ILoAttributeValuePair setAllAttribute(String attr, String value);
}
class MTLoAttributeValuePair implements ILoAttributeValuePair {
	public MTLoAttributeValuePair() {}
	
	// RETURNS: true iff this list  has the given attribute
	public boolean hasAttribute(String attr) {
		return false;
	}
	
	public String toString() {
		return "";
	}
	
	// RETURNS: a list of attribute value pair like this one but with all 
	// attribute equals to attr set to the given value
	public ILoAttributeValuePair setAllAttribute(String attr, String value) {
		return this;
	}
}
class ConsLoAttributeValuePair implements ILoAttributeValuePair {
	AttributeValuePair fst;
	ILoAttributeValuePair rst;
	
	public ConsLoAttributeValuePair(AttributeValuePair fst, ILoAttributeValuePair rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: true iff this list  has the given attribute
	public boolean hasAttribute(String attr) {
		return this.fst.attributeEquals(attr) ||
				this.rst.hasAttribute(attr);
	}
	
	public String toString() {
		return " " + this.fst.toString() + this.rst.toString();
	}
	
	// RETURNS: a list of attribute value pair like this one but with all 
	// attribute equals to attr set to the given value
	public ILoAttributeValuePair setAllAttribute(String attr, String value) {
		return 
			new ConsLoAttributeValuePair(
					this.fst.setAttribute(attr, value), 
					this.rst.setAllAttribute(attr, value));
	}
}

 
class AttributeValuePair {
	String attribute;
	String value;
	
	public AttributeValuePair(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}
	
	// RETURNS: true iff the attribute of this pair equals to the given pair
	public boolean attributeEquals(String attr) {
		return this.attribute.equals(attr);
	}
	
	public String toString() {
		return this.attribute + "=\"" + this.value + "\"";
	}
	
	// RETURNS: a pair like this one but with its value set to the given value 
	// if the attribute of this pair equals to the given one, other return a
	// same pair
	public AttributeValuePair setAttribute(String attr, String value) {
		if(this.attribute.equals(attr)) {
			return new AttributeValuePair(attr, value);
		}
		else {
			return this;
		}
	}
}

class XMLExamples {
	IXMLFragment xmlf1 = new NoTaggedXMLFragment("I am XML!");
	IXML mtLoXML = new MTLoXMLFragment();
	IXML xml1 = new ConsLoXMLFragment(xmlf1, mtLoXML);
	
	IXMLFragment xmlf2 = new NoTaggedXMLFragment("I am ");
	IXMLFragment xmlf3 = new NoTaggedXMLFragment("XML");
	IXML xml2 = new ConsLoXMLFragment(xmlf3, mtLoXML);
	ILoAttributeValuePair mtAttributeValuePair = new MTLoAttributeValuePair();
	IXMLFragment xmlf4 = 
			new TaggedXMLFragment(new Tag("yell"), mtAttributeValuePair, xml2);
	IXMLFragment xmlf5 = new NoTaggedXMLFragment("!");
	IXML xml3 = new ConsLoXMLFragment(xmlf2, 
							new ConsLoXMLFragment(xmlf4, 
									new ConsLoXMLFragment(xmlf5, mtLoXML)));
	
	IXMLFragment x = new NoTaggedXMLFragment("X");
	IXMLFragment ml = new NoTaggedXMLFragment("ML");
	Tag yell = new Tag("yell");
	Tag italic = new Tag("italic");
	IXML xml4 = new ConsLoXMLFragment(x, mtLoXML);  // X
	IXMLFragment xmlf6 = new TaggedXMLFragment(italic, mtAttributeValuePair, xml4);
	// <italic>X</italic>ML
	IXML xml5 = new ConsLoXMLFragment(xmlf6, 
							new ConsLoXMLFragment(ml, mtLoXML)); 
	AttributeValuePair attriValue1 = new AttributeValuePair("volume", "30db");
	ILoAttributeValuePair mtLoAV = new MTLoAttributeValuePair();
	ILoAttributeValuePair loAV = new ConsLoAttributeValuePair(attriValue1, mtLoAV);
	IXMLFragment xmlf7 = new TaggedXMLFragment(yell, loAV, xml5);
	IXML xml6 = new ConsLoXMLFragment(xmlf2,
							new ConsLoXMLFragment(xmlf7,
									new ConsLoXMLFragment(xmlf5, mtLoXML)));

	// tests for contentLength()
	boolean testContentLength(Tester t) {
		return
		t.checkExpect(this.mtLoXML.contentLength(), 0) &&
		t.checkExpect(this.xml1.contentLength(), "I am XML!".length()) &&
		t.checkExpect(this.xml3.contentLength(), "I am XML!".length()) &&
		t.checkExpect(this.xml6.contentLength(), "I am XML!".length());
	}
	
	// tests for maxNest()
	boolean testMaxNest(Tester t) {
		return
		t.checkExpect(this.mtLoXML.maxNest(), 0) &&
		t.checkExpect(this.xml1.maxNest(), 0) &&
		t.checkExpect(this.xml3.maxNest(), 1) &&
		t.checkExpect(this.xml6.maxNest(), 2);
	}
	
	// tests for hasTag()
	boolean testHasTag(Tester t) {
		return
		t.checkExpect(this.mtLoXML.hasTag(this.italic), false) &&
		t.checkExpect(this.xml1.hasTag(this.italic), false) &&
		t.checkExpect(this.xml3.hasTag(this.italic), false) &&
		t.checkExpect(this.xml6.hasTag(this.italic), true);
	}
	
	// tests for hasAttribute()
	boolean testHasAttribute(Tester t) {
		String volume = new String("volume");
		return
		t.checkExpect(this.mtLoXML.hasAttribute(volume), false) &&
		t.checkExpect(this.xml1.hasAttribute(volume), false) &&
		t.checkExpect(this.xml3.hasAttribute(volume), false) &&
		t.checkExpect(this.xml6.hasAttribute(volume), true);
	}
	
	// tests for hasAttributeInTag()
	boolean testHasAttributeInTag(Tester t) {
		String volume = new String("volume");
//		System.out.println(this.xml1);
//		System.out.println(this.xml3);
//		System.out.println(this.xml6);
		return
		t.checkExpect(this.mtLoXML.hasAttributeInTag(volume, this.yell), false) &&
		t.checkExpect(this.xml1.hasAttributeInTag(volume, this.yell), false) &&
		t.checkExpect(this.xml3.hasAttributeInTag(volume, this.yell), false) &&
		t.checkExpect(this.xml6.hasAttributeInTag(volume, this.yell), true);
	}
	
	// tests for mute()
	boolean testMute(Tester t) {
		AttributeValuePair volume0 = new AttributeValuePair("volume", "0db");
		ILoAttributeValuePair loAV1 = 
				new ConsLoAttributeValuePair(volume0, this.mtLoAV);
		IXMLFragment xmlf7Muted = 
				new TaggedXMLFragment(this.yell, loAV1, this.xml5);
		IXML xml6Muted = new ConsLoXMLFragment(this.xmlf2,
							new ConsLoXMLFragment(xmlf7Muted, 
								new ConsLoXMLFragment(this.xmlf5, this.mtLoXML)));	
		return
		t.checkExpect(this.mtLoXML.mute(), this.mtLoXML) &&
		t.checkExpect(this.xml1.mute(), this.xml1) &&
		t.checkExpect(this.xml3.mute(), this.xml3) &&
		t.checkExpect(this.xml6.mute(), xml6Muted);
	}
	
	// tests for method renderAsString()
	boolean testRenderAsString(Tester t) {
		return 
		t.checkExpect(mtLoXML.renderAsString(), "") &&
		t.checkExpect(xml1.renderAsString(), "I am XML!") &&
		t.checkExpect(xml3.renderAsString(), "I am XML!") &&
		t.checkExpect(xml6.renderAsString(), "I am XML!");
	}
	
	// tests for method muteYell()
	boolean testMuteYell(Tester t) {
		AttributeValuePair volume0 = new AttributeValuePair("volume", "0db");
		ILoAttributeValuePair loAV1 = 
				new ConsLoAttributeValuePair(volume0, this.mtLoAV);
		IXMLFragment xmlf7Muted = 
				new TaggedXMLFragment(this.yell, loAV1, this.xml5);
		IXML xml6Muted = new ConsLoXMLFragment(this.xmlf2,
							new ConsLoXMLFragment(xmlf7Muted, 
								new ConsLoXMLFragment(this.xmlf5, this.mtLoXML)));	
		return
		t.checkExpect(this.mtLoXML.muteYell(), this.mtLoXML) &&
		t.checkExpect(this.xml1.muteYell(), this.xml1) &&
		t.checkExpect(this.xml3.muteYell(), this.xml3) &&
		t.checkExpect(this.xml6.muteYell(), xml6Muted);
				
	}
	
	// tests for method renameTag()
	boolean testRenameTag(Tester t) {
		Tag src = new Tag("yell");
		Tag dst = new Tag("shout");
		IXMLFragment xmlf4Shout = 
				new TaggedXMLFragment(dst, 
						this.mtAttributeValuePair, 
						this.xml2);
		IXML xml3Shout = new ConsLoXMLFragment(this.xmlf2, 
				new ConsLoXMLFragment(xmlf4Shout, 
						new ConsLoXMLFragment(this.xmlf5, this.mtLoXML)));

		IXMLFragment xmlf7Shout = 
				new TaggedXMLFragment(dst, this.loAV, this.xml5);
		IXML xml6Shout = new ConsLoXMLFragment(this.xmlf2,
				new ConsLoXMLFragment(xmlf7Shout,
						new ConsLoXMLFragment(this.xmlf5, this.mtLoXML)));
		
		return
		t.checkExpect(this.mtLoXML.renameTag(src, dst), this.mtLoXML) &&
		t.checkExpect(this.xml1.renameTag(src, dst), this.xml1) &&
		t.checkExpect(this.xml3.renameTag(src, dst), xml3Shout) &&
		t.checkExpect(this.xml6.renameTag(src, dst), xml6Shout);
	}
}