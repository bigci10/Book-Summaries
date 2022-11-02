package lambda;

public class CompleteReferenceJava {}

/*----------------------------------------------------------------------------------------------------------------
 Java'nın lambda ifadeleri uygulamasını anlamanın anahtarı iki yapıdır. 
 Birincisi, lambda ifadesinin kendisidir. İkincisi functional interface. Her birinin basit bir tanımıyla başlayalım.
 
 Bir lambda ifadesi, esasen, anonim  bir yöntemdir.Ancak, bu yöntem kendi başına yürütülmez.
 Bunun yerine, functional interface tarafından tanımlanan bir yöntemi uygulamak için kullanılır.
 Böylece, bir lambda ifadesi anonim bir sınıf biçimiyle sonuçlanır.
 Lambda ifadeleri genellikle closures olarak da adlandırılır.
 
 Functional interface, yalnızca bir abstract metot içeren bir interfacedir. Normalde, bu yöntem interfacein amacını belirtir.
 Bu nedenle, functional interface tipik olarak tek bir eylemi temsil eder.
 	
 Örneğin standart bir interface olan Runnable bir functional interfacedir çünkü sadece 1 adet metodu vardır run().
 Ayrıca functional interface lambda ifadesinin hedef türünü tanımlar, lambda ifadesi yalnızca hedef türünün belirtildiği 
 bir bağlamda kullanılabilir. 
 
 -----------------------------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------------------------------------------
  Lambda ifadesi, Java diline yeni bir sözdizimi öğesi ve işleci getirdi. Bazen lambda operatörü veya ok operatörü olarak adlandırılan yeni operatör ->. 
  
  Bir lambda ifadesini iki bölüme ayırır.
  	Sol taraf, lambda ifadesinin gerektirdiği parametreleri belirtir.
  	(Herhangi bir parametreye gerek yoksa, boş bir parametre listesi kullanılır.) 
  
  Sağ tarafta, lambda ifadesinin eylemlerini belirten lambda gövdesi bulunur.
  	−> \"olur\" veya \"gider\" olarak ifade edilebilir.
  
  Java iki tür lambda gövdesi tanımlar. Biri tek bir ifadeden, diğeri ise bir kod bloğundan oluşur.
  
  Bi kaç basit lambda örneğiyle devam edelim. 
  
 ------------------------------------------------------------------------------------------------------------------*/




/*----------------------------------------------------------------------------------------------------------------
 () -> 123.45
 Bu lambda ifadesi parametre almaz , bu nedenle parametre listesi boştur.123,45 sabit değerini döndürür.
 Bu nedenle, aşağıdaki yönteme benzer:
 	double myMeth() {return 123.45;}
 
 Tabii ki, lambda ifadesiyle tanımlanan yöntemin bir adı yoktur.
 
 
 Burada biraz daha ilginç bir lambda ifadesi gösterilmektedir:
 	() -> Math.random() * 100
 	Bu lambda ifadesi Math.random( dosyasından sözde rasgele bir değer alır), 100 ile çarpar ve sonucu döndürür.
 	
 
 Bir lambda ifadesi parametre gerektirdiğinde, lambda işlecinin sol tarafındaki parametre listesinde belirtilir. İşte basit bir örnek:
 	(n) -> (n % 2) == 0
 	Bu lambda ifadesi, n parametresinin değeri eşitse true değerini döndürür.
 	Bu durumda n gibi bir parametrenin türünü açıkça belirtmek mümkün olsa da, çoğu durumda türü çıkarılabileceğinden, genellikle bunu yapmanız gerekmez.
 
 -----------------------------------------------------------------------------------------------------------------*/








/*---------------------------------------------------------------------------------------------------------
 												FUNCTİONAL INTERFACE 
-----------------------------------------------------------------------------------------------------------*/


//Belirtildiği gibi functional interface sadece bir adet abstract metotu olan interfacelerdir. 
//Bir süredir Java'da programlama yapıyorsanız, ilk başta tüm arayüz yöntemlerinin dolaylı olarak soyut olduğunu düşünebilirsiniz.
//Bu, JDK 8'den önce doğru olmasına rağmen, durum değişti. Bölüm 9'da açıklandığı gibi, JDK 8'den başlayarak, bir arayüzde default bir nonstatic metot bildirilebilir,static metot ve private static final bir değişken bildirilebilir.


//@FunctionalInterface
//interface myInterface
//{
//	double getMyValue();
//}

/*
 *Bu durumda getValue yöntemi abstracttır ve MyInterface tarafından tanımlanan tek yöntemdir. Bu nedenlel MyInterFace functional bir interfacedir ve işlevi 
 *getMyValue tarafından tanımlanır.
 */




/*----------------------------------------------------------------------------------------------------------
 Daha önce de belirtildiği gibi, bir lambda ifadesi kendi başına yürütülmez.
 Bunun yerine, hedef türünü belirten Functional interface tarafından tanımlanan soyut yöntemin uygulanmasını oluşturur.
 Sonuç olarak, lambda ifadesi yalnızca hedef türün tanımlandığı bir bağlamda belirtilebilir.
 Bu bağlamlardan biri, functional interface in başvurusuna bir lambda ifadesi atandığında oluşturulur.
 Bir lambda ifadesinin atama bağlamında nasıl kullanılabileceğini gösteren bir örnek üzerinde çalışalım. 
 ----------------------------------------------------------------------------------------------------------*/

//interface MyNumber 
//
//{
//	double getValue();
//}
//
//class Main 
//{
//	public static void main(String[] args)
//	{
//		MyNumber myNum; //İlk olarak, MyNumber işlevsel arayüzüne bir başvuru bildirilir:
//		
//		myNum = () -> 123.45; //Ardından, bu arabirim başvurusuna bir lambda ifadesi atanır
//		
//	}
//}

/*----------------------------------------------------------------------------------------------------------------------
  Hedef tür bağlamında bir lambda ifadesi oluştuğunda,lambda ifadesi functional interface tarafından bildirilen,
   abstract metodun davranışını tanımlayan lambda ifadesiyle birlikte functional interface i uygulayan bir sınıf örneği Ara kodda otomatik olarak oluşturulur.
   Bu yöntem hedef üzerinden çağrıldığında, lambda ifadesi yürütülür. . Böylece, lambda ifadesi bize bir kod segmentini bir nesneye dönüştürmenin bir yolunu sunar.
   
  Önceki örnekte, lambda ifadesi getValue( ) yönteminin uygulaması haline gelir. Sonuç olarak, aşağıda 123,45 değeri görüntülenir:
  // Call getValue(), which is implemented by the previously assigned
  // lambda expression.
  System.out.println(myNum.getValue())
  
  
  
  Bir lambda ifadesinin hedef tür bağlamında kullanılabilmesi için, abstract yönteminin türü ile lambda ifadesinin türünün uyumlu olması gerekir.
  Örneğin, abstract yöntemi iki int parametresi belirtiyorsa, 
  lambda'nın türü açıkça int olan veya bağlam tarafından örtük olarak int olarak çıkarılabilen iki parametre belirtmesi gerekir.
  
  Genel olarak, lambda ifadesinin parametrelerinin türü ve sayısı, yöntemin parametreleriyle uyumlu olmalıdır; iade türleri uyumlu olmalıdır; 
  ve lambda ifadesi tarafından atılan tüm özel durumlar yöntem için kabul edilebilir olmalıdır.
  
 -----------------------------------------------------------------------------------------------------------------------*/



