package generics;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class JavaHowToProgram {}


/*----------------------------------------------------------------------------------
	Generic yöntemler ve sınıflar derleme zamanı türü güvenliği ile yazılımın
	yeniden kullanımı için Javanın en güçlü yetenekleri arasındadır.
------------------------------------------------------------------------------------*/






/*-----------------------------------------------------------------------------------
Overloaded yöntemler genellikle farklı veri türlerinde benzer işlemleri gerçekleştirmek için kullanılır.
Şimdi generic metotları anlayabilmek için bi örnek yaratalım..
------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------
Derleyici bir yöntem çağrısıyla karşılaştığında, çağrıdaki bağımsız değişken türleriyle eşleşen aynı ada ve parametrelere sahip bir yöntem bildirimi bulmaya çalışır. 
Bu örnekte, her printArray çağrısı printArray yöntem bildirimlerinden biriyle eşleşir.


Örneğin, satır 36, - bağımsız değişkeni olarak integerArray ile printArray'i çağırır. 
Derleyici bağımsız değişkenin türünü (Integer[]) belirler ve Integer[] parametresini (satır 33-52) belirten bir printArray yöntemi bulmaya çalışır, 
ardından bu yönteme bir çağrı ayarlar.

Benzer şekilde, derleyici 15. satırdaki çağrıyla karşılaştığında, bağımsız değişkenin türünü (yani, Double[]) belirler, 
ardından Double[] parametresini (satır 31-38) belirten bir printArray yöntemini bulmaya çalışır, ardından bu yönteme bir çağrı ayarlar.
------------------------------------------------------------------------------------*/

//class OverloadedMethods {
//	
//	public static void main(String[] args)
//	{
//		
//		Integer[] integerArray = {1,2,3,4,5,6};
//		Double[] doubleArray = {1.1,2.2,3.3,4.4};
//		Character[] charArray = {'a','b','c','d'};
//		
//	}
//	
//	public static void printArray(Integer[] inputArray)
//	
//	{
//		//display array elements
//		for(Integer element : inputArray)
//		{
//			System.out.println(element);
//		}
//	}
//	
//	public static void printArray(Double[] inputArray)
//	{
//		for(Double element : inputArray)
//		{
//			System.out.println(element);
//		}
//	}
//	
//	public static void printArray(Character[] inputArray)
//	{
//		for(Character element : inputArray)
//		{
//			System.out.println(element);
//		}
//	}
//}






/*----------------------------------------------------------------------------------------
	Yukarıda ki örneğe ithafen Birkaç aşırı yüklenmiş yöntem tarafından gerçekleştirilen işlemler her bağımsız değişken türü için aynıysa, 
	aşırı yüklenmiş yöntemler generic bir yöntem kullanılarak daha kompakt ve rahat bir şekilde kodlanabilir. 
	Farklı türlerdeki bağımsız değişkenlerle çağrılabilen tek bir generic yöntem bildirimi yazabilirsiniz. 
	Generic yönteme geçirilen bağımsız değişkenlerin türlerine bağlı olarak, derleyici her yöntem çağrısını uygun şekilde işler.
------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------
  Type değişkeni olarak da bilinen type parametresi, generic tür adını belirten bir tanımlayıcıdır <T>.
  type parametreleri, generic bir yöntem bildiriminde dönüş türünü, parametre türlerini ve yerel değişken türlerini bildirmek için kullanılabilir
  ve gerçek tür bağımsız değişkenleri olarak bilinen generic yönteme geçirilen bağımsız değişken türleri için yer tutucu görevi görür.
  Tür parametreleri yalnızca başvuru türlerini temsil edebilir, ilkel türleri (int, double ve char gibi) temsil edemez.
  
  
  Tür parametrelerinin tek tek büyük harfler olarak belirtilmesi önerilir. 
  Genellikle, bir dizi öğesinin türünü (veya başka bir koleksiyonu) temsil eden type parametresi T olarak adlandırılır.
   
 ----------------------------------------------------------------------------------------------------------------------------------*/


//class App {
//	
//	public static void main(String[] args) {
//		Integer[] intArray = {1,2,3,4,5};
//		Double[] doubleArray = {1.1,2.2,3.3,4.4};
//		Character[] charArray = {'H','E','L','L','O'};
//		
//		System.out.println("Array integerArray contains");
//		printArray(intArray);
//		printArray(charArray);
//		printArray(doubleArray);
//	}
//	
//	public static<T> void printArray(T[] inputArray)
//	
//	{
//		
//		for(T element : inputArray)
//			System.out.println(element);
//		
//	}
//}

