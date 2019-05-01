package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestClass {
    @XmlElement private String id;
    @XmlElement private String name;
    @XmlElement private String amount;

    TestClass(){}

    /*
    TestClass(String id, String name, String amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
    */

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}
