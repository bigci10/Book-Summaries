package stream;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeadFirstJava {}


/*------------------------------------------------------------------------------------------------------------------------------
 Streamsi kullanırken lambda ifadelerinin ne kadar yararlı olabileceğini görecek ve bir koleksiyondaki verileri sorgulamak ve
  dönüştürmek için Streans API'sini nasıl kullanacağınızı öğreneceksiniz.
  -----------------------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------------------------
 				Bilgisayara NE istediğinizi söyleyin
 	
 	Bir renk listeniz olduğunu ve tüm renkleri yazdırmak istediğinizi hayal edin. Bunu yapmak için for döngüsünü kullanabilirsiniz.
 	
 -------------------------------------------------------------------------------------------------------------------------------*/
class Example
{
	public static void main(String[] args) 
	{
		List<String> allColors = List.of("Red","Blue","Yellow"); //List Of , bilinen bir değer grubundan yeni bir Liste oluşturmak için kullanılan bir \"kolaylık fabrikası yöntemi\"dir. 
		for(String color:allColors)
		{
			System.out.println(color);
		}
		
	}
}

/*----------------------------------------------------------------------------------------------------------------------------
	Ancak bir listedeki her öğeye bir şeyler yapmak veya yapmak istemek gerçekten yaygın bir şeydir.
	Bu nedenle, listedeki \"her biri için\" öğe için bir şey yapmak istediğimizde bir for döngüsü oluşturmak yerine, 
	forEach yöntemini Iterable arabiriminden çağırabiliriz, List Iterable'ı uygular, böylece Iterable arayüzündeki tüm yöntemlere sahiptir.
	
------------------------------------------------------------------------------------------------------------------------------*/

class Example2
{
	public static void main(String[] args) 
	{
		List<String> allColors = List.of("Red","Blue","Yellow");
		allColors.forEach(color -> System.out.println(color));
	}
}

/*-----------------------------------------------------------------------------------------------------
	Bir listenin forEach yöntemi, önceki bölümde ilk kez gördüğümüz bir lambda ifadesini alır.
	Bu, veri içeren bir nesneyi (\"burada kullanabileceğiniz bir nesne\") iletmek yerine davranışı 
	bir yönteme geçirmenin (\"bu talimatları izleyin\") bir yoludur.
-------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------
		For Loop vs For each 
	
	FL:Varsayılan olan benim! for döngüsü o kadar önemlidir ki, bir sürü programlama dili bana sahiptir.
		Bir programcının öğrendiği ilk şeylerden biridir! Birinin bir şey yapmak için belirli sayıda döngü yapması gerekiyorsa, döngü için güvenilir olanlarına ulaşacaklardır.
	
	FE:Pff. Lütfen. Çok yaşlısın; bu yüzden tüm programlama dillerindesiniz. Ama işler değişir, diller gelişir. Daha iyi bir yol var. Daha modern bir yol. O benim
	
	FL:Elbette, moda değişiyor. Ama bazen bu sadece bir hevestir; işler de modası geçiyor. Benim gibi bir klasik, Java dışı programcılar için bile sonsuza dek okumak ve yazmak kolay olacak.
	
	FE:Ancak geliştiricilerin sizi yazmak için ne kadar iş yapmaları gerektiğine bakın! Döngünün ne zaman başlatılacağını, artırılacağını ve durdurulacağını kontrol etmenin yanı sıra döngü içinde çalıştırılması gereken kodu yazmaları gerekir.
		Her türlü işler ters gidebilir! Beni kullanırlarsa, sadece her bir öğeye ne olması gerektiğini düşünmeleri gerekir, her bir öğeyi bulmak için nasıl döngü yapacakları konusunda endişelenmelerine gerek yoktur.
	
	FL:	Çok fazla iş mi?! Ha! Bir geliştirici, ne yapacağını ve nasıl yapılacağını açıkça belirtmek için küçük bir sözdiziminden korkmaz. En azından benimle, kodumu okuyan biri neler olup bittiğini açıkça görebilir.
	
	FE:Dostum, neler olup bittiğini görmek zorunda kalmamalılar. Yöntem adımda, tam olarak ne yaptığımı çok açık bir şekilde söylüyor - \"her biri için\" belirttikleri mantığı uygulayacağım. İş bitti.
	
	FL: Ben daha hızlıyım. Bunu herkes biliyor.
----------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------
 		WHEN FOR LOOPS GO WRONG
 	for döngüsü yerine forEach kullanmak, biraz daha az yazma anlamına gelir ve derleyiciye nasıl yapılacağını değil, ne yapmak istediğinizi söylemeye odaklanmak da güzeldir.
 	Kütüphanelerin bu gibi rutin kodlarla ilgilenmesine izin vermenin başka bir avantajı daha vardır - bu daha az yanlışlıkla hata anlamına gelebilir.
 		
	Kısa bir Java programı aşağıda listelenmiştir. Programın bir bloğu \neksik. Programın çıktısının \"1 2 3 4 5\" olmasını bekliyoruz. \nama bazen doğru bir for döngüsü elde etmek zordur.
	Aşağıdaki koda hangi for döngüsü uygulanmalıdır ? 
----------------------------------------------------------------------------------------------------------------------------------------------*/

