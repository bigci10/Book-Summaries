package lambda;


import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

class Lambda {}


//Lambda ifadeleri daha iyi anlayabilmemiz için streamsleri işlerken ki for each yöntemininden örnek vericez 
//bir for each yöntemi nasıl oluyor
//class example
//{
//	void forEach(???)
//	{
//		for (Element element : list)
//		{
//			//bu blok kodu her gelen elemana uygulanacak işlemi gösteriyor
//		}
//	}
//}

//\"?????\" olduğu yere ne koyardınız? Bir şekilde o güzel, boş kareye girecek olan kod bloğu olması gerekir.
//O zaman yöntemi çağıran birinin şunları söyleyebilmesini istersiniz:
	

/*forEach(do this: System.out.println( item ) );
	Bu kodu buraya yazamazsınız, çünkü hemen çalıştırılacaktır. Bunun yerine, bu kod bloğunu forEach yöntemine teslim etmenin bir yoluna ihtiyacımız var, 
	böylece yöntem hazır olduğunda onu çağırabilir.

	(item): Bu kodun bir şekilde yazdırmak için öğeyi ele geçirmesi gerekir, ancak bu kod forEach yönteminin İÇİNDE olduğunda bir öğeyi nasıl elde edebilir?


  Şimdi, bu kodun hemen çalıştırılmayacağını, bunun yerine yönteme geçirilmesi gerektiğini göstermek için bunu yap işlemini bir tür sembolle değiştirmemiz gerekiyor. 
  Kullanabiliriz, oh, hadi bakalım... Bu sembol olarak \"->\".	


  O zaman \"bakın, bu kodun başka yerlerden değerler üzerinde çalışması gerekecek\" demenin bir yoluna ihtiyacımız var. Kodun ihtiyaç duyduğu şeyleri \"bunu yap\" sembolünün sol tarafına koyabiliriz....

  forEach(item -> system.out.println(item));
  

*/





/*
 Tamam, şimdi bir yöntem argümanı olarak geçtiğim lambda'nın bir şekilde bu yöntemin gövdesinde kullanıldığını anlıyorum. Ama lambda nedir? 
 Yöntem, az önce geçirdiğim bu kod yığınını nasıl KULLANABİLİR?


 Lambda ifadeleri nesnelerdir ve bunları Tek Soyut Yöntemlerini çağırarak çalıştırırsınız
 Unutmayın, Java'daki her şey bir Nesnedir (ilkel türler hariç) ve lambdalar istisna değildir.
 
 Bir lambda ifadesi bir Funtional Interface uygular.
 Bu, lambda ifadesine yapılan başvurunun İşlevsel Arabirim olacağı anlamına gelir.
 Bu nedenle, yönteminizin bir lambda ifadesini kabul etmesini istiyorsanız, türü işlevsel bir arabirim olan bir parametreye sahip olmanız gerekir.
 Bu işlevsel arayüzün lambda'nız için doğru \"şekil\" olması gerekir.
 
 
 for each örneğimize geri dönelim; parametremizin bir İşlevsel Arayüz uygulaması gerekir.
 Ayrıca bu lambda ifadesini bir şekilde çağırmamız gerekiyor, liste öğesinden geçiyor.
 
 İşlevsel Arayüzlerin Tek Soyut Yöntemi (SAM) olduğunu unutmayın. Adı ne olursa olsun, 
  lambda kodunu çalıştırmak istediğimizde çağrılan bu yöntemdir.
 
  
 void forEach(SomeFunctionalInterface lambda)
 {
 	for(Element element : list) 
 	{
 		lambda.singleAbstractMethodName(element);
 	}
 }
 
  Lambdalar sihir değildir; onlar da diğer her şey gibi sınıflardır.
  
 
 
*/




/*
  		THE SHAPE OF LAMBDA EXPRESSIONS 
  		
*/