/*-------------------------------------------------------------------------------------------------------
 Kod şu şekilde çalışıyor ..
 Derleyici satır 14 ile karşılaştığında, önce bağımsız değişken integerArray'in türünü (yani, Integer[]) belirler ve tek bir Integer[] parametresi belirten printArray adlı bir yöntem bulmaya çalışır. 
 Bu örnekte böyle bir yöntem yoktur. 
 Daha sonra derleyici, printArray adlı tek bir array parametresi belirten ve array öğesi türünü temsil etmek için type parametresi kullanan genel bir yöntem olup olmadığını belirler. 
 Derleyici, printArray (satır 22-29) bir eşleşme olduğunu belirler ve yönteme bir çağrı ayarlar.
 --------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------------------------------------------
 Örneğin, T tür parametresi adı aşağıdaki yöntemin parametre listesinde iki kez görünür:
 	public static <T> void printTwoArrays( T[] array1, T[] array2 )
 Tip-parametre adlarının farklı generic yöntemler arasında benzersiz olması gerekmez.
 
 -----------------------------------------------------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------------------------------------------------
Derleyici genel method printArray öğesini Java bayt kodlarına çevirdiğinde, 
type-parameter bölümünü kaldırır ve type parametrelerini gerçek türlerle değiştirir.
Bu işlem silme Type erasure denir.
-----------------------------------------------------------------------------------------------------------------------------------*/




/*----------------------------------------------------------------------------------------------------------------------------------
Varsayılan olarak, tüm genel türler Object türüyle değiştirilir. 
Bu nedenle, printArray yönteminin derlenmiş sürümü Şekil 21.4'te gösterildiği gibi görünür—bu kodun örnekteki tüm printArray çağrıları için kullanılan yalnızca bir kopyası vardır.
-----------------------------------------------------------------------------------------------------------------------------------*/

//class App {
//	
//	public static void printArray(Object[] inputArray )
//	{
//		for ( Object element : inputArray )
//			 System.out.printf( "%s ", element );
//		System.out.println();
//	}
//	
//}



/*----------------------------------------------------------------------------------------------------------------------------------
Generic bir yöntem overload olabilir. Bir sınıf, aynı yöntem adını ancak farklı yöntem parametrelerini belirten iki veya daha fazla generic yöntem sağlayabilir.
-----------------------------------------------------------------------------------------------------------------------------------*/




/*----------------------------------------------------------------------------------------------------------------------------------
Generic bir yöntem, generic olmayan yöntemler tarafından da overload edilebilir. 
Derleyici bir yöntem çağrısıyla karşılaştığında, yöntem adıyla ve çağrıda belirtilen bağımsız değişken türleriyle en tam olarak eşleşen yöntem bildirimini arar.

-----------------------------------------------------------------------------------------------------------------------------------*/