//class Example3 
//{
//	public static void main(String[] args) 
//	{
//		List<Integer> nums = List.of(1,2,3,4,5);
//		String output = "";
//		
//		for(int i = 1; i < nums.size(); i++)
//			output += nums.get(i) + " ";
//		
//		for(Integer num : nums)
//			output += nums + " ";
//		
//		for(int i = 0; i < nums.length; i++)
//			output += nums.get(i) + " ";
//		
//		for(int i = 0; i <= nums.size() ; i++)
//			output += nums.get(i) + " ";
//
//		
//		
//		System.out.println(output);
//		
//		
//	}
//}



/*------------------------------------------------------------------------------------------------------------------------------------------
 			Ortak koddaki küçük hataları tespit etmek zor olabilir
 	Önceki alıştırmadaki for döngülerinin hepsi oldukça benzer görünüyor ve ilk bakışta hepsi Listedeki tüm değerleri sırayla yazdıracakmış gibi görünüyor.
 	
 	Derleyici hatalarını tespit etmek en kolay olabilir, çünkü IDE'niz veya derleyiciniz size kodun yanlış olduğunu söyleyecektir 
 		ve Özel Durumlar (Bölüm 13, Riskli Davranış'ta göreceğimiz) de koddaki bir soruna işaret edebilir. 		
 	
 	Ancak, yalnızca koda bakarak yanlış çıktı üreten kodu tespit etmek daha zor olabilir.
 	
 	forEach gibi bir yöntem kullanmak, for döngüsü gibi tekrarlayan ve ortak kod olan \"boilerplate\" ile ilgilenir.
 	forEach kullanmak, yalnızca yapmak istediğimiz şeyi iletmek, kodumuzdaki yanlışlıkla yapılan hataları azaltabilir.
 	
 	
 	API'den \"her öğe için\" bir şeyler yapmasını isteyebilirsek, API'nin bizim için yapmasını sağlayabileceğimiz başka ortak görevler var gibi görünüyor.
 	
 	Evet kesinlikle, aslında Java 8 sadece bunun için bütün bir API tanıttı.
 	
 	Java 8, önceki bölümde baktığımız Collections sınıfları da dahil olmak üzere birçok sınıfta kullanılabilecek yeni bir yöntem kümesi olan Streams API'sini tanıttı.
 	
 	Streams API'si yalnızca bir dizi yararlı yöntem değil, aynı zamanda biraz farklı bir çalışma şeklidir. 
 	
 	Verilerimiz hakkında bilmek istediklerimizin bir dizi gereksinimini, isterseniz bir tarifini oluşturmamızı sağlar.
--------------------------------------------------------------------------------------------------------------------------------------------*/




/*---------------------------------------------------------------------------------------------------------------------------------------------
 			Ortak operasyonların yapı taşları
 	Koleksiyonlarımızda arama yapma yöntemlerimiz ve bu koleksiyonlardan çıktısını almak istediğimiz bilgi türleri, farklı Nesne türleri içeren farklı koleksiyon türlerinde bile oldukça benzer olabilir.
 
-----------------------------------------------------------------------------------------------------------------------------------------------*/