/*---------------------------------------
 			Some Examples 
-----------------------------------------*/



//interface MyNumber{
//	
//	double getValue();
//}
//
//class LambdaDemo
//{
//	public static void main(String[] args) 
//	{
//	
//		MyNumber myNum; //declare an interface reference
//		
//		//Burada, lambda ifadesi sadece sabit bir ifadedir.
//		//myNum'a atandığında, Ara kodda lambda ifadesinin MyNumber'da getValue() yöntemini uyguladığı bir sınıf örneği oluşturulur		
//		myNum = () -> 123.45;
//		
//		System.out.println("A fixed value :" + myNum.getValue());
//	}
//	
//	
//	
//}


//lambda ifadesiyle bir parametrenin kullanımı

//interface NumericTest
//{
//	boolean test(int n);
//}
//
//class LambdaDemo2
//{
//	
//	public static void main(String[] args)
//	{
//		//A lambda expression that tests if a number is even.
//		NumericTest isEven = (n) -> (n % 2) == 0;
//		
//		if(isEven.test(10)) System.out.println("10 is even");
//		if(!isEven.test(9)) System.out.println("9 is not even");
//		
//		//Now, use a lambda expression that tests if a number
//		//is non - negative
//		
//		NumericTest isNonNeg = (n) -> n >= 0;
//		if(isNonNeg.test(1)) System.out.println("1 is non-negative");
//		if(!isNonNeg.test(-1)) System.out.println("-1 is negative");
//		
//		
//	}
//}


/*----------------------------------------------------------------------------------------------------------------------------------
 Bu program, lambda ifadeleri hakkında yakından incelenmeyi gerektiren önemli bir gerçeği göstermektedir.
 iseven testini gerçekleştiren lambda ifadesine özellikle dikkat edin. Burada tekrar gösterilir:
 	(n) -> (n % 2)==0
 	n türünün belirtilmediğine dikkat edin.
 	Daha ziyade, türü bağlamdan çıkarılır.
 	Bu durumda, türü, int olan NumericTest arabirimi tarafından tanımlanan test( ) parametre türünden çıkarılır.
 
 
 Bir lambda ifadesinde parametre türünü açıkça belirtmek de mümkündür.
 	(int n) -> (n % 2)==0
 	Burada, n açıkça int olarak belirtilir. Genellikle türü açıkça belirtmek gerekli değildir, ancak bunu gerektiren durumlarda bunu yapabilirsiniz.
 	JDK 11'den başlayarak, bir lambda ifadesi parametresi için yerel değişken türü çıkarımını açıkça belirtmek üzere "var" seçeneğini de kullanabilirsiniz.
 	
 	
 Bu program, lambda ifadeleriyle ilgili bir başka önemli noktayı daha gösterir: functional interface başvurusu, onunla uyumlu herhangi bir lambda ifadesini yürütmek için kullanılabilir.
 Programın, işlevsel arabirim NumericTest'in test( ) yöntemiyle uyumlu iki farklı lambda ifadesi tanımladığına dikkat edin.
 isEven adı verilen birincisi, bir değerin çift olup olmadığını belirler.
 isNonNeg adı verilen ikincisi, bir değerin negatif olup olmadığını kontrol eder.
 Her durumda, n parametresinin değeri test edilir.
 Her lambda ifadesi test( ) ile uyumlu olduğundan, her biri bir NumericTest başvurusu aracılığıyla yürütülebilir.
 
 Devam etmeden önce bir nokta daha. Bir lambda ifadesinin yalnızca bir parametresi olduğunda, lambda işlecinin sol tarafında belirtildiğinde parametre adını parantez içinde çevrelemek gerekli değildir.
 	n -> (n % 2)==0
 	
---------------------------------------------------------------------------------------------------------------------------------*/



//Sonraki program, iki parametre alan bir lambda ifadesini gösterir.

//interface NumericTest2 
//{
//	boolean test(int n, int d);
//	
//}
//
//class LambdaDemo3
//{
//	public static void main(String[] args)
//	{
//		NumericTest2 isFactor = (n,d) -> (n%d) == 0;
//		
//		if(isFactor.test(10, 2))
//			System.out.println("2 is a factor of 10");
//		if(!isFactor.test(10, 3))
//			System.out.println("3 is not a factor of 10");
//		
//	}
//}

/*------------------------------------------------------------------------------------------------------
   Bu programda, işlevsel arayüz NumericTest2 test( ) yöntemini tanımlar
   	boolean test(int n,int d);
   Bu sürümde, test( ) iki parametre belirtir. Bu nedenle, bir lambda ifadesinin test( ) ile uyumlu olması için, lambda ifadesinin de iki parametre belirtmesi gerekir.	
   
   (n, d) -> (n % d) == 0
   	İki parametre, n ve d, parametre listesinde virgülle ayrılmış olarak belirtilir.
   	
   	Bu örnek genelleştirilebilir. Birden fazla parametre gerektiğinde, parametreler lambda işlecinin sol tarafındaki parantez içinde virgülle ayrılmış olarak belirtilir.
   	
   	
   	Bir lambda ifadesindeki birden çok parametre hakkında önemli bir nokta şudur: 
   		Bir parametrenin türünü açıkça bildirmeniz gerekiyorsa, tüm parametrelerin bildirilen türlere sahip olması gerekir. Örneğin, bu yasaldır
   		(int n, int d) -> (n % d) == 0
   		
   		bu uygun değildir:
   		(int n, d) -> (n % d) == 0
-------------------------------------------------------------------------------------------------------*/




