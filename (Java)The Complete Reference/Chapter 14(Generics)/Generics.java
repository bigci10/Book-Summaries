package generics;

import java.util.stream.IntStream;

public class CompleteReference {}


/*--------------------------------------------------------------------------------------------
	1995'teki orijinal 1.0 sürümünden bu yana Java'ya birçok yeni özellik eklendi.
	Derin ve uzun süreli bir etkiye sahip olanlardan biri jeneriklerdir.
	
	JDK 5 tarafından tanıtılan jenerikler, Java'yı iki önemli şekilde değiştirdi. 
	İlk olarak, dile yeni bir sözdizimsel unsur ekledi.
	
	İkincisi, core API'deki sınıfların ve yöntemlerin çoğunda değişikliklere neden oldu. 
	Bugün, jenerikler Java programlamanın ayrılmaz bir parçasıdır ve bu önemli özelliğin sağlam bir şekilde anlaşılması gerekmektedir.
	
	Jeneriklerin kullanımı sayesinde, çeşitli veri türleriyle tür açısından güvenli bir şekilde çalışacak sınıflar, arayüzler ve yöntemler oluşturmak mümkündür.
	Birçok algoritma, ne tür verilere uygulandıklarına bakılmaksızın mantıksal olarak aynıdır.
	
	Örneğin, bir yığını destekleyen mekanizma, yığının Tamsayı, Dize, Nesne veya İş Parçacığı türündeki öğeleri depolamasına bakılmaksızın aynıdır.
	
	Genel türlerle, belirli bir veri türünden bağımsız olarak bir algoritmayı bir kez tanımlayabilir 
	ve ardından bu algoritmayı herhangi bir ek çaba harcamadan çok çeşitli veri türlerine uygulayabilirsiniz.
	
	Dile eklenen ifade gücü jenerikleri, Java kodunun yazılma şeklini temelden değiştirdi.
--------------------------------------------------------------------------------------------*/
	









/*--------------------------------------------------------------------------------------------
			What are the generics
			
	Özünde, jenerik terimi parametreli türler anlamına gelir.
	Parametreli türler önemlidir, çünkü üzerinde çalıştıkları veri türünün parametre olarak belirtildiği sınıflar, arabirimler ve yöntemler oluşturmanıza olanak tanır.
	
	Genericleri kullanarak, örneğin farklı veri türleriyle otomatik olarak çalışan tek bir sınıf oluşturmak mümkündür.
	
	Parametreli bir türde çalışan bir sınıf, arabirim veya yöntem, generic sınıf veya generic yöntem olarak adlandırılır.
	
	Java'nın size her zaman Object türündeki referanslarla çalışarak genelleştirilmiş sınıflar, arabirimler ve yöntemler oluşturma yeteneği verdiğini anlamak önemlidir.
	Object diğer tüm sınıfların üst sınıfı olduğundan, Object başvurusu herhangi bir tür nesnesine başvurabilir.
	
	Bu nedenle, jenerik öncesi kodda, genelleştirilmiş sınıflar, arabirimler ve yöntemler, çeşitli nesne türleri üzerinde çalışmak için Nesne başvurularını kullandı.
	Sorun, tip güvenliği ile bunu yapamamalarıydı.
	
	Jenerikler, eksik olan tür güvenliğini ekledi.
	Ayrıca süreci kolaylaştırdılar, 
	çünkü artık Nesne ile gerçekte üzerinde çalışılan veri türü arasında çeviri yapmak için açıkça cast kullanmak gerekli değildi.
	
	jeneriklerde, tüm castler otomatik ve örtüktür.
	
--------------------------------------------------------------------------------------------*/






/*--------------------------------------------------------------------------------------------
		A Simple Generics Example
		
	Generic bir sınıfın basit bir örneğiyle başlayalım. 
	Aşağıdaki program iki sınıf tanımlar. 
	Birincisi jenerik sınıf Gen, ikincisi ise Gen'i kullanan GenDemo'dur.
		
		
--------------------------------------------------------------------------------------------*/





//class Gen<T>
//{
//	T ob;
//	
//	
//	Gen(T o)
//	{
//		ob = o;
//	}
//	
//	T getOb()
//	{
//		return ob;
//	}
//	
//	void showType()
//	{
//		System.out.println("Type of T is " + ob.getClass().getName());
//	}
//}
//
//
//class GenDemo
//{
//	public static void main(String[] args) 
//	{
//		Gen<Integer> iOb;
//		
//		iOb = new Gen<Integer>(88);
//		
//		iOb.showType();
//		
//		int v = iOb.getOb();
//		System.out.println("value: " + v);
//		
//		System.out.println();
//		
//		Gen<String> strOb = new Gen<String> ("Generics Test");
//		strOb.showType();
//		
//		String str = strOb.getOb();
//		System.out.println("value: " + str);
//		
//	}
//}





