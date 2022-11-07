

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
//							   .collect(Collectors.joining(" "));
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
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		IntStream Operations 
	
	Aşağıda, dizilerden oluşturulan akışlar üzerindeki ek IntStream işlemleri gösterilmektedir.
	Bu ve önceki örneklerde gösterilen IntStream teknikleri, sırasıyla long ve double değerler için LongStreams ve DoubleStreams için de geçerlidir.
	

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class IntStreamOperations
//{
//	public static void main(String[] args) 
//	{
//		int[] values = {3,10,6,1,4,8,2,5,9,7};
//		
//		System.out.print("Original values: ");
//		System.out.println(IntStream.of(values)
//				           .mapToObj(String::valueOf)
//				           .collect(Collectors.joining(" ")));
//		
//		
//		System.out.printf("%nCount: %d%n", IntStream.of(values).count());
//		System.out.printf("Min: %d%n",IntStream.of(values).min().getAsInt());
//		System.out.printf("Max: %d%n",IntStream.of(values).max().getAsInt());
//		System.out.printf("Sum: %d%n", IntStream.of(values).sum());
//		System.out.printf("Average: %.2f%n",IntStream.of(values).average().getAsDouble());
//		
//		System.out.printf("Product via reduce method %d%n",
//							IntStream.of(values)
//							.reduce((x,y) -> x * y).getAsInt());
//		
//		System.out.printf("Sum of squares via map and sum: %d%n%n",
//						 IntStream.of(values)
//						 .map(x -> x * x)
//						 .sum());
//		
//		System.out.printf("Values displayed in sorted order: %s%n",
//						 IntStream.of(values)
//						 .sorted()
//						 .mapToObj(String::valueOf)
//						 .collect(Collectors.joining(" ")));
//		
//
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Creating an IntStream and Displaying Its Values
 		
 		
 	IntStream statik yöntemi bir int array bağımsız değişkeni alır ve dizinin değerlerini işlemek için bir Int-Stream döndürür.
 		IntStream.of(values)
 				 .mapToObj(String::valueOf)
 				 .collect(Collectors.joining(" ")));
 	
 	akışın öğelerini görüntüler. İlk olarak,  değerler dizisi için bir IntStream oluşturur, ardından mapToObj'yi kullanır ve Şekil gösterildiği gibi yöntemleri collect yapar.
	Bu tekniği bu örnekte ve sonraki örneklerde akış öğelerini görüntülemek için birkaç kez kullanırız.
	Bu örnek, aşağıdaki kodu kullanarak dizi değerlerinden  bir IntStream oluşturur:
	IntStream.of(values)
	
	Akışı basitçe depolayabileceğimizi ve yeniden kullanabileceğimizi düşünebilirsiniz. Ancak, bir akış işlem hattı terminal işlemiyle işlendikten sonra, özgün veri kaynağının bir kopyasını tutmadığı için akış yeniden kullanılamaz.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Terminal Operations count,min,max,sum and average
 
	 count, akıştaki öğelerin sayısını döndürür.
	 
	 min
	 	 muhtemelen akıştaki en küçük int'yi içeren bir OptionalInt (java.util paketi) döndürür.
	 	 Herhangi bir akış için, akışta öğe bulunmaması mümkündür.
	 	 OptionalInt öğesinin döndürülmesi, akış en az bir öğe içeriyorsa min yönteminin en düşük değeri döndürmesini sağlar.
	 	 Bu örnekte, akışın 10 öğesi olduğunu biliyoruz, bu nedenle minimum değeri elde etmek için class OptionalInt'in getAsInt yöntemini çağırıyoruz.
	 	 Öğe yoksa, OptionalInt bir int içermez ve getAsInt bir NoSuchElementException atar. 
	 	 Bunu önlemek için, bunun yerine, varsa OptionalInt'in değerini veya ilettiğiniz değeri veya Aksi takdirde Else değerini döndüren method orElse'yi çağırabilirsiniz.
	 	 
	
	 max
	 	içindeki en büyük int'yi içeren bir OptionalInt döndürür.
	 	Yine, en büyük değeri elde etmek için OptionalInt'in getAsInt yöntemini çağırıyoruz, çünkü bu akışın öğeler içerdiğini biliyoruz.

	 
	 sum
	 	sum (), akıştaki tüm int'lerin toplamını döndürür
	 	
	 
	 average
	 	verage, muhtemelen akıştaki int'lerin ortalamasını double türünde bir değer olarak içeren bir OptionalDouble (java.util paketi) döndürür.
	 	Bu örnekte, akışın öğeleri olduğunu biliyoruz, bu nedenle ortalamayı elde etmek için class OptionalDouble'ın getAsDouble yöntemini çağırıyoruz. 
	 	Öğe yoksa, OptionalDouble ortalamayı içermez ve getAsDouble bir NoSuchElementException atar. 
	 	OptionalInt'te olduğu gibi, bu özel durumu önlemek için, varsa OptionalDouble'ın değerini veya aksi takdirde orElse'ye ilettiğiniz değeri döndüren method orElse'yi çağırabilirsiniz.



	 IntStream sınıfı ayrıca count, min, max, sum ve ortalama işlemlerini bir IntStream öğesinin tek geçişinde gerçekleştiren ve sonuçları bir IntSummaryStatistics nesnesi (java.util paketi) olarak döndüren summaryStatistics yöntemini de sağlar. 
	 Bu, her bir işlem için bir IntStream'in art arda yeniden işlenmesine göre önemli bir performans artışı sağlar. 
	 Bu nesnenin her sonucu elde etmek için yöntemleri ve tüm sonuçları özetleyen bir toString yöntemi vardır.
	 
	 System.out.println(IntStream.of(values).summaryStatistics());
	 ve şöyle bir çıktı alırız
	 IntSummaryStatistics{count = 10, sum =55, min=1,average = 5.50000,max = 10}
	 
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/