/*-----------------------------------------------------------
 				BLOCK LAMBDA EXPRESSIONS
 				
 Önceki örneklerde gösterilen lambdaların gövdesi tek bir ifadeden oluşmaktadır.
 Bu tür lambda gövdeleri ifade gövdeleri olarak adlandırılır bu tür lambdalalara "expression lambdas" denir
 Bir ifade gövdesinde, lambda işlecinin sağ tarafındaki kod tek bir ifadeden oluşmalıdır.
 İfade lambdaları oldukça kullanışlı olsa da, bazen durum tek bir ifadeden fazlasını gerektirecektir.
 Bu gibi durumları işlemek için Java, lambda işlecinin sağ tarafındaki kodun birden fazla deyim içerebilen bir kod bloğundan oluştuğu ikinci bir lambda ifadesi türünü destekler.
 Bu tip lambda gövdesine blok gövdesi denir. Blok gövdeleri olan lambdalar bazen "blok lambdas" olarak adlandırılır.
 
 
 Blok lambda, lambda ifadesi içinde işlenebilecek işlem türlerini genişletir, çünkü lambda'nın gövdesinin birden çok deyim içermesine izin verir.
 Örneğin, bir blok lambda'da değişkenleri bildirebilir, döngüleri kullanabilir, if ve switch deyimlerini belirtebilir, iç içe geçmiş bloklar oluşturabilir vb.
 
 Bir bloklambda oluşturmak kolaydır lambdanın bodysini {} içerisine alın .
 Birden fazla ifadeye izin vermenin yanı sıra, blok lambdalar, az önce tartışılan lambdas ifadesi gibi kullanılır. 
 Bununla birlikte, önemli bir fark, bir değer döndürmek için açıkça bir return deyimi kullanmanız gerektiğidir.
 Bu gereklidir, çünkü bir blok lambda gövdesi tek bir ifadeyi temsil etmez.
 
 
-------------------------------------------------------------*/


//Burada, int değerinin faktöriyeli değerini hesaplamak ve döndürmek için bir blok lambda kullanan bir örnek verilmiştir

//interface NumericFunc
//{
//	int func(int n);
//}
//
//class BlockLambdaDemo 
//{
//	public static void main(String[] args)
//	{
//		//this block lambda computes the factorial of an int value.
//		
//		NumericFunc factorial = (n) -> 
//		{
//			int result = 1;
//			
//			for(int i = 1; i <= n; i++)
//				result = i * result;
//			
//			return result;
//			
//		};
//		System.out.println("The factoral of 3 is " + factorial.func(3));
//		System.out.println("The factoral of 5 is " + factorial.func(5));
//	}	
//}



/*---------------------------------------------------------------------------------------------
 Programda, lambda bloğunun result adlı bir değişken bildirdiğine, for loop kullandığına ve return deyimine sahip olduğuna dikkat edin.
 Bunlar bir blok lambda gövdesi içinde yasaldır. Temel olarak, bir lambda'nın blok gövdesi bir yöntem gövdesine benzer.
 Bir nokta daha. Bir lambda ifadesi içinde bir return deyimi oluştuğunda, yalnızca lambda'dan bir geri dönüşe neden olur.
-----------------------------------------------------------------------------------------------*/




//Example

//interface StringFunc
//{
//	String func(String n);
//}
//
//class BlokcLambdaDemo2
//{
//	public static void main(String[] args)
//	{
//		StringFunc reverse = (str) -> 
//		{
//			
//			String result = "";
//			int i;
//			
//			for(i = str.length()-1; i >= 0; i--)
//				result += str.charAt(i);
//			
//			return result;
//		};
//		
//		System.out.println("Lambda reversed is "+reverse.func("Lambda"));
//		System.out.println("Expression reversed is "+reverse.func("Expression"));
//	}
//}


/*--------------------------------------------------------------------------
 Bu örnekte, functional interface StringFunc func( ) yöntemini bildirir.
 Bu yöntem, String türünde bir parametre alır ve String dönüş türüne sahiptir.
 Böylece, ters lambda ifadesinde, str türünün String olduğu sonucuna varılır.
---------------------------------------------------------------------------*/



/*---------------------------------------------
  			GENERİC FUNCTİONAL INTERFACE
Bir lambda ifadesinin kendisi, tür parametrelerini belirtemez.  Bu nedenle, bir lambda ifadesi generic olamaz.
Ancak, lambda ifadesiyle ilişkili functional interface generic olabilir.
Bu durumda, lambda ifadesinin hedef türü, kısmen, functional interface başvurusu bildirildiğinde belirtilen tür bağımsız değişkeni veya bağımsız değişkenleri tarafından belirlenir.



Generic functional interfaclerin değerini anlamak için bunu göz önünde bulundurun.
Önceki bölümdeki iki örnek, biri NumericFunc ve diğeri StringFunc olarak adlandırılan iki farklı functional interface kullanıldı.
Bununla birlikte, her ikisi de bir parametre alan ve bir sonuç döndüren func( ) adlı bir yöntem tanımladı.
İlk durumda, parametrenin türü ve dönüş türü int idi.
İkinci durumda, parametre ve dönüş türü String idi. Bu nedenle, iki yöntem arasındaki tek fark, ihtiyaç duydukları veri türüydü.
Yöntemleri yalnızca veri türlerinde farklılık gösteren iki functional interface yerine, her iki koşulu da işlemek için kullanılabilecek bir generic functional interface mümkündür.

-----------------------------------------------*/
//interface SomeFunc<T>
//{
//	T func (T t);
//}
//
//class GenericFunctionalInterfaceDemo
//{
//	
//	public static void main(String[] args) 
//	{
//		SomeFunc<String> reverse = (str) -> 
//		{
//			String result = "";
//			int i;
//			
//			for(i = str.length() -1 ; i >= 0 ; i --)
//				result += str.charAt(i);
//			return result;
//		};
//		System.out.println("Lambda reversed is " + reverse.func("Lambda"));
//		System.out.println("Expression reversed is " + reverse.func("Expression"));
//	
//		
//		SomeFunc<Integer> factorial = (n) ->
//		{
//			int result = 1;
//			
//			for (int i = 1; i <= n ; i++)
//				
//				result = i * result;
//			
//			return result;
//		};
//		
//		System.out.println("The factoral of 3 is " + factorial.func(3));
//		System.out.println("The factoral of 5 is " + factorial.func(5));
//	
//		
//	
//	}
//}


/*---------------------------------------------------------------------------------
  Programda, genel işlevsel arayüz SomeFunc burada gösterildiği gibi bildirilir:
  interface SomeFunc<T>
  {
  	T func(T t);
  }
  Burada T, func( ) öğesinin hem dönüş türünü hem de parametre türünü belirtir.
  Bu, bir parametre alan ve aynı türde bir değer döndüren herhangi bir lambda ifadesiyle uyumlu olduğu anlamına gelir.
  
-----------------------------------------------------------------------------------*/


/*-----------------------------------------------
 	PASSING LAMBDA EXPRESSIONS AS ARGUMENTS
 	
Daha önce açıklandığı gibi, lambda ifadesi hedef tür sağlayan herhangi bir bağlamda kullanılabilir.
Bunlardan biri, bir lambda ifadesinin arg olarak geçirilmesidir.
Aslında, bir lambda ifadesini argüman olarak geçirmek, lambdas'ın yaygın bir kullanımıdır.
Dahası, çok güçlü bir kullanımdır çünkü size yürütülebilir kodu bir yönteme bağımsız değişken olarak geçirmenin bir yolunu sunar.
Bir lambda ifadesini bağımsız değişken olarak geçirmek için, lambda ifadesi bağımsız değişkenini alan parametrenin türü, lambda ile uyumlu functional interfacein türünde olmalıdır.
Bir lambda ifadesini bağımsız değişken olarak kullanmak basit olsa da, onu çalışırken görmek hala yararlıdır.

-------------------------------------------------*/

