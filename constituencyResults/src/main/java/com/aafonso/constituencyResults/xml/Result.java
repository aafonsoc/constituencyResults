//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.30 at 12:24:32 AM WEST 
//


package com.aafonso.constituencyResults.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}partyCode"/>
 *         &lt;element ref="{}votes"/>
 *         &lt;element ref="{}share"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "partyCode",
    "votes",
    "share"
})
@XmlRootElement(name = "result")
public class Result
    implements Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String partyCode;
    @XmlElement(required = true)
    protected String votes;
    @XmlElement(required = true)
    protected String share;

    /**
     * Gets the value of the partyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyCode() {
        return partyCode;
    }

    /**
     * Sets the value of the partyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyCode(String value) {
        this.partyCode = value;
    }

    /**
     * Gets the value of the votes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVotes() {
        return votes;
    }

    /**
     * Sets the value of the votes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVotes(String value) {
        this.votes = value;
    }

    /**
     * Gets the value of the share property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShare() {
        return share;
    }

    /**
     * Sets the value of the share property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShare(String value) {
        this.share = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String thePartyCode;
            thePartyCode = this.getPartyCode();
            strategy.appendField(locator, this, "partyCode", buffer, thePartyCode);
        }
        {
            String theVotes;
            theVotes = this.getVotes();
            strategy.appendField(locator, this, "votes", buffer, theVotes);
        }
        {
            String theShare;
            theShare = this.getShare();
            strategy.appendField(locator, this, "share", buffer, theShare);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Result)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Result that = ((Result) object);
        {
            String lhsPartyCode;
            lhsPartyCode = this.getPartyCode();
            String rhsPartyCode;
            rhsPartyCode = that.getPartyCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "partyCode", lhsPartyCode), LocatorUtils.property(thatLocator, "partyCode", rhsPartyCode), lhsPartyCode, rhsPartyCode)) {
                return false;
            }
        }
        {
            String lhsVotes;
            lhsVotes = this.getVotes();
            String rhsVotes;
            rhsVotes = that.getVotes();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "votes", lhsVotes), LocatorUtils.property(thatLocator, "votes", rhsVotes), lhsVotes, rhsVotes)) {
                return false;
            }
        }
        {
            String lhsShare;
            lhsShare = this.getShare();
            String rhsShare;
            rhsShare = that.getShare();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "share", lhsShare), LocatorUtils.property(thatLocator, "share", rhsShare), lhsShare, rhsShare)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String thePartyCode;
            thePartyCode = this.getPartyCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "partyCode", thePartyCode), currentHashCode, thePartyCode);
        }
        {
            String theVotes;
            theVotes = this.getVotes();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "votes", theVotes), currentHashCode, theVotes);
        }
        {
            String theShare;
            theShare = this.getShare();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "share", theShare), currentHashCode, theShare);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}