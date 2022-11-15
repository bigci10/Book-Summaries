
public class WorkingStreams {}



/*
 	Önceki bölümde, akışların external iterationdan ,  internal iterationa . geçmenize izin verdiğini gördünüz. 
 	Aşağıdaki gibi, bir veri koleksiyonu üzerinden yinelemeyi açıkça yönettiğiniz kod yazmak yerine (dış yineleme),
 	
 	List<Dish> vegetarianDishes = new ArrayList<>();
 	for(Dish d: menu)
 		{
 			if(d.isVegetarian())
 				{
 					vegetarianDishes.add(d);
 				}
 		}
 	
 	
 	filtre ve collect işlemlerini destekleyen Akışlar API'sini (internal iteration) kullanarak sizin için veri toplama üzerindeki yinelemeyi yönetebilirsiniz.
 	
 	Tek yapmanız gereken, filtreleme davranışını filtre yöntemine bağımsız değişken olarak geçirmektir:
 	
 	List<Dish> veggetarianDishes = menu.stream().filter(Dish :: isVegetarian).collect(toList());
 	Bu farklı verilerle çalışma şekli yararlıdır, çünkü akış API'ının verilerin nasıl işleneceğine izin verirsiniz.
 	Sonuç olarak, Akışlar API'si perde arkasında çeşitli optimizasyonlar yapabilir.
 	
 	Ayrıca, dahili yinelemeyi kullanarak Akışlar API'si kodunuzu paralel olarak çalıştırmaya karar verebilir.
 	Dış yinelemeyi kullanarak bu mümkün değildir, çünkü tek iş parçacıklı adım adım sıralı yinelemeye bağlısınızdır. 
 	
*/


/*----------------------------------------------------------------------------------------------------------------------------------
 			Filtering
 	
 	Bu bölümde, bir akışın öğelerini seçme yollarına bakacağız: yüklemle filtreleme ve yalnızca benzersiz öğeleri filtreleme.
 	
 	Yüklemle filtreleme(Predicate)
 		Stream arabirimi bir filtre yöntemini destekler (şimdiye kadar aşina olmanız gerekir).
 		Bu işlem, bağımsız değişken olarak bir yüklem (boolean döndüren bir işlev) alır ve yüklemle eşleşen tüm öğeleri içeren bir akış döndürür.
 		
 		Örneğin, Şekil 5.1'de gösterildiği gibi tüm vejetaryen yemekleri filtreleyerek bir vejetaryen menü oluşturabilirsiniz:
 		
 		List<Dish> vegetarianMenu = menu.stream()
 									.filter(Dish::isVegetarian)
 									.collect(toList());
----------------------------------------------------------------------------------------------------------------------------------*/	
 	
/*----------------------------------------------------------------------------------------------------------------------------------
 	Filtering unique elements:
 		Akışlar ayrıca, benzersiz öğelere sahip bir akışı döndüren
 		distinct adlı bir yöntemi de destekler (karma kodun uygulanmasına göre ve akış tarafından üretilen nesnelerin eşit yöntemlerine göre).
 		Örneğin, aşağıdaki kod, bir listeden bile tüm sayı taşlarını filtreler ve daha sonra kopyaları ortadan kaldırır (karşılaştırma için eşit yöntem kullanarak). Şekil 5.2 bunu görsel olarak göstermektedir
 		
 		List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
 		numbers.stream()
 			   .filter(i -> i % 2 == 0)
 			   .distinct()
 			   .forEach(System.out::println);
 		
 		Şekil olarak 5.2 de gösterildi.
----------------------------------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------------------------------------------
  		Slicing a stream
  		
  	Bu bölümde, bir akıştaki öğelerin farklı şekillerde nasıl seçileceği ve atlanacağı ele alınacaktır. 
  	Bir yüklem kullanarak öğeleri verimli bir şekilde seçmenize veya bırakmanıza, 
  	bir akışın ilk birkaç öğesini yoksaymanıza veya bir akışı belirli bir boyuta kesmenize olanak tanıyan işlemler vardır.
  	
  	USING TAKEWHILE
  	List<Dish> specialMenu = Arrays.asList( 
 						new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
 						new Dish("prawns", false, 300, Dish.Type.FISH),
 						new Dish("rice", true, 350, Dish.Type.OTHER),
 						new Dish("chicken", false, 400, Dish.Type.MEAT),
 						new Dish("french fries", true, 530, Dish.Type.OTHER));
		         	
       					}
       
   320 kaloriden az kaloriye sahip yemekleri nasıl seçersiniz? İçgüdüsel olarak, önceki bölümden işlem filtresinin aşağıdaki gibi kullanılabileceğini zaten biliyorsunuz:
   	List<Dish> filteredMenu = specialMenu.stream()
 							.filter(dish -> dish.getCalories() < 320)
 							.collect(toList()); 
	
	Ancak, ilk listenin zaten kalori sayısına göre sıralandığını fark edeceksiniz!
	Burada filtre işlemini kullanmanın dezavantajı, tüm akış boyunca yinelemeniz gerekmesi ve yüklemin her öğeye uygulanmasıdır.
	Bunun yerine, 320 kaloriden daha büyük (veya eşit) bir yemek bulduğunuzda durabilirsiniz.
	Küçük bir listeyle bu büyük bir fayda gibi görünmeyebilir, ancak potansiyel olarak büyük bir öğe akışıyla çalışıyorsanız yararlı olabilir.
	
	takeWhile operasyonu sizi kurtarmak için burada! Bir yüklem kullanarak herhangi bir akışı (daha sonra öğreneceğiniz gibi sonsuz bir akışı bile) dilimlemenizi sağlar.
	Ama neyse ki, eşleşmeyen bir unsur bulduğunda durur. Bunu şu şekilde kullanabilirsiniz:
		List<Dish> slicedMenu1 = specialMenu.stream()
 								 .takeWhile(dish -> dish.getCalories() < 320)
 								 .collect(toList()); 

----------------------------------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------------------------------------------
	USING DROPWHILE
	Yine de diğer unsurları almaya ne dersiniz? 320 kaloriden daha fazla kaloriye sahip elementleri bulmaya ne dersiniz? 
	Bunun için dropWhile işlemini kullanabilirsiniz:
		List<Dish> slicedMenu2 = specialMenu.stream()
								 .dropWhile(dish -> dish.getCalories() < 320)
 								 .collect(toList()); 
 								 
		dropWhile operasyonu takeWhile'ın tamamlayıcısıdır. Yüklemin yanlış olduğu başlangıçtaki öğeleri atar.
		Yüklem true olarak değerlendirildikten sonra, kalan tüm öğeleri durdurur ve döndürür ve sonsuz sayıda kalan öğe varsa bile çalışır!
		
----------------------------------------------------------------------------------------------------------------------------------*/   	