/*-------------------------------------------------------------------------------------------------------------------- 
 			Code Explain
 	
	class Gen<T>
	
	Burada, T bir type parametresinin adıdır.
	Bu ad, bir nesne oluşturulduğunda Gen'e geçirilecek gerçek tür için yer tutucu olarak kullanılır.
	
	Bu nedenle, type parametresine ihtiyaç duyulduğunda Gen içinde T kullanılır.
	T'nin < > içinde bulunduğuna dikkat edin. Bu sözdizimi genelleştirilebilir.
	Bir type parametresi bildirildiğinde, açılı parantezler içinde belirtilir
	
	Gen bir type parametresi kullandığından, 
	Gen parametreli tür olarak da adlandırılan genel bir sınıftır.
	
	Gen'in bildirgesinde(declared), T isminin özel bir önemi yoktur.
	Geçerli herhangi bir tanımlayıcı kullanılmış olabilir, ancak T gelenekseldir.
	
	Ayrıca, tür parametre adlarının tek karakterli büyük harflerle yazılması önerilir.
	
	Yaygın olarak kullanılan diğer tür parametre adları V ve E'dir.
	
	Type parametre adlarıyla ilgili başka bir nokta: JDK 10'dan başlayarak, type parametresinin adı olarak "var" kullanamazsınız.
	
	Daha sonra, burada gösterildiği gibi ob adlı bir nesneyi bildirmek için T kullanılır:
	
	T ob;
	Açıklandığı gibi, T, bir Gen nesnesi oluşturulduğunda belirtilecek gerçek tür için bir yer tutucudur. 
	Böylece, ob, T'ye iletilen tipte bir nesne olacaktır. 
	Örneğin, String türü T'ye geçirilirse, bu örnekte ob, String türünde olacaktır.
	
	
	Gen(T o) {
	ob = o;
	
	}
	
	Parametresi o'nun T türünde olduğuna dikkat edin. 
	Bu, gerçek o türünün, bir Gen nesnesi oluşturulduğunda T'ye geçirilen türe göre belirlendiği anlamına gelir.
	Ayrıca, hem o parametresi hem de değişken ob T türünde olduğundan, bir Gen nesnesi oluşturulduğunda her ikisi de aynı gerçek türde olacaktır
	
	
	type parametresi T, burada gösterilen getOb( ) yönteminde olduğu gibi, bir yöntemin dönüş türünü belirtmek için de kullanılabilir:
	T getOb() {
	return ob;
	}
	
	ob aynı zamanda T türünde olduğundan, türü getOb( ) tarafından belirtilen dönüş türüyle uyumludur.
	
	showType( ) yöntemi, ob üzerinde getClass( ) çağrısı tarafından döndürülen Class nesnesinde getName( ) öğesini çağırarak T türünü görüntüler.
	
	getClass( ) yöntemi Object tarafından tanımlanır ve bu nedenle tüm sınıf türlerinin bir üyesidir. 
	Çağrıldığı nesnenin sınıfının türüne karşılık gelen bir Class nesnesi döndürür.
	
	
	Class, sınıf adının dize temsilini döndüren getName( ) yöntemini tanımlar.
	GenDemo sınıfı, genel Gen sınıfını gösterir. 
	İlk olarak, burada gösterildiği gibi tamsayılar için Gen'in bir sürümünü oluşturur:
		Gen<Integer> iOb;
		
	Bu deklarasyona yakından bakın. 
	İlk olarak, Integer türünün Gen'den sonraki açı parantezleri içinde belirtildiğine dikkat edin.
	Bu durumda, Tamsayı, Gen'in type parametresi T'ye iletilen bir tür bağımsız değişkenidir.
	Bu, etkili bir şekilde, T'ye yapılan tüm referansların Tamsayıya yapılan referanslara çevrildiği bir Gen sürümü oluşturur.
	Bu nedenle, bu bildirim için ob Tamsayı türündedir ve getOb( ) dönüş türü Tamsayı türüdür.
	
	Devam etmeden önce, Java derleyicisinin aslında Gen'in veya başka bir genel sınıfın farklı sürümlerini oluşturmadığını belirtmek gerekir. 
	Bu terimlerle düşünmek yararlı olsa da, gerçekte olan şey bu değildir. 
	Bunun yerine, derleyici kodunuzun Gen'in belirli bir sürümü oluşturulmuş gibi davranmasını sağlamak için gerekli castleri değiştirerek tüm generic tür bilgilerini kaldırır.
	Bu nedenle, programınızda gerçekten var olan Gen'in sadece bir sürümü vardır.
	Generic tür bilgilerini kaldırma işlemine erasure adı verilir
	
	Sonraki satır, iOb'a Gen sınıfının Tamsayı sürümünün bir örneğine bir başvuru atar:
		iOb = new Gen<Integer>(88);
		Gen oluşturucu çağrıldığında, Integer tür bağımsız değişkeninin de belirtildiğine dikkat edin.
		Bunun nedeni, başvurunun atandığı nesnenin türünün (bu durumda iOb) 'Gen'<Integer> türünde olmasıdır.
		Bu nedenle, new tarafından döndürülen referans da Gen<Integer> tipinde olmalıdır.
	
	Değilse, derleme zamanı hatası oluşur. Örneğin, aşağıdaki atama derleme zamanı hatasına neden olur:
		iOb = new Gen<Double>(88.0); //Error
		iOb Gen<Integer> türünde olduğundan, Gen<Double>'ın bir nesnesine başvurmak için kullanılamaz.
		
	Bu tip kontrolü, jeneriklerin ana faydalarından biridir, çünkü tip güvenliğini sağlar.
	
		
	
	iOb = new Gen<Integer>(88);
	int olan 88 değerini bir Tamsayıya kapsüllemek için otomatik kutulamadan yararlanır.
	Bu işe yarar çünkü Gen,<Integer> Tamsayı argümanı alan bir oluşturucu oluşturur.
	
	Bu işe yarar çünkü Gen,<Integer> Tamsayı argümanı alan bir oluşturucu oluşturur. 
	Bir Tamsayı beklendiğinden, Java otomatik olarak 88'i bir tamsayı içine yerleştirir. 
	Tabii ki, açıkça şöyle yazılabilirdi:
		iOb = new Gen<Integer>(Integer.valueOf(88));
		Ancak, bu sürümü kullanmanın hiçbir yararı olmaz.
	
	
	Program daha sonra integer olan iOb içindeki ob türünü görüntüler. 
	Ardından, program aşağıdaki satırı kullanarak ob değerini elde eder:
		int v = iOb.getOb();
	
	getOb( ) öğesinin dönüş türü, iOb bildirildiğinde Tamsayı ile değiştirilen T olduğundan, 
	getOb( ) öğesinin dönüş türü de v'ye (int olan) atandığında int kutusuna açılan Integer'dir.
	Bu nedenle, getOb( ) dönüş türünü Tamsayı'ya cast etmeye gerek yoktur.

	Tabii ki, otomatik kutudan çıkarma özelliğini kullanmak gerekli değildir.
	Bir önceki satır da şöyle yazılabilirdi:
		int v = iOb.getOb().intValue();
	Ancak, otomatik kutudan çıkarma özelliği kodu daha kompakt hale getirir.
	
	Next, GenDemo declares an object of type Gen<String>:
		Gen<String> strOb = new Gen<String>("Generics Test");
		
	Tür bağımsız değişkeni String olduğundan, String, Gen'in içindeki T ile değiştirilir. 
	Bu, programdaki kalan satırların gösterdiği gibi, Gen'in bir String sürümünü (kavramsal olarak) oluşturur.
	
	
------------------------------------------------------------------------------------------------------------------------------*/





/*--------------------------------------------------------------------------------------------------------------------------
 		Generics Work Only with Reference Types
 	
 	Genel bir türün örneğini bildirirken, type parametresine iletilen type bağımsız değişkeni bir referans türü olmalıdır.
 	int veya char gibi ilkel bir tür kullanamazsınız.
 	Örneğin, Gen ile herhangi bir sınıf türünü T'ye geçirmek mümkündür, ancak ilkel bir türü type parametresine geçiremezsiniz.
 	Bu nedenle, aşağıdaki beyan hata verir:
 		Gen<int> intOb = new Gen<int>(53); // Error, can't use primitive type
 	
 	Elbette, ilkel bir tür belirtememek ciddi bir kısıtlama değildir, 
 	çünkü ilkel bir türü kapsüllemek için tür sarmalayıcılarını(Wrapper Classes) (önceki örnekte olduğu gibi) kullanabilirsiniz.
 	Ayrıca, Java'nın otomatik kutulama ve otomatik kutudan çıkarma mekanizması, tür sarmalayıcısının kullanımını şeffaf hale getirir.
 	
---------------------------------------------------------------------------------------------------------------------------*/





