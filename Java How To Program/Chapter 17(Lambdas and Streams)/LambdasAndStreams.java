

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




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Creating a Stream<String> from a File
 		
	Aşağıdaki kod, bir dosyadaki her sözcüğün oluşum sayısını özetlemek için lambdaları ve akışları kullanır, 
	ardından sözcüklerin özetini başlangıç harfine göre gruplandırılmış alfabetik sırada görüntüler. 
	Buna genellikle concordance denir:	
	
	Uyumlar genellikle yayınlanmış çalışmaları analiz etmek için kullanılır. 
	Örneğin, William Shakespeare'in ve Christopher Marlowe'un eserlerinin (diğerlerinin yanı sıra) uyumları, aynı kişi olup olmadıklarını sorgulamak için kullanılmıştır.
	
	 Aşağıdaki kodun 14. satırı, metin satırlarını tek tek sözcüklerine bölmek için kullanacağımız normal bir ifade Deseni oluşturur.
	 

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class StreamsOfLines
//{
//	public static void main(String[] args) throws IOException 
//	{
//		Pattern pattern = Pattern.compile("\\ss+");
//		
//		Map<String, Long> wordCounts = Files.lines(Paths.get("Chapter2paragraph.txt"))
//									   .flatMap(line -> pattern.splitAsStream(line))
//									   .collect(Collectors.groupingBy(String::toLowerCase,TreeMap::new,Collectors.counting()));
//		
//		wordsCounts.entrySet()
//				   .stream()
//				   .collect(Collectors.groupingBy(entry -> entry.getKey().charAt(0),
//						   							TreeMap::new, Collectors.toList()))
//				   .forEach((letter,wordList) -> {
//					   	System.out.printf("%n%C%n",letter);
//					   	wordList.stream().forEach(word -> System.out.printf("%13s: %d%n"),word.getKey(), word.getValue()));
//				   });
//	}
//	
//	
//}



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 				Summarizing the Occurrences of Each Word in the File
 	
 		Map<String, Long> wordCounts = 
 			Files.lines(Paths.get("Chapter2Paragraph.txt")) 
 				.flatMap(line -> pattern.splitAsStream(line)) 
 				.collect(Collectors.groupingBy(String::toLowerCase,
 					TreeMap::new, Collectors.counting()));
 					
 		
 		18. satır, bir<String> dosyadaki metin satırlarını okuyan ve her satırı bir Dize olarak döndüren bir Akış döndüren Files lines yöntemi (Java SE 8'e eklenmiştir) çağırır.
		
		Satır 19, metnin her satırını ayrı sözcüklere bölmek için Stream yöntemi flatMap kullanır.
		
		flatMap yöntemi, bir nesneyi öğe akışına eşleyen bir <Function> alır.
		
		Bu durumda, nesne sözcükleri içeren bir Stringdir ve sonuç Stream<String> tek sözcükler için bir Akış'tır.
		
		Dizeyi tek tek sözcüklerine tokenize etmek için  Pattern.splitAsStream kullanılmuştır
		
		20-21 arası satırlar, her kelimenin sıklığını saymak ve anahtarlarını sıralanmış sırada tuttuğu için sözcükleri ve sayımlarını bir TreeMap<String, Long> bir TreeMap'e yerleştirmek için Stream method collect kullanır.
		
		Burada, Collectors method groupingBy öğesinin classifier, Map factory ve downstream Collector olmak üzere üç bağımsız değişken alan bir sürümünü kullanıyoruz.
		
		Classifier, sonuçta elde edilen Map yöntem başvurusunda anahtar olarak kullanılmak üzere nesneleri döndüren bir <Function> , String::toLowerCase her sözcüğü küçük harfe dönüştürür. Map factory, Supplier functional interface uygulayan ve yeni bir Map koleksiyonu döndüren bir nesnedir ve burada anahtarlarını sıralanmış sırada tutan bir TreeMap döndüren TreeMap::new yapıcı referansını kullanırız.
		
		Derleyici, bir oluşturucu başvurusunu yeni bir TreeMap döndüren parametresiz lambda'ya dönüştürür. Collectors.counting(), akıştaki her anahtarın oluşum sayısını belirleyen Collectordur
		.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

 
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 				Displaying the Summary Grouped by Starting Letter
 		

 	
 	wordCounts.entrySet() 
 			  .stream()
 			  .collect( 
 				Collectors.groupingBy(entry -> entry.getKey().charAt(0),
 					TreeMap::new, Collectors.toList())) 
 			  .forEach((letter, wordList) -> { 
 				 System.out.printf("%n%C%n", letter); 
 				 wordList.stream().forEach(word -> System.out.printf( 
 					"%13s: %d%n", word.getKey(), word.getValue())); 
 			  });	
 			  
 			  		
    Bu kod, her anahtarın bir Karakter olduğu ve karşılık gelen değerin, anahtarın Karakter ile başladığı wordCounts'taki anahtar-değer çiftlerinin bir Listesi olduğu yeni bir Map oluşturur. 
    kod aşağıdaki görevleri gerçekleştirir:
    
    Öncelikle, wordCounts'taki anahtar-değer çiftlerini işlemek için bir Akış almamız gerekir. Map, Akışları döndüren herhangi bir yöntem içermez.
    
    -Bu nedenle, satır 24, her biri wordCounts'tan bir anahtar-değer çifti içeren Map.Entry nesneleri kümesi almak için mapin entryset metodunu wordcountsdan çağırır. 
    Bu, Set<Map.Entry<String, Long>> türünde bir nesne üretir.
    
    - Satır 25, Stream<Map.Entry<String, Long>> almak için Set method stream'i çağırır.
    
    -26-28 arası satırlar Stream yöntemini üç bağımsız değişkenle toplar: bir classifier, bir Map factory ve downstream akış Collector. 
    	Bu durumda classifer entry.getKey alır.
    	Entry daha sonra anahtarın ilk karakterini almak için charAt String yöntemini kullanır 
    	ve sonuçta elde edilen Eşlemede bir Karakter anahtarı haline gelir.
    	Bir kez daha, anahtarlarını sıralanmış bir şekilde tutan bir TreeMap oluşturmak için Map factory olarak TreeMap::new yapıcı referansını kullanıyoruz.
   		downstream Collector (Collectors.toList()), Map.Entry nesnelerini bir List koleksiyonuna yerleştirir. 
   		Toplamanın sonucu bir Map<Character, List<Map.Entry<String, Long>>>.

	-Son olarak, kelimelerin özetini ve sayımlarını harfle (yani, uyum) görüntülemek için, 29-33 satırları Map forEach yöntemine bir lambda iletir.
		Lambda (BiConsumer) iki parametre harfi alır ve wordList, önceki Collector işlemi tarafından üretilen Map her anahtar-değer çifti için sırasıyla Karakter anahtarını ve List değerini temsil eder.
		Bu lambda'nın gövdesinin iki ifadesi vardır, bu yüzden kıvırcık içine alınmalıdır.
		
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 				Streams of Random Values
 	
 	Aşağıdaki kod, 60.000.000 kez yuvarlamak, frekansları hesaplamak ve sonuçları görüntülemek için lambdas, akışlar, iç yineleme ve değiştirilebilir değişkenler kullanmadan, her şeyi yapan tek bir ifadeyle bu programları yeniden uygular.
 	
 	Secururandom'un güvenli rastgele sayılar üretmek için kullandığı teknikler, Random (paketi java.util) tarafından kullanılanlardan daha yavaştır.
 	Bu nedenle, Aşağıdaki kodu , bilgisayarlarımızda çalıştırdığınızda donuyor gibi görünebilir, tamamlanması bir dakikadan fazla sürdü. 
 	Zaman kazanmak için, Random sınıfını kullanarak bu örneğin yürütülmesini hızlandırabilirsiniz.
 	
 	
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class RandomIntStream
//{
//	public static void main(String[] args) 
//	{
//		SecureRandom random = new SecureRandom();
//		
//		//roll a die 60,000,000 times and summarize the results.
//		System.out.printf("%-6s%s%n", "Face", "Frequency");
//		random.ints(60_000_000,1,7).boxed()
//		.collect(Collectors.groupingBy(Function.identity(),
//				Collectors.counting()))
//		.forEach((face,frequency) -> System.out.printf("%-6d%d%n",face,frequency));
//		
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	SecureRandom sınıfı, Random sınıfından (java.util paketi) devraldığı ints, longs ve double yöntemlerini overload etmiştir. 
 	Bu yöntemler, rasgele sayı akışlarını temsil eden sırasıyla bir IntStream, bir LongStream veya DoubleStream döndürür.
 	
 	-ints(long)—belirtilen sayıda rastgele int içeren bir IntStream oluşturur.
 	
 	-ints(int, int)—ilk bağımsız değişkenden başlayarak ikinci bağımsız değişkene kadar olan ancak bu bağımsız değişkeni içermeyen yarı açık aralıktaki rastgele int değerlerinin sonsuz akışı için bir IntStream oluşturur.
 	
 	-ints(long, int, int), ilk bağımsız değişkenden başlayarak ikinci bağımsız değişkene kadar olan ancak bu bağımsız değişkeni içermeyen aralıkta belirtilen sayıda rasgele int değerine sahip bir IntStream oluşturur.
 	
 	
 --------------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Converting an IntStream to a Stream<Integer>
		
		Ne yazık ki, Java koleksiyonlarda ilkel değerlere izin vermez, bu nedenle  önce IntStream'i bir Akışa dönüştürmeliyiz<Integer>. Bunu, IntStream yöntemini kutulu olarak çağırarak yaparız.
	
		-15-16 arası satırlar, sonuçları bir Map<Integer, Long> şeklinde özetlemek için Stream yöntemini çağırır. 
		Collectors method groupingBy (satır 15) için ilk bağımsız değişken, yalnızca bağımsız değişkenini döndüren bir İşlev oluşturan interface Function'dan statik yöntem identity çağırır.
		
		Bu, gerçek rastgele değerlerin Map anahtarları olarak kullanılmasına izin verir. GroupingBy yönteminin ikinci bağımsız değişkeni, her anahtarın oluşum sayısını sayar.

		17-18 arası satırlar, sonuçların özetini görüntülemek için ortaya çıkan Map'in forEach yöntemini çağırır. Bu yöntem, BiConsumer işlevsel arabirimini bağımsız değişken olarak uygulayan bir nesne alır.
		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/		




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 					Infinite Streams

 		Dizi veya koleksiyon gibi bir veri yapısı, her zaman sonlu sayıda öğeyi temsil eder, tüm öğeler bellekte depolanır ve bellek sonludur.
 		Tabii ki, sonlu bir veri yapısından oluşturulan herhangi bir akış, bu bölümün önceki örneklerinde olduğu gibi, sonlu sayıda öğeye sahip olacaktır.
 		
 		Lazy evaluation, bilinmeyen, potansiyel olarak sonsuz sayıda öğeyi temsil eden sonsuz akışlarla çalışmayı mümkün kılar.
 		Örneğin, nextPrime'ı her çağırdığınızda sırayla bir sonraki asal sayıyı üreten bir yöntem tanımlayabilirsiniz.
 		Daha sonra bunu, kavramsal olarak tüm asal sayıları temsil eden sonsuz bir akış tanımlamak için kullanabilirsiniz.
 		
 		Ancak, bir terminal işlemi gerçekleştirene kadar akışlar lazy olduğundan, bir terminal işlemi gerçekleştirildiğinde gerçekte hesaplanan toplam öğe sayısını kısıtlamak için ara işlemleri kullanabilirsiniz.
 		
 		Görev:Create an infinite stream representing all prime numbers
			  If the prime number is less than 10,000 
			  Display the prime number
		
		Sonsuz bir akışla başlasak bile, yalnızca 10.000'den küçük sonlu asal küme görüntülenir.
		
		Akış arabirimleri yöntemlerinden iterator ve generator ile sonsuz akışlar oluşturursunuz.
		
					IntStream Method iterate
		
		IntStream.iterate(1, x -> x + 1)
				 .forEach(System.out::println);
		
		IntStream yöntemi yineleme, ilk bağımsız değişkenindeki çekirdek değerden (1) başlayarak sıralı bir değer dizisi oluşturur.
		Sonraki her öğe, yinelemenin ikinci bağımsız değişkeni olarak belirtilen IntUnaryOperator dizisindeki önceki değere uygulanarak üretilir.
		Önceki işlem hattı 1, 2, 3, 4, 5, ...'nin sonsuz dizisini oluşturur, ancak bu işlem hattının bir sorunu vardır. 
		Kaç tane eleman üreteceğimizi belirtmedik, bu yüzden bu sonsuz bir döngünün eşdeğeridir.
		
		
		Limiting an Infinite Stream’s Number of Elements
		limit() sayesinde biz bu sonsuza kadar sürecek olan akışı kaç eleman istediğimiz ile sınırlandırabiliriz.
	
		IntStream.iterate(1, x -> x + 1)
				 .limit(10)
				 .forEach(System.out::println);
		
		
		sonsuz bir akışla başlar, ancak üretilen toplam öğe sayısını 10 ile sınırlar, böylece sayıları 1'den 10'a kadar görüntüler.
		
		
		IntStream.iterate(1, x -> x + 1)
				 .map(x -> x * x)
				 .limit(10)
				 .sum()
		
		sonsuz bir akışla başlar, ancak yalnızca 1'den 10'a kadar tamsayıların karelerini toplar.
		
		not:Sonsuz akışlar üreten yöntemler kullanan akış işlem hatlarının, üretilecek öğe sayısını sınırladığından emin olun.
		
		
		
					IntStream Method generate
		
		Ayrıca, bağımsız değişken almayan ve int döndüren bir yöntemi temsil eden bir IntSupplier alan generate yöntemini kullanarak sıralanmamış sonsuz akışlar da oluşturabilirsiniz.
		Örneğin, rasgele adlı bir SecureRandom nesneniz varsa, aşağıdaki akış işlem hattı 10 rasgele tamsayı oluşturur ve görüntüler:
		
		IntStream.generate(() -> random.nextInt())
				 .limit(10)
				 .forEach(System.out::println);
				 
		SecureRandom.ints()
				    .limit(10)
				    .forEach(System.out::println);
				 
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/






/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 					Lambda Event Handlers

 					
 	Bölüm 12.5.5'te, anonim bir iç sınıf kullanarak bir ero işleyicisinin nasıl uygulanacağını öğrendiniz. 
 	ChangeListener gibi soyut bir yönteme sahip işlevsel arabirimlerdir. Bu tür arabirimler için, lambdas ile ero işleyici uygulayabilirsiniz.			
 	
 		tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>()
 		{
 			@Override
 			public void changed(ObservableValue<? extends Number> ov, 
 				Number oldValue, Number newValue) {
 				tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100);
 			tipPercentageLabel.setText(percent.format(tipPercentage));
 		    }
 		  }
 	    )
 	
 	Lambda kullanarak bu anonim sınıfı şu şekilde yazıyoruz: 
 	
 	tipPercentageSlider.valueProperty().addListener(
 	(ov, oldValue, newValue) -> 
 	{
 		tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100);
 		tipPercentageLabel.setText(percent.format(tipPercentage));
	}
	)
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Additional Notes on Java SE8 Interfaces
 			
 	Java SE 8 Interfaces Allow Inheritance of Method Implementations
 	
 	Fonksiyonel arayüzler yalnızca bir soyut yöntem içermelidir, ancak arayüz bildirimlerinde tam olarak uygulanan default yöntemler ve statik yöntemler içerebilir.
	Örneğin, fonksiyonel programlamada yaygın olarak kullanılan İşlev arayüzünde apply (soyut), compose (varsayılan) ve Then (varsayılan) ve identity (statik) yöntemleri vardır.
	Bir sınıf varsayılan yöntemlerle bir arabirim uyguladığında ve bunları geçersiz kılmadığında, sınıf varsayılan yöntemlerin uygulamalarını devralır.
	Bir arabirimin tasarımcısı artık arabirimi uygulayan varolan kodu bozmadan yeni varsayılan ve statik yöntemler ekleyerek arabirimi geliştirebilir. 
	
	For example, interface Comparator
	artık birçok varsayılan ve statik yöntem içeriyor, ancak bu arabirimi uygulayan eski sınıflar Java SE 8'de derlemeye ve düzgün çalışmaya devam edecek.
	Bir sınıfın birçok arabirim uygulayabileceğini hatırlayın.
	
	Bir sınıf, aynı imzaya sahip varsayılan bir yöntem sağlayan iki veya daha fazla ilişkisiz arabirim uygularsa, uygulayan sınıfın bu yöntemi geçersiz kılması gerekir; aksi takdirde, bir derleme hatası oluşur.
	
	
	
			Java SE 8: @FunctionalInterface Annotation
	
	Her birinin yalnızca bir soyut yöntem ve sıfır veya daha fazla varsayılan ve statik yöntem içerdiğinden emin olarak kendi işlevsel arayüzlerinizi oluşturabilirsiniz.
	istenmese de, bir arayüzün önünde bir arayüzün işlevsel bir arayüz olduğunu beyan edebilirsiniz. @FunctionalInterface
	Derleyici daha sonra arabirimin yalnızca bir soyut yöntem içerdiğinden emin olacaktır; aksi takdirde, bir derleme hatası oluşturur.
	
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*
 				Summary

 		-Section 17.1 Introduction-
 
 	*Java SE 8, lambda'lar ekledi ve işlevsel programlamanın temel teknolojilerini Stream aldı.
 	*Lambda'lar ve Streamler, belirli program türlerini önceki tekniklere göre daha hızlı, daha basit, daha özlü ve daha az hatayla yazmanızı sağlar.
 	
 		
 		-Section 17.2 Streams and Reduction-	
 	
 	*counter-controlled yinelemede, genellikle neyi başarmak istediğinizi belirler, ardından bir for döngüsü kullanarak tam olarak nasıl gerçekleştirileceğini belirtirsiniz.

		
		- 17.2.1 Summing the Integers from 1 through 10 with a for Loop-
	
	*Dış yineleme ile tüm yineleme ayrıntılarını belirtirsiniz.
	
		-Section 17.2.2 External Iteration with for Is Error Prone-
		
	* Dış yineleme, her döngü yinelemesi sırasında değişkenleri mutasyona uğratır.
	* Bir değişkeni değiştiren kodu  yazdığınızda, bir hata almak mümkündür.
	
	
		-Section 17.2.3 Summing with a Stream and Reduction-,
	
	*Class IntStream (java.util.stream paketi), karşı denetimli yinelemeden kaçınmanızı sağlayan yöntemleri uygun bir şekilde tanımlar.
	* Akış, üzerinde görevler gerçekleştirdiğiniz bir öğeler dizisidir.
	* Akış işlem hattı, akışın öğelerini bir dizi görev (veya işleme adımı) boyunca taşır.
	* Akış işlem hattı genellikle veri kaynağı olarak bilinen akışı oluşturan bir yöntem çağrısıyla başlar.
	* IntStream statik yöntem rangeClosed, kapalı bir değer aralığını, yani yöntemin bağımsız değişkenlerinin her ikisini de içeren bir öğe aralığını içeren bir IntStream oluşturur. 
	* IntStream range yöntemi, ilk bağımsız değişkeninden ikinci bağımsız değişkenine kadar yarı açık bir değer aralığı üretir, ancak bu değer aralığını içermez.
	* IntStream'in sum  yöntemi, akıştaki tüm int'lerin toplamını döndürür. 
	* Yöntem sum(), değer akışını tek bir değere indirir.
	* Terminal işlemi, bir akış işlem hattının işlenmesini başlatır ve bir sonuç üretir.
	* IntStream yöntemi sum(), akışın öğelerinin toplamını üreten bir terminal işlemidir.
	
	
		-Section 17.2.4 Internal Iteration-
	
	* Dahili olarak, IntStream zaten herhangi bir değiştirilebilir değişkeni bildirmek ve kullanmak zorunda kalmadan öğeleri arasında nasıl yineleme yapılacağını biliyor. 
	* Bu, iç yineleme olarak bilinir, çünkü IntStream, tüm yineleme ayrıntılarını işlevsel programlamanın önemli bir yönü olarak işler.
	* Buna alıştıktan sonra, akış işlem hattı kodunun okunması da daha kolay olabilir.
	
	
		-Section 17.3 Mapping and Lambdas-
	
	* Çoğu akış işlem hattı, terminal işlemi bir sonuç üretmeden önce akışın öğeleri üzerinde gerçekleştirilecek görevleri belirten ara işlemler içerir.
	* Map ara işlemi, akışın öğelerini yeni değerlere dönüştürür. 
	* Sonuç, dönüşümün sonuçlarını içeren aynı sayıda öğeye sahip bir akıştır. Bazen Maplenen öğeler, orijinal akışın öğelerinden farklı türlerdedir.
	* IntStream map yöntemi, bağımsız değişkeni olarak, sonuç döndüren tek bir parametreye sahip bir yöntemi temsil eden bir nesne alır.
	* Akıştaki her öğe için map, bağımsız değişken olarak aldığı yöntemi çağırır ve yönteme geçerli akış öğesini iletir. Yöntemin dönüş değeri, eşlemenin döndürdüğü yeni akışın bir parçası haline gelir. 
	
	
		-Section 17.3.1 Lambda Expressions-
	
	* Birçok ara ve terminal akış işlemi, yöntemleri bağımsız değişken olarak alır ve genellikle lambda ifadeleri olarak uygulanır.
	* Lambda ifadesi anonim bir yöntemi, yani adı olmayan bir yöntemi temsil eder.
	* Lambda ifadeleri (veya kısaca lambdalar), veri olarak işlenebilecek yöntemler oluşturmanızı sağlar. 
	* Lambda ifadelerini diğer yöntemlere bağımsız değişken olarak geçirebilir, lambda ifadelerini daha sonra kullanmak üzere değişkenlere atayabilir ve yöntemlerden lambda ifadeleri döndürebilirsiniz.
	
	
		-Section 17.3.2 Lambda Syntax-
	
	*  Bir lambda, bir parametre listesinin ardından ok belirteci (->) ve aşağıdaki gibi bir gövdeden oluşur:
	*  (parameterList) -> {statements}
	*  Gövde, kıvırcık parantezlerle çevrili bir veya daha fazla ifade içerebilen bir ifade bloğudur.
	*  Derleyici, lambda'nın bağlamından dönüş türünü çıkarır. 
	*  Bir yöntem bildiriminde olduğu gibi, lambdalar virgülle ayrılmış bir listede birden çok parametre belirtir.
	*  Bir lambda'nın parametre türleri genellikle atlanabilir, bu durumda derleyici parametreyi lambda'nın bağlamına göre çıkarır ve türleri döndürür.
	*  Gövde yalnızca bir ifade içeriyorsa, return anahtar sözcüğü, kıvırcık ayraçlar ve noktalı virgül gövdeden çıkarılabilir. Bu durumda, lambda dolaylı olarak ifadenin değerini döndürür.
	*  Parametre listesi yalnızca bir parametre içeriyorsa, parantezler atlanabilir.
	*  Lambda'yı boş parametre listesiyle tanımlamak için, ok belirtecinin solunda boş parantezler kullanın (->).
	
	
		-Section 17.3.3 Intermediate and Terminal Operations-
	
	* Ara işlemler lazydir, her ara işlem yeni bir akış nesnesiyle sonuçlanır, ancak bir sonuç üretmek için bir terminal işlemi çağrılana kadar akışın öğeleri üzerinde herhangi bir işlem gerçekleştirmez.
	* Bu,  geliştiricilerinin akış işleme performansını en iyi duruma getirmesine olanak tanır.
	* Terminal operasyonları, çağrıldıklarında istenen işlemi gerçekleştirirler. 
	* 
	* 
	 	
	 	
	 	-Filtering-
	
	* Diğer bir yaygın ara akış işlemi, Predicate olarak bilinen bir koşulla eşleşenleri seçmek için öğeleri filtrelemektir.
	* Filter() bağımsız değişkeni olarak bir parametre alan ve Boolean sonucunu döndüren bir yöntem alır. Sonuç belirli bir öğe için doğruysa, bu öğe ortaya çıkan akışa dahil edilir.
	* Bir terminal işlemi gerçekleştirildiğinde, ara işlemler tarafından belirtilen birleşik işleme adımları her elemana uygulanır.
	 	




		-Section 17.5 How Elements Move Through Stream Pipelines-
	
	* Her yeni akış, işlem hattında o noktaya kadar belirtilen tüm işleme adımlarını temsil eden bir nesnedir.
	* Zincirleme ara işlemler, her akış öğesinde gerçekleştirilecek işleme adımları kümesine eklenir.
	* Akış ardışık düzenindeki son akış nesnesi, her akış öğesinde gerçekleştirilecek tüm işleme adımlarını içerir.
	* Terminal işlemiyle bir akış işlem hattı başlattığınızda, işlem hattının ara işlemleri tarafından belirtilen tüm işleme adımları, bir sonraki akış öğesine uygulanmadan önce belirli bir akış öğesi için uygulanır. 
	
	
	
		-Section 17.6 Method References-
	
	* Bir lambda'nın kullanılabileceği her yerde, derleyicinin her yöntem başvurusunu uygun bir lambda ifadesine dönüştürdüğü bir yöntem başvurusu aracılığıyla varolan bir yöntemin adını kullanabilirsiniz. 
	* Bir lambda yalnızca karşılık gelen yöntemi çağırdığında yöntem başvurularını kullanırsınız.
	
	
	
		-Section 17.6.1 Creating an IntStream of Random Values-
	
	* Class SecureRandom'ın ints yöntemi, rasgele sayılardan oluşan bir IntStream döndürür.
	
	 
	 	-Section 17.6.2 Performing a Task on Each Stream Element with forEach and a Method Reference-
	
	* IntStream yöntemi forEach (bir terminal işlemi) her akış öğesinde bir görev gerçekleştirir.
	* Method forEach bağımsız değişkeni olarak bir parametre alan ve parametrenin değerini kullanarak bir görev gerçekleştiren bir yöntem alır.
	* Yöntem başvurusu, belirtilen yöntemi çağıran bir lambda için kısayol gösterimidir.
	
	
	
		-Section 17.6.3 Mapping Integers to String Objects with mapToObj-
	
	* IntStream method map returns another IntStream. 
	* IntStream yöntemi mapToObj, ints'den referans türündeki öğelerin akışına eşlenir.
	* Statik yöntem başvurusu, derleyicinin belirtilen sınıftaki statik yöntemi çağıran ve geçerli akış öğesini bağımsız değişken olarak ileten tek parametreli bir lambda'ya dönüştürdüğü ClassName::staticMethodName biçimine sahiptir.
	* Stream terminal işlem Collection, String akış öğelerini birleştirmek için kullanılabilir. Collect yöntemi bir indirgeme biçimidir çünkü bir nesne döndürür.
	
		-Section 17.6.4 Concatenating Strings with collect-
	
	* Stream terminal işlemi collect, bağımsız değişkeni olarak, akışın öğelerinin tek bir nesnede nasıl toplanacağını belirten bir collector nesnesi alır. 
	* Collectors statik yöntem birleştirme tarafından döndürülen önceden tanımlanmış collector, akış öğelerinin birleştirilmiş bir String temsilini oluşturur ve her öğeyi birleştirme yönteminin bağımsız değişkeniyle bir sonrakinden ayırır. Yöntem collect daha sonra elde edilen String'i döndürür.

		-Section 17.7 IntStream Operations-
	
	* LongStreams ve DoubleStreams, sırasıyla long ve double değerdeki akışları işler.
	
		-Section 17.7.1 Creating an IntStream and Displaying Its Values-
	
	* IntStream statik yöntemi bir int array bağımsız değişkeni alır ve dizinin değerlerini işlemek için bir IntStream döndürür.
	* Bir akış işlem hattı bir terminal işlemi ile işlendikten sonra, akış tekrar kullanılamaz, çünkü orijinal veri kaynağının bir kopyasını tutmaz.
	
		-Section 17.7.2 Terminal Operations count, min, max, sum and average-
	
	* IntStream metodu count, akıştaki öğelerin sayısını döndürür.
	* IntStream method min, muhtemelen akıştaki en küçük int'yi içeren bir OptionalInt (java.util paketi) döndürür.
	* Herhangi bir akış için, akışta öğe bulunmaması mümkündür. OptionalInt öğesinin döndürülmesi, akış en az bir öğe içeriyorsa min yönteminin en düşük değeri döndürmesini sağlar. 
	* OptionalInt'in getAsInt yöntemi, varsa değeri elde eder; aksi takdirde, bir NoSuchElementException atar
	* IntStream yöntemi max, muhtemelen akıştaki en büyük int'yi içeren bir OptionalInt döndürür.
	* IntStream yöntem average(), muhtemelen akıştaki int'lerin ortalamasını double türünde bir değer olarak içeren bir OptionalDouble (java.util paketi) döndürür. OptionalDouble'ın getAsDouble yöntemi, varsa değeri elde eder; aksi takdirde, bir NoSuchElementException atar. 
	* IntStream method summaryStatistics, count, min, max, sum ve average işlemlerini bir IntStream öğesinin tek geçişinde gerçekleştirir ve sonuçları bir IntSummaryStatistics nesnesi (package java.util) olarak döndürür. 
	
		-Section 17.7.4 Sorting IntStream Values-
	
	* IntStream ara işlem sorted order, akışın öğelerini varsayılan olarak artan düzene sıralar.

		-Section 17.8 Functional Interfaces-
	
	* Functional bir arayüz tam olarak bir soyut yöntem içerir. Bu tür arabirimler tek soyut yöntem (SAM) arabirimleri olarak da bilinir.
	* İşlevsel programcılar, yalnızca parametrelerine bağlı olan saf işlevlerle çalışırlar, yan etkileri yoktur ve herhangi bir durumu korumaz.
	* Saf işlevler, genellikle lambdas olarak tanımlanan işlevsel arabirimlerin uygulamalarıdır.
	* java.util.function paketi birkaç işlevsel arayüz içerir.
	
	 	
	 	-Section 17.9 Lambdas: A Deeper Look-
	
	* Lambda ifadeleri, işlevsel arabirimlerin beklendiği her yerde kullanılabilir.
	* Java derleyicisi genellikle lambda'nın parametrelerinin türlerini ve lambda tarafından döndürülen türü, lambda'nın kullanıldığı bağlamdan çıkarabilir. 
	 	Bu, lambda'nın hedef türü tarafından, lambda'nın kodda göründüğü yerde beklenen işlevsel arabirim türü tarafından belirlenir. 
	* Yöntemlerin aksine, lambdaların kendi kapsamları yoktur.
	* Çevreleyen yöntemden (lambda'nın sözcüksel kapsamı olarak bilinir) yerel bir değişkene başvuran bir lambda, yakalayan bir lambda'dır. Derleyici, yerel değişkenin değerini yakalar ve lambda sonunda yürütüldüğünde lambda'nın değeri kullanabilmesini sağlamak için lambda ile birlikte depolar. 
	* Bir lambda'nın sözlük kapsamında başvurduğu herhangi bir yerel değişken final veya etkili bir effectively final olmalıdır.  
	* Derleyici, yerel bir değişkenin nihai olarak bildirilmiş olabileceği sonucuna varırsa, çünkü içine alma yöntemi bildirildikten ve başlatıldıktan sonra değişkeni hiçbir zaman değiştirmez, o zaman değişken effectively finaldir.
	
	
		-Section 17.10 Stream<Integer> Manipulations-
	
	* Akışlar, başvuru türündeki nesneler üzerinde görevler gerçekleştirebilir.
	* Arrays yöntemi asList bir dizinin List görünümünü oluşturur. 
	
	 
		-Section 17.10.1 Creating a Stream<Integer>-
	
	* Arrays yöntem akışı, bir nesne dizisinden Stream oluşturmak için kullanılabilir.
	* Interface Stream (paketi java.util.stream), herhangi bir referans türünde akış operasyonlarını gerçekleştirmek için genel bir arayüzdür.
	* Class Arrays ayrıca int, long ve double dizilerden veya dizilerdeki öğe aralıklarından IntStreams, LongStreams ve DoubleStreams oluşturmak için yöntem akışının aşırı yüklenmiş sürümlerini de sağlar
	
		-Section 17.10.2 Sorting a Stream and Collecting the Results-
	
	* Akışları işlerken, daha sonra bunlar üzerinde işlem yapabilmek için genellikle sonuçları içeren yeni koleksiyonlar oluşturursunuz. Bir koleksiyon oluşturmak için Stream'in terminal işlemi collect komutunu kullanabilirsiniz.
	* Method collect, bir Liste, Map veya Set oluşturan ve akış ardışık düzeninin sonuçlarını koleksiyona yerleştirerek değiştiren değiştirilebilir bir azaltma(reduction) işlemi gerçekleştirir.
	* toArray , sonuçları Stream'in öğe türünde yeni bir diziye yerleştirir.
	* Collector (java.util.stream paketi), değiştirilebilir bir reduction nasıl gerçekleştirileceğini belirtir.
	* Sınıf Collectors (java.util.stream paketi), önceden tanımlanmış Collector uygulamalarını döndüren statik yöntemler sağlar. 
	* Collectors yöntemi toList, Stream'in öğelerini List'e yerleştiren bir Collector döndürür.
	
	
		-Section 17.10.3 Filtering a Stream and Storing the Results for Later Use-
	
	* Stream method filter'ın lambda bağımsız değişkeni, parametre değerinin yüklemi karşılayıp karşılamadığını belirten bir boolean döndüren tek parametreli bir yöntemi temsil eden işlevsel arabirim Predicate'i (package java.util.function) uygular. 

		
		-Section 17.11.1 Mapping Strings to Uppercase-
	
	* Stream yöntem map(), bağımsız değişken olarak işlevsel arabirim Function uygulayan bir nesne alır. Bu arabirim, bir görevi parametresiyle gerçekleştiren ve ardından sonucu döndüren tek parametreli bir yöntemi temsil eder.
	
		-Section 17.11.2 Filtering Strings Then Sorting Them in Case-Insensitive Ascending Order-
	
	* Stream yönteminin overload edilmiş sorted metodu, karşılaştırılan ilk değer ikinciden küçükse negatif bir değer, eşitse 0 ve ilk değer ikinciden büyükse pozitif bir değer döndüren bir karşılaştırma yöntemi tanımlayan bir Comparator alır.
	* Varsayılan olarak, sorted yöntem türün doğal sırasını kullanır. Önceden tanımlanmış Comparator'a String.CASE_INSENSITIVE_ORDER geçirilmesi, büyük\/küçük harfe duyarlı olmayan bir sıralama gerçekleştirir.
	 	
	 	
	 	-Section 17.11.3 Filtering Strings Then Sorting Them in Case-Insensitive Descending Order-
	
	* İşlevsel arabirim Comparator, varolan bir Comparator sıralamasını tersine çeviren ters çevrilmiş varsayılan yöntemi içerir. 
	

	 	
		-Section 17.12.2 Filtering Employees with Salaries in a Specified Range-
	
	* Bir lambda'yı daha sonra kullanmak üzere bir değişkende depolayabilirsiniz.
	* Lazy evaluation güzel performans özelliklerinden biri, istenen sonuç elde edilir edilmez akış işlem hattının işlenmesini durdurmak için kısa devre(short-cut) değerlendirmesi yapabilme yeteneğidir.
	* Akış yöntemi findFirst kısa devreli bir terminal işlemi, akış ardışık düzenini işler ve akışın ara işlemlerinden ilk nesne bulunur bulunmaz işlemeyi sonlandırır. 
	
		
		-Section 17.12.3 Sorting Employees By Multiple Fields-
	
	* Comparatorlar varsayılan yöntemle oluşturulabilir  (thenComparing).
	* java.util.function paketindeki birçok işlevsel arabirim, işlevsellik oluşturmanıza olanak tanıyan varsayılan yöntemler sağlar.
	* Intpredicate’in varsayılan yöntemi IntPredicate ile bağımsız değişken olarak aldığı IntPredicate arasında kısa devre değerlendirmesi ile mantıksal bir AND gerçekleştirir.
	* Interface IntPredicate'in varsayılan yöntemi negate, çağrıldığı IntPredicate'in boole değerini tersine çevirir.
	* Intpredicate’in varsayılan yöntemi IntPredicate ile bağımsız değişken olarak aldığı IntPredicate arasında kısa devre değerlendirmesi ile mantıksal bir OR gerçekleştirir.
	* Interface Predicate, nesne bağımsız değişkeninin bir koşulu karşılayıp karşılamadığını belirten bir boolean döndüren bir yöntemi temsil eder.
	
	
	
		-Section 17.12.4 Mapping Employees to Unique Last Name Strings-
	
	* Stream method distinct eliminates any duplicates in the stream
	
	
		-Section 17.12.5 Grouping Employees By Department-
	
	* Collectors statik yöntem groupingBy tarafından döndürülen Collector, akıştaki nesneleri sınıflandıran bir İşlev alır. Bu İşlev tarafından döndürülen değerler, Mapcollection'da anahtar olarak kullanılır. Karşılık gelen değerler, varsayılan olarak, belirli bir kategorideki akış öğelerini içeren Listeler'dir.
	
		
		-Section 17.12.6 Counting the Number of Employees in Each Department-
	
	* İki bağımsız değişkene sahip Collectors static method groupingBy akıştaki nesneleri sınıflandıran bir İşlev ve İşlev tarafından sınıflandırılan nesneleri toplamak için kullanılan başka bir Collect alır.
	* Collectors statik yöntemi count, belirli bir sınıflandırmadaki öğeleri bir Listede toplamak yerine bu öğelerin sayısına indirger.
	
	
		-Section 17.12.7 Summing and Averaging Employee Salaries-
	
	* Akış yöntemi mapToDouble, nesneleri double değerlerle eşler ve bir DoubleStream döndürür.
	* mapToDouble yöntemi, double değer döndüren tek parametreli bir yöntemi temsil eden bir ToDoubleFunction (java.util.function paketi) alır.
	
		-Section 17.13 Creating a Stream<String> from a File-
	
	* Files yöntemi lines, bir<String> dosyadaki metin satırlarını okumak için bir Akış oluşturur.
	* Akış yöntemi flatMap, bir nesneyi bir akışa eşleyen bir İşlev alır, örneğin, bir metin satırı kelimelere dönüştürülür. 
	* Pattern yöntemi splitAsStream bir String'i tokenize etmek için normal bir ifade kullanır.
	* Map method entrySet, Map'in Key-value çiftlerini içeren bir Map.Entry nesneleri kümesi döndürür.
	* Set yöntemi akışı, Set'in öğelerini işlemek için bir akış döndürür.
	
	
		-Section 17.14 Streams of Random Values-
	
	* Class SecureRandom'ın ints, longs ve doubles yöntemleri (Random sınıfından devralınan) rasgele sayı akışları için sırasıyla IntStream, LongStream ve DoubleStream döndürür.
	* Bağımsız değişken içermeyen ints yöntemi, rasgele int değerlerinin sonsuz akışı için bir IntStream oluşturur. Sonsuz akış, sonsuz bir akışta işlemeyi tamamlamak için kısa devre terminal işlemi kullandığınız bilinmeyen sayıda öğeye sahip bir akıştır.
	* long bağımsız değişkenli ints yöntemi, belirtilen sayıda rasgele int değeri içeren bir IntStream oluşturur.
	* İki int bağımsız değişkenine sahip ints yöntemi, ilk bağımsız değişkenden başlayarak ve ikincisine kadar olan ancak bu bağımsız değişkeni içermeyen aralıktaki rasgele int değerlerinin sonsuz akışı için bir IntStream oluşturur.
	* long ve iki int bağımsız değişkenine sahip ints yöntemi, ilk bağımsız değişkenden ikincisine kadar olan aralıkta belirtilen sayıda rasgele int değeri içeren bir IntStream oluşturur.
	* Bir IntStream'i Stream'e dönüştürmek için<Integer> kutulu IntStream yöntemini çağırın.
	* İşlev statik yöntem identity, bağımsız değişkenini döndüren bir İşlev oluşturur.
	
	
		-Section 17.15 Infinite Streams-
	
	* Java'nın akış arayüzleri, bilinmeyen, potansiyel olarak sonsuz sayıda öğeyi temsil eden veri kaynaklarını da destekler. Bunlar sonsuz akışlar olarak bilinir.
	* IntStream yöntem iterator(), ilk bağımsız değişkeninde çekirdek değerle başlayan sıralı bir değer dizisi oluşturur. Sonraki her öğe, yinelemenin ikinci bağımsız değişkeni olarak belirtilen IntUnaryOperator dizisindeki önceki değere uygulanarak üretilir. 
	* Sonsuz bir akışın ürettiği toplam öğe sayısını, bir akıştan işlenecek en fazla öğe sayısını belirten limit() çağırarak sınırlayabilirsiniz.
	* Ayrıca, bir IntSupplier alan generate yöntemini kullanarak sıralanmamış sonsuz akışlar oluşturabilirsiniz. Bu arabirim, bağımsız değişken almayan ve int döndüren bir yöntemi temsil eder.
	
		-Section 17.17 Additional Notes on Java SE 8 Interfaces-
	
	* İşlevsel arabirimler yalnızca bir soyut yöntem içermelidir, ancak arabirim bildirimlerinde tam olarak uygulanan default yöntemleri ve statik yöntemleri de içerebilir.
	* Bir sınıf varsayılan yöntemlerle bir arabirim uyguladığında ve bunları geçersiz kılmadığında, sınıf varsayılan uygulamaları devralır. Bir arabirimin tasarımcısı, arabirimi uygulayan varolan kodu bozmadan yeni varsayılan ve statik yöntemler ekleyerek arabirimi geliştirebilir. 
	* Bir sınıf aynı varsayılan yöntemi iki arabirimden devralırsa, sınıfın bu yöntemi geçersiz kılması gerekir; aksi takdirde, derleyici bir derleme hatası oluşturur.
	* Bir arabirimin işlevsel bir arabirim olduğunu, önüne @FunctionalInterface ek açıklama ekleyerek bildirebilirsiniz.
	
*/