/*----------------------------------------------------------------------------------------------------------------------------------
Derleyici bir yöntem çağrısıyla karşılaştığında, hangi yöntemin çağrılacağını belirlemek için eşleşen bir işlem gerçekleştirir.
Derleyici, yöntem çağrısının yöntem adı ve bağımsız değişken türlerinin belirli bir yönteminkilerle eşleştiği kesin bir eşleşme bulmaya ve kullanmaya çalışır
Böyle bir yöntem yoksa, derleyici uyumlu türlere veya eşleşen bir genel yönteme sahip bir yöntem bulmaya çalışır.
-----------------------------------------------------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------------------------------------------------
											METOTLAR BİTTİ ŞİMDİ GENERİC CLASSLARA GEÇİYORUZ 
-----------------------------------------------------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------------------------------------------------
Genel bir sınıfa sahip olduğunuzda, sınıfın type parametrelerinin yerine kullanılması gereken türleri belirtmek için basit, kısa bir gösterim kullanabilirsiniz. 
Derleme zamanında, derleyici kodunuzun tür güvenliğini sağlar ve 
istemci kodunuzun genel sınıfla etkileşim kurmasını sağlamak için Bölüm 21.3–21.4'te açıklanan silme tekniklerini(TYPE ERASURE) kullanır.
-----------------------------------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------------------------------------------
Örneğin, bir genel Stack sınıfı, birçok mantıksal Stack sınıfı (örneğin; \"Stack of Double\", \"Stack of Integer\", \"Stack of Character\", \"Stack of Employee\") 
oluşturmanın temeli olabilir. "
+ "Bu sınıflar, bir veya daha fazla tür parametresini kabul ettikleri için parametreli sınıflar veya parametreli türler olarak bilinir.

Tür parametrelerinin yalnızca başvuru türlerini temsil ettiğini hatırlayın, bu da Stack genel sınıfının ilkel türlerle örneklenemeyeceği anlamına gelir.
     
-----------------------------------------------------------------------------------------------------------------------------------

Şekil 21.7'de generic bir Stack sınıf bildirimi sunulmaktadır. generic sınıf bildirimi generic olmayan bir bildirime benzer, 
ancak sınıf adını bir tür-parametre bölümü (satır 5) izler. Bu durumda, type parametresi T, Stack'in işleyeceği öğe türünü temsil eder. 
Generic yöntemlerde olduğu gibi, generic bir sınıfın type-parameter bölümünde virgülle ayrılmış bir veya daha fazla tür parametresi olabilir. 
(Alıştırma 21.8'de iki tür parametresine sahip genel bir sınıf oluşturacaksınız.) Type parametresi T, öğe türünü temsil etmek için Stack sınıf bildirimi boyunca kullanılır.
 Bu örnek, bir Stack'i ArrayList olarak uygular.

-----------------------------------------------------------------------------------------------------------------------------------*/

//class Stack<T>
//{
//	private ArrayList<T> elements ; //ArrayList stores stack elements
//	
//	
//	//no-argument constructor creates a stack of default size
//	public Stack()
//	{
//		this( 10 ); //default stack size
//	
//	}//end no-arg Stack constructor
//	
//	public Stack(int capacity)
//	{
//		int initCapacity = capacity > 0 ? capacity : 10; //validate
//		elements = new ArrayList< T >(initCapacity); //create ArrayList 
//	}//end one-arg Stack constructor
//	
//	public void push(T pushValue)
//	{
//		elements.add(pushValue); //Place pushValue on Stack
//	}
//	
//	// return the top element if not empty; else throw EmptyStackException
//	public T pop()
//	{
//		if(elements.isEmpty())// if stack is empty
//			throw new EmptyStackException();
//		// remove and return top element of Stack
//		return elements.remove(elements.size() - 1);
//	}
//}


/*----------------------------------------------------------------------------------------------------------------------------------------------------
 - Üstte ki Kodun açıklaması -
 
 Class Stack, elements ArrayList <T> olarak bildirir.
 Bu ArrayList, Stack'in öğelerini depolar.
 Bildiğiniz gibi, bir ArrayList dinamik olarak büyüyebilir, böylece Stack sınıfımızın nesneleri de dinamik olarak büyüyebilir.
 Stack sınıfının no-arg constructorı temel alınan ArrayList'in 10 öğelik bir kapasiteye sahip olduğu bir Stack oluşturmak için tek bağımsız değişkenli constrcutorı çağırır.
 Tek bağımsız değişkenli oluşturucu, belirtilen başlangıç kapasitesine sahip bir Yığın oluşturmak için doğrudan da çağrılabilir. 
 Ternary Operator ile , constructorın bağımsız değişkenini doğrular. Satır 19, belirtilen kapasitenin ArrayList'ini (veya kapasite geçersizse 10) oluşturur.
 
 Genel yöntemlerde olduğu gibi, genel bir sınıf derlendiğinde, derleyici sınıfın tür parametreleri üzerinde silme işlemi gerçekleştirir ve bunları üst sınırlarıyla değiştirir. 
 Stack sınıfı için (Şekil 21.7), üst sınır belirtilmemiştir, bu nedenle varsayılan üst sınır olan Object,kullanılmış.
 Genel bir sınıfın type parametresinin kapsamı tüm sınıftır. Ancak, tür parametreleri bir sınıfın statik değişken bildirimlerinde kullanılamaz.
 -----------------------------------------------------------------------------------------------------------------------------------------------------*/