/*----------------------------------------------------------------------------------------------------------------------------------
   	Truncating a stream 
   	Akışlar, belirli bir boyuttan daha uzun olmayan başka bir akış döndüren limit(n) yöntemini destekler. İstenen boyut, limite bağımsız değişken olarak geçirilir. 
   	
   	List<Dish> dishes = specialMenu.stream()
 						.filter(dish -> dish.getCalories() > 300) 
 						.limit(3)
 						.collect(toList()); 
 	
 	
     Sınırın sıralanmamış akışlarda da çalıştığını unutmayın (örneğin, kaynak bir Set ise).
     Bu durumda, limitle üretilen sonuç üzerinde herhangi bir işlemi varsaymamalısınız.
     (Şekil 5.3)
     
----------------------------------------------------------------------------------------------------------------------------------*/
     

/*----------------------------------------------------------------------------------------------------------------------------------
	Skipping elements
     
	Akışlar, ilk n öğeyi atan bir akış döndürmek için skip(n) yöntemini destekler.
    Akışta n'den az öğe varsa, boş bir akış döndürülür.
    Limit(n) ve skip(n)'nin tamamlayıcı olduğunu unutmayın!
    Örneğin, aşağıdaki kod 300'den fazla kaloriye sahip ilk iki yemeği atlar ve geri kalanını döndürür.
    (Şekil 5.4)
      	
    List <Dish> dishes = menu.stream()
      				     .filter(d -> d.getCalories())
      					 .skip(2)
      					 .collect(toList());
----------------------------------------------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------------------------------------------------------------
		Mapping
		
	Yaygın bir veri işleme deyimi, belirli nesnelerden bilgi seçmektir.
	Örneğin, SQL'de bir tablodan belirli bir sütunu seçebilirsiniz.
	Streams API'si, map ve flatMap yöntemleri aracılığıyla benzer olanaklar sağlar.
	
	"Applying a function to each element of a stream"
	
	Akışlar, bağımsız değişken olarak bir işlev alan map yöntemini destekler.
	İşlev, her bir öğeye uygulanır ve onu yeni bir öğeye eşler (eşleme kelimesi, dönüştürmeye benzer bir anlama sahip olduğu için kullanılır,
	 ancak \"değiştirmek\" yerine \"yeni bir sürümünü oluşturma\" nüansı vardır).
	
	Örneğin, aşağıdaki kodda, akıştaki yemeklerin adlarını ayıklamak için map yöntemine Dish::getName yöntem başvurusunu geçirirsiniz:
		List<String> dishNames = menu.stream()
								 .map(Dish::getName)
								 .collect(toList());
		
	getName yöntemi bir string döndürdüğünden, eşleme yöntemi tarafından çıktısı alınan akış Stream türündedir<String>.
	
	Map anlayışınızı sağlamlaştırmak için biraz farklı bir örnek alalım. 
	Bir kelime listesi verildiğinde, her kelime için karakter sayısının bir listesini döndürmek istersiniz.
	Bunu nasıl yapardınız? Listenin her öğesine bir işlev uygulamanız gerekir.
	Bu map yöntemi için bir iş gibi geliyor!
	
	Uygulanacak işlev bir kelime almalı ve uzunluğunu döndürmelidir. Bu sorunu, eşlemeye bir yöntem başvurusu String::length geçirerek aşağıdaki gibi çözebilirsiniz:
	
	List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
	
	List<Integer> wordLengths = words.stream()
								.map(String::length)
								.collect(toList());
	
	Her yemeğin adını çıkardığınız örneğe geri dönelim. Ya her yemeğin adının uzunluğunu öğrenmek isteseydiniz? 
	Bunu, başka bir mapi aşağıdaki gibi zincirleyerek yapabilirsiniz:
		
		List<Integer> dishNameLengths = menu.stream()
										.map(Dish::getName)
										.map(String::length).collect(toList());
							
	
----------------------------------------------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------------------------------------------------------------
			Flattening streams
	Map yöntemini kullanarak bir listedeki her kelimenin uzunluğunun nasıl döndürüleceğini gördünüz.
	Bu fikri biraz daha genişletelim: Bir kelime listesi için tüm benzersiz karakterlerin bir listesini nasıl döndürebilirsiniz? 
	For example, given the list of words ["Hello," "World"] you’d like to return the list ["H," "e," "l," "o," "W," "r," "d"].
	
	Bunun kolay olduğunu, her kelimeyi bir karakter listesine eşleyebileceğinizi ve ardından yinelenen karakterleri filtrelemek için distinct çağırabileceğinizi düşünebilirsiniz.
	
	words.stream().map(word -> word.split("")).distinct().collect(toList());
	Bu yaklaşımla ilgili sorun, map yöntemine geçirilen lambda'nın her sözcük için bir String[] (bir String dizisi) döndürmesidir.
	
	Eşleme yöntemi tarafından döndürülen akış, Stream<String[]> türündedir. İstediğiniz şey,<String> Akış'ın bir karakter akışını temsil etmesidir. Şekil 5.5 sorunu göstermektedir.
	Neyse ki flatMap yöntemini kullanarak bu soruna bir çözüm var! Nasıl çözüleceğini adım adım görelim.
	
	İlk olarak, bir dizi akışı yerine bir karakter akışına ihtiyacınız vardır. Bir dizi alıp akış üreten Arrays.stream() adlı bir yöntem vardır:
		String[] arrayOfWords = {"Goodbye", "World"};
		Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
		
		words.stream()
		.map(word -> word.split("")) 
		.map(Arrays::stream) 
		.distinct().collect(toList());
		
		Mevcut çözüm hala işe yaramıyor! Bunun nedeni, artık bir akış listesiyle (daha doğrusu, List<Stream>) sahip olmanızdır<String>. 
		Gerçekten de, önce her kelimeyi ayrı harflerinden oluşan bir diziye dönüştürür ve sonra her diziyi ayrı bir akışa dönüştürürsünüz.
		
	USING FLATMAP
	
	List<String> uniqueCharacters = words.stream()
									.map(word -> word.split("")) 
									.flatMap(Arrays::stream) 
									.distinct()
									.collect(toList());
	
	flatMap yöntemini kullanmak, her diziyi bir akışla değil, o akışın içeriğiyle eşleme etkisine sahiptir.
	map(Arrays::stream) kullanılırken oluşturulan tüm ayrı akışlar birleştirilir ve tek bir akışta düzleştirilir. Görsel(5.6)
	
	Özetle, flatMap yöntemi, bir akışın her değerini başka bir akışla değiştirmenize olanak tanır ve ardından oluşturulan tüm akışları tek bir akışta birleştirir.
	

----------------------------------------------------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------------------------------------------------
					 Finding and matching

Başka bir yaygın veri işleme deyimi, 
bir veri kümesindeki bazı öğelerin belirli bir öğeyle eşleşip eşleşmediğini bulmaktır. 
Akışlar API'si, bir akışın allMatch, anyMatch, noneMatch, findFirst ve findAny yöntemleri aracılığıyla bu tür olanaklar sağlar.

----------------------------------------------------------------------------------------------------------------------------------*/



