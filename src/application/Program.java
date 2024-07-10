package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Departament;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// Cria uma instância de SimpleDateFormat com o padrão "dd/MM/yyyy"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter departament's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		
		// instanciar o trabalhador
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Departament(departmentName));
		// entrar com valor de contrato para o trabalhador
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for(int i=1; i<=n; i++) {
			System.out.printf("Enter contract #%d data: %n", i);
			System.out.print("Date (DD/MM/YYY): ");
			Date contractDate = sdf.parse(sc.next()); // Ler a data como string e convertê-la para Date
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			// instanciar um contrato
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract); // associonar contrato com trabalhador
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY):");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
		
		System.out.println();
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartament().getName());
		System.out.println("Income for " + monthAndYear + " : " + String.format("%.2f", worker.income(year, month)));
		sc.close();
	}

}