//interface StringFunc
//{
//	String func(String n);
//}
//
//class LambdaAsArgumentsDemo
//{
//	
//	
//	static String stringOp(StringFunc sf,String s)
//	{
//		
//		return sf.func(s);
//	}
//	
//	public static void main(String[] args) 
//	{
//		String inStr = "Lambdas add power to Java";
//		String outStr;
//		
//		System.out.println("Here is input string: " + inStr);
//		
//		outStr = stringOp((str) -> str.toUpperCase(), inStr);
//		
//		System.out.println("The string in uppercase: " + outStr);
//		
//		outStr = stringOp((str) -> {
//			String result = "";
//			int i;
//			for(i = 0; i < str.length(); i++)
//			if(str.charAt(i) != ' ')
//			result += str.charAt(i);
//			return result;
//		}, inStr);
//	}
//}

/*------------------------------------------------------------------------------------------------------------------------------------
 Programda önce stringOp( ) yöntemine dikkat edin. İki parametresi vardır. Birincisi, işlevsel bir arayüz olan StringFunc tipindedir.
 Böylece, bu parametre lambda ifadesi tarafından oluşturulan örnek de dahil olmak üzere herhangi bir StringFunc örneğine başvuru alabilir.
 stringOp( ) öğesinin ikinci bağımsız değişkeni String türündedir ve bu, üzerinde çalışılan stringdir.
 	outStr = stringOp((str) -> str.toUpperCase(), inStr);
 	
 Burada basit bir lambda ifadesi argüman olarak geçmektedir.
 Bu durumda, StringFunc functional interface in bir örneği oluşturulur ve bu nesneye bir başvuru stringOp( ) öğesinin ilk parametresine geçirilir.
 Böylece, bir sınıf örneğine katıştırılmış lambda kodu yönteme geçirilir. Hedef türü bağlamı, parametre türüne göre belirlenir.
 Lambda ifadesi bu türle uyumlu olduğundan, çağrı geçerlidir.
 Az önce gösterildiği gibi basit lambdaları bir yöntem çağrısının içine gömmek, özellikle lambda ifadesi tek bir kullanım için tasarlanmışsa, genellikle kullanışlı bir tekniktir.
 
 Ardından, program stringOp( ) öğesine bir blok lambda geçirir. Bu lambda bir dizedeki boşlukları kaldırır. Burada tekrar gösterilir:
 	outStr = stringOp((str) -> {
 	String result = "";
 	int i;
 	for(i = 0; i < str.length(); i++)
 	if(str.charAt(i) != ' ')
 	result += str.charAt(i);
 	return result;
 	}, inStr);
 	
 ------------------------------------------------------------------------------------------------------------------------------------*/



/*-----------------------------------------------
 		LAMBDA EXPRESSIONS AND EXCEPTIONS
 A lambda expression can throw an exception. 
 Ancak, checked bir exception throw ederse, bu istisnanın functional interfacede ki soyut yöntemin throws yan tümcesinde listelenen istisnalarla uyumlu olması gerekir.
 
 
 İşte bu gerçeği gösteren bir örnek.
 Bir double değer dizisinin ortalamasını hesaplar.
 Ancak sıfır uzunlukta bir dizi geçirilirse, EmptyArrayException özel istisnasını atar.
 Örnekte gösterildiği gibi, bu özel durum DoubleNumericArrayFunc functional interface içinde bildirilen func( ) öğesinin throws yan tümcesinde listelenmiştir.
 
-------------------------------------------------*/

//interface DoubleNumericArrayFunc 
//{
//	double func(double[] n) throws EmptyArrayException;
//}
//
//class EmptyArrayException extends Exception
//{
//	
//	public EmptyArrayException() 
//	{
//		super("Array Empty");
//	}
//}
//
//class LambdaExceptionDemo
//{
//	public static void main(String[] args) throws EmptyArrayException
//	{
//		double[] values = {1.0,2.0,3.0,4.0};
//		
//		DoubleNumericArrayFunc average = (n) -> 
//		{
//			double sum = 0;
//			
//			if(n.length == 0)
//				throw new EmptyArrayException();
//			
//			for(int i = 0; i < n.length; i++)
//				sum+= n[i];
//			
//			return sum / n.length;
//		};
//		
//		System.out.println("The average is "+ average.func(values));
//		System.out.println("The average is " + average.func(new double[0]));
//	}
//}

/*-------------------------------------------------------------------------------------------------
   average.func( ) öğesine yapılan ilk çağrı 2,5 değerini döndürür. Sıfır uzunlukta bir diziyi geçen ikinci çağrı, bir EmptyArrayException atılmasına neden olur.
   Unutmayın, throws yan tümcesinin func( ) içine dahil edilmesi gereklidir.
   Bu olmadan, lambda ifadesi artık func( ) ile uyumlu olmayacağından program derlenmez.
   Bu örnek, lambda ifadeleriyle ilgili başka bir önemli noktayı göstermektedir.
   DoubleNumericArrayFunc işlevsel arabiriminde func( ) tarafından belirtilen parametrenin bir dizi olduğuna dikkat edin.
   Ancak, lambda ifadesinin parametresi n[ ] yerine basitçe n'dir.
   Bir lambda ifadesi parametresinin türünün hedef bağlamdan çıkarılacağını unutmayın.
   Bu durumda, hedef bağlam double[ ], dolayısıyla n türü double[ ] olacaktır.
   bunu n[ ] olarak belirtmek gerekli (veya yasal) değildir.
   Bunu açıkça double[ ] n olarak ilan etmek yasal olacaktır, ancak bunu yapmak bu durumda hiçbir şey kazandırmaz.
---------------------------------------------------------------------------------------------------*/





/*----------------------------------------
 LAMBDA EXPRESSIONS AND VARIABLE CAPTURE
 
 
 Bir lambda ifadesinin çevreleyen kapsamı tarafından tanımlanan değişkenlere lambda ifadesi içinden erişilebilir.
 Örneğin, bir lambda ifadesi, çevreleyen sınıfı tarafından tanımlanan bir örneği veya statik değişkeni kullanabilir.
 Bir lambda ifadesinin de "this" erişimi vardır (her ikisi de açıkça \nve örtük olarak), lambda ifadesinin çevreleyen sınıfının çağırma örneğini ifade eder.
 Böylece, bir lambda ifadesi bir örneğin veya statik değişkenin değerini elde edebilir veya ayarlayabilir ve çevreleyen sınıfı tarafından tanımlanan bir yöntemi çağırabilir.
 
 Ancak, bir lambda ifadesi çevreleyen kapsamından yerel bir değişken kullandığında, değişken yakalama olarak adlandırılan özel bir durum oluşturulur.
 Bu durumda, lambda ifadesi yalnızca etkin bir şekilde effectively final olan yerel değişkenleri kullanabilir.
 effectively final , ilk atandıktan sonra değeri değişmeyen değişkendir.Böyle bir değişkeni açıkça final olarak bildirmeye gerek yoktur, ancak bunu yapmak bir hata olmaz.
 (Çevreleyen bir kapsamın this parametresi otomatik olarak effectively finaldır ve lambda ifadelerinin kendilerine ait bir parametresi yoktur.)
 
 Çevreleyen kapsamın yerel değişkeninin lambda ifadesi tarafından değiştirilemeyeceğini anlamak önemlidir.
 Bunu yapmak, effectively final statüsünü ortadan kaldıracak ve böylece yakalanması için yasadışı hale getirecektir.
 
 Aşağıdaki program, effectively final ve değiştirilebilir yerel değişkenler arasındaki farkı göstermektedir:
------------------------------------------*/