//class StackTest
//{
//	public static void main(String[] args) 
//	{
//		double[] doubleElements = {1.1,2.2,3.3,4.4,5.5};
//		int[] integerElements = {1,2,3,4,5,6,7,8,9,0};
//		
//		//create a Stack<Double> and a Stack <Integer>
//		Stack<Double> doubleStack = new Stack<>(5);
//		Stack<Integer> integerStack = new Stack<>();
//		
//		// push elements of doubleElements onto doubleStack
//		testPushDouble(doubleStack,doubleElements);
//		testPopDouble( doubleStack ); // pop from doubleStack
//		
//		// push elements of integerElements onto integerStack
//		testPushInteger( integerStack, integerElements );
//		testPopInteger( integerStack ); // pop from integerStack
//	}
//	
//	
//	// test push method with double stack
//	private static void testPushDouble(Stack<Double> stack,double[] values)
//	{
//		System.out.println("\nPushing elements onto doubleStack");
//		// push elements to Stack
//		for(double value : values)
//		{
//			System.out.println(value);
//			stack.push(value); // push onto doubleStack
//		}
//	}
//	
//	private static void testPopDouble( Stack< Double > stack)
//	{
//		
//		// pop elements from stack
//		try
//		{
//			System.out.println( "\nPopping elements from doubleStack" );
//			double popValue; // store element removed from stack
//			
//			// remove all elements from Stack
//			while ( true )
//			{
//				popValue = stack.pop(); // pop from doubleStack
//				System.out.printf( "%.1f ", popValue );
//			}
//		}
//		catch(EmptyStackException emptyStackException)
//		{
//			System.err.println();
//			emptyStackException.printStackTrace();
//		}
//	}
//	
//	
//	// test push method with integer stack
//	private static void testPushInteger(Stack<Integer> stack, int[] values)
//	{
//		System.out.println( "\nPushing elements onto integerStack" );
//		
//		// push elements to Stack
//		for ( int value : values )
//		{
//			System.out.printf( "%d ", value );
//			stack.push( value ); // push onto integerStack
//
//		}
//	}
//	
//	// test pop method with integer stack
//	private static void testPopInteger( Stack< Integer > stack )
//	{
//		// pop elements from stack
//		try
//		{
//			System.out.println("\nPopping elements from integerStack");
//			int popValue; // store element removed from stack
//	
//			// remove all elements from Stack
//			while ( true )
//			{
//				popValue = stack.pop(); // pop from intStack
//				System.out.printf( "%d ", popValue );
//
//			}
//		}
//		catch( EmptyStackException emptyStackException )
//		{
//			System.err.println();
//			emptyStackException.printStackTrace();
//		}
//	}
//}


/*----------------------------------------------------------------------------------------
 Yukarıdaki kodun daha basit ve efektif halini generic metotlarla yazalım 
 -----------------------------------------------------------------------------------------*/


//class StackTest2
//{
//	public static void main(String[] args)
//	{
//		Double[] doubleElements = {1.1,2.2,3.3,4.4,5.5};
//		Integer[] integerElements = {1,2,3,4,5,6,7};
//		
//		Stack<Double> doubleStack = new Stack<Double>(5);
//		Stack< Integer > integerStack = new Stack< Integer >();
//		
//		
//		testPush( "doubleStack", doubleStack, doubleElements );
//		testPop( "doubleStack", doubleStack ); // pop from doubleStack
//		
//		testPush( "integerStack", integerStack, integerElements );
//		testPop( "integerStack", integerStack ); // pop from integerStack
//		
//		
//	}
//	
//	public static <T> void testPush(String name, Stack<T> stack,T[] elements)
//	{
//		System.out.printf( "\nPushing elements onto %s\n", name );
//		
//		
//		for(T element : elements)
//		{
//			System.out.println(element);
//			stack.push(element);
//		}
//	}
//	
//	
//	public static <T> void testPop(String name, Stack<T> stack)
//	{
//		try
//		{
//			System.out.printf( "\nPopping elements from %s\n", name );
//			T popValue;
//		
//		
//			while(true)
//			{
//				popValue = stack.pop();
//				System.out.printf( "%s ", popValue );
//			
//			}
//	
//		}
//		catch(EmptyStackException emptyStackException)
//		{
//			System.out.println();
//			emptyStackException.printStackTrace();
//		}
//		
//	}
//}



/*----------------------------------------------------------------------------------------
genel sınıf Stack için test programları, Double ve Integer tür bağımsız değişkenleriyle Stacks'in örneğini oluşturur. 
Genel sınıf Stack'i bir tür bağımsız değişkeni belirtmeden aşağıdaki gibi örneklemek de mümkündür.


Bu durumda, objectStack'in ham bir türe sahip olduğu söylenir, 
bu da derleyicinin her tür bağımsız değişkeni için genel sınıf boyunca örtülü olarak Object türünü kullandığı anlamına gelir.
-----------------------------------------------------------------------------------------*/


