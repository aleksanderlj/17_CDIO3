package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class TestClass {
    //@XmlElement private String id;
    //@XmlElement private String name;
    //@XmlElement private String amount;
    private String id;
    private String name;
    private String amount;
    private String[] ham;

    TestClass(){}


    public TestClass(String id, String name, String amount, String[] ham){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.ham = ham;
    }


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


    public void setHam(String[] ham) {
        this.ham = ham;
    }

    public String[] getHam() {
        return ham;
    }
}
