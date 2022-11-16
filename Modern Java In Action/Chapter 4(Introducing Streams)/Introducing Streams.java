package stream;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModernJavaInAction {}

/*-----------------------------------------------------------------------------------------
 				Introducing streams
 				
	Java'daki koleksiyonlar olmadan ne yapardınız? Neredeyse her Java uygulaması koleksiyon oluşturur ve işler.
	Koleksiyonlar birçok programlama görevinin temelidir: verileri gruplandırmanıza ve işlemenize olanak tanır.
	Koleksiyonları çalışırken göstermek için, farklı sorguları hesaplamak üzere bir menüyü temsil edecek bir yemek koleksiyonu oluşturmakla görevlendirildiğinizi düşünün.
	Örneğin, menü için toplam kalori miktarını öğrenmek isteyebilirsiniz.
	Veya, özel bir sağlıklı menü için yalnızca düşük kalorili yemekler seçmek üzere menüyü filtrelemeniz gerekebilir.
	
	Ancak koleksiyonların hemen hemen her Java uygulaması için gerekli olmasına rağmen, koleksiyonları manipüle etmek mükemmel olmaktan uzaktır:
		Çoğu business logic, bir yemek listesini kategoriye göre gruplandırmak (örneğin, tüm vejetaryen yemekler) veya en pahalı yemeği bulmak gibi veritabanı benzeri işlemleri gerektirir.
		Iteratorları kullanarak bu işlemleri kaç kez yeniden uygularken buluyorsunuz? Çoğu veritabanı, bu tür işlemleri bildirimli olarak belirtmenize izin verir.
		Örneğin, aşağıdaki SQL sorgusu seçim yapmanıza (veya \"filtrelemenize\") olanak tanır
			"SELECT name FROM dishes WHERE calorie < 400".
		

	Gördüğünüz gibi, SQL'de bir yemeğin kalori özniteliğini kullanarak filtreleme işlemini uygulamanız gerekmez (Java koleksiyonlarında yaptığınız gibi, örneğin bir yineleyici ve bir akümülatör kullanarak).
	Bunun yerine, sonuç olarak istediğinizi yazarsınız.
	Bu temel fikir, bu tür sorguların açıkça nasıl uygulanacağı konusunda daha az endişelenmeniz anlamına gelir
	Neden koleksiyonlarla benzer bir şey yapamıyorsunuz? 
	
	Geniş bir öğe koleksiyonunu nasıl işlersiniz?
	Performans elde etmek için paralel olarak işlemeniz ve çok çekirdekli mimariler kullanmanız gerekir. 
	Ancak paralel kod yazmak, iteratorlarla çalışmaya kıyasla karmaşıktır.
	
	Java dil tasarımcıları değerli zamanınızdan tasarruf etmek ve programcılar olarak hayatınızı kolaylaştırmak için ne yapabilir? 
	Tahmin etmiş olabilirsiniz: cevap akışlardır.
	
--------------------------------------------------------------------------------------------*/




/*
 		What are streams?
 		
 	Akışlar, veri koleksiyonlarını bildirime dayalı bir şekilde değiştirmenize olanak tanıyan Java API'sine yönelik bir güncellemedir
 	(geçici bir uygulamayı kodlamak yerine bir sorgu ile ifade edersiniz).
 	
 	Şimdilik onları bir veri koleksiyonu üzerinde süslü yineleyiciler olarak düşünebilirsiniz.
 	Buna ek olarak, akışlar, çok iş parçacıklı herhangi bir kod yazmak zorunda kalmadan şeffaf bir şekilde paralel olarak işlenebilir!
 	
 	Şimdi yukarıda verdiğimiz yemek örneğini koda dökelim.
 	
 	
 */

//class Example1
//{
//	List<String> lowCaloricDishesNames = 
//			menu.stream()
//			//select dishes that are below 400 calories
//			.filter(d -> d.getCalories() < 400)
//			//sorts them by calories
//			.sorted(comparing(Dish :: getCalories))
//			//Extracts the names of these dishes
//			.map(Dish :: getName)
//			//stores all the names in a list
//			.collect(toList));
//			
//			
//	//Çok çekirdekli bir mimariden yararlanmak ve bu kodu paralel olarak yürütmek için stream() öğesini parallelStream() olarak değiştirmeniz yeterlidir:
//	List<String> lowCaloricDishesName = 
//			menu.parallelStream()
//			.filter(d -> d.getCalories() < 400)
//			.sorted(comparing(Dishes :: getCalories))
//			.map(Dish :: getName)
//			.collect(toList());
//}

