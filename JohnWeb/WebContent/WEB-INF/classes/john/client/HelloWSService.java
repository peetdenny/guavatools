package john.client;

/**
 * Generated class, do not edit.
 *
 * This service interface was generated by weblogic
 * webservice stub gen on Wed Aug 08 10:20:38 BST 2007  
 */

public interface HelloWSService extends javax.xml.rpc.Service {

  weblogic.wsee.context.WebServiceContext context();

  weblogic.wsee.context.WebServiceContext joinContext()
       throws weblogic.wsee.context.ContextNotFoundException;
 
     

     //***********************************
     // Port Name: HelloWSSoapPort  
     // Port Type: HelloWS   
     //***********************************
     
  /**
   * returns HelloWSSoapPort port in this service 
   */
  john.client.HelloWS getHelloWSSoapPort() throws javax.xml.rpc.ServiceException;

  /**
   * @deprecated Use getHelloWSSoapPort(byte[] username, byte[] password);
   */
  john.client.HelloWS getHelloWSSoapPort(String username, String password) throws javax.xml.rpc.ServiceException;

  john.client.HelloWS getHelloWSSoapPort(byte[] username, byte[] password) throws javax.xml.rpc.ServiceException;
  
    
  }