//class Example
//{
//	public static void main(String[] args) 
//	{
//		(s1,s2) -> s1.compareToIgnoreCase(s2);
//		
//		/*(compareToIgnoreCase) bir int sonucuna sahiptir ve bu, Comparator interface de ki karşılaştırma yönteminin dönüş türüyle eşleşir.
//		
//		
//		return anahtar kelimesinin lambda ifadesinde nerede olduğunu merak ediyor olabilirsiniz.
//		
//		Kısa versiyon: buna ihtiyacınız yok.
//		Uzun versiyon:lambda ifadesi tek bir satırsa ve işlevsel arabirimin yöntem imzası döndürülen bir değer gerektiriyorsa, derleyici yalnızca bir kod satırınızın döndürülecek değeri oluşturacağını varsayar.
//		*/
//	}
//}



//interface Comparator<T>
//{
//	int compare(T o1,T o2);
//}


/*
 			ANATOMY OF A LAMBDA EXPRESSION
 			
 	Comparator'u uygulayan lambda ifadesinin bu genişletilmiş sürümüne daha yakından <String>bakarsanız, 
 	standart bir Java yönteminden çok farklı olmadığını göreceksiniz. 
 	
 	(String s1, String s2) -> 
 	{		
 		return s1.compareToIgnoreCase(s2);
 	}
 	
 	1-lambda ifadesinin parametrelerinin sayısı ve türleri, uyguladığı İşlevsel Arabirim tarafından belirlenir.
 	2-(String s1, String s2) lambda ifadesinin parametre türleri gerekli değildir, ancak bunları açık olacak şekilde ekleyebilirsiniz. Bu, eşleşebilecek birden fazla işlevsel arabirim varsa gerekli olabilir.
 	3- ; Lambda gövdesi kıvırcık parantezlerin içindeyse, normal bir Java yöntemindeki çizgiler gibi, tüm satırların sonuna noktalı virgül koymalısınız.
 	4-{} Lambda ifadeleri kıvırcık parantezlerle sarılabilir. Bir satırdan daha uzun bir lambda ifadeniz varsa, onu kıvırcık parantezlerle sarmanız GEREKİR.
 	5-return Lambda bir değer döndüren bir yöntemi geçersiz kılarsa ve lambda gövdesi kıvırcık parantezlerin içindeyse, lambda gövdesinin sonuna bir dönüş ifadesi koymanız gerekir. Lambda ifadesi tek bir satırsa, derleyici neyin döndürülmesi gerektiğini çözebilir.
 
 */


/*
 			VARIETY IS THE SPICE OF LİFE
 	Lambda ifadeleri tüm şekil ve boyutlarda gelebilir ve yine de gördüğümüz aynı temel kurallara uygundur.
 	
 	A lambda might have more than one line
 		Çok satırlı lambda ifadeleri kıvırcık parantezlerin içinde olmalıdır. Daha sonra, diğer herhangi bir yöntem kodu gibi, her satır noktalı virgülle bitmelidir ve yöntemin bir şey döndürmesi gerekiyorsa, lambda gövdesi normal bir yöntem gibi \"return\" kelimesini içermelidir.
 		(st1,str2) -> { 
 			int l1 = str.length();
 			int l2 = str2.length();
 			return l2-l1;
 		}
 		
 	Single-line lambdas don’t need ceremony 
 	Lambda ifadeniz tek bir satırsa, derleyicinin neler olup bittiğini tahmin etmesini çok daha kolay hale getirir. Bu nedenle, \"boilerplate\" sözdiziminin çoğunu dışarıda bırakabiliriz. Son örnekteki lambda ifadesini tek bir satıra küçültürsek, şöyle görünür:
 		(str1,str2) -> str2.length() - str1.length()
 	
 	Bu aynı İşlevsel Arabirimdir (Karşılaştırıcı) ve aynı işlemi gerçekleştirir. Çok hatlı lambdalar mı yoksa tek hatlı lambdalar mı kullanacağınız tamamen size kalmış. 
 	Muhtemelen lambda ifadesindeki mantığın ne kadar karmaşık olduğuna ve okumanın ne kadar kolay olduğunu düşündüğünüze bağlı olacaktır - bazen daha uzun kod daha açıklayıcı olabilir.
 	
 */



