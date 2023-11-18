/***************
	    CLASS: PhaseOne.java
	    CSC212 Data structures - Project phase I
	    Fall 2023
	    EDIT DATE:
	    
	    TEAM:
	    Data Craftsmen.
	    AUTHORS:
	    authors-names:
	    Abdulaziz Almousa , (443101909).
	    Faisal Mohammed Alomran , (443102216).
	    Mohammed Alrabah , (437106719).
	    
	    *************/

public class BSTNode<T> {
	    public String key;  
	    public T data;  
	    public BSTNode<T> left,right;  
	  
	    //node without left right  
	    public BSTNode(String k, T val) {  
	        key=k;  
	        data=val;  
	        left=right=null;  
	    }  
	  
	    //node with left right  
	  
	    public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {  
	        key=k;  
	        data=val;  
	        left=l;  
	        right=r;  
	    }  
	    
	   
}