/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  		Terminal Operation Reduce
  
  	Şimdiye kadar, önceden tanımlanmış çeşitli IntStream reduceları sunduk. 
  	Kendi reduce işlemlerimizi bir IntStream'in reduce yöntemiyle tanımlayabilirsiniz
  	
  	IntStream.of(values).reduce(0,(x,y) -> x + y)
  	
  	bir IntStream değerlerinin sum() yerine reduce() kullanarak nasıl toplanacağını gösterir.
  	Örneğin, öğeleri toplarken kimlik değeri 0'dır, çünkü 0'a eklenen herhangi bir int değeri orijinal int değeriyle sonuçlanır.
  	Benzer şekilde, öğelerin ürününü alırken kimlik değeri 1'dir, çünkü 1 ile çarpılan herhangi bir int değeri orijinal int değeriyle sonuçlanır.
  	
  	Yöntem azaltmanın ikinci bağımsız değişkeni, iki int değeri alan (ikili işlecin sol ve sağ işlenenlerini temsil eden), değerlerle bir hesaplama gerçekleştiren ve sonucu döndüren bir yöntemdir. 
  	
  	Lambda değerleri ekler. İki veya daha fazla parametreye sahip bir lambda bunları parantez içine almalıdır.
  	(x, y) -> x + y
  	
  	
  	Bir reduce'ın argümanı tarafından belirtilen işlem ilişkisel olmalıdır, yani redüksiyonun işlemi akışın öğelerine uyguladığı sıra önemli olmamalıdır. 
  	Bu önemlidir, çünkü reduce işleminin akış elemanlarına herhangi bir sırayla uygulanmasına izin verilir. 
  	İlişkisel olmayan bir işlem, işleme sırasına bağlı olarak farklı sonuçlar verebilir. 
  	
  	Akışın öğeleri
  	3 10 6 1 4 8 2 5 9 7
  	
  	reduce şu şekilde işlenir:
  		0+3 --> 3
  		3+10 --> 13
  		13 + 6 --> 19
  		19 + 1 --> 20
		20 + 4 --> 24
		24 + 8 --> 32
		32 + 2 --> 34
		34 + 5 --> 39
		39 + 9 --> 48
		48 + 7 --> 55
  	
  	İlk hesaplamanın sol operand olarak kimlik değerini (0) kullandığını ve sonraki her hesaplamanın sol işlenen olarak önceki hesaplamanın sonucunu kullandığını unutmayın.
  	reduce işlemi, hepsi kullanılıncaya kadar IntStream değerlerinin çalışan bir toplamını üretmeye devam eder ve bu noktada son toplam döndürülür.
  	
  
  	
  	"Calculating the Product of the Values with Method reduce"
  		IntStream.of(values).reduce((x,y) -> x * y).getAsInt()
  		
  		reducte yönteminin tek bağımsız değişken sürümünü kullanır ve bu sürüm, akışta öğeler varsa, IntStream değerlerinin ürününü içeren bir OptionalInt döndürür; aksi takdirde, OptionalInt bir sonuç içermez.
  		
  		Akışın öğeleri
  		3 10 6 1 4 8 2 5 9 7
  		
  		3 * 10 --> 30
		30 * 6 --> 180
		180 * 1 --> 180
		180 * 4 --> 720
		720 * 8 --> 5,760
		5,760 * 2 --> 11,520
		11,520 * 5 --> 57,600
		57,600 * 9 --> 518,400
		518,400 * 7 --> 3,628,800
		
		Bu işlem, hepsi kullanılıncaya kadar IntStream değerlerinin çalışan bir ürününü üretmeye devam eder ve bu noktada nihai ürün çıktısını alırız.
		Ancak, akış boş olsaydı, bu reduce kimlik değerini (1) döndürür ve bu da boş bir akış için beklenen sonuç olmazdı.
		

	"Summing the Squares of the Values "
		Şimdi akışın elemanlarının karelerini toplamayı düşünün. 
		Akış işlem hatlarınızı uygularken, işleme adımlarını anlaşılması kolay görevlere ayırmak yararlı olur. 
		Akış öğelerinin karelerini toplamak iki ayrı görev gerektirir:
		
		1-her akış elemanının değerinin karesini alma
		2-bu kareleri toplamını hesaplama
		
		IntStream.of(values).map(x -> x * x).sum();
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 				Functional Interfaces 
	Java SE 8'in gelişmiş arabirim özellikleri varsayılan yöntemler ve statik yöntemler tanıtıldı
	ve işlevsel arabirim(functional interface) kavramı, tam olarak bir soyut yöntem içeren (ve ayrıca default ve statik yöntemler de içerebilir) bir arabirim kavramını tartıştı.
	
	İşlevsel arayüzler, işlevsel tarzda Java programlamada yaygın olarak kullanılmaktadır. 

 	Java'da saf fonksiyonlar, bu bölümün örneklerinde şimdiye kadar gördüğünüz gibi, tipik olarak lambdas olarak tanımlanan fonksiyonel arayüzleri uygulayan yöntemlerdir. 
 	Durum değişiklikleri, verileri yöntemden yönteme aktararak meydana gelir. Hiçbir veri paylaşılmaz.	
 	Saf fonksiyonlar daha güvenlidir çünkü bir programın durumunu (değişkenler) değiştirmezler. Bu aynı zamanda onları hataya daha az eğilimli hale getirir ve böylece test etmeyi, değiştirmeyi ve hata ayıklamayı kolaylaştırır. 		
 	
 				
 	"Bazı Fonksiyonel Arayüzler
 		
 		BinaryOperator<T>
 			Aynı türden iki parametre alan ve bu türden bir değer döndüren bir yöntemi temsil eder.
 		
 		Consumer<T>
 			void döndüren tek parametreli bir yöntemi temsil eder.
 		
 		Function<T,R>
 			Parametre üzerinde bir görev gerçekleştiren ve büyük olasılıkla parametreden farklı türde bir sonuç döndüren tek parametreli bir yöntemi temsil eder.
 		
 		Predicate<T>
 			Boole sonucu döndüren tek parametreli bir yöntemi temsil eder.
 			
 		Supplier<T>
 			Sonuç döndüren parametresiz bir yöntemi temsil eder. 
 		
 		UnaryOperator<T>
 			Parametresiyle aynı türde bir sonuç döndüren tek parametreli bir yöntemi temsil eder.
 			
 --------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Lambdas: A Deeper Look

	"Type Inference and a Lambda’s Target Type"
		Lambda ifadeleri, işlevsel arabirimlerin beklendiği her yerde kullanılabilir.
		Java derleyicisi genellikle lambda'nın parametrelerinin türlerini ve lambda tarafından döndürülen türü, lambda'nın kullanıldığı bağlamdan çıkarabilir.
		Bu, lambda'nın hedef türü tarafından, lambda'nın kodda göründüğü yerde beklenen işlevsel arabirim türüne göre belirlenir. 
		
		IntStream.rangeClosed(1,10).map((int x) -> {return x * 2;}).sum()
		hedef tür, bir int parametresi alan ve bir int sonucu döndüren bir yöntemi temsil eden IntUnaryOperator'dur.
		Bu durumda, lambda parametresinin türü açıkça int olarak bildirilir ve derleyici lambda'nın dönüş türünü int olarak çıkarır, 
		çünkü IntUnaryOperator'un gerektirdiği şey budur.
		
		Derleyici ayrıca bir lambda parametresinin türünü de çıkarabilir.
		
		IntStream.rangeClosed(1,10).filter(x -> x % 2 == 0).map(x -> x * 3).sum()
		hedef tür, bir int parametresi alan ve bir boole sonucu döndüren bir yöntemi temsil eden IntPredicate'tir. Bu durumda, derleyici lambda parametresi x'in türünü int olarak çıkarır, 
		çünkü IntPredicate'in gerektirdiği şey budur. 
		
		Genellikle derleyicinin örneklerimizde lambda parametresinin türünü çıkarsamasına izin veririz.
		
	
	"Scope and Lambdas"
		Yöntemlerin aksine, lambdaların kendi kapsamları yoktur.
		Bu nedenle, örneğin, bir çevreleyen yöntemin yerel değişkenlerini aynı adlara sahip lambda parametreleriyle gölgeleyemezsiniz.
		Bu durumda, yöntemin yerel değişkenleri ve lambda parametreleri aynı kapsamda olduğundan bir derleme hatası oluşur. 


	
	"Capturing Lambdas and final Local Variables"
		Çevreleyen yöntemden (lambda'nın sözcüksel kapsamı olarak bilinir) yerel bir değişkene başvuran bir lambda, bir capturing lambdasıdır.
		Böyle bir lambda için, derleyici localvariable'ın değerini yakalar ve lambda sonunda yürütüldüğünde lambda'nın değeri kullanabilmesini sağlamak için lambda ile birlikte depolar.
		Bu önemlidir, çünkü bir lambda'yı, sözlük kapsamı artık mevcut olmadığında lambda'yı yürüten başka bir yönteme geçirebilirsiniz.
		Bir lambda'nın sözlük kapsamında başvurduğu herhangi bir yerel değişken final olmalıdır.
		Böyle bir değişken ya açıkça final olarak ilan edilebilir ya da effectively final olabilir (Java SE 8).
		Effectively final değişken için, derleyici yerel değişkenin final olarak bildirilmiş olabileceği sonucuna varır, çünkü içine alma yöntemi bildirildikten ve başlatıldıktan sonra değişkeni hiçbir zaman değiştirmez. 

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 				Stream<Integer> Manipulations
 	Şimdiye kadar IntStreams'i işledik. 
 	Akış, başvuru türündeki nesneler üzerinde görevler gerçekleştirir. 
 	IntStream yalnızca ortak int işlemleri için yöntemler sağlayan int için optimize edilmiş bir Akıştır. 
 	Aşağıda, önceki örneklerdekine benzer teknikler kullanarak bir Akışta filtreleme ve sıralama gerçekleştirir ve bir akış işlem hattının sonuçlarının sonraki işlemler için yeni bir koleksiyona nasıl yerleştirileceğini gösterir. 
 	Sonraki örneklerde diğer referans türlerinin Akışları ile çalışacağız.
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class ArraysAndStreams
//{
//	public static void main(String[] args) 
//	{
//		Integer[] values = {2,9,5,0,3,7,1,4,8,6};
//		
//		System.out.printf("Original values%s%n", Arrays.asList(values));
//		
//		System.out.printf("Sorted values",
//						 Arrays.stream(values)
//						 .sorted()
//						 .collect(Collectors.toList()));
//		
//		List<Integer> greaterThan4 = Arrays.stream(values)
//						   .filter(value -> value > 4)
//						   .collect(Collectors.toList());
//		
//		System.out.printf("Sorted values greater than 4: %s%n",
//						 Arrays.stream(values)
//						 .filter(value -> value > 4)
//						 .sorted()
//						 .collect(Collectors.toList()));
//		
//		System.out.printf("Values Greater than 4 (ascending with streams): %s%n",
//						 greaterThan4.stream()
//						 .sorted()
//						 .collect(Collectors.toList()));
//		
//	}
//}





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
			Stream<String> Manipulations

	Şimdiye kadar, yalnızca int değerlerinin ve integer nesnelerinin akışlarını değiştirdik. 
	Bu örnek boyunca, ilk harfi büyük harfle renk adlarıyla başlatılan String dizi dizelerini kullanırız.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class ArraysAndStreams2
//{
//	public static void main(string[] args) 
//	{
//		String[] strings = {"RED","orange","yello","GREEN","BLUE","İNDİGO"};
//		
//		system.out.printf("original strings: %s%n",arrays.asList(strings));
//		
//		system.out.printf("STRİNGS İN UPPARCASE: %s%n",
//						 arrays.stream(strings)
//						 .map(String::TOUPPERCASE)
//						 .collect(Collectors.toList()));
//		
//		
//		
//		system.out.printf("STRİNGS LESS THAN N SORTED ASCENDİNG: %s%n",
//					     arrays.stream(strings)
//					     .filter(s -> s.compareToIgnoreCase("n") < 0)
//					     .sorted(String.CASE_INSENSITIVE_ORDER)
//					     .collect(Collectors.toList()));
//		
//
//	}
//}




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  			Mapping Strings to Uppercase
 	
 		Arrays.stream(strings)
 			.map(String::toUpperCase)
 			.collect(Collectors.toList())
 		
 		bu stream işlem hattı stringleri büyük harflerle görüntüler.
 		1. satır = içine bir string alan akış oluşturur
 		2. satır = akışın içinde ki her öğeye stringden gelen toUpperCase metodunu uygular
 		3. satır = Listeye çevirir.
 		
 	
 	Akış yöntemi map, parametresiyle bir görevi gerçekleştiren ve ardından sonucu döndüren tek parametreli bir yöntemi temsil eden Function işlevsel arabirimini uygulayan bir nesne alır.
 	
 	Arrays.stream(strings)
 	.filters(s -> s.compareToIgnoreCase("n") < 0)
 	.sorted(String.CASE_INSENSITIVE_ORDER)
 	.collect(Collectors.toList())
 	
 	Bu durumda, bağımsız değişken olarak bir compareToIgnoreCase alan Stream yönteminin sıralanmış sürümünü çağırır. 
 	compareToIgnoreCase, karşılaştırılan ilk değer ikinciden küçükse negatif bir değer, eşitse 0 ve ilk değer ikinciden büyükse pozitif bir değer döndüren bir karşılaştırma yöntemi tanımlar.
 	Varsayılan olarak, sıralanan yöntem türün doğal sırasını kullanır—Dizeler için doğal düzen büyük\/küçük harfe duyarlıdır, yani 'Z' harf 'a'dan küçüktür. 
 	Bu sebeple büyük küçük harf duyarlılığını es geçmek için String.CASE_INSENSITIVE_ORDER yöntemini kullanarak sıralama işlemimizi yapıyoruz.
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/






/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Stream<Employee> Manipulations

	Bu bölümdeki önceki örnekler, ilkel türlerde (int gibi) ve Java sınıf kitaplığı türlerinde (Tamsayı ve String gibi) akış manipülasyonları gerçekleştirdi. 
	Tabii ki, programcı tanımlı türlerin koleksiyonları üzerinde de işlemler gerçekleştirebilirsiniz.
	
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Employee
//{
//	private String firstName;
//	private String lastName;
//	private double salary;
//	private String department;
//	
//	public Employee(String firstName, String lastName, double salary, String department)
//	{
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.salary = salary;
//		this.department = department;
//	}
//	
//	public String getFirstName()
//	{
//		return firstName;
//	}
//	
//	public String getLastName()
//	{
//		return lastName;
//	}
//	
//	public double getSalary()
//	{
//		return salary;
//	}
//	
//	public String getDepartment()
//	{
//		return department;
//	}
//	
//	public String getName()
//	{
//		return String.format("%s %s", getFirstName(), getLastName());
//	}
//	
//	public String toString()
//	{
//		return String.format("%-8s %-8s %8.2f   %s", getFirstName(), getLastName(), getSalary(), getDepartment());
//	}
//}
//
//
//class ProcessingEmployees
//{
//	public static void main(String[] args) 
//	{
//		
//		Employee[] employees = {new Employee("Jason","Red",5000,"IT"),
//				new Employee("Ashley","Green",7600,"IT"),
//				new Employee("Matthew","Indigo",3587.5,"Sales"),
//				new Employee("James","Indigo",4700,"Marketing")
//			   };
//		
//		
//		// get List view of the Employees
//		List<Employee> list = Arrays.asList(employees);
//		
//		//display all Employees
//		System.out.println("Complete Employee List:");
//		list.stream().forEach(System.out::println);
//		
//		
//		
//		//Java SE 9: Creating an Immutable List<Employee> with List Method of
//		List<Employee> listIm = List.of(new Employee("Jason","Red",5000,"IT"),
//								      new Employee("Ashley","Green",7600,"IT"),
//								      new Employee("Matthew","Indigo",3587.5,"Sales"),
//								      new Employee("James","Indigo",4700,"Marketing")
//				);
//		
//		
//		/*.2 Filtering Employees with Salaries in a Specified Range */
//		Predicate<Employee> fourToSixThousand = e -> (e.getSalary() >= 4000 && e.getSalary() <= 6000);
//		
//		System.out.printf("%nEmployees earning $4000-$6000 per month sorted by salary:%n");
//		
//		list.stream()
//		.filter(fourToSixThousand)
//		.sorted(Comparator.comparing(Employee::getSalary))
//		.forEach(System.out::println);
//		
//		
//		System.out.printf("%nFirst employee who earns $4000-6000:%n%s%n",list.stream()
//																		 .filter(fourToSixThousand)
//																		 .findFirst()
//																		 .get());
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		  yukarıda ki kodda filtreleme yöntemi için fourToSixThousand isimli bir predicate Arayüzü türünden bir emplooye list tanımlıyoruz.
//		  ve bunları salarysi $4000 ve 6000$ aralığında olacak şekilde sıralıyoruz.
//		  Sıralamayı yaparkenComparator arabiriminin statik yöntemi olan comparing kullanırız, bu da bağımsız değişkeni üzerinde bir görev gerçekleştiren ve sonucu döndüren bir Function alır.
//		  
//		  Find-First:findFirst—akış ardışık düzenini işleyen ve akışın ara işlemlerinden ilk nesne bulunur bulunmaz işlemeyi sonlandıran kısa devreli bir terminal işlemi(Optional Döndürür).
//		  
//		   
//		  FindAny:
//		  	findFirst'e benzer, ancak önceki ara işlemlere dayalı olarak herhangi bir akış öğesini bulur ve döndürür. 
//		  	Böyle bir öğe bulunduğunda akış ardışık düzeninin işlenmesini hemen sonlandırır. 
//		  	Genellikle, findFirst sıralı akışlarla kullanılır ve findAny paralel akışlarla kullanılır.
//		  	
//		  
//		  anyMatch:
//		  	Herhangi bir akış öğesinin belirtilen bir koşulla eşleşip eşleşmediğini belirler. 
//		  	En az bir akış öğesi eşleşirse true değerini ve aksi takdirde false değerini döndürür. 
//		  	Bir öğe eşleşirse akış işlem hattının işlenmesini hemen sonlandırır.
//		  	
//		  allMatch:
//		  	Akıştaki tüm öğelerin belirli bir koşulla eşleşip eşleşmediğini belirler. 
//		  	Aksi halde true ve false döndürür. 
//		  	Herhangi bir eleman eşleşmezse derhal işlem hattını sonlandırır.
//		  	
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 		Sorting Employees By Multiple Fields
//		 	Bi sonraki örneğimizde nesneleri birden çok alana göre sıralamak için akışların nasıl kullanılacağını gösterir. 
//		 	Bu örnekte, Çalışanları soyadına göre sıralarız, ardından aynı soyadına sahip Çalışanlar için onları adlarına göre de sıralarız. 
//		 	Bunu yapmak için, her biri anEmployee alan ve bir String döndüren iki İşlev oluşturarak başlarız:
//		 	bunlar byFirstName , ve ByLastName isimli iki metodumuz olsun 
//		 	
//		 	Daha sonra, bu İşlevleri, önce iki Çalışanı soyadına göre karşılaştıran, ardından adlarıyla karşılaştıran bir Comparator oluşturmak için kullanırız.
//		
//			Comparator<Employee> lastThenFirst = Comparator.comparing(Employee::getLastName)
//									.thenComparing(Employee::getFirstName);
//		
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		Function<Employee,String> byFirstName = Employee::getFirstName;
//		Function<Employee,String> byLastName = Employee::getLastName;
//		
//		Comparator<Employee> lastThenFirst = Comparator.comparing(byLastName).thenComparing(byFirstName);
//		
//		System.out.printf("%nEmployees in ascending order by last name then first :%n");
//		
//		list.stream()
//			.sorted(lastThenFirst)
//			.forEach(System.out::println);
//		
//		System.out.printf("%nEmployees in descending order by last name then first: %n");
//		
//		list.stream()
//			.sorted(lastThenFirst.reversed())
//			.forEach(System.out::println);
//		
//	
//		
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 java.util.function paketindeki birçok işlevsel arabirim, işlevsellik oluşturmanıza olanak tanıyan varsayılan yöntemler sağlar.
//		 
//		 Örneğin, üç varsayılan yöntem içeren IntPredicate arabirimini düşünün:
//		 	and:
//		 		çağrıldığı IntPredicate ile argüman olarak aldığı IntPredicate arasında kısa devre değerlendirmesi ile mantıksal bir AND gerçekleştirir. 
//			
//			negate: 
//				çağrıldığı IntPredicate'in boole değerini tersine çevirir. 
//			
//			or:
//				çağrıldığı IntPredicate ile bağımsız değişken olarak aldığı IntPredicate arasında kısa devre değerlendirmesi ile mantıksal bir OR gerçekleştirir.
//				
//		
//		 Bu yöntemleri kullanabilir ve daha karmaşık durumlar oluşturmak için nesneleri intpredicate edebilirsiniz. 
//		 
//		 Örneğin, her biri lambdas ile başlatılan aşağıdaki iki intpredicate'i düşünün:
//		 	
//		 	IntPredicate even = value -> value % 2 == 0;
//		 	IntPredicate greaterThan5 = value -> value > 5;
//		 	
//		 	Bir IntStream'de 5'ten büyük tüm çift tamsayıları bulmak için, aşağıdaki oluşturulmuş IntPredicate'i IntStream yöntem filtresine geçirebilirsiniz:
//		 		even.and(greaterThan5)
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 	Mapping Employees to Unique-Last-Name Strings.
//		
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		System.out.printf("%nUnique employee last names:%n");
//		list.stream()
//			.map(Employee::getLastName)
//			.distinct()
//			.sorted()
//			.forEach(System.out::println);
//		
//		
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 	Grouping Employees By Department
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		System.out.printf("%nEmployees by department:%n");
//		Map<String, List<Employee>> groupByDepartment = list.stream()
//								.collect(Collectors.groupingBy(Employee::getDepartment));
//		
//		groupByDepartment.forEach((department,employeesInDeparatment) -> 
//		{System.out.printf("%n%s%n", department);
//		employeesInDeparatment.forEach(employee -> System.out.printf(" %s%n",employee));});
//	
//	
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 	Collect'in bağımsız değişkeninin, verilerin yararlı bir formda nasıl özetleneceğini belirten bir Toplayıcı olduğunu hatırlayın. 
//		 	Bu durumda, akıştaki nesneleri sınıflandıran bir İşlev alan Collectors statik yöntemi groupingBy tarafından döndürülen Collector'ı kullanırız.
//		 	Bu İşlev tarafından döndürülen değerler, bir Map koleksiyonunda key olarak kullanılır.
//		 	Karşılık gelen değerler, varsayılan olarak, belirli bir kategorideki akış öğelerini içeren Listeler'dir.
//		 	Bu Collector ile collect yöntemi kullanıldığında, sonuç, her String <Employee>anahtarının bir departman olduğu ve her Listenin o departmandaki Çalışanları içerdiği bir Map<String,<Employee> List> olur.
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		
//		
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 	Counting The Number of Employees in Each Department
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		System.out.printf("%nCount of Employees by department:%n");
//		Map<String,Long> employeeCountByDepartment = list.stream()
//							     .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
//		employeeCountByDepartment.forEach((department,count) -> System.out.printf("%s has %d employee(s)%n",department,count));
//	
//	
//	
//		/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
//		 	Summing and Averagin Employee Salaries 
//		 	
//		  Daha önce, ilkel tür öğelerin akışlarının mapToObj yöntemiyle (IntStream, LongStream ve DoubleStream sınıflarında bulunur) nesne akışlarına eşlenebileceğini göstermiştik. 
//		  Benzer şekilde, bir nesne akışı bir IntStream, LongStreamor DoubleStream ile eşlenebilir.
//		  Bu örnekte, toplamı ve ortalamayı hesaplayabilmemiz için Çalışan nesnelerini maaşlarıyla eşleştiririz.
//		--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//		
//		System.out.printf("%nSum of Employees' salaries (via sum method): %.2f%n",list.stream()
//						 .mapToDouble(Employee::getSalary)
//						 .sum());
//		
//		
//		System.out.printf("Sum of employee salaries (via reduce method): %.2f%n",list.stream()
//											.mapToDouble(Employee::getSalary)
//											.reduce(0,(value1,value2) -> value1 + value2));
//		
//		System.out.printf("Average of Employees salaries: %.2f%n",list.stream()
//									  .mapToDouble(Employee::getSalary)
//									  .average()
//									  .getAsDouble());
//	}
//
//	
//
//	
//}




/*
 		Creating a Stream<String> from a File
 		
 	
 */