/*
 			A lambda might have zero, one, or many parameters
 	Lambda ifadesinin ihtiyaç duyduğu parametre sayısı, İşlevsel Arabirim yönteminin aldığı parametre sayısına bağlıdır.
 	Parametre türleri (örneğin, \"String\" adı) genellikle gerekli değildir, ancak kodu anlamayı kolaylaştırdığını düşünüyorsanız bunları ekleyebilirsiniz
 	Derleyici, lambda'nızın hangi İşlevsel Arabirimi uyguladığını otomatik olarak hesaplayamıyorsa türleri eklemeniz gerekebilir.
 	 
 	() -> System.out.println("Hello!")  -------- @FunctionalInterface       Bir lambda ifadesi herhangi bir parametre almıyorsa, \nbunu göstermek için boş parantezler kullanın.
 	 											 public interface Runnable {
 												 	void run();
 												 }
 	
 												 
 	str -> System.out.println(str)      -------- @FunctionalInterface        Türü olmayan tek bir parametreyse yuvarlak parantezlere gerek yoktur.
 												 public interface Consumer<T>
 												 {
 												 	void accept(T t);
 												 }
 	
 	(str1,str2) -> str1.compareToIgnoreCase(str2) ------ @FunctionalInterface
 														 public interface Comparator<T>
 														 {
 														 	int compare(T o1, T o2);
 														 }

*/



/*
 		How can I tell if a method takes a lambda?
 	Şimdiye kadar, lambda ifadelerinin işlevsel bir arabirimin, yani Tek Soyut Yöntemli bir Arabirimin uygulamaları olduğunu gördünüz. Bu, lambda ifadesinin türünün bu arabirim olduğu anlamına gelir.
 		
 	Devam edin ve bir lambda ifadesi oluşturun. Bunu şimdiye kadar yaptığımız gibi bir yönteme geçirmek yerine, bir değişkene atayın. Java'daki diğer Nesneler gibi ele alınabileceğini göreceksiniz, çünkü Java'daki her şey bir Nesnedir. Değişkenin türü \nİşlevsel Arayüz.
 		
	Comparator<String> comparator = (s1,s2) -> s1.compareToIgnoreCase(s2);
	Runnable runnable = () -> System.out.println("Hello!");
	Consumer<>String consumer = str -> System.out.println(str);
	
	
	Bu, bir yöntemin lambda ifadesi alıp almadığını görmemize nasıl yardımcı olur? Yöntemin parametre türü bir İşlevsel Arayüz olacaktır. Akışlar API'sinden bazı örneklere göz atın:
		Stream<T>filter(Predicate<? super T>predicate)
		boolean allMatch(Predicate<? super T>predicate)
						@FunctionalInterface
						public interface Predicate<T>
		
		<R>Stream<R> map(Function<? super T,? extends R> mapper)
						@FunctionalInterface
						public interface Function<T,R>
		
		void forEach(Consumer<? super T> action)
					@FunctionalInterface
					public interface Consumer<T>
		
*/



/*
 		Compile Game
 */
//class Game
//{
//	public static void main(String[] args) 
//	{
//		
//		Runnable r = () -> System.out.println("Hi!");
//		Consumer<String> c = s -> System.out.println(s);
//		Supplier<String> s = () -> System.out.println("Some string");
//		Consumer<String> c = (s1, s2) -> System.out.println(s1 + s2);
//		Runnable r = (String str) -> System.out.println(str);
//		Function<String, Integer> f = s -> s.length();
//		Supplier<String> s1 = () -> "Some string";
//		Consumer<String> c1 = s2 -> "String" + s;
//		Function<String, Integer> f2 = (int i) -> "i = " + i;
//		Supplier<String> s3 = s -> "Some string: " + s;
//		Function<String, Integer> f1 = (String s) -> s.length();	
//	}
//}