/*------------------------------------------------------------------------------------------------------
 		Generic Types Differ Based on Their Type Arguments
 		
 	Genel türler hakkında anlaşılması gereken önemli bir nokta, genel bir türün belirli bir sürümünün başvurusunun, 
 	aynı genel türün başka bir sürümüyle uyumlu bir tür olmamasıdır.
 	
 	iOb = strOb; //Wrong!
 	
 	Hem iOb hem de strOb Gen türünde olsa da<T>, tür bağımsız değişkenleri farklı olduğu için farklı türlere referanslardır. 
 	Bu, jeneriklerin tür güvenliği ekleme ve hataları önleme yönteminin bir parçasıdır.
 	
 	
-------------------------------------------------------------------------------------------------------*/



/*------------------------------------------------------------------------------------------------------
 		How Generics Improve Type Safety
 	
 	Bu noktada, kendinize şu soruyu soruyor olabilirsiniz: Genel Gen sınıfında bulunan aynı işlevselliğin, 
 	jenerikler olmadan, yalnızca Object'i veri türü olarak belirterek ve uygun castleri kullanarak elde edilebileceği göz önüne alındığında, 
 	Gen'i jenerik yapmanın yararı nedir?
 	
 	Cevap, jeneriklerin Gen'i içeren tüm operasyonların tip güvenliğini otomatik olarak sağlamasıdır.
 	Bu süreçte, castleri girme ve kodu elle kontrol etme ihtiyacını ortadan kaldırırlar.
 	
 	Genel türlerin yararlarını anlamak için, önce Gen'in genel olmayan bir eşdeğerini oluşturan aşağıdaki programı göz önünde bulundurun:
---------------------------------------------------------------------------------------------------------*/ 

//class NonGen
//{
//	Object ob;
//	
//	NonGen(Object o)
//	{
//		ob = o;
//	}
//	
//	Object getOb()
//	{
//		return ob;
//	}
//	
//	void showType()
//	{
//		System.out.println("Type of ob is" + ob.getClass().getName());
//	}
//	
//}
//
//
//class NonGenDemo
//{
//	public static void main(String[] args) 
//	{
//		NonGen iob;
//		
//		iob = new NonGen(88);
//		iob.showType();
//		
//		int v = (Integer) iob.getOb();
//		System.out.println("value : " + v);
//		
//		System.out.println();
//		
//		NonGen strOb = new NonGen("Non Generic Test");
//		strOb.showType();
//		
//		
//		String str = (String) strOb.getOb();
//		System.out.println("value: " + str);
//		
//		// This compiles, but is conceptually wrong!
//		iob=strOb;
//		
//		v = (Integer) iob.getOb(); // run-time error!
//	}
//}



/*--------------------------------------------------------------------------------------------------------------------------
 		Code Exlplain
 	Bu sürümde ilgi çekici birkaç şey var. İlk olarak, NonGen'in T'nin tüm kullanımlarını Object ile değiştirdiğine dikkat edin.
 	Bu, NonGen'in genel sürümde olduğu gibi her türlü nesneyi depolayabilmesini sağlar.
 	
 	Bununla birlikte, Java derleyicisinin NonGen'de depolanan veri türü hakkında gerçek bir bilgiye sahip olmasını da önler, bu da iki nedenden dolayı kötüdür.
 	İlk olarak, depolanan verileri almak için açıkca cast işlemleri kullanılmalıdır.
 	
 	İkincisi, çalışma zamanına kadar birçok türde tür uyuşmazlık hatası bulunamaz.
 	Her soruna yakından bakalım.
 	
 	int v = (Integer) iOb.getOb();
 	getOb( ) dönüş türü Object olduğundan, bu değerin kutusunun otomatik olarak kaldırılmasını ve v içinde depolanmasını sağlamak için Tamsayıya cast gereklidir. Casti kaldırırsanız, program derlenmez.
 	Generic versiyonda, bu cast işlemleri arka planda yapılıyor. 
 	Genel olmayan sürümde, casting işlemini açıkca yapıyoruz. Bu sadece bir rahatsızlık değil, aynı zamanda potansiyel bir hata kaynağıdır.
 	
 	
 	Şimdi, programın sonuna yakın bir yerden aşağıdaki sırayı düşünün:
 	
 		// This compiles, but is conceptually wrong!
		iOb = strOb;
		v = (Integer) iOb.getOb(); // run-time error!
		
 	Burada, strOb iOb'a atanır. Ancak, strOb bir tamsayı değil, bir string içeren bir nesneyi ifade eder.
 	Bu atama sözdizimsel olarak geçerlidir, çünkü tüm NonGen başvuruları aynıdır ve herhangi bir NonGen başvurusu başka bir NonGen nesnesine başvurabilir.
 	
 	Ancak, bir sonraki satırın gösterdiği gibi, ifade semantik olarak yanlıştır.
 	Burada, getOb( ) dönüş türü Tamsayı'ya atılır ve ardından bu değeri v'ye atamaya çalışılır.
 	
 	Sorun şu ki, iOb artık bir Tamsayı değil, bir String depolayan bir nesneye atıfta bulunuyor. 
 	Ne yazık ki, jenerik kullanımı olmadan, Java derleyicisinin bunu bilmesinin bir yolu yoktur.
 	
 	Bunun yerine, Tamsayıya cast denendiğinde bir runtime error alırız. 
 	Bildiğiniz gibi, kodunuzda çalışma zamanı istisnalarının gerçekleşmesi son derece kötüdür!
 	
 	Önceki dizi, jenerikler kullanıldığında oluşamaz. 
 	Bu dizi programın genel sürümünde denenirse, derleyici bunu yakalar ve bir hata bildirir, 
 	böylece çalışma zamanı özel durumuna neden olan ciddi bir hatayı önler. 
 	
 	Derleme sırasında tür uyuşmazlığı hatalarının yakalandığı tür açısından güvenli kod oluşturma yeteneği, jeneriklerin önemli bir avantajıdır.
 	
 	
 	\"Genel\" kod oluşturmak için Obje nesnesini başvurularını kullanmak her zaman mümkün olsa da, 
 	bu kod türü güvenli değildi ve yanlış kullanımı çalışma zamanı özel durumlarına neden olabilir.
 	
 	Genel türler bunun oluşmasını önler. 
 	Temel olarak, jenerikler aracılığıyla, çalışma zamanı hataları derleme zamanı hatalarına dönüştürülür. 
 	Bu büyük bir avantajdır.
 	
 	
----------------------------------------------------------------------------------------------------------------*/