/*
		Checking to see if a predicate matches at least one element
		
anyMatch yöntemi, \"Akışta verilen yüklemle eşleşen bir öğe var mı?\" 
sorusunu yanıtlamak için kullanılabilir. 
Örneğin, menünün vejetaryen bir seçeneği olup olmadığını öğrenmek için kullanabilirsiniz:

if(menu.stream().anyMatch(Dish::isVegetarian))
{
	System.out.println("The menu is (somewhat) vegetarian friendly!!)
}
anyMatch yöntemi bir boolean döndürür ve bu nedenle bir terminal işlemidir.
*/



/*-------------------------------------------------------------------------------------
		 Checking to see if a predicate matches all elements

	allMatch yöntemi, anyMatch'e benzer şekilde çalışır, ancak akışın tüm öğelerinin verilen yüklemle eşleşip eşleşmediğini kontrol eder.
	Örneğin, menünün sağlıklı olup olmadığını öğrenmek için kullanabilirsiniz (tüm yemekler 1000 kalorinin altındadır):
	
	boolean isHealthy = menu.stream()
						.allMatch(dish -> dish.getCalories() < 1000);
	
	allMatch'in tam tersi noneMatch'tir. 
	Akıştaki hiçbir öğenin verilen yüklemle eşleşmemesini sağlar. 
	Örneğin, noneMatch kullanarak önceki örneği aşağıdaki gibi yeniden yazabilirsiniz:
	
	boolean isHealthy = menu.stream()
						.noneMatch(d -> d.getCalories() >= 1000);
	
----------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------
 		 Finding an element
 		 
	findAny yöntemi, geçerli akışın rasgele bir öğesini döndürür. 
	Diğer akış işlemleriyle birlikte kullanılabilir.
	
	Örneğin, vejetaryen bir yemek bulmak isteyebilirsiniz. 
	Bu sorguyu ifade etmek için filtre yöntemini ve findAny öğesini birleştirebilirsiniz:
	
	Optional<Dish> dish = menu.stream()
						 .filter(Dish::isVegetarian)
						 .findAny();
						 
	Akış işlem hattı, kısa devre kullanılarak bir sonuç bulunur bulunmaz tek bir geçiş gerçekleştirmek ve bitirmek için perde arkasında optimize edilecektir. 
	Ama bir dakika; koddaki bu Optional nedir?
	
-----------------------------------------------------------------------------------------*/




/*----------------------------------------------------------------------------------------
			OPTIONAL IN A NUTSHELL
			
	İsteğe bağlı<T> sınıf (java.util.Optional), bir değerin varlığını veya yokluğunu temsil eden bir kapsayıcı sınıfıdır. 
	Önceki kodda, findAny herhangi bir öğe bulamıyor olabilir. 

	Hataya açık olduğu bilinen null değerini döndürmek yerine, Java 8 kütüphane tasarımcıları Optional<T> tanıttı.
	Burada İsteğe Bağlı ayrıntılarına girmeyeceğiz, 
	çünkü kodunuzun null denetimle ilgili hataları önlemek için İsteğe Bağlı kullanımından nasıl yararlanabileceğini 
	11. bölümde ayrıntılı olarak göstereceğiz.
	
	
	Ancak şimdilik, İsteğe Bağlı olarak sizi bir değerin varlığını açıkça kontrol etmeye
	veya bir değerin yokluğuyla ilgilenmeye zorlayan birkaç yöntem olduğunu bilmek güzel:
		
		-isPresent(),  bir değer içeriyorsa true değerini döndürür, aksi takdirde false değerini döndürür.
		-ifPresent(Consumer<T> block), bir değer varsa verilen bloğu yürütür.
		-T Get () varsa değeri döndürür; Aksi takdirde bir nosuchelement istisnası atar
		-T orElse(T other) varsa değeri döndürür; aksi takdirde varsayılan bir değer döndürür.
		
	Örneğin, önceki kodda, adına erişmek için İsteğe Bağlı nesnede bir değerin varlığını açıkça kontrol etmeniz gerekir.
		
		menu.stream()
			.filter(Dish::isVegetarian)
			.findAny()
			.ifPresent(dish -> System.out.println(dish.getName()) //Eğer değer varsa bunu bastırır.

	
	

---------------------------------------------------------------------------------------------*/




/*-------------------------------------------------------------------------------------------
 		Finding the first element
 	
 	Bazı akışlarda, öğelerin akışta mantıksal olarak görünme sırasını belirten bir karşılaşma sırası vardır 
 	(örneğin, bir Listeden veya sıralanmış bir veri dizisinden oluşturulan bir akış).	
 	Bu tür akışlar için ilk öğeyi bulmak isteyebilirsiniz.
 	Bunun için findAny ile benzer şekilde çalışan findFirst yöntemi vardır (örneğin, bir sayı listesi verildiğinde aşağıdaki kod, 3'e bölünebilen ilk kareyi bulur)
 	
 	List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
 	Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
 													.map(n -> n * n)
 													.filter(n -> n % 3 == 0)
 													.findFirst(); //9
 													
 	When to use findFirst and findAny
 	Neden hem findFirst hem de findAny olduğunu merak edebilirsiniz. 
 	Cevap paralelliktir. İlk elemanı bulmak paralel olarak daha kısıtlayıcıdır. 
 	Hangi öğenin döndürüleceğini umursamıyorsanız, paralel akışları kullanırken daha az kısıtlayıcı olduğundan findAny kullanın.
 	
 	
 		
---------------------------------------------------------------------------------------------*/




/*----------------------------------------------------------------------------------------------------
 		Reducing

 	Gördüğünüz terminal işlemleri bir boolean (allMatch vb.), void (forEach) 
 	veya İsteğe Bağlı bir nesne (findAny vb.) döndürür. 
 	Ayrıca, bir akıştaki tüm öğeleri bir Listede birleştirmek için collect kullanıyorsunuz. 	
 	
 	
 	Bu bölümde, \"Menüdeki tüm kalorilerin toplamını hesapla\" veya \"Menüdeki en yüksek kalorili yemek nedir?\" 
 	gibi daha karmaşık sorguları ifade etmek için bir akışın öğelerini nasıl birleştirebileceğinizi göreceksiniz.
 	
 	reduce işlemini kullanarak. Bu tür sorgular, Tamsayı gibi tek bir değer üretmek için akıştaki tüm öğeleri art arda birleştirir.
 	Bu sorgular reduce işlemleri (bir değere indirgenmiş bir akış) olarak sınıflandırılabilir.
 	
 	İşlevsel programlama dili jargonunda, 
 	buna bir kıvrım olarak atıfta bulunulur, 
 	çünkü bu işlemi, katlama işleminin sonucu olan küçük bir kare oluşturana kadar uzun bir kağıt parçasını (akışınızı) tekrar tekrar katlamak olarak görebilirsiniz.
 	
------------------------------------------------------------------------------------------------------*/