//interface MyFunc
//{
//	int func(int n);
//}
//
//class VarCapture
//{
//	public static void main(String[] args)
//	{
//		int num = 10;
//	
//		MyFunc myLambda = (n) -> 
//		{
//			int v = num + n;
//			
//			num++; //Error
//			
//			return v;
//		};
//	}
//	
//}


/*------------------------------------------------------------------------------------------
 Yorumlarda belirtildiği gibi, num effectively finaldır ve bu nedenle myLambda içinde kullanılabilir.
 Bununla birlikte, eğer num, lambda'nın içinde veya dışında değiştirilecek olsaydı, effectively final statüsünü kaybederdi.
 Bu bir hataya neden olur ve program derlemez.
 
 Bir lambda ifadesinin, invoking classden bir örnek değişkenini kullanabileceğini ve değiştirebileceğini vurgulamak önemlidir.
 effectively final olmadığı sürece, çevreleyen kapsamının yerel bir değişkenini kullanamaz.
--------------------------------------------------------------------------------------------*/



/*---------------------------------------
 			METHOD REFERENCES
 
 Lambda ifadeleri ile ilgili yöntem başvurusu adı verilen önemli bir özellik vardır.
 Yöntem başvurusu, bir yöntemi yürütmeden başvurmanın bir yolunu sağlar.
 Lambda ifadeleriyle ilgilidir, çünkü o da uyumlu bir functional interfaceden oluşan bir hedef türü bağlamı gerektirir.
 
 Değerlendirildiğinde, bir yöntem başvurusu functional interface in bir örneğini de oluşturur.
 
 Farklı yöntem başvuru türleri vardır.
 Statik yöntemlere yöntem referansları ile başlayacağız.(We will begin with method references to static methods.)
 
-----------------------------------------*/




/*------------------------------------------------------
 	METHOD REFERENCES TO STATIC METHODS
 Statik yöntem başvurusu oluşturmak için şu genel sözdizimini kullanın:
 	ClassName::methodName
 Sınıf adının yöntem adından çift noktayla ayrıldığına dikkat edin.
 :: JDK 8 tarafından açıkça bu amaçla Java'ya eklenen bir ayırıcıdır.
 Bu yöntem başvurusu, hedef türüyle uyumlu olduğu her yerde kullanılabilir.
 
 Aşağıdaki program statik yöntem başvurusunu gösterir
 
--------------------------------------------------------*/

//A functional interface for string operations.
//interface StringFunc
//{
//	String func(String n);
//}
//
//
////This class defines a static method called strReverse().
//class MyStringOps
//{
//	static String strReverse(String str)
//	{
//		String result = "";
//		int i ;
//		
//		for(i = str.length() - 1; i >= 0; i--)
//			result += str.charAt(i);
//		
//		return result;
//	}
//}
//
//class MethodRefDemo
//{
//	static String stringOp(StringFunc sf,String s)
//	{
//		return sf.func(s);
//	}
//	
//	public static void main(String[] args)
//	{
//		String inStr = "Lambdas add power to java";
//		String outStr;
//		
//		// Here, a method reference to strReverse is passed to stringOp().
//		
//		outStr = stringOp(MyStringOps :: strReverse, inStr);
//		
//		System.out.println("Original string: "+inStr);
//		System.out.println("String reversed: "+outStr);
//		
//	}
//}



/*-------------------------------------------------------------------------
 Programda, bu satıra özellikle dikkat edin:
 	outStr = stringOp(MyStringOps::strReverse, inStr);
 
 Burada, MyStringOps içinde bildirilen strReverse( ) statik yöntemine yapılan bir başvuru, stringOp( ) öğesine ilk bağımsız değişken olarak geçirilir.
 Bu işe yarar çünkü strReverse StringFunc fonksiyonel arayüzü ile uyumludur.
 Böylece, MyStringOps :: strReverse ifadesi, strReverse'nın StringFunc'ta func( ) uygulamasını sağladığı bir nesneye yapılan başvuruyu değerlendirir.
 
 ---------------------------------------------------------------------------*/




/*------------------------------------------
 	METHOD REFERENCES TO INSTANCE METHODS
 
 Belirli bir nesnedeki örnek yöntemine başvuru iletmek için şu temel sözdizimini kullanın:
 	objRef::methodName
 Gördüğünüz gibi, sözdizimi, sınıf adı yerine nesne başvurusunun kullanılması dışında, statik bir yöntem için kullanılana benzer.
 
 Örnek yöntemi başvurusu kullanmak için yeniden yazılan önceki program aşağıda verilmiştir:
-------------------------------------------*/

//A functional interface for string operations.
//interface StringFunc
//{
//	String func(String str);
//}
//
////Now, this class defines an instance method called strReverse().
//class MyStringOps
//{
//	String strReverse(String str)
//	{
//		String result = "";
//		int i;
//		
//		for(i = str.length() - 1; i >= 0; i--)
//			result += str.charAt(i);
//		
//		return result;
//	}
//}

//class MethodRefDemo2 
//
////This method has a functional interface as the type of
//// its first parameter. Thus, it can be passed any instance
//// of that interface, including method references.
//{
//	static String stringOp(StringFunc sf, String s)
//	{
//		return sf.func(s);
//	}
//	
//	public static void main(String[] args)
//	{
//		String inStr = "Lambdas add power to java";
//		String outStr;
//		
//		// Create a MyStringOps object.
//		MyStringOps strOps = new MyStringOps();
//		
//		// Now, a method reference to the instance method strReverse
//		// is passed to stringOp()
//		outStr = stringOp(strOps::strReverse,inStr);
//		
//		System.out.println("Original string: " + inStr);
//		System.out.println("String reversed: " + outStr);
//		
//		
//	}
//}