/*---------------------------------------------------------------------------------------------------------------------------------------------
		INTRODUCING THE STREAMS API
		
	Streams API'si, bir koleksiyon üzerinde gerçekleştirebileceğimiz bir dizi işlemdir, bu nedenle bu işlemleri kodumuzda okuduğumuzda, Collection verileriyle ne yapmaya çalıştığımızı anlayabiliriz. 
	
	java.util.stream.Stream
	
	Stream<T>distinct() : Farklı öğelerden oluşan bir stream döndürür.
	
	Stream<T>filter(Predicate<? super T> predicate) : Verilen yüklemle eşleşen öğelerin streamini döndürür.
	
	Stream<T>limit(long maxsize): MaxSize'den daha uzun olmayacak şekilde kesilmiş bir öğe akışı döndürür. 
	
	<R> Stream<R> map(Function<? super T,? extends R> mapper):Verilen işlevi bu akışın öğelerine uygulama sonuçlarını içeren bir akış döndürür.
	
	Stream<T>skip(long n): Akışın ilk n öğesini attıktan sonra bu akışın kalan öğelerinin akışını döndürür.
	
	Stream<T> sorted(): Bu akışın öğelerinin doğal düzene göre sıralanmış bir akışını döndürür.
	
	<R> : genellikle yöntemin Sonucunun türüdür.
	
------------------------------------------------------------------------------------------------------------------------------------------------*/








/*--------------------------------------------------------------------------------------------------------
 			Getting started with Streams
 	
 	Streams API'sinin ne olduğu, ne işe yaradığı ve nasıl kullanılacağı hakkında ayrıntılara girmeden önce, denemeye başlamak için size bazı çok temel araçlar vereceğiz.
 	
 	Streams yöntemlerini kullanmak için bir Stream nesnesine ihtiyacımız var (açıkçası).
 	List gibi bir koleksiyonumuz varsa bu, Stream'i uygulamaz. 
 	Ancak, Collection arabiriminde, Collection için bir Stream nesnesi döndüren stream adlı bir yöntem vardır.		
 	
----------------------------------------------------------------------------------------------------------*/

//class Example4
//{
//	public static void main(String[] args) 
//	{
//		List<String> strings = List.of("I","am","a","list","of","Strings");
//		Stream<String> stream = strings.stream(); //bu Dizelerin bir streamini elde etmek için bu yöntemi çağırabiliriz.
		
		//Artık Streams API'sinin yöntemlerini çağırabiliriz. Örneğin, en fazla dört öğe istediğimizi söylemek için limiti kullanabiliriz.
		
//		Stream<String> limit = stream.limit(4);
		
		//limit yöntemi, başka bir değişkene atayacağımız başka bir Dize Akışı döndürür
		
		
		//limit() çağrısının sonucunu yazdırmaya çalışırsak ne olur?
//		System.out.println("limit = "+limit); //output java.util.stream.SliceOps$1@7a0ac6e3
		//Bu hiç de doğru görünmüyor! SliceOps nedir ve neden listedeki ilk dört öğeden oluşan bir koleksiyon yok? 
		
		/*--------------------------------------------------------------------------------
		 *Java'daki her şey gibi, örnekteki akış değişkenleri de Nesneler'dir.
		 *Ancak Stream, koleksiyondaki öğeleri içermez.
		 *Daha çok, işlemlerin 'Collection data' üzerinde gerçekleştirilmesi için talimatlar kümesi gibidir.
		 *
		 *Başka bir Akış döndüren akış yöntemlerine Intermediate Operations adı verilir.
		 *Bunlar yapılacak şeylerin talimatlarıdır, ancak aslında işlemi kendi başlarına yapmazlar.
		 
		 ---------------------------------------------------------------------------------*/
		
		
//	}
//}


/*----------------------------------------------------------------------------------------------------
 				Streamler yemek tarifleri gibidir: Birisi onları gerçekten pişirene kadar hiçbir şey olmaz.
 		
 		Bir kitaptaki bir tarif sadece birisine bir şeyi nasıl pişireceğini söyler.
 		Tarifi açmak size otomatik olarak taze pişmiş çikolatalı kek sunmaz.
 		
 		Malzemeleri tarife göre toplamanız ve istediğiniz sonucu elde etmek için talimatları tam olarak izlemeniz gerekir.
 		Koleksiyonlar içerik değildir ve dört girişle sınırlı bir liste çikolatalı kek değildir (ne yazık ki).
 		Ancak, istediğiniz sonucu elde etmek için Stream'in \"yap\" yöntemlerinden birini çağırmanız gerekir. 
 		Bu \"yap\" yöntemlerine Terminal İşlemleri denir ve bunlar aslında size bir şeyler döndürecek yöntemlerdir.
 		
 		java.util.stream.Stream
 		
 		boolean anyMatch(Predicate<? super T> predicate):Herhangi bir öğe sağlanan yüklemle eşleşirse true değerini döndürür.
 		long count():Bu akıştaki öğelerin sayısını döndürür.
 		<R,A> R collect(Collector<? super T,A,R> collector): Bir Toplayıcı kullanarak bu akışın öğeleri üzerinde değiştirilebilir bir indirgeme işlemi gerçekleştirir.
 		Optional<T> findFirst():Bu akışın ilk öğesini açıklayan bir Optional veya akış boşsa boş bir Optional döndürür.
 			
------------------------------------------------------------------------------------------------------*/


