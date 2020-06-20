import java.util.concurrent.Semaphore;

//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;	
    
	
	public Process(int m) {
		processID = m;
	}
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}

	}
	
	private void process1() {
		OperatingSystem.Diagnostics("start of p1"+"\n");
		boolean temp1=BinarySemaphore.semPrintWait(this);
		if(!temp1) {
			//this.suspend();
		}
		boolean temp2=BinarySemaphore.semReadDataWait(this);
		if(!temp2) {
			//this.suspend();
		}
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		try {
			BinarySemaphore.semPrintPost();
			//OperatingSystem.Diagnostics("P1 released print sema"+"\n");
			BinarySemaphore.semReadDataPost();
			//OperatingSystem.Diagnostics("P1 released Read sema"+"\n");
		} catch (OSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProcessState(this,ProcessState.Terminated);
		
		//this.destroy();
		
	
		
		
		}
	public String toString() {
		return ""+this.processID;
	}
	
	private void process2() {
		//OperatingSystem.Diagnostics("line 56 before suspension");
		boolean temp1=BinarySemaphore.semPrintWait(this);
		if(!temp1) {
			//this.suspend();
		}
		//OperatingSystem.Diagnostics("line 58 after suspension");
		boolean temp2=BinarySemaphore.semUserInputWait(this);
		if(!temp2) {
			//this.suspend();
		}
		boolean temp3=BinarySemaphore.semWriteFileWait(this);
		if(!temp3) {
			//this.suspend();
		}
		OperatingSystem.printText("Enter File Name: ");
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter Data: ");
		String data= OperatingSystem.TakeInput();
		OperatingSystem.writefile(filename,data);
		
		
		
		try {
			BinarySemaphore.semPrintPost();
			//OperatingSystem.Diagnostics("P2 released print sema"+"\n");
			BinarySemaphore.semUserInputPost();
			//OperatingSystem.Diagnostics("P2 released Input sema"+"\n");
			BinarySemaphore.semWriteFilePost();
			//OperatingSystem.Diagnostics("P2 released write sema"+"\n");
		} catch (OSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProcessState(this,ProcessState.Terminated);
		}
	private void process3() {
		OperatingSystem.Diagnostics("start of p3"+"\n");
		boolean temp1=BinarySemaphore.semPrintWait(this);
		if(!temp1) {
			//this.suspend();
		}
		
		int x=0;
		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}

		try {
			BinarySemaphore.semPrintPost();
			//OperatingSystem.Diagnostics("P3 released print sema"+"\n");
		} catch (OSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process4() {
		boolean temp1=BinarySemaphore.semPrintWait(this);
		if(!temp1) {
			//this.suspend(); 
		}
		int x=500;
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	

		try {
			BinarySemaphore.semPrintPost();
			//OperatingSystem.Diagnostics("P4 released print sema"+"\n");
			//System.out.println(BinarySemaphore.getPrintData());
		} catch (OSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProcessState(this,ProcessState.Terminated);
		}
	private void process5() {
		boolean temp1=BinarySemaphore.semPrintWait(this);
		//return;
	if(!temp1) {
		//this.suspend();
	}
		
		
		boolean temp2=BinarySemaphore.semUserInputWait(this);
			//return;
		if(!temp2) {
			//this.suspend();
		}
		
		boolean temp3=BinarySemaphore.semWriteFileWait(this);
			//return;
		if(!temp3) {
			//this.suspend();
		}
		OperatingSystem.printText("Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.writefile("P5.txt", data);

		
		try {
			BinarySemaphore.semPrintPost();
			//OperatingSystem.Diagnostics("P5 released print sema"+"\n");
			BinarySemaphore.semWriteFilePost();
			//OperatingSystem.Diagnostics("P5 released write sema"+"\n");
			BinarySemaphore.semUserInputPost();
			//OperatingSystem.Diagnostics("P5 released input sema"+"\n");
		} catch (OSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		setProcessState(this,ProcessState.Terminated);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
			 if(OperatingSystem.execqueue.size()>0) {
			 OperatingSystem.execqueue.remove();
			 }
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