/*------------------------------------------------------------------------------------------------------
 		Summing the elements
 		
 	Reduce yönteminin nasıl kullanılacağını araştırmadan önce, her biri için bir döngü kullanarak bir sayı listesinin öğelerini nasıl toplayacağınızı görmek yardımcı olur: 
 	
 	int sum = 0;
 	for (int x : numbers)
 	{
 		sum += x;
 	}
 	
 	Sayıların her öğesi, bir sonuç oluşturmak için toplama işleci ile yinelemeli olarak birleştirilir.
 	Toplama işlemini art arda kullanarak sayı listesini tek bir sayıya indirgersiniz.
 	
 	Bu kodda iki parametre vardır:
 		-Toplam değişkeninin başlangıç değeri, bu durumda 0
 		-Bu durumda, listenin tüm öğelerini birleştirme işlemi  
 		
 	Bu kodu tekrar tekrar kopyalayıp yapıştırmak zorunda kalmadan tüm sayıları çarpabilseydiniz harika olmaz mıydı?
 	
 	Bu tekrarlanan uygulama modelini soyutlayan reduce işleminin yardımcı olabileceği yer burasıdır.
 	Bir akışın tüm öğelerini aşağıdaki gibi toplayabilirsiniz:
 	
 		int sum = numbers.stream()
 					 	 .reduce(0, (a, b) -> a + b);
 		
 		reduce takes two arguments:
 			-Bir başlangıç değeri, burada 0.
 			-<T> İki öğeyi birleştirmek ve yeni bir değer üretmek için bir BinaryOperator; burada lambda (a, b) -> a b kullanırsınız.
 			
 		int product = numbers.stream()
 							 .reduce(1, (a, b) -> a * b);	//bu şekilde kolayca çarpabiliriz.
 		
 		
 	Şekil 5.7, küçültme işleminin bir akışta nasıl çalıştığını göstermektedir
 	: lambda, 4, 5, 3, 9 tamsayılarını içeren akış tek bir değere indirgenene kadar her bir öğeyi tekrar tekrar birleştirir.
 	
 	
 	Azaltma işleminin bir sayı akışını toplamak için nasıl gerçekleştiğine derinlemesine bir göz atalım.
 	İlk olarak, lambda'nın (a) ilk parametresi olarak 0 kullanılır ve 4 akıştan tüketilir ve ikinci parametre (b) olarak kullanılır. 0 4, 4 üretir ve yeni değer haline gelir.
 	Daha sonra lambda, birikmiş değer ve akışın bir sonraki elemanı olan 5 ile tekrar çağrılır ve bu da yeni birikmiş değeri üretir, 9.
 	İleriye dönük olarak, lambda biriken değer ve 12 üreten bir sonraki eleman olan 3 ile tekrar çağrılır.
 	Son olarak, lambda 12 ve akışın son elemanı, 9 ile adlandırılır ve bu da son değeri üreten 21'dir.
 	
 	
 	Bir yöntem başvurusu kullanarak bu kodu daha özlü hale getirebilirsiniz. 
 	Java 8'den Integer sınıfı artık lambda ile aynı kodu tekrar tekrar yazmak yerine istediğiniz iki sayı eklemek için statik bir toplam yöntemiyle birlikte gelir:
 	
 	int sum = numbers.stream().reduce(0, Integer::sum);
 	
 	Ayrıca, başlangıç değeri almayan aşırı yüklenmiş bir reduce değişkeni de vardır, ancak İsteğe Bağlı bir nesne döndürür:
 	Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
 	
 	Neden İsteğe Bağlı olarak döndürülüyor<Integer>? 
 	Akışın hiçbir öğe içermediği durumu göz önünde bulundurun.
 	reduce işlemi, başlangıç değeri olmadığından toplam döndüremez.
 	Bu nedenle sonuç, toplamın eksik olabileceğini belirtmek için Optional bir nesneye sarılır.
 	
 -------------------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------
 			 Maximum and minimum
 	
 	Max ve min hesaplamak için ihtiyacınız olan tek şeyin reduce olduğu ortaya çıktı! 
 	Bir akıştaki maksimum veya minimum öğeyi hesaplamak için reduce hakkında yeni öğrendiklerinizi nasıl uygulayabileceğinizi görelim.
 	
 	As you saw, reduce takes two parameters:
 		-Başlangıç değeri 
 		-İki akış elemanını birleştirmek ve yeni bir değer üretmek için bir lambda
 	
 	Lambda, şekil 5.7'de gösterildiği gibi, toplama işleci ile akışın her bir elemanına adım adım uygulanır.
 	İki element verildiğinde, bunların maksimumunu döndüren bir lambda'ya ihtiyacınız var.
 	Reduce işlemi, tüm akış tüketilene kadar yeni bir maksimum üretmek için akışın bir sonraki elemanı ile yeni değeri kullanacaktır!
 	Bir akıştaki maksimum değeri hesaplamak için aşağıdaki gibi reduce kullanabilirsiniz; bu durum şekil 5.8'de gösterilmiştir.
 	
 	Optional<Integer> max = numbers.stream().reduce(Integer::max);
 	
 	veya minumum değeri hesaplamak için : 
 	Optional<Integer> min = numbers.stream().reduce(Integer::min);
----------------------------------------------------------------------------*/




/*
 		Benefit of the reduce method and parallelism
 		
 	Daha önce yazdığınız adım adım yineleme toplamına kıyasla azaltmayı kullanmanın yararı, 
 	yinelemenin, iç uygulamanın azaltma işlemini paralel olarak gerçekleştirmeyi seçmesine olanak tanıyan iç yineleme kullanılarak soyutlanmasıdır.
 	Yinelemeli toplama örneği, düzgün bir şekilde paralelleştirmeyen bir toplam değişkenine yönelik paylaşılan güncelleştirmeleri içerir.
 	Gerekli senkronizasyonu eklerseniz, muhtemelen thredlerin paralelliğin size vermesi gereken tüm performanstan çaldığını keşfedeceksiniz!
 	Bu hesaplamayı paralelleştirmek farklı bir yaklaşım gerektirir: girişi bölümlemek, bölümleri toplamak ve toplamları birleştirmek.
 	
	int sum = numbers.parallelStream().reduce(0, Integer::sum);
*/




