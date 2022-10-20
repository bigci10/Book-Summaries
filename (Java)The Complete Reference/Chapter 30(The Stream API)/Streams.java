package stream;

import java.util.*;
import java.util.stream.Stream;

public class CompleteReferences {}

/*----------------------------------------------------------------------------------------------

	Yıllar geçtikçe, Java devam eden bir evrim sürecine girmiştir ve her sürüm, dilin zenginliğini ve gücünü genişleten özellikler eklemektedir. 
	Bu tür özel öneme sahip iki özellik, lambda ifadeleri ve akış API'sidir.
	
	Göreceğiniz gibi, akış API'si lambda ifadeleri göz önünde bulundurularak tasarlanmıştır. 
	Dahası, akış API'si, lambdaların Java'ya getirdiği gücün en önemli gösterimlerinden bazılarını sağlar.
	
	Lambda ifadeleriyle tasarım uyumluluğu etkileyici olsa da, akış API'sinin temel yönü, verileri arayan, filtreleyen, eşleyen veya başka bir şekilde işleyen çok karmaşık işlemler gerçekleştirme yeteneğidir. 
	Örneğin, akış API'sini kullanarak, kavram olarak, SQL kullanabileceğiniz veritabanı sorgularının türüne benzeyen eylem dizileri oluşturabilirsiniz. 
	Ayrıca, birçok durumda, bu tür eylemler paralel olarak gerçekleştirilebilir, böylece özellikle büyük veri kümeleri söz konusu olduğunda yüksek düzeyde verimlilik sağlanır. 
	Basitçe söylemek gerekirse, akış API'si verileri verimli ancak kullanımı kolay bir şekilde işlemek için güçlü bir araç sağlar.
	
	Devam etmeden önce önemli bir noktaya değinmek gerekiyor: Akış API'si, Java'nın en gelişmiş özelliklerinden bazılarını kullanır. Bunu tam olarak anlamak ve kullanmak, jeneriklerin ve lambda ifadelerinin sağlam bir şekilde anlaşılmasını gerektirir. 
	Paralel yürütme ve çalışmanın temel kavramları \nKoleksiyonlar Çerçevesi bilgisine de ihtiyaç vardır.
-------------------------------------------------------------------------------------------------*/

/*-------------------------------------------------------------------------------------------------------
		Stream Basics
	
	Stream terimini Stream API'si için geçerli olduğu şekilde tanımlayarak başlayalım: Stream, veriler için bir kanaldır.
	Böylece, bir Stream bir dizi nesneyi temsil eder.
	Stream, array veya collection gibi bir veri kaynağı üzerinde çalışır.
	Bir Streamin kendisi, veriler için hiçbir zaman depolama alanı sağlamaz.
	Verileri taşır, muhtemelen filtreleme, sıralama veya işlemdeki bu veriler üzerinde başka bir şekilde çalışır.
	Bununla birlikte, genel bir kural olarak, bir Stream işlemi tek başına veri kaynağını değiştirmez.
	Örneğin, bir Streami sıralamak kaynağın sırasını değiştirmez.
	Bunun yerine, bir streami sıralamak, sıralanmış sonucu üreten yeni bir stream oluşturulmasıyla sonuçlanır.
	

--------------------------------------------------------------------------------------------------------*/