/*-------------------------------------------------------------------------
		Getting a result from a Stream
	Evet, size birçok yeni kelime attık: akışlar; ara operasyonlar; terminal operasyonları... Ve hala size akışların neler yapabileceğini söylemedik!
	Akışlarla neler yapabileceğimiz hakkında bir fikir edinmeye başlamak için, Akışlar API'sinin basit bir kullanımı için kod göstereceğiz.
	Bundan sonra, geri adım atacağız ve burada gördüklerimiz hakkında daha fazla bilgi edineceğiz.
---------------------------------------------------------------------------*/

//class Example5
//{
//	public static void main(String[] args) 
//	{
//		List<String> strings = List.of("I","am","a","list","of","Strings");
//		Stream<String> stream = strings.stream();
//		Stream<String> limit = stream.limit(4);
//		long result = limit.count();
//		
//		System.out.println("result = "+ result);
//	
	/*
	 Bu işe yarıyor, ancak çok kullanışlı değil. Akışlarla ilgili en yaygın şeylerden biri, 
	 sonuçları başka bir koleksiyon türüne koymaktır.
	 */
	
//		List<String> result = limit.collect(Collectors.toList());
		
		//Akış Strings içerdiğinden, çıktı nesnesi de Strings içerecektir.
		//toList Collector, sonuçları Liste olarak çıkarır.
		//collect:Çıktıyı bir tür Nesne halinde toplayacak terminal işlemi.
		//Collectors: Ortak Collector uygulamalarını döndürmek için yöntemler içeren yararlı bir sınıf.
		//toList:Bu yöntem, akışın sonuçlarını bir Listeye çıkaracak bir Toplayıcı döndürür.
	
//	}
//	
//}

/*-------------------------------------------------------------------------------
 Son olarak, bekleyeceğimiz bir şeye benzeyen bir sonucumuz var: Bir Dize Listemiz vardı 
 ve bu listeyi ilk dört öğeyle sınırlamamızı ve ardından bu dört öğeyi yeni bir Listede toplamamızı istedik.
 
---------------------------------------------------------------------------------*/



/*------------------------------------------------------------------------------
 Sadece listedeki ilk dört öğenin çıktısını almak için çok fazla kod yazdık.
 Ayrıca birçok yeni terminoloji sunduk: Streams, Intermediate Operations ve terminal operation.
 Tüm bunları bir araya getirelim: Üç farklı yapı taşı türünden bir akış işlem hattı oluşturursunuz.
 
 
 1-Stream i bir kaynak koleksiyonundan alın ".stream()"
 
 2-Stream' de sıfır veya daha fazla intermediate operation çağırın. ".limit()"
 
 3-Sonuçları bir terminal işlemiyle çıkarın. ".collect()".
 
 Streams API'sini kullanmak için bulmacanın en azından ilk ve son parçalarına ihtiyacınız vardır.
 
 Ancak, her adımı kendi değişkenine atamanız gerekmez (son sayfada yaptığımız gibi). 
 Aslında, işlemler zincirlenecek şekilde tasarlanmıştır, böylece her aşamayı kendi değişkenine koymadan bir aşamayı bir öncekinden hemen sonra çağırabilirsiniz.
 
 Son sayfada, akışın tüm yapı taşları vurgulandı (stream, limit, count, collect).
 Bu yapı taşlarını alıp limit-and-collect işlemini şu şekilde yeniden yazabiliriz:
 
 -------------------------------------------------------------------------------*/

