package mpa.main;

public class Mpa {
	String filelist;
	int numThread;
	String root;
	HashMap<String, Statistics> output;
	public Mpa(String flist, String r, int nThread){
		filelist = flist;
		numThread = nThread;
		root = r;
	}
	
	public void Start(){
		Input();
		Process();
		return 
	}

}