/*---------------------------------------------------------------------------------------------------
 Programda, strReverse( ) öğesinin artık MyStringOps'un bir örnek yöntemi olduğuna dikkat edin.
 main( ) içinde, strOps adlı bir MyStringOps örneği oluşturulur.
 Bu örnek, burada tekrar gösterildiği gibi, stringOp çağrısında strReverse yöntem başvurusunu oluşturmak için kullanılır:
 	outStr = stringOp(strOps::strReverse, inStr);
 Bu örnekte, strOps nesnesinde strReverse( ) çağrılır.
 
 
 Ayrıca, yalnızca belirli bir nesneyle değil, belirli bir sınıfın herhangi bir nesnesiyle kullanılabilecek bir örnek yöntemi belirtmek istediğiniz bir durumu işlemek de mümkündür. 
 Bu durumda, burada gösterildiği gibi bir yöntem başvurusu oluşturacaksınız:
 	ClassName::instanceMethodName
 
 Burada, bir örnek yöntemi belirtilmiş olsa bile, belirli bir nesne yerine sınıfın adı kullanılır.
 Bu formda, functional interface in ilk parametresi çağıran nesneyle, ikinci parametre ise yöntem tarafından belirtilen parametreyle eşleşir.
 
 İşte bir örnek. MyFunc functional int func( ) yöntemi tarafından tanımlanan koşulu karşılayan bir dizideki nesnelerin sayısını sayan counter( ) adlı bir yöntemi tanımlar.
 Bu durumda, HighTemp sınıfının örneklerini sayar.
 
----------------------------------------------------------------------------------------------------*/


//interface MyFunc<T>
////Use an instance method reference with different objects.
////A functional interface that takes two reference arguments
////and returns a boolean result.
//{
//	boolean func(T v1,T v2);
//}

//A class that stores the temperature high for a day.
//class HighTemp
//{
//	private int hTemp;
//	
//	HighTemp(int ht) {this.hTemp = ht;}
//	
//	
//	// Return true if the invoking HighTemp object has the same
//	// temperature as ht2.
//	boolean sameTemp(HighTemp ht2)
//	{
//		return hTemp < ht2.hTemp;
//	}
//	
//	// Return true if the invoking HighTemp object has a temperature
//	// that is less than ht2.
//	boolean lessThanTemp(HighTemp ht2)
//	{
//		return hTemp < ht2.hTemp;
//	}
//}

//class InstanceMethWithObjectRefDemo
//{
//	// A method that returns the number of occurrences
//	// of an object for which some criteria, as specified by
//	// the MyFunc parameter, is true.
//	
//	static <T> int counter(T[] vals, MyFunc<T> f, T v)
//	{
//		int count = 0;
//		
//		for (int i = 0; i < vals.length; i++)
//			if (f.func(vals[i], v)) count ++;
//		return count;
//	}
//	
//	
//	public static void main(String[] args)
//	{
//		int count;
//		
//		// Create an array of HighTemp objects.
//		HighTemp[] weekDayHighs = { new HighTemp(89), new HighTemp(82),
//									new HighTemp(90), new HighTemp(89),
//									new HighTemp(89), new HighTemp(91),
//									new HighTemp(84), new HighTemp(83) };
//		
//		// Use counter() with arrays of the class HighTemp.
//		// Notice that a reference to the instance method
//		// sameTemp() is passed as the second argument.
//		
//		count = counter(weekDayHighs, HighTemp::sameTemp,new HighTemp(89));
//		
//		System.out.println(count + " days had a high of 89");
//		
//		// Now, create and use another array of HighTemp objects.
//		HighTemp[] weekDayHighs2 = { new HighTemp(32), new HighTemp(12),
//				 					 new HighTemp(24), new HighTemp(19),
//				 					 new HighTemp(18), new HighTemp(12),
//				 					 new HighTemp(-1), new HighTemp(13) };
//		
//		count = counter(weekDayHighs2, HighTemp::sameTemp,
//				 new HighTemp(12));
//		System.out.println(count + " days had a high of 12");
//		
//		// Now, use lessThanTemp() to find days when temperature was less
//		// than a specified value.
//		count = counter(weekDayHighs, HighTemp::lessThanTemp,new HighTemp(89));
//		System.out.println(count + " days had a high less than 89");
//		
//		count = counter(weekDayHighs2, HighTemp::lessThanTemp,new HighTemp(19));
//		System.out.println(count + " days had a high of less than 19");
//		
//	}
//}



/*-----------------------------------------------------------------
  Programda, HighTemp'in iki örnek yöntemi olduğuna dikkat edin: sameTemp( ) ve lessThanTemp( ).
  Birincisi, iki HighTemp nesnesi aynı sıcaklığı içeriyorsa true değerini döndürür.
  İkincisi, çağıran nesnenin sıcaklığı geçirilen nesneninkinden düşükse true değerini döndürür.
  Her yöntemin HighTemp türünde bir parametresi vardır ve her yöntem bir boole sonucu döndürür.
  Bu nedenle, her biri MyFunc işlevsel arabirimiyle uyumludur, çünkü çağıran nesne türü func( ) öğesinin
   ilk parametresine ve bağımsız değişken func( )'nin ikinci parametresine eşlenebilir.
  
  Böylece, ifade edildiğinde
  	HighTemp::sameTemp
  	
  counter () yöntemine iletilir, fonksiyonel arabirimin bir örneği, ilk parametrenin parametre türünün Hightemp olan örnek yönteminin çağırma nesnesi olduğu bir örneği oluşturulur.
  İkinci parametrenin türü de HighTemp'tir, çünkü parametrenin sameTemp( ) türü budur.
  Aynı şey lessThanTemp( ) yöntemi için de geçerlidir.
  
  Başka bir nokta: Burada gösterildiği gibi, süper kullanarak bir yöntemin süper sınıf sürümüne başvurabilirsiniz:
  	super::name
  typeName.super::name	
  	burada Typename kapsayan bir sınıf veya süper arayüz anlamına gelir.
-------------------------------------------------------------------*/





/*----------------------------------------------------------------
 				METHOD REFERENCE WİTH GENERİCS
 
 Yöntem başvurularını genel sınıflarla ve\/veya genel yöntemlerle kullanabilirsiniz. Örneğin, aşağıdaki programı göz önünde bulundurun:
------------------------------------------------------------------*/

//Demonstrate a method reference to a generic method
//declared inside a non-generic class.
//A functional interface that operates on an array
//and a value, and returns an int result.
//interface MyFunc<T>
//{
//	int func(T[] vals, T v);
//}

//This class defines a method called countMatching() that
//returns the number of items in an array that are equal
//to a specified value. Notice that countMatching()
//is generic, but MyArrayOps is not.
//class MyArrayOps
//{
//	static <T> int countMatching(T[] vals, T v)
//	{
//		int count = 0;
//		
//		for (int i = 0; i < vals.length; i++)
//			if(vals[i] == v) count ++;
//		
//		return count;
//	}
//}