/*
 			A Generic Class with Two Type Parameters

 	Genel bir türde birden fazla tür parametresi bildirebilirsiniz. İki veya daha fazla tür parametresi belirtmek için, 
 	virgülle ayrılmış bir liste kullanmanız yeterlidir. Örneğin, aşağıdaki TwoGen sınıfı, iki tür parametresi olan Gen sınıfının bir varyasyonudur:
 			
*/

//class TwoGen<T,V>
//{
//	T ob1;
//	V ob2;
//	
//	//pass the constructor a reference to
//	//an object of type T and an object of type V.
//	
//	TwoGen(T o1, V o2)
//	{
//		ob1 = o1;
//		ob2 = o2;
//	}
//	
//	//Show types of t and v.
//	
//	void showTypes()
//	{
//		System.out.println("Type of T is" + ob1.getClass().getName());
//		
//		System.out.println("Type of V is" + ob2.getClass().getName());
//	}
//	
//	T getOb1()
//	{
//		return ob1;
//	}
//	
//	V getOb2()
//	{
//		return ob2;
//	}
//}
//
//class SimpGen
//{
//	public static void main(String[] args) 
//	{
//		TwoGen<Integer,String> tgObj = new TwoGen<Integer,String>(88,"Generics");
//		
//		tgObj.showTypes();
//		
//		int v = tgObj.getOb1();
//		System.out.println("value: " + v);
//		
//		String str = tgObj.getOb2();
//		System.out.println("value: " + str);
//	}
//}


/*------------------------------------------------------------------------------------------------------
		Code Explain

	İki tür parametresi belirtir: virgülle ayrılmış T ve V. 
	İki tür parametresi olduğundan, aşağıda gösterildiği gibi bir nesne oluşturulduğunda iki tür bağımsız değişkeninin TwoGen'e geçirilmesi gerekir:
		TwoGen<Integer, String> tgObj = new TwoGen<Integer, String>(88, "Generics");

	
	Bu durumda, Tamsayı T yerine geçer ve String V yerine kullanılır.
	
	Bu örnekte iki tür bağımsız değişkeni farklı olsa da, her iki türün de aynı olması mümkündür. 
	Örneğin, aşağıdaki kod satırı geçerlidir:
	TwoGen<String, String> x = new TwoGen<String, String> ("A", "B");
	
	Bu durumda, hem T hem de V, String türünde olacaktır. 
	Tabii ki, tür bağımsız değişkenleri her zaman aynı olsaydı, 
	iki tür parametresi gereksiz olurdu.

------------------------------------------------------------------------------------------------------*/








/*--------------------------------------------------------------------------------------------
 		The General Form of a Generic Class
 	Genel bir sınıf bildirmeye yönelik sözdizimi aşağıda verilmiştir:
 	
 	class class-name<type-param-list> { // …
 	
 	Genel bir sınıfa ve örnek oluşturmaya bir başvuru atamak için tam sözdizimi aşağıda verilmiştir:
 		class-name<type-arg-list> var-name = new class-name<type-arg-list>(cons-arg-list);
---------------------------------------------------------------------------------------------*/


/*
 			Bounded Types
 			
	Önceki örneklerde, tür parametreleri herhangi bir sınıf türüyle değiştirilebilir.
	Bu, birçok amaç için iyidir, ancak bazen bir type parametresine geçirilebilecek türleri sınırlamak yararlı olabilir.

	Örneğin, bir sayı dizisinin ortalamasını döndüren bir yöntem içeren genel bir sınıf oluşturmak istediğinizi varsayalım.
	
	Ayrıca, integer, float ve doubler dahil olmak üzere herhangi bir sayı türündeki bir dizinin ortalamasını elde etmek için sınıfı kullanmak istersiniz.
	
	Bu nedenle, bir type parametresi kullanarak sayıların türünü genel olarak belirtmek istersiniz. 
	Böyle bir sınıf oluşturmak için şöyle bir şey deneyebilirsiniz:
*/

//class Stats<T>
//{
//	T[] nums;
//	
//	Stats(T[] o)
//	{
//		nums = o;
//	}
//	
//	double average()
//	{
//		double sum = 0.0;
//		
//		for(int i = 0; i < nums.length; i++)
//			sum += nums[i].doubleValue();
//		
//		
//		return sum / nums.length;
//	}
//}


/*
 	Stats sınıfında, average( ) yöntemi, doubleValue( ) öğesini çağırarak nums dizisindeki her sayının çift sürümünü elde etmeye çalışır.
 	
 	Integer ve Double gibi tüm sayısal sınıflar Number'ın alt sınıfları olduğundan ve Number doubleValue( ) 
 	yöntemini tanımladığından, bu yöntem tüm sayısal sarmalayıcı sınıfları tarafından kullanılabilir.

	Sorun şu ki, derleyicinin yalnızca sayısal türler kullanarak Stats nesneleri oluşturmak istediğinizi bilmesinin bir yolu yoktur.
	
	Bu nedenle, Stats sınıfını derlemeye çalıştığınızda, doubleValue( ) yönteminin bilinmediğini gösteren bir hata bildirilir.
	Bu sorunu çözmek için, derleyiciye yalnızca sayısal türleri T'ye geçirmek istediğinizi söylemenin bir yoluna ihtiyacınız vardır.
	
	Ayrıca, yalnızca sayısal türlerin gerçekten geçildiğinden emin olmak için bir yola ihtiyacınız vardır.
	
	Bu gibi durumlarla başa çıkmak için Java sınırlı türler sağlar. 
	Bir type parametresi belirtirken, tüm type bağımsız değişkenlerinin türetilmesi gereken üst sınıfı bildiren bir üst sınır oluşturabilirsiniz.
	
	Bu, burada gösterildiği gibi type parametresini belirtirken extends yan tümcesi kullanılarak gerçekleştirilir:
		<T extends superclass>
		
	Bu, T'nin yalnızca üst sınıf veya üst sınıfın alt sınıfları ile değiştirilebileceğini belirtir.
	Böylece, süper sınıf kapsayıcı, üst bir sınır tanımlar.
	
	Burada gösterildiği gibi, Number'ı üst sınır olarak belirterek daha önce gösterilen
	Stats sınıfını düzeltmek için bir üst sınır kullanabilirsiniz:
	 
*/
//Numberdan extend eden herhangi bir tür = <T extends Number> 
//class Stats<T extends Number>
//{
//	T[] nums;
//	
//	Stats(T[] o)
//	{
//		nums = o;
//	}
//	
//	double average()
//	{
//		double sum = 0.0;
//		
//		for(int i = 0; i< nums.length; i++)
//		{
//			sum += nums[i].doubleValue();
//		}
//		
//		return sum / nums.length;
//	}
//}
//
//class BoundsDemo
//{
//	public static void main(String[] args) 
//	{
//		Integer[] inums = {1,2,3,4,5};
//		Stats<Integer> iob = new Stats<>(inums);
//		double v = iob.average();
//		System.out.println("iob average is " + v);
//		
//		Double[] dnums = { 1.1, 2.2, 3.3, 4.4, 5.5 };
//		Stats<Double> dob = new Stats<>(dnums);
//		double w = dob.average();
//		System.out.println("dob average is " + w);
//		
//		
//		// This won't compile because String is not a
//		// subclass of Number.
//		String[] strs = { "1", "2", "3", "4", "5" };
//		Stats<String> strob = new Stats<String>(strs);
//		
//	}
//}