//class App 
//{
//	Stack objectStack = new Stack();
//}


/*----------------------------------------------------------------------------------------
Ham türdeki bir Stack değişkenine, Stack nesnesi gibi bir type bağımsız değişkenini belirten bir Stack<Double> atanabilir
çünkü Double türü, Object'in bir alt sınıfıdır. Bu atamaya izin verilir
-----------------------------------------------------------------------------------------*/

//class App 
//{
//	Stack rawTypeStack2 = new Stack< Double >( 5 );
//}

/*----------------------------------------------------------------------------------------
Benzer şekilde, bildiriminde type bağımsız değişkeni belirten bir Stack değişkenine, aşağıdaki gibi ham türde bir Stack nesnesi atanabilir:

Bu atamaya izin verilse de, 
ham türdeki bir Yığın Tamsayı dışındaki türleri depolayabileceğinden, güvenli değildir.
 Bu durumda, derleyici güvenli olmayan atamayı gösteren bir uyarı iletisi verir.
-----------------------------------------------------------------------------------------*/

//class App
//{
//	Stack< Integer > integerStack = new Stack( 10 );
//
//}






/*------------------------------------------------------------------
 							WildCard
 							
Bu bölümde, joker karakterler olarak bilinen güçlü bir jenerik kavramı tanıtıyoruz.
Belki de bu veri yapılarından en basiti, dinamik olarak yeniden boyutlandırılabilir, dizi benzeri bir veri yapısı olan ArrayList sınıfıdır. 
Bu tartışmanın bir parçası olarak, bir ArrayList oluşturmayı, 
ona öğeler eklemeyi ve enhanced for statement.structures kullanarak bu öğeler arasında geçiş yapmayı öğreneceksiniz.

Joker karakterleri motive eden bir örnek düşünelim. 
ArrayList gibi bir koleksiyondaki sayıları toplayan genel bir yöntem toplamı uygulamak istediğinizi varsayalım.
Koleksiyondaki sayıları ekleyerek başlarsınız.
 
Genel sınıflar yalnızca sınıf veya interface türleriyle kullanılabildiğinden, sayılar tür sarmalayıcı sınıflarının nesneleri olarak otomatik olarak kutulanır
Örneğin, herhangi bir int değeri bir Integer nesnesi olarak otomatik olarak kutulanır ve herhangi bir double değer Double nesne olarak otomatik olarak kutulanır.
ArrayList'teki tüm sayıları, türlerine bakılmaksızın toplayabilmek istiyoruz. Bu nedenle, ArrayList'i hem Tamsayı hem de Double'ın üst sınıfı olan Number tür bağımsız değişkeniyle bildireceğiz.
 
 
--------------------------------------------------------------------*/


//class TotalNumbers
//{
//	public static void main(String[] args)
//	{
//		Double[] numbers = {1.1, 2, 3, 4};
//		ArrayList<Number> numberList = new ArrayList<>();
//		
//		numberList.add(112.2);
//		
//
//		
//		System.out.printf( "numberList contains: %s\n",numberList );
//		System.out.printf( "Total of the elements in numberList: %.1f\n",sum(numberList));
//	}
//	
//	
//	public static double sum(ArrayList<Number> list)
//	{
//		double total = 0;
//		
//		for(Number element : list)
//			total += element.doubleValue();
//		
//		return total;
//		
//	}
//}