//class GenericMethodRefDemo
//{
//	// This method has the MyFunc functional interface as the
//	// type of its first parameter. The other two parameters
//	// receive an array and a value, both of type T.
//	
//	static <T> int myOp(MyFunc<T> f, T[] vals, T v)
//	{
//		return f.func(vals, v);
//	}
//	
//	public static void main(String[] args)
//	{
//		Integer[] vals = { 1, 2, 3, 4, 2, 3, 4, 4, 5 };
//		String[] strs = { "One", "Two", "Three", "Two" };
//		
//		int count;
//		
//		count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
//		System.out.println("vals contains " + count + " 4s");
//		
//		count = myOp(MyArrayOps::<String>countMatching, strs, "Two");
//		System.out.println("strs contains " + count + " Twos");
//		
//		
//		
//	}
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------
 	Programda, MyArrayOps, countMatching( ) adlı generic bir yöntem içeren generic olmayan bir sınıftır.
 	Yöntem, bir dizideki belirtilen bir değerle eşleşen öğelerin sayısını döndürür.
 	Genel tür bağımsız değişkeninin nasıl belirtildiğine dikkat edin. Örneğin, main( ) içindeki ilk çağrısı burada gösterilmiştir:
 	
 	count = myOp(MyArrayOps::<Integer>countMatching, vals, 4); passes the type argument Integer. 
 	::'den sonra oluştuğuna dikkat edin. Bu sözdizimi genelleştirilebilir: 
 	Genel bir yöntem yöntem başvurusu olarak belirtildiğinde, tür bağımsız değişkeni :: ve yöntem adından önce gelir.
 	Bununla birlikte, tür bağımsız değişkeninin bu durumda (ve diğerlerinde) açıkça belirtilmesinin gerekli olmadığını belirtmek önemlidir, çünkü tür bağımsız değişkeni otomatik olarak çıkarılmış olacaktır.
 	Genel bir sınıfın belirtildiği durumlarda, type bağımsız değişkeni sınıf adını izler ve ::'den önce gelir.
 	Önceki örnekler yöntem referanslarını kullanmanın mekaniğini gösterse de, gerçek faydalarını göstermiyorlar.
-----------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------
 			CONSTRUCTORE REFERENCES
 
Yöntemlere başvurular oluşturma şeklinize benzer şekilde, oluşturuculara başvurular oluşturabilirsiniz. 
Kullanacağınız sözdiziminin genel biçimi aşağıda verilmiştir:
classname::new
Bu başvuru, oluşturucuyla uyumlu bir yöntem tanımlayan herhangi bir functional interface başvurusuna atanabilir. İşte basit bir örnek:
----------------------------------------------*/
//interface MyFunc
//{
//	MyClass func(int n);
//}
//
//class MyClass
//{
//	private int val;
//	
//	MyClass(int v) {this.val=v;}
//	
//	MyClass() {val = 0;}
//	
//	int getVal() {return val;}
//}
//
//class ConstructorRefDemo 
//{
//	public static void main(String[] args) 
//	{
//		
//		
//		
//		MyFunc myClassCons = MyClass :: new;
//		
//		MyClass mc = myClassCons.func(100);
//	}
//}

/*-----------------------------------------------------------------------------------------------
 Programda, MyFunc'un func( ) yönteminin MyClass türünde bir başvuru döndürdüğüne ve bir int parametresine sahip olduğuna dikkat edin. 
 Ardından, MyClass'ın iki oluşturucu tanımladığına dikkat edin.
  Birincisi, int türünde bir parametre belirtir. 
  İkincisi, varsayılan, parametresiz oluşturucudur. Şimdi, aşağıdaki satırı inceleyin:
  	MyFunc myClassCons = MyClass::new;
  Burada, MyClass::new ifadesi MyClass oluşturucusuna bir oluşturucu başvurusu oluşturur.
  Bu durumda, MyFunc'un func( ) yöntemi bir int parametresi aldığından, başvurulan oluşturucu MyClass(int v) olur, çünkü eşleşen budur. 
  Ayrıca, bu oluşturucuya yapılan başvurunun myClassCons adlı bir MyFunc başvurusuna atandığına dikkat edin.
  Bu deyim yürütüldükten sonra, myClassCons bu satırda gösterildiği gibi MyClass'ın bir örneğini oluşturmak için kullanılabilir:
  	MyClass mc = myClassCons.func(100);
  Özünde, myClassCons MyClass(int v) olarak adlandırmanın başka bir yolu haline geldi.
  
------------------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------------
 Generic sınıflara constructor başvuruları aynı şekilde oluşturulur.
 Tek fark, type bağımsız değişkeninin belirtilebilmesidir.
 Bu, yöntem başvurusu oluşturmak için geneeic bir sınıf kullanmakla aynı şekilde çalışır: sınıf adından sonra tür bağımsız değişkenini belirtmeniz yeterlidir.
 Aşağıda, önceki örneği MyFunc ve MyClass generic olacak şekilde değiştirerek bu gösterilmektedir.
 
 ------------------------------------------------------------------------------------------------*/

//interface MyFunc<T>
//{
//	MyClass<T> func(T n);
//}
//
//class MyClass<T>
//{
//	private T val;
//	
//	//A constructor that taken args
//	MyClass(T v) {val = v;}
//	
//	MyClass() {val = null;}
//	
//	T getVal() {return val;};
//}
//
//class ConstructorRefDemo2
//{
//	public static void main(String[] args)
//	{
//		MyFunc<Integer> myClassCons = MyClass<Integer> :: new;
//		
//		MyClass<Integer> mc = myClassCons.func(100);
//		
//		
//		
//	}
//}


/*--------------------------------------------------------------------------------------------------------------
 Bu program önceki sürümle aynı çıktıyı üretir. 
 Aradaki fark, şimdi hem MyFunc hem de MyClass'ın genel olmasıdır. 
 Bu nedenle, bir oluşturucu başvurusu oluşturan dizi, burada gösterildiği gibi bir tür bağımsız değişkeni içerebilir (her zaman gerekli olmasa da):
 	MyFunc<Integer> myClassCons = MyClass<Integer>::new;
 myClassCons oluşturulduğunda Integer tür bağımsız değişkeni zaten belirtilmiş olduğundan, bir<Integer> sonraki satırda gösterildiği gibi bir MyClass nesnesi oluşturmak için kullanılabilir:
 	MyClass<Integer> mc = myClassCons.func(100);
 Önceki örnekler bir constructor referansı kullanmanın mekaniğini gösterse de, hiç kimse hiçbir şey kazanılmadığı için gösterildiği gibi bir constructor referansı kullanmaz.
 Dahası, aynı oluşturucu için iki isme denk gelen şeye sahip olmak, kafa karıştırıcı bir durum yaratır.
 Bununla birlikte, size daha pratik bir kullanımın tadını vermek için, aşağıdaki program myClassFactory( ) adı verilen statik bir yöntem kullanır, bu da her türlü MyFunc nesnesinin nesneleri için bir fabrikadır.
 İlk parametresiyle uyumlu bir oluşturucuya sahip herhangi bir nesne türü oluşturmak için kullanılabilir.
 
-----------------------------------------------------------------------------------------------------------------*/
//interface MyFunc<R,T>
//{
//	R func(T n);
//}
//
//class MyClass<T>
//{
//	private T val;
//	
//	MyClass(T v) { val = v; }
//	
//	MyClass() { val = null; }
//	
//	T getVal() { return val; };
//	
//}
//
//
//class MyClass2
//{
//	String str;
//	
//	MyClass2(String s) { str = s; }
//	
//	MyClass2() { str = ""; }
//	
//	String getVal() { return str; };
//	
//}
//
//
//class ConstructorRefDemo3 
//{
//
//	// A factory method for class objects. The class must
//	// have a constructor that takes one parameter of type T.
//	// R specifies the type of object being created.
//	
//	static <R,T> R myClassFactory(MyFunc<R, T> cons, T v)
//	{
//		return cons.func(v);
//	}
//	
//	
//	public static void main(String[] args) 
//	{
//		// Create a reference to a MyClass constructor.
//		// In this case, new refers to the constructor that
//		// takes an argument.
//		MyFunc<MyClass<Double>, Double> myClassCons = MyClass<Double>::new;
//		
//		// Create an instance of MyClass by use of the factory method.
//		MyClass<Double> mc = myClassFactory(myClassCons, 100.1);
//		
//		// Use the instance of MyClass just created
//		System.out.println("val in mc is " + mc.getVal( ));
//		
//		// Now, create a different class by use of myClassFactory().
//		MyFunc<MyClass2, String> myClassCons2 = MyClass2::new;
//		
//		
//		// Create an instance of MyClass2 by use of the factory method.
//		MyClass2 mc2 = myClassFactory(myClassCons2, "Lambda");
//	}
//}

