package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {
	private String name;
	private WorkerLevel level; // level do tipo WorkerLevel
	private Double baseSalary;
	
	// adicionar as associações
	private Departament departament; //  Worker -> 1 departament
	private List<HourContract> contracts = new ArrayList<>(); // Worker -> vários contracts / criar uma lista para adiconar os contratos
	
	public Worker() {
	}
	
	// não inserir List no construtor / quando tiver uma composição "tem-muitas" não inclui ela no construtor
	public Worker(String name, WorkerLevel level, Double baseSalary, Departament departament) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.departament = departament;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	
	// adicionar contrato na lista
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	// removar contrato na lista
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	// calcula quanto o trabalhador ganhou num determinado ano e mês
	public double income(int year, int month) {
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance(); // // Obter a instância do Calendar
		
		// percorrer os contratos e somar os contratos do ano e mês específico
		for(HourContract c : contracts) {
			cal.setTime(c.getDate()); // Configurar o Calendar para a data do objeto c
			int c_year = cal.get(Calendar.YEAR); // // Extrair o ano
			int c_month = 1 + cal.get(Calendar.MONTH); // representa o mês do contrato / acrescentar o +1 porque o mês do Calendar inicia em 0
			if(year == c_year && month == c_month) {
				sum += c.totalValue();
			}
		}
		
		return sum;
	}
}