/*
 		Paralel Stream yöntemini çağırdığınızda tam olarak ne olduğunu merak ediyor olabilirsiniz.
 		Kaç tane iş parçacığı kullanılıyor? Performans avantajları nelerdir?
 		
 		Bu yöntemi hiç kullanmalı mısınız? 
 		Bölüm 7 bu soruları ayrıntılı olarak ele almaktadır. 
 		Şimdilik, yeni yaklaşımın yazılım mühendisliği açısından birkaç acil fayda sağladığını görebilirsiniz:
 		
 			Kod bildirimsel bir şekilde yazılmıştır: Bir işlemin nasıl uygulanacağını belirtmek yerine (döngüler ve if koşulları gibi kontrol-akış bloklarını kullanarak) 
 			ne elde etmek istediğinizi belirtirsiniz (kalorisi düşük olan yemekleri filtreleyin).
 			
 			Önceki bölümde gördüğünüz gibi, bu yaklaşım, davranış parametrelendirmesi ile birlikte, değişen gereksinimlerle başa çıkmanızı sağlar:
 				kodu kopyalayıp yapıştırmak zorunda kalmadan, lambda ifadesi kullanarak yüksek kalorili yemekleri filtrelemek için kodunuzun ek bir sürümünü kolayca oluşturabilirsiniz.
 				
 				Bu yaklaşımın yararını düşünmenin başka bir yolu, iş parçacığı modelinin sorgunun kendisinden ayrılmasıdır.
				Bir sorgu için bir tarif sağladığınızdan, sorgu sıralı veya paralel olarak yürütülebilir. Bu konuda daha fazla bilgiyi 7. bölümde öğreneceksiniz.
				
		
		Karmaşık bir veri işleme işlem hattını ifade etmek için birkaç yapı taşı işlemini birbirine zincirlersiniz
		kodunuzu okunabilir ve amacını açık tutarken.
		Filtrenin sonucu sorted yönteme geçirilir ve bu yöntem daha sonra map yöntemine ve ardından collect yöntemine geçirilir.
		(Resim - 1)
		
		Filtre (veya sıralanmış, eşlenmiş ve toplamış) gibi işlemler belirli bir iş parçacığı modeline bağlı olmayan üst düzey yapı taşları olarak mevcut olduğundan, 
		bunların iç uygulaması tek iş parçacıklı olabilir veya çok çekirdekli mimarinizi şeffaf bir şekilde en üst düzeye çıkarabilir!
		
		Pratikte bu, belirli veri işleme görevlerini nasıl paralelleştireceğinizi bulmak için artık iş parçacıkları ve kilitler hakkında endişelenmenize gerek olmadığı anlamına gelir: Streams API bunu sizin için yapar!
		
		
*/


/*
 	-Declarative—More concise and readable
 	-Composable : Greater flexibility
 	-Parallelizable—Better performance
 	
 	Bu bölümün geri kalanında ve bir sonraki bölümde, örneklerimiz için aşağıdaki alanı kullanacağız: yemek listesinden başka bir şey olmayan bir menü
*/