/*------------------------------------------------------------------------------------------------
 		Code Explain
 	
 	T türü artık Number ile sınırlandırıldığından, 
 	Java derleyicisi T türündeki tüm nesnelerin doubleValue( ) öğesini çağırabileceğini bilir, 
 	çünkü bu Number tarafından bildirilen bir yöntemdir.
 	Bu, kendi başına, büyük bir avantajdır. 
 	Bununla birlikte, ek bir bonus olarak, T'nin sınırlandırılması, sayısal olmayan Stats nesnelerinin oluşturulmasını da önler.
 	
 	Bir sınıf türünü sınır olarak kullanmanın yanı sıra, bir arabirim türü de kullanabilirsiniz.
 	Aslında birden fazla arabirimi sınır olarak belirtebilirsiniz.
 	Bu durumda, önce sınıf türü belirtilmelidir.
 	
 	
 	Ayrıca, bir sınır hem sınıf türünü hem de bir veya daha fazla arabirimi içerebilir.
 	Bir sınır arabirim türü içerdiğinde, yalnızca bu arabirimi uygulayan tür bağımsız değişkenleri geçebilir.
 	
 	Bir sınıfı ve arabirimi veya birden çok arabirimi olan bir sınır belirtirken, bunları bağlamak için & işlecini kullanın.
 	class Gen<T extends MyClass & MyInterface> {
 	Burada T, MyClass adlı bir sınıf ve MyInterface adlı bir arabirim tarafından sınırlandırılmıştır.
 	
 	Bu nedenle, T'ye geçirilen herhangi bir tür bağımsız değişkeni MyClass'ın bir alt sınıfı olmalı ve MyInterface'i uygulamalıdır.
 	
--------------------------------------------------------------------------------------------------*/






/*----------------------------------------------------------------------------------------------------
 		Using Wildcard Arguments
 		
 	Tip güvenliği ne kadar kullanışlı olursa olsun, bazen mükemmel kabul edilebilir yapıların önüne geçebilir.
 	Örneğin, önceki bölümün sonunda gösterilen Stats sınıfı göz önüne alındığında, 
 	her nesnenin ne tür sayısal veri tuttuğuna bakılmaksızın, 
 	iki Stats nesnesinin aynı ortalamayı veren diziler içerip içermediğini belirleyen isSameAvg( ) adlı bir yöntem eklemek istediğinizi varsayalım.
 	Örneğin, bir nesne 1.0, 2.0 ve 3.0 double değerlerini içeriyorsa ve diğer nesne 2, 1 ve 3 integer değerlerini içeriyorsa, ortalamalar aynı olacaktır.
 	
 	isSameAvg( ) öğesini uygulamanın bir yolu, ona bir Stats bağımsız değişkeni iletmek
 	ve ardından bu bağımsız değişkenin ortalamasını çağıran nesneyle karşılaştırmak ve yalnızca ortalamalar aynıysa true değerini döndürmektir.
 	
 	Örneğin, burada gösterildiği gibi isSameAvg( ) öğesini çağırabilmek istiyorsunuz:
 	
 	Integer[] inums = { 1, 2, 3, 4, 5 };
	Double[] dnums = { 1.1, 2.2, 3.3, 4.4, 5.5 };
	
	Stats<Integer> iob = new Stats<Integer>(inums);
	Stats<Double> dob = new Stats<Double>(dnums);
	if(iob.isSameAvg(dob))
 		System.out.println("Averages are the same.");
	else
 		System.out.println("Averages differ.");
 		
 		
 	İlk başta, isSameAvg( ) oluşturmak kolay bir sorun gibi görünüyor.
 	Stats genel olduğundan ve average( ) yöntemi herhangi bir Stats nesnesi türünde çalışabildiğinden, isSameAvg( ) öğesinin oluşturulması kolay olacaktır.
 	
 	Ne yazık ki, Stats türünde bir parametre bildirmeye çalıştığınız anda sorun başlar.
 	Stats parametreli bir tür olduğundan, 
 	bu türde bir parametre bildirdiğinizde Stats türü parametresi için ne belirtirsiniz?
 	
 	İlk başta, T'nin type parametresi olarak kullanıldığı böyle bir çözüm düşünebilirsiniz:
 		
 	// This won't work!
	// Determine if two averages are the same.
	boolean isSameAvg(Stats<T> ob) {
 		if(average() == ob.average())
 		return true;
 	return false;
	}
	
	Bu denemeyle ilgili sorun, yalnızca türü çağıran nesneyle aynı olan diğer Stats nesneleriyle çalışacağıdır.
	Örneğin, çağıran nesne Stats <Integer>türündeyse, ob parametresi de Stats<Integer> türünde olmalıdır.
	
	Örneğin, Stats türündeki bir nesnenin<Double> ortalamasını, Stats türündeki bir nesnenin<short> ortalamasıyla karşılaştırmak için kullanılamaz.
	Bu nedenle, bu yaklaşım çok dar bir bağlam dışında işe yaramaz ve genel (yani generic) bir çözüm getirmez.
	
	Genel bir isSameAvg( ) yöntemi oluşturmak için Java jeneriklerinin başka bir özelliğini kullanmanız gerekir: wildcard bağımsız değişkeni.
	
	
	Wildcard bağımsız değişkeni ?, tarafından belirtilir ve bilinmeyen bir türü temsil eder. 
	wildcard kullanarak, isSameAvg( ) yöntemini yazmanın bir yolu aşağıda verilmiştir:
		// Determine if two averages are the same.
		// Notice the use of the wildcard.
		boolean isSameAvg(Stats<?> ob) {
 			if(average() == ob.average())
 			return true;
 		return false;
		}
		
	Burada, Stats<?> herhangi bir Stats nesnesiyle eşleşir ve herhangi iki Stats nesnesinin ortalamalarının karşılaştırılmasına izin verir. 
	Aşağıdaki program bunu göstermektedir:
	
--------------------------------------------------------------------------------------------------------------------------------------*/



