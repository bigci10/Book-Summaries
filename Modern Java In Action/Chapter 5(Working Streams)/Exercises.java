import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TooManyListenersException;

public class ModernJavaExercises {}

/*
  Görevler
 
 1- 2011 yılındaki tüm işlemleri bulun ve değere göre sıralayın (küçükten büyüğe).
 2-Tüccarların çalıştığı tüm eşsiz şehirler nelerdir?
 3-Cambridge'deki tüm yatırımcıları bulun ve adlarına göre sıralayın.
 4-Tüm tüccarların adlarının alfabetik olarak sıralanmış bir dizesini döndürün.
 5-Milano'da bulunan herhangi bir tüccar var mı?
 6-Cambridge'de yaşayan tüccarlardan gelen tüm işlemlerin değerlerini yazdırın.
 7-Tüm işlemlerin toplam değeri nedir?
 8-En küçük değere sahip işlemi bulun.
 
 */

class Trader
{
	private final String name;
	private final String city;
	
	public Trader(String n, String c)
	{
		this.name = n;
		this.city = c;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public String toString()
	{
		return "Trader:"+this.name + "in" + this.city; 
	}
}


class Transaction
{
	private final Trader trader;
	private final int year;
	private final int value;
	
	public Transaction(Trader trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	
	public Trader getTrader()
	{
		return this.trader;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public String toString()
	{
		return "{" + this.trader + ", " + "year: "+this.year+", " + "value:" + this.value+"}";
	}
}

class Solutions
{

			
	public static void main(String[] args) 
	{
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(brian, 2011, 300), 
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
		
	
		
		//Solution 1 
		List<Transaction> tr2011 = transactions.stream()
						       .filter(s -> s.getYear() == 2011)
						       .sorted(Comparator.comparing(Transaction::getValue))
						       .toList();
		
		
		//Solution 2 
		List<String> solution2 = transactions.stream()
						     .map(s -> s.getTrader().getCity())
						     .distinct()
						     .toList();
		
		//Solution 3 
		List<Trader> solution3 = transactions.stream()
						     .map(transaction -> transaction.getTrader())
						     .filter(isCambridge -> isCambridge.getCity().equals("Cambridge"))
						     .distinct()
						     .sorted(Comparator.comparing(Trader::getName))
						     .toList();
		
		//Solution 4 
		List<Trader> solution4 = transactions.stream()
						     .map(trader -> trader.getTrader())
						     .sorted()
						     .toList();
		//Solution 5
		boolean solution5 = transactions.stream()
		       .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
		
		
		//Solution 6
		transactions.stream()
			    .filter(fromCambridge -> fromCambridge.getTrader().getCity().equals("Cambridge"))
			    .map(Transaction::getValue)
			    .forEach(System.out::println);
		
		//Solution 7
		
		Optional<Integer> maxtValue = transactions.stream()
							  .map(Transaction::getValue)
						          .reduce(Integer::max);
		
		//Solution 8
		Optional<Transaction> minValue = transactions.stream()
							     .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
	
				
	}


	
}


