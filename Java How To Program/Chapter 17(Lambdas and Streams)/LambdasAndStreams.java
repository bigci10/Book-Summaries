

public class LambdasAndStreams {}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
Java programlama hakkında düşünme şekliniz derinden değişmek üzere. 
Java SE 8'den önce Java üç programlama paradigmasını destekliyordu: prosedürel programlama, nesne yönelimli programlama ve genel programlama. 
Java SE 8, işlevsel programlamanın temel teknolojileri olan lambda'ları ve akışları ekledi. 

Bu bölümde, belirli program türlerini önceki tekniklerden daha hızlı, 
daha basit, daha özlü ve daha az hatayla yazmak için lambdaları ve akışları kullanacağız.

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
				Streams and Reduction

	counter-controlled iteration yaparken, genellikle neyi başarmak istediğinizi belirlersiniz, 
	ardından bir for döngüsü kullanarak tam olarak nasıl gerçekleştirileceğini belirtirsiniz.
	Bu bölümde, bu yaklaşımı inceleyeceğiz ve ardından size aynı görevleri yerine getirmenin daha iyi bir yolunu göstereceğiz.
	
	Başarmak istediğiniz şeyin, 1'den 10'a kadar olan tamsayıları toplamak olduğunu varsayalım.
	ve bunu counter-controlled iteration kullanarak yapalım
	
		int total = 0;
		for(int number = 1; number <= 10; number++)
		{
			total += number;
		}

	Bu döngü, kontrol değişkeni numarasının her değerini 1'den 10'a kadar işleyen, döngü yinelemesi başına bir kez sayıya sayının geçerli değerini ekleyen
	ve her toplama işleminden sonra sayıyı artıran bir for deyimiyle görevin tam olarak nasıl gerçekleştirileceğini belirtir. 
	Bu, dış yineleme olarak bilinir, çünkü tüm yineleme ayrıntılarını belirtirsiniz.
		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		External Iteration with for Is Error Prone
		
	Önceki kodla ilgili olası sorunları göz önünde bulunduralım. 
	Uygulandığı gibi, döngü, her döngü yinelemesi sırasında kodun mutasyona uğrattığı (yani değiştirdiği) iki değişken (toplam ve sayı) gerektirir.
	Bir değişkeni değiştiren kodu her yazdığınızda, kodunuza bir hata eklemek mümkündür. 
	
	Önceki kodda hata için birkaç fırsat vardır. Örneğin, şunları yapabilirsiniz:
		-toplam değişkenini yanlış başlatın.
		-for döngüsünün kontrol değişkeni numarasını yanlış başlatın.
		- yanlış döngü devam koşulunu kullanın.
		-artış denetimi değişken numarası yanlış veya yanlış her sayı değerini toplamın içine ekleyin.
	
	Ayrıca, gerçekleştirdiğiniz görevler daha karmaşık hale geldikçe, kodun nasıl çalıştığını anlamak, ne yaptığını anlamanın önüne geçer. 
	Bu, kodun okunmasını, hata ayıklamasını ve değiştirilmesini zorlaştırır ve hata içerme olasılığını artırır.		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Summing with a Stream and Reduction
		
	Şimdi farklı bir yaklaşım benimseyelim, nasıl yapılacağından ziyade ne yapılacağını belirleyelim. 
	ve 1 den 10 a kadar olan sayıların toplamını bulalım.
	
	IntStream.rangeClosed(1,10).sum();
	
	
 	"Streams and Stream Pipelines"
 		yukarıdaki satırdaki zincirleme yöntem çağrıları bir akış işlem hattı oluşturur.
 		Akış, üzerinde görevler gerçekleştirdiğiniz bir öğe dizisidir ve akış ardışık düzeni, 
 		akışın öğelerini bir dizi görev (veya işleme adımı) boyunca taşır. 
 
 	"Specifying the Data Source"
 		Akış işlem hattı genellikle veri kaynağı olarak bilinen akışı oluşturan bir yöntem çağrısıyla başlar.
 		yukarıdaki kodda, sıralı bir int değerleri aralığını temsil eden bir IntStream oluşturan yöntem çağrısıyla veri kaynağını belirtir.
 		Burada, 1, 2, 3, 4, 5, 6, 7, 8, 9 ve 10 int öğelerinin sıralı dizisini içeren bir IntStream oluşturmak için rangeClosed statik yöntemini kullanıyoruz.
 	
 		Yöntem rangeClosed olarak adlandırılır, çünkü kapalı bir değer aralığı, yani yöntemin bağımsız değişkenlerinin her ikisini de (1 ve 10) içeren bir öğe aralığı üretir.
 		sum, IntStream'in toplam  yöntemini çağırır, bu durumda akıştaki tüm int'lerin toplamını, 1'den 10'a kadar olan tamsayıların toplamını döndürür. 
 	
 		Yöntem sum() tarafından gerçekleştirilen işleme adımı, değer akışını tek bir değere (toplam) indirgeyen bir reduction olarak bilinir.
 	
 	
 	"Processing the Stream Pipeline"
 		Terminal işlemi, bir akış işlem hattının işlenmesini başlatır ve bir sonuç üretir.
 		IntStream sum() metodu, akışın öğelerinin toplamını üreten bir terminal işlemidir.
 		Benzer şekilde, count, min, max, average, ve reduce işlemlerinin tümü terminal işlemleridir.
 		(terminal işlemi hakkında bilgi ilerleyen sayfalarda verilecektir.)
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Internal Iteration
 		
 	Önceki örneğin anahtarı, görevin nasıl gerçekleştirileceğinden ziyade, 1'den 10'a kadar olan tamsayıların toplamını hesaplayarak neyi başarmasını istediğimizi belirtmesidir.
 	Bu, bildirimsel programlamaya (neyi belirtme) ve zorunlu programlamaya (nasıl yapılacağını belirtme) bir örnektir.
 	
 	Hedefi iki basit göreve ayırdık ve sayıları kapalı bir aralıkta (1-10) ürettik ve toplamlarını hesapladık.
 	Dahili olarak, IntStream (yani, veri kaynağının kendisi) bu görevlerin her birinin nasıl gerçekleştirileceğini zaten biliyor.
 	
 	Öğeler arasında nasıl yineleme yapılacağını veya herhangi bir değiştirilebilir değişkenin nasıl bildirileceğini ve kullanılacağını belirtmemize gerek yoktu.
 	Bu, iç yineleme olarak bilinir, çünkü IntStream tüm yineleme ayrıntılarını işlevsel programlamanın önemli bir yönü olarak işler.
 	
 	
 	Not:
 		İşlevsel programlama teknikleri, daha üst düzey kod yazmanıza olanak tanır, çünkü ayrıntıların çoğu Java akışları kitaplığı tarafından sizin için uygulanır. 
 		Kodunuz daha özlü hale gelir, bu da üretkenliği artırır ve programların prototipini hızla oluşturmanıza yardımcı olabilir.
 	
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Mapping and Lambdas

	Önceki örnekte, yalnızca bir veri kaynağı ve sonuç üreten bir terminal işlemi içeren bir akış işlem hattı belirtiliyordu.
	Bu örnekte, bir akışın öğelerini yeni değerlere dönüştüren mapping adı verilen ortak bir ara işlemi tanıtıyoruz.
	Sonuç, dönüşümün sonuçlarını içeren aynı sayıda öğeye sahip bir akıştır. 
	Bazen map uygulanan öğeler, orijinal akışın öğelerinden farklı türlerdedir. 
	
	External Example
	int total = 0;
	
	for(int number = 2; number <= 20;  number += 2)
	{
		total += number;
	}
	
	
	Internal Example
	public class StreamMapReduce
	{
		public static void main(String[] args)
		{
			IntStream.rangeClosed(1,10)
					 .map((int x) -> {return x * 2})
					 .sum());

 		}
 	}	
	
	
	yukarıdaki örneğimizde akış işlem hattı üç zincirli yöntem çağrısı gerçekleştirir:
	
	1- 1, 2, 3, 4, 5, 6, 7, 8, 9 ve 10 öğelerini içeren bir IntStream olan veri kaynağını oluşturur.
	2- akıştaki her bir öğeyi  2 ile çarpılan o öğeyle eşleyen(map)
	3- akışın öğelerini tek bir değere, yani öğelerin toplamına indirger. Bu, işlem hattının işlemesini başlatan ve ardından akışın öğelerini toplayan terminal işlemidir.

	
	Buradaki yeni özellik, bu durumda her akış öğesini 2 ile çarpan map işlemidir.
	(int x) -> {return x * 2;}
	bir sonraki bölümde göreceğiniz şey, \"x int parametresini alan ve bu değeri 2 ile çarpılarak döndüren bir yöntem\" için alternatif bir gösterimdir. 
	Akıştaki her öğe için map, geçerli akış öğesini ileterek bu yöntemi çağırır.
	Yöntemin dönüş değeri, mapin döndürdüğü yeni akışın bir parçası haline gelir.
	

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Lambda Expressions
 			
	Bu bölüm boyunca göreceğiniz gibi, birçok ara ve terminal akış işlemi yöntemleri bağımsız değişken olarak alır.
	
	(int x) -> {return x * 2;}
	anonim bir yöntemi, yani adı olmayan bir yöntemi temsil eden lambda ifadesi (veya yalnızca lambda) olarak adlandırılır.
	Bir lambda ifadesinin sözdizimi daha önce gördüğünüz yöntemlere benzemese de, sol taraf bir yöntem parametre listesi ve sağ taraf bir yöntem gövdesi gibi görünür.
	
	Syntax ayrıntılarını kısaca açıklıyoruz.
	Lambda ifadeleri, veri olarak değerlendirilebilecek yöntemler oluşturmanızı sağlar ve bu sayede şunları yapabilirsiniz:
	- lambdaları diğer yöntemlere (map veya diğer lambdalar gibi) argüman olarak geçirmek
	- lambda ifadelerini daha sonra kullanmak üzere değişkenlere atama
	- yöntemlerden lambda ifadelerini döndürme.
	
	
	Lambda'lar ve akışlar, işlevsel programlama tekniklerinin birçok avantajını nesne yönelimli programlamanın avantajlarıyla birleştirmenizi sağlar.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Lambda Syntax
 	
 	Bir lambda, bir parametre listesinin ardından ok belirteci (->) ve aşağıdaki gibi bir gövdeden oluşur:
 	(parameterList) -> {statements}
 	
 	
 	(int x) -> {return x * 2;}
 		int değerini 2 ile çarpar ve sonucu döndürür.
 		Bu durumda, gövde kıvırcık parantezlerle çevrili ifadeler içerebilen bir ifade bloğudur.
 		Derleyici lambda'dan int döndürdüğü sonucunu çıkarır, çünkü x parametresi bir int'dir ve değişmez 2 bir int'dir ve int'yi int ile çarpan bir int sonucu verir.
 		Bir yöntem bildiriminde olduğu gibi, lambdalar parametreleri virgülle ayrılmış bir listede belirtir.
 		Önceki lambda yönteme aşağıdaki yönteme benzer, 
 		
 		int multiplyBy2(int x)
 		{
 			return x * 2;
 		}
 		

 		ancak lambda'nın bir adı yoktur ve derleyici dönüş türünü çıkarır. 
 		Lambda sözdiziminin birkaç varyasyonu vardır.
 		
 		
 	"Eliminating a Lambda’s Parameter Type(s)"
 		Bir lambda'nın parametre tipleri genellikle aşağıdaki gibi atlanabilir: 
 		(x) -> {return x * 2;}
 		Bu durumda, derleyici lambda'nın bağlamına göre parametre ve dönüş türlerini çıkarır, bu konuda daha sonra daha fazla şey söyleyeceğiz. 
 		Herhangi bir nedenle derleyici parametreyi veya dönüş türlerini çıkarsamazsa (örneğin, birden fazla tür olasılığı varsa), bir hata oluşturur.
 		
 	
 		
 	"Simplifying the Lambda’s Body "
 		Gövde yalnızca bir ifade içeriyorsa, return anahtar sözcüğü, kıvırcık ayraçlar ve noktalı virgül aşağıdaki gibi atlanabilir:
 		(x) -> x * 2
 		Bu durumda, lambda dolaylı olarak ifadenin değerini döndürür.
 		
 		
 	"Simplifying the Lambda’s Parameter List"
 		Parametre listesi yalnızca bir parametre içeriyorsa, parantez aşağıdaki gibi atlanabilir:
 		x -> x * 2
 		
 	"Lambdas with Empty Parameter Lists"
 		Boş parametre listesi olan bir lambda tanımlamak için, ok belirtecinin solunda boş parantezler kullanın (->), aşağıdaki gibi:
 		() -> System.out.println("Welcome to lambdas!")
 	
 	"Method Reference"
 		Ek olarak, önceki lambda-sözdizimi varyasyonlarına, yöntem referansları olarak bilinen lambdaların özel stenografi formları vardır,
 		bunlara geleceğiz..
 		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  		Intermediate and Terminal Operation
 
 	Yukarıda gösterilen akış işlem hattında, map bir ara işlemdir ve sum bir terminal işlemidir. 
 	Map yöntemi , bir akışın öğeleri üzerinde gerçekleştirilecek görevleri belirten birçok ara işlemden biridir. 
 	
 	
 	"Lazy and Eager Operations"
 		Ara işlemler tembel değerlendirme(lazy evaluation ) kullanır—her ara işlem yeni bir akış nesnesiyle sonuçlanır, 
 		ancak bir sonuç üretmek için bir terminal işlemi çağrılana kadar akışın öğeleri üzerinde herhangi bir işlem gerçekleştirmez.
 		Bu, kütüphane geliştiricilerinin akış işleme performansını en iyi duruma getirmesine olanak tanır. 
 		Örneğin, 1.000.000 Kişi nesneniz varsa ve1.000.000 öğenin tümünü işlemek yerine soyadı 'Jones' olan ilkini arıyorsanız, 
 		akış işleme ilk eşleşen Kişi nesnesi bulunur bulunmaz sonlandırılabilir.
 		lazy evaluation, işlemlerin yalnızca gerektiğinde yapılmasını sağlayarak performansı artırmaya yardımcı olur.
 		
 		Terminal operasyonları, çağrıldıklarında istenen işlemi gerçekleştirirler.
 		
 	
 	Sık kullanılan ara işlemler(Intermediate Operations)
 		-filter
 			Yalnızca bir koşulu karşılayan öğeleri (Predicate) içeren bir akış döndürür. 
 			Yeni akış genellikle orijinal akıştan daha az öğeye sahiptir.
 			
 		-distinct
 			Yalnızca yinelenen benzersiz öğeleri içeren bir akış döndürür.
 		
 		-limit
 			Orijinal akışın başlangıcından belirtilen sayıda öğeye sahip bir akış döndürür.
 			
 		-map
 			Yeni akış, orijinal akışla aynı sayıda öğeye sahiptir. ve orjinal akış üzerindeki tüm verilere uygulanması gereken işlemler sonucunda yeni akışı döndürür
 			
 		-sorted
 			Öğelerin sıralı halde olduğu bir akış döndürür. Yeni akış, orijinal akışla aynı sayıda öğeye sahiptir.
 	
 	
 	Sık kullanılan terminal işlemler(Terminal Operations)
 		-forEach
 			Bir akıştaki her öğe üzerinde işleme gerçekleştirir (örneğin, her öğeyi görüntüleme).
 		
 		"Reduction operations—Take all values in the stream and return a single  value"
 		-average
 		-count
 		-max
 		-min
 		
 		-reduce
 			İlişkisel birikim işlevini kullanarak bir koleksiyonun öğelerini tek bir değere indirir (örneğin, iki öğe ekleyen ve toplamı döndüren bir lambda).
 		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Filtering
 	Diğer bir yaygın ara akış işlemi, yüklem olarak bilinen bir koşulla eşleşenleri seçmek için öğeleri filtrelemektir.
 	Örneğin, aşağıdaki kod 1-10 aralığındaki çift tamsayıları seçer, her birini 3 ile çarpar ve sonuçları toplar:
 	
 	
 	Without Streams and Lambdas
 	int total = 0;
 	
 	for(int x = 1; x <= 10; x++){
 		if(x % 2 == 0)
 		{
 			total += x * 3;
 		}
 	}
 	
 	Şimdi yukarıda ki örneği Stream ve Lambda kullanarak yapalım.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/ 	