/*---------------------------------------------------------------------------------------
 		Stream operations: stateless vs. stateful 
 		
	Çok sayıda akış işlemi gördünüz. 
	İlk bakışta onları her derde deva gibi gösterebilir. 
	Her şey sorunsuz çalışır ve bir koleksiyondan akış almak için akış yerine parallelStream kullandığınızda paralelliği ücretsiz olarak elde edersiniz.
	
	Kesinlikle birçok uygulama için durum böyledir, 
	önceki örneklerde gördüğünüz gibi. 
	Bir yemek listesini bir akışa dönüştürebilir, belirli bir türdeki çeşitli yemekleri seçmek için filtre uygulayabilir, 
	ardından kalori sayısını eklemek için ortaya çıkan akışı mapleyebilit ve ardından menünün toplam kalori sayısını üretmek için reduce kullanabilirsiniz. 
	Hatta şunları bile yapabilirsiniz:Bu tür akış hesaplamalarını paralel olarak yapın. Ancak bu operasyonların farklı özellikleri vardır. 
	Hangi iç durumları işlemesi gerektiği konusunda sorunlar var.
	
	map ve filtre gibi işlemler her bir öğeyi giriş akışından alır ve çıkış akışında sıfır veya bir sonuç üretir. 
	Bu işlemler genel olarak durum bilgisi yoktur: bir iç durumları yoktur (kullanıcı tarafından sağlanan lambda veya yöntem referansının iç değiştirilebilir bir durumu olmadığı varsayılarak)

	Ancak reduce, sum ve max  gibi işlemlerin, sonucu biriktirmek için iç duruma sahip olması gerekir. 
	Bu durumda iç durum küçüktür. Örneğimizde bir int veya doubledan oluşuyordu. 
	İç durum, işlenen akışta kaç öğe olursa olsun sınırlı boyuttadır.
	
	
	
	Buna karşılık, sorted veya distinct gibi bazı işlemler ilk başta filtre veya map gibi davranıyor gibi görünüyor 
	- hepsi bir akış alıyor ve başka bir akış üretiyor (bir ara işlem) - ancak çok önemli bir fark var. 
	Bir akıştan kopyaları sıralamak ve kaldırmak, işlerini yapmak için önceki geçmişi bilmeyi gerektirir. 
	Örneğin, sorted, çıktı akışına tek bir öğe eklenmeden önce tüm öğelerin arabelleğe alınmasını gerektirir; işlemin depolama gereksinimi sınırsızdır. 
	Veri akışı büyük veya sonsuzsa bu sorunlu olabilir. (Tüm asal sayıların akışını tersine çevirmek ne yapmalı? Matematiğin bize var olmadığını söylediği en büyük asal sayıyı döndürmelidir.) 
	Bu operasyonlara durum bilgisi olan operasyonlar diyoruz.
	
	Artık karmaşık veri işleme sorgularını ifade etmek için kullanabileceğiniz birçok akış işlemi gördünüz! 
	Tablo 1 - 2 şu ana kadar görülen işlemleri özetlemektedir.
-----------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------
 			Numeric streams

 	Daha önce bir akışın öğelerinin toplamını hesaplamak için reduce yöntemini kullanabileceğinizi görmüşsünüzdür. 
 	Örneğin, menüdeki kalori sayısını aşağıdaki gibi hesaplayabilirsiniz:
 	
 		int calories = menu.stream()
 						   .map(Dish::getCalories)
 						   .reduce(0, Integer::sum);
 	

	Bu kodla ilgili sorun, sinsi bir box maliyetinin olmasıdır. 
	Perde arkasında, her Tamsayı'nın toplama işlemini gerçekleştirmeden önce bir ilkelin kutusunun açılması gerekir. 
	Ek olarak, bir sum yöntemini doğrudan aşağıdaki gibi çağırabilseydiniz daha güzel olmaz mıydı? 

		int calories = menu.stream()
						   .map(Dish::getCalories)
						   .sum();
						   
	Ancak bu mümkün değil. 
	Sorun, map metodunun bir akış oluşturmasıdır Stream<T>. 
	Akışın öğeleri Tamsayı türünde olsa da, akışlar arabirimi bir toplam yöntemi tanımlamaz. 
	
	Neden olmasın? Diyelim ki Stream<Dish> akışınız var menü gibi ; bulaşıkları toplayabilmek mantıklı olmazdı.
	Ama endişelenmeyin; Akışlar API'si, sayı akışlarıyla çalışmak için özel yöntemleri destekleyen ilkel akış uzmanlıkları da sağlar.
	
-----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 		Primitive Stream Specializations
 	
 	Java 8, bu sorunu çözmek için üç ilkel özel akış arabirimi sunar: 
 	IntStream, DoubleStream ve LongStream, sırasıyla bir akışın öğelerini int, long ve double olacak şekilde uzmanlaştırır ve böylece gizli box maliyetlerinden kaçınır.
 	Bu arabirimlerin her biri, sayısal akışın toplamını hesaplamak için sum() ve maksimum öğeyi bulmak için max() gibi yaygın sayısal azaltmaları gerçekleştirmek için yeni yöntemler getirir. 
 	Hatırlanması gereken şey, bu uzmanlıkların ek karmaşıklığının akışlara özgü olmadığıdır. boxun karmaşıklığını, int ve Integer arasındaki (verimliliğe dayalı) farkı vb. Yansıtır. 
 	
 	
-----------------------------------------------------------------------------------------*/




