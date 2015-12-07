package introsde.assignment.soap;

import introsde.document.model.LifeStatus;
import introsde.document.model.Person;
import introsde.document.model.HealthMeasureHistory;
import introsde.document.model.MeasureDefinition;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.People",
	serviceName="PeopleService")
public class PeopleImpl implements People {

    //Method #1:
	@Override
	public List<Person> getPeople() {
		return Person.getAll();
	}

    
     //Method #2:
	@Override
    //public Person readPerson(int id) {
	public Person readPerson(Long id) {
		System.out.println("---> Reading Person by id = "+id);
		Person p = Person.getPersonById((int)(long) id);
		return p;
	}
    
     //Method #3:
    @Override
	public Person updatePerson(Person person) {
		Person.updatePerson(person);
		return person;
	}
    
     //Method #4:
	@Override
	public Person createPerson(Person person) {
		Person finalperson= Person.savePerson(person);
		return finalperson;
	}

    //Method #5:
	@Override
	public int deletePerson(Long id) {
    //public Long deletePerson(int id) {
		Person p = Person.getPersonById((int)(long) id);
		if (p!=null) {
			Person.removePerson(p);
			return 0;
		} else {
			return 1;
		}
	}
     //Method #6:
    @Override
    //public  List<HealthMeasureHistory> readPersonHistory(int id,String measureType) {
	public  List<HealthMeasureHistory> readPersonHistory(Long id,String measureType) {
        
        int idPerson=(int)(long)id;
        List<HealthMeasureHistory> list= HealthMeasureHistory.getAll();
        List<HealthMeasureHistory> resultlist =new ArrayList<HealthMeasureHistory>();
        List<HealthMeasureHistory> finallist =new ArrayList<HealthMeasureHistory>();
        //System.out.println("FLAAAAG");
        
        HealthMeasureHistory healthtemp=null;
        for(int i=0;i<list.size();i++){
            healthtemp=list.get(i);
            if(healthtemp.getPerson().getIdPerson()==idPerson){
                System.out.println("Trovato inserisco nella lista di ritorno");
                resultlist.add(healthtemp);
                System.out.println("contiene: " +resultlist.size());
            }
            System.out.println("looop");
        }
        System.out.println("measuretype: "+measureType);
        healthtemp=null;
        for(int i=0;i<resultlist.size();i++){
            System.out.println("FLAAAAG");
            healthtemp=resultlist.get(i);
            System.out.println("===============================================================");
            System.out.println("IDMEASUREHISTORY:="+healthtemp.getIdMeasureHistory());
            
            MeasureDefinition measure =healthtemp.getMeasureDefinition();
            
            System.out.println("IDMEASURTYPE:="+measure.getIdMeasureDef());
            System.out.println("IDMEASURTYPE:="+measureType);
            System.out.println("IDPERSON:="+healthtemp.getPerson().getIdPerson());
            System.out.println("VALUE:="+healthtemp.getValue());
            System.out.println("===============================================================");
            if(measure.getMeasureName().equals(measureType)){
                
                System.out.println("OK inserita");
                finallist.add(healthtemp);
            }
            
        }
        
        return finallist;
        
        
        
        
		/*List<HealthMeasureHistory> l= HealthMeasureHistory.getAll();
        List<HealthMeasureHistory> lf= new ArrayList<HealthMeasureHistory>();
        List<HealthMeasureHistory> lsf= new ArrayList<HealthMeasureHistory>();
        System.out.println("looking for history");
        for (HealthMeasureHistory hl : l) {
            if(hl.getPerson()!=null){
            
                Person p=hl.getPerson();
                if (p.getIdPerson()==id){
                    lf.add(hl);
                    System.out.println("found history adding");
                
                }
            }
        }
        System.out.println("second loop");
        for (HealthMeasureHistory hl2 : lf) {
            MeasureDefinition measure=hl2.getMeasureDefinition();
            //System.out.println("measureneme found: "+measure.getMeasureName());
             System.out.println("measureneme look for: "+measureType);
            String name=measure.getMeasureName();
            
            if (name.equals(measureType)){
                System.out.println("found history per type adding");
                lsf.add(hl2);
                
            }
        }
        //magari mettere if vuota print
        return lsf;
*/
        
      	}
    
     //Method #7:
    @Override
	public  List<MeasureDefinition> readMeasureTypes() {
		return MeasureDefinition.getAll();
	}

     //Method #8:
    @Override
    //public  HealthMeasureHistory readPersonMeasure(int id,String measureType,int mid) {
	public  HealthMeasureHistory readPersonMeasure(Long id,String measureType,Long mid) {
		List<HealthMeasureHistory> l= HealthMeasureHistory.getAll();
        List<HealthMeasureHistory> lf= new ArrayList<HealthMeasureHistory>();
        List<HealthMeasureHistory> lsf= new ArrayList<HealthMeasureHistory>();
    
        for (HealthMeasureHistory hl : l) {
            Person p=hl.getPerson();
            if (p.getIdPerson()==id){
                lf.add(hl);
                
            }
        }
        for (HealthMeasureHistory hl : lf) {
            MeasureDefinition measure=hl.getMeasureDefinition();
            if (measure.getMeasureName().equals(measureType)){
                lsf.add(hl);
                
            }
        }
        for (HealthMeasureHistory hl : lsf) {
            if (hl.getIdMeasureHistory()==mid){
                return hl;
            }
        }
        return null;
        //mettere controlli se vuota

        
	}
     //Method #9:
    @Override
    //public  LifeStatus savePersonMeasure(int id,LifeStatus m) {
	public  LifeStatus savePersonMeasure(Long id,LifeStatus m) {
        Person person=  Person.getPersonById((int)(long) id);
        List<LifeStatus> lifestatus= person.getLifeStatus();
        MeasureDefinition measure=null;
         MeasureDefinition measure2=null;
        
        for(LifeStatus lf : lifestatus){
            measure=lf.getMeasureDefinition();
            measure2=m.getMeasureDefinition();
            
            if(person.getIdPerson()==id){//redundant
                if(measure.getIdMeasureDef()==measure2.getIdMeasureDef()){
                    LifeStatus.removeLifeStatus(lf);
                    
                    HealthMeasureHistory hlm =new HealthMeasureHistory();
                    hlm.setTimestamp(new Date(System.currentTimeMillis()));
                    hlm.setValue(m.getValue());
                    hlm.setPerson(person);
                    hlm.setMeasureDefinition(measure);
                    //hlm.setIdMeasureDefinition(measure.getIdMeasureDef());
                    
                    HealthMeasureHistory.saveHealthMeasureHistory(hlm);
                    
                    LifeStatus.saveLifeStatus(m);
                    
                }
            }
            
            
        }
        
        return m;
        
	}
     //Method #10:
    @Override
    //public HealthMeasureHistory updateHealthMeasureHistory(int id, HealthMeasureHistory m){
    public HealthMeasureHistory updateHealthMeasureHistory( Long id, HealthMeasureHistory m){
        List <HealthMeasureHistory> hl=new ArrayList<HealthMeasureHistory>();
        hl=HealthMeasureHistory.getAll();
        Person p=null;
        
        for(HealthMeasureHistory hm : hl){
            p=hm.getPerson();
            if(p.getIdPerson()==id){
                if(hm.getIdMeasureHistory()==m.getIdMeasureHistory()){
                    hm.setValue(m.getValue());
                    HealthMeasureHistory.updateHealthMeasureHistory(hm);
                    return hm;
               }
            }
            
        }
        return null;
        
    }

    
   

}