/*---------------------------------------------------------------------------------------------
  			Stream Interfaces
  	
  	Stream API'si, java.util.stream içinde paketlenmiş ve java.base modülünde bulunan çeşitli stream arabirimlerini tanımlar.
  	Temelde, tüm streamlerde bulunan temel işlevselliği tanımlayan BaseStream bulunur.
  	BaseStream şu şekilde bildirilen generic bir arayüzdür:
  		interface BaseStream<T,S extends BaseStream<T,S>>
  		Burada T, akıştaki öğelerin türünü belirtir
  		ve S, BaseStream'i genişleten akış türünü belirtir.
  		BaseStream AutoCloseble arabirimini genişletir; Böylece, bir akış kaynaklarla try-with-resources ile yönetilebilir.
  		Ancak genel olarak, yalnızca veri kaynağı kapatılmasını gerektiren akışların (bir dosyaya bağlı olanlar gibi) kapatılması gerekir.

  	
  	Veri kaynağının bir koleksiyon olduğu durumlar gibi çoğu durumda, akışı kapatmaya gerek yoktur. BaseStream tarafından bildirilen yöntemler Tablo 30-1'de gösterilmiştir.

------------------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------------------------
 		BASESTREAM METHODS
 		
 	void close():Çağırma akışını kapatır ve kayıtlı tüm yakın işleyicileri çağırır.
 	
 	boolean isParallel():Çağıran stream paralelse true değerini döndürür. Stream sequential ise false değerini döndürür.
 	
 	Iterator<T>iterator():Streame bir yineleyici alır ve ona bir başvuru döndürür.(terminal operation)
 	
 	S onClose(Runnable handler):İşleyici tarafından belirtilen yakın işleyici ile yeni bir akış döndürür. Bu işleyici, akış kapatıldığında çağrılır.(Intermediate Operation)
 	
 	S parallel():Çağıran akışı temel alan paralel bir akış döndürür. Çağıran akış zaten paralelse, bu akış döndürülür.(Intermediate Operation)
 	
 	S sequential():Çağıran akışı temel alan sıralı bir akış döndürür. Çağıran akış zaten sıralıysa bu akış döndürülür.(Intermediate Operation)
 	
 	Spliterator<T> spliterator():Akışa bir ayırıcı alır ve ona bir başvuru döndürür.(Terminal Operation)
 	
 	S unordered() :Çağıran akışı temel alan sıralanmamış bir akış döndürür. Çağıran akış zaten sıralanmamışsa bu akış döndürülür.(Intermediate Operations)
 	
 
 
 BaseStream'den çeşitli akış arabirimi türleri türetilir. Bunlardan en geneli Stream'dir. Burada gösterildiği gibi beyan edilir:
 	interface Stream<T>
 	Burada T, akıştaki öğelerin türünü belirtir. Genel olduğundan, Stream tüm başvuru türleri için kullanılır. 
 	BaseStream'den devraldığı yöntemlere ek olarak, Stream arabirimi, bir örneklemesi aşağıdaki tabloda gösterilen kendi yöntemlerinden birkaçını ekler.
--------------------------------------------------------------------------------------------------------------*/



/*------------------------------------------------------------------------------------------------------------
  			STREAM METHODS
  	<R, A> R collect(Collector<? super T, A, R> collectorFunc)
  		Öğeleri değiştirilebilir bir kapsayıcıda toplar ve kapsayıcıyı döndürür.
  		Buna değişken indirgeme işlemi denir.
  		Burada, R elde edilen kabın türünü belirtir ve T, çağırma akışının öğe türünü belirtir.
  		A specifies the internal accumulated type. 
  		collectorFunc, collection işleminin nasıl çalıştığını belirtir. (Terminal Operation)

	
	
	long count() : Akıştaki öğelerin sayısını sayar ve sonucu döndürür. (Terminal Operation)
	
	Stream<T> filter(Predicate<? super T> pred)
		Çağıran akıştan pred tarafından belirtilen yüklemi karşılayan öğeleri içeren bir akış üretir.
		(predicate true ya da false dönüyordu bu şekilde filterı uyguluyoruz)
		(Intermediate Operations)

	
	void forEach(Consumer<? super T> action)
		Çağırma akışındaki her öğe için, eylem tarafından belirtilen kod yürütülür. (Terminal operation.)
		
	
	
	<R> Stream<R> map(Function<? super T,? extends R> mapFunc)
		mapFunc'u çağıran akıştaki öğelere uygulayarak bu öğeleri içeren yeni bir akış oluşturur. (Intermediate operation.)
	
	
	DoubleStream mapToDouble(ToDoubleFunction<? super T> mapFunc)
		mapFunc'u çağıran akıştaki öğelere uygulayarak bu öğeleri içeren yeni bir DoubleStream oluşturur.(Intermediate operation.)

	
	IntStream mapToInt(ToIntFunction<? super T> mapFunc)
		mapFunc'u çağıran akıştaki öğelere uygulayarak bu öğeleri içeren yeni bir IntStream oluşturur.(Intermediate operation.)
		
	LongStream mapToLong(ToLongFunction<? super T> mapFunc)	
		mapFunc'u çağıran akıştaki öğelere uygulayarak bu öğeleri içeren yeni bir LongStream oluşturur. (Intermediate operation.)
	
	Optional<T> max(Comparator<? super T> comp)
		Comp tarafından belirtilen sıralamayı kullanarak, çağırma akışındaki maksimum öğeyi bulur ve döndürür. (Terminal operation.)

	Optional<T> min(Comparator<? super T> comp)
		Comp tarafından belirtilen sıralamayı kullanarak, çağırma akışındaki minimum öğeyi bulur ve döndürür. (Terminal operation.)


	T reduce(T identityVal,BinaryOperator<T> accumulator)
		Çağırma akışındaki öğeleri temel alan bir sonuç döndürür. Buna indirgeme işlemi denir. (Terminal operation.)
		
	Stream<T> sorted( )
		Doğal düzende sıralanmış çağıran akışın öğelerini içeren yeni bir akış üretir. (Intermediate Operation.)
	
	Object[] toArray( )
		Çağırma akışındaki öğelerden bir dizi oluşturur. (Terminal operation.)
	
	default List<T> toList( )
		Çağırma akışındaki öğelerden değiştirilemez bir liste oluşturur (Terminal operation.)
	
--------------------------------------------------------------------------------------------------------------*/




