
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bantotal.dlya.com.uy.btsoa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bantotal.dlya.com.uy.btsoa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticateExecute }
     * 
     */
    public AuthenticateExecute createAuthenticateExecute() {
        return new AuthenticateExecute();
    }

    /**
     * Create an instance of {@link SBTInReq }
     * 
     */
    public SBTInReq createSBTInReq() {
        return new SBTInReq();
    }

    /**
     * Create an instance of {@link AuthenticateExecuteResponse }
     * 
     */
    public AuthenticateExecuteResponse createAuthenticateExecuteResponse() {
        return new AuthenticateExecuteResponse();
    }

    /**
     * Create an instance of {@link SBTErroresNegocio }
     * 
     */
    public SBTErroresNegocio createSBTErroresNegocio() {
        return new SBTErroresNegocio();
    }

    /**
     * Create an instance of {@link SBTOutReq }
     * 
     */
    public SBTOutReq createSBTOutReq() {
        return new SBTOutReq();
    }

    /**
     * Create an instance of {@link SBTErrorNegocio }
     * 
     */
    public SBTErrorNegocio createSBTErrorNegocio() {
        return new SBTErrorNegocio();
    }

}