class Example6
{
	public static void main(String[] args) 
	{
		List<String> strings = List.of("I","am","a","list","of","Strings");
		
		List<String> result = strings.stream().limit(4).collect(Collectors.toList());
	}
}

/*---------------------------------------------------------------------------------------------------
 		Building blocks can be stacked and combined
 	Her intermediate operasyon bir Stream üzerinde hareket eder ve bir Stream döndürür.
 	Bu, sonuçların çıktısını almak için bir terminal işlemi çağırmadan önce bu işlemlerin çoğunu istediğiniz kadar 
 		bir araya getirebileceğiniz anlamına gelir.
 	
 	Kaynak, ara işlemler ve terminal işleminin tümü bir Akış İşlem Hattı oluşturmak üzere birleşir. 
 		Bu işlem hattı, özgün koleksiyondaki bir sorguyu temsil eder.
 	
 	Streams API'sinin gerçekten kullanışlı hale geldiği yer burasıdır. 
 	Önceki örnekte, orijinal Listenin daha kısa bir sürümünü oluşturmak için üç yapı taşına (akış, sınır, toplama) ihtiyacımız vardı, 
 	bu da basit bir işlem için çok fazla iş gibi görünebilir.
 	
 	Ancak daha karmaşık bir şey yapmak için birden çok işlemi tek bir akış işlem hattında bir araya getirebiliriz.
 	Örneğin, limiti uygulamadan önce akıştaki öğeleri sıralayabiliriz:
 -----------------------------------------------------------------------------------------------------*/

//class Example7
//{
//	public static void main(String[] args) 
//	{
//		List<String> strings = List.of("I","am","a","list","of","Strings");
//		List<String> result = strings.stream().sorted().limit(4).collect(Collectors.toList());
//		
//		System.out.println("Result = " + result);
//		
//	}
//}



/*--------------------------------------------------------------------------------------
 			Customizing the building blocks
 	
 	Koleksiyonumuzda daha gelişmiş bir sorgu oluşturmak için işlemleri bir araya getirebiliriz.
 	Ayrıca blokların ne yaptığını da özelleştirebiliriz. Örneğin, 
 		iade edilecek maksimum öğe sayısını (dört) geçirerek limit yöntemini özelleştirdik.
	
	Dizelerimizi sıralamak için doğal sıralamayı kullanmak istemeseydik, onları sıralamanın belirli bir yolunu tanımlayabilirdik. 
	Sıralama yöntemi için sıralama kriterlerini ayarlamak mümkündür.
	
----------------------------------------------------------------------------------------*/

//class Example8
//{
//	List<String> strings = List.of("I","am","a","list","of","Strings");
//	List<String> result = strings.stream()
//						  .sorted((s1,s2) -> s1.compareTo(s2))
//						  .limit(4)
//						  .collect(Collectors.toList());

	/*
	 Sıralanmış yönteme akıştaki dizelerin nasıl sıralanacağını söyleyen lambda ifadesi. 
	 Bu lambda ifadesi, önceki bölümde bahsettiğimiz bir Karşılaştırıcıyı temsil eder. 
	 Bu bölümde lambdaları daha sonra özetleyeceğiz.
	 */
//}




/*----------------------------------------------------------------------------
 	Bu daha uzun akış işlem hatlarıyla, sanırım bilgisayarın bitmesini ve her işlemi ayrı ayrı gerçekleştirmesini ve ardından 
 		bir sonrakini gerçekleştirmek için geri dönmesini istemezsiniz, değil mi?
 		
 	Yani bir terminal operasyonuna ihtiyaç duymamızın nedeni, kütüphanenin YALNIZCA bekleme hattındaki TÜM işlemleri bildiğinde \"yapması\" mıydı?
 			Yes, because Streams are lazy
 	
 	Bu onların yavaş veya işe yaramaz oldukları anlamına gelmez! Bu, her ara işlemin sadece ne yapılacağına dair talimat olduğu anlamına gelir; talimatın kendisini yerine getirmez. Ara operasyonlar tembel bir şekilde değerlendirilir.
 	Terminal işlemi, tüm talimat listesine, bekleme hattındaki tüm ara işlemlere bakmaktan ve ardından tüm seti tek seferde birlikte çalıştırmaktan sorumludur.
 		Terminal operasyonları isteklidir; çağrıldıkları anda çalıştırılırlar.
 	
 	Her bir ara işlem için orijinal koleksiyonu yinelemek zorunda kalmak yerine, verileri yalnızca bir kez gözden geçirirken tüm işlemleri yapmak mümkün olabilir.
-------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------------
 			Terminal operations do all the work
 	Ara işlemler tembel olduğundan, her şeyi yapmak terminal operasyonuna bağlıdır.
 	
 	1-Tüm ara işlemleri mümkün olduğunca verimli bir şekilde gerçekleştirin. İdeal olarak, orijinal verileri bir kez gözden geçirmek.
 	2-Terminal işleminin kendisi tarafından tanımlanan işlemin sonucunu hesaplayın. Örneğin, bu bir değer listesi, tek bir değer veya bir boolean (doğru\/yanlış) olabilir.
 	3-Ve sonucu dönün 
 	
 			Collecting to a List
 	
 	Artık bir terminal operasyonunda neler olup bittiği hakkında daha fazla bilgi sahibi olduğumuza göre, sonuçların bir listesini döndüren \"sihirli büyü\" ye daha yakından bakalım.
--------------------------------------------------------------------------------------------*/ 
 
