package introsde.assignment.soap;
import introsde.document.model.LifeStatus;
import introsde.document.model.Person;
import introsde.document.model.HealthMeasureHistory;
import introsde.document.model.MeasureDefinition;



import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
    //Method #1:
    @WebMethod(operationName="readPersonList")
    @WebResult(name="persons")
    public List<Person> getPeople();

    //Method #2:
    @WebMethod(operationName="readPerson")
    @WebResult(name="person")
    //public Person readPerson(@WebParam(name="personId") int id);
    public Person readPerson(@WebParam(name="personId") Long id);
 
    //Method #3:
    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId")
    public Person updatePerson(@WebParam(name="person") Person person);
    
    //Method #4:
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public Person createPerson(@WebParam(name="person") Person person);
 
    //Method #5:
    @WebMethod(operationName="deletePerson")
    @WebResult(name="op")
    //public Long deletePerson(@WebParam(name="personId") int id);
    public int deletePerson(@WebParam(name="personId") Long id);
    
    //Method #6:
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="HealthMeasureHistory")
    //public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") int id, @WebParam(name="measureType") String measureType);
    public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType);
    
    //Method #7:
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="MeasureDefinition")
    public List<MeasureDefinition> readMeasureTypes();
    
    //Method #8:
    @WebMethod(operationName="readPersonMeasure")
    @WebResult(name="HealthMeasureHistory")
    //public HealthMeasureHistory readPersonMeasure(@WebParam(name="personId") int id, @WebParam(name="measureType") String measureType,@WebParam(name="mid") int mid);

    public HealthMeasureHistory readPersonMeasure(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType,@WebParam(name="mid") Long mid);

    
    //Method #9:
    @WebMethod(operationName="savePersonMeasure")
    @WebResult(name="LifeStatus")
    //public LifeStatus savePersonMeasure(@WebParam(name="personId") int id, @WebParam(name="lifestatus") LifeStatus m);
    public LifeStatus savePersonMeasure(@WebParam(name="personId") Long id, @WebParam(name="lifestatus") LifeStatus m);
    
    

    //Method #10:
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="HealthMeasureHistory")
     //public HealthMeasureHistory updateHealthMeasureHistory(@WebParam(name="personId") int id, @WebParam(name="lifestatus") HealthMeasureHistory m);
     public HealthMeasureHistory updateHealthMeasureHistory(@WebParam(name="personId") Long id, @WebParam(name="lifestatus") HealthMeasureHistory m);
}

/*
 // The WebResult annotation specifies that the name of the result of the
 // operation in the generated WSDL is "IntegerOutput", rather than the
 // default name "return".   The WebParam annotation specifies that the input
 // parameter name in the WSDL file is "IntegerInput" rather than the Java
 // name of the parameter, "input".
 @WebMethod()
 @WebResult(name="IntegerOutput",
 targetNamespace="http://example.org/complex")*/