/*----------------------------------------------------------------------------------------
Parametresinde Joker Karakter Türü Bağımsız Değişkeni ile Yöntem toplamını Uygulama (yukarıdaki koda ithafen yazılmıştır)


Şekil 21.13'teki yöntem toplamının amacının, bir ArrayList'te depolanan her türlü Sayıyı toplamak olduğunu hatırlayın.
Hem Integer hem de Double nesneleri içeren bir ArrayList of Numbers oluşturduk.

Şimdi Number tüm sayıların üst sınıfı eğer biz ArrayList<Integer> diye bir şey belirtirsek ve bu arrayi sum metoduna geçersek çalışmaz 
Peki ama neden? Number sınıfı Integer sınıfının super classı değil mi ? , bütün buna rağmen derleyici ArrayList<Number> to be a superclass of ArrayList<Integer>. olduğunu düşünmez
Eğer öyle olsaydı, ArrayList <Number> 'da gerçekleştirebileceğimiz her işlem de bir ArrayList <Integer> üzerinde de çalışırdı.
Arraylist <Number> 'e double nesne ekleyebileceğinizi düşünün, çünkü Double bir Numberdır
ama biz bir Double ı alıp ArrayList<Integer> içine atamayız çünkü Double bir Integer değildir. işte burada bir alt tip ilişkisi problemi vardır.




Toplam yöntemin, herhangi bir sayı alt sınıfının öğelerini içeren herhangi bir ArrayList'in öğelerini toplamlayabilecek daha esnek bir sürümünü nasıl oluştururuz? 
Burası joker tipi argümanların önemli olduğu yerdir. Joker karakterler, yöntem parametrelerini, dönüş değerlerini, değişkenleri veya alanları vb. Belirtmenizi sağlar.

	ArrayList< ? extends Number >
	
Bir joker türü argümanı, tek başına “bilinmeyen bir türü” temsil eden bir soru işareti (?) İle gösterilir. 
Bu durumda, joker karakter sınıf numarasını genişletir, yani joker karakter üst sınırına sahiptir. 
Dolayısıyla, bilinmeyen tip argümanı sayı veya bir alt sınıf olmalıdır.


Yöntem toplamında (satır 50-59), ArrayList bağımsız değişkeninin öğe türleri yöntem tarafından doğrudan bilinmese de
, joker karakter üst sınır Numarası ile belirtildiğinden, en azından Number türü oldukları bilinmektedir.
-----------------------------------------------------------------------------------------*/

//class WildCardTest
//{
//	public static void main(String[] args)
//	{
//		Integer[] integers = { 1, 2, 3, 4, 5 };
//		ArrayList< Integer > integerList = new ArrayList< Integer >();
//		
//		for(Integer element : integers)
//			integerList.add(element);
//		System.out.printf( "integerList contains: %s\n", integerList );
//		System.out.printf( "Total of the elements in integerList: %.0f\n\n",sum(integerList));
//		
//		// create, initialize and output ArrayList of Doubles, then
//		// display total of the elements
//		
//		Double[] doubles = { 1.1, 3.3, 5.5 };
//		ArrayList< Double > doubleList = new ArrayList< Double >();
//		
//		for ( Double element : doubles )
//			doubleList.add(element);
//		
//		System.out.printf( "doubleList contains: %s\n", doubleList );
//		System.out.printf( "Total of the elements in doubleList: %.1f\n\n",sum( doubleList ));
//		
//	}
//	
//	public static double sum(ArrayList<? extends Number> list)
//	{
//		
//		double total = 0;
//		
//		for(Number element : list)
//			total += element.doubleValue();
//		
//		return total;
//	}
//}



/*----------------------------------------------------------------------------------------------------------------
 Joker karakterler, parametreli türleri bir yönteme geçirirken esneklik sağlasa da, bazı dezavantajları da vardır.
 Yöntemin üstbilgisindeki joker karakter (?) bir type-parameter adı belirtmediğinden,
  bunu yöntemin gövdesi boyunca tür adı olarak kullanamazsınız (yani, 55. satırda Sayı yerine ? koyamazsınız).
  
  Ancak, yöntem toplamını aşağıdaki gibi bildirebilirsiniz:
  public static <T extends Number> double sum( ArrayList< T > list )
  
  Bu, yöntemin herhangi bir Number alt sınıfının öğelerini içeren bir ArrayList almasını sağlar.
  Daha sonra yöntem gövdesi boyunca T type parametresini kullanabilirsiniz.
  
  
  Joker karakter üst sınır olmadan belirtilirse, joker karakter türündeki değerlerde yalnızca Object türündeki yöntemler çağrılabilir.
  Ayrıca, parametrelerinin tür bağımsız değişkenlerinde joker karakterler kullanan yöntemler, 
  parametre tarafından başvurulan bir koleksiyona öğe eklemek için kullanılamaz.
  
  
  Bir yöntemin type-parameter bölümünde joker karakter kullanmak 
  veya yöntem gövdesinde açık bir değişken türü olarak joker karakter kullanmak bir sözdizimi hatasıdır.
 -----------------------------------------------------------------------------------------------------------------*/












