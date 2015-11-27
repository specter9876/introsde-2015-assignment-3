package introsde.document.endpoint;
import introsde.document.ws.PeopleImpl;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "6902";
	public static String BASE_URL = "/ws/people";
	
	public static String getEndpointURL() {
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) {
		String endpointUrl = getEndpointURL();
		System.out.println("Starting People Service...");
		System.out.println("--> Published at = "+endpointUrl);
        ///mmmm mettere più cose qui per accedere ad altre resourches
		Endpoint.publish(endpointUrl, new PeopleImpl());
    }
}

/*
 static Endpoint	publish(String address, Object implementor)
 Creates and publishes an endpoint for the specified implementor object at the given address.
 
 A Web service endpoint.
 Endpoints are created using the static methods defined in this class. An endpoint is always tied to one Binding and one implementor, both set at endpoint creation time.
 
 An endpoint is either in a published or an unpublished state. The publish methods can be used to start publishing an endpoint, at which point it starts accepting incoming requests. Conversely, the stop method can be used to stop accepting incoming requests and take the endpoint down. Once stopped, an endpoint cannot be published again.
 
 An Executor may be set on the endpoint in order to gain better control over the threads used to dispatch incoming requests. For instance, thread pooling with certain parameters can be enabled by creating a ThreadPoolExecutor and registering it with the endpoint.
 
 Handler chains can be set using the contained Binding.
 
 An endpoint may have a list of metadata documents, such as WSDL and XMLSchema documents, bound to it. At publishing time, the JAX-WS implementation will try to reuse as much of that metadata as possible instead of generating new ones based on the annotations present on the implementor.*/