//class Stats<T extends Number>
//{
//	T[] nums ;
//	
//	Stats(T[] o)
//	{
//		nums = o;
//	}
//	
//	double average()
//	{
//		double sum = 0.0;
//		
//		for(int i = 0; i< nums.length; i++)
//			sum += nums[i].doubleValue();
//		
//		return sum / nums.length;
//	}
//	
//	boolean isSameAvg(Stats<?> ob)
//	{
//		if (average() == ob.average())
//			return true;
//		
//		return false;
//	}
//}
//
//class WildcardDemo
//{
//	public static void main(String[] args)
//	{
//		Integer[] inums = {1,2,3,4,5};
//		Stats<Integer> iob = new Stats<Integer>(inums);
//		
//		double v = iob.average();
//		System.out.println("iob average is" + v);
//		
//		Double[] dums = {1.1, 2.2, 3.3, 4.4, 5.5};
//		Stats<Double> dob = new Stats<Double>(dums);
//		
//		double w = dob.average();
//		System.out.println("dob average is " + w);
//		
//		Float[] fnums = { 1.0F, 2.0F, 3.0F, 4.0F, 5.0F };
//		Stats<Float> fob = new Stats<Float>(fnums);
//		
//		double x = fob.average();
//		System.out.println("fob average is " + x);
//		
//		
//		if(iob.isSameAvg(dob))
//			System.out.println("are the same.");
//		else
//			System.out.println("differ.");
//			
//		System.out.print("Averages of iob and fob ");
//		if(iob.isSameAvg(fob))
//			 System.out.println("are the same.");
//		else
//			 System.out.println("differ.");
//		
//	}
//}

/*-----------------------------------------------------------------------------------------
 	Son bir nokta: Wildcardın ne tür Stats nesnelerinin oluşturulabileceğini etkilemediğini anlamak önemlidir.
 	Bu, Stats bildirimindeki extends maddesi tarafından yönetilir. 
 	wildcard, geçerli herhangi bir Stats nesnesiyle eşleşir.
--------------------------------------------------------------------------------------------*/





/*----------------------------------------------------------------------------------------
 				Bounded Wildcards
 	
 	Wildcard bağımsız değişkenleri, bir type parametresinin sınırlanabileceği şekilde sınırlandırılabilir.
 	Sınırlı wildcard, sınıf hiyerarşisinde çalışacak generic bir tür oluştururken özellikle önemlidir.
 	Nedenini anlamak için, bir örnek üzerinde çalışalım. 
 	Koordinatları kapsülleyen aşağıdaki sınıf hiyerarşisini göz önünde bulundurun:
------------------------------------------------------------------------------------------*/

class TwoD
{
	int x,y;
	
	TwoD(int a,int b)
	{
		x = a;
		y = b;
	}
	
}

class ThreeD extends TwoD
{
	int z;
	
	ThreeD(int a, int b,int c)
	{
		super(a,b);
		z = c;
	}
}

class FourD extends ThreeD
{
	int t;
	
	FourD(int a, int b,int c, int d)
	{
		super(a,b,c);
		t = d;
	}
}


/*
 Hiyerarşinin en üstünde, iki boyutlu, XY koordinatını kapsülleyen TwoD bulunur.
 TwoD, üçüncü bir boyut ekleyen ve bir XYZ koordinatı oluşturan ThreeD tarafından devralınır.
 ThreeD, dört boyutlu bir koordinat veren dördüncü bir boyut (zaman) ekleyen FourD tarafından miras alınır. 
  
 Sonra, bir dizi koordinatı depolayan Coords adlı genel bir sınıf gösterilmektedir:
*/
class Coords<T extends TwoD>
{
	T[] coords;

	Coords(T[] o) {coords = o;}
	/*
	 Coords'un TwoD ile sınırlanmış bir type parametresi belirttiğine dikkat edin.
	 Bu, bir Coords nesnesinde depolanan herhangi bir dizinin TwoD türünde nesneler veya alt sınıflarından birini içereceği anlamına gelir.
	 Şimdi, bir Coords nesnesinin coords dizisindeki her öğe için X ve Y koordinatlarını görüntüleyen bir yöntem yazmak istediğinizi varsayalım.
	 Tüm Coords nesne türlerinin en az iki koordinatı (X ve Y) olduğundan, burada gösterildiği gibi wildcard kullanarak bunu yapmak kolaydır:
	*/
	
	static void showXy(Coords<?> c)
	{
		System.out.println("X Y coordinates:");
		for(int i = 0; i < c.coords.length;i++)
			System.out.println(c.coords[i].x + " " +c.coords[i].y);
		System.out.println();
	}
	
	
	/*
	 Coords, TwoD'yi üst sınır olarak belirten sınırlı bir genel tür olduğundan, 
	 Coords nesnesi oluşturmak için kullanılabilecek tüm nesneler TwoD türünde diziler veya TwoD'den türetilmiş sınıflar olacaktır.
	 
	 Böylece, showXY( ) herhangi bir Coords nesnesinin içeriğini görüntüleyebilir.
	 Ancak, bir ThreeD veya FourD nesnesinin X, Y ve Z koordinatlarını görüntüleyen bir yöntem oluşturmak isterseniz ne olur?
	 ? Sorun şu ki, tüm Coords nesnelerinin üç koordinatı olmayacak, çünkü bir Coords<TwoD> nesnesi yalnızca X ve Y'ye sahip olacak.
	 
	 Bu nedenle, Coords ve Coords nesneleri için X, Y ve Z koordinatlarını görüntüleyen<ThreeD> ve<FourD> 
	 bu yöntemin Coords nesneleriyle kullanılmasını engelleyen bir yöntemi nasıl yazarsınız<TwoD>?
	 The answer is the bounded wildcard argument.
	 
	 Sınırlanmış bir wildcard, tür bağımsız değişkeni için bir üst sınır veya alt sınır belirtir.
	 Bu, bir yöntemin üzerinde çalışacağı nesne türlerini kısıtlamanıza olanak tanır.
	 
	 En yaygın sınırlanmış wildcard, sınırlanmış bir tür oluşturmak için kullanıldığı şekilde bir extends yan tümcesi kullanılarak oluşturulan üst sınırdır.
	 
	 Sınırlı wildcard kullanarak, bir Coords nesnesinin X, Y ve Z koordinatlarını görüntüleyen bir yöntem oluşturmak kolaydır, eğer nesne gerçekten bu üç koordinata sahipse.


	Örneğin, aşağıdaki showXYZ( ) yöntemi, bir Coords nesnesinde depolanan öğelerin X, Y ve Z koordinatlarını, 
	bu öğeler aslında ThreeD türündeyse (veya ThreeD'den türetilmişse) gösterir:
*/
	static void showXYZ(Coords<? extends ThreeD> c)
	{
		System.out.println("X Y Z Coordinates :");
		for(int i=0; i < c.coords.length; i++)
			 System.out.println(c.coords[i].x + " " +
					 			c.coords[i].y + " " +
					 			c.coords[i].z);
		System.out.println();
	}
	