/*----------------------------------------------------------------------------------------
Jenerikler kalıtımla birlikte çeşitli şekillerde kullanılabilir:
	Generic bir sınıf, generic olmayan bir sınıftan türetilebilir. Örneğin, Object sınıfı, her generic sınıfın doğrudan veya dolaylı bir üst sınıfıdır.
	Generic bir sınıf başka bir generic sınıftan türetilebilir.
	Generic olmayan bir sınıf, generic bir sınıftan türetilebilir.
	Son olarak, bir alt sınıftaki genel bir yöntem, her iki yöntem de aynı imzalara sahipse, bir üst sınıftaki genel bir yöntemi geçersiz kılabilir.
-----------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------
										ÖZET
-Genel yöntemler, tek bir yöntem bildirimiyle bir dizi ilgili yöntem belirtmenizi sağlar.
-Genel sınıflar ve arabirimler, ilgili türlerin kümelerini belirtmenizi sağlar.
										
-Aşırı yüklenmiş yöntemler genellikle farklı veri türlerinde benzer işlemleri gerçekleştirmek için kullanılır.
-Derleyici bir yöntem çağrısıyla karşılaştığında, yöntem çağrısındaki bağımsız değişken türleriyle uyumlu bir ad ve parametrelerle bir yöntem bildirimi bulmaya çalışır.

-Birkaç aşırı yüklenmiş yöntem tarafından gerçekleştirilen işlemler her bağımsız değişken türü için aynıysa, 
genel bir yöntem kullanılarak daha kompakt ve rahat bir şekilde kodlanabilirler.
Tek bir genericmethod bildirimi, farklı veri türlerinin bağımsız değişkenleriyle çağrılabilir. 
Genel bir yönteme geçirilen bağımsız değişkenlerin türlerine bağlı olarak, derleyici her yöntem çağrısını uygun şekilde işler.

-type-parameter bölümü, virgülle ayrılmış bir veya daha fazla tür parametresi içerir.
-type parametresi (s. 878), genel tür adını belirten bir tanımlayıcıdır. 
Tür parametreleri, genel bir yöntem bildiriminde dönüş türü, parametre türleri ve yerel değişken türleri olarak kullanılabilir
ve gerçek tür bağımsız değişkenleri olarak bilinen genel yönteme geçirilen bağımsız değişken türleri için yer tutucu görevi görürler

-Bir yöntem bildirimi boyunca kullanılan tür-parametre adları, tür-parametre bölümünde bildirilenlerle eşleşmelidir. 
typeparameter adı, type-parameter bölümünde yalnızca bir kez bildirilebilir, ancak yöntemin parametre listesinde birden çok kez görünebilir.

-Derleyici bir yöntem çağrısıyla karşılaştığında, bağımsız değişken türlerini belirler ve bağımsız değişken türleriyle eşleşen aynı ada ve parametrelere sahip bir yöntem bulmaya çalışır.
Varsa o yöntemi çağırır ama yoksa derleyici aynı ada ve uyumlu parametrelere sahip yöntemleri ve eşleşen genel yöntemleri arar

-İlkel türler için tüm tür sarmalayıcı sınıfları Comparable'ı implement eder.
-Genel bir yöntem derlendiğinde, derleyici tür parametre bölümünü kaldırmak ve tür parametrelerini gerçek türlerle değiştirmek için silme (s. 879) gerçekleştirir.

-Generic bir yöntem, diğer generic yöntemlerle veya generic olmayan yöntemlerle aşırı yüklenebilir.

-Genel sınıflar, bir sınıfı türden bağımsız bir şekilde tanımlamak için bir araç sağlar. Daha sonra genel sınıfın türe özgü nesnelerinin örneğini oluşturabiliriz.
-Genel sınıf bildirimi, sınıf adının ardından bir tür-parametre bölümü gelmesi dışında, genel olmayan bir sınıf bildirimine benzer.
Genel bir sınıfın type-parameter bölümü, virgülle ayrılmış bir veya daha fazla tür parametresine sahip olabilir.
-Genel bir sınıf derlendiğinde, derleyici sınıfın tür parametreleri üzerinde silme işlemi gerçekleştirir ve bunları üst sınırlarıyla değiştirir.
-Tür parametreleri bir sınıfın statik bildirimlerinde kullanılamaz.
-Bir tür bağımsız değişkeni belirtmeden genel bir sınıfın örneğini oluşturmak mümkündür. Bu durumda, sınıfın yeni nesnesinin ham bir türe sahip olduğu söylenir



-----------------------------------------------------------------------------------------*/