/*
 				Spotting Functional Interfaces
 	Şimdiye kadar, @FunctionalInterface bir ek açıklama ile işaretlenmiş İşlevsel Arayüzler gördük (Ek B'de ek açıklamaları ele alacağız), bu da bize bu arayüzün Tek Soyut Yönteme sahip olduğunu ve bir lambda ifadesiyle uygulanabileceğini uygun bir şekilde söylüyor.
 	Tüm işlevsel arabirimler, özellikle eski kodlarda bu şekilde etiketlenmez, bu nedenle işlevsel bir arabirimi kendiniz için nasıl belirleyeceğinizi anlamak yararlıdır.
 	Başlangıçta, arayüzlerde izin verilen tek yöntem türü, bu arayüzü uygulayan herhangi bir sınıf tarafından geçersiz kılınması gereken yöntemler olan soyut yöntemlerdi. Ancak Java 8'den itibaren, arayüzler default ve static yöntemler de içerebilir.
 	
 	Statik yöntemleri Bölüm 10, Sayılar Önemlidir'de gördünüz ve bunları daha sonra bu bölümde de göreceksiniz. Bunlar, bir örneğe ait olması gerekmeyen yöntemlerdir ve genellikle yardımcı yöntemler olarak kullanılır.
 	
 	Bir arayüzde, varsayılan bir yöntem soyut bir sınıftaki standart bir yöntem gibi çalışır - bir gövdeleri vardır ve alt sınıflar tarafından devralınırlar. 
 	
 	Hem varsayılan hem de statik yöntemler, tanımlanmış davranışa sahip bir yöntem gövdesine sahiptir. Arabirimlerde, varsayılan veya statik olarak tanımlanmayan herhangi bir yöntem, geçersiz kılınması gereken soyut bir yöntemdir.
 */




/*
 				Functional interfaces in the wild
 	Artık arayüzlerin soyut olmayan yöntemlere sahip olabileceğini bildiğimize göre, 
 	arayüzleri yalnızca bir soyut yöntemle tanımlamanın biraz daha fazla hilesi olduğunu görebiliriz. 
 	Eski dostumuz Comparator'a bir göz atın. Birçok yöntemi var! Ve yine de hala bir SAM tipidir; sadece bir Tek Soyut Yöntemi vardır. 
 	Bir lambda ifadesi olarak uygulayabileceğimiz bir İşlevsel Arayüzdür.	
 	
*/

//Örnek
//class Songs
//{
//	public List<Song> getSongs()
//	{
//		return List.of(
//				new Song("$10", "Hitchhiker", "Electronic", 2016, 183),
//				new Song("Havana", "Camila Cabello", "R&B", 2017, 324),
//				new Song("Cassidy", "Grateful Dead", "Rock", 1972, 123),
//				new Song("50 ways", "Paul Simon", "Soft Rock", 1975, 199),
//				new Song("Silence", "Delerium", "Electronic", 1999, 134),
//				new Song("Hurt", "Nine Inch Nails", "Industrial Rock", 1995, 257),
//				new Song("Watercolour", "Pendulum", "Electronic", 2010, 155),
//				new Song("The Outsider", "A Perfect Circle", "Alternative Rock", 2004, 312),
//				new Song("With a Little Help from My Friends", "The Beatles", "Rock", 1967, 168),
//				new Song("Come Together", "The Beatles", "Blues rock", 1968, 173),
//				new Song("Come Together", "Ike & Tina Turner", "Rock", 1970, 165),
//				new Song("With a Little Help from My Friends", "Joe Cocker", "Rock", 1968, 46),
//				new Song("Immigrant Song", "Karen O", "Industrial Rock", 2011, 12),
//				new Song("Breathe", "The Prodigy", "Electronic", 1996, 337),
//				new Song("What's Going On", "Gaye", "R&B", 1971, 420),
//				new Song("Hallucinate", "Dua Lipa", "Pop", 2020, 75),
//				new Song("Walk Me Home", "P!nk", "Pop", 2019, 459),
//				new Song("I am not a woman, I'm a god", "Halsey", "Alternative Rock", 2021, 384),
//				new Song("Pasos de cero", "Pablo Alborán", "Latin", 2014, 117),
//				new Song("Smooth", "Santana", "Latin", 1999, 244),
//				new Song("Immigrant song", "Led Zeppelin", "Rock", 1970, 484));
//				
//	}
//}
//
//class Song
//{
//	private final String title ;
//	private final String artist;
//	private final String genre;
//	private final int year;
//	private final int timesPlayed;
//	
//	Song(String title,String artist,String genre,int year,int timesPlayed)
//	{
//		this.title = title;
//		this.artist = artist;
//		this.genre = genre;
//		this.year = year;
//		this.timesPlayed = timesPlayed;
//	}
//}