/*------------------------------------------------------------------------------------------------------------
 	Her iki tabloda da, yöntemlerin çoğunun terminal veya intermediate olarak belirtildiğine dikkat edin.
 	İkisi arasındaki fark çok önemlidir. Bir terminal işlemi akışı tüketir.
 	Akıştaki minimum değeri bulmak gibi bir sonuç üretmek veya forEach( ) yönteminde olduğu gibi bazı eylemleri yürütmek için kullanılır.
 	Bir akış tüketildikten sonra yeniden kullanılamaz.
 	
 	intermediate işlemler başka bir akış üretir. Böylece, intermediate işlemler bir dizi eylem gerçekleştiren bir işlem hattı oluşturmak için kullanılabilir.
 	Bir başka nokta: ara işlemler hemen gerçekleşmez. Bunun yerine, belirtilen eylem, bir ara işlem tarafından oluşturulan yeni akışta bir terminal işlemi yürütüldüğünde gerçekleştirilir.
 	
 	This mechanism is referred to as lazy behavior, and the intermediate operations are referred to as lazy. 
 	
 	lazy behavior kullanılması, akış API'sinin daha verimli çalışmasını sağlar.
 	Akışların bir diğer önemli yönü, bazı ara işlemlerin durumsuz ve bazılarının durum bilgisi olmasıdır.
 	
 	Durum bilgisi olmayan bir işlemde, her öğe diğerlerinden bağımsız olarak işlenir.
 	Durum bilgisi olan bir işlemde, bir öğenin işlenmesi diğer öğelerin yönlerine bağlı olabilir.
 	
 	Örneğin, sorting durum bilgisi olan bir işlemdir, çünkü bir öğenin sırası diğer öğelerin değerlerine bağlıdır. Böylece, sorted( ) yöntemi durum bilgisine sahiptir.
 	Ancak, öğeleri durum bilgisi olmayan bir yüklemi temel alan filtreleme durum bilgisi yoktur, çünkü her öğe ayrı ayrı işlenir. Bu nedenle, filter( ) durum bilgisi olmayan olabilir (ve olmalıdır).
 	
 	
 	Durum bilgisi olmayan ve durum bilgisi olan işlemler arasındaki fark, bir akışın paralel işlenmesi istendiğinde özellikle önemlidir, 
 	çünkü durum bilgisi olan bir işlemin tamamlanması için birden fazla geçiş gerekebilir.
 	
 	Stream nesne başvuruları üzerinde çalıştığından, doğrudan ilkel türlerde çalışamaz. 
 	İlkel tür akışları işlemek için akış API'si aşağıdaki arabirimleri tanımlar:
 		DoubleStream , IntStream , LongStream
 		
 	Bu akışların tümü BaseStream'i genişletir ve başvuru türleri yerine ilkel türlerde çalışmaları dışında Stream'e benzer özelliklere sahiptir.
 	
 		
---------------------------------------------------------------------------------------------------------------*/


/*-------------------------------------------------------------------------------------------------------------
 		How to Obtain a Stream
 	
 	Bir akışı çeşitli şekillerde elde edebilirsiniz. Belki de en yaygın olanı, bir koleksiyon için bir akış elde edildiğindedir.
 	JDK 8'den başlayarak, Koleksiyon arabirimi bir koleksiyondan akış elde eden iki yöntem içerecek şekilde genişletildi. Birincisi stream( ), burada gösterilmiştir:
 		default Stream<E> stream( )
 		
 	
 	Varsayılan uygulaması sıralı bir akış döndürür. İkinci yöntem parallelStream( ), daha sonra gösterilmiştir:
 		default Stream<E> parallelStream( )
 	
 	Varsayılan uygulaması, mümkünse paralel bir akış döndürür. (Paralel bir akış elde edilemezse, bunun yerine sıralı bir akış döndürülebilir.)
 	Paralel akışlar, akış işlemlerinin paralel yürütülmesini destekler.
 	Collection her koleksiyon tarafından uygulandığından, bu yöntemler ArrayList veya HashSet gibi herhangi bir koleksiyon sınıfından akış elde etmek için kullanılabilir.
 	
 	Bir diziden akış, Arrays sınıfına eklenen static stream( ) yöntemi kullanılarak da elde edilebilir. Formlarından biri burada gösterilmiştir:
 		static <T> Stream<T> stream(T[ ] array)
 		
 	Bu yöntem, dizideki öğelere sıralı bir akış döndürür. Örneğin, Adres türünde adresler adlı bir dizi verildiğinde, aşağıdakiler bu diziye bir akış sağlar:
 		Stream<Address> addrStrm = Arrays.stream(addresses);
 	
 	stream( ) yönteminin ilkel türlerin dizilerini işleyenler gibi çeşitli aşırı yükleri de tanımlanır. IntStream, DoubleStream veya LongStream türünde bir akış döndürürler.
 	
---------------------------------------------------------------------------------------------------------------*/





