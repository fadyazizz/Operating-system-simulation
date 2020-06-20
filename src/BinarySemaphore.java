
public class BinarySemaphore {
	private static volatile  int readData=1;
	private static volatile  int writeFile=1;
	private static  volatile int printData=1;
	private static volatile  int userInput=1;


	public static boolean semPrintWait(Process p) {
		
		
		
		
		if (printData == 1) {
			printData = 0;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire print sema Result: Granted! "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return true;
		}else {
			
			OperatingSystem.blockedqueuePrint.add(p);
			p.suspend();
			p.status=ProcessState.Waiting;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Print sema Result: Blocked "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return false;
		}
		

	}

	public static boolean getReadData() {
		if(readData==1) {
			
			return true;
		}
		
		return false;
		
		}

	public static boolean getWriteFile() {
		if(writeFile==1) {
			return true;
		}
		else {
		return false;
		}
	}

	public static boolean getPrintData() {
		if(printData==1) {
			return true;
		}
		else {
		return false;
		}
	}

	public static boolean getUserInput() {
		if(userInput==1) {
			return true;
		}
		else {
		return false;
		}
	}

	public static void semPrintPost() throws OSException {

		if (printData == 0) {
			printData = 1;
			if(!OperatingSystem.blockedqueuePrint.isEmpty()) {
				
				Process p=OperatingSystem.blockedqueuePrint.remove();
				BinarySemaphore.semPrintWait(p);
				p.status=ProcessState.Ready;
				OperatingSystem.readyqueue.add(p);
				
			
			}
		}
		else {
			throw new OSException("Failure Posting");
		}
	}
	

	public static boolean semWriteFileWait(Process p) {
		if (writeFile == 1) {
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Write sema Result: Granted! "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			writeFile = 0;
			return true;
		}
		else {
			
			OperatingSystem.blockedqueueWrite.add(p);
			p.suspend();
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			p.status=ProcessState.Waiting;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire write sema Result: Blocked "+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return false;
		}
		

	}

	public static void semWriteFilePost() throws OSException {

		if (writeFile == 0) {
			writeFile = 1;
			if(!OperatingSystem.blockedqueueWrite.isEmpty()) {
				Process p=OperatingSystem.blockedqueueWrite.remove();
				BinarySemaphore.semWriteFileWait(p);
				OperatingSystem.readyqueue.add(p);
				
			}
		}
		else {
			throw new OSException("Failure Posting");
		}
	}
	
	public static boolean semReadDataWait(Process p) {
		if (readData == 1) {
			readData = 0;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Read sema Result: Granted! "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return true;
		}
		else {
			
			OperatingSystem.blockedqueueRead.add(p);
			p.suspend();
			p.status=ProcessState.Waiting;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Read sema Result: Blocked "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return false;
		}

	}

	public static void semReadDataPost() throws OSException {

		if (readData == 0) {
			readData = 1;
			if(!OperatingSystem.blockedqueueRead.isEmpty()) {
				Process p=OperatingSystem.blockedqueueRead.remove();
				BinarySemaphore.semReadDataWait(p);
				OperatingSystem.readyqueue.add(p);
				
			}
		}
		else {
			throw new OSException("Failure Posting");
		}
	}
	
	public static boolean semUserInputWait(Process p) {
		if (userInput == 1) {
			userInput = 0;
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Input sema Result: Granted! "+"\n");
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return true;
		}
		else {
			
			OperatingSystem.blockedqueueTakeInput.add(p);
			p.suspend();
			OperatingSystem.Diagnostics("Process "+p.processID+" Tried to aquire Input sema Result: Blocked "+"\n");
			p.status=ProcessState.Waiting;
			//OperatingSystem.Diagnostics("ReadyQueue "+OperatingSystem.readyqueue.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintqueue: "+OperatingSystem.blockedqueuePrint.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintTakeInput: "+OperatingSystem.blockedqueueTakeInput.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintRead: "+OperatingSystem.blockedqueueRead.toString()+"\n");
			OperatingSystem.Diagnostics("BlockedPrintwrite: "+OperatingSystem.blockedqueueWrite.toString()+"\n");
			return false;
		}

	}

	public static void semUserInputPost() throws OSException {

		if (userInput == 0) {
			userInput = 1;
			if(!OperatingSystem.blockedqueueTakeInput.isEmpty()) {
				Process p=OperatingSystem.blockedqueueTakeInput.remove();
				BinarySemaphore.semUserInputWait(p);
				OperatingSystem.readyqueue.add(p);
				
			}
		}
		else {
			throw new OSException("Failure Posting");
		}
	}

}