//class StreamFilterMapReduce
//{
//	public static void main(String[] args) 
//	{
//		System.out.printf("Sum of the triples of the even integers from 2 through 10 is: %d%n",
//				IntStream.rangeClosed(1, 10)
//				.filter(x -> x % 2 == 0)
//				.map(x -> x * 3)
//				.sum());
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Code Explain

 	akış işlem hattı dört zincirli yöntem çağrısı gerçekleştirir:
 	1-veri kaynağını oluşturur: 1 ile 10 arasındaki kapalı aralık için bir IntStream.
 	2-yalnızca 2'ye bölünebilen öğeleri seçerek akışın öğelerini filtreler
 	3-akıştaki her öğeyi (x) 3 ile çarpar.
 	4-akışı elemanlarının toplamına indirger(reduce).
 	
 	Buradaki yeni özellik,  filtreleme işlemidir. IntStream yöntem filtresi, bağımsız değişkeni olarak bir parametre alan ve bir boole sonucu döndüren bir yöntem alır. 
 	Sonuç belirli bir öğe için true ise, bu öğe elde edilen akışa dahil edilir.
 	x -> x % 2 == 0;
 	
 	int argümanının 2'ye bölünebilir olup olmadığını belirler (yani, 2'ye bölündükten sonra kalan 0'dır) ve eğer öyleyse, true değerini döndürür; aksi takdirde, lambda false değerini döndürür.
 	Akıştaki her öğe için filter, bağımsız değişken olarak aldığı yöntemi çağırır ve yönteme geçerli akış öğesini iletir.
 	Yöntemin dönüş değeri true ise, karşılık gelen öğe filtrenin döndürdüğü ara akışın bir parçası haline gelir. 
 	sonunda yalnızca 2 ile bölünebilen olan öğeleri temsil eden bir ara akış oluşturur.
 	daha sonra map ile bu filterlardan dönen öğeler 3 ile çarpılır ve yeni akış döndürür
 	Bu noktada, birleştirilmiş işleme adımları her öğeye uygulanır, ardından sum(), akışta kalan öğelerin toplamını döndürür.
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
 	
 	
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		How Elements Move Through Stream Pipelines
	
	her ara işlemin yeni bir akışla sonuçlandığını belirtmiştik.
	Her yeni akış, işlem hattında o noktaya kadar belirtilen işleme adımlarını temsil eden bir nesnedir.
	Zincirleme ara işlem yöntemi çağrıları, her akış öğesinde gerçekleştirilecek işleme adımları kümesine eklenir.
	Akış ardışık düzenindeki son akış nesnesi, her akış öğesinde gerçekleştirilecek tüm işleme adımlarını içerir.
	Terminal işlemiyle bir akış işlem hattı başlattığınızda, ara işlemlerin işleme adımları, bir sonraki akış öğesine uygulanmadan önce belirli bir akış öğesi için uygulanır. 
	
	Dolayısıyla, yukarıdaki akış işlem hattı aşağıdaki gibi çalışır:
		For each element
			If the element is an even integer
				Multiply the element by 3 and add the result to the total
				
	IntStream.rangeClosed(1,10)
			 .filter(x -> {System.out.printf("%n filter: %d%n",x})
			 			   return x % 2 == 0;
			 			  })
			 
			 .map(x -> {System.out.printf("map: "+x);
			 			return x * 3;}
			 	 )
			 	
			 .sum()
		
	
	Aşağıdaki değiştirilmiş işlem hattının çıktısı (yorumları ekledik), 
	her çift tamsayının map adımının bir sonraki akış öğesinin filtre adımından önce uygulandığını açıkça göstermektedir:
	
		filter: 1 // odd so no map step is performed for this element
		
		filter: 2 // even so a map step is performed next
		map:2
		
		filter: 3 // odd so no map step is performed for this element
		
		filter: 4 even so a map step is performed next
		map: 4
		
		filter: 5 // odd so no map step is performed for this element
		
		filter: 6 // even so a map step is performed next
		map: 6
		
		filter: 7 // odd so no map step is performed for this element
		
		filter: 8 // even so a map step is performed next
		map: 8
		
		filter: 9 // odd so no map step is performed for this element
		
		filter: 10 // even so a map step is performed next
		map: 10
	
	Tek öğeler için map adımı gerçekleştirilmedi. 
	Bir filtre adımı false değerini döndürdüğünde, öğenin kalan işleme adımları yoksayılır, çünkü bu öğe sonuçlara dahil edilmez.	
	
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  		Method References
  	
  	Yalnızca başka bir yöntemi çağıran bir lambda için, lambda'yı yöntem başvurusu olarak bilinen yöntemin adıyla değiştirebilirsiniz. 
	Derleyici, bir yöntem başvurusunu uygun bir lambda ifadesine dönüştürür.


	Örneğin
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//class RandomIntegers
//{
//	public static void main(String[] args) 
//	{
//		SecureRandom randomNumbers = new SecureRandom();
//		
//		System.out.println("Random numbers on separate lines :");
//		randomNumbers.ints(10,1,7)
//					 .forEach(System.out::println);
//		
//		String numbers = randomNumbers.ints(10,1,7).mapToObj(String::valueOf)
//												   .collect(Collectors.joining(" "));
//		
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Class SecureRandom'ın ints yöntemi, rasgele sayılardan oluşan bir IntStream döndürür.
	randomNumbers.ints(10, 1, 7) 
	ilk bağımsız değişkenden (1) başlayarak ikinci bağımsız değişkene (7) kadar olan ancak bu bağımsız değişkeni içermeyen aralıkta belirtilen sayıda(10) rasgele int değeri  içeren bir IntStream veri kaynağı oluşturur. 
	Bu nedenle, satır 12, 1-6 aralığında 10 rastgele tamsayıdan oluşan bir IntStream üretir.
	
	
	akış işlem hattı, her akış öğesinde bir görev gerçekleştirmek için forEach yöntemini (bir terminal işlemi) kullanır.
	Method forEach bağımsız değişkeni olarak bir parametre alan ve parametrenin değerini kullanarak bir görev gerçekleştiren bir yöntem alır.
	System.out::println
	
	bu durumda, belirtilen yöntemi çağıran bir lambda için kısayol gösterimine başvuran bir yöntem başvurusudur.
	objectName::instanceMethodName
	Derleyici System.out::println dosyasını aşağıdaki gibi tek parametreli bir lambda'ya dönüştürür:
	x -> System.out.println(x)
	
	
	String numbers = randomNumbers.ints(10,1,7)
								  .mapToObj(String::valueOf)
								  .collect(Collectors.joining(" ");
	
	boşluklarla ayrılmış 1-6 aralığında 10 rastgele tamsayı içeren bir String oluşturur. 
	İşlem hattı üç zincirli yöntem çağrısı gerçekleştirir:
	
	1- veri kaynağını oluşturur (1-6 arasında 10 rastgele tamsayıdan oluşan bir IntStream).
	2-her int'yi kendi String gösterimine eşleyerek bir ara String akışı elde eder.
		Daha önce kullandığımız IntStream map yöntemi başka bir IntStream döndürür. 
		Stringlerle eşlemek için, bunun yerine ints'den reference-typeelements akışına eşlemenizi sağlayan IntStream yöntemi mapToObj'u kullanırız.
		Map gibi, mapToObject de sonuç döndüren tek parametreli bir yöntem bekler. Bu örnekte, mapToObj'un bağımsız değişkeni ClassName::staticMethodName formunun statik yöntem başvurusudur. 
		
		Derleyici, String::valueOf (bağımsız değişkeninin String gösterimini döndürür) öğesini valueOf öğesini çağıran ve geçerli akış öğesini bağımsız değişken olarak ileten tek parametreli bir lambda'ya dönüştürür. 
		x -> String.valueOf(x);
	
	3-Stream, her birini bir boşlukla bir sonraki Dizeden ayırarak tüm Dizeleri birleştirmek için Stream terminal işlemi collect'i kullanır. metot collect bir indirgeme biçimidir, çünkü bu durumda bir nesne, bir String döndürür.
		
	
	//Concatenating Strings with collect..

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