/*-------------------------------------------------------
  Daha ileri gitmeden önce, akışları kullanan bir örnek üzerinde çalışalım. 
  Aşağıdaki program, tamsayı koleksiyonunu (otomatik olarak Tamsayı başvuru türüne kutulanır) tutan myList adlı bir ArrayList oluşturur. 
  Ardından, kullanan bir akış elde eder \nkaynak olarak myList. Daha sonra çeşitli akış işlemlerini gösterir.
---------------------------------------------------------*/

class Example1
{
	public static void main(String[] args) 
	{
		ArrayList<Integer> myList = new ArrayList<>();
		myList.add(7);
		myList.add(8);
		myList.add(4);
		myList.add(3);
		myList.add(9);
		myList.add(2);
		
		
		System.out.println("Original list :" + myList);
		
		//array listten bi stream oluşturuyoruz 
		Stream<Integer> myStream = myList.stream();
		
		//min ve max değeri elde ediyorum 
		Optional<Integer> minVal = myStream.min(Integer :: compare);
		if (minVal.isPresent()) System.out.println("Minumum value: " + minVal.get());
		
		//yeni bir stream oluşturmamız lazım çünkü bi öncekinde oluşturduğumuz streami tükettik 
		myStream = myList.stream();
		Optional<Integer> maxVal = myStream.max(Integer::compare);
		if(maxVal.isPresent()) System.out.println("Maximum value: " + maxVal.get());
	
		//Sıralama yapmak için sorted kullanıyoruz
		Stream<Integer> sortedStream = myList.stream().sorted();
		
		//Sıralama yaptıktan sonra listemizi bastırıyoruz 
		sortedStream.forEach((n) -> System.out.print(n + " "));
		System.out.println();
		
		//sadece tek sayıları bastıralım listedeki 
		Stream<Integer> oddVals = myList.stream().sorted().filter((n) -> (n % 2) == 1);
		System.out.print("Odd values: ");
		oddVals.forEach((n) -> System.out.print(n + " "));
		System.out.println();
		
		//sadece tek ve 5 den büyük sayıları bastıralım 
		oddVals = myList.stream().filter((n) -> (n % 2) == 1).filter((n) -> n > 5);
		System.out.print("Odd values greater than 5: ");
		oddVals.forEach((n) -> System.out.print(n + " ") );
		System.out.println();
		
		
		
		
	}
}