class Dish
{
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	
	Dish(String name, boolean vegetarian,int calories, Type type)
	{
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isVegetarian()
	{
		return vegetarian;
	}
	
	public int getCalories()
	{
		return calories;
	}
	
	public Type getType()
	{
		return type;
	}
	
	enum Type {MEAT, FISH, OTHER}
}

class Test
{
	public static void main(String[] args) 
	{
		List<Dish> menu = List.of(new Dish("pork", false, 800, Dish.Type.MEAT),
								  new Dish("beef", false, 700, Dish.Type.MEAT),
								  new Dish("chicken", false, 400, Dish.Type.MEAT),
								  new Dish("french fries", true, 530, Dish.Type.OTHER),
								  new Dish("rice", true, 350, Dish.Type.OTHER),
								  new Dish("season fruit", true, 120, Dish.Type.OTHER),
								  new Dish("pizza", true, 550, Dish.Type.OTHER),
								  new Dish("prawns", false, 300, Dish.Type.FISH),
								  new Dish("salmon", false, 450, Dish.Type.FISH));
		
	}
}


/*
 		Şimdi Akışlar API'sini nasıl kullanabileceğinizi daha ayrıntılı olarak inceleyeceğiz. 
 		Akışları koleksiyonlarla karşılaştıracağız ve bazı arka planlar sağlayacağız.
		Bir sonraki bölümde, karmaşık veri işleme sorgularını ifade etmek için kullanılabilen akış işlemlerini ayrıntılı olarak inceleyeceğiz.

		filtering, slicing, finding, matching, mapping, and reducing gibi kalıplara bakacağız 
*/






/*
 			Getting started with streams	
 			
 	Akışlarla ilgili tartışmamıza koleksiyonlarla başlıyoruz, çünkü akışlarla çalışmaya başlamanın en basit yolu budur.
 	Java 8'deki koleksiyonlar, stream döndüren yeni bir stream yöntemini destekler (arabirim tanımı java.util.stream.Stream dosyasında kullanılabilir).
 	
 	İlk olarak, akış tam olarak nedir? Kısa bir tanım, \"veri işleme işlemlerini destekleyen bir kaynaktan gelen bir dizi öğe\" dir.
 	 “a sequence of elements from a sequence that supports data-processing operations.” 
 	Bu tanımı adım adım inceleyelim:
 	
 		Sequence of elements: Bir koleksiyon gibi, akış da belirli bir öğe türündeki sıralı değerler kümesine bir arabirim sağlar.
 		Koleksiyonlar veri yapıları olduğundan, çoğunlukla belirli zaman\/mekan karmaşıklıklarına sahip öğeleri depolamak ve bunlara erişmekle ilgilidir.
 		Ancak akışlar, daha önce gördüğünüz filtre, sorted ve map gibi hesaplamaları ifade etmekle ilgilidir.
 		
 		Koleksiyonlar verilerle ilgilidir;
 		akışlar hesaplamalarla ilgilidir.
 		
 		
 		
 		Source : Akışlar, koleksiyonlar, diziler veya I/O kaynakları gibi veri sağlayan bir kaynaktan tüketir.
 		Bir listeden gelen akışın öğeleri, listeyle aynı sıraya sahip olur.
 		
 		
 		Data-processin-operations: 
 		Akışlar, filter ,map, reduce, find, match, sort vb. gibi verileri işlemek için veritabanı benzeri işlemleri ve işlevsel programlama dillerinden ortak işlemleri destekler.
 		Akış işlemleri sıralı veya paralel olarak yürütülebilir.
 	
 	
 	Ek olarak, akış operasyonları iki önemli özelliğe sahiptir:
 		
 		Pipelining : 
 		Birçok akış işlemi, bir akışı kendileri döndürerek işlemlerin daha büyük bir işlem hattı oluşturmak üzere zincirlenmesine olanak tanır.
 		Bu, lazy ve kısa devre gibi bir sonraki bölümde açıklayacağımız belirli optimizasyonları mümkün kılar.
 		İşlemlerin işlem hattı, veri kaynağında veritabanı benzeri bir sorgu olarak görüntülenebilir.
 		
 		Internal iteration:
 			Bir yineleyici kullanılarak açıkça yinelenen koleksiyonların aksine, akış işlemleri yinelemeyi sizin için perde arkasında yapar.
 			
 */



class Test1
{
	public static void main(String[] args) 
	{
		List<Dish> menu = List.of(new Dish("pork", false, 800, Dish.Type.MEAT),
								  new Dish("beef", false, 700, Dish.Type.MEAT),
								  new Dish("chicken", false, 400, Dish.Type.MEAT),
								  new Dish("french fries", true, 530, Dish.Type.OTHER),
								  new Dish("rice", true, 350, Dish.Type.OTHER),
								  new Dish("season fruit", true, 120, Dish.Type.OTHER),
								  new Dish("pizza", true, 550, Dish.Type.OTHER),
								  new Dish("prawns", false, 300, Dish.Type.FISH),
								  new Dish("salmon", false, 450, Dish.Type.FISH));
		
		List<String> threeHighCaloricDishNames = 
				menu.stream()
				.filter(dish -> dish.getCalories() > 300)
				.map(Dish :: getName)
				.limit(3)
				.collect(Collectors.toList());
		
	}
}



/*
 		Bu örnekte, önce menüdeki akış yöntemini çağırarak yemek listesinden bir akış alırsınız.
 		Veri kaynağı, yemeklerin listesidir (menü) ve akışa öğe dizisi sağlar.
 		Ardından, akışa bir dizi veri işleme işlemi uygularsınız: filtreleme, map, limit ve collect.
 		
 		Collect dışındaki tüm bu işlemler başka bir akış döndürür, böylece kaynakta sorgu olarak görüntülenebilen bir işlem hattı oluşturmak üzere bağlanabilirler.
 		Son olarak, collect işlemi bir sonuç döndürmek için işlem hattını işlemeye başlar (burada bir akıştan başka bir şey, bir Liste döndürdüğü için farklıdır). 
 		Hiçbir sonuç üretilmez ve aslında collect çağrılana kadar menüden hiçbir öğe seçilmez.
 		Bunu, zincirdeki yöntem çağrıları, collect çağrılana kadar sıraya alınmış gibi düşünebilirsiniz.
 		
 		
 		Açıkladığımız kodun, menü öğelerinin listesini adım adım işleyecek olursanız yazacağınızdan ne kadar farklı olduğuna dikkat edin.
 		(Resim 2)
*/






/*
 			STREAMS vs COLLECTIONS 
 	Hem mevcut Java koleksiyon kavramı hem de yeni akış kavramı, öğe türünün sıralı(Sequential) bir değer kümesini temsil eden veri yapılarına arabirimler sağlar.
 	Sıralanmış derken, değerlere herhangi bir sırayla rastgele erişmek yerine genellikle sırayla adım attığımızı kastediyoruz.
 	
 	
 	Aralarındaki fark nedir?
 	
 	Görsel bir metaforla başlayacağız. DVD'de depolanan bir filmi düşünün.
 	Bu bir koleksiyondur (belki de bayt veya çerçevelerden hangisinin burada olduğu umurumuzda değil) çünkü tüm veri yapısını içerir.
 	
 	Şimdi aynı videoyu internet üzerinden yayınlanırken izlemeyi düşünün.
 	Bu artık bir akıştır (bayt veya frames).
 	
 	Akışlı video oynatıcının, kullanıcının izlediği yerden önce yalnızca birkaç kare indirmiş olması gerekir,
 	böylece akıştaki değerlerin çoğu hesaplanmadan önce yayının başlangıcından itibaren değerleri görüntülemeye başlayabilirsiniz (canlı bir futbol maçı yayınlamayı düşünün).
 	Özellikle, video oynatıcının tüm akışı bir koleksiyon olarak bellekte arabelleğe almak için bellekten yoksun olabileceğini 
 	ve videoyu göstermeye başlamadan önce son karenin görünmesini beklemek zorunda kalırsanız, başlatma süresinin korkunç olacağını unutmayın.
 	Video oynatıcı uygulama nedenleriyle akışın bir bölümünü koleksiyona arabelleğe almayı seçebilirsiniz, ancak bu kavramsal farktan farklıdır.
 	
 	En kaba terimlerle, koleksiyonlar ve akışlar arasındaki fark, şeylerin hesaplandığı zamanla ilgilidir.
 	
 	Koleksiyon, veri yapısının şu anda sahip olduğu tüm değerleri tutan bellek içi bir veri yapısıdır (koleksiyondaki her öğenin koleksiyona eklenebilmesi için önce hesaplanması gerekir). 
 	(Koleksiyona bir şeyler ekleyebilir ve koleksiyondan kaldırabilirsiniz, ancak zaman içinde her anda, koleksiyondaki her öğe bellekte depolanır; öğelerin koleksiyonun bir parçası olmadan önce hesaplanması gerekir.)
 	
 	Buna karşılık, akış, öğeleri isteğe bağlı olarak hesaplanan kavramsal olarak sabit bir veri yapısıdır (ondan öğe ekleyemez veya kaldıramazsınız). 
 	Bu, önemli programlama avantajlarına yol açar.
 	Buradaki fikir, bir kullanıcının bir akıştan yalnızca ihtiyaç duydukları değerleri çıkarması ve bu öğelerin yalnızca gerektiği gibi ve gerektiğinde üretilmesidir.
 	Bu, üretici-tüketici ilişkisinin bir biçimidir.
 	
 	
 	Başka bir görüş ise, bir akışın lazy bir şekilde oluşturulmuş bir koleksiyon gibi olduğudur: değerler, bir tüketici tarafından talep edildiğinde hesaplanır (yönetimde bu, talep odaklı, hatta tam zamanında üretimdir).
 	
 	
 	
 	Buna karşılık, bir koleksiyon hevesle inşa edilmiştir (tedarikçi odaklı: sınırlı bir ömre sahip bir Noel yeniliği gibi, satışa başlamadan önce deponuzu doldurun).
 	Bunu asal örneklere uyguladığınızı hayal edin. 
 	Tüm asal sayılardan oluşan bir koleksiyon oluşturmaya çalışmak, 
 	sonsuza dek yeni bir asal sayıyı hesaplayan bir program döngüsüyle sonuçlanır - koleksiyona ekler, ancak koleksiyonu yapmayı asla bitiremez, böylece tüketici onu asla göremez.
 	
 	Başka bir örnek bir tarayıcı internet aramasıdır. Google'da veya bir e-ticaret çevrimiçi mağazasında çok sayıda eşleşmesi olan bir kelime öbeği aradığınızı varsayalım.
 	Fotoğraflarıyla birlikte tüm sonuç koleksiyonunun indirilmesini beklemek yerine, öğeleri en iyi 10 veya 20 eşleşme olan bir akış ve sonraki 10 veya 20 için tıklamak için bir düğme elde edersiniz.
 	Tüketici olarak sonraki 10 için tıkladığınızda, tedarikçi bunları görüntülemek üzere tarayıcınıza iade etmeden önce talep üzerine hesaplar.
 	
 	

	Collection DVD gibi, koleksiyon da veri yapısının şu anda sahip olduğu tüm değerleri tutar; koleksiyondaki her öğenin koleksiyona eklenebilmesi için önce hesaplanması gerekir.
	
	Stream Akışlı bir video gibi(youtube da izlediğimiz), değerler de ihtiyaç duyuldukları şekilde hesaplanır.
	
	
*/




/*
 	 Sadece bir kez geçilebilir
 	 
 	Yineleyicilere benzer şekilde, bir akışın yalnızca bir kez geçilebileceğini unutmayın.
 	Bundan sonra bir akışın tüketildiği söylenir.
 	İlk veri kaynağından yeni bir akış elde ederek bir yineleyicide yaptığınız gibi tekrar geçiş yapabilirsiniz
 	
 	Örneğin:
 	List<String> title = Arrays.asList("Modern","Java","In","Action");
 	Stream<String> s = title.stream();
 	s.forEach(System.out::println); 
 	s.forEach(System.out::println); //hata çünkü stream sadece bir kez tüketilir.
 
	
	Felsefi olarak akışlar ve koleksiyonlar
	Felsefi bakış açılarını seven okuyucular için, bir akışı zaman içinde yayılmış bir değerler kümesi olarak görebilirsiniz.
	Buna karşılık, koleksiyon, tümü zamanda tek bir noktada var olan ve her biri için bir döngü içindeki üyelere erişmek için bir yineleyici kullanarak eriştiğiniz, 
	uzaya yayılmış bir değerler kümesidir.
	
	
	Koleksiyonlar ve akışlar arasındaki bir diğer önemli fark, veriler üzerindeki yinelemeyi nasıl yönettikleridir.
*/


/*
 		External vs. Internal iteration
 	
 	Koleksiyon arabiriminin kullanılması, yinelemenin kullanıcı tarafından yapılmasını gerektirir (örneğin, for-each kullanarak); buna dış yineleme denir.
 	
 	Stream kütüphanesi , aksine, iç yinelemeyi kullanır, yinelemeyi sizin için yapar ve elde edilen akış değerini bir yerde depolamaya özen gösterir; 
 	sadece ne yapılması gerektiğini söyleyen bir işlev sağlarsınız.
  	
  	Örneğin:(Collections: external iteration with a for-each loop)
  	List<String> names = new ArrayList<>();
  	for(Dish dish: menu) {  //Menü listesini sırayla açıkça yineler
  		names.add(dish.getName()); //Adı ayıklar ve bir listeye ekler.
	}
	
	
	 Collections: external iteration using an iterator behind the scenes
	 List<String> names = new ArrayList<>();
	 Iterator<String> iterator = menu.iterator(); 
	 while(iterator.hasNext()) { 
 			Dish dish = iterator.next();
 			names.add(dish.getName());
	 }
	 
	 
	 Streams: internal iteration
	 List<String> names = menu.stream()
	 					  .map(Dish :: getName)
	 					  .collect(toList());
	 					  
	 İç yinelemenin farklılıklarını ve faydalarını anlamak için bir analoji kullanalım. 
	 Diyelim ki iki yaşındaki kızınız Sofia ile konuşuyorsunuz ve oyuncaklarını bir kenara koymasını istiyorsunuz:
	 
	  Siz: \"Sofya, oyuncakları bir kenara bırakalım. Yerde bir oyuncak var mı?\"
	  Sofya: \"Evet, top.\"
	  Siz: \"Tamam, topu kutuya koyun. Başka bir şey var mı?\"
	  Sofya: \"Evet, işte benim bebeğim.\"
	  Siz: \"Tamam, bebeği kutuya koyun. Başka bir şey var mı?\"\
	  Sofia: “Yes, there’s my book.”
	  You: “Okay, put the book in the box. Is there something else?”
	  Sofia: “No, nothing else.”
	  You: “Fine, we’re finished.”
	  
	 Java koleksiyonlarınızla her gün yaptığınız şey tam olarak budur.
	 Bir koleksiyonu harici olarak yineler, öğeleri tek tek açıkça çıkarır ve işlersiniz.
	 Sofya'ya \"Yerdeki tüm oyuncakları kutunun içine koy\" diyebilseydiniz çok daha iyi olurdu.
	 
	 Internal iterationın tercih edilmesinin iki nedeni daha vardır: 
	 Birincisi, Sofia bebeği bir eliyle ve topu diğeriyle aynı anda almayı seçebilir ve ikincisi, önce kutuya en yakın nesneleri ve sonra diğerlerini almaya karar verebilir.
	 Aynı şekilde, bir iç yineleme kullanılarak, öğelerin işlenmesi şeffaf bir şekilde paralel olarak veya daha optimize edilebilecek farklı bir sırada yapılabilir.
	 
	 Bu optimizasyonlar, koleksiyonu Java'da yapmaya alışkın olduğunuz gibi harici olarak yinelerseniz zordur.
	 
	 Akışlar kitaplığındaki iç yineleme, donanımınıza uyacak şekilde otomatik olarak bir veri gösterimi ve paralellik uygulaması seçebilir.
	 Buna karşılık, 'for-each' yazarak dış yinelemeyi seçtikten sonra, herhangi bir paralelliği kendi kendine yönetmeyi taahhüt ettiniz. 
	 
	 Java 8'in Collection gibi bir arayüze ihtiyacı vardı, ancak yineleyiciler(iterators) olmadan, ergo Stream! Şekil 4.4, bir akış (iç yineleme) ile bir koleksiyon (dış yineleme) arasındaki farkı göstermektedir.
	 
	 Koleksiyonlar ve akışlar arasındaki kavramsal farklılıkları açıkladık. 
	 Özellikle, akışlar, sizin için yineleme yapmakla ilgilendiği iç yinelemeyi kullanır.
	 
	 Ancak bu, yalnızca yinelemeyi gizleyen önceden tanımlanmış işlemlerin (örneğin, filtre veya eşleme) bir listesine sahipseniz kullanışlıdır.
	
	 Java dil tasarımcıları, Streams API'sini karmaşık veri işleme sorgularını ifade etmek için kullanabileceğiniz kapsamlı bir işlem listesiyle birlikte gönderdi.
	 Şimdi bu operasyon listesine kısaca bakacağız ve bir sonraki bölümde örneklerle bunları daha ayrıntılı olarak inceleyeceğiz. 
	 
*/  	



/*
 			 Stream operations
 	
 	java.util.stream.Stream içindeki akışlar arabirimi birçok işlemi tanımlar. İki kategoriye ayrılabilirler. Önceki örneğimize bir kez daha bakalım:
 	
 	List<String> names = menu.stream()
 						.filter(dish -> dish.getCalories() > 300) //intermediate operations
 						.map(Dish :: getName) //intermediate operations
 						.limit(3) 			  //intermediate operations 
 						.collect(toList());   //converts the stream into a list
 						
 	
 	İki işlem grubu görebilirsiniz:
 		filtre, map ve limit , bir işlem hattı oluşturmak için birbirine bağlanabilir.
 		collect işlem hattının yürütülmesine neden olur ve kapatır.
 	
 		
 		Bağlanabilen akış işlemlerine ara işlemler, akışı kapatan işlemlere ise terminal işlemleri denir. 
 		Şekil 4.5 bu iki grubu vurgulamaktadır. Bu ayrım neden önemlidir?
 		
 		
*/


/*
 			 Intermediate operations
 			 
 	Filtre veya sıralama gibi ara işlemler, dönüş türü olarak başka bir akış döndürür.
 	Bu, işlemlerin bir sorgu oluşturmak üzere bağlanmasını sağlar.
 	Önemli olan, ara işlemlerin, lazy oldukları akış işlem hattında bir terminal işlemi çağrılana kadar herhangi bir işlem gerçekleştirmemesidir. 
	Bunun nedeni, ara işlemlerin genellikle terminal işlemi tarafından tek bir geçişte birleştirilebilmesi ve işlenebilmesidir.
	
	Akış işlem hattında neler olup bittiğini anlamak için kodu değiştirin, böylece her lambda işlediği geçerli çanağı da yazdırır.
	
	List<String> names = 
 						menu.stream()
 						.filter(dish -> {
 										System.out.println("filtering:" + dish.getName()); 
 										return dish.getCalories() > 300;
 										})
 						.map(dish -> {
 										System.out.println("mapping:" + dish.getName()); 
 										return dish.getName();
									 }
						.limit(3)
						.collect(toList());
						
						output
						filtering:pork
						mapping:pork
						filtering:beef
						mapping:beef
						filtering:chicken
						mapping:chicken
						[pork, beef, chicken]


	Bunu yaparak, Akışlar kitaplığının akışların tembel doğasından yararlanarak çeşitli iyileştirmeler gerçekleştirdiğini fark edebilirsiniz.
	İlk olarak, birçok yemeğin 300'den fazla kaloriye sahip olmasına rağmen, sadece ilk üçü seçilir!
	Bunun nedeni, bir sonraki bölümde açıklayacağımız gibi, limit işlemi ve kısa devre(short-circuiting) adı verilen bir tekniktir.
	
	İkincisi, filtre ve mapin iki ayrı işlem olmasına rağmen, aynı geçişte birleştirildiler (derleyici uzmanları bu tekniğe döngü füzyonu(loop fusion) diyor).	
					
						
*/



/*
 	Terminal operations

	erminal işlemleri bir akış işlem hattından sonuç üretir. 
	Sonuç, Liste, Tamsayı ve hatta void gibi akış dışı herhangi bir değerdir.
	 Örneğin, aşağıdaki işlem hattında forEach void döndüren ve kaynaktaki her yemeğe bir lambda uygulayan bir terminal işlemidir. 
	 System.out.println'i forEach öğesine iletmek, menüden oluşturulan akıştaki her Çanağı yazdırmasını ister:
	 
	 menu.stream().forEach(System.out::println);
	 
 
*/ 