//class Example9
//{
//	public static void main(String[] args) 
//	{
//		List<String> strings = List.of("I","am","a","list","of","Strings");
//		List<String> result = strings.stream()
//							  .sorted()
//							  .skip(2)
//							  .limit(4)
//							  .collect(Collectors.toList());
		/*
		 Terminal Operation(collect):
		 -1. tüm ara işlemleri gerçekleştirir, bu durumda: sorted; skip; limit
		 -2. Sonuçları, kendisine iletilen talimatlara göre toplar
		 -3. ve bu sonuçları döner
		 */
		
		/*
		 Collectors, Collector'ın farklı uygulamalarını sağlayan statik yöntemlere sahip bir sınıftır. 
		 Sonuçları toplamanın en yaygın yollarını bulmak için Collectors sınıfına bakın.
		
		 Collect yöntemi, sonuçların nasıl bir araya getirileceğine dair bir tarif olan bir Collector alır. 
		 Bu durumda, sonuçları bir Listeye yerleştiren önceden tanımlanmış yararlı bir Toplayıcı kullanılır. 
		
		*/
		
//	}
//}
 
 
/*-----------------------------------------------------------------------------------------------------------
 			Guidelines for working with streams.
 	
 	Herhangi bir bulmaca veya oyun gibi, Stream yapı taşlarının düzgün çalışmasını sağlamak için kurallar vardır.
 	
 	1-Bir akış işlem hattı oluşturmak için en azından ilk ve son parçalara ihtiyacınız vardır.
 		stream() parçası olmadan hiç Akış elde edemezsiniz ve terminal işlemi olmadan herhangi bir sonuç elde edemezsiniz.
 	
 	2-Akışları yeniden kullanamazsınız. 
 		Bir sorguyu temsil eden bir Akışı depolamak ve sorgunun kendisi yararlı olduğu için veya üzerinde derleme yapmak ve 
 		eklemek istediğiniz için birden çok yerde yeniden kullanmak yararlı görünebilir.
 		Ancak bir akışta terminal işlemi çağrıldıktan sonra, bu akışın herhangi bir bölümünü yeniden kullanamazsınız; yeni bir tane oluşturmanız gerekiyor. 
 		
 		Bir işlem hattı yürütüldükten sonra, bu akış kapatılır ve bir kısmını başka bir yerde yeniden kullanmak üzere bir değişkende saklamış olsanız bile başka bir işlem hattında kullanılamaz. 
 		Bir akışı herhangi bir şekilde yeniden kullanmaya çalışırsanız bir İstisna alırsınız.
--------------------------------------------------------------------------------------------------------------*/

class example10
{
	public static void main(String[] args) 
	{
		List<String> strings = List.of("I","am","a","list","of","Strings");
		
		Stream<String> limit = strings.stream()
							   .limit(4);
		
		List<String> result = limit.collect(Collectors.toList());
		List<String> result2 = limit.collect(Collectors.toList());
		
		
	}
}

