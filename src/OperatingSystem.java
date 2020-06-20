import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class OperatingSystem {

	public static ArrayList<Thread> ProcessTable;
	public  static volatile Queue<Process> blockedqueuePrint = new LinkedList<>();
	public  static volatile Queue<Process> blockedqueueTakeInput = new LinkedList<>();
	public  static volatile Queue<Process> blockedqueueWrite = new LinkedList<>();
	public  static volatile Queue<Process> blockedqueueRead = new LinkedList<>();
	public static volatile  Queue<Process> readyqueue = new LinkedList<>();
	public static volatile  Queue<Process> execqueue = new LinkedList<>();
	// public static int activeProcess= 0;
	// system calls:
	// 1- Read from File
	
	
	
	
	public   static void Diagnostics(String data) {
		OperatingSystem.writefile2("Diagnostics1.txt", data);
	}
	
	
	
	
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data = "";
		File file = new File(name);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				Data += scan.nextLine() + "\n";
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return Data;
	}
	public static void writefile2(String name, String data) {
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(name,true));
			BW.write(data);
			BW.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	// 3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);

	}

	// 4- take input

	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in = new Scanner(System.in);
		String data = in.nextLine();
		return data;

	}

	private static void createProcess(int processID) {
		
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p, ProcessState.Ready);
		//System.out.println("ready queue size " +readyqueue.size());
		readyqueue.add(p);
		//p.start();

	}

	


	public static void Scheduler() {
		while( !(readyqueue.isEmpty())   ) {
			if(OperatingSystem.execqueue.size()==0) {
			if(!readyqueue.isEmpty()) {

				Process p=readyqueue.remove();
				OperatingSystem.execqueue.add(p);
				if(p.isAlive()) {
					//OperatingSystem.Diagnostics("ReadyQueue "+readyqueue.toString()+"\n");	
					OperatingSystem.Diagnostics("Process "+p.processID+" Will resume"+"\n");
					p.status=ProcessState.Running;
					p.resume();
				}else {
					//OperatingSystem.Diagnostics("ReadyQueue "+readyqueue.toString()+"\n");	
					OperatingSystem.Diagnostics("Process "+p.processID+" Will start"+"\n");
					p.status=ProcessState.Running;
					p.start();
				}
					
				}	
			}
			}
			




				
				}
			
		
		
	

	public static void main(String[] args) {
		ProcessTable = new ArrayList<Thread>();
		File f=new File("Diagnostics1.txt");
		if(f.exists()) {
			f.delete();
		}
		
		//createProcess(4);
		createProcess(1);
		createProcess(3);
		//createProcess(5);
		//createProcess(2);
		
		Scheduler();
		
		
		
		
		
	
		
	}

}