	/*
	 C parametresinin bildirimindeki wildcarda bir extends yan tümcesinin eklendiğine dikkat edin.
	 Diyor ki? ThreeD veya ThreeD'den türetilmiş bir sınıf olduğu sürece herhangi bir türle eşleşebilir.
	 Böylece, extends maddesi bir üst sınır oluşturur ki ? eşleşebilir.
	 
	 Bu sınır nedeniyle, showXYZ( ) Coords<ThreeD> veya Coords<FourD> türündeki nesnelere referanslarla çağrılabilir,ancak Coords<TwoD> türünde bir referansla çağrılamaz.
	 showXZY( )'yi bir Coords<TwoD> referansıyla çağırmaya çalışmak derleme zamanı hatasıyla sonuçlanır ve böylece tür güvenliği sağlanır.
	 
	 */
	
}

/*--------------------------------------------------------------------------------------------------------
 	Genel olarak, wildcard için bir üst sınır oluşturmak üzere aşağıdaki wildcard ifadesi türünü kullanın:
 		
 		<? extends superclass>
 	
 	burada üst sınıf, üst sınır olarak hizmet veren sınıfın adıdır. 
 	Unutmayın, bu kapsayıcı bir maddedir, çünkü üst sınırı oluşturan sınıf (yani üst sınıf tarafından belirtilen) de sınırlar içindedir.
 	
 	
 	wildcard bildirimine super yan tümce ekleyerek wildcard için daha düşük bir sınır da belirtebilirsiniz. 
 	İşte genel şekli:
 	
 	<? super subclass>
 	
 	Bu durumda, yalnızca alt sınıfın üst sınıfları olan sınıflar kabul edilebilir bağımsız değişkenlerdir. 
 	Bu kapsayıcı bir maddedir.
 
----------------------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------------------------------------
  		Creating Generic Method
  	
  	Önceki örneklerde gösterildiği gibi, genel sınıf içindeki yöntemler bir sınıfın type parametresini kullanabilir ve bu nedenle type parametresine göre otomatik olarak geneldir. 
  	
  	Ancak, kendi başına bir veya daha fazla tür parametresi kullanan genel bir yöntem bildirmek mümkündür. 
  	Ayrıca, genel olmayan bir sınıf içine alınmış genel bir yöntem oluşturmak mümkündür.
  	
  	Bir örnekle başlayalım. Aşağıdaki program, GenMethDemo adlı genel olmayan bir sınıfı ve bu sınıf içinde isIn( ) adlı statik bir genel yöntemi bildirir.
  	isIn( ) yöntemi, bir nesnenin bir dizinin üyesi olup olmadığını belirler.
  	Dizi, aranan nesnenin türüyle uyumlu nesneler içerdiği sürece herhangi bir nesne ve dizi türüyle kullanılabilir.
  

-----------------------------------------------------------------------------------------------------------*/

class GenMethDemo
{
	
	static <T extends Comparable<T>,V extends T> boolean isIn(T x, V[] y)
	{
		for(int i = 0; i < y.length; i++)
			if(x.equals(y[i])) return true;
		
		return false;
	}
	
	public static void main(String[] args) 
	{
		Integer[] nums = {1,2,3,4,5};
		if(isIn(2, nums))
			System.out.println("2 is in nums");
		
		if(!isIn(7, nums))
			 System.out.println("7 is not in nums");
		System.out.println();
		
		// Use isIn() on Strings.
		String[] strs = { "one", "two", "three",
		 "four", "five" };
		 
		 
		if(isIn("two", strs))
			System.out.println("two is in strs");
		 
		if(!isIn("seven", strs))
			 System.out.println("seven is not in strs");
		
	}
}

/*----------------------------------------------------------------------------------------------------------------
	isIn( ) i yakından inceleyelim. İlk olarak, bu satırla nasıl bildirildiğine dikkat edin:
	static <T extends Comparable<T>, V extends T> boolean isIn(T x, V[] y) {
	
	Type parametreleri, yöntemin dönüş türünden önce bildirilir.
	Ayrıca T'nin Comparable olduğunu unutmayın.
	Comparable, java.lang kütüphanesinde bildirilen bir interfacedir.
	Comparable uygulayan bir sınıf, sıralanabilir nesneleri tanımlar.
	
	Bu nedenle, Comparable üst sınırının istenmesi, isIn( ) öğesinin yalnızca comparable ı implement eden nesnelerle kullanılabilmesini sağlar.
	Comparable genericdir ve type parametresi comparable nesnelerin türünü belirtir.
	
	Ardından, V türünün T ile üst sınırlandığına dikkat edin. 
	Bu nedenle, V ya T tipi ile aynı olmalı ya da T'nin bir alt sınıfı olmalıdır.
	
	Bu ilişki, isIn( )'nin yalnızca birbiriyle uyumlu bağımsız değişkenlerle çağrılabileceğini zorlar.
	Ayrıca isIn( ) öğesinin statik olduğuna ve herhangi bir nesneden bağımsız olarak çağrılmasına olanak tanıdığına dikkat edin.
	
	Bununla birlikte, genel yöntemlerin statik veya statik olmayabilir. Bu konuda herhangi bir kısıtlama yoktur.
	
	Şimdi, isIn( ) 'nin main( ) içinde normal çağrı sözdizimini kullanarak, tür bağımsız değişkenlerini belirtmeye gerek kalmadan nasıl çağrıldığına dikkat edin.
	Bunun nedeni, bağımsız değişken türlerinin otomatik olarak algılanması ve T ve V türlerinin buna göre ayarlanmasıdır.
	if(isIn(2, nums))
	ilk bağımsız değişkenin türü Tamsayı'dır (otomatik kutulama nedeniyle), bu da Tamsayı'nın T ile değiştirilmesine neden olur.
	İkinci argümanın temel türü de Tamsayı'dır, bu da Tamsayı'yı V'nin yerine koyar.
	
	Tür çıkarımı çoğu genel yöntem çağrısı için yeterli olsa da, gerekirse 
	tür bağımsız değişkenini açıkça belirtebilirsiniz. 
	Örneğin, tür bağımsız değişkenleri belirtildiğinde isIn( ) öğesine yapılan ilk çağrının nasıl göründüğü aşağıda açıklanmıştır:
	
	GenMethDemo.<Integer,Integer> isIn(2,nums)
	Tabii ki, bu durumda, tür argümanlarını belirterek kazanılan hiçbir şey yoktur.
	
	// if(isIn("two", nums))
	// System.out.println("two is in strs");
	
	Açıklamaları kaldırır ve sonra programı derlemeye çalışırsanız, bir hata alırsınız.
	Bunun nedeni, V type parametresinin, V'nin bildirimindeki extends yan tümcesinde T ile sınırlandırılmış olmasıdır.
	Bu, V'nin ya T tipi ya da T'nin bir alt sınıfı olması gerektiği anlamına gelir.
	Bu durumda, ilk bağımsız değişken String türündedir ve T'yi String'e dönüştürür, 
	ancak ikinci bağımsız değişken String'in bir alt sınıfı olmayan Integer türündedir.
	
	Bu,  compile-time type-mismatch hatasına neden olur.
	
	Tip güvenliğini uygulama yeteneği, generic yöntemlerin en önemli avantajlarından biridir.
	
	isIn( ) oluşturmak için kullanılan sözdizimi genelleştirilebilir. 
	Genel bir yöntemin sözdizimi aşağıda verilmiştir:
	
	<type-param-list> ret-type meth-name (param-list) { // …
	
	Her durumda, type-param-list, tür parametrelerinin virgülle ayrılmış bir listesidir. 
	Genel bir yöntem için, type parametre listesinin dönüş türünden önce geldiğine dikkat edin.
	
----------------------------------------------------------------------------------------------------------------*/