/*-----------------------------------------------------------------------------------------------------------------------
  Gördüğünüz gibi, myClassFactory( ) MyClass ve MyClass2 türünde nesneler oluşturmak için kullanılır
  Her iki sınıf da farklı olsa da, örneğin MyClass geneldir ve MyClass2 değildir, 
  her ikisi de myClassFactory( ) tarafından oluşturulabilir, çünkü her ikisinin de MyFunc'taki func( ) ile uyumlu oluşturucuları vardır.
  Bu işe yarar çünkü myClassFactory( ) oluşturduğu nesne için oluşturucuya geçirilir.
  Oluşturduğunuz farklı sınıfları deneyerek bu programla biraz deneme yapmak isteyebilirsiniz.
  
  Ayrıca farklı türlerdeki MyClass nesnelerinin örneklerini oluşturmayı deneyin. 
  Göreceğiniz gibi, myClassFactory( ), sınıfı MyFunc'ta func( ) ile uyumlu bir yapıcıya sahip herhangi bir nesne türü oluşturabilir.
  
  Bu örnek oldukça basit olmasına rağmen, constructor referansların Java'ya getirdiği güce işaret ediyor.
  
  Devam etmeden önce, diziler için kullanılan yapıcı başvuru sözdiziminin ikinci bir biçiminden bahsetmek önemlidir. 
  Bir dizi için yapıcı başvurusu oluşturmak üzere şu yapıyı kullanın:
  	type[]::new
  Burada, type oluşturulmakta olan nesnenin türünü belirtir. 
  Örneğin, ilk oluşturucu başvuru örneğinde (ConstructorRefDemo) gösterildiği gibi MyClass biçimini varsaymak ve burada gösterilen MyArrayCreator arabirimi verilen:
  
 -----------------------------------------------------------------------------------------------------------------------*/

//interface MyArrayCreator<T>
//{
//	T func(int n);
//}
//
//
//class MyClass
//{
//	
//	public MyClass(int i) {}
//
//	public static void main(String[] args) 
//	{
//		MyArrayCreator<MyClass[]> myArrayCons = MyClass[] :: new;
//		MyClass[] a = myArrayCons.func(2);
//		
//		a[0] = new MyClass(1);
//		a[1] = new MyClass(2);
//		
//	}
//}

/*----------------------------------------------------------------------------------
 Burada, func(2) çağrısı iki öğeli bir dizinin oluşturulmasına neden olur.
 Genel olarak, işlevsel bir arabirim, bir dizi oluşturucuya başvurmak için kullanılacaksa, tek bir int parametresi alan bir yöntem içermelidir.
------------------------------------------------------------------------------------*/




/*----------------------------------------------
 		PREDEFINED FUNCTIONAL INTERFACE
 Bu noktaya kadar, bu bölümdeki örnekler kendi işlevsel arayüzlerini tanımlamışlardır,
 böylece lambda ifadelerinin ve fonksiyonel arayüzlerin arkasındaki temel kavramlar açıkça gösterilebilir.
 Ancak, çoğu durumda, java.util.function adlı paket önceden tanımlanmış birkaç tane sağladığından, kendi işlevsel arabiriminizi tanımlamanız gerekmez.
 Bölüm II'de onlara daha yakından bakacak olsak da, işte bir örnekleme:
 
 (INTERFACE) : (Purpose)
 
 UnaryOperator<T> : T türündeki bir nesneye tek bir işlem uygulayın ve aynı zamanda T türünde olan sonucu döndürün. Yöntemine apply( ) denir.
 BinaryOperator<T> : T türündeki iki nesneye bir işlem uygulayın ve aynı zamanda T türünde olan sonucu döndürün. Yöntemine apply( ) denir.
 Consumer<T> : T türündeki bir nesneye işlem uygulayın. Yöntemine accept( ) denir.
 Supplier<T> : T türünde bir nesne döndürün. Yöntemine get( ) denir.
 Function<T, R> : T türündeki bir nesneye bir işlem uygulayın ve sonucu R türünde bir nesne olarak döndürün. Yöntemine apply( ) denir.
 Predicate<T> : T türündeki bir nesnenin bazı kısıtlamaları karşılayıp karşılamadığını belirleyin. Sonucu gösteren bir boole değeri döndürün. Yöntemine test( ) denir.
 
 
 		
 Aşağıdaki program, faktöriyel bir örnek uygulayarak blok lambdaları gösteren BlockLambdaDemo adlı önceki örneği yeniden işlemek için kullanarak işlev arabirimini çalışırken gösterir.		
 Bu örnek, NumericFunc adlı kendi işlevsel arabirimini oluşturdu, ancak programın bu sürümünde gösterildiği gibi, yerleşik İşlev arabirimi kullanılmış olabilir:
------------------------------------------------*/
//import java.util.function.Function;
//
//class useFunctionInterfaceDemo
//{
//	public static void main(String[] args)
//	{
//		
//		// This block lambda computes the factorial of an int value.
//		// This time, Function is the functional interface.
//		
//		Function<Integer,Integer> factorial = (n) ->
//		{
//			int result = 1;
//			for(int i = 1; i <= n ; i++) 
//				result - (i * result);
//			
//			return result;
//		};
//		System.out.println("The factoral of 3 is " + factorial.apply(3));
//		System.out.println("The factoral of 5 is " + factorial.apply(5));
//	}
//}