/*------------------------------------------------------------------------------------------------------
 	Her akış işlemine yakından bakalım. Bir ArrayList oluşturduktan sonra, program burada gösterildiği gibi stream( ) öğesini çağırarak liste için bir akış alır:
 	Stream<Integer> myStream = myList.stream();
 	
 	Açıklandığı gibi, Koleksiyon arabirimi, çağıran koleksiyondan bir akış elde eden stream( ) yöntemini tanımlar.
 	
 	Collection her koleksiyon sınıfı tarafından uygulandığından, stream( ) burada kullanılan ArrayList de dahil olmak üzere herhangi bir koleksiyon türü için bir akış elde etmek üzere kullanılabilir.
 	Bu durumda, myStream öğesine akışa bir başvuru atanır.
 	
 	Ardından, program akıştaki minimum değeri (elbette veri kaynağındaki minimum değerdir) alır ve burada gösterildiği gibi görüntüler:
 		Optional<Integer> minVal = myStream.min(Integer::compare);
 		if(minVal.isPresent()) System.out.println("Minimum value: " + minVal.get());
 	
 	Optional<T> min(Comparator<? super T> comp)
	
	İlk olarak, min( )'s parametresinin türünün bir Comparator olduğuna dikkat edin.
	Bu karşılaştırıcı, akıştaki iki öğeyi karşılaştırmak için kullanılır. 
	Örnekte, min( ), iki Tamsayıyı karşılaştırabilen bir Karşılaştırıcı uygulamak için kullanılan 
	Tamsayı'nın compare( ) yöntemine bir yöntem başvurusu geçirilir.
	
	Ardından, min( ) dönüş türünün optional olduğuna dikkat edin.
	
	Optional, java.util dosyasında paketlenmiş ve şu şekilde bildirilen genel bir sınıftır:
	class Optional<T>
		Burada, T öğe türünü belirtir. İsteğe bağlı bir örnek, T türünde bir değer içerebilir veya boş olabilir. Bir değerin mevcut olup olmadığını belirlemek için isPresent( ) öğesini kullanabilirsiniz.
	
	Önceki satırla ilgili bir başka nokta: min( ), akışı tüketen bir terminal işlemidir. Bu nedenle, myStream min( ) çalıştırıldıktan sonra tekrar kullanılamaz.
		
		
	

 	Sonraki satırlar akıştaki maksimum değeri alır ve görüntüler:
 		myStream = myList.stream(); 
 		Optional<Integer> maxVal = myStream.max(Integer::compare);
 		if(maxVal.isPresent()) System.out.println("Maximum value: " + maxVal.get());
 		
 	İlk olarak, myStream bir kez daha myList.stream( ) tarafından döndürülen akışa atanır. 
 	Az önce açıklandığı gibi, bu gereklidir çünkü min( ) için önceki çağrı önceki akışı tüketmiştir. 
 	Bu nedenle, yenisine ihtiyaç vardır.
 	Ardından, maksimum değeri elde etmek için max( ) yöntemi çağrılır. Min( ), max( ) gibi bir İsteğe Bağlı nesne döndürür. Değeri, get( ) çağrılarak elde edilir.
 	
 	Program daha sonra bu satırı kullanarak sıralanmış bir akış elde eder:
 		Stream<Integer> sortedStream = myList.stream().sorted();
 	
 	Burada, myList.stream( ) tarafından döndürülen akışta sorted( ) yöntemi çağrılır. sorted( ) bir ara işlem olduğundan, sonucu yeni bir akıştır ve bu, sortedStream öğesine atanan akıştır. 
 	Sıralanmış akışın içeriği forEach( ) kullanılarak görüntülenir:
 		sortedStream.forEach((n) -> System.out.print(n + " "));
 		
 	Burada, forEach( ) yöntemi akıştaki her öğe üzerinde bir işlem yürütür. Bu durumda, sortedStream içindeki her öğe için System.out.print( ) öğesini çağırır. Bu, lambda ifadesi kullanılarak gerçekleştirilir. forEach( ) yöntemi şu genel biçime sahiptir:
 		void forEach(Consumer<? super T> action)
 	
 	Consumer, java.util.function dosyasında bildirilen genel bir işlevsel arabirimdir. Soyut yöntemi accept( ), burada gösterilmiştir:
 		void accept(T objRef)
 	
 	forEach( ) çağrısındaki lambda ifadesi, accept( ) öğesinin uygulanmasını sağlar. forEach( ) yöntemi bir terminal işlemidir. Böylece tamamlandıktan sonra stream tüketilmiş olur.
 	
 	
 	
 	
 	Ardından, sıralanmış bir akışa filter( ) göre filtre uygulanır, böylece yalnızca tek değerler içerir:
 		Stream<Integer> oddVals = myList.stream().sorted().filter((n) -> (n % 2) == 1);
 	filter( ) yöntemi, bir yüklemi temel alan bir akışı filtreler. Yalnızca yüklemi karşılayan öğeleri içeren yeni bir akış döndürür. Burada gösterilmiştir:
 		Stream<T> filter(Predicate<? super T> pred)
 		
 	Predicate, java.util.function dosyasında tanımlanan genel bir işlevsel arabirimdir. Soyut yöntemi test( ) dir ve burada gösterilmiştir:
 		boolean test(T objRef)
 	objRef tarafından başvurulan nesne yüklemi karşılıyorsa true değerini ve aksi takdirde false değerini döndürür.
 	filter( ) öğesine geçirilen lambda ifadesi bu yöntemi uygular. 
 	filter( ) bir ara işlem olduğundan, bu durumda tek sayılar olan filtrelenmiş değerleri içeren yeni bir akış döndürür. 
 	Bu öğeler daha sonra daha önce olduğu gibi forEach( ) aracılığıyla görüntülenir.
 	
 	filter( ) veya başka bir ara işlem yeni bir akış döndürdüğünden, filtrelenmiş bir akışa ikinci kez filtre uygulamak mümkündür. 
 	Bu, yalnızca 5'ten büyük olan tek değerleri içeren bir akış üreten aşağıdaki satırla gösterilir:
 		oddVals = myList.stream().filter((n) -> (n % 2) == 1).filter((n) -> n > 5);
 	
-------------------------------------------------------------------------------------------------------------------*/