/*----------------------------------------------------------------------------------------
		Generic Constructors
		
	Constructorların sınıfları generic olmasa bile kendilerinin generic olmaları mümkündür. 
	Örneğin, aşağıdaki kısa programı göz önünde bulundurun:

------------------------------------------------------------------------------------------*/

//class GenCons
//{
//	private double val;
//	
//	<T extends Number> GenCons(T arg)
//	{
//		val = arg.doubleValue();
//	}
//	
//	void showVal()
//	{
//		System.out.println("val : "+ val);
//	}
//}
//
//class GenConsDemo
//{
//	public static void main(String[] args) 
//	{
//		GenCons test = new GenCons(100);
//		GenCons test2 = new GenCons(123.5F);
//		
//		test.showVal();
//		test.showVal();
//		
//	}
//}

/*-----------------------------------------------------------------------------------------
 	GenCons( ), Number'ın bir alt sınıfı olması gereken genel türde bir parametre belirttiğinden, 
 	GenCons( ) Tamsayı, Float veya Double dahil olmak üzere herhangi bir sayısal türle çağrılabilir.
 	
 	Bu nedenle, GenCons genel bir sınıf olmasa da, oluşturucusu geneldir.
------------------------------------------------------------------------------------------*/





/*---------------------------------------------------------------------------------------------------
 			Generic Interfaces
 	
 	Genel sınıflara ve yöntemlere ek olarak, genel arabirimlere de sahip olabilirsiniz.
 	Genel arabirimler tıpkı genel sınıflar gibi belirtilir. İşte bir örnek. 
 	Bazı nesne kümelerinin minimum ve maksimum değerini döndürmesi beklenen min( ) ve max( ) yöntemlerini bildiren MinMax adlı bir arabirim oluşturur.
-----------------------------------------------------------------------------------------------------*/

//interface MinMax<T extends Comparable<T>>
//{
//	T min();
//	T max();
//}
//
//class MyCLass <T extends Comparable<T>> implements MinMax<T>
//{
//
//	T[] vals;
//	
//	MyClass(T[] o) { vals = o; }
//	
//	@Override
//	public T min() {
//		T v = vals[0];
//		for(int i=1; i < vals.length; i++)
//			 if(vals[i].compareTo(v) < 0) v = vals[i];
//		return v;
//	}
//
//	@Override
//	public T max() {
//		T v = vals[0];
//		for(int i=1; i < vals.length; i++)
//			if(vals[i].compareTo(v) > 0) v = vals[i];
//		
//		return v;
//		
//	}
//	
//}


/*----------------------------------------------------------------------------------------------------------------------------
 	Bu programın çoğu yönünün anlaşılması kolay olsa da, birkaç önemli noktanın yapılması gerekir. 
 	İlk olarak, MinMax'in şu şekilde bildirildiğine dikkat edin:
 		interface MinMax<T extends Comparable<T>> {
 		
 	Genel olarak, genel bir arabirim, genel bir sınıfla aynı şekilde bildirilir.
 	Bu durumda, type parametresi T'dir ve üst sınırı Comparabledır.
 	type parametresi, comparable nesnelerin türünü belirtir.
 	
 	Daha sonra, MinMax MyClass tarafından uygulanır. 
 	Burada gösterilen MyClass bildirimine dikkat edin:
 	class MyClass<T extends Comparable<T>> implements MinMax<T> {
 	
 	T tür parametresinin MyClass tarafından bildirilme ve ardından MinMax'e geçirilme şekline özellikle dikkat edin.
 	MinMax, Comparable'ı uygulayan bir tür gerektirdiğinden, uygulayıcı sınıf (bu durumda MyClass) aynı sınırı belirtmelidir.
 	Ayrıca, bu sınır bir kez kurulduktan sonra, implements maddesinde tekrar belirtmeye gerek yoktur.
 	
 	Aslında bunu yapmak yanlış olur. Örneğin, bu satır yanlıştır ve derlenmeyecektir:
 		class MyClass<T extends Comparable<T>> implements MinMax<T extends Comparable<T>> {


	Type parametresi oluşturulduktan sonra, daha fazla değişiklik yapmadan interface e geçirilir.
	Genel olarak, bir sınıf genel bir arabirim uygularsa, en azından arabirime geçirilen bir type parametresi aldığı ölçüde, 
	bu sınıfın da genel olması gerekir. Örneğin, MyClass bildirmek için aşağıdaki girişim hatalı:
	class MyClass implements MinMax<T> { // Wrong!
	
	MyClass bir type parametresi bildirmediğinden, MinMax'e bir parametre geçirmenin bir yolu yoktur.
	Bu durumda, T tanımlayıcısı basitçe bilinmemektedir ve derleyici bir hata bildirir. 
	
	Elbette, bir sınıf burada gösterildiği gibi belirli bir genel arabirim türü uyguluyorsa:
	class MyClass implements MinMax<Integer> { // OK
	o zaman uygulayıcı sınıfın genel olması gerekmez.
	
	Genel arayüz iki avantaj sunar. İlk olarak, farklı veri türleri için uygulanabilir.
	İkincisi, arayüzün uygulanabileceği veri türlerine kısıtlamalar (yani sınırlar) koymanıza olanak tanır.
	
	MinMax örneğinde, yalnızca Comparable arabirimi uygulayan türler T'ye geçirilebilir.
	
	Genel bir arabirim için genelleştirilmiş sözdizimi aşağıda verilmiştir:
	interface interface-name<type-param-list> { // …
	
	Burada, type-param-list, tür parametrelerinin virgülle ayrılmış bir listesidir. 
	Genel bir arabirim uygulandığında, burada gösterildiği gibi tür bağımsız değişkenlerini belirtmeniz gerekir:
	
	class class-name<type-param-list> implements interface-name<type-arg-list> {
  
  --------------------------------------------------------------------------------------------------------------------------*/