//class Challenges
//{
	// #1: Find all the “rock” songs
	//Bu Akışlar bölümüdür, bu nedenle çözümün Akışlar API'sini içereceği açıktır. Unutmayın, bir çözüm oluşturmak için bir araya getirebileceğimiz üç tür parça vardır.
	/*
	 1- .stream()
	 2- intermediate Operations
	 3- terminal Operations
	 
	 
	 Streams API çağrısının nasıl oluşturulacağına dair ipuçları var: yalnızca belirli bir türe sahip Şarkılar için filtrelemek istiyor ve bunları yeni bir Listede toplamak istiyor.
	 Stream<T> filter(Predicate<? super T> predicate) : Verilen yüklemle eşleşen öğelerin akışını döndürür.
	 <R,A> R collect(Collector<? super T,A,R> collector) : Bir Toplayıcı kullanarak bu akışın öğeleri üzerinde değiştirilebilir bir indirgeme işlemi gerçekleştirir.
	 
	 
	 
	 
	 1.stream();
	 2.filter();
	 3.collect(toList)
	 
	 filter yöntemi bir predicate alır yani bi functionalInterface bu interface in metodu boolean test(T t) ; 
	 Lambda ifadelerinin şekilleri hakkında bildiklerimiz göz önüne alındığında, Predicate'i uygulayan bir lambda ifadesinin nasıl yazılacağını çözebilmeliyiz.
	 
	 Predicate predicate = ... -> ........ ; takes a single parameter ; and return true or false ;
	 
	 Lambda'ya giriş türü akıştaki türlere göre belirleneceğinden, Stream işlemine taktığımızda tek parametrenin türünün ne olduğunu bileceğiz.
	 
	 */
	// #1: Find all the “rock” songs
//	public static void main(String[] args) 
//	{
//		
//		List<Song> songs = new Songs().getSongs();
//		
//		List<Song> rockSongs = songs.stream().filter(song -> song.getGenre().equals("Rock")).collect(Collectors.toList());
//		
//	}
	
	
	//#2: List all the genres
	/*türlerin bir listesini istiyor, bu da akıştaki şarkı öğelerini bir şekilde tür (String) öğelerine dönüştürmemiz gerektiği anlamına geliyor. map bunun içindir. map işlemi, bir türden başka bir türe nasıl eşleneceğini belirtir.
	 1-stream()
	 2-map : Bir türün öğelerinin diğerine nasıl dönüştürüleceğinin ayrıntıları. Burada şarkıları türlere dönüştürmek isteyeceğiz.
	 Map işlemine, bir türden diğerine nasıl dönüştürüleceğinin ayrıntılarını içeren bir lambda ifadesi vermeniz gerekir.
	 3-.collect(toList)
	 
	 
	  map yöntemi bir İşlev alır.
	  Tanım gereği jenerikler biraz belirsizdir, bu da anlamayı biraz zorlaştırır, ancak İşlevler bir şey yapar: bir türden bir şey alırlar ve farklı türde bir şey döndürürler. 
	  mapping için tam olarak ne gerektiği bir türden diğerine değişir.
	 
	  map yöntemi = R apply (T t);
	 */
//	public static void main(String[] args) 
//	{
//		List<Song> songs = new Songs().getSongs();
//		List<String> genres = songs.stream().map(song -> song.getGenre()).collect(toList());
//		
//	}
	
//}


/*
 	Sometimes you don’t even need a lambda expression
 	
   Bazı lambda ifadeleri, parametrenin türü veya işlevsel arabirimin şekli göz önüne alındığında basit ve öngörülebilir bir şey yapar. Map işlemi için lambda ifadesine tekrar bakın.
   
   Function<Song, String> getGenre = song -> song.getGenre();
   
   Tüm bunları hecelemek yerine, bir yöntem başvurusu kullanarak derleyiciyi istediğimiz işlemi yapan bir yönteme yönlendirebilirsiniz.
   
   Function<Song, String> getGenre = Song::getGenre;
   Yöntem başvurusu—derleyicinin yöntemi çağırmasına neden olacak bir \".\" kullanmak yerine, derleyiciyi yöntemin yönüne yönlendirmek için bir \"::\" kullanın.
   
   Yöntem başvuruları lambda ifadelerinin yerini alabilir, ancak bunları kullanmanız gerekmez.
   
   Bazen yöntem başvuruları kodun daha kolay anlaşılmasını sağlar.
   
   Yöntem başvuruları, birkaç farklı durumda lambda ifadelerinin yerini alabilir. Genel olarak, kodun okunmasını kolaylaştırıyorsa bir yöntem başvurusu kullanabiliriz.
   
   
   Örneğin eski dostumuz Karşılaştırıcı'yı ele alalım. Karşılaştırıcı arabiriminde, bir yöntem başvurusuyla birleştirildiğinde, sıralama için hangi değerin kullanıldığını ve hangi yönde kullanıldığını görmenizi 
   sağlayan birçok yardımcı yöntem vardır. 
   Bunu yapmak yerine, şarkıları en eskisinden en yenisine doğru sıralamak için:
   List<Song> result = allSongs.stream()
 						.sorted((o1, o2) -> o1.getYear() - o2.getYear())
 						.collect(toList());
 
   Karşılaştırmanın ne olması gerektiğini belirtmek için Karşılaştırıcı'dan statik yardımcı yöntemle birleştirilmiş bir yöntem başvurusu kullanın:
   List<Song> result = allSongs.stream()
 						.sorted(Comparator.comparingInt(Song::getYear))
 						.collect(toList());
   
 	
*/