/*
 		Reduction Operations
 		
 	Önceki örnek programdaki min( ) ve max( ) yöntemlerini göz önünde bulundurun.
 	Her ikisi de akıştaki öğelere dayalı bir sonuç döndüren terminal işlemleridir.
 	Akış API'sinin dilinde, her biri bir akışı bu durumda minimum ve maksimum olmak üzere tek bir değere indirgediği için reduction işlemlerini temsil eder.
 	Akış API'si, belirli bir işlevi yerine getirdikleri için bunları özel durum azaltmaları olarak adlandırır.
 	Min( ) ve max( ) öğelerine ek olarak, bir akıştaki öğe sayısını sayan count( ) gibi başka özel durum azaltmaları da mevcuttur.
 	
 	Ancak, akış API'si reduce( ) yöntemini sağlayarak bu kavramı genelleştirir.
 	
 	reduce( ) komutunu kullanarak, herhangi bir rasgele ölçüte göre akıştan bir değer döndürebilirsiniz. Tanım gereği, tüm indirgeme işlemleri terminal işlemleridir.
 	Akış, reduce( )'nin üç sürümünü tanımlar. İlk olarak kullanacağımız ikisi burada gösterilmiştir:
 		Optional<T> reduce(BinaryOperator<T> accumulator)
 		T reduce(T identityVal, BinaryOperator<T> accumulator)
 	
 	İlk form, sonucu içeren Optional türünde bir nesne döndürür.
 	İkinci form, T türünde bir nesne döndürür (akışın öğe türüdür).
 	
 	Her iki formda da accumulator , iki değer üzerinde çalışan ve bir sonuç üreten bir fonksiyondur.
 	İkinci formda, identityVal, identityVal ve akışın herhangi bir öğesini içeren bir akümülatör işleminin bu öğeyi değişmeden vereceği bir değerdir.
 	
 	BinaryOperator, java.util.function içinde bildirilen ve BiFunction işlevsel arabirimini genişleten işlevsel bir arabirimdir. BiFunction bu soyut yöntemi tanımlar:
 		R apply(T val, U val2)
 		Burada, R sonuç türünü belirtir, T ilk işlenenin türüdür ve U ikinci işlenenin türüdür.
 		Böylece, apply( ) bir işlevi iki işlenenine (val ve val2) uygular ve sonucu döndürür.
 	
 	BinaryOperator BiFunction'ı genişlettiğinde, tüm type parametreleri için aynı türü belirtir.
 		Bu nedenle, BinaryOperator ile ilgili olarak, apply( ) şöyle görünür:
 		T apply(T val, T val2)
 		Ayrıca, reduce( ) ile ilgili olduğu için, val önceki sonucu içerecek ve val2 bir sonraki öğeyi içerecektir.
 	
 	İlk çağrısında val, reduce( ) öğesinin hangi sürümünün kullanıldığına bağlı olarak kimlik değerini veya ilk öğeyi içerir.
 	Akümülatör işleminin üç kısıtlamayı karşılaması gerektiğini anlamak önemlidir. Olmalı
 	
 	-Stateless
 	-Non-interfering
 	-Associative

	Stateless
	Daha önce açıklandığı gibi, durum bilgisi olmadan, işlemin herhangi bir durum bilgisine dayanmadığı anlamına gelir.
	Böylece, her eleman bağımsız olarak işlenir. Müdahale etmeme, veri kaynağının işlem tarafından değiştirilmediği anlamına gelir.
	
	
	Associative
	Son olarak, işlem ilişkisel olmalıdır. Burada, ilişkisel terimi normal, aritmetik anlamında kullanılır; 
	bu, bir dizi işlemde kullanılan ilişkisel bir operatör göz önüne alındığında, önce hangi işlenen çiftinin işlendiğinin önemli olmadığı anlamına gelir.
	Örneğin:
		(10 * 2) * 7
		10 * (2 * 7)
		
	İlişkisellik, bir sonraki bölümde tartışılan paralel akışlarda indirgeme işlemlerinin kullanımı için özellikle önemlidir.
 	
*/

class Example2
{
	public static void main(String[] args) 
	{
		ArrayList<Integer> myList = new ArrayList<>( );
		myList.add(7);
		myList.add(18);
		myList.add(10);
		myList.add(24);
		myList.add(17);
		myList.add(5);
		
		
		Optional<Integer> productObj = myList.stream().reduce((a,b) -> a*b);
		if(productObj.isPresent()) System.out.println("Product as Optional: " + productObj.get());
			
		int product = myList.stream().reduce(1, (a,b) -> a*b);
		System.out.println("Product as int: " + product);
		
	}
}