/*
 3-Stream çalışırken temel alınan koleksiyonu değiştiremezsiniz.
	Bunu yaparsanız garip sonuçlar veya istisnalar görürsünüz. 
	Bir düşünün – birisi size bir alışveriş listesinde ne olduğu hakkında bir soru sorsaydı ve 
	aynı anda başka biri o alışveriş listesine karalama yapıyorsa, kafa karıştırıcı cevaplar da verirdiniz.
	
Dolayısıyla, temel alınan koleksiyonu sorgularken değiştirmemeniz gerekiyorsa, akış işlemleri de koleksiyonu değiştirmez, değil mi?

*/


/*----------------------------------------------------------------------------------
 		Correct! Stream operations don’t change the original collection.
 		
 	Streams API'si bir koleksiyonu sorgulamanın bir yoludur, ancak koleksiyonun kendisinde değişiklik yapmaz. 
 	Bu koleksiyona bakmak ve koleksiyonun içeriğine göre sonuçlar döndürmek için Streams API'sini kullanabilirsiniz, ancak orijinal koleksiyonunuz olduğu gibi kalır.
 	
 	Bu aslında çok yardımcı oluyor. Bu, koleksiyonları sorgulayabileceğiniz ve 
 	sonuçları programınızın herhangi bir yerinden çıkarabileceğiniz ve özgün koleksiyonunuzdaki 
 	verilerin güvende olduğunu bildiğiniz anlamına gelir;
 	
 	
------------------------------------------------------------------------------------*/



/*------------------------------------------------------------------------------
 							Dumb Questions
 	?Bir akış işlem hattına koyabileceğim ara işlem sayısı için bir sınır var mı?
 	Hayır, bu işlemleri istediğiniz kadar zincirlemeye devam edebilirsiniz.
 
 	
 	?Ara işlemler olmadan bir akış işlem hattına sahip olmanın bir anlamı var mı?
 	Kullanılıyor
 	
 	?Akış işlemi devam ederken kaynak koleksiyonunu değiştirmemenizi söylediniz. Kodum bir akış işlemi yapıyorsa, koleksiyonu kodumdan nasıl değiştirebilirim?
 	Harika bir soru! Aynı anda farklı kod bitlerini çalıştıran programlar yazmak mümkündür.
 	 Bunu eşzamanlılığı kapsayan Bölüm 17 ve 18'de öğreneceğiz. Güvende olmak için, değiştirilmeleri gerekmediğini biliyorsanız değiştirilemeyecek koleksiyonlar oluşturmak genellikle en iyisidir (yalnızca Akışlar için değil, genel olarak).
 	
 	?Terminal Operations işleminden değiştirilemeyen bir Listenin çıktısını nasıl çıkarabilirim?
 		Java 10 veya üstünü kullanıyorsanız Collectors'ı kullanabilirsiniz. Collect'i çağırdığınızda Collectors.toList kullanmak yerine 'toUnmodifiableList'.
 		
--------------------------------------------------------------------------------*/


/*-------------------------------------------------------------------------------------
 			BULLET POINTS
 	
 	JVM'ye tam olarak ne yapacağını ve nasıl yapılacağını söyleyen ayrıntılı kod yazmanız gerekmez. Koleksiyonları sorgulamak ve sonuçların çıktısını almak için Akışlar API'si de dahil olmak üzere kütüphane yöntemlerini kullanabilirsiniz.
 	
 	for döngüsü oluşturmak yerine koleksiyonda forEach kullanın. Yönteme, koleksiyonun her öğesinde gerçekleştirmek üzere işlemin lambda ifadesini geçirin.
 	
 	streammethod'u çağırarak bir koleksiyondan (kaynak) akış oluşturun.
 	
 	Akışta bir veya daha fazla Intermediate Operations çağırarak koleksiyonda çalıştırmak istediğiniz sorguyu yapılandırın.

	Bir terminal işlemini çağırana kadar herhangi bir sonuç almazsınız. Sorgunuzun çıktısını almak istediğiniz şeye bağlı olarak bir dizi farklı terminal işlemi vardır.
	
	Sonuçları yeni bir Listeye çıkarmak için, terminal işlemi olarak collect(Collectors.toList) öğesini kullanın.
	
	Kaynak koleksiyonunun, ara işlemlerin ve terminal işlemlerinin birleşimi bir akış işlem hattıdır.
	
	Akış işlemleri özgün koleksiyonu değiştirmez; bunlar koleksiyonu sorgulamanın ve sorgunun sonucu olan farklı bir Nesne döndürmenin bir yoludur.
	
--------------------------------------------------------------------------------------*/