/*
 	Bazı işlemler bir şey döndürebilir veya hiçbir şey döndürmeyebilir.
 	Bir yöntemin bir değer döndürmesi veya döndürmemesi garip görünebilir, ancak gerçek hayatta her zaman olur.
 	
 	Bir dondurma standında olduğunuzu ve çilekli dondurma istediğinizi hayal edin.
 	IceCream iceCream = getIceCream("Strawberry");
 	
 	Kolay, değil mi? Peki ya çilekleri yoksa? Dondurma insanının size \"bu dondurmaya sahip değiliz\" demesi muhtemeldir.
 	Java dünyasında bunu yapmaya çalıştığınızı hayal edin. İlk örnekte, bir dondurma örneği alırsınız. İkincisinde, ... bir String mesajı? Ancak bir mesaj dondurma şeklindeki bir değişkene uymuyor. Boş mu? Ama null gerçekten ne anlama geliyor?
 	
 */


/*
  	Optional is a Wrapper
  	Java 8'den bu yana, bir yöntemin bazen bir sonuç döndürmeyebileceğini bildirmesinin normal yolu, İsteğe Bağlı bir yöntem döndürmektir. Bu, sonucu saran bir nesnedir, böylece \"Sonuç aldım mı? Yoksa boş mu?\" O zaman bir sonraki adımda ne yapacağınıza karar verebilirsiniz.
  	
  	Optional<IceCream> optional = getIceCream("Strawberry");
  	Strawberry ice cream, please! --- Here you go 
  	
  	if (optional.isPresent()) {
	Do you have something inside? ---- yes 
	
	
	IceCream ice = optional.get();}
	Great! Give it to me! Please. ---- All yours.
	

	Optional İsteğe bağlı olarak, dondurma almadığınız zamanları öğrenmenin ve bunlarla başa çıkmanın bir yolunu sunar.
	

	Optional<IceCream> optional = getIceCream("Strawberry");
	Strawberry ice cream, please! --- Here you go 

	if (optional.isPresent()) {
	Do you have something inside? ---- nope
	
	else {System.out.println("No ice cream for you!");}
	OK, never mind, thanks.

	Geçmişte, yöntemler bu durum için Özel Durumlar atmış veya \"null\" ya da özel bir \"Bulunamadı\" dondurma örneği türü döndürmüş olabilir. 
	Bir yöntemden Optional döndürmek, yöntemi çağıran herhangi bir şeyin önce bir sonuç olup olmadığını kontrol etmesi ve ardından bir sonuç yoksa ne yapılacağı konusunda kendi kararlarını vermesi gerektiğini açıkça ortaya koyar.
	
	
	Optional sarmalayıcı ile konuşmayı unutmayın
	İsteğe bağlı sonuçlarla ilgili önemli olan şey, boş olabilmeleridir. Önce bir değer olup olmadığını ve sonucun boş olup olmadığını kontrol etmezseniz, bir istisna alırsınız.
	
	
	Optional<IceCream> optional = getIceCream("Strawberry");
	Strawberry ice cream, please! --- Here you go 
	
	
	IceCream ice = optional.get();
	booom exception
	
	
*/