/*---------------------------------------------------------------------------------------------------------
 	Programda, reduce( ) öğesinin ilk sürümü, iki değerden oluşan bir ürün üretmek için lambda ifadesini kullanır. 
 	Bu durumda, akış Tamsayı değerleri içerdiğinden, Tamsayı nesneleri çarpma için otomatik olarak kutudan çıkarılır ve sonucu döndürmek için yeniden kutulanır.
 	
 	İki değer, çalışan sonucun geçerli değerini ve akıştaki bir sonraki öğeyi temsil eder. 
 	Nihai sonuç, İsteğe Bağlı(optional) türünde bir nesneyle döndürülür. 
 	Değer, döndürülen nesnede get( ) çağrılarak elde edilir.
 	
 	İkinci sürümde, çarpma için 1 olan kimlik değeri açıkça belirtilir. 
 	Sonucun, bu durumda Tamsayı olan öğe türünün bir nesnesi olarak döndürüldüğüne dikkat edin.
 	
 	Çarpma gibi basit indirgeme işlemleri örnekler için yararlı olsa da, azaltmalar bu konuda sınırlı değildir. 
 	Örneğin, önceki programı varsayarsak, aşağıdakiler yalnızca çift değerlerin ürününü elde eder:
 		
 		int evenProduct = myList.stream().reduce(1, (a,b) -> {if(b%2 == 0) return a*b; else return a;})
 	
 	Lambda ifadesine özellikle dikkat edin. 
 	Eğer b çift ise, o zaman a * b döndürülür. 
 	Aksi takdirde, a döndürülür. 
 	Bu işe yarar, çünkü a mevcut sonucu tutar ve b, daha önce açıklandığı gibi bir sonraki elemanı tutar.
 	
-----------------------------------------------------------------------------------------------------------*/



/*-------------------------------------------------------------------------------------------------
 				Using Parallel Streams

 	Akış API'sini daha fazla keşfetmeden önce, paralel akışları tartışmak yararlı olacaktır.
 	Bu kitapta daha önce de belirtildiği gibi, kodun çok çekirdekli işlemciler aracılığıyla paralel olarak yürütülmesi, performansta önemli bir artışa neden olabilir.
 	
 	Bu nedenle, paralel programlama modern programcının işinin önemli bir parçası haline gelmiştir. Bununla birlikte, paralel programlama karmaşık ve hataya açık olabilir.
 	Akış kitaplığının sunduğu avantajlardan biri, belirli işlemleri kolay ve güvenilir bir şekilde paralel işleme yeteneğidir.
 	
 	Bir akışın paralel işlenmesini istemek oldukça basittir: sadece paralel bir akış kullanın.
 	
 	Daha önce de belirtildiği gibi, paralel akış elde etmenin bir yolu,
 	Collection tarafından tanımlanan parallelStream( ) yöntemini kullanmaktır.
 	 
 	Paralel akış elde etmenin başka bir yolu da sıralı bir akışta parallel( )yöntemini çağırmaktır. parallel( ) yöntemi, burada gösterildiği gibi BaseStream tarafından tanımlanır:
 	S parallel()
 	Kendisini çağıran sıralı akışı temel alan paralel bir akış döndürür.
 	Elbette, paralel bir akışla bile, paralelliğin ancak çevre onu destekliyorsa elde edileceğini anlayın.
 	
 	
 	Paralel bir akış elde edildikten sonra, paralelliğin çevre tarafından desteklendiği varsayılarak, 
 	akıştaki işlemler paralel olarak gerçekleşebilir.
 	
 	Örneğin, önceki programdaki ilk reduce( ) işlemi, stream( ) çağrısı yerine parallelStream( ) kullanılarak paralelleştirilebilir:
 		Optional<Integer> productObj = myList.parallelStream().reduce((a,b) -> a*b);
 		Sonuçlar aynı olacaktır, ancak çarpmalar farklı iş parçacıklarında gerçekleşebilir.
 		
 	Genel bir kural olarak, paralel akışa uygulanan herhangi bir işlem durum bilgisi olmadan olmalıdır.
 	
 	Aynı zamanda müdahaleci ve ilişkisel olmamalıdır. 
 	Bu, paralel bir akışta işlemlerin yürütülmesiyle elde edilen sonuçların, aynı işlemlerin sıralı bir akışta yürütülmesinden elde edilenlerle aynı olmasını sağlar.
 	
 	Paralel akışları kullanırken, aşağıdaki reduce( ) sürümünü özellikle yararlı bulabilirsiniz. Kısmi sonuçların nasıl birleştirileceğini belirtmenin bir yolunu sunar:
 		<U> U reduce(U identityVal, BiFunction<U, ? super T, U> accumulatorBinaryOperator<U> combiner)
 	
 	Bu sürümde, birleştirici akümülatör işlevi tarafından üretilen iki değeri birleştiren işlevi tanımlar.
 	Önceki programı varsayarsak, aşağıdaki deyim myList'teki öğelerin ürününü paralel bir akış kullanarak hesaplar:
 	int parallelProduct = myList.parallelStream().reduce(1, (a,b) -> a*b, (a,b) -> a*b); 
 	
 	gördüğünüz gibi, bu örnekte, hem akümülatör hem de birleştirici aynı işlevi yerine getirir.
 	
 	Bununla birlikte, akümülatörün eylemlerinin birleştiricininkilerden farklı olması gereken durumlar vardır. Örneğin, aşağıdaki programı düşünün.
 	
 	Burada, myList çift değerlerin bir listesini içerir. Daha sonra listedeki her öğenin kare köklerinin ürününü hesaplamak için reduce( ) öğesinin birleştirici sürümünü kullanır.
------------------------------------------------------------------------------------------*/

