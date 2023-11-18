
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
