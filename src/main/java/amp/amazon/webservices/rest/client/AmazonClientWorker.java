/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package amp.amazon.webservices.rest.client;

import static amp.amazon.webservices.rest.client.AmazonClient.documentToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import amp.amazon.webservices.rest.BrowseNodeLookupResponse;
import amp.amazon.webservices.rest.BrowseNodes;
import amp.amazon.webservices.rest.ItemAttributes;
import amp.amazon.webservices.rest.ItemLookupResponse;
import amp.amazon.webservices.rest.ItemSearchResponse;
import amp.amazon.webservices.rest.Items;
import amp.amazon.webservices.rest.SimilarityLookupResponse;
import amp.amazon.webservices.rest.client.AmazonClient.AmazonResponse;
import amp.commerce.service.api.CommerceServiceClientBase;
/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class AmazonClientWorker extends CommerceServiceClientBase
{
    
	 
	/*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    //public String AWS_ACCESS_KEY_ID = "AKIAJUJEQFBKLVSUB4WQ";
	public String AWS_ACCESS_KEY_ID = "AKIAIQU2FHPYCZDYXCIA";
    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    //public String AWS_SECRET_KEY = "1rbtdzN0i+bYKrlS9NQoqVrVrQ+XHWzGNydVBB5z";
	public String AWS_SECRET_KEY = "k9TUoikDHilxh11Ukrxl9PN1h4KxgQIpQ2HozC7w";

    /**
     * Your AWS associate tag
     */
    //public String AWS_ASSOCIATE_TAG = "allmarket05-20"; //canada working
    public String AWS_ASSOCIATE_TAG = "allmarket0b-20";   //us working
  
	public String getAWS_ACCESS_KEY_ID() {
		return AWS_ACCESS_KEY_ID;
	}
	public void setAWS_ACCESS_KEY_ID(String aWS_ACCESS_KEY_ID) {
		AWS_ACCESS_KEY_ID = aWS_ACCESS_KEY_ID;
	}
	public String getAWS_SECRET_KEY() {
		return AWS_SECRET_KEY;
	}
	public void setAWS_SECRET_KEY(String aWS_SECRET_KEY) {
		AWS_SECRET_KEY = aWS_SECRET_KEY;
	}
	public String getAWS_ASSOCIATE_TAG() {
		return AWS_ASSOCIATE_TAG;
	}
	public void setAWS_ASSOCIATE_TAG(String aWS_ASSOCIATE_TAG) {
		AWS_ASSOCIATE_TAG = aWS_ASSOCIATE_TAG;
	}
	public static void main(String[] args) 
    {
    	new AmazonClientWorker().examples();
    }
	
	  /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */
	 //private static final String ITEM_ID = "0545010225";
	
    public void examples() 
	{

        /*
        Properties props;
		try {
			props = new Properties();
			props.load(new FileReader(new File(args[0])));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        
        final String accessKeyId = props.getProperty(AWS_ACCESS_KEY_ID);
        final String secretKey = props.getProperty(AWS_SECRET_KEY);
        final String associateTag = props.getProperty(AWS_ASSOCIATE_TAG);
        */
    	
    	final String accessKeyId  = AWS_ACCESS_KEY_ID;
        final String secretKey    = AWS_SECRET_KEY;
        final String associateTag = AWS_ASSOCIATE_TAG;
        
        /* The helper can sign requests in two forms - map form and string form */
        AmazonClient client = new AmazonClient(accessKeyId, secretKey, associateTag);
 
        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
        {
        	System.out.println("Map form example (lookup):");
        	
	        final Map<String, String> params = new HashMap<String, String>(3);
	        params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        params.put("ItemId", "B0014WYXYW");
	        params.put("ResponseGroup", "Large");
	        //params.put("AssociateTag", associateTag);
	        
	        System.out.println(documentToString(client.getXml(params)));
	        System.out.println("------------------");
        }
        
        {
        	System.out.println("Query String example (lookup):");
        	
        	final String queryString = "Service=AWSECommerceService&AssociateTag=wwwallmarketc-20&Version="+AmazonClient.VERSION+"&Operation=ItemLookup&ResponseGroup=Small&ItemId=" + "B0014WYXYW";
	        
        	System.out.println(documentToString(client.getXml(queryString)));
	        System.out.println("------------------");
        }
        
        
        {
        	System.out.println("Map form example (search):");
        	
        	final Map<String, String> params = new HashMap<String, String>(5);
    		params.put("MerchantId", "All");
    		params.put("SearchIndex", "Grocery");
    		params.put("ResponseGroup", "Large");
    		params.put("BrowseNode", "16318401");
    		//params.put("AssociateTag", associateTag);
    		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_SEARCH);
    		
    		System.out.println(client.get(params).getPrettyXml());
    		System.out.println("------------------");
        }
        
        {
        	System.out.println("Map form example (search, predefined OP):");
        	
           	final Map<String, String> params = new HashMap<String, String>(4);
    		params.put("MerchantId", "All");
    		params.put("SearchIndex", "Grocery");
    		params.put("ResponseGroup", "Large");
    		//params.put("AssociateTag", associateTag);
    		params.put("BrowseNode", "16318401");
  
    		ItemSearchResponse r = client.search().execute(params);
    		
    		if ( r.getItems().size() > 0 )
    		{
		        System.out.println("Total results: " + r.getItems().get(0).getTotalResults());
		        
		        if ( r.getItems().get(0).getItem().size() > 0 )
		        {
		        	ItemAttributes ia = r.getItems().get(0).getItem().get(0).getItemAttributes();
		        
		        	System.out.println("EAN of first item: " + ia.getEAN());
		        }
    		}
	        System.out.println("------------------");
        }
        
        {
        	System.out.println("Map form example (similarity, predefined OP):");
        	
	        final Map<String, String> params = new HashMap<String, String>(2);
	        params.put("ItemId", "B0014WYXYW");
	        params.put("ResponseGroup", "Large");
	        //params.put("AssociateTag", associateTag);
	        
	        System.out.println(client.similarity().execute(params).getItems().size());
	        System.out.println("------------------");
        }
        
        {
        	System.out.println("Map form example (similarity, predefined OP):");
        	
	        final Map<String, String> params = new HashMap<String, String>();
	        params.put("Service", "AWSECommerceService");
	        params.put("AssociateTag", AWS_ASSOCIATE_TAG);
	        //params.put("Operation", "ItemSearch");
	        params.put("SearchIndex", "Books");
	        params.put("Keywords", "harry+potter");
	        params.put("ResponseGroup", "ItemAttributes");
	        params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_SEARCH);
	        
	        System.out.println(client.get(params).getPrettyXml());
	        System.out.println("------------------");
        }
    }
    
    public AmazonClientWorker()
	{
    	super();
    	
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
			StackTraceElement ste = stacktrace[1];
			cMethodName = ste.getMethodName();
		
		}
		catch( Exception e)
		{
			System.out.println(cMethodName + "::Exception:" + e.getMessage());
		}
	}
    
    public AmazonClientWorker(HashMap<String, String> cSystemConfiguration)
	{
		super();
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
			StackTraceElement ste = stacktrace[1];
			cMethodName = ste.getMethodName();
			
			this.setcSystemConfiguration(cSystemConfiguration);
			
			this.setSystemProperties();
		}
		catch( Exception e)
		{
			System.out.println(cMethodName + "::Exception:" + e.getMessage());
		}
	}
    
    public AmazonClientWorker(HashMap<String, String> getcSystemConfiguration,
							  HashMap<String, String> cWorkerConfiguration)
    {
    	super();
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        this.setcSystemConfiguration(getcSystemConfiguration);
	        
	        this.setcWorkerConfiguration(cWorkerConfiguration);
	        
	        this.setSystemProperties();
	        
	        this.setWorkerProperties(this.getClass());
	        
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    	}
	}
    
    
    //-------------------------------------------------------------------
    @Override
    public String itemSearchXml(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	AmazonClient client = null;
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	        
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_SEARCH);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_MERCHANT_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_MERCHANT_ID, AmazonClient.PARAM_VALUE_MERCHANT_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	cResXml = client.get(params).getPrettyXml();
		        System.out.println("------------------");
	        }
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = cMethodName + "::Error:" );
    	}
    }
    //-------------------------------------------------------------------
    public List<Items> itemSearchList(HashMap<String, String> params)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		//String cResXml = "";
		
		AmazonClient client = null;
		
		List<Items> cResponseItems = new LinkedList<Items>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	         String endpoint     = ""; 
	         String accessKeyId  = AWS_ACCESS_KEY_ID;
	         String secretKey    = AWS_SECRET_KEY;
	         String associateTag = AWS_ASSOCIATE_TAG;
	        
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	if ( params.containsKey(AmazonClient.PARAM_ENDPOINT) )
	        	{
	        		endpoint = params.get(AmazonClient.PARAM_ENDPOINT);
	        	}
	        	if ( params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		associateTag = params.get(AmazonClient.PARAM_ASSOCIATE_TAG);
	        	}
	        	if ( params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		accessKeyId = params.get(AmazonClient.PARAM_ACCESS_KEY_ID);
	        	}
	        	if ( params.containsKey(AmazonClient.PARAM_ACCESS_SECRET_KEY_ID) )
	        	{
	        		secretKey = params.get(AmazonClient.PARAM_ACCESS_SECRET_KEY_ID);
	        	}
	        	
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag, endpoint);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_SEARCH);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_MERCHANT_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_MERCHANT_ID, AmazonClient.PARAM_VALUE_MERCHANT_ID);
	        	}
	        	
	        }
	        
	        if ( cRes )
	        {
	        	ItemSearchResponse cItemSearchResponse = client.search().execute(params);
	        	
	        	cResponseItems = cItemSearchResponse.getItems();
	        	//cResXml = client.get(params).getPrettyXml();
		        System.out.println("------------------");
	        }
	        
	        return cResponseItems;
		}
		catch( Exception e)
		{
			System.out.println(cMethodName + "::Exception:" + e.getMessage());
			
			e.printStackTrace();
			
			return new LinkedList<Items>();
		}
	}
	//-------------------------------------------------------------------
    public String itemSearchXml(String cSearchIndex,
    						    String cResponseGroup,
    						    String cSort,
    						    String cKeywords,
    						    String cMerchantId,
    						    String cBrowseNode)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String requestUrl  = null;
    	String cResXml     = "";

    	SignedRequestsHelper helper = null;
    	
    	Map<String, String> params = new HashMap<String, String>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        if ( cRes )
	        {
		        //---Set up the signed requests helper.
		        helper = SignedRequestsHelper.getInstance(
		        			AmazonClient.ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        }
	   
	        //---add parameters
	        if ( cRes )
	        {
		        if ( cKeywords != null && cKeywords.equals("") )
		        {
		        	params.put(AmazonClient.PARAM_KEYWORDS, cKeywords);
		        }
		        
		        if ( cSort != null && cSort.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_SORT, cSort);
		        }
		        
		        if ( cSearchIndex != null && cSearchIndex.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_SEARCH_INDEX, cSearchIndex);
		        }
		        
		        if ( cResponseGroup != null && cResponseGroup.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_RESPONSE_GROUP, cResponseGroup);
		        }
		        
		        if ( cBrowseNode != null && cBrowseNode.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_BROWSE_NODE, cBrowseNode);
		        }
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_SEARCH);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_MERCHANT_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_MERCHANT_ID, AmazonClient.PARAM_VALUE_MERCHANT_ID);
	        	}
	        }
	        
	        requestUrl = helper.sign(params);
	        
	        cResXml = new AmazonResponse(requestUrl).getPrettyXml();
	        System.out.println("------------------");
	        
	       
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = "" );
    	}
    }
    //-------------------------------------------------------------------
    @Override
    public String itemLookupXml(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	AmazonClient client = null;
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	        
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	cResXml = client.get(params).getPrettyXml();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = "" );
    	}
    }
    
    //-------------------------------------------------------------------
	public List<Items> itemLookupList(HashMap<String, String> params)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		//String cResXml = "";
		
		AmazonClient client = null;
		
		List<Items> cResponseItems = new LinkedList<Items>();
		 
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	       
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	ItemLookupResponse cItemLookupResponse = client.lookup().execute(params);
	        	
	        	cResponseItems = cItemLookupResponse.getItems();
	        	//cResXml = client.get(params).getPrettyXml();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResponseItems;
		}
		catch( Exception e)
		{
			System.out.println(cMethodName + "::Exception:" + e.getMessage());
			
			return new LinkedList<Items>();
		}
	}
	//-------------------------------------------------------------------
    public List<Items> itemLookupList(String cItemId, 
    						      String cResponseGroup)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	//String cResXml = "";
    	
    	AmazonClient client = null;
    	
    	Map<String, String> params = new HashMap<String, String>();
    	 
    	List<Items> cResponseItems = new LinkedList<Items>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	       
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---add Parameters
	        if ( cRes )
	        {
	        	System.out.println("Map form example (lookup):");
		       
		        params.put(AmazonClient.PARAM_ITEM_ID, cItemId);
		        params.put(AmazonClient.PARAM_RESPONSE_GROUP, cResponseGroup);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	ItemLookupResponse cItemLookupResponse = client.lookup().execute(params);
	        	
	        	cResponseItems = cItemLookupResponse.getItems();
	        	//cResXml = client.get(params).getPrettyXml();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResponseItems;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return new LinkedList<Items>();
    	}
    }
    //-------------------------------------------------------------------
    @Override
    public String similarityXml(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	AmazonClient client = null;
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	        
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_SIMILARITY_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	cResXml = client.get(params).getPrettyXml();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = "" );
    	}
    }
   
    //-------------------------------------------------------------------
    public List<Items> similarityList(String cItemId, String cResponseGroup)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	AmazonClient client = null;
    	
    	Map<String, String> params = new HashMap<String, String>();
    	
    	List<Items> cResponseItems = new LinkedList<Items>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	       
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---add Parameters
	        if ( cRes )
	        {
	        	System.out.println("Map form example (lookup):");
		       
		        params.put(AmazonClient.PARAM_ITEM_ID, cItemId);
		        params.put(AmazonClient.PARAM_RESPONSE_GROUP, cResponseGroup);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	SimilarityLookupResponse cSimLookupResponse = client.similarity().execute(params);
	        	
	        	cResponseItems = cSimLookupResponse.getItems();
	        	
	        	//cResXml = client.get(params).getPrettyXml();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResponseItems;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return new LinkedList<Items>();
    	}
    }
    //-------------------------------------------------------------------
    public List<Items> similarityList(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	AmazonClient client = null;
    	
    	List<Items> cResponseItems = new LinkedList<Items>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	       
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_ITEM_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	SimilarityLookupResponse cSimLookupResponse = client.similarity().execute(params);
	        	
	        	cResponseItems = cSimLookupResponse.getItems();
	        
		        System.out.println("------------------");
	        }
	        
	        return cResponseItems;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return new LinkedList<Items>();
    	}
    }
    //-------------------------------------------------------------------
    @Override
    public String browseNodeLookupXml(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String requestUrl  = null;
    	String cResXml     = "";

    	SignedRequestsHelper helper = null;
    	
    	
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        if ( null == params )
	        {
	        	System.out.println(cMethodName + "::params is null!");
	        	
	        	cRes = false;
	        }
	        
	        if ( cRes )
	        {
		        //---Set up the signed requests helper.
		        helper = SignedRequestsHelper.getInstance(
		        			AmazonClient.ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        }
	   
	        //---//---check parameters
	        if ( cRes )
	        {
		        
	        	if ( !params.containsKey(AmazonClient.PARAM_RESPONSE_GROUP) )
	        	{
	        		System.out.println(cMethodName + "::params " + 
	        				AmazonClient.PARAM_RESPONSE_GROUP + " is null!");
		        	
		        	cRes = false;
	        	}
	        	
	        	if ( !params.containsKey(AmazonClient.PARAM_BROWSE_NODE_ID) )
	        	{
	        		System.out.println(cMethodName + "::params " + 
	        				AmazonClient.PARAM_BROWSE_NODE_ID + " is null!");
	        		
	        		cRes = false;
	        	}
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_BROWSE_NODE_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        requestUrl = helper.sign(params);
	        
	        cResXml = new AmazonResponse(requestUrl).getPrettyXml();
	        System.out.println("------------------");
	        
	       
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = "" );
    	}
    }
    //-------------------------------------------------------------------
    public String browseNodeLookupXml( String cResponseGroup, String cBrowseNodeId)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String requestUrl  = null;
    	String cResXml     = "";

    	SignedRequestsHelper helper = null;
    	
    	Map<String, String> params = new HashMap<String, String>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        if ( cRes )
	        {
		        //---Set up the signed requests helper.
		        helper = SignedRequestsHelper.getInstance(
		        			AmazonClient.ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        }
	   
	        //---add parameters
	        if ( cRes )
	        {
		        
		        
		        if ( cResponseGroup != null && cResponseGroup.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_RESPONSE_GROUP, cResponseGroup);
		        }
		        
		        if ( cBrowseNodeId != null && cBrowseNodeId.equals(""))
		        {
		        	params.put(AmazonClient.PARAM_BROWSE_NODE_ID, cBrowseNodeId);
		        }
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_BROWSE_NODE_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        requestUrl = helper.sign(params);
	        
	        cResXml = new AmazonResponse(requestUrl).getPrettyXml();
	        System.out.println("------------------");
	        
	       
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return ( cResXml = "" );
    	}
    }
    //-------------------------------------------------------------------
    public List<BrowseNodes> browseNodeLookupList(HashMap<String, String> params)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	AmazonClient client = null;
    	
    	List<BrowseNodes> cBrowseNodes = new LinkedList<BrowseNodes>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        final String accessKeyId  = AWS_ACCESS_KEY_ID;
	        final String secretKey    = AWS_SECRET_KEY;
	        final String associateTag = AWS_ASSOCIATE_TAG;
	       
	        if ( null == params )
	        {
	        	cRes = false;
	        	
	        	System.out.println(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //---check mandatory parameters
	        if ( cRes )
	        {
	        	
	        	if ( !params.containsKey(AmazonClient.PARAM_SERVICE) )
	        	{
	        		params.put(AmazonClient.PARAM_SERVICE, AmazonClient.SERVICE);
	        	}
	        	if ( !params.containsKey(AmazonClient.Op.PARAM_OPERATION) )
	        	{
	        		params.put(AmazonClient.Op.PARAM_OPERATION, AmazonClient.OPERATION_BROWSE_NODE_LOOKUP);
	        	}
	        	if ( !params.containsKey(AmazonClient.VERSION) )
	        	{
	        		params.put(AmazonClient.VERSION, AmazonClient.VERSION);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ASSOCIATE_TAG) )
	        	{
	        		params.put(AmazonClient.PARAM_ASSOCIATE_TAG, AWS_ASSOCIATE_TAG);
	        	}
	        	if ( !params.containsKey(AmazonClient.PARAM_ACCESS_KEY_ID) )
	        	{
	        		params.put(AmazonClient.PARAM_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID);
	        	}
	        }
	        
	        //---init AmazonClient
	        if ( cRes )
	        {
	        	/* The helper can sign requests in two forms - map form and string form */
	        	client = new AmazonClient(accessKeyId, secretKey, associateTag);
	        }
	        
	        if ( cRes )
	        {
	        	BrowseNodeLookupResponse cBrowseNodeLookupResponse = client.browseNodeLookup().execute(params);
	        	
	        	cBrowseNodes = cBrowseNodeLookupResponse.getBrowseNodes();
	        
		        System.out.println("------------------");
	        }
	        
	        return cBrowseNodes;
    	}
    	catch( Exception e)
    	{
    		System.out.println(cMethodName + "::Exception:" + e.getMessage());
    		
    		return new LinkedList<BrowseNodes>();
    	}
    }
    
}