class Example3
{
	public static void main(String[] args) 
	{
		ArrayList<Double> myList = new ArrayList<>( );
		
		myList.add(7.0);
		myList.add(18.0);
		myList.add(10.0);
		myList.add(24.0);
		myList.add(17.0);
		myList.add(5.0);
		
		double productOfSqrRoots = myList.parallelStream().reduce(1.0, (a,b) -> a * Math.sqrt(b),(a,b) -> a * b);
	
		
	
	}
}

/*
 Akümülatör fonksiyonunun iki elemanın kare köklerini çarptığına, ancak birleştiricinin kısmi sonuçları çarptığına dikkat edin.
 Bu nedenle, iki işlev farklıdır. Dahası, bu hesaplamanın doğru çalışması için farklı olmaları gerekir.
 
 Örneğin, aşağıdaki ifadeyi kullanarak öğelerin kare köklerinin ürününü elde etmeye çalışırsanız, bir hataya neden olur:
 	error : double productOfSqrRoots2 = myList.parallelStream().reduce(1.0, (a,b) -> a * Math.sqrt(b));
 
 Reduce( )'nin bu versiyonunda, akümülatör ve birleştirici fonksiyonu bir ve aynıdır.
 Bu bir hataya neden olur, çünkü iki kısmi sonuç birleştirildiğinde, kısmi sonuçların kendileri yerine kare kökleri birlikte çarpılır.
 
 İlgi çekici bir nokta olarak, önceki reduce( ) çağrısındaki akış sıralı bir akışa değiştirilseydi, 
 işlem doğru cevabı verirdi, çünkü iki kısmi sonucu birleştirmeye gerek kalmazdı.
 Paralel akış kullanıldığında sorun oluşur.
 
 BaseStream tarafından belirtilen sequential( ) yöntemini çağırarak paralel akışı sequential olarak değiştirebilirsiniz. Burada gösterilmiştir:
 	S sequential( )
 	
 Genel olarak, bir akış gerektiğinde paralel ve sıralı arasında değiştirilebilir.
 
 Paralel yürütme kullanırken akılda tutulması gereken bir akışın başka bir yönü daha vardır: öğelerin sırası.
 Akışlar sıralı veya sırasız olabilir. 
 Genel olarak, veri kaynağı sıralanırsa, akış da sıralanır.
 
 Ancak, paralel akış kullanırken, bazen bir akışın sırasız olmasına izin vererek bir performans artışı elde edilebilir.
 Paralel bir akış sıralanmadığında, akışın her bölümü diğerleriyle koordine olmak zorunda kalmadan bağımsız olarak çalıştırılabilir.
 
 İşlemlerin sırasının önemli olmadığı durumlarda, burada gösterilen unordered( ) yöntemini çağırarak sırasız davranışı belirtmek mümkündür:
 	S unordered( )
 	
 
 Başka bir nokta: forEach( ) yöntemi paralel bir akışın sırasını koruyamayabilir.
 Sırayı korurken paralel akıştaki her öğe üzerinde bir işlem gerçekleştirmek istiyorsanız, forEachOrdered( ) öğesini kullanmayı düşünün. Tıpkı forEach( ) gibi kullanılır.
  
 
 */