/*-----------------------------------------------------------------------------------------
 			MAPPING TO A NUMERIC STREAM
 	
 	Bir akışı özel bir sürüme dönüştürmek için kullanacağınız en yaygın yöntemler mapToInt, mapToDouble ve mapToLong'dur.
 	Bu yöntemler tam olarak daha önce gördüğünüz map yöntemi gibi çalışır, ancak Stream<T> yerine özel bir akış döndürür.
 	Örneğin, menüdeki kalorilerin toplamını hesaplamak için mapToInt'i aşağıdaki gibi kullanabilirsiniz:
 	
 	int calories = menu.stream()
 					   .mapToInt(Dish::getCalories)
 					   .sum();
 	
 	Burada, mapToInt yöntemi her bir nesneden tüm kalorileri çıkarır ve sonuç olarak bir "IntStream" döndürür (bir Stream<Integer> yerine).
 	Daha sonra kalori toplamını hesaplamak için IntStream arayüzünde tanımlanan sum yöntemini çağırabilirsiniz!
 	Akış boşsa toplamın varsayılan olarak 0 döndüreceğini unutmayın. IntStream ayrıca max, min ve average gibi diğer kolaylık yöntemlerini de destekler.
 	
 -----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 		    CONVERTING BACK TO A STREAM OF OBJECTS
 		    
 	 Benzer şekilde, sayısal bir akışınız olduğunda, bunu özel olmayan bir akışa geri dönüştürmekle ilgilenebilirsiniz.
 	 Örneğin, bir IntStream'in işlemleri ilkel tamsayılar üretmek için sınırlandırılmıştır: Bir IntStream'in map işlemi, int alan ve int (IntUnaryOperator) üreten bir lambda alır.
 	 
 	 Ancak Dish gibi farklı bir değer üretmek isteyebilirsiniz.
 	 Bunun için daha genel olan akış arayüzünde tanımlanan operasyonlara erişmeniz gerekir.
 	 İlkel bir akıştan genel bir akışa dönüştürmek için (her int bir Tamsayıya kutulanır) aşağıdaki gibi kutulu yöntemi kullanabilirsiniz:
	 
	 IntStream intStream = menu.stream()  
	 						   .mapToInt(Dish::getCalories); Converts a Stream to a numeric stream
	 
	 Stream<Integer> stream = intStream.boxed; Converts the numeric stream to a stream;
	 Bir sonraki bölümde, genel bir akışa kutulanması gereken sayısal aralıklarla uğraşırken kutulu seçeneğin özellikle yararlı olduğunu öğreneceksiniz.
	
 
 -----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 			DEFAULT VALUES: OPTIONALINT
 			
 	Toplamlık örneği, varsayılan bir değere sahip olduğu için kullanışlıydı: 0. 
 	Ancak bir IntStream'deki maksimum öğeyi hesaplamak istiyorsanız, 0 yanlış bir sonuç olduğundan farklı bir şeye ihtiyacınız olacaktır.
	Akışın hiçbir öğesi olmadığını ve gerçek maksimumun 0 olduğunu nasıl ayırt edebilirsiniz?
	Daha önce, bir değerin varlığını veya yokluğunu gösteren bir konteyner olan Optional sınıfı tanıttık. Optional, Integer, String vb.

	Üç ilkel akış uzmanlığı için de Optionalın ilkel bir özelleştirilmiş sürümü vardır: OptionalInt, OptionalDouble ve OptionalLong. 
	Örneğin, bir IntStream'in maksimum öğesini, OptionalInt döndüren max yöntemini çağırarak bulabilirsiniz:
	
	OptionalInt maxCalories = menu.stream()
								  .mapToInt(Dish::getCalories)
								  .max();
	
	Artık maksimum değer yoksa varsayılan bir değer tanımlamak için OptionalInt'i açıkça işleyebilirsiniz:
	
	int max = maxCalories.orElse(1);
								  
-----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 				Numeric Ranges
 
 	Sayılarla uğraşırken ortak bir kullanım durumu, sayısal değer aralıklarıyla çalışmaktır.
 	Örneğin, 1 ile 100 arasındaki tüm sayıları oluşturmak istediğinizi varsayalım. 
 	Java 8, bu tür aralıkların oluşturulmasına yardımcı olmak için IntStream ve LongStream'de bulunan iki statik yöntem sunar: range ve rangeClosed. 
 	Her iki yöntem de aralığın başlangıç değerini ilk parametre ve aralığın bitiş değerini ikinci parametre olarak alır. 
 	Ancak aralık özeldir, oysa rangeClosed kapsayıcıdır. Bir örneğe bakalım.
 	
 	IntStream evenNumbers = IntStream.rangeClosed(1,100)
 									 .filter(n -> n % 2 == 0);
 	
 	System.out.println(evenNumbers.count());
 	
 	
 	Burada, 1 ile 100 arasındaki tüm sayıların bir aralığını oluşturmak için rangeClosed yöntemini kullanırsınız.
 	Bir akış üretir, böylece filtre yöntemini yalnızca çift sayıları seçecek şekilde zincirleyebilirsiniz.
 	Bu aşamada hiçbir hesaplama yapılmamıştır. Son olarak, ortaya çıkan akışta sayım çağrısı yaparsınız.
 	Count bir terminal işlemi olduğundan, akışı işler ve 1'den 100'e kadar olan çift sayıların sayısı olan 50 sonucunu döndürür.
 	Karşılaştırma yaparak, bunun yerine IntStream.range(1, 100) kullanıyor olsaydınız,  range is exclusive. olduğu için sonucun 49 çift sayı olacağını unutmayın.
 	
 	
-----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 			 Building streams

	Umarım, şimdiye kadar akışların veri işleme sorgularını ifade etmek için güçlü ve kullanışlı olduğuna ikna olmuşsunuzdur. 
	Akış yöntemini kullanarak bir koleksiyondan akış alabildiniz. 
	Ek olarak, size bir dizi sayıdan sayısal akışların nasıl oluşturulacağını gösterdik. 
	Ancak akışları daha birçok şekilde oluşturabilirsiniz! 
	Bu bölümde, sonsuz akışlar oluşturmak için bir dizi değerden, bir diziden, bir dosyadan ve hatta üretici bir işlevden nasıl akış oluşturabileceğiniz gösterilmektedir!
	
	
-----------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
  			Streams from values
	
	Herhangi bir sayıda parametre alabilen Stream.of statik yöntemini kullanarak açık değerlere sahip bir akış oluşturabilirsiniz.
	Örneğin, aşağıdaki kodda doğrudan Stream.of kullanarak bir dize akışı oluşturursunuz. 
	Daha sonra dizeleri tek tek yazdırmadan önce büyük harfe dönüştürürsünüz:		 
		
		Stream<String> stream = Stream.of("Modern","Java","In","Action");
		stream.map(String::tuUpperCase).forEach(System.out::println);
		
		empty metodunu kullanarak boş bir stream elde edelirsiniz
		Stream<String> emptyStream = Stream.empty();
		
-----------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
			 Stream from nullable
	
	
	Java 9'da, nullable bir nesneden akış oluşturmanıza olanak tanıyan yeni bir yöntem eklenmiştir.
	Akışlarla oynadıktan sonra, null olabilecek ve ardından bir akışa (veya null için boş bir akışa) dönüştürülmesi gereken bir nesneyi ayıkladığınız bir durumla karşılaşmış olabilirsiniz.
	
		
	Örneğin, System.getProperty yöntemi, verilen anahtarda bir özellik yoksa null değerini döndürür.
	Bir akışla birlikte kullanmak için null değerini aşağıdaki gibi açıkça denetlemeniz gerekir:
	
		String homeValue = System.getProperty("home");
		Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(value);
	
	Using Stream.ofNullable you can rewrite this code more simply:
		Stream<String> homeValueStream = Stream.ofNullable(System.getProperty("home"));
	
	Bu desen, flatMap ve nullable nesneler içerebilecek bir değer akışı ile birlikte özellikle kullanışlı olabilir:
		Stream<String> values = Stream.of("config","home","user")
								.flatMap(key -> Stream.ofNullable(System.getProperty(key)));
	
	
-----------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
			Stream from arrays
	
	Bir diziyi parametre olarak alan Arrays.stream statik yöntemini kullanarak bir diziden akış oluşturabilirsiniz. 
	Örneğin, bir dizi ilkel int'yi IntStream'e dönüştürebilir ve ardından aşağıdaki gibi bir int üretmek için IntStream'i toplayabilirsiniz:
	
		int[] numbers = {2,3,5,7,11,13};
		int sum = Arrays.stream(numbers).sum();
		
	
	
-----------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
			Streams from files 
	
	Bir dosyayı işleme gibi G\/Ç işlemleri için kullanılan Java'nın NIO API'si (engellemeyen G\/Ç), Akışlar API'sinden yararlanacak şekilde güncelleştirilmiştir.
	java.nio.file.Files dosyasındaki birçok statik yöntem bir akış döndürür.
	Örneğin, yararlı bir yöntem, belirli bir dosyadan dize olarak bir satır akışı döndüren Files.lines'tır.
	Şimdiye kadar öğrendiklerinizi kullanarak, bir dosyadaki benzersiz sözcük sayısını aşağıdaki gibi bulmak için bu yöntemi kullanabilirsiniz:
	
	long uniqueWords = 0;
	try
	{
		Stream<String> lines = Files.lines(Paths.get("data.txt"),Charset.defaultCharset()))
							   {
							   	 uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
							   	 					.distinct()
							   	 					.count();
							   }
	catch(IOException e)
	{
	}
	
	
	Her öğenin verilen dosyadaki bir satır olduğu bir akışı döndürmek için Files.lines kullanın.
	Bu çağrı, akışın kaynağı bir I\O kaynağı olduğundan bir deneme\/yakalama bloğuyla çevrilidir.
	Aslında, Files.lines çağrısı, sızıntıyı önlemek için kapatılması gereken bir G\/Ç kaynağı açacaktır.
	Geçmişte, bunu yapmak için açık bir finally ile engellemeye ihtiyacınız olurdu.
	Akış arabirimi kullanışlı bir şekilde AutoCloseable arabirimini uygular.
	Bu, kaynağın yönetiminin deneme bloğu içinde sizin için işlendiği anlamına gelir.
	Bir satır akışınız olduğunda, split yöntemini çağırarak her satırı kelimelere bölebilirsiniz.

-----------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------

			Streams from functions: creating infinite streams!
			
	Akışlar API'si, bir işlevden akış oluşturmak için iki statik yöntem sağlar: Stream.iterate ve Stream.generate.
	Bu iki işlem, sonsuz akış dediğimiz, sabit bir koleksiyondan akış oluşturduğunuzda olduğu gibi sabit bir boyuta sahip olmayan bir akış oluşturmanıza olanak tanır. 
	'İterate' ve 'generate' tarafından üretilen akışlar, bir işlev verilen talep üzerine değerler oluşturur ve bu nedenle değerleri sonsuza dek hesaplayabilir!
	Sonsuz sayıda değer yazdırmaktan kaçınmak için bu tür akışlarda limit(n) kullanmak genellikle mantıklıdır.
	
	Iterate i açıklamadan önce yinelemenin nasıl kullanılacağına dair basit bir örneğe bakalım:
	Stream.iterate(0,n -> n + 2)
		  .limit(10)
		  .forEach(System.out::println);
	
	Iterator yöntemi, <T>üretilen her yeni değere art arda uygulanmak üzere bir başlangıç değeri, burada 0 ve bir lambda (UnaryOperator türünde) alır.
	Burada, lambda n -> n 2'yi kullanarak 2 ile eklenen önceki öğeyi döndürürsünüz. Sonuç olarak, yineleme yöntemi tüm çift sayıların bir akışını üretir: akışın ilk öğesi başlangıç değeri 0'dır.
	Daha sonra yeni değer 2'yi üretmek için 2 ekler; yeni değer üretmek için tekrar 2 ekler 4 vb. Bu yineleme işlemi temelde sıralıdır, çünkü sonuç önceki uygulamaya bağlıdır.
	Bu işlemin sonsuz bir akış ürettiğini, değerlerin isteğe bağlı olarak hesaplandığını ve sonsuza dek hesaplanabileceğini unutmayın. Akışın unbounded olduğunu söylüyoruz.
	Daha önce de tartıştığımız gibi, bu bir akış ile koleksiyon arasındaki önemli bir farktır. 
	Akışın boyutunu açıkça sınırlamak için limit yöntemini kullanıyorsunuz.
	Burada sadece ilk 10 çift sayıyı seçersiniz. Daha sonra akışı kullanmak ve her öğeyi ayrı ayrı yazdırmak için forEach terminal işlemini çağırırsınız.
	
	Genel olarak, bir dizi ardışık değer (örneğin, bir tarihin ardından bir sonraki tarihi: 31 Ocak, 1 Şubat vb.) oluşturmanız gerektiğinde iterator kullanmanız gerekir.
	
	
	Java 9'da yineleme yöntemi bir predicate desteği ile geliştirildi. 
	Sınav için, 0'dan başlayarak numaralar oluşturabilirsiniz, ancak sayı 100'den büyük olduğunda yinelemeyi durdurabilirsiniz:
	
	IntStream.iterate(0,n -> n < 100, n -> n + 4)
			 .forEach(System.out::println);
	
	Yineleme yöntemi, ikinci bağımsız değişkeni olarak, yinelemeye ne zamana kadar devam edeceğinizi söyleyen bir predicate alır.
	Aynı sonucu elde etmek için filtre operasyonunu kullanabileceğinizi düşünebilirsiniz:
	
	IntStream.iterate(0,n -> n+4)
			 .filter( n -> n < 100)
			 .forEach(System.out::println);
	
	Ne yazık ki durum böyle değil. Aslında, bu kod sona ermezdi! 
	Bunun nedeni, filtrede sayıların artmaya devam ettiğini bilmenin bir yolu olmamasıdır, bu yüzden onları sonsuz bir şekilde filtrelemeye devam eder! 
	Sorunu, akışı kısa devre yapacak olan takeWhile'ı kullanarak çözebilirsiniz:
	
	IntStream.iterate(0, n -> n + 4)
			 .takeWhile(n -> n < 100)
			 .forEach(System.out::println);
	
	Ancak, bir predicate ile yinelemenin biraz daha özlü olduğunu kabul etmelisiniz!
	
-----------------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
 
				GENERATE
				
	Yineleme yöntemine benzer şekilde, generate yöntem, isteğe bağlı olarak hesaplanan sonsuz bir değer akışı üretmenizi sağlar.
	Ancak generate, üretilen her yeni değere art arda bir işlev uygulamaz. 
	Yeni değerler sağlamak için Supplier tipi bir lambda<T> gerekir. 
	Nasıl kullanılacağına dair bir örneğe bakalım:
	
	Stream.generate(Math::random)
	      .limit(5)
	      .forEach(System.out::println);
	
	bu kod 0 ile 1 arasında 5 adet double değer üretecektir. 
	
	Math.random statik yöntemi, yeni değerler için bir generator olarak kullanılır. 
	Yine limit yöntemini kullanarak akışın boyutunu açıkça sınırlarsınız; aksi takdirde akış sınırsız olurdu!
	Generate yöntemini kullanarak yapabileceğiniz başka yararlı bir şey olup olmadığını merak ediyor olabilirsiniz. 
	Kullandığımız Supplier (Math.random'a bir yöntem referansı) durum bilgisizdi: daha sonraki hesaplamalarda kullanılabilecek herhangi bir yere herhangi bir değer kaydetmiyordu. 
	Akışın bir sonraki değerini oluştururken değiştirebileceği ve kullanabileceği durumu depolayan bir Supplier oluşturabilirsiniz.
	
	Fibonacci serisini quiz 5.4'ten generate kullanarak nasıl oluşturabileceğinizi göstereceğiz, böylece yineleme yöntemini kullanan yaklaşımla karşılaştırabilirsiniz!
	Ancak, durum bilgisi olan bir Supplier paralel kodda kullanılmasının güvenli olmadığını belirtmek önemlidir. 
	Fibonacci için durum bilgisi olan IntSupplier bu bölümün sonunda eksiksizlik için gösterilmiştir, ancak genellikle kaçınılmalıdır!
	
	Örneğimizde, kutulama işlemlerinden kaçınmak üzere tasarlanmış kodu göstermek için bir IntStream kullanacağız.
	
	
	IntStream üzerinde oluşturma yöntemi Supplier<T> yerine bir IntSupplier alır. 
	Örneğin, sonsuz bir akış oluşturmanın yolu şudur:
	
	IntStream ones = IntStream.generate(() -> 1);
	
	Bölüm 3'te, Lambdas'ın yöntemin doğrudan satır içi uygulamasını sağlayarak fonksiyonel bir yüzün bir örneğini oluşturmanıza izin verdiğini gördünüz.
	Ayrıca, IntSupplier arayüzünde tanımlanan getAsInt yöntemini uygulayarak aşağıdaki gibi açık bir nesneyi iletebilirsiniz:
	
	IntStream twos = IntStream.generate(new IntSupplier()
					{
						public int getAsInt() {
							return 2;
						}
					};
	
	Generate yöntemi, verilen Supplier ı kullanır ve her zaman 2 döndüren getAsInt yöntemini tekrar tekrar çağırır.
	Ancak burada kullanılan anonim sınıf ile lambda arasındaki fark, anonim sınıfın getAsInt yönteminin değiştirebileceği alanlar aracılığıyla durumu tanımlayabilmesidir.
	Bu bir yan etki örneğidir. Şimdiye kadar gördüğünüz tüm lambdalar yan etkisi yoktu; hiçbir durumu değiştirmediler.
	
	Fibonacci görevlerimize geri dönmek için şimdi yapmanız gereken şey, serideki önceki değeri durumunda tutan bir IntSupplier oluşturmaktır, böylece getAsInt bir sonraki öğeyi hesaplamak için kullanabilir.
	Ayrıca, bir dahaki sefere çağrıldığında IntSupplier durumunu güncelleyebilir. 
	Aşağıdaki kod, çağrıldığında bir sonraki Fibonacci öğesini döndürecek bir IntSupplier öğesinin nasıl oluşturulacağını gösterir:
	
	IntSupplier fib = new IntSupplier(){
		private int previous = 0;
		private int current = 1;
		public int getAsInt()
		{
			int oldPrevious = this.previous;
			int nextValue = this.previous + this.current;
			this.previous = this.current;
			this.current = nextValue;
			return oldPrevious;
		}
	};
	
	IntStream.generate(fib).limit(10).forEach(System.out::println);
	
	Kod, IntSupplier'ın bir örneğini oluşturur. 
	Bu nesnenin değiştirilebilir bir durumu vardır: önceki Fibonacci öğesini ve geçerli Fibonacci öğesini iki örnek değişkeninde izler.
	getAsInt yöntemi, çağrıldığında nesnenin durumunu değiştirir, böylece her çağrıda yeni değerler üretir.
	Buna karşılık, yineleme kullanan yaklaşımımız tamamen değişmezdi; mevcut durumu değiştirmediniz, ancak her yinelemede yeni demetler oluşturuyordunuz.
	
	Sonsuz boyutlu bir akışla uğraştığınız için, limit() kullanarak boyutunu açıkça sınırlamanız gerektiğini unutmayın; aksi takdirde, terminal işlemi (bu durumda forEach) sonsuza dek hesaplanır
	Benzer şekilde, sonsuz bir akışı sıralayamaz veya azaltamazsınız, çünkü tüm öğelerin işlenmesi gerekir, ancak akış sonsuz sayıda öğe içerdiğinden bu sonsuza dek sürer!
	
-----------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------
 				Summaries 

 				
 	-Akışlar API'si, karmaşık veri işleme sorgularını ifade etmenizi sağlar. Ortak akış işlemleri tablo 5.1'de özetlenmiştir.
 	-Filtre, distinct, takeWhile (Java 9), dropWhile (Java 9), skip ve limit yöntemlerini kullanarak bir akışı filtreleyebilir ve dilimleyebilirsiniz.
 	-takeWhile ve dropWhile yöntemleri, kaynağın sıralandığını(sorted) bildiğiniz zaman filtreden daha verimlidir.
 	-Map ve flatMap yöntemlerini kullanarak bir akışın öğelerini ayıklayabilir veya dönüştürebilirsiniz.
 	-findFirst ve findAny yöntemlerini kullanarak bir akıştaki öğeleri bulabilirsiniz.
 		Bir akıştaki belirli bir predicate i allMatch, noneMatch ve anyMatch yöntemlerini kullanarak eşleştirebilirsiniz.
 	
 	-Bu yöntemler kısa devreden yararlanır: bir hesaplama bir sonuç bulunur bulunmaz durur; tüm akışı işlemeye gerek yoktur.
 	
 	-Bir akışın tüm öğelerini, reduce yöntemini kullanarak bir sonuç üretmek için, örneğin toplamı hesaplamak veya bir akışın maksimum değerini bulmak için yinelemeli olarak birleştirebilirsiniz.
 	
 	-Filtre ve Map gibi bazı işlemler durum bilgisi içermez: herhangi bir durum depolamazlar.
 		Bir değeri hesaplamak için depo durumunu azaltma gibi bazı işlemler,Sorted ve distinct gibi bazı işlemler de durumu depolar, çünkü yeni bir akışı döndürmeden önce akışın tüm öğelerini arabelleğe almaları gerekir.
 		bu tür operasyonlare statefull operations denir.
 	
 	-Akışların üç ilkel özel durumu  vardır: IntStream, DoubleStream ve LongStream. Operasyonları da buna göre özeldir .	
 	
 	-Akışlar yalnızca bir koleksiyondan değil, values, arrays, files ve iterator ve generator gibi belirli yöntemlerden de oluşturulabilir.
 	
 	-Sonsuz bir akışın sonsuz sayıda öğesi vardır (örneğin tüm olası dizeler). 
 	Bu mümkündür, çünkü bir akışın elemanları yalnızca talep üzerine üretilir. 
 	Limit gibi yöntemleri kullanarak sonsuz bir akıştan sonlu bir akış elde edebilirsiniz.
 	
-----------------------------------------------------------------------------------------